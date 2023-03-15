# Changelog

All notable changes to the `java-darts-cli` project (see [darts](https://github.com/mauritssilvis/darts) > [CLI](https://github.com/mauritssilvis/darts/tree/main/cli) > [Java](.)) are documented in this file.

The file format is based on [keep a changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [semantic versioning](https://semver.org/spec/v2.0.0.html).

## [0.2.0] - 2023-03-15

### Changed

#### Documentation

- Document changes in the usage of `darts`.

#### Installation

- Change the `darts` installation location.

### Removed

#### Execution

- Remove scripts to run `darts` from the project folder.

## [0.1.0] - 2023-03-15

### Added

#### Documentation

- Describe the installation of `darts`.
- Document the usage of the `darts` command-line interface.
- Document potential problems with the installation or usage of `darts`.

#### Installation

- Add scripts to install `darts` in the project folder (`install`).
 
#### Execution

- Add scripts to run `darts` from the project folder (`darts`).

#### Command-line interface

- Add a command-line interface (`DartsApp`).
- Add a command to print dartboards (`BoardsCommand`).
- Add a command to generate darts checkout tables (`CheckoutsCommand`).

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
- Add support for master and double check-ins and checkouts (`CheckMode`).
- Add support for fixing the number of throws (`ThrowMode`).
- Add a string-representation-based serializer for checkout tables (`StringTableSerializer`).
- Add a Markdown serializer for checkout tables (`MarkdownTableSerializer`).
- Add an HTML serializer for checkout tables (`HtmlTableSerializer`).
- Add a JSON serializer for checkout tables (`JsonTableSerializer`).
