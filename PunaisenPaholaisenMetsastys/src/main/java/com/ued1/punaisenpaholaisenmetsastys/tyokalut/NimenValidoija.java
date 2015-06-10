
package com.ued1.punaisenpaholaisenmetsastys.tyokalut;

/**
 * Luokka tarkistaa, että käyttäjän antama pelaajanimi täyttää vaatimukset.
 */
public class NimenValidoija {
    
    private final int MINIMIPITUUS = 2;
    private final int MAKSIMIPITUUS = 15;
    
    
    public NimenValidoija() {
    }
    
    /**
     * Metodi tarkistaa onko merkkijono sopiva pelaajan nimeksi. Nimen
     * täytyy olla pituudeltaan 2-15 ja sisältää suomalaisia aakkosten
     * kirjaimia.
     * 
     * @param tarkistettavaNimi nimi, joka tarkistetaan
     * @return totuusarvo, true jos nimi on kelvollinen
     */
    public boolean tarkista(String tarkistettavaNimi) {
        String nimi = tarkistettavaNimi.trim();
        if(!onkoSopivaPituus(nimi) || !onkoVainKirjaimia(nimi)) {
            return false;
        }
        return true;
    }
    
    
    private boolean onkoSopivaPituus(String nimi) {
        return (nimi.length() >= MINIMIPITUUS && nimi.length() <= MAKSIMIPITUUS);
    }
    
    private boolean onkoVainKirjaimia(String nimi) {
        for(int i = 0; i < nimi.length(); i++) {
            if(nimi.charAt(i) >= (int)'A' && nimi.charAt(i) <= (int)'Z') {
                
            } else if(nimi.charAt(i) >= (int)'a' && nimi.charAt(i) <= (int)'z') {
                
            } else if("ÅåÄäÖö".contains(""+nimi.charAt(i))) {
                
            } else {
                return false;
            }
        }
        return true;
    }
    
}
