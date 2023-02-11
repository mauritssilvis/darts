# London dartboard any-in, master-out checkouts

> Checkout tables for any-in, master-out darts games with the London dartboard

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) > [Markdown](../..) project provides checkout tables for darts games with the London dartboard played in any-in, master-out mode.

Darts games commonly require players to score x01 points, where x is an integer.
The following table shows the required number of darts to reach such scores in London dartboard any-in, master-out games.
In addition, the corresponding number of possible checkouts is shown.

|         Score | Darts |     Checkouts |
|--------------:|------:|--------------:|
|   [101](#101) |     2 |             2 |
|           201 |     4 |           772 |
|           301 |     6 |        65,007 |
|   [401](#401) |     7 |           392 |
|           501 |     9 |       223,026 |
|           601 |    11 |    24,032,174 |
|   [701](#701) |    12 |         3,432 |
|           801 |    14 |    10,666,320 |
|           901 |    16 | 1,820,922,471 |
| [1001](#1001) |    17 |        13,872 |

The most exciting games will be those with a comparatively small number of possible checkouts.
Checkout tables for these scores are given below.
Additional tables can be generated using the `darts` toolbox of the [darts](https://github.com/mauritssilvis/darts) project.

## 101

| Score |   1 |   2 | # |
|------:|----:|----:|--:|
|   101 |   * |   * | 2 |
|       | T17 | D25 | 1 |
|       | D25 | T17 | 1 |

This table was generated using the command:

```shell
darts checkouts -j master 101 101
```

## 401

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|
|   401 |   * |   * |   * |   * |   * |   * |   * | 392 |
|       | T20 | T20 | T20 | T20 | T20 | T17 | D25 |   6 |
|       | T20 | T20 | T20 | T20 | T20 | D25 | T17 |   6 |
|       | T20 | T20 | T20 | T20 | T19 | T18 | D25 |  30 |
|       | T20 | T20 | T20 | T20 | T19 | D25 | T18 |  30 |
|       | T20 | T20 | T20 | T20 | T18 | D25 | T19 |  30 |
|       | T20 | T20 | T20 | T20 | T17 | D25 | T20 |  30 |
|       | T20 | T20 | T20 | T19 | T19 | T19 | D25 |  20 |
|       | T20 | T20 | T20 | T19 | T19 | D25 | T19 |  60 |
|       | T20 | T20 | T20 | T19 | T18 | D25 | T20 | 120 |
|       | T20 | T20 | T19 | T19 | T19 | D25 | T20 |  60 |

This table was generated using the command:

```shell
darts checkouts -j master 401 401
```

## 701

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |    # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|-----:|
|   701 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 3432 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |   11 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D25 | T17 |   11 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 |  110 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 | T18 |  110 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T18 | D25 | T19 |  110 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 | T20 |  110 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 |  165 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | D25 | T19 |  495 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | T20 |  990 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | T20 | 1320 |

This table was generated using the command:

```shell
darts checkouts -j master 701 701
```

## 1001

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |  13 |  14 |  15 |  16 |  17 |     # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|------:|
|  1001 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 13872 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |    16 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D25 | T17 |    16 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 |   240 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 | T18 |   240 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T18 | D25 | T19 |   240 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 | T20 |   240 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 |   560 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | D25 | T19 |  1680 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | T20 |  3360 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | T20 |  7280 |

This table was generated using the command:

```shell
darts checkouts -j master 1001 1001
```

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../../../LICENSE.md), or later.