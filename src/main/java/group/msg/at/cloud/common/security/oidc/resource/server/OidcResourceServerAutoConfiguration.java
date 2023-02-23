package group.msg.at.cloud.common.security.oidc.resource.server;

import group.msg.at.cloud.common.security.oidc.client.JwtPropagatingRestTemplateCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

/**
 * Abstract base class for all Spring Security OpenID Connect web security configuration bean in Spring Boot
 * Applications.
 * <p>
 * Concrete implementations must extend this class and add an {@code @EnableWebSecurity} annotation on type level.
 * </p>
 *
 * @author michael.theis@msg.group
 */
@Configuration
@ConditionalOnClass({BearerTokenAuthenticationToken.class})
public class OidcResourceServerAutoConfiguration {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        logger.info("*** CONFIG *** Adding custom JwtAuthenticationConverter to application context");
        JwtAuthenticationConverter result = new JwtAuthenticationConverter();
        result.setJwtGrantedAuthoritiesConverter(new CompositeJwtGrantedAuthoritiesConverter(
                new JwtGrantedAuthoritiesConverter(),
                new IdTokenGrantedAuthoritiesConverter()));
        return result;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        logger.info("*** CONFIG *** Adding custom JwtDecoder to application context");
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
        jwtDecoder.setClaimSetConverter(new OidcMappedJwtClaimSetConverter());
        return jwtDecoder;
    }

    /**
     * Exposes a {@code RestTemplateCustomizer} to add JWT propagation to all {@code RestTemplates}.
     */
    @Bean
    JwtPropagatingRestTemplateCustomizer jwtPropagatingRestTemplateCustomizer() {
        logger.info("*** CONFIG *** Adding JwtPropagatingRestTemplateCustomizer to application context");
        return new JwtPropagatingRestTemplateCustomizer();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        logger.info("*** CONFIG *** Adding JwtWebSecurityCustomizer to application context");
        return new JwtWebSecurityCustomizer(jwtAuthenticationConverter());
    }
}
