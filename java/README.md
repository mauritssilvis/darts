# darts > Java

> A Java-based computational toolbox aimed at the game of darts

## Introduction

This part of the [darts](https://github.com/mauritssilvis/darts) project provides a Java-based computational toolbox aimed at the game of darts.
This command-line toolbox, referred to as `darts` in what follows, can:

- Determine all possible darts checkouts.
- Generate checkout tables for any range of scores.

Additionally, `darts` lets you:

- Choose the dartboard type.
- Select the game mode.
- Set the number of darts.
- Pick the output format.

In what follows, the [installation](#1-installation) and [usage](#2-usage) of `darts` are discussed.
For solutions to commonly occurring problems, refer to the [troubleshooting](#3-troubleshooting) section.
If you are interested in exploring extensive checkout tables for different types of darts games, refer to the [darts > Markdown](../md) readme.

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
- Navigate to the `darts/java` folder.
- Run the `install` command.
- Test execution of the `darts` toolbox by running the `darts` command.

## 2. Usage

### 2.1 Generate a darts checkout table

#### 2.1.1 Choose the scores

#### 2.1.2 Default settings

#### 2.1.3 Change the dartboard

#### 2.1.4 Change the game mode

#### 2.1.5 Select the number of throws

#### 2.1.6 Change the output format

#### 2.1.7 Change the checkout finder

### 2.2 Print a dartboard

#### 2.2.1 Choose the dartboard

#### 2.2.2 Change the output format

### 2.3 Get help

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

- Ensure you navigated to the `darts/java` folder.
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
- Navigate to the `darts/java` directory.
- Prefix the `darts` command with `./` (Bash) or `.\` (PowerShell) before execution.

#### 3.2.2 Missing required subcommand

While executing `darts`, you may encounter the following error:

```text
Missing required subcommand
```

To solve this problem, execute `darts` with a subcommand:

- `darts help` for [getting help](#23-get-help).
- `darts boards` for [printing a dartboard](#22-print-a-dartboard).
- `darts checkouts` for [generating a darts checkout table](#21-generate-a-darts-checkout-table).

#### 3.2.3 Missing required `board` parameter

While executing the `darts boards` subcommand, you may encounter the following error:

```text
Missing required parameter: '<board>'
```

To solve this problem, [specify the dartboard](#221-choose-the-dartboard) that you would like to print.

#### 3.2.4 Missing required `minimum` and/or `maximum` parameters

While executing the `darts checkouts` subcommand, you may encounter the following errors:

```text
Missing required parameters: '<minimum>', '<maximum>'
```

```text
Missing required parameter: '<maximum>'
```

To solve these problems, [choose the scores](#211-choose-the-scores) for which you would like to generate a checkout table.

#### 3.2.5 Invalid value

While executing the `darts boards` or `darts checkouts` subcommands, you may encounter the following errors:

```text
Invalid value for positional parameter at index ...: expected one of ... but was '...'
```

```text
Invalid value for positional parameter at index ...: '...' is not an int
```

```text
Invalid value for option '...': expected one of ... but was '...'
```

To solve these problems, refer to the [usage instructions](#2-usage) to pick valid parameters.

#### 3.2.6 Missing required option parameter

While executing the `darts boards` or `darts checkouts` subcommands, you may encounter the following error:

```text
Missing required parameter for option '...'
```

To solve this problem, refer to the [usage instructions](#2-usage) to provide the option with a valid parameter.

#### 3.2.7 Unknown option

While executing the `darts boards` or `darts checkouts` subcommands, you may encounter the following error:

```text
Unknown option: '...'
```

To solve this problem, pick an existing subcommand option from the [usage instructions](#2-usage).

### 3.3 Other issues

#### 3.3.1 Building `darts` failed

While building `darts`, you may encounter another error than those listed above.
Such an error may contain a message like:

```text
FAILURE: Build failed with an exception.
...
BUILD FAILED in XXs
...
```

Should this situation occur, please [report an issue](https://github.com/mauritssilvis/darts/issues), documenting exactly how your issue can be reproduced.

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../LICENSE.md), or later.
