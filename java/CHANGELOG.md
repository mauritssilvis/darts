# Changelog

All notable changes to this project are documented in this file.

The file format is based on [keep a changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [semantic versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

#### Pathfinders

- Create a pathfinder that considers sequences of edges with non-increasing weights (`DescendingPathfinder`).
- Create a pathfinder that considers all combinations of weighted edges between nodes (`CartesianPathfinder`).

#### Dartboards

- Add support for the Yorkshire dartboard (`YorkshireBoard`).
- Add support for the Quadro dartboard (`QuadroBoard`).
- Add support for the London dartboard (`LondonBoard`).
- Add support for single, double, triple and quadruple dartboard fields (`TypedField`).

#### Checkout finders

- Create a checkout finder that considers sequences of dartboard fields with non-increasing scores (`DescendingCheckoutFinder`).
- Create a checkout finder that considers all combinations of dartboard fields (`CartesianCheckoutFinder`).
