package ru.otus;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        // ATM 1
        ATM atm = new ATM("ATM1");
        Map ib = new TreeMap<>();
        ib.put(Banknote.BANKNOTE10, 1);
        atm.setInitialBanknotes(ib);
        atm.initializeATM();

        atm.takeBanknote(Banknote.BANKNOTE10);
        atm.takeBanknote(Banknote.BANKNOTE50);
        atm.takeBanknote(Banknote.BANKNOTE50);
        System.out.println("Current balance of " + atm.getName()+" : " + atm.getAtmBalance());
        atm.initializeATM();

        // ATM2
        ATM atm2 = new ATM("ATM2");
        atm2.takeBanknote(Banknote.BANKNOTE10);
        System.out.println("Current balance: " + atm2.getAtmBalance());

        // ATM department
        ATMDepartment dep = new ATMDepartment();
        HashMap<Integer, ATM> atms = new HashMap();
        atms.put(0, atm);
        atms.put(1, atm2);
        dep.setAtms(atms);

        Integer totalBalance = dep.getTotalBalance();
        System.out.println("Total balance of " + atms.size() + " ATMs is " + totalBalance);

        dep.initializeATMs();
        totalBalance = dep.getTotalBalance();
        System.out.println("Total balance of " + atms.size() + " ATMs after re-initializing is " + totalBalance);


    }
}
