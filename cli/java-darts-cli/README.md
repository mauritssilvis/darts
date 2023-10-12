# Java darts CLI

> A Java-based command-line interface for the `darts` toolbox

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) > [CLIs](https://github.com/mauritssilvis/darts/tree/main/cli) project provides a Java-based command-line interface for `darts`, a computational toolbox aimed at the game of darts.
This command-line interface, referred to as `darts` in what follows, can be used to:

- Determine all possible darts checkouts.
- Generate checkout tables for any range of scores.

Additionally, `darts` lets you:

- Choose the dartboard type.
- Select the game mode.
- Set the number of darts.
- Pick the output format.

The [installation](#1-installation) and [usage](#2-usage) of `darts` are discussed in what follows.
For solutions to commonly occurring problems, refer to the [troubleshooting](#3-troubleshooting) section.

The release history of the Java darts CLI project is contained in the [changelog](CHANGELOG.md). 
If you would like to explore extensive checkout tables for different types of darts games, refer to the [checkout table overview](https://github.com/mauritssilvis/darts/tree/main/tables/md-darts-tables).

## 1. Installation

To use `darts`, you have to execute the following steps.

### 1.1 Install Java 21+

`darts` requires the installation of Java 21 or higher.

You can install the latest version of Java as follows:

- Go to https://adoptium.net/temurin/releases/.
- Select the latest long-term support (LTS) version of Java, currently Java 21.
- Download the archive or installer matching your system.
- Extract or install the files in a convenient location.
- Set the `JAVA_HOME` environment variable to the full path of the created `jdk-X+Y` folder. Here, `X` and `Y` are placeholders for version numbers.

### 1.2 Install `darts`

To install the `darts` toolbox, first follow the following steps:

- Download the Java darts CLI code.
    - You can download the latest Java darts CLI release from https://github.com/mauritssilvis/darts/releases.
    - Alternatively, clone or download the full `darts` code from https://github.com/mauritssilvis/darts.
- If necessary, extract the code to a convenient location.

You can then install the `darts` toolbox as follows:

- Navigate to the folder containing the `darts` installation scripts, `install` and `install.bat`.
    - If you downloaded the latest Java darts CLI release, these scripts can be found in the extracted folder.
    - If you downloaded the full `darts` code, these scripts can be found in the `cli/java-darts-cli` folder of that code.
- Run the `install` command to install `darts` in the `bin` folder.
- Navigate to the newly created `bin` folder.
- Test the execution of the `darts` toolbox by running the `darts` command.

#### 1.2.1 Command execution

##### Unix-like systems

On Unix-like systems, the `install` and `darts` commands can be executed as follows:

- Open a terminal.
- Navigate to the folder containing the `darts` installation script, `install`.
- Run the `install` command by executing `./install`.
- Navigate to the newly created `bin` folder.
- Run the `darts` command by executing `./darts`.

##### Windows

On Windows, the `install` and `darts` commands can be executed using the command prompt or the PowerShell.

With the command prompt, the execution of commands works as follows:

- Open the command prompt, for example, by pushing the `Windows key`, typing `cmd` and hitting `Enter`.
- Navigate to the folder containing the `darts` installation script, `install.bat`.
- Run the `install` command by executing `install`.
- Navigate to the newly created `bin` folder.
- Run the `darts` command by executing `darts`.

With the PowerShell, execution works as follows:

- Open the PowerShell, for example, by pushing the `Windows key`, typing `powershell` and hitting `Enter`.
- Navigate to the folder containing the `darts` installation script, `install.bat`.
- Run the `install` command by executing `.\install`.
- Navigate to the newly created `bin` folder.
- Run the `darts` command by executing `.\darts`.

#### 1.2.2 Troubleshooting

Should any problems occur while installing `darts`, please refer to the [troubleshooting](#3-troubleshooting) section.

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

This checkout table shows how scores from 1 to 4 can be reached for a specific type of darts game.
Specifically, the table applies to a double-out game with the ‘standard’ London dartboard.
There are no restrictions on the first dart.
No one-point checkouts were found since 1 is less than the minimal final score — a double 1 (D1).
Otherwise, all rows have a multiplicity of one.
They represent a single checkout, which can only be attained by hitting the fields in their listed order.

Similarly, the command

```shell
darts checkouts 20 21
```

produces a checkout table for the scores 20 to 21.
This table, which is truncated for brevity, looks as follows:

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
By default, these fields are listed together, and their row has a multiplicity of two.
In other words, this row represents two checkouts.
With the default configuration, checkouts for a given score are sorted in descending order.
That is, for a given score, checkouts that start with high-value fields are listed before checkouts that start with lower scores.

The `darts` toolbox can also generate a checkout table for a single score.
For example, to find 501-point checkouts, pass the same value as the minimum and maximum score:

```shell
darts checkouts 501 501
```

The resulting (truncated) output shows that there are 3,944 different nine-dart checkouts in a 501-point double-out darts game:

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

It does not matter in which order the first eight darts are thrown.
Therefore, each row of the table represents multiple checkouts.

#### 2.1.2 Default parameters

As was hinted at above, the `darts checkouts` subcommand has several default parameters.
When left unspecified, the following options are set:

- The [dartboard type](#the-dartboard-type): London.
- The [check-in mode](#the-check-in-mode): any.
- The [checkout mode](#the-checkout-mode): double.
- The [number of throws](#the-number-of-throws): 0 (not fixed).
- The [throw mode](#the-throw-mode): optimal.
- The [checkout finder type](#the-checkout-finder-type): descending.
- The [output format](#the-output-format): Markdown.

As such, the command

```shell
darts checkouts 1 4
```

is equivalent to

```shell
darts checkouts -b london -i any -j double -n 0 -m optimal -f descending -o markdown 1 4
````

With full-length versions of the one-letter flags, this command reads:

```shell
darts checkouts --board london --check-in any --checkout double --throws 0 --throw-mode optimal --finder descending --output markdown 1 4
````

The following sections explain how the default parameters can be changed.

#### 2.1.3 Change the game mode

##### The checkout mode

By default, the `darts checkouts` subcommand looks for checkouts for a double-out darts game.
To change the checkout mode, use the `-j` or `--checkout` option.
The supported checkout modes are:

- `any` for final darts of [any](#any-out) score;
- `master` for [double or triple](#master-out) finishes;
- `double` for a double checkout (default).

##### Any out

To allow for final darts of any score, use a command of the following form:

```shell
darts checkouts -j any 1 4
```

The corresponding output is:

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

Single final scores are accepted with the selected checkout mode, and a finish of 1 is possible.

##### Master out

To select master, i.e., double or triple, checkouts, use a command like:

```shell
darts checkouts -j master 20 21
```

The resulting output shows that only one dart is required to reduce a score of 21 to zero in master-out games:

```markdown
| Score |   1 | # |
|------:|----:|--:|
|    20 |   * | 1 |
|       | D10 | 1 |
|    21 |   * | 1 |
|       |  T7 | 1 |
```

##### The check-in mode

By default, the `darts checkouts` subcommand does not impose restrictions on the first dart and, thus, assumes an any-in game.
The check-in mode can, however, be changed using the `-i` and `--check-in` options.
The supported check-in modes are:

- `any` for an unrestricted initial throw (default);
- `master` for a [double or triple](#master-in-master-out) check-in;
- `double` for an initial [double](#double-in) score.

##### Double in

For example, to find the 501-point checkouts of a double-in, double-out darts game, run the following command:

```shell
darts checkouts -i double 501 501
```

The resulting (truncated) table shows there are 574 nine-dart checkouts in such a game:

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

##### Master in, master out

You can also set both the check-in and checkout modes.
For example, a master-in, master-out game would require a command of the form:

```shell
darts checkouts -i master -j master 501 501
```

When truncated from 1,262 lines representing more than 200,000 (!) possible checkouts, the output looks as follows:

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

##### The number of throws

By default, the `darts checkouts` subcommand does not fix the number of throws and finds ‘optimal’ checkouts.
That is, this command finds checkouts with the minimum required number of darts.
Using `darts`, you can, however, fix the number of throws with the `-n` and `--throws` options.
This option takes a non-negative integer as a parameter:

- zero (`0`) will not fix the number of throws (default);
- a non-zero, positive integer (`1`, `2`, `3`, etc.) fixes the number of throws.

##### The throw mode

Two different throw modes can be used if the number of throws is fixed.
These throw modes are represented by the two values the `-m` and `--throw-mode` options can take:

- `optimal` for finding only [optimal](#optimal) checkouts (default);
- `fixed` for finding [all](#fixed) checkouts for a given number of throws.

##### Optimal

To find only the *optimal* checkouts with a fixed number of darts, pass the desired number to the `-n` or `--throws` option.
The throw mode will then take its default value, `optimal`.
For example:

```shell
darts checkouts -n 2 1 4
```

The resulting output is as follows:

```markdown
| Score |  1 |  2 | # |
|------:|---:|---:|--:|
|     1 |  * |  * | 0 |
|     2 |  * |  * | 0 |
|     3 |  * |  * | 1 |
|       |  1 | D1 | 1 |
|     4 |  * |  * | 0 |
```

Here, only the checkouts that require two darts are found.
Scores of 2 and 4 can be reached by hitting one double.
Therefore, these checkouts are not shown.

##### Fixed

You can find *all* checkouts for a fixed number of darts by specifying that number and changing the `-m` or `--throw-mode` option from its default value to `fixed`.
For example:

```shell
darts checkouts -n 2 -m fixed 1 4
```

The corresponding checkout table looks as follows:

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

Note that the minimum two-dart score in a double-out game is 3.
Therefore, no checkouts are shown for scores 1 and 2.
Furthermore, a two-dart four-point checkout is shown even if a one-dart checkout exists.
With the fixed-throw mode, all possible checkouts for a given number of darts are found.

#### 2.1.5 Change the output format

##### The output format

By default, the `darts checkouts` subcommand creates checkout tables in the Markdown format.
To change the output format, use the `-o` or `--output` option.
The following output formats are supported:

- `Markdown` for an easily readable Markdown table (default);
- `HTML` for an [HTML](#html) table for web pages;
- `JSON` for a [JSON](#json) object for computer processing;
- `string` for output based on Java's [string](#string) representation of objects.

##### HTML

To generate an HTML checkout table, use a command like:

```shell
darts checkouts -o html 1 4
```

The output will look as follows:

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

Here, header cell elements (`th`) are used for column headers and cells with scores in the first column.
The header cells with scores span multiple rows to correspond to all checkouts of that score.
All checkout table elements have their own CSS class for (potential) custom styling.
Specifically, the following classes are used:

- `h` for the header row;
- `s` for a score row;
- `c` for a checkout row;
- `t` for a cell in a throw column;
- `e` for the content of a score-throw cell;
- `f` for a dart field in a checkout-throw cell;
- `n` for a missing dart field in a checkout-throw cell;
- `m` for a cell in the multiplicity column.

Of course, classes can be renamed or dropped at will after retrieving the HTML output.

A command containing one or more of the following lines can be used to sanitize the HTML output:

```shell
darts checkouts -o html 1 4 \
  `# Remove superfluous whitespace` \
  | sed -r "s|> +|>|g" \
  `# Remove all class attributes` \
  | sed "s| class=\"\\w\"||g" \
  `# Remove all span tags` \
  | sed -r "s|</span><span( class=\"\\w\")?>| / |g" \
  | sed -r "s|</?span( class=\"\\w\")?>||g"
```

This command has the following output:

```html
<table>
  <tr><th>Score</th><th>1</th><th>2</th><th>#</th></tr>
  <tr><th rowspan="1" scope="rowgroup">1</th><td>*</td><td>*</td><td>0</td></tr>
  <tr><th rowspan="2" scope="rowgroup">2</th><td>*</td><td>*</td><td>1</td></tr>
  <tr><td>D1</td><td>-</td><td>1</td></tr>
  <tr><th rowspan="2" scope="rowgroup">3</th><td>*</td><td>*</td><td>1</td></tr>
  <tr><td>1</td><td>D1</td><td>1</td></tr>
  <tr><th rowspan="2" scope="rowgroup">4</th><td>*</td><td>*</td><td>1</td></tr>
  <tr><td>D2</td><td>-</td><td>1</td></tr>
</table>
```

##### JSON

Markdown and HTML tables aren't very suitable for computer processing.
To obtain a JSON object that contains all checkouts for a range of scores, use a command like:

```shell
darts checkouts -o json 1 4
```

The output will look as follows:

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

##### String

`darts` can also print checkout tables as a string showing the internal representation of the used Java objects.
Since this feature mainly exists for debugging, it is not discussed further here.

#### 2.1.6 Change the dartboard

##### The dartboard type

By default, the `darts checkouts` subcommand generates checkout tables for the London dartboard.
You can change the dartboard type using the `-b` and `--board` options.
This option has the following possible values:

- `London` for the London dartboard (default);
- `Quadro` for the [Quadro 240](#quadro) board;
- `Yorkshire` for the [Yorkshire](#yorkshire) dartboard.

##### Quadro

The Quadro 240 board is a dartboard with quadruple fields in addition to the usual single, double and triple fields (see [Print a dartboard](#22-print-a-dartboard)).
The following command can be used to generate a checkout table for a double-out darts game using this board:

```shell
darts checkouts -b quadro 501 501
```

The resulting output shows there are six seven-dart checkouts with a Quadro board for a score of 501 points:

```markdown
| Score |   1 |   2 |   3 |   4 |   5 |   6 |   7 | # |
|------:|----:|----:|----:|----:|----:|----:|----:|--:|
|   501 |   * |   * |   * |   * |   * |   * |   * | 6 |
|       | Q20 | Q20 | Q20 | Q20 | Q20 | T17 | D25 | 6 |
```

##### Yorkshire

`darts` also supports the legacy Yorkshire dartboard.
This dartboard does not have a single bull or triple fields (see [Print a dartboard](#22-print-a-dartboard)).
To find checkouts for games with the Yorkshire board, use a command like:

```shell
darts checkouts -b yorkshire 20 21
```

The resulting (truncated) checkout table is:

```markdown
| Score |   1 |   2 |  # |
|------:|----:|----:|---:|
|    20 |   * |   * |  1 |
|       | D10 |   - |  1 |
|    21 |   * |   * | 10 |
|       |  19 |  D1 |  1 |
|       |  17 |  D2 |  1 |
|       | ... | ... | .. |
|       |   3 |  D9 |  1 |
|       |   1 | D10 |  1 |
```

#### 2.1.7 Change the checkout finder

##### The checkout finder type

By default, the `darts checkouts` subcommand uses an optimized descending checkout finder.
The `darts` toolbox, however, also provides a Cartesian checkout finder, which uses a brute-force method to find checkouts and sorts results differently.
If you want to change the checkout finder, use the `-f` or `--finder` option, which supports the following values:

- `descending` for an optimized descending checkout finder (default);
- `Cartesian` for a brute-force [Cartesian](#cartesian) checkout finder.

##### Cartesian

To use the Cartesian checkout finder, execute a command like:

```shell
darts checkouts -f cartesian 20 21
```

The resulting checkout table looks as follows:

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

Checkout tables generated using the Cartesian checkout finder look different than tables produced by the descending checkout finder.
With the Cartesian checkout finder, checkouts for a given score are sorted by throw score (in ascending order) and type (single, double, triple, quadruple).
Additionally, checkouts are not summarized.
That is, each possible checkout is represented using its own row.
Consequently, checkout tables can become very long when many checkouts exist.

The Cartesian checkout finder looks for checkouts with a brute-force method that scans all possible combinations of available dartboard fields.
Therefore, this checkout finder tends to be slow for scores that require more than five darts.

### 2.2 Print a dartboard

#### 2.2.1 Choose the dartboard

In addition to generating checkout tables, `darts` can print the dartboards it supports.
To that end, use the `darts boards` subcommand and supply it with the dartboard type.

##### The dartboard type

The three types of supported dartboards are designated using the following parameters:

- `London` for the [London](#london) dartboard;
- `Quadro` for the [Quadro 240](#quadro-1) board;
- `Yorkshire` for the [Yorkshire](#yorkshire-1) dartboard.

##### London

To see which fields the London dartboard consists of, use the command:

```shell
darts boards london
```

The output, which is truncated for brevity, looks as follows:

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

##### Quadro

Similarly, the Quadro 240 board can be inspected using the command:

```shell
darts boards quadro
```

The output of this command will look similar to:

```markdown
|   S |   D |   T |   Q |
|----:|----:|----:|----:|
|   1 |  D1 |  T1 |  Q1 |
|   2 |  D2 |  T2 |  Q2 |
|   3 |  D3 |  T3 |  Q3 |
| ... | ... | ... | ... |
|  19 | D19 | T19 | Q19 |
|  20 | D20 | T20 | Q20 |
|  25 | D25 |   - |   - |
```

As can be observed, the Quadro board is a dartboard with quadruple fields in addition to the usual single, double and triple fields.

##### Yorkshire

The Yorkshire board can be printed with the following command:

```shell
darts boards yorkshire
```

This dartboard has no single bull and no triple fields:

```markdown
|   S |   D |
|----:|----:|
|   1 |  D1 |
|   2 |  D2 |
|   3 |  D3 |
| ... | ... |
|  19 | D19 |
|  20 | D20 |
|   - | D25 |
```

#### 2.2.2 Change the output format

##### The output format

By default, the `darts boards` subcommand prints dartboards as a Markdown table.
The output format can be changed using the `-o` and `--output` options.
The following output formats are supported:

- `Markdown` for an easily readable Markdown table (default);
- `HTML` for an [HTML](#html-1) table for web pages;
- `JSON` for a [JSON](#json-1) object for computer processing;
- `string` for output based on Java's [string](#string-1) representation of objects.

##### HTML

The following command shows how the `darts boards` subcommand can generate HTML output:

```shell
darts boards -o html london
````

The (truncated) output looks as follows:

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

##### JSON

To retrieve a dartboard in the JSON format, use a command like:

```shell
darts boards -o json london
````

The output will be of the following form:

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

##### String

Dartboards can also be printed as a string showing the internals of the used Java objects.
This feature mainly exists for debugging purposes and is not discussed further here.

### 2.3 Get help

In addition to consulting this online documentation, one can request command-line help from `darts` itself.

To receive general information about `darts` or to see which subcommands are available, execute:

```shell
darts
```

Alternatively, run any of the following commands: `darts help`, `darts -h` or `darts --help`.

You can receive help with the `darts checkouts` subcommand by executing:

```shell
darts checkouts
```

Otherwise, execute `darts help checkouts`, `darts checkouts -h` or `darts checkouts --help`.

The `darts boards` subcommand returns help when it is not passed (valid) parameters:

```shell
darts boards
```

It is also possible to run `darts help boards`, `darts boards -h` or `darts boards --help`.

## 3. Troubleshooting

### 3.1 Installation

#### 3.1.1 The `install` command was not found

While trying to install `darts`, you may encounter the following errors:

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

```text
./install: No such file or directory
```

To solve these problems, take the following steps:

- Ensure you navigated to the folder containing the `darts` installation scripts, `install` and `install.bat`.
    - If you downloaded the latest Java darts CLI release, these scripts can be found in the extracted folder.
    - If you downloaded the full `darts` code, these scripts can be found in the `cli/java-darts-cli` folder of that code.
- Prefix the `install` command with `./` (Bash) or `.\` (PowerShell) before execution.

#### 3.1.2 Java was not installed or found

While installing or executing `darts`, you may encounter the following errors:

```text
JAVA_HOME is not set and no 'java' command could be found in your PATH.
```

```text
JAVA_HOME is set to an invalid directory: ...
```

To solve these problems, follow the instructions for [installing Java 21+](#11-install-java-21).

#### 3.1.3 The `darts` toolbox was not built

While executing `darts`, you may encounter the following errors:

```text
Error: Could not find or load main class nl.mauritssilvis.darts.java.cli.DartsApp
Caused by: java.lang.ClassNotFoundException: nl.mauritssilvis.darts.java.cli.DartsApp
```

```text
Error occurred during initialization of boot layer
java.lang.module.FindException: Module nl.mauritssilvis.darts.java.cli not found
```

To solve these problems, [install](#12-install-darts) the `darts` toolbox.

#### 3.1.4 Java is not up-to-date

While executing `darts`, you may encounter the following errors:

```text
Error: LinkageError occurred while loading main class nl.mauritssilvis.darts.java.cli.DartsApp
        java.lang.UnsupportedClassVersionError: nl/mauritssilvis/darts/java/cli/DartsApp
        has been compiled by a more recent version of the Java Runtime (class
        file version ...), this version of the Java Runtime only recognizes
        class file versions up to ...
```

```text
Error occurred during initialization of boot layer
java.lang.module.FindException: Error reading module: ...lib/java-darts-cli-....jar
Caused by: java.lang.module.InvalidModuleDescriptorException: Unsupported
major.minor version ...
```

To solve these problems, follow the instructions for [installing Java 21+](#11-install-java-21).

#### 3.1.5 Installing `darts` failed

While installing `darts`, you may encounter other errors than those listed above.
Such errors may contain a Gradle failure message like:

```text
FAILURE: Build failed with an exception.
...
BUILD FAILED in ...s
...
```

Should the Gradle build fail, please [report an issue](https://github.com/mauritssilvis/darts/issues) and document the error and how it can be reproduced.

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

```text
./darts: No such file or directory
```

To solve these problems, take the following steps:

- Install `darts` by following the [installation instructions](#1-installation).
- Ensure you navigated to the folder containing the `darts` installation scripts, `install` and `install.bat`.
    - If you downloaded the latest Java darts CLI release, these scripts can be found in the extracted folder.
    - If you downloaded the full `darts` code, these scripts can be found in the `cli/java-darts-cli` folder of that code.
- Navigate to the `bin` folder.
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

#### 3.2.3 Unmatched argument

While executing `darts`, you may encounter the following errors:

```text
Unmatched argument at index 0: 'board'
```

```text
Unmatched argument at index 0: 'checkout'
```

To solve these problems, use one of the existing subcommands of `darts`:

- `darts checkouts` for [generating a darts checkout table](#21-generate-a-darts-checkout-table).
- `darts boards` for [printing a dartboard](#22-print-a-dartboard).
- `darts help` for [getting help](#23-get-help).

#### 3.2.4 Missing required `minimum` or `maximum` parameters

While executing the `darts checkouts` subcommand, you may encounter the following errors:

```text
Missing required parameters: '<minimum>', '<maximum>'
```

```text
Missing required parameter: '<maximum>'
```

To solve these problems, [choose the scores](#211-choose-the-scores) for which you would like to generate a checkout table.

#### 3.2.5 Missing required `board` parameter

While executing the `darts boards` subcommand, you may encounter the following error:

```text
Missing required parameter: '<board>'
```

To solve this problem, [specify the dartboard](#221-choose-the-dartboard) you would like to print.

#### 3.2.6 Invalid value

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

#### 3.2.7 Missing required option parameter

While executing the `darts checkouts` or `darts boards` subcommands, you may encounter the following error:

```text
Missing required parameter for option '...'
```

To solve this problem, choose a valid option parameter from the [usage instructions](#2-usage).

#### 3.2.8 Unknown option

While executing the `darts checkouts` or `darts boards` subcommands, you may encounter the following error:

```text
Unknown option: '...'
```

To solve this problem, choose an existing subcommand option from the [usage instructions](#2-usage).

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the [GNU General Public License v3.0](LICENSE.md) or later.
