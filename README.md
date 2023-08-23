# cnj-common-security-oidc-spring

Provides common security classes which are supposed to fill the gaps in Spring Security's OpenID Connect support.

## Synopsis

This common library has been introduced to avoid the usage of a vendor specific Spring Security adapter (like Keycloak or Microsoft Azure AD).

Additionally, it makes sure, that existing JWT bearer tokens are propagated to downstream services by adding
customizers for `RestTemplateBuilder`s and `WebClient.Builder`s.

## Status

![Build status](https://codebuild.eu-west-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiSjJMY1lpTE1YM3BCVFI0WStvVHh4cmlMaEZvckhTWmszQnhjK0J3THlYS2hkSzV4K1NVZzVtcEpma3NwdlU2cEhnRm1OcFZoME5JZmxSRll4QVNGSmk0PSIsIml2UGFyYW1ldGVyU3BlYyI6InNtSEtzcHh6aXAvMlZzZW8iLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=main)

## Release information

See changelog for latest version and release infos.

A changelog can be found in [changelog.md](changelog.md).


