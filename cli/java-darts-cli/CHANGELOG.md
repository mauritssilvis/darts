# Changelog

All notable changes to the `java-darts-cli` project (see [darts](https://github.com/mauritssilvis/darts) > [CLIs](https://github.com/mauritssilvis/darts/tree/main/cli) > [Java](https://github.com/mauritssilvis/darts/tree/main/cli/java-darts-cli)) are documented in this file.

The file format is based on [keep a changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [semantic versioning](https://semver.org/spec/v2.0.0.html).

## [0.7.3] - 2023-08-27

### Changed

#### Dependencies

- Update the Gradle version from 8.1.1 to 8.3.
- Update the Foojay resolver convention version from 0.4.0 to 0.7.0.
- Update the refreshVersions version from 0.51 to 0.60.
- Update the Lombok version from 1.18.26 to 1.18.28.
- Update the picocli version from 4.7.3 to 4.7.5.
- Update the JUnit version from 5.9.3 to 5.10.0.

## [0.7.2] - 2023-05-06

### Changed

#### Dependencies

- Update the Gradle version from 8.1-RC-3 to 8.1.1.
- Update the picocli version from 4.7.2 to 4.7.3.
- Update the JUnit version from 5.9.2 to 5.9.3.

## [0.7.1] - 2023-04-10

### Changed

#### Dependencies

- Update the Gradle version from 8.1-RC-2 to 8.1-RC-3.
- Update the picocli version from 4.7.1 to 4.7.2.

### Fixed

#### Installation

- Fix the line endings of the installation batch script.

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

### Removed

#### Dartboards

- Move the dartboard API to the `java-darts-api` project.
- Move the dartboard implementations and serializers to the `java-darts-core` project.

#### Pathfinders

- Move the pathfinder API to the `java-darts-api` project.
- Move the pathfinder implementations to the `java-darts-core` project.

#### Checkout finders

- Move the checkout finder API to the `java-darts-api` project.
- Move the checkout finder implementations to the `java-darts-core` project.

#### Checkout tables

- Move the checkout table API to the `java-darts-api` project.
- Move the checkout table implementations and serializers to the `java-darts-core` project.

## [0.4.0] - 2023-03-18

### Changed

#### Command-line interface

- Load the version from the project manifest file.

#### General

- Split the code into CLI, API and core packages.

## [0.3.0] - 2023-03-17

### Added

#### Deployment

- Set up Maven Central deployment.

## [0.2.0] - 2023-03-15

### Changed

#### Installation

- Change the `darts` installation location.

#### Documentation

- Document changes in the usage of `darts`.

#### Dependencies

- Update the Gradle version from 8.0 to 8.0.2.
- Update the Lombok version from 1.18.24 to 1.18.26.
- Update the JUnit version from 5.9.1 to 5.9.2.

### Removed

#### Execution

- Remove scripts to run `darts` from the project folder.

## [0.1.0] - 2023-03-15

### Added

#### Documentation

- Describe the installation of `darts`.
- Document the usage of the `darts` command-line interface.
- Document potential problems with the installation and usage of `darts`.

#### Installation

- Add scripts to install `darts` in the project folder (`install`).

#### Execution

- Add scripts to run `darts` from the project folder (`darts`).

#### Command-line interface

- Add a command-line interface (`DartsApp`).
- Add a command to print dartboards (`BoardsCommand`).
- Add a command to generate darts checkout tables (`CheckoutsCommand`).

#### Dartboards

- Add support for multiple dartboard field types (`FieldType`).
- Add single, double, triple and quadruple dartboard fields (`TypedField`).
- Add support for multiple dartboard types (`BoardType`).
- Add the London dartboard (`LondonBoard`).
- Add the Quadro dartboard (`QuadroBoard`).
- Add the Yorkshire dartboard (`YorkshireBoard`).
- Add support for multiple dartboard output formats (`OutputFormat`).
- Add a string-representation-based serializer for dartboards (`StringBoardSerializer`).
- Add a Markdown serializer for dartboards (`MarkdownBoardSerializer`).
- Add an HTML serializer for dartboards (`HtmlBoardSerializer`).
- Add a JSON serializer for dartboards (`JsonBoardSerializer`).

#### Pathfinders

- Add support for multiple pathfinder types (`FinderType`).
- Create a brute-force pathfinder that considers all combinations of weighted edges between nodes (`CartesianPathfinder`).
- Create an optimized pathfinder that considers sequences of edges with non-increasing weights (`DescendingPathfinder`).

#### Checkout finders

- Add support for multiple checkout finder types (`FinderType`).
- Create a brute-force checkout finder that considers all combinations of dartboard fields (`CartesianCheckoutFinder`).
- Create an optimized checkout finder that considers sequences of dartboard fields with non-increasing scores (`DescendingCheckoutFinder`).

#### Checkout tables

- Add support for multiple check-in and checkout modes (`CheckMode`).
- Add support for multiple throw modes (`ThrowMode`).
- Create an ascending checkout table generator (`AscendingTableGenerator`).
- Add support for multiple checkout table output formats (`OutputFormat`).
- Add a string-representation-based serializer for checkout tables (`StringTableSerializer`).
- Add a Markdown serializer for checkout tables (`MarkdownTableSerializer`).
- Add an HTML serializer for checkout tables (`HtmlTableSerializer`).
- Add a JSON serializer for checkout tables (`JsonTableSerializer`).
