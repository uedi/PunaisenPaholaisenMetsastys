
package com.ued1.punaisenpaholaisenmetsastys.logiikka;

import com.ued1.punaisenpaholaisenmetsastys.apuvalineet.KossuPotion;
import com.ued1.punaisenpaholaisenmetsastys.apuvalineet.Pupu;
import com.ued1.punaisenpaholaisenmetsastys.hahmot.Pelaaja;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class KapakkaTest {
    
    public Pelaaja pelaaja;
    public KossuPotion kossu;
    public Kapakka kapakka;
    
    public KapakkaTest() {
        pelaaja = new Pelaaja("Testaaja");
        kossu = new KossuPotion(pelaaja);
    }
    
    @Before
    public void setUp() {
        kapakka = new Kapakka(pelaaja);
    }
    
    @Test
    public void KossuPotionLoytyyValikoimasta() {
        assertTrue(kapakka.hinnastoMerkkijonona().contains(kossu.toString()));
    }
    
    @Test
    public void PupuListattuOstettavissaKunRahaa() {
        assertTrue(kapakka.getValikoima().get(2).toString().contains("Pupu"));
        assertFalse(kapakka.ostettavissaOlevat(pelaaja).contains("Pupu"));
        pelaaja.muutaRahoja(new Pupu(pelaaja).arvo());
        assertTrue(kapakka.ostettavissaOlevat(pelaaja).contains("Pupu"));
    }
    
    @Test
    public void metodiOstettavissaOlevatIlmoittaaJosEiRahaa() {
        pelaaja.muutaRahoja(-200000000);
        assertTrue(kapakka.ostettavissaOlevat(pelaaja).contains("Sinulla ei ole yhtään rahaa"));
    }
    
    @Test
    public void pelaajaVoiOstaaPupunKunTarpeeksiRahaa() {
        assertTrue(kapakka.getValikoima().get(2).toString().contains("Pupu"));
        assertFalse(kapakka.voikoPelaajaOstaaOstoksen(pelaaja, 2));
        pelaaja.muutaRahoja(new Pupu(pelaaja).arvo());
        assertTrue(kapakka.voikoPelaajaOstaaOstoksen(pelaaja, 2));
    }
    
            
}