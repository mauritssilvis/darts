# London dartboard any-in, double-out checkouts

> Checkout tables for any-in, double-out darts games with the London dartboard

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) > [Markdown](../..) project provides checkout tables for darts games with the London dartboard played in any-in, double-out mode.

Darts games commonly require players to score x01 points, where x is an integer.
The following table shows the required number of darts to reach such scores in London dartboard any-in, double-out games.
In addition, the corresponding number of possible checkouts is shown.

|         Score | Darts |   Checkouts |
|--------------:|------:|------------:|
|   [101](#101) |     2 |           1 |
|           201 |     4 |          73 |
|           301 |     6 |      15,681 |
|   [401](#401) |     7 |          56 |
|   [501](#501) |     9 |       3,944 |
|           601 |    11 |   3,658,382 |
|   [701](#701) |    12 |         286 |
|           801 |    14 |      38,740 |
|           901 |    16 | 202,772,073 |
| [1001](#1001) |    17 |         816 |

The most exciting games will be those with a comparatively small number of possible checkouts.
Checkout tables for these scores are given below.
A checkout table for 501 points was included as it is a typical score in darts games.
Additional tables can be generated using the `darts` toolbox of the [darts](https://github.com/mauritssilvis/darts) project.

## 101

| Score |   1 |   2 | # |
|------:|----:|----:|--:|
|   101 |   * |   * | 1 |
|       | T17 | D25 | 1 |

This table was generated using the command:

```shell
darts checkouts 101 101
```

## 401

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |  # |
|------:|----:|----:|----:|----:|----:|----:|----:|---:|
|   401 |   * |   * |   * |   * |   * |   * |   * | 56 |
|       | T20 | T20 | T20 | T20 | T20 | T17 | D25 |  6 |
|       | T20 | T20 | T20 | T20 | T19 | T18 | D25 | 30 |
|       | T20 | T20 | T20 | T19 | T19 | T19 | D25 | 20 |

This table was generated using the command:

```shell
darts checkouts 401 401
```

## 501

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |    # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|-----:|
|   501 |   * |   * |   * |   * |   * |   * |   * |   * |   * | 3944 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D12 |    8 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D15 |    8 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T15 | D18 |    8 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D15 |   56 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 | D17 |   56 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T16 | D18 |   56 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D17 | D25 |   56 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T18 | T17 | D18 |   56 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 | D20 |   56 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D20 | D25 |   56 |
|       | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D15 |   56 |
|       | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T17 | D18 |  168 |
|       | T20 | T20 | T20 | T20 | T20 | T19 | T18 | T18 | D18 |  168 |
|       | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | D20 |  336 |
|       | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D20 | D25 |  336 |
|       | T20 | T20 | T20 | T20 | T20 | T17 | D25 | D25 | D25 |  168 |
|       | T20 | T20 | T20 | T20 | T19 | T19 | T19 | T18 | D18 |  280 |
|       | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | D20 |  280 |
|       | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D20 | D25 |  280 |
|       | T20 | T20 | T20 | T20 | T19 | T18 | D25 | D25 | D25 |  840 |
|       | T20 | T20 | T20 | T19 | T19 | T19 | T19 | T19 | D18 |   56 |
|       | T20 | T20 | T20 | T19 | T19 | T19 | D25 | D25 | D25 |  560 |

This table was generated using the command:

```shell
darts checkouts 501 501
```

## 701

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |   # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|
|   701 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 286 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |  11 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | 110 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | 165 |

This table was generated using the command:

```shell
darts checkouts 701 701
```

## 1001

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |  13 |  14 |  15 |  16 |  17 |   # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|
|  1001 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 816 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |  16 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | 240 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | 560 |

This table was generated using the command:

```shell
darts checkouts 1001 1001
```

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../../../LICENSE.md), or later.
