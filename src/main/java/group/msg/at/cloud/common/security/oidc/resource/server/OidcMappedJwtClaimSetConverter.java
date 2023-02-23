package group.msg.at.cloud.common.security.oidc.resource.server;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.converter.ClaimConversionService;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;

import java.net.URL;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * OpenID Connect specific extension of the {@link MappedJwtClaimSetConverter} which supports all OIDC standard claims as well.
 *
 * @author michael.theis@msg.group
 * @version 1.0
 * @since 1.0.0
 */
final class OidcMappedJwtClaimSetConverter implements Converter<Map<String, Object>, Map<String, Object>> {
    private final static ConversionService CONVERSION_SERVICE = ClaimConversionService.getSharedInstance();
    private final static TypeDescriptor OBJECT_TYPE_DESCRIPTOR = TypeDescriptor.valueOf(Object.class);
    private final static TypeDescriptor STRING_TYPE_DESCRIPTOR = TypeDescriptor.valueOf(String.class);
    private final static TypeDescriptor INSTANT_TYPE_DESCRIPTOR = TypeDescriptor.valueOf(Instant.class);
    private final static TypeDescriptor URL_TYPE_DESCRIPTOR = TypeDescriptor.valueOf(URL.class);
    private final static TypeDescriptor BOOLEAN_TYPE_DESCRIPTOR = TypeDescriptor.valueOf(Boolean.class);

    private final MappedJwtClaimSetConverter delegate;

    public OidcMappedJwtClaimSetConverter() {
        Map<String, Converter<Object, ?>> oidcClaimConverters = new HashMap<>();
        oidcClaimConverters.put(OidcClaimNames.NAME, getConverter(STRING_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.GIVEN_NAME, getConverter(STRING_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.FAMILY_NAME, getConverter(STRING_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.MIDDLE_NAME, getConverter(STRING_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.NICKNAME, getConverter(STRING_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.PREFERRED_USERNAME, getConverter(STRING_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.PROFILE, getConverter(URL_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.PICTURE, getConverter(URL_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.WEBSITE, getConverter(URL_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.EMAIL, getConverter(STRING_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.EMAIL_VERIFIED, getConverter(BOOLEAN_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.GENDER, getConverter(STRING_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.BIRTHDATE, getConverter(STRING_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.ZONEINFO, getConverter(STRING_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.PHONE_NUMBER, getConverter(STRING_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.PHONE_NUMBER_VERIFIED, getConverter(BOOLEAN_TYPE_DESCRIPTOR));
        oidcClaimConverters.put(OidcClaimNames.UPDATED_AT, getConverter(INSTANT_TYPE_DESCRIPTOR));
        this.delegate = MappedJwtClaimSetConverter.withDefaults(oidcClaimConverters);
    }

    private static Converter<Object, ?> getConverter(TypeDescriptor targetDescriptor) {
        return source -> CONVERSION_SERVICE.convert(source, OBJECT_TYPE_DESCRIPTOR, targetDescriptor);
    }

    @Override
    public Map<String, Object> convert(Map<String, Object> claims) {
        Map<String, Object> convertedClaims = this.delegate.convert(claims);

        String username = (String) convertedClaims.get(OidcClaimNames.PREFERRED_USERNAME);
        convertedClaims.put("sub", username);

        return convertedClaims;
    }

}