package com.ued1.punaisenpaholaisenmetsastys.peli.haarniskat;

/**
 * Riepu on pelin oletushaarniska, jonka puolustusvoima on 1 ja arvo 0.
 */
public class Riepu implements Haarniska {

    @Override
    public int suojaa() {
        return 1;
    }

    @Override
    public String toString() {
        return "Riepu";
    }

    @Override
    public int arvo() {
        return 0;
    }

}
