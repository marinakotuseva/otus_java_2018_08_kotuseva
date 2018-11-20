package ru.otus;

import java.util.*;

public class ATM {
    private Map<Banknote, Integer> atmBanknotes = new EnumMap<Banknote, Integer>(Banknote.class);
    private Map<Banknote, Integer> initialBanknotes = new EnumMap<Banknote, Integer>(Banknote.class);
    private int balance;

    public String getName() {
        return name;
    }

    private String name;

    public ATM(String name){
        for (Banknote b:
                Banknote.values()) {
            atmBanknotes.put(b, 0);
            initialBanknotes.put(b, 0);
        }
        balance = 0;
        this.name = name;
    }

    public void setInitialBanknotes(Map initialBanknotes) {
        for (Banknote b:
                Banknote.values()) {
            if (initialBanknotes.containsKey(b)){
                initialBanknotes.put(b, initialBanknotes.get(b));
            } else {
                initialBanknotes.put(b, 0);
            }
        }
        this.initialBanknotes = initialBanknotes;
    }

    public void initializeATM(){
        for (Banknote b:
                Banknote.values()) {
            if (initialBanknotes.containsKey(b)){
                atmBanknotes.put(b, initialBanknotes.get(b));
            } else {
                atmBanknotes.put(b, 0);
            }
        }
        System.out.println(name + " initialized with " + getAtmBalance());
    }

    public String getAtmBalance(){
        Integer balance = 0;
        var str = "";
        for (Banknote b:
                Banknote.values()) {
            balance += (int)atmBanknotes.get(b)*b.getNom();
            var d = b.getNom();
            str+= (int)atmBanknotes.get(b) + " banknotes by " + b.getNom() + ", ";
        }
        this.balance = balance;
        return str;
    }

    public Integer getBalanceInteger(){
        return this.balance;
    }

    public void takeBanknote(Banknote b){
        atmBanknotes.put(b, (int)atmBanknotes.get(b)+1);
        System.out.println("Taken 1 banknote by " + b.getNom());

    }

    public boolean getCash(Integer money){
        // In reverse order
        Map<Banknote, Integer> sortedAtmBanknotes = new TreeMap(Collections.reverseOrder());
        sortedAtmBanknotes.putAll(atmBanknotes);

        System.out.println("Want to withdraw " + money);
        if (this.balance < money){
            System.out.println("Not enough cash in the ATM");
            return false;
        }

        for (var entry:
                sortedAtmBanknotes.entrySet()) {
            var currentBn = entry.getKey();
            var currentNom = currentBn.getNom();
            var haveBanknotes = entry.getValue();
            var needBanknotes = money / currentNom;
            //System.out.println("need " + needBanknotes + " banknote by " + currentNom);
            //System.out.println("have " + haveBanknotes + " banknote by " + currentNom);
            if (haveBanknotes < needBanknotes) {
                //do nothing - try another nominal?
            } else {
                atmBanknotes.put(currentBn, (int)atmBanknotes.get(currentBn)-needBanknotes);
                money -= needBanknotes*currentNom;
                System.out.println("Withdrawn " + needBanknotes + " banknote by " + currentNom);
            }

        }
        return true;
    }
}
