package ru.otus;


public abstract class Banknote {
    abstract void AddToBalance(ATM atm);
}
class Banknote10 extends Banknote {
    @Override
    void AddToBalance(ATM atm){
        int current10 = atm.getBanknotes10Amount();
        atm.setBanknotes10(current10 + 1);
    }
}
class Banknote50 extends Banknote {
    @Override
    void AddToBalance(ATM atm){
        int current50 = atm.getBanknotes50Amount();
        atm.setBanknotes50(current50 + 1);
    }

}
class Banknote100 extends Banknote {
    @Override
    void AddToBalance(ATM atm){
        int current100 = atm.getBanknotes100Amount();
        atm.setBanknotes100(current100 + 1);
    }

}
