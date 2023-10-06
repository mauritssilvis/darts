# Changelog

All notable changes to the [Java darts API](https://github.com/mauritssilvis/darts/tree/main/api/java-darts-api) project are documented in this file.

The file format is based on [keep a changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [semantic versioning](https://semver.org/spec/v2.0.0.html).

## [0.8.0] - 2023-08-28

### Changed

#### Documentation

- Change the release name.

## [0.7.3] - 2023-08-27

### Changed

#### Dependencies

- Update the Gradle version from 8.1.1 to 8.3.
- Update the Foojay resolver convention version from 0.4.0 to 0.7.0.
- Update the refreshVersions version from 0.51.0 to 0.60.0.
- Update the Lombok version from 1.18.26 to 1.18.28.

## [0.7.2] - 2023-05-06

### Changed

#### Dependencies

- Update the Gradle version from 8.1-RC-3 to 8.1.1.

## [0.7.1] - 2023-04-10

### Changed

#### Dependencies

- Update the Gradle version from 8.1-RC-2 to 8.1-RC-3.

### Fixed

#### Dependencies

- Fix the line endings of the build batch script.

## [0.7.0] - 2023-04-02

### Changed

#### Dependencies

- Update the Java version from 17 to 20.
- Update the Gradle version from 8.0.2 to 8.1-RC-2.

## [0.6.0] - 2023-03-20

### Changed

#### General

- Wrap the code in a Java module.

## [0.5.0] - 2023-03-19

### Added

#### Deployment

- Set up Maven Central deployment.

#### Dartboards

- Define dartboards (`Board`).
- Define multiple dartboard types (`BoardType`).
- Define dartboard fields (`Field`).
- Define multiple dartboard field types (`FieldType`).
- Define multiple dartboard output formats (`OutputFormat`).

#### Pathfinders

- Define paths (`Path`).
- Define pathfinders (`Pathfinder`).
- Define multiple pathfinder types (`FinderType`).

#### Checkout finders

- Define throws (`Throw`).
- Define checkouts (`Checkout`).
- Define checkout finders (`CheckoutFinder`).
- Define multiple checkout finder types (`FinderType`).

#### Checkout tables

- Define checkout tables (`Table`).
- Define a checkout table type (`TableType`).
- Define checkout table generators (`TableGenerator`).
- Define multiple check-in and checkout modes (`CheckMode`).
- Define multiple throw modes (`ThrowMode`).
- Define multiple checkout table output formats (`OutputFormat`).
- Define checkout table settings (`Settings`).
- Define checkout table settings builders (`SettingsBuilder`).

#### Output

- Define serializers (`Serializer`).
- Define output formatters (`Formatter`).
- Define multiple output formats (`OutputFormat`).
