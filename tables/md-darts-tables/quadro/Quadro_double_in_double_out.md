# Quadro 240 board double-in, double-out checkouts

> Checkout tables for double-in, double-out darts games with the Quadro 240 board

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) > [Tables](https://github.com/mauritssilvis/darts/tree/main/tables) > [Markdown](..) project provides checkout tables for darts games with the Quadro 240 board played in double-in, double-out mode.
These tables are valid for games where the first (in) and last (out) darts must land on a double.

In double-out games with the Quadro 240 board, the last dart can score between 2 and 50 points.
The last two darts can reach 130 points, and 210 points can be scored with the last three.
After a double has been thrown, the extensive [any-in, double-out checkout tables](Quadro_any_in_double_out.md) apply to these scores.

As for double-in, double-out checkouts, the following can be observed.
Darts games commonly require players to score x01 points, where x is an integer.
The following table shows the required number of darts to reach such scores in Quadro 240 board double-in, double-out games.
In addition, the corresponding number of possible checkouts is shown.

|       Score | Darts |   Checkouts |
|------------:|------:|------------:|
|         101 |     3 |          98 |
|         201 |     4 |         190 |
| [301](#301) |     5 |          45 |
|         401 |     7 |     758,455 |
|         501 |     8 |     310,486 |
|         601 |     9 |      46,676 |
| [701](#701) |    10 |       1,880 |
|         801 |    12 | 277,420,080 |
|         901 |    13 |  34,829,663 |
|        1001 |    14 |   1,752,252 |

The most exciting games will be those with relatively few possible checkouts.
Checkout tables for these scores are given below.
Additional tables can be generated using the `darts` toolbox of the [darts](https://github.com/mauritssilvis/darts) project.

## 301

| Score |   1 |   2 |   3 |   4 |   5 |  # |
|------:|----:|----:|----:|----:|----:|---:|
|   301 |   * |   * |   * |   * |   * | 45 |
|       | D25 | Q20 | Q20 | T19 | D17 |  3 |
|       | D25 | Q20 | Q20 | T17 | D20 |  3 |
|       | D25 | Q20 | Q19 | T19 | D19 |  6 |
|       | D25 | Q20 | Q19 | T15 | D25 |  6 |
|       | D25 | Q20 | Q16 | T19 | D25 |  6 |
|       | D25 | Q19 | Q17 | T19 | D25 |  6 |
|       | D25 | Q18 | Q18 | T19 | D25 |  3 |
|       | D20 | Q20 | Q20 | T17 | D25 |  3 |
|       | D19 | Q20 | Q19 | T19 | D25 |  6 |
|       | D17 | Q20 | Q20 | T19 | D25 |  3 |

This table was generated using the command:

```shell
darts checkouts -b quadro -i double 301 301
```

## 701

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |     # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|------:|
|   701 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 1,880 |
|       | D25 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | T19 | D17 |     8 |
|       | D25 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | T17 | D20 |     8 |
|       | D25 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | T19 | D19 |    56 |
|       | D25 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | T15 | D25 |    56 |
|       | D25 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q16 | T19 | D25 |    56 |
|       | D25 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | Q17 | T19 | D25 |   336 |
|       | D25 | Q20 | Q20 | Q20 | Q20 | Q20 | Q18 | Q18 | T19 | D25 |   168 |
|       | D25 | Q20 | Q20 | Q20 | Q20 | Q19 | Q19 | Q18 | T19 | D25 |   840 |
|       | D25 | Q20 | Q20 | Q20 | Q19 | Q19 | Q19 | Q19 | T19 | D25 |   280 |
|       | D20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | T17 | D25 |     8 |
|       | D19 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q19 | T19 | D25 |    56 |
|       | D17 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | Q20 | T19 | D25 |     8 |

This table was generated using the command:

```shell
darts checkouts -b quadro -i double 701 701
```

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../LICENSE.md), or later.
