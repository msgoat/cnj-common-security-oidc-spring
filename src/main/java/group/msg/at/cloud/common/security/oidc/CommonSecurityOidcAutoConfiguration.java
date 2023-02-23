package group.msg.at.cloud.common.security.oidc;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Configuration class for Spring Boot's auto-configuration feature.
 */
@AutoConfiguration
@ComponentScan(basePackageClasses = CommonSecurityOidc.class)
public class CommonSecurityOidcAutoConfiguration {
}
