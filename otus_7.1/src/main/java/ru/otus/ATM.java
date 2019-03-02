package ru.otus;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class ATM {
    private Integer atmBalance;
    private Map atmBanknotes = new TreeMap();
    private Integer balance;

    public ATM(){
        for (Banknote b:
                Banknote.values()) {
            atmBanknotes.put(b, 0);
        }
    }


    public String getAtmBalance(){
        Integer balance = 0;
        var str = "";
        for (Banknote b:
                Banknote.values()) {
            balance += (int)atmBanknotes.get(b)*b.getNom();
            str+= (int)atmBanknotes.get(b) + " banknotes by " + b.getNom() + ", ";
        }
        this.balance = balance;
        return str;
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
