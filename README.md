# Expense-Tracker
An expense tracker utilizing Java and SQLite.

## Prerequisites
- Java JDK (tested with JDK 21)
- PowerShell (Windows)
- `lib\sqlite-jdbc-3.51.0.0.jar` (included in the `lib` folder)

## Build & Run (Windows PowerShell)
These commands compile every Java source in the repository and then run the application with the bundled SQLite JDBC driver. Copy/paste each block into a PowerShell prompt in the project root.

1) Compile all Java sources into `out\`:

```powershell
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }
if (!(Test-Path out)) { New-Item -ItemType Directory -Path out | Out-Null }
javac -d out $files
if ($LASTEXITCODE -ne 0) { Write-Output "javac exit code: $LASTEXITCODE" }
```

2) Run the program (include the main class name after the classpath):

```powershell
java --enable-native-access=ALL-UNNAMED -cp "out;lib\sqlite-jdbc-3.51.0.0.jar" main.main
```

3) One-line (compile then run):

```powershell
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }; if (!(Test-Path out)) { New-Item -ItemType Directory -Path out | Out-Null }; javac -d out $files; if ($LASTEXITCODE -ne 0) { Write-Output "javac exit code: $LASTEXITCODE" }; java --enable-native-access=ALL-UNNAMED -cp "out;lib\sqlite-jdbc-3.51.0.0.jar" main.main
```

## DB verification helper
A small helper program is available at `tools/DBInspect.java`. It prints the `transactions` table schema and the most recent rows. Run it like this (after compiling):

```powershell
java --enable-native-access=ALL-UNNAMED -cp "lib\sqlite-jdbc-3.51.0.0.jar;.;tools;out" DBInspect
```

## Troubleshooting
- If `java` prints its usage, you likely omitted the main class after `-cp`. Always include `main.main` (or the fully-qualified main class you want to run).
- If DB Browser doesn't show new rows, close and reopen the DB file in the viewer or use the `DBInspect` helper to confirm the absolute path and the current rows. The helper prints the absolute DB path so you can ensure you're viewing the same file.
- If you see native-access warnings from SQLite, the `--enable-native-access=ALL-UNNAMED` flag suppresses those warnings — it's recommended but optional.

