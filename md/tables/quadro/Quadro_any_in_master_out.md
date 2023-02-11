# Quadro 240 board any-in, master-out checkouts

> Checkout tables for any-in, master-out darts games with the Quadro 240 board

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) > [Markdown](../..) project provides checkout tables for darts games with the Quadro 240 board played in any-in, master-out mode.

Darts games commonly require players to score x01 points, where x is an integer.
The following table shows the required number of darts to reach such scores in Quadro 240 board any-in, master-out games.
In addition, the corresponding number of possible checkouts is shown.

|         Score | Darts |  Checkouts |
|--------------:|------:|-----------:|
|           101 |     2 |          6 |
|   [201](#201) |     3 |          7 |
|           301 |     5 |     21,061 |
|           401 |     6 |     13,631 |
|           501 |     7 |      3,553 |
|   [601](#601) |     8 |        217 |
|           701 |    10 | 10,595,757 |
|           801 |    11 |  1,789,517 |
|           901 |    12 |    117,085 |
| [1001](#1001) |    13 |      1,377 |

The most exciting games will be those with a comparatively small number of possible checkouts.
Checkout tables for these scores are given below.
Additional tables can be generated using the `darts` toolbox of the [darts](https://github.com/mauritssilvis/darts) project.

## 201

| Score |   1 |   2 |   3 | # |
|------:|----:|----:|----:|--:|
|   201 |   * |   * |   * | 7 |
|       | Q20 | Q19 | T15 | 2 |
|       | Q20 | Q16 | T19 | 2 |
|       | Q19 | Q17 | T19 | 2 |
|       | Q18 | Q18 | T19 | 1 |

This table was generated using the command:

```shell
darts checkouts -b quadro -j master 201 201
```

## 601

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|
|   601 |   * |   * |   * |   * |   * |   * |   * |   * | 217 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | T15 |   7 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q16 | T19 |   7 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q17 | T19 |  42 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q18 | Q18 | T19 |  21 |
|       | Q20 | Q20 | Q20 | Q20 | Q19 | Q19 | Q18 | T19 | 105 |
|       | Q20 | Q20 | Q20 | Q19 | Q19 | Q19 | Q19 | T19 |  35 |

This table was generated using the command:

```shell
darts checkouts -b quadro -j master 601 601
```

## 1001

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |  13 |    # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|-----:|
|  1001 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 1377 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | T15 |   12 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q16 | T19 |   12 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q17 | T19 |  132 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q18 | Q18 | T19 |   66 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q19 | Q18 | T19 |  660 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q19 | Q19 | Q19 | T19 |  495 |

This table was generated using the command:

```shell
darts checkouts -b quadro -j master 1001 1001
```

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../../../LICENSE.md), or later.
