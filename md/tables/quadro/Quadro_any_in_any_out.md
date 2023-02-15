# Quadro 240 board any-in, any-out checkouts

> Checkout tables for any-in, any-out darts games with the Quadro 240 board

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) > [Markdown](../..) project provides checkout tables for darts games with the Quadro 240 board played in any-in, any-out mode.

Darts games commonly require players to score x01 points, where x is an integer.
The following table shows the required number of darts to reach such scores in Quadro 240 board any-in, any-out games.
In addition, the corresponding number of possible checkouts is shown.

|         Score | Darts |  Checkouts |
|--------------:|------:|-----------:|
|           101 |     2 |         12 |
|   [201](#201) |     3 |         21 |
|           301 |     5 |     62,970 |
|           401 |     6 |     56,952 |
|           501 |     7 |     21,301 |
|   [601](#601) |     8 |      1,736 |
|           701 |    10 | 67,297,690 |
|           801 |    11 | 15,375,723 |
|           901 |    12 |  1,306,680 |
| [1001](#1001) |    13 |     17,901 |

The most exciting games will be those with a comparatively small number of possible checkouts.
Checkout tables for these scores are given below.
Additional tables can be generated using the `darts` toolbox of the [darts](https://github.com/mauritssilvis/darts) project.

## 201

| Score |   1 |   2 |   3 |  # |
|------:|----:|----:|----:|---:|
|   201 |   * |   * |   * | 21 |
|       | Q20 | Q19 | T15 |  6 |
|       | Q20 | Q16 | T19 |  6 |
|       | Q19 | Q17 | T19 |  6 |
|       | Q18 | Q18 | T19 |  3 |

This table was generated using the command:

```shell
darts checkouts -b quadro -j any 201 201
```

## 601

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |    # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|-----:|
|   601 |   * |   * |   * |   * |   * |   * |   * |   * | 1736 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | T15 |   56 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q16 | T19 |   56 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q17 | T19 |  336 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q18 | Q18 | T19 |  168 |
|       | Q20 | Q20 | Q20 | Q20 | Q19 | Q19 | Q18 | T19 |  840 |
|       | Q20 | Q20 | Q20 | Q19 | Q19 | Q19 | Q19 | T19 |  280 |

This table was generated using the command:

```shell
darts checkouts -b quadro -j any 601 601
```

## 1001

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |  13 |     # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|------:|
|  1001 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 17901 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | T15 |   156 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q16 | T19 |   156 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q17 | T19 |  1716 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q18 | Q18 | T19 |   858 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q19 | Q18 | T19 |  8580 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q19 | Q19 | Q19 | T19 |  6435 |

This table was generated using the command:

```shell
darts checkouts -b quadro -j any 1001 1001
```

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../../../LICENSE.md), or later.