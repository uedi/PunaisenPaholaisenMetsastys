
package com.ued1.punaisenpaholaisenmetsastys.hahmot;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class KilpailijaTest {
    
    private Kilpailija kilpailija;
    private Kilpailija toinenKilpailija;
    private String[] nimet = {"Ajeltu Luolamies", "Pillastunut Assari", "Pasi Puunhalaaja",
            "Alaston Anoppi", "Usko Eevertti", "Kenraali Kalsareissa", "Parraton Gandalf",
            "Karannut Vanki", "Kapteeni Koukku", "Tekstaileva Ministeri", "Batman Valeasussa",
            "Per Saukko", "Veli Sikiö", "Raitis Juoppo", "Karvainen Munkki",
            "Isoperseinen Anorektikko", "Kuntoileva Pyhimys", "Pullisteleva Putin"};
    
    public KilpailijaTest() {
    }
        
    @Before
    public void setUp() {
        kilpailija = new Kilpailija(20, 10, 5);
        toinenKilpailija = new Kilpailija(1, 2, 3);
    }
    
    @Test
    public void kilpailijanNimiLoytyyListalta() {
        List<String> nimetListana = Arrays.asList(nimet);
        assertTrue(nimetListana.contains(kilpailija.getNimi()));
        assertTrue(nimetListana.contains(toinenKilpailija.getNimi()));
    }
    
    @Test
    public void vointiLyontiPuolustusAsetettuOikein() {
        assertEquals(20, kilpailija.getVointi());
        assertEquals(10, kilpailija.lyo());
        assertEquals(5, kilpailija.suojaa());
    }
    
    
}