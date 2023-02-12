# Changelog

All notable changes to this project are documented in this file.

The file format is based on [keep a changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [semantic versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

#### Pathfinders

- Create a brute-force pathfinder that considers all combinations of weighted edges between nodes (`CartesianPathfinder`).
- Create an optimized pathfinder that considers sequences of edges with non-increasing weights (`DescendingPathfinder`).

#### Dartboards

- Add single, double, triple and quadruple dartboard fields (`TypedField`).
- Add the London dartboard (`LondonBoard`).
- Add the Quadro dartboard (`QuadroBoard`).
- Add the Yorkshire dartboard (`YorkshireBoard`).
- Add a string-representation-based serializer for dartboards (`StringBoardSerializer`).
- Add a Markdown serializer for dartboards (`MarkdownBoardSerializer`).
- Add a JSON serializer for dartboards (`JsonBoardSerializer`).
- Add an HTML serializer for dartboards (`HtmlBoardSerializer`).

#### Checkout finders

- Create a brute-force checkout finder that considers all combinations of dartboard fields (`CartesianCheckoutFinder`).
- Create an optimized checkout finder that considers sequences of dartboard fields with non-increasing scores (`DescendingCheckoutFinder`).

#### Checkout tables

- Create an ascending checkout table generator (`AscendingTableGenerator`).
- Add support for master and double check-ins and checkouts (`CheckMode`).
- Add a string-representation-based serializer for checkout tables (`StringTableSerializer`).
- Add a Markdown serializer for checkout tables (`MarkdownTableSerializer`).
- Add a JSON serializer for checkout tables (`JsonTableSerializer`).
- Add an HTML serializer for checkout tables (`HtmlTableSerializer`).

#### Command-line interface

- Add a command-line interface (`DartsApp`).
- Add a command to print dartboards (`BoardsCommand`).
- Add a command to generate darts checkout tables (`CheckoutsCommand`).
