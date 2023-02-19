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

### Install Java 17+

`darts` requires the installation of Java 17 or higher.

You can install the latest version of Java as follows:

- Go to https://jdk.java.net/.
- Navigate to the page with the latest ready-to-use version of the Java Development Kit (JDK), currently JDK 19.
- From the builds section, download the archive matching your system.
- Extract the archive to a convenient location.
- Set the `JAVA_HOME` environment variable to the full path of the extracted `jdk-XX.X.X` folder.

### Build `darts`

You can build the `darts` toolbox as follows:

- Clone or download the `darts` code from https://github.com/mauritssilvis/darts.
- If necessary, extract the code to a convenient location.
- Navigate to the `darts/java` folder.
- Run the `install` command.
- Test execution of the `darts` toolbox by running the `darts` command.

## 2. Usage

### Generate darts checkout tables

#### Introduction

#### Default settings

#### Change the dartboard

#### Change the game mode

#### Select the number of throws

#### Change the output format

#### Change the checkout finder

### Print dartboards

#### Choose the dartboard

#### Change the output format

### Getting help

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

To solve these problems, take the following steps:

- Ensure you navigated to the `darts/java` folder.
- Prefix the `install` command with `./` (Bash) or `.\` (PowerShell) before execution.

#### 3.1.2 Java was not installed or found

While trying to install or execute `darts`, you may encounter the following errors:

```text
JAVA_HOME is not set and no 'java' command could be found in your PATH.
```

```text
JAVA_HOME is set to an invalid directory: ...
```

To solve these problems, follow the instructions for [installing Java 17+](#install-java-17).

#### 3.1.3 The `darts` toolbox was not built

While trying to execute `darts`, you may encounter the following error:

```text
Error: Could not find or load main class nl.mauritssilvis.darts.java.cli.DartsApp
Caused by: java.lang.ClassNotFoundException: nl.mauritssilvis.darts.java.cli.DartsApp
```

To solve this problem, [build](#build-darts) the `darts` toolbox.

#### 3.1.4 Java is not up-to-date

While trying to execute `darts`, you may encounter the following error:

```text
Error: LinkageError occurred while loading main class nl.mauritssilvis.darts.java.cli.DartsApp
        java.lang.UnsupportedClassVersionError: nl/mauritssilvis/darts/java/cli/DartsApp
        has been compiled by a more recent version of the Java Runtime (class
        file version 61.0), this version of the Java Runtime only recognizes 
        class file versions up to XX.X
```

To solve these problems, follow the instructions for [installing Java 17+](#install-java-17).

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

## License

Copyright © 2023 Maurits Silvis

This source code package is subject to the terms and conditions defined in the GNU General Public License v3.0, which can be found in the file [LICENSE.md](../LICENSE.md), or later.
