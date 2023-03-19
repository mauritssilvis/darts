# Changelog

All notable changes to the `java-darts-api` project (see [darts](https://github.com/mauritssilvis/darts) > [API](https://github.com/mauritssilvis/darts/tree/main/api) > [Java](.)) are documented in this file.

The file format is based on [keep a changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [semantic versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

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