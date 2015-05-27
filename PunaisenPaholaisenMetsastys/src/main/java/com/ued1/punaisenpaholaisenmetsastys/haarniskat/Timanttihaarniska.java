
package com.ued1.punaisenpaholaisenmetsastys.haarniskat;

public class Timanttihaarniska implements Haarniska {

    @Override
    public int suojaa() {
        return 45;
    }

    @Override
    public String nimi() {
        return "Timanttihaarniska";
    }

    @Override
    public int arvo() {
        return 500000;
    }
    
}