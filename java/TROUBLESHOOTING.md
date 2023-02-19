### The `darts` command was not found

Bash:

```shell
darts
```

```text
darts: command not found
```

Command prompt:

```shell
darts
```

```text
'darts' is not recognized as an internal or external command, operable program or batch file.
```

PowerShell:

```shell
darts
```

```text
darts : The term 'darts' is not recognized as the name of a cmdlet, function, script file, or operable program.
```

- Ensure you navigated to the `darts/java` directory.
- Prefix the `darts` command with `./` (Bash) or `\.` (PowerShell).

### The `darts` toolbox was not built

Bash:

```shell
./darts
```

Command prompt:

```shell
darts
```

PowerShell:

```shell
.\darts
```

```text
Error: Could not find or load main class nl.mauritssilvis.darts.java.cli.DartsApp
Caused by: java.lang.ClassNotFoundException: nl.mauritssilvis.darts.java.cli.DartsApp
```

- Build the `darts` toolbox.

### Java was not installed or found

Bash:

```shell
./install # or
./darts   # or
./gradlew # or
```

Command prompt:

```shell
install # or
darts   # or
gradlew # or
```

PowerShell:

```shell
.\install # or
.\darts   # or
.\gradlew # or
```

```text
JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the location of your Java installation.
```

```text
ERROR: JAVA_HOME is set to an invalid directory: ...

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation.
```

- Install Java 17+.
- Ensure the `JAVA_HOME` environment variable points to the Java installation location.

### Java is not up-to-date

Bash:

```shell
./install # and
./darts
```

Command prompt:

```shell
install # and
darts
```

PowerShell:

```shell
.\install # and
.\darts
```

```text
Error: LinkageError occurred while loading main class nl.mauritssilvis.darts.java.cli.DartsApp
        java.lang.UnsupportedClassVersionError: nl/mauritssilvis/darts/java/cli/DartsApp has been compiled by a more recent version of the Java Runtime (class file version 61.0), this version of the Java Runtime only recognizes class file versions up to 59.0
```

- Install Java 17+.
- Ensure the `JAVA_HOME` environment variable points to the Java installation location.
