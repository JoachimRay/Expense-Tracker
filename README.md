# Expense-Tracker
An expense tracker utilizing Java and SQL



To run the program paste this into the terminal (Make sure you have maven installed and proper java version)

```
javac -d out Wallets/Functions/wallet.java Wallets/Accounts/*.java main/main.java ; if ($LASTEXITCODE -ne 0) { Write-Output "javac exit code: $LASTEXITCODE" } ; java --enable-native-access=ALL-UNNAMED -cp "out;lib\sqlite-jdbc-3.51.0.0.jar" main.main
```