# Changelog
All notable changes to `cnj-common-security-oidc-springt` will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [3.0.0] - 2024-03-28
### Added
- added OWASP dependency check to build pipeline
- added tagging of git branch on successful build
### Changed
- upgraded to Java 21
- upgraded to Spring Boot 3.2.4
- upgraded Maven plugin versions
- upgraded third-party dependency versions
- removed obsolete dependency on RestAssured which caused problems by adding an unwanted dependency to commons-logging
- added SonarQube analysis to build pipeline
- consolidated build configuration

## [2.2.0] - 2023-08-23
### Changed
- added JWT propagation support for WebClient based REST clients
- added SonarQube analysis to build pipeline

## [2.1.0] - 2023-07-25
### Changed
- upgraded SL4J to 2.0.6
- upgraded to Spring Boot 3

## [2.0.0] - 2023-02-23
### Changed
- upgraded to Java 17
- upgraded to Spring Boot 3

## [1.2.0] - 2021-04-21
### Added
### Changed
- performed lift&shift to new environment AWS CloudTrain
- upgrade to latest Spring version pending

## [1.2.0] - 2020-07-22
### Added
- added support of JWT propagation
### Changed
- upgraded to Spring Boot 2.3.1

## [1.1.0] - 2020-05-20
### Added
### Changed
- moved to new toolchain based on GitHub and AWS.
- upgraded to Spring Boot 2.3.0

## [1.0.0] - 2020-02-27
### Added
- first revision with minimum OpenID Connect support.
### Changed
