# darts

> A computational toolbox aimed at the game of darts

## Introduction

In darts games, there is always something to compute.
How many points did I score?
How many are left?
What is my average score?
What percentage of my darts successfully hit a double?

This project focuses on the following questions:

- How can I reach zero in a game of darts?
- How many of these so-called checkouts are there?

To answer these questions, this project presents a computational toolbox going by the name of `darts`.

## Features

`darts` can determine all possible darts checkouts and generate checkout tables for any range of scores.
This toolbox supports several dartboards, including the ‘standard’ London dartboard and the Quadro 240 board.
In addition, several game modes are supported, including modes that require players to start or end with double or triple scores.
Finally, `darts` can provide output in several formats: easily readable Markdown tables, HTML tables for web pages and JSON objects for computer processing.

Currently, `darts` is available as a Java-based command-line application.
More information about the installation and use of this app can be found in the [Java-based command-line interface](cli/java-darts-cli) readme.
You can also check out an extensive set of [checkout tables](tables/md-darts-tables) generated using `darts`.

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the [GNU General Public License v3.0](LICENSE.md) or later.
