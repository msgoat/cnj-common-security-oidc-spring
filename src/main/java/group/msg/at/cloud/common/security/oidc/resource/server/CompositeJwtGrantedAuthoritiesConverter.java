package group.msg.at.cloud.common.security.oidc.resource.server;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;

/**
 * {@code Composite} JWT granted authorities converter which applies multiple converters to a given JWT token.
 * <p>
 * All given converters are applied in the same order as passed to this types constructor.
 * </p>
 *
 * @author michael.theis@msg.group
 */
final class CompositeJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final List<Converter<Jwt, Collection<GrantedAuthority>>> converters;

    @SafeVarargs
    public CompositeJwtGrantedAuthoritiesConverter(Converter<Jwt, Collection<GrantedAuthority>>... converters) {
        this.converters = Arrays.asList(converters);
    }

    /**
     * Applies all converters to the given JWT token.
     *
     * @param jwt JWT Token
     * @return collection of granted authorities without duplicates; may be empty but never {@code null}
     */
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Objects.requireNonNull(jwt, "missing required parameter \"jwt\"");
        Set<GrantedAuthority> result = new LinkedHashSet<>();
        this.converters.forEach(converter -> result.addAll(converter.convert(jwt)));
        return result;
    }
}
