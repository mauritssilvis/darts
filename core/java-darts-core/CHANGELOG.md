# Changelog

All notable changes to the [Java darts core](https://github.com/mauritssilvis/darts/tree/main/core/java-darts-core) project are documented in this file.

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
- Update the JUnit version from 5.9.3 to 5.10.0.

## [0.7.2] - 2023-05-06

### Changed

#### Dependencies

- Update the Gradle version from 8.1-RC-3 to 8.1.1.
- Update the JUnit version from 5.9.2 to 5.9.3.

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

- Add single, double, triple and quadruple dartboard fields (`TypedField`).
- Add the London dartboard (`LondonBoard`).
- Add the Quadro dartboard (`QuadroBoard`).
- Add the Yorkshire dartboard (`YorkshireBoard`).
- Add a string-representation-based serializer for dartboards (`StringBoardSerializer`).
- Add a Markdown serializer for dartboards (`MarkdownBoardSerializer`).
- Add an HTML serializer for dartboards (`HtmlBoardSerializer`).
- Add a JSON serializer for dartboards (`JsonBoardSerializer`).

#### Pathfinders

- Create a brute-force pathfinder that considers all combinations of weighted edges between nodes (`CartesianPathfinder`).
- Create an optimized pathfinder that considers sequences of edges with non-increasing weights (`DescendingPathfinder`).

#### Checkout finders

- Create a brute-force checkout finder that considers all combinations of dartboard fields (`CartesianCheckoutFinder`).
- Create an optimized checkout finder that considers sequences of dartboard fields with non-increasing scores (`DescendingCheckoutFinder`).

#### Checkout tables

- Create an ascending checkout table generator (`AscendingTableGenerator`).
- Add a string-representation-based serializer for checkout tables (`StringTableSerializer`).
- Add a Markdown serializer for checkout tables (`MarkdownTableSerializer`).
- Add an HTML serializer for checkout tables (`HtmlTableSerializer`).
- Add a JSON serializer for checkout tables (`JsonTableSerializer`).
