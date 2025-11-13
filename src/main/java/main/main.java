package main;

import Wallets.Accounts.Gcash;
import Wallets.Accounts.Metrobank;

public class main {
    public static void main(String[] args) {
        // Simple run: create two accounts and display info (no DB required)
        Gcash gcash = new Gcash(800.0);
        Metrobank metro = new Metrobank(7000.0);

        gcash.DisplayInfo();
        metro.DisplayInfo();
    }
}
