package ru.otus;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        System.out.println("Current balance: " + atm.getBalance());

        for (int i = 0; i < 10; i++) {
            Banknote10 bn10 = new Banknote10();
            ATM.takeBanknote(atm, bn10);
        }
        System.out.println("Current balance: " + atm.getBalance());

        for (int i = 0; i < 1; i++) {
            Banknote50 bn50 = new Banknote50();
            ATM.takeBanknote(atm, bn50);
        }
        System.out.println("Current balance: " + atm.getBalance());

        Banknote100 bn100 = new Banknote100();
        ATM.takeBanknote(atm, bn100);
        System.out.println("Current balance: " + atm.getBalance());

        String res = atm.getCash(atm, 180);
        System.out.println(res);

    }
}
