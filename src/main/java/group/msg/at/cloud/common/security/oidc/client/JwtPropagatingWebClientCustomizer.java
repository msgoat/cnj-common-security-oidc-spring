package group.msg.at.cloud.common.security.oidc.client;

import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * {@code WebClientCustomizer} that adds the {@code JwtPropagatingExchangeFilterFunction}
 * to the exchange filter function chain of a given {@code WebClient.Builder} in
 * order to ensure that JWT tokens are promoted to downstream services.
 *
 * @author Michael Theis (msg)
 */
public class JwtPropagatingWebClientCustomizer implements WebClientCustomizer {
    @Override
    public void customize(WebClient.Builder webClientBuilder) {
        webClientBuilder.filter(new JwtPropagatingExchangeFilterFunction());
    }
}
