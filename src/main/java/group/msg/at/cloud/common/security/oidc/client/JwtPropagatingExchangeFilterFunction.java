package group.msg.at.cloud.common.security.oidc.client;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

/**
 * {@code ExchangeFilterFunction} that propagates existing JWT tokens to downstream services.
 *
 * @author Michael Theis (msg)
 */
public class JwtPropagatingExchangeFilterFunction implements ExchangeFilterFunction {

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        ClientRequest mutatedRequest = request;
        if (!request.headers().containsKey(HttpHeaders.AUTHORIZATION)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Object credentials = null;
            if (auth != null) {
                credentials = auth.getCredentials();
            }
            if (credentials != null && AbstractOAuth2Token.class.isAssignableFrom(credentials.getClass())) {
                AbstractOAuth2Token jwt = (AbstractOAuth2Token) credentials;
                mutatedRequest = ClientRequest.from(request).header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt.getTokenValue()).build();
            }
        }
        return next.exchange(mutatedRequest);
    }
}
