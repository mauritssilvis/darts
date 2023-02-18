# London dartboard master-in, double-out checkouts

> Checkout tables for master-in, double-out darts games with the London dartboard

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) > [Markdown](../..) project provides checkout tables for darts games with the London dartboard played in master-in, double-out mode.

Darts games commonly require players to score x01 points, where x is an integer.
The following table shows the required number of darts to reach such scores in London dartboard master-in, double-out games.
In addition, the corresponding number of possible checkouts is shown.

|         Score | Darts |   Checkouts |
|--------------:|------:|------------:|
|   [101](#101) |     2 |           1 |
|           201 |     4 |          73 |
|           301 |     6 |      15,645 |
|   [401](#401) |     7 |          56 |
|   [501](#501) |     9 |       3,944 |
|           601 |    11 |   3,658,161 |
|   [701](#701) |    12 |         286 |
|           801 |    14 |      38,740 |
|           901 |    16 | 202,771,392 |
| [1001](#1001) |    17 |         816 |

The most exciting games will be those with relatively few possible checkouts.
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
darts checkouts -i master 101 101
```

## 401

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |  # |
|------:|----:|----:|----:|----:|----:|----:|----:|---:|
|   401 |   * |   * |   * |   * |   * |   * |   * | 56 |
|       | T20 | T20 | T20 | T20 | T20 | T17 | D25 |  5 |
|       | T20 | T20 | T20 | T20 | T19 | T18 | D25 | 20 |
|       | T20 | T20 | T20 | T19 | T19 | T19 | D25 | 10 |
|       | T19 | T20 | T20 | T20 | T20 | T18 | D25 |  5 |
|       | T19 | T20 | T20 | T20 | T19 | T19 | D25 | 10 |
|       | T18 | T20 | T20 | T20 | T20 | T19 | D25 |  5 |
|       | T17 | T20 | T20 | T20 | T20 | T20 | D25 |  1 |

This table was generated using the command:

```shell
darts checkouts -i master 401 401
```

## 501

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |     # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|------:|
|   501 |   * |   * |   * |   * |   * |   * |   * |   * |   * | 3,944 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D12 |     7 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D15 |     7 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T15 | D18 |     7 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D15 |    42 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 | D17 |    42 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T16 | D18 |    42 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D17 | D25 |    42 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T18 | T17 | D18 |    42 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 | D20 |    42 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D20 | D25 |    42 |
|       | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D15 |    35 |
|       | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T17 | D18 |   105 |
|       | T20 | T20 | T20 | T20 | T20 | T19 | T18 | T18 | D18 |   105 |
|       | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | D20 |   210 |
|       | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D20 | D25 |   210 |
|       | T20 | T20 | T20 | T20 | T20 | T17 | D25 | D25 | D25 |   105 |
|       | T20 | T20 | T20 | T20 | T19 | T19 | T19 | T18 | D18 |   140 |
|       | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | D20 |   140 |
|       | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D20 | D25 |   140 |
|       | T20 | T20 | T20 | T20 | T19 | T18 | D25 | D25 | D25 |   420 |
|       | T20 | T20 | T20 | T19 | T19 | T19 | T19 | T19 | D18 |    21 |
|       | T20 | T20 | T20 | T19 | T19 | T19 | D25 | D25 | D25 |   210 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D12 |     1 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T18 | D15 |     7 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | D25 | D17 |     7 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T16 | D18 |     7 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | D17 | D25 |     7 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | D15 |    21 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T19 | T17 | D18 |    42 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T18 | T18 | D18 |    21 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T18 | D25 | D20 |    42 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T18 | D20 | D25 |    42 |
|       | T19 | T20 | T20 | T20 | T20 | T19 | T19 | T18 | D18 |   105 |
|       | T19 | T20 | T20 | T20 | T20 | T19 | T19 | D25 | D20 |   105 |
|       | T19 | T20 | T20 | T20 | T20 | T19 | T19 | D20 | D25 |   105 |
|       | T19 | T20 | T20 | T20 | T20 | T18 | D25 | D25 | D25 |   105 |
|       | T19 | T20 | T20 | T20 | T19 | T19 | T19 | T19 | D18 |    35 |
|       | T19 | T20 | T20 | T20 | T19 | T19 | D25 | D25 | D25 |   210 |
|       | T18 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D15 |     7 |
|       | T18 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D18 |     7 |
|       | T18 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D18 |    42 |
|       | T18 | T20 | T20 | T20 | T20 | T20 | T19 | D25 | D20 |    42 |
|       | T18 | T20 | T20 | T20 | T20 | T20 | T19 | D20 | D25 |    42 |
|       | T18 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D18 |    35 |
|       | T18 | T20 | T20 | T20 | T20 | T19 | D25 | D25 | D25 |   105 |
|       | T17 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D15 |     1 |
|       | T17 | T20 | T20 | T20 | T20 | T20 | T20 | T18 | D18 |     7 |
|       | T17 | T20 | T20 | T20 | T20 | T20 | T20 | D25 | D20 |     7 |
|       | T17 | T20 | T20 | T20 | T20 | T20 | T20 | D20 | D25 |     7 |
|       | T17 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | D18 |    21 |
|       | T17 | T20 | T20 | T20 | T20 | T20 | D25 | D25 | D25 |    21 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D17 |     7 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D20 |     7 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D20 |    42 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T17 | D25 | D25 |    42 |
|       | D25 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D20 |    35 |
|       | D25 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | D25 |   210 |
|       | D25 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | D25 |   140 |
|       | T16 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D18 |     7 |
|       | T15 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D18 |     1 |
|       | D20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |     7 |
|       | D20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 |    42 |
|       | D20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 |    35 |
|       | D17 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 |     7 |

This table was generated using the command:

```shell
darts checkouts -i master 501 501
```

## 701

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |   # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|
|   701 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 286 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |  10 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 |  90 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | 120 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T18 | D25 |  10 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | D25 |  45 |
|       | T18 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 |  10 |
|       | T17 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D25 |   1 |

This table was generated using the command:

```shell
darts checkouts -i master 701 701
```

## 1001

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |  13 |  14 |  15 |  16 |  17 |   # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|
|  1001 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 816 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |  15 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | 210 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | 455 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T18 | D25 |  15 |
|       | T19 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | D25 | 105 |
|       | T18 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 |  15 |
|       | T17 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | D25 |   1 |

This table was generated using the command:

```shell
darts checkouts -i master 1001 1001
```

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../../../LICENSE.md), or later.
