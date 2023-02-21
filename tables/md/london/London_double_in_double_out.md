# London dartboard double-in, double-out checkouts

> Checkout tables for double-in, double-out darts games with the London dartboard

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) > [Tables](../..) > [Markdown](..) project provides checkout tables for darts games with the London dartboard played in double-in, double-out mode.
These tables are valid for games where the first (in) and last (out) darts must land on a double.

In double-out games with the London dartboard, the last dart can score between 2 and 50 points.
The last two darts can reach 110 points, and 170 points can be scored with the last three.
After a double has been thrown, the extensive [any-in, double-out checkout tables](London_any_in_double_out.md) apply to these scores.

As for double-in, double-out checkouts, the following can be observed.
Darts games commonly require players to score x01 points, where x is an integer.
The following table shows the required number of darts to reach such scores in London dartboard double-in, double-out games.
In addition, the corresponding number of possible checkouts is shown.

|       Score | Darts |     Checkouts |
|------------:|------:|--------------:|
|         101 |     3 |            98 |
| [201](#201) |     4 |            14 |
|         301 |     6 |         2,544 |
|         401 |     8 |       380,228 |
| [501](#501) |     9 |           574 |
|         601 |    11 |       319,586 |
|         701 |    13 |    64,693,283 |
| [801](#801) |    14 |         4,184 |
|         901 |    16 |    12,139,768 |
|        1001 |    18 | 3,400,574,560 |

The most exciting games will be those with relatively few possible checkouts.
Checkout tables for these scores are given below.
Additional tables can be generated using the `darts` toolbox of the [darts](https://github.com/mauritssilvis/darts) project.

## 201

| Score |   1 |   2 |   3 |   4 |  # |
|------:|----:|----:|----:|----:|---:|
|   201 |   * |   * |   * |   * | 14 |
|       | D25 | T20 | T19 | D17 |  2 |
|       | D25 | T20 | T17 | D20 |  2 |
|       | D25 | T19 | T18 | D20 |  2 |
|       | D25 | T17 | D25 | D25 |  2 |
|       | D20 | T20 | T17 | D25 |  2 |
|       | D20 | T19 | T18 | D25 |  2 |
|       | D17 | T20 | T19 | D25 |  2 |

This table was generated using the command:

```shell
darts checkouts -i double 201 201
```

## 501

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |   # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|
|   501 |   * |   * |   * |   * |   * |   * |   * |   * |   * | 574 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D17 |   7 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D20 |   7 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D20 |  42 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T17 | D25 | D25 |  42 |
|       | D25 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D20 |  35 |
|       | D25 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | D25 | 210 |
|       | D25 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | D25 | 140 |
|       | D20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |   7 |
|       | D20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 |  42 |
|       | D20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 |  35 |
|       | D17 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 |   7 |

This table was generated using the command:

```shell
darts checkouts -i double 501 501
```

## 801

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |  13 |  14 |     # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|------:|
|   801 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 4,184 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D17 |    12 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D20 |    12 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D20 |   132 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 | D25 |   132 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D20 |   220 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | D25 | 1,320 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | D25 | 1,980 |
|       | D20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |    12 |
|       | D20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 |   132 |
|       | D20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 |   220 |
|       | D17 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 |    12 |

This table was generated using the command:

```shell
darts checkouts -i double 801 801
```

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../../../LICENSE.md), or later.
