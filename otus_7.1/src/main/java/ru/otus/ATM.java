package ru.otus;

public class ATM {
    //private float balance;
    private int banknotes10Amount;
    private int banknotes50Amount;
    private int banknotes100Amount;

    public float getBalance(){
        return getBanknotes10Amount()*10 + getBanknotes50Amount()*50 + getBanknotes100Amount()*100;
    }

    public static void takeBanknote(ATM atm, Banknote banknote){
        banknote.AddToBalance(atm);
    }

    public void setBanknotes10(int banknotes10Amount) {
        this.banknotes10Amount = banknotes10Amount;
    }
    public void setBanknotes50(int banknotes50Amount) {
        this.banknotes50Amount = banknotes50Amount;
    }
    public void setBanknotes100(int banknotes100Amount) {
        this.banknotes100Amount = banknotes100Amount;
    }

    public int getBanknotes10Amount() {
        return banknotes10Amount;
    }
    public int getBanknotes50Amount() { return banknotes50Amount; }
    public int getBanknotes100Amount() {
        return banknotes100Amount;
    }

    public String getCash(ATM atm, double money){
        boolean haveCash = true;
        int needed100 = 0;
        int needed50 = 0;
        int needed10 = 0;
        int got10 = 0;
        int got50 = 0;
        int got100 = 0;
        String res;
        double leftMoney;

        float currentCash = atm.getBalance();

        if (money > currentCash){
            haveCash = false;
        } else {
            needed100 = (int)money/100;
            //System.out.println("needed100 = " + needed100);
            //System.out.println("banknotes100Amount = " + atm.banknotes100Amount);
            leftMoney = money - needed100*100;
            if (needed100 <= atm.banknotes100Amount){
                got100 = needed100;
                //System.out.println(leftMoney);
                needed50 = (int)leftMoney/50;
                //System.out.println("needed50 = " + needed50);
                //System.out.println("banknotes50Amount = " + atm.banknotes50Amount);
                //leftMoney = leftMoney - needed50*50;
                System.out.println(leftMoney);
                if ((needed50 <= atm.banknotes50Amount)){
                    got50 = needed50;
                    needed10 = (int)leftMoney/10;
                    leftMoney = leftMoney - needed10*10;
                    //System.out.println("needed10 = " + needed10);
                    //System.out.println("banknotes10Amount = " + atm.banknotes10Amount);
                    //System.out.println(leftMoney);
                    if (leftMoney > 0){
                        haveCash = false;
                    } else if ( needed10 <= atm.banknotes10Amount){
                        got10 = needed10;
                    }
                } else {
                    needed10 = (int)leftMoney/10;
                    leftMoney = leftMoney - needed10*10;
                    //System.out.println("needed10 = " + needed10);
                    //System.out.println("banknotes10Amount = " + atm.banknotes10Amount);
                    //System.out.println(leftMoney);
                    if (leftMoney > 0) {
                        haveCash = false;
                    } else {
                        got10 = needed10;
                    }
                }

            } else {
                needed50 = (int)money/50;
                //System.out.println("needed50 = " + needed50);
                //System.out.println("banknotes50Amount = " + atm.banknotes50Amount);
                leftMoney = money - needed50*50;
                if (needed50 <= atm.banknotes50Amount){
                    got50 = needed50;
                    needed10 = (int)leftMoney/10;
                    leftMoney = leftMoney - needed10*10;
                    if (needed10 < atm.banknotes10Amount & leftMoney > 0){
                        haveCash = false;
                    } else {
                        got10 = needed10;
                    }
                } else {
                    needed10 = (int)money/10;
                    leftMoney = leftMoney - needed10*10;
                    if (leftMoney > 0) {
                        haveCash = false;
                    } else {
                        got10 = needed10;
                    }
                }
            }

        }


        if (!haveCash){
            res = "Can't return cash";
        } else {
            res = "You have " + got100 + " banknotes by 100, " + got50 + " banknotes by 50, " + got10 +  " banknotes by 10";
        }

        return res;
    }
}
