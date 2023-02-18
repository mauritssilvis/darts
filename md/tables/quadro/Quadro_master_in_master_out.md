# Quadro 240 board master-in, master-out checkouts

> Checkout tables for master-in, master-out darts games with the Quadro 240 board

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) > [Markdown](../..) project provides checkout tables for darts games with the Quadro 240 board played in master-in, master-out mode.
These tables are valid for games where the first (in) and last (out) darts must land on a double or triple field.

In master-out games with the Quadro 240 board, the last dart can score between 2 and 60 points.
The last two darts can reach 120 points, and 180 points can be scored with the last three.
As long as a double or triple has been thrown, the extensive [any-in, master-out checkout tables](Quadro_any_in_master_out.md) apply to these scores.

As for master-in, master-out checkouts, the following can be observed.
Darts games commonly require players to score x01 points, where x is an integer.
The following table shows the required number of darts to reach such scores in Quadro 240 board master-in, master-out games.
In addition, the corresponding number of possible checkouts is shown.

|       Score | Darts |   Checkouts |
|------------:|------:|------------:|
| [101](#101) |     2 |           2 |
|         201 |     4 |       5,384 |
|         301 |     5 |       5,795 |
|         401 |     6 |       1,950 |
| [501](#501) |     7 |         172 |
|         601 |     9 |   5,237,514 |
|         701 |    10 |   1,028,526 |
|         801 |    11 |      83,056 |
| [901](#901) |    12 |       1,492 |
|        1001 |    14 | 557,589,546 |

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
darts checkouts -b quadro -i master -j master 101 101
```

## 501

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|
|   501 |   * |   * |   * |   * |   * |   * |   * | 172 |
|       | T20 | Q20 | Q20 | Q20 | Q20 | Q19 | T15 |   5 |
|       | T20 | Q20 | Q20 | Q20 | Q20 | Q16 | T19 |   5 |
|       | T20 | Q20 | Q20 | Q20 | Q19 | Q17 | T19 |  20 |
|       | T20 | Q20 | Q20 | Q20 | Q18 | Q18 | T19 |  10 |
|       | T20 | Q20 | Q20 | Q19 | Q19 | Q18 | T19 |  30 |
|       | T20 | Q20 | Q19 | Q19 | Q19 | Q19 | T19 |   5 |
|       | T19 | Q20 | Q20 | Q20 | Q20 | Q19 | T16 |   5 |
|       | T19 | Q20 | Q20 | Q20 | Q20 | Q16 | T20 |   5 |
|       | T19 | Q20 | Q20 | Q20 | Q19 | Q17 | T20 |  20 |
|       | T19 | Q20 | Q20 | Q20 | Q18 | Q18 | T20 |  10 |
|       | T19 | Q20 | Q20 | Q19 | Q19 | Q18 | T20 |  30 |
|       | T19 | Q20 | Q19 | Q19 | Q19 | Q19 | T20 |   5 |
|       | T18 | Q20 | Q20 | Q20 | Q20 | Q19 | T17 |   5 |
|       | T17 | Q20 | Q20 | Q20 | Q20 | Q20 | D25 |   1 |
|       | T17 | Q20 | Q20 | Q20 | Q20 | Q19 | T18 |   5 |
|       | D25 | Q20 | Q20 | Q20 | Q20 | Q20 | T17 |   1 |
|       | T16 | Q20 | Q20 | Q20 | Q20 | Q19 | T19 |   5 |
|       | T15 | Q20 | Q20 | Q20 | Q20 | Q19 | T20 |   5 |

This table was generated using the command:

```shell
darts checkouts -b quadro -i master -j master 501 501
```

## 901

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |     # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|------:|
|   901 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 1,492 |
|       | T20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | T15 |    10 |
|       | T20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q16 | T19 |    10 |
|       | T20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q17 | T19 |    90 |
|       | T20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q18 | Q18 | T19 |    45 |
|       | T20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q19 | Q18 | T19 |   360 |
|       | T20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q19 | Q19 | Q19 | T19 |   210 |
|       | T19 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | T16 |    10 |
|       | T19 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q16 | T20 |    10 |
|       | T19 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q17 | T20 |    90 |
|       | T19 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q18 | Q18 | T20 |    45 |
|       | T19 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q19 | Q18 | T20 |   360 |
|       | T19 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q19 | Q19 | Q19 | T20 |   210 |
|       | T18 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | T17 |    10 |
|       | T17 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | D25 |     1 |
|       | T17 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | T18 |    10 |
|       | D25 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | T17 |     1 |
|       | T16 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | T19 |    10 |
|       | T15 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | T20 |    10 |

This table was generated using the command:

```shell
darts checkouts -b quadro -i master -j master 901 901
```

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../../../LICENSE.md), or later.
