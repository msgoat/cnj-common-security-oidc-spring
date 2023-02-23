package group.msg.at.cloud.common.security.oidc.client;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

/**
 * {@code RestTemplateCustomizer} that adds an {@code JwtPropa} to {@RestTemplate}s.
 *
 * @author Michael Theis (msg)
 */
public class JwtPropagatingRestTemplateCustomizer implements RestTemplateCustomizer {

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.getClientHttpRequestInitializers().add(new JwtPropagatingRequestInitializer());
    }
}
