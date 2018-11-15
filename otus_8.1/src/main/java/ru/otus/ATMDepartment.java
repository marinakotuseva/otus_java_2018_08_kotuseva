package ru.otus;

import java.util.HashMap;

public class ATMDepartment {
    private HashMap<Integer, ATM> atmList;

    public void setAtmList(HashMap<Integer, ATM> atmList) {
        this.atmList = atmList;
    }

    public Integer getTotalBalance(){
        Integer totalBalance = 0;
        for (var entry :
                atmList.entrySet()) {
            var atm = entry.getValue();
            var currentb = atm.getBalanceInteger();
            totalBalance+= currentb;
        }
        return totalBalance;
    }

    public void initializeATMs(){
        for (var entry :
                atmList.entrySet()) {
            var atm = entry.getValue();
            atm.initializeATM();
        }
    }
}
