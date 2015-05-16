
package com.ued1.punaisenpaholaisenmetsastys.logiikka;

import com.ued1.punaisenpaholaisenmetsastys.haarniskat.*;
import com.ued1.punaisenpaholaisenmetsastys.hahmot.Pelaaja;
import java.util.ArrayList;

public class HaarniskaKauppa {
    
    private ArrayList<Haarniska> haarniskat;
    
    public HaarniskaKauppa() {
        haarniskat = new ArrayList<>();
        lisaaHaarniskatValikoimaan();
        // lajittele haarniskat arvojärjestykseen
    }
    
    private void lisaaHaarniskatValikoimaan() {
        haarniskat.add(new Riepu());
        haarniskat.add(new Vaatteet());
    }
    
    public boolean voikoOstaaHaarniskat(Pelaaja pelaaja, Haarniska haarniska) {
        if(pelaaja.getRahat() < haarniska.arvo()) {
            return false;
        }
        return true;
    }
    
    // aseista poiketen vanhaa haarniskaa ei myydä eikä siitä saa rahaa takaisin
    public boolean ostaHaarniska(Pelaaja pelaaja, int haarniskanNumero) {
        if(voikoOstaaHaarniskat(pelaaja, haarniskat.get(haarniskanNumero))) {
            pelaaja.setHaarniska(haarniskat.get(haarniskanNumero));
            pelaaja.muutaRahoja(0-haarniskat.get(haarniskanNumero).arvo());
            return true;
        }
        return false;
    }
        
    public String hinnastoMerkkijonona() {
        String hinnasto = "";
        for (int i = 0; i < haarniskat.size(); i++) {
            hinnasto += "" + i + " " + haarniskat.get(i).nimi() + "  \t" + haarniskat.get(i).arvo() + "\n";
        }
        return hinnasto;
    }
    
}
