package ru.otus;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        System.out.println("Current balance: " + atm.getAtmBalance());

        atm.takeBanknote(Banknote.BANKNOTE10);
        System.out.println("Current balance: " + atm.getAtmBalance());
        atm.takeBanknote(Banknote.BANKNOTE50);
        atm.takeBanknote(Banknote.BANKNOTE50);
        //atm.takeBanknote(Banknote.BANKNOTE100);
        System.out.println("Current balance: " + atm.getAtmBalance());

        atm.getCash(200);
        System.out.println("Current balance: " + atm.getAtmBalance());

        atm.getCash(110);
        System.out.println("Current balance: " + atm.getAtmBalance());
    }
}
