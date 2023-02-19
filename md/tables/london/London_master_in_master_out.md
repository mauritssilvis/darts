# London dartboard master-in, master-out checkouts

> Checkout tables for master-in, master-out darts games with the London dartboard

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) > [Markdown](../..) project provides checkout tables for darts games with the London dartboard played in master-in, master-out mode.
These tables are valid for games where the first (in) and last (out) darts must land on a double or triple.

In master-out games with the London dartboard, the last dart can score between 2 and 60 points.
The last two darts can reach 120 points, and 180 points can be scored with the last three.
As long as a double or triple has been thrown, the extensive [any-in, master-out checkout tables](London_any_in_master_out.md) apply to these scores.

As for master-in, master-out checkouts, the following can be observed.
Darts games commonly require players to score x01 points, where x is an integer.
The following table shows the required number of darts to reach such scores in London dartboard master-in, master-out games.
In addition, the corresponding number of possible checkouts is shown.

|         Score | Darts |     Checkouts |
|--------------:|------:|--------------:|
|   [101](#101) |     2 |             2 |
|           201 |     4 |           772 |
|           301 |     6 |        63,870 |
|   [401](#401) |     7 |           392 |
|           501 |     9 |       223,026 |
|           601 |    11 |    23,997,646 |
|   [701](#701) |    12 |         3,432 |
|           801 |    14 |    10,666,320 |
|           901 |    16 | 1,820,538,222 |
| [1001](#1001) |    17 |        13,872 |

The most exciting games will be those with relatively few possible checkouts.
Checkout tables for these scores are given below.
Additional tables can be generated using the `darts` toolbox of the [darts](https://github.com/mauritssilvis/darts) project.

## 101

| Score |   1 |   2 | # |
|------:|----:|----:|--:|
|   101 |   * |   * | 2 |
|       | T17 | D25 | 2 |

This table was generated using the command:

```shell
darts checkouts -i master -j master 101 101
```

## 401

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|
|   401 |   * |   * |   * |   * |   * |   * |   * | 392 |
|       | T20 | T20 | T20 | T20 | T20 | T17 | D25 |   5 |
|       | T20 | T20 | T20 | T20 | T20 | D25 | T17 |   5 |
|       | T20 | T20 | T20 | T20 | T19 | T18 | D25 |  20 |
|       | T20 | T20 | T20 | T20 | T19 | D25 | T18 |  20 |
|       | T20 | T20 | T20 | T20 | T18 | D25 | T19 |  20 |
|       | T20 | T20 | T20 | T20 | T17 | D25 | T20 |  20 |
|       | T20 | T20 | T20 | T19 | T19 | T19 | D25 |  10 |
|       | T20 | T20 | T20 | T19 | T19 | D25 | T19 |  30 |
|       | T20 | T20 | T20 | T19 | T18 | D25 | T20 |  60 |
|       | T20 | T20 | T19 | T19 | T19 | D25 | T20 |  20 |
|       | T19 | T20 | T20 | T20 | T20 | T18 | D25 |   5 |
|       | T19 | T20 | T20 | T20 | T20 | D25 | T18 |   5 |
|       | T19 | T20 | T20 | T20 | T19 | T19 | D25 |  10 |
|       | T19 | T20 | T20 | T20 | T19 | D25 | T19 |  20 |
|       | T19 | T20 | T20 | T20 | T18 | D25 | T20 |  20 |
|       | T19 | T20 | T20 | T19 | T19 | D25 | T20 |  30 |
|       | T18 | T20 | T20 | T20 | T20 | T19 | D25 |   5 |
|       | T18 | T20 | T20 | T20 | T20 | D25 | T19 |   5 |
|       | T18 | T20 | T20 | T20 | T19 | D25 | T20 |  20 |
|       | T17 | T20 | T20 | T20 | T20 | T20 | D25 |   1 |
|       | T17 | T20 | T20 | T20 | T20 | D25 | T20 |   5 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T17 |   1 |
|       | D25 | T20 | T20 | T20 | T20 | T19 | T18 |   5 |
|       | D25 | T20 | T20 | T20 | T20 | T18 | T19 |   5 |
|       | D25 | T20 | T20 | T20 | T20 | T17 | T20 |   5 |
|       | D25 | T20 | T20 | T20 | T19 | T19 | T19 |  10 |
|       | D25 | T20 | T20 | T20 | T19 | T18 | T20 |  20 |
|       | D25 | T20 | T20 | T19 | T19 | T19 | T20 |  10 |

This table was generated using the command:

```shell
darts checkouts -i master -j master 401 401
```

## 701

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |     # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|------:|
|   701 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 3,432 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |    10 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D25 | T17 |    10 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 |    90 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 | T18 |    90 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T18 | D25 | T19 |    90 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 | T20 |    90 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 |   120 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | D25 | T19 |   360 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | T20 |   720 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | T20 |   840 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T18 | D25 |    10 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D25 | T18 |    10 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | D25 |    45 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 | T19 |    90 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T18 | D25 | T20 |    90 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | D25 | T20 |   360 |
|       | T18 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 |    10 |
|       | T18 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D25 | T19 |    10 |
|       | T18 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 | T20 |    90 |
|       | T17 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D25 |     1 |
|       | T17 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D25 | T20 |    10 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 |     1 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 |    10 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T18 | T19 |    10 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | T20 |    10 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 |    45 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | T20 |    90 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | T20 |   120 |

This table was generated using the command:

```shell
darts checkouts -i master -j master 701 701
```

## 1001

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |  13 |  14 |  15 |  16 |  17 |      # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|-------:|
|  1001 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 13,872 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |     15 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D25 | T17 |     15 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 |    210 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 | T18 |    210 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T18 | D25 | T19 |    210 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 | T20 |    210 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 |    455 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | D25 | T19 |  1,365 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | T20 |  2,730 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | T20 |  5,460 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T18 | D25 |     15 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D25 | T18 |     15 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | D25 |    105 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 | T19 |    210 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T18 | D25 | T20 |    210 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | D25 | T20 |  1,365 |
|       | T18 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 |     15 |
|       | T18 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D25 | T19 |     15 |
|       | T18 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 | T20 |    210 |
|       | T17 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D25 |      1 |
|       | T17 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D25 | T20 |     15 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 |      1 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 |     15 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T18 | T19 |     15 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | T20 |     15 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 |    105 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | T20 |    210 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | T20 |    455 |

This table was generated using the command:

```shell
darts checkouts -i master -j master 1001 1001
```

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../../../LICENSE.md), or later.
