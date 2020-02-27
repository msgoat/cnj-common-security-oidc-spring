package group.msg.at.cloud.common.security.oidc;

import org.springframework.security.oauth2.jwt.JwtClaimNames;

/**
 * Extends {@link JwtClaimNames} with standard OpenID Connect Standard claims.
 *
 * @author michael.theis@msg.group
 * @version 1.0
 * @see <a target="_blank" href="https://openid.net/specs/openid-connect-core-1_0.html#StandardClaims" >OpenID Connect Core Spec 1.0</a>
 * @since 1.0.0
 */
public interface OidcClaimNames extends JwtClaimNames {

    /**
     * {@code name} - (string) End-User's full name in displayable form including all name parts, possibly including titles and suffixes, ordered according to the End-User's locale and preferences
     */
    String NAME = "name";

    /**
     * {@code given_name} - (string) Given name(s) or first name(s) of the End-User. Note that in some cultures, people can have multiple given names; all can be present, with the names being separated by space characters.
     */
    String GIVEN_NAME = "given_name";

    /**
     * {@code family_name} - (string) Surname(s) or last name(s) of the End-User. Note that in some cultures, people can have multiple family names or no family name; all can be present, with the names being separated by space characters.
     */
    String FAMILY_NAME = "family_name";

    /**
     * {@code middle_name} - (string) Middle name(s) of the End-User. Note that in some cultures, people can have multiple middle names; all can be present, with the names being separated by space characters. Also note that in some cultures, middle names are not used.
     */
    String MIDDLE_NAME = "middle_name";

    /**
     * {@code nickname} - (string) Casual name of the End-User that may or may not be the same as the {@code given_name}. For instance, a nickname value of Mike might be returned alongside a given_name value of Michael
     */
    String NICKNAME = "nickname";

    /**
     * {@code preferred_username} - (string) Shorthand name by which the End-User wishes to be referred to at the RP, such as {@code janedoe} or {@code j.doe}.	This value MAY be any valid JSON string including special characters such as @, /, or whitespace. The RP MUST NOT rely upon this value being unique!
     */
    String PREFERRED_USERNAME = "preferred_username";

    /**
     * {@code profile} - (URL) URL of the End-User's profile page. The contents of this Web page SHOULD be about the End-User.
     */
    String PROFILE = "profile";

    /**
     * {@code picture} - (URL) URL of the End-User's profile picture. This URL MUST refer to an image file (for example, a PNG, JPEG, or GIF image file), rather than to a Web page containing an image. Note that this URL SHOULD specifically reference a profile photo of the End-User suitable for displaying when describing the End-User, rather than an arbitrary photo taken by the End-User.
     */
    String PICTURE = "picture";

    /**
     * {@code website} - (string) URL of the End-User's Web page or blog. This Web page SHOULD contain information published by the End-User or an organization that the End-User is affiliated with.
     */
    String WEBSITE = "website";

    /**
     * {@code email} - (string) URL of the End-User's Web page or blog. This Web page SHOULD contain information published by the End-User or an organization that the End-User is affiliated with.
     */
    String EMAIL = "email";

    /**
     * {@code email_verified} - (boolean) User at the time the verification was performed. The means by which an e-mail address is verified is context-specific, and dependent upon the trust framework or contractual agreements within which the parties are operating.
     */
    String EMAIL_VERIFIED = "email_verified";

    /**
     * {@code gender} - (string) End-User's gender. Values defined by this specification are female and male. Other values MAY be used when neither of the defined values are applicable.
     */
    String GENDER = "gender";

    /**
     * {@code birthdate} - (date) End-User's birthday, represented as an ISO 8601:2004 YYYY-MM-DD format. The year MAY be 0000, indicating that it is omitted. To represent only the year, YYYY format is allowed. Note that depending on the underlying platform's date related function, providing just year can result in varying month and day, so the implementers need to take this factor into account to correctly process the dates.
     */
    String BIRTHDATE = "birthdate";

    /**
     * {@code zoneinfo} - (string) String from zoneinfo time zone database representing the End-User's time zone. For example, {@code Europe/Paris} or {@code America/Los_Angeles}.
     */
    String ZONEINFO = "zoneinfo";

    /**
     * {@code phone_number} - (string) End-User's preferred telephone number. E.164 [E.164] is RECOMMENDED as the format of this Claim, for example, {@code +1 (425) 555-1212} or {@code +56 (2) 687 2400}. If the phone number contains an extension, it is RECOMMENDED that the extension be represented using the RFC 3966 [RFC3966] extension syntax, for example, +1 (604) 555-1234;ext=5678.
     */
    String PHONE_NUMBER = "phone_number";

    /**
     * {@code phone_number_verified} - (boolean) True if the End-User's phone number has been verified; otherwise false. When this Claim Value is true, this means that the OP took affirmative steps to ensure that this phone number was controlled by the End-User at the time the verification was performed. The means by which a phone number is verified is context-specific, and dependent upon the trust framework or contractual agreements within which the parties are operating. When true, the phone_number Claim MUST be in E.164 format and any extensions MUST be represented in RFC 3966 format.
     */
    String PHONE_NUMBER_VERIFIED = "phone_number_verified";

    /**
     * {@code address} - (object) End-User's preferred postal address. The value of the address member is a JSON [RFC4627] structure containing some or all of the members defined in Section 5.1.1.
     */
    String ADDRESS = "address";

    /**
     * {@code updated_at} - (long) Time the End-User's information was last updated. Its value is a JSON number representing the number of seconds from 1970-01-01T0:0:0Z as measured in UTC until the date/time.
     */
    String UPDATED_AT = "updated_at";
}
