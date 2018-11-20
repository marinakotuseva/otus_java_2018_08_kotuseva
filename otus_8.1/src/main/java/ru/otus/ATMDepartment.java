package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class ATMDepartment {
    private Map<Integer, ATM> atms;

    public void setAtms(HashMap<Integer, ATM> atms) {
        this.atms = atms;
    }

    public Integer getTotalBalance(){
        Integer totalBalance = 0;
        for (var entry :
                atms.entrySet()) {
            var atm = entry.getValue();
            var currentb = atm.getBalanceInteger();
            totalBalance+= currentb;
        }
        return totalBalance;
    }

    public void initializeATMs(){
        for (var entry :
                atms.entrySet()) {
            var atm = entry.getValue();
            atm.initializeATM();
        }
    }
}
