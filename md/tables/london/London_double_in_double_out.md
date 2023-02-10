# London dartboard double-in double-out checkouts

## Introduction

|       Score | Darts |     Checkouts |
|------------:|------:|--------------:|
|         101 |     3 |            98 |
| [201](#201) |     4 |            14 |
|         301 |     6 |         2,544 |
|         401 |     8 |       380,228 |
| [501](#501) |     9 |           574 |
|         601 |    11 |       319,586 |
|         701 |    13 |    64,693,283 |
| [801](#801) |    14 |         4,184 |
|         901 |    16 |    12,139,768 |
|        1001 |    18 | 3,400,574,560 |

## Checkout tables

### 201

| Score |   1 |   2 |   3 |   4 |  # |
|------:|----:|----:|----:|----:|---:|
|   201 |   * |   * |   * |   * | 14 |
|       | D25 | T20 | T19 | D17 |  2 |
|       | D25 | T20 | T17 | D20 |  2 |
|       | D25 | T19 | T18 | D20 |  2 |
|       | D25 | T17 | D25 | D25 |  2 |
|       | D20 | T20 | T17 | D25 |  2 |
|       | D20 | T19 | T18 | D25 |  2 |
|       | D17 | T20 | T19 | D25 |  2 |

This table was generated using the command:

```shell
darts checkouts -i double 201 201
```

### 501

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |   # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|
|   501 |   * |   * |   * |   * |   * |   * |   * |   * |   * | 574 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D17 |   7 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D20 |   7 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D20 |  42 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T17 | D25 | D25 |  42 |
|       | D25 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D20 |  35 |
|       | D25 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | D25 | 210 |
|       | D25 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | D25 | 140 |
|       | D20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |   7 |
|       | D20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 |  42 |
|       | D20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 |  35 |
|       | D17 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 |   7 |

This table was generated using the command:

```shell
darts checkouts -i double 501 501
```

### 801

| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |  11 |  12 |  13 |  14 |    # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|-----:|
|   801 |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * |   * | 4184 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D17 |   12 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D20 |   12 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D20 |  132 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 | D25 |  132 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D20 |  220 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 | D25 | 1320 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 | D25 | 1980 |
|       | D20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D25 |   12 |
|       | D20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 |  132 |
|       | D20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 |  220 |
|       | D17 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 |   12 |

This table was generated using the command:

```shell
darts checkouts -i double 801 801
```

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../LICENSE.md), or later.
