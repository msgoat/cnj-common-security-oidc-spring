package group.msg.at.cloud.common.security.oidc.resource.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

/**
 * {@code WebSecurityCustomizer} which adds a custom JWT Authentication Converter to the web security.
 */
final class JwtWebSecurityCustomizer implements WebSecurityCustomizer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final JwtAuthenticationConverter converter;

    public JwtWebSecurityCustomizer(JwtAuthenticationConverter converter) {
        this.converter = converter;
    }

    @Override
    public void customize(WebSecurity web) {
        OAuth2ResourceServerConfigurer configurer = web.getConfigurer(OAuth2ResourceServerConfigurer.class);
        if (configurer != null) {
            configurer.jwt().jwtAuthenticationConverter(this.converter);
        } else {
            logger.warn("*** CONFIG *** Expected web security to have an OAuth2ResourceServerConfigurer but got none!");
        }
    }
}
