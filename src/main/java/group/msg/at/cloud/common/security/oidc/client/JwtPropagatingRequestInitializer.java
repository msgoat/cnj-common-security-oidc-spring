package group.msg.at.cloud.common.security.oidc.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestInitializer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;

/**
 * {@code ClientHttpRequestInitializer} that propagates existing JWT tokens to downstream services.
 *
 * @author Michael Theis (msg)
 * @version 1.0
 * @since 1.2.0
 */
public class JwtPropagatingRequestInitializer implements ClientHttpRequestInitializer {

    @Override
    public void initialize(ClientHttpRequest request) {
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.isAuthenticated()) {
                Object credentials = auth.getCredentials();
                if (credentials != null && AbstractOAuth2Token.class.isAssignableFrom(credentials.getClass())) {
                    AbstractOAuth2Token jwt = (AbstractOAuth2Token) credentials;
                    request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt.getTokenValue());
                }
            }
        }
    }
}
