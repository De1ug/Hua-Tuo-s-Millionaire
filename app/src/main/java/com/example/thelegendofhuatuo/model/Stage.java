package com.example.thelegendofhuatuo.model;

public class Stage {
    private static final int time = 60000;
    private static final int[] money = { 1000,2000,3000,4000,8000,
                            10000,20000,30000,40000,60000,
                            80000,150000,250000,500000,1000000 };
    private static final boolean[] safeLine = {  false,false,false,false,true,
                                    false,false,false,false,true,
                                    false,false,false,false,true  };

    public static int getStageCount() {return money.length;}
    public static int getTime() {return time;}
    public static int getCurrentStatgeMoney(int stage) {
        if (stage < 0)
            return 0;

        if (stage > money.length)
            return money[money.length - 1];

        return money[stage];
    }
    public static int getStageMoney(int stage) {
        if (stage < 0)
            return 0;

        if (stage > money.length)
            return money[money.length - 1];

        if (isSafeLine(stage))
            return money[stage];
        else {
            for (int i = stage; i >= 0; i--) {
                if (isSafeLine(i))
                    return money[i];
            }
        }

        return 0;
    }

    public static boolean isSafeLine(int stage) {
        if (stage > safeLine.length || stage < 0)
            return false;

        return safeLine[stage];
    }
}
