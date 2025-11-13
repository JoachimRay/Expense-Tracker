package main;

import Wallets.Accounts.Gcash;
import Wallets.Accounts.Metrobank;

public class main {
	public static void main(String[] args) {
		Gcash gcash = new Gcash(800.0, "Gcash", 10);
		Metrobank metro = new Metrobank(7000.0);

		gcash.DisplayInfo();
		metro.DisplayInfo();
	}
}
