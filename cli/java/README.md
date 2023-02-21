# darts > CLI > Java

> A Java-based command-line toolbox aimed at the game of darts

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) > [CLI](https://github.com/mauritssilvis/darts/tree/main/cli) project provides a Java-based command-line toolbox aimed at the game of darts.
This command-line toolbox, referred to as `darts` in what follows, can:

- Determine all possible darts checkouts.
- Generate checkout tables for any range of scores.

Additionally, `darts` lets you:

- Choose the dartboard type.
- Select the game mode.
- Set the number of darts.
- Pick the output format.

The [installation](#1-installation) and [usage](#2-usage) of `darts` are discussed, in what follows.
For solutions to commonly occurring problems, refer to the [troubleshooting](#3-troubleshooting) section.
If you would like to explore extensive checkout tables for different types of darts games, refer to the [Markdown checkout tables](https://github.com/mauritssilvis/darts/tree/main/tables/md).

## 1. Installation

To use `darts`, you have to execute the following steps.

### 1.1 Install Java 17+

`darts` requires the installation of Java 17 or higher.

You can install the latest version of Java as follows:

- Go to https://jdk.java.net/.
- Navigate to the page with the latest ready-to-use version of the Java Development Kit (JDK), currently JDK 19.
- From the builds section, download the archive matching your system.
- Extract the archive to a convenient location.
- Set the `JAVA_HOME` environment variable to the full path of the extracted `jdk-XX.X.X` folder.

### 1.2 Build `darts`

You can build the `darts` toolbox as follows:

- Clone or download the `darts` code from https://github.com/mauritssilvis/darts.
- If necessary, extract the code to a convenient location.
- Navigate to the `java` folder that is part of the code.
- Run the `install` command.
- Test execution of the `darts` toolbox by running the `darts` command.

#### 1.2.1 Command execution

On Unix-like systems, the `install` and `darts` commands can be executed as follows:

- Open a terminal.
- Navigate to the `java` folder that is part of the `darts` code.
- Run the `install` command by executing `./install`.
- Run the `darts` command by executing `./darts`.

On Windows, the `install` and `darts` commands can be executed using the command prompt or the PowerShell.

With the command prompt, the execution of commands works as follows:

- Open the command prompt, for example, by pushing the `Windows key`, typing `cmd` and hitting `Enter`.
- Navigate to the `java` folder that is part of the `darts` code.
- Run the `install` command by executing `install`.
- Run the `darts` command by executing `darts`.

With the PowerShell, execution works as follows:

- Open the PowerShell, for example, by pushing the `Windows key`, typing `powershell` and hitting `Enter`.
- Navigate to the `java` folder that is part of the `darts` code.
- Run the `install` command by executing `.\install`.
- Run the `darts` command by executing `.\darts`.

#### 1.2.2 Troubleshooting

Should any problems occur while building `darts`, please refer to the [troubleshooting](#3-troubleshooting) section.

## 2. Usage

The `darts` command-line toolbox offers three subcommands.
These subcommands are described in detail in what follows:

- [Generate a darts checkout table](#21-generate-a-darts-checkout-table) using `darts checkouts`.
- [Print a dartboard](#22-print-a-dartboard) using `darts boards`.
- [Get help](#23-get-help) using `darts help`.

### 2.1 Generate a darts checkout table

#### 2.1.1 Choose the scores

Generating a darts checkout table using `darts` is as simple as choosing a minimum and a maximum score and passing these to the `darts checkouts` subcommand.

For example, to create a checkout table with scores from 1 to 4, execute the following command:

```shell
darts checkouts 1 4
```

The corresponding output looks as follows:

```markdown
| Score |  1 |  2 | # |
|------:|---:|---:|--:|
|     1 |  * |  * | 0 |
|     2 |  * |  * | 1 |
|       | D1 |  - | 1 |
|     3 |  * |  * | 1 |
|       |  1 | D1 | 1 |
|     4 |  * |  * | 1 |
|       | D2 |  - | 1 |
```

This table shows how scores from 1 to 4 can be reached for a specific type of darts game.
Specifically, the table applies to a double-out game with the ‘standard’ London dartboard.
There are no restrictions on the first dart.

Similarly, the command

```shell
darts checkouts 20 21
```

produces a checkout table for the scores 20 to 21 in such a game.
In truncated form, this table looks as follows:

```markdown
| Score |         1 |         2 |  # |
|------:|----------:|----------:|---:|
|    20 |         * |         * |  1 |
|       |       D10 |         - |  1 |
|    21 |         * |         * | 13 |
|       |        19 |        D1 |  1 |
|       |        17 |        D2 |  1 |
|       |       ... |       ... |  1 |
|       |   3 /  T1 |        D9 |  2 |
|       |         1 |       D10 |  1 |
```

Note that a single 3 and a triple 1 (T1) have the same score.
For brevity, checkouts involving different throws of the same score are summarized.

The `darts` toolbox can also be used to generate a checkout table for a single score.
For example, to find 501-point checkouts, pass the same value as the minimum and maximum score:

```shell
darts checkouts 501 501
```

The resulting output shows that there are 3944 different nine-dart checkouts in a 501-point double-out darts game:

```markdown
| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |     # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|------:|
|   501 |   * |   * |   * |   * |   * |   * |   * |   * |   * | 3,944 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D12 |     8 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D15 |     8 |
|       | T20 | T20 | T20 | T20 | T20 | T20 | T20 | T15 | D18 |     8 |
|       | ... | ... | ... | ... | ... | ... | ... | ... | ... |   ... |
|       | T20 | T20 | T20 | T20 | T19 | T18 | D25 | D25 | D25 |   840 |
|       | T20 | T20 | T20 | T19 | T19 | T19 | T19 | T19 | D18 |    56 |
|       | T20 | T20 | T20 | T19 | T19 | T19 | D25 | D25 | D25 |   560 |
```

Note that it doesn't matter in which order the first eight darts are thrown.
Therefore, each row of the table represents multiple checkouts.

#### 2.1.2 Default parameters

As was hinted at above, the `darts checkouts` subcommand has several default parameters.
Specifically, when left unspecified, the following options are set:

- The [dartboard type](#216-change-the-dartboard): London.
- The [check-in mode](#213-change-the-game-mode): any.
- The [checkout mode](#213-change-the-game-mode): double.
- The [number of throws](#214-select-the-number-of-throws): 0 (not fixed).
- The [throw mode](#214-select-the-number-of-throws): optimal.
- The [checkout finder type](#217-change-the-checkout-finder): descending.
- The [output format](#215-change-the-output-format): Markdown.

In other words, the command

```shell
darts checkouts 1 4
```

is equivalent to

```shell
darts checkouts -b London -i any -j double -n 0 -m optimal -f descending -o Markdown 1 4
````

With full-length versions of the one-letter flags, this command can be expanded to:

```shell
darts checkouts --board London --check-in any --checkout double --throws 0 --throw-mode optimal --finder descending --output Markdown 1 4
````

The following sections explain how the default parameters can be changed.

#### 2.1.3 Change the game mode

```shell
darts checkouts -j any 1 4
```

```markdown
| Score |       1 | # |
|------:|--------:|--:|
|     1 |       * | 1 |
|       |       1 | 1 |
|     2 |       * | 2 |
|       |  2 / D1 | 2 |
|     3 |       * | 2 |
|       |  3 / T1 | 2 |
|     4 |       * | 2 |
|       |  4 / D2 | 2 |
```

```shell
darts checkouts -j master 20 21
```

```markdown
| Score |   1 | # |
|------:|----:|--:|
|    20 |   * | 1 |
|       | D10 | 1 |
|    21 |   * | 1 |
|       |  T7 | 1 |
```

```shell
darts checkouts -i double 501 501
```

```markdown
| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |   # |
|------:|----:|----:|----:|----:|----:|----:|----:|----:|----:|----:|
|   501 |   * |   * |   * |   * |   * |   * |   * |   * |   * | 574 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D17 |   7 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T20 | T17 | D20 |   7 |
|       | D25 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D20 |  42 |
|       | ... | ... | ... | ... | ... | ... | ... | ... | ... | ... |
|       | D20 | T20 | T20 | T20 | T20 | T20 | T19 | T18 | D25 |  42 |
|       | D20 | T20 | T20 | T20 | T20 | T19 | T19 | T19 | D25 |  35 |
|       | D17 | T20 | T20 | T20 | T20 | T20 | T20 | T19 | D25 |   7 |
```

```shell
darts checkouts -i master -j master 501 501
```

```markdown
| Score |         1 |         2 |         3 |         4 |         5 |         6 |         7 |         8 |         9 |       # |
|------:|----------:|----------:|----------:|----------:|----------:|----------:|----------:|----------:|----------:|--------:|
|   501 |         * |         * |         * |         * |         * |         * |         * |         * |         * | 223,026 |
|       |       T20 |       T20 |       T20 |       T20 |       T20 |       T20 |       T20 |       T20 |        T7 |       1 |
|       |       T20 |       T20 |       T20 |       T20 |       T20 |       T20 |       T20 |       T19 | D12 /  T8 |      14 |
|       |       T20 |       T20 |       T20 |       T20 |       T20 |       T20 |       T20 |       T18 |        T9 |       7 |
|       |       ... |       ... |       ... |       ... |       ... |       ... |       ... |       ... |       ... |     ... | 
|       | D12 /  T8 |       T20 |       T20 |       T20 |       T20 |       T20 |       T20 |       T20 |       T19 |       2 |
|       | D12 /  T8 |       T20 |       T20 |       T20 |       T20 |       T20 |       T20 |       T19 |       T20 |      14 |
|       |        T7 |       T20 |       T20 |       T20 |       T20 |       T20 |       T20 |       T20 |       T20 |       1 |
```

#### 2.1.4 Select the number of throws

```shell
darts checkouts 1 4 -n 2
```

```markdown
| Score |  1 |  2 | # |
|------:|---:|---:|--:|
|     1 |  * |  * | 0 |
|     2 |  * |  * | 0 |
|     3 |  * |  * | 1 |
|       |  1 | D1 | 1 |
|     4 |  * |  * | 0 |
```

```shell
darts checkouts 1 4 -n 2 -m fixed
```

```markdown
| Score |       1 |       2 | # |
|------:|--------:|--------:|--:|
|     1 |       * |       * | 0 |
|     2 |       * |       * | 0 |
|     3 |       * |       * | 1 |
|       |       1 |      D1 | 1 |
|     4 |       * |       * | 2 |
|       |  2 / D1 |      D1 | 2 |
```

#### 2.1.5 Change the output format

```shell
darts checkouts 1 4 -o html
```

```html
<table>
  <tr class="h"><th>                             Score</th><th class="t">                        1</th><th class="t">                        2</th><th class="m">#</th></tr>
  <tr class="s"><th rowspan="1" scope="rowgroup">    1</th><td class="t"><span class="e"> *</span></td><td class="t"><span class="e"> *</span></td><td class="m">0</td></tr>
  <tr class="s"><th rowspan="2" scope="rowgroup">    2</th><td class="t"><span class="e"> *</span></td><td class="t"><span class="e"> *</span></td><td class="m">1</td></tr>
  <tr class="c">                                           <td class="t"><span class="f">D1</span></td><td class="t"><span class="n"> -</span></td><td class="m">1</td></tr>
  <tr class="s"><th rowspan="2" scope="rowgroup">    3</th><td class="t"><span class="e"> *</span></td><td class="t"><span class="e"> *</span></td><td class="m">1</td></tr>
  <tr class="c">                                           <td class="t"><span class="f"> 1</span></td><td class="t"><span class="f">D1</span></td><td class="m">1</td></tr>
  <tr class="s"><th rowspan="2" scope="rowgroup">    4</th><td class="t"><span class="e"> *</span></td><td class="t"><span class="e"> *</span></td><td class="m">1</td></tr>
  <tr class="c">                                           <td class="t"><span class="f">D2</span></td><td class="t"><span class="n"> -</span></td><td class="m">1</td></tr>
</table>
```

```shell
darts checkouts 1 4 -o json
```

```json
{
    "1": {
        "multiplicity": 0,
        "checkouts": [
        ]
    },
    "2": {
        "multiplicity": 1,
        "checkouts": [
            {
                "throws": [
                    [
                        "D1"
                    ]
                ],
                "multiplicity": 1
            }
        ]
    },
    "3": {
        "multiplicity": 1,
        "checkouts": [
            {
                "throws": [
                    [
                        "1"
                    ],
                    [
                        "D1"
                    ]
                ],
                "multiplicity": 1
            }
        ]
    },
    "4": {
        "multiplicity": 1,
        "checkouts": [
            {
                "throws": [
                    [
                        "D2"
                    ]
                ],
                "multiplicity": 1
            }
        ]
    }
}
```

#### 2.1.6 Change the dartboard

```shell
darts checkouts -b quadro 501 501
```

```markdown
| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 | # |
|------:|----:|----:|----:|----:|----:|----:|----:|--:|
|   501 |   * |   * |   * |   * |   * |   * |   * | 6 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | T17 | D25 | 6 |
```

#### 2.1.7 Change the checkout finder

```shell
darts checkouts -f Cartesian 20 21
```

```markdown
| Score |   1 |   2 |  # |
|------:|----:|----:|---:|
|    20 |   * |   * |  1 |
|       | D10 |   - |  1 |
|    21 |   * |   * | 13 |
|       |   1 | D10 |  1 |
|       |   3 |  D9 |  1 |
|       |  T1 |  D9 |  1 |
|       | ... | ... | .. |
|       |  T5 |  D3 |  1 |
|       |  17 |  D2 |  1 |
|       |  19 |  D1 |  1 |
```

### 2.2 Print a dartboard

#### 2.2.1 Choose the dartboard

```shell
darts boards london
```

```markdown
|   S |   D |   T |
|----:|----:|----:|
|   1 |  D1 |  T1 |
|   2 |  D2 |  T2 |
|   3 |  D3 |  T3 |
| ... | ... | ... |
|  19 | D19 | T19 |
|  20 | D20 | T20 |
|  25 | D25 |   - |
```

#### 2.2.2 Change the output format

```shell
darts boards london -o html
````

```html
<table>
  <tr><th>  S</th><th>  D</th><th>  T</th></tr>
  <tr><td>  1</td><td> D1</td><td> T1</td></tr>
  <tr><td>  2</td><td> D2</td><td> T2</td></tr>
  <tr><td>  3</td><td> D3</td><td> T3</td></tr>
  <tr><td>...</td><td>...</td><td>...</td></tr>
  <tr><td> 19</td><td>D19</td><td>T19</td></tr>
  <tr><td> 20</td><td>D20</td><td>T20</td></tr>
  <tr><td> 25</td><td>D25</td><td>  -</td></tr>
</table>
```

```shell
darts boards london -o json
````

```json5
{
    "singles": [
        "1",
        "2",
        // ...
        "20",
        "25"
    ], 
    "doubles": [
        "D1",
        "D2",
        // ...
        "D20",
        "D25"
    ],
    "triples": [
        "T1",
        "T2",
        // ...
        "T19",
        "T20"
    ]
}
```

### 2.3 Get help

```shell
darts help
```

```shell
darts help checkouts
```

```shell
darts help boards
```

## 3. Troubleshooting

### 3.1 Installation

#### 3.1.1 The `install` command was not found

While trying to build `darts`, you may encounter the following errors:

```text
install: command not found
```

```text
'install' is not recognized as an internal or external command, operable program
or batch file.
```

```text
install : The term 'install' is not recognized as the name of a cmdlet,
function, script file, or operable program.
```

```text
install: missing file operand
Try 'install --help' for more information.
```

To solve these problems, take the following steps:

- Ensure you navigated to the `java` folder that is part of the `darts` code.
- Prefix the `install` command with `./` (Bash) or `.\` (PowerShell) before execution.

#### 3.1.2 Java was not installed or found

While building or executing `darts`, you may encounter the following errors:

```text
JAVA_HOME is not set and no 'java' command could be found in your PATH.
```

```text
JAVA_HOME is set to an invalid directory: ...
```

To solve these problems, follow the instructions for [installing Java 17+](#11-install-java-17).

#### 3.1.3 The `darts` toolbox was not built

While executing `darts`, you may encounter the following error:

```text
Error: Could not find or load main class nl.mauritssilvis.darts.java.cli.DartsApp
Caused by: java.lang.ClassNotFoundException: nl.mauritssilvis.darts.java.cli.DartsApp
```

To solve this problem, [build](#12-build-darts) the `darts` toolbox.

#### 3.1.4 Java is not up-to-date

While executing `darts`, you may encounter the following error:

```text
Error: LinkageError occurred while loading main class nl.mauritssilvis.darts.java.cli.DartsApp
        java.lang.UnsupportedClassVersionError: nl/mauritssilvis/darts/java/cli/DartsApp
        has been compiled by a more recent version of the Java Runtime (class
        file version 61.0), this version of the Java Runtime only recognizes 
        class file versions up to XX.X
```

To solve this problem, follow the instructions for [installing Java 17+](#11-install-java-17).

#### 3.1.5 Building `darts` failed

While building `darts`, you may encounter other errors than those listed above.
Such errors may contain a message like:

```text
FAILURE: Build failed with an exception.
...
BUILD FAILED in XXs
...
```

Should the build fail, please [report an issue](https://github.com/mauritssilvis/darts/issues) and document the error and how it can be reproduced.

### 3.2 Execution

#### 3.2.1 The `darts` command was not found

While trying to execute `darts`, you may encounter the following errors:

```text
darts: command not found
```

```text
'darts' is not recognized as an internal or external command, operable program 
or batch file.
```

```text
darts : The term 'darts' is not recognized as the name of a cmdlet, function, 
script file, or operable program.
```

To solve these problems, take the following steps:

- Install `darts` by following the [installation instructions](#1-installation).
- Navigate to the `java` folder that is part of the `darts` code.
- Prefix the `darts` command with `./` (Bash) or `.\` (PowerShell) before execution.

#### 3.2.2 Missing required subcommand

While executing `darts`, you may encounter the following error:

```text
Missing required subcommand
```

To solve this problem, execute `darts` with a subcommand:

- `darts checkouts` for [generating a darts checkout table](#21-generate-a-darts-checkout-table).
- `darts boards` for [printing a dartboard](#22-print-a-dartboard).
- `darts help` for [getting help](#23-get-help).

#### 3.2.3 Missing required `minimum` or `maximum` parameters

While executing the `darts checkouts` subcommand, you may encounter the following errors:

```text
Missing required parameters: '<minimum>', '<maximum>'
```

```text
Missing required parameter: '<maximum>'
```

To solve these problems, [choose the scores](#211-choose-the-scores) for which you would like to generate a checkout table.

#### 3.2.4 Missing required `board` parameter

While executing the `darts boards` subcommand, you may encounter the following error:

```text
Missing required parameter: '<board>'
```

To solve this problem, [specify the dartboard](#221-choose-the-dartboard) that you would like to print.

#### 3.2.5 Invalid value

While executing the `darts checkouts` or `darts boards` subcommands, you may encounter the following errors:

```text
Invalid value for positional parameter at index ...: '...' is not an int
```

```text
Invalid value for option '...': expected one of ... but was '...'
```

```text
Invalid value for positional parameter at index ...: expected one of ... but was '...'
```

To solve these problems, choose valid parameters from the [usage instructions](#2-usage).

#### 3.2.6 Missing required option parameter

While executing the `darts checkouts` or `darts boards` subcommands, you may encounter the following error:

```text
Missing required parameter for option '...'
```

To solve this problem, choose a valid option parameter from the [usage instructions](#2-usage).

#### 3.2.7 Unknown option

While executing the `darts checkouts` or `darts boards` subcommands, you may encounter the following error:

```text
Unknown option: '...'
```

To solve this problem, choose an existing subcommand option from the [usage instructions](#2-usage).

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](LICENSE.md), or later.
