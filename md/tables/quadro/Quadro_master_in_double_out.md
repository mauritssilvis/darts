# Quadro 240 board master-in, double-out checkouts

> Checkout tables for master-in, double-out darts games with the Quadro 240 board

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) > [Markdown](../..) project provides checkout tables for darts games with the Quadro 240 board played in master-in, double-out mode.
These tables are valid for games where the first (in) dart must land on a double or a triple, and the last (out) dart must land on a double.

In double-out games with the Quadro 240 board, the last dart can score between 2 and 50 points.
The last two darts can reach 130 points, and 210 points can be scored with the last three.
After a double or triple has been thrown, the extensive [any-in, double-out checkout tables](Quadro_any_in_double_out.md) apply to these scores.

As for master-in, double-out checkouts, the following can be observed.
Darts games commonly require players to score x01 points, where x is an integer.
The following table shows the required number of darts to reach such scores in Quadro 240 board master-in, double-out games.
In addition, the corresponding number of possible checkouts is shown.

|       Score | Darts |  Checkouts |
|------------:|------:|-----------:|
| [101](#101) |     2 |          1 |
|         201 |     4 |      1,428 |
|         301 |     5 |      1,084 |
|         401 |     6 |        172 |
| [501](#501) |     7 |          1 |
|         601 |     9 |    763,923 |
|         701 |    10 |     85,068 |
|         801 |    11 |      2,148 |
| [901](#901) |    12 |          1 |
|        1001 |    14 | 46,245,501 |

The most exciting games will be those with relatively few possible checkouts.
Checkout tables for these scores are given below.
Additional tables can be generated using the `darts` toolbox of the [darts](https://github.com/mauritssilvis/darts) project.

## 101

| Score |   1 |   2 | # |
|------:|----:|----:|--:|
|   101 |   * |   * | 1 |
|       | T17 | D25 | 1 |

This table was generated using the command:

```shell
darts checkouts -b quadro -i master 101 101
```

## 501

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 | # |
|------:|----:|----:|----:|----:|----:|----:|----:|--:|
|   501 |   * |   * |   * |   * |   * |   * |   * | 1 |
|       | T17 | Q20 | Q20 | Q20 | Q20 | Q20 | D25 | 1 |

This table was generated using the command:

```shell
darts checkouts -b quadro -i master 501 501
```

## 901

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 | # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|--:|
|   901 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 1 |
|       | T17 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | D25 | 1 |

This table was generated using the command:

```shell
darts checkouts -b quadro -i master 901 901
```

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../../../LICENSE.md), or later.
