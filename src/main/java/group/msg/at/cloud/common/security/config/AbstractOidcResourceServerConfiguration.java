package group.msg.at.cloud.common.security.config;

import group.msg.at.cloud.common.security.oidc.CompositeJwtGrantedAuthoritiesConverter;
import group.msg.at.cloud.common.security.oidc.IdTokenGrantedAuthoritiesConverter;
import group.msg.at.cloud.common.security.oidc.OidcMappedJwtClaimSetConverter;
import group.msg.at.cloud.common.security.oidc.client.JwtPropagatingRestTemplateCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

/**
 * Abstract base class for all Spring Security OpenID Connect web security configuration bean in Spring Boot
 * Applications.
 * <p>
 * Concrete implementations must extend this class and add an {@code @EnableWebSecurity} annotation on type level.
 * </p>
 * <p>
 * Endpoint specific security constraints have to be added to {@link #configure(HttpSecurity)}.
 * </p>
 *
 * @author michael.theis@msg.group
 * @version 1.1
 * @since 1.0.0
 */
public abstract class AbstractOidcResourceServerConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    protected JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter result = new JwtAuthenticationConverter();
        result.setJwtGrantedAuthoritiesConverter(new CompositeJwtGrantedAuthoritiesConverter(
                new JwtGrantedAuthoritiesConverter(),
                new IdTokenGrantedAuthoritiesConverter()));
        return result;
    }

    @Bean
    protected JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
        jwtDecoder.setClaimSetConverter(new OidcMappedJwtClaimSetConverter());
        return jwtDecoder;
    }

    /**
     * Defines the security rules applied to different request URIs.
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected abstract void configure(HttpSecurity http) throws Exception;

    /**
     * Exposes a {@code RestTemplateCustomizer} to add JWT propagation to all {@code RestTemplates}.
     */
    @Bean
    public JwtPropagatingRestTemplateCustomizer jwtPropagatingRestTemplateCustomizer() {
        return new JwtPropagatingRestTemplateCustomizer();
    }
}
