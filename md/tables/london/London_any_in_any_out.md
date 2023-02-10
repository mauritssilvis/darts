# London dartboard any-in any-out checkouts

> Checkout tables for London dartboard any-in any-out darts games 

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) > [Markdown](../..) project provides checkout tables for darts games with the London dartboard played in any-in any-out mode.

Darts games commonly require players to score x01 points, where x is an integer.
The following table shows the minimum required number of darts to reach such scores in London dartboard any-in, any-out games.
In addition, the corresponding number of possible checkouts is shown.

The most exciting games will be those with a comparatively small number of checkouts.
Checkout tables for these scores are given below.
Additional tables can be generated using the `darts` toolbox of the [darts](https://github.com/mauritssilvis/darts) project.

|         Score | Darts |     Checkouts |
|--------------:|------:|--------------:|
|   [101](#101) |     2 |             2 |
|           201 |     4 |           772 |
|           301 |     6 |        66,144 |
|   [401](#401) |     7 |           392 |
|           501 |     9 |       223,026 |
|           601 |    11 |    24,066,702 |
|   [701](#701) |    12 |         3,432 |
|           801 |    14 |    10,666,320 |
|           901 |    16 | 1,821,306,720 |
| [1001](#1001) |    17 |        13,872 |

## 101

| Score |   1 |   2 | # |
|------:|----:|----:|--:|
|   101 |   * |   * | 2 |
|       | T17 | D25 | 2 |

This table was generated using the command:

```shell
darts checkouts -j any 101 101
```

## 401

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|
|   401 |   * |   * |   * |   * |   * |   * |   * | 392 |
|       | T20 | T20 | T20 | T20 | T20 | T17 | D25 |  42 |
|       | T20 | T20 | T20 | T20 | T19 | T18 | D25 | 210 |
|       | T20 | T20 | T20 | T19 | T19 | T19 | D25 | 140 |

This table was generated using the command:

```shell
darts checkouts -j any 401 401
```

## 701

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |    # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|-----:|
|   701 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 3432 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |  132 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | 1320 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | 1980 |

This table was generated using the command:

```shell
darts checkouts -j any 701 701
```

## 1001

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |  13 |  14 |  15 |  16 |  17 |     # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|------:|
|  1001 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 13872 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |   272 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 |  4080 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 |  9520 |

This table was generated using the command:

```shell
darts checkouts -j any 1001 1001
```

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../LICENSE.md), or later.
