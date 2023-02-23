package group.msg.at.cloud.common.security.oidc.resource.server;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * OpenID Connect specific JWT granted authorities converter, which extracts the users roles passed in claim {@code groups}
 * from the given OpenID Connect ID token.
 *
 * @author michael.theis@msg.group
 */
final class IdTokenGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    private static final String DEFAULT_AUTHORITY_PREFIX = "ROLE_";

    private static final Collection<String> WELL_KNOWN_AUTHORITIES_CLAIM_NAMES =
            List.of("groups");

    private String authorityPrefix = DEFAULT_AUTHORITY_PREFIX;

    private String authoritiesClaimName;

    /**
     * Extract {@link GrantedAuthority}s from the given {@link Jwt}.
     */
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String authority : getAuthorities(jwt)) {
            grantedAuthorities.add(new SimpleGrantedAuthority(this.authorityPrefix + authority));
        }
        return grantedAuthorities;
    }

    /**
     * Sets the prefix to use for {@link GrantedAuthority authorities} mapped by this converter.
     * Defaults to {@link IdTokenGrantedAuthoritiesConverter#DEFAULT_AUTHORITY_PREFIX}.
     *
     * @param authorityPrefix The authority prefix
     */
    public void setAuthorityPrefix(String authorityPrefix) {
        Assert.hasText(authorityPrefix, "authorityPrefix cannot be empty");
        this.authorityPrefix = authorityPrefix;
    }

    /**
     * Sets the name of token claim to use for mapping {@link GrantedAuthority authorities} by this converter.
     * Defaults to {@link IdTokenGrantedAuthoritiesConverter#WELL_KNOWN_AUTHORITIES_CLAIM_NAMES}.
     *
     * @param authoritiesClaimName The token claim name to map authorities
     */
    public void setAuthoritiesClaimName(String authoritiesClaimName) {
        Assert.hasText(authoritiesClaimName, "authoritiesClaimName cannot be empty");
        this.authoritiesClaimName = authoritiesClaimName;
    }

    private String getAuthoritiesClaimName(Jwt jwt) {

        if (this.authoritiesClaimName != null) {
            return this.authoritiesClaimName;
        }

        for (String claimName : WELL_KNOWN_AUTHORITIES_CLAIM_NAMES) {
            if (jwt.getClaims().containsKey(claimName)) {
                return claimName;
            }
        }
        return null;
    }

    private Collection<String> getAuthorities(Jwt jwt) {
        Collection<String> result = Collections.emptyList();
        String claimName = getAuthoritiesClaimName(jwt);
        if (claimName != null) {
            result = jwt.getClaimAsStringList(claimName);
        }
        return result;
    }
}
