# Quadro 240 board any-in, double-out checkouts

> Checkout tables for any-in, double-out darts games with the Quadro 240 board

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) > [Markdown](../..) project provides checkout tables for darts games with the Quadro 240 board played in any-in, double-out mode.

Darts games commonly require players to score x01 points, where x is an integer.
The following table shows the required number of darts to reach such scores in Quadro 240 board any-in, double-out games.
In addition, the corresponding number of possible checkouts is shown.

|       Score | Darts |   Checkouts |
|------------:|------:|------------:|
| [101](#101) |     2 |           1 |
|         201 |     4 |       3,244 |
|         301 |     5 |       3,412 |
|         401 |     6 |         820 |
| [501](#501) |     7 |           6 |
|         601 |     9 |   4,207,648 |
|         701 |    10 |     642,234 |
|         801 |    11 |      21,300 |
| [901](#901) |    12 |          11 |
|        1001 |    14 | 449,392,814 |

The most exciting games will be those with a comparatively small number of possible checkouts.
Checkout tables for these scores are given below.
Additional tables can be generated using the `darts` toolbox of the [darts](https://github.com/mauritssilvis/darts) project.

## 101

| Score |   1 |   2 | # |
|------:|----:|----:|--:|
|   101 |   * |   * | 1 |
|       | T17 | D25 | 1 |

This table was generated using the command:

```shell
darts checkouts -b quadro 101 101
```

## 501

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 | # |
|------:|----:|----:|----:|----:|----:|----:|----:|--:|
|   501 |   * |   * |   * |   * |   * |   * |   * | 6 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | T17 | D25 | 6 |

This table was generated using the command:

```shell
darts checkouts -b quadro 501 501
```

## 901

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |  # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|---:|
|   901 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 11 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | T17 | D25 | 11 |

This table was generated using the command:

```shell
darts checkouts -b quadro 901 901
```

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../../../LICENSE.md), or later.
