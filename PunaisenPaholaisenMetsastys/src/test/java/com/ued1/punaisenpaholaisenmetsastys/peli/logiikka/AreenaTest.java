package com.ued1.punaisenpaholaisenmetsastys.peli.logiikka;

import com.ued1.punaisenpaholaisenmetsastys.peli.logiikka.Areena;
import com.ued1.punaisenpaholaisenmetsastys.peli.Vaikeus;
import com.ued1.punaisenpaholaisenmetsastys.peli.aseet.Maila;
import com.ued1.punaisenpaholaisenmetsastys.peli.haarniskat.Vaatteet;
import com.ued1.punaisenpaholaisenmetsastys.peli.hahmot.Hahmo;
import com.ued1.punaisenpaholaisenmetsastys.peli.hahmot.Pelaaja;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class AreenaTest {

    private Areena areena;
    private Areena helppoAreena;
    private Pelaaja pelaaja;
    private Pelaaja helppoPelaaja;

    public AreenaTest() {
        pelaaja = new Pelaaja("Testipelaaja");
        helppoPelaaja = new Pelaaja("Helppopelaaja", Vaikeus.HELPPO);
    }

    @Before
    public void setUp() {
        areena = new Areena(pelaaja);
        helppoAreena = new Areena(helppoPelaaja);
    }

    @Test
    public void taisteluaEiOleValmiina() {
        assertTrue(areena.getTaistelu() == null);
    }

    @Test
    public void metodiAloitaUusiTaisteluAsettaaTaistelun() {
        areena.aloitaUusiTaistelu();
        assertFalse(areena.getTaistelu() == null);
    }

    @Test
    public void uudenTaistelunAloittaminenParantaaPelaajan() {
        pelaaja.laskeVointia();
        assertTrue(pelaaja.getVointi() < pelaaja.getMaxVointi());
        areena.aloitaUusiTaistelu();
        assertEquals(pelaaja.getVointi(), pelaaja.getMaxVointi());
    }

    @Test
    public void otteluitaVoitettavaSeuraavanTasonVerran() {
        assertEquals(pelaaja.getTaso() + 1, areena.getOtteluitaJaljella());
        pelaaja.nostaTasoa();
        pelaaja.nostaTasoa();
        assertEquals(pelaaja.getTaso() + 1, areena.getOtteluitaJaljella());

    }

    private void tapaPelaaja(Pelaaja kohde) {
        int vointiAlussa = kohde.getVointi();
        for (int i = 0; i < vointiAlussa; i++) {
            kohde.laskeVointia();
        }
    }

    @Test
    public void pelaajanHavitessaPelaajaJaVastustajaParantuu() {
        areena.aloitaUusiTaistelu();
        areena.getTaistelu().taistele();
        tapaPelaaja(pelaaja);
        assertFalse(pelaaja.onkoElossa());
        areena.asetaTaistelunTulos();
        assertTrue(pelaaja.onkoElossa());
        assertEquals(pelaaja.getVointi(), pelaaja.getMaxVointi());
        Hahmo vastustaja = areena.getTaistelu().vastustaja();
        assertEquals(vastustaja.getVointi(), vastustaja.getMaxVointi());
    }

    @Test
    public void pelaajanHavitessaTaisteluJatkuu() {
        areena.aloitaUusiTaistelu();
        tapaPelaaja(pelaaja);
        areena.asetaTaistelunTulos();
        assertFalse(areena.getTaistelu() == null);
    }

    @Test
    public void pelaajanVoittaessaTaisteluLoppuu() {
        aloitaTaisteluJaAsetaTulos();
        assertTrue(areena.getTaistelu() == null);
    }

    @Test
    public void pelaajanHavitessaOtteluitaVoitettavaSailyy() {
        int jaljellaAlussa = areena.getOtteluitaJaljella();
        taisteleJaHavia();
        assertEquals(jaljellaAlussa, areena.getOtteluitaJaljella());
    }

    @Test
    public void pelaajanVoittaessaOtteluitaVoitettavaEriMaara() {
        int jaljellaAlussa = areena.getOtteluitaJaljella();
        aloitaTaisteluJaAsetaTulos();
        assertFalse(jaljellaAlussa == areena.getOtteluitaJaljella());
    }

    @Test
    public void voittaessaKaksiOtteluaNouseeTasolleKaksi() {
        aloitaTaisteluJaAsetaTulos();
        assertEquals(1, pelaaja.getTaso());
        aloitaTaisteluJaAsetaTulos();
        assertEquals(2, pelaaja.getTaso());
    }

    @Test
    public void tasoNouseeJaOtteluitaJaljellaOikein() {
        // tarvitaan nousuun: 2 + 3 + 4 + 5
        // 11 voittoa: tasolla 4 voitettava 3
        aloitaTaisteluJaAsetaTulos(11);
        assertEquals(4, pelaaja.getTaso());
        assertEquals(3, areena.getOtteluitaJaljella());
    }

    @Test
    public void tappioillaEiMerkitysta() {
        // edellinen testi tappioiden kanssa
        aloitaTaisteluJaAsetaTulos(3);
        taisteleJaHavia();
        taisteleJaHavia();
        aloitaTaisteluJaAsetaTulos(8);
        taisteleJaHavia();
        assertEquals(4, pelaaja.getTaso());
        assertEquals(3, areena.getOtteluitaJaljella());
    }

    private void aloitaTaisteluJaAsetaTulos() {
        areena.aloitaUusiTaistelu();
        areena.asetaTaistelunTulos();
    }

    private void aloitaTaisteluJaAsetaTulos(int kerrat) {
        for (int i = 0; i < kerrat; i++) {
            aloitaTaisteluJaAsetaTulos();
        }
    }

    private void taisteleJaHavia() {
        areena.aloitaUusiTaistelu();
        tapaPelaaja(pelaaja);
        areena.asetaTaistelunTulos();
    }

    @Test
    public void seuraavanTasonKokemusPalautuuOikein() {
        assertEquals(100, areena.seuraavanTasonKokemus()); // tasolle 2
        nostaTasolle(4);
        assertEquals(5000, areena.seuraavanTasonKokemus()); // tasolle 5
    }

    @Test
    public void pelaajaValmisAreenaanKunKokemustaTarpeeksi() {
        pelaaja.muutaKokemusta(99);
        assertEquals(99, pelaaja.getKokemus());
        assertFalse(areena.onkoPelaajaValmisAreenaan());
        pelaaja.muutaKokemusta(1);
        assertTrue(areena.onkoPelaajaValmisAreenaan());
        pelaaja.nostaTasoa();
        assertFalse(areena.onkoPelaajaValmisAreenaan());
    }

    @Test
    public void viimeisellaTasollaEiAreenaa() {
        nostaTasolle(15); // viimeinen taso korkeintaan 10
        pelaaja.muutaKokemusta(123456789);
        assertFalse(areena.onkoPelaajaValmisAreenaan());
    }

    private void nostaTasolle(int uusiTaso) {
        for (int i = 0; i < (uusiTaso - 1); i++) {
            pelaaja.nostaTasoa();
        }
    }

    @Test
    public void viimeisellaTasollaEiSeuraavanTasonKokemusta() {
        nostaTasolle(10);
        assertEquals(-1, areena.seuraavanTasonKokemus());
    }

    @Test
    public void metodiGetVastustajanTiedotToimii() {
        assertTrue(areena.getVastustajanTiedot().contains("vastustajasi selviää kun"));
        areena.aloitaUusiTaistelu();
        String vastustajanNimi = areena.getTaistelu().vastustaja().getNimi();
        assertTrue(areena.getVastustajanTiedot().contains(vastustajanNimi));
    }

    private void varustaPelaaja(Pelaaja varustettava) {
        varustettava.setAse(new Maila());
        varustettava.setHaarniska(new Vaatteet());
    }

    @Test
    public void vastustajaHeikkeneeVoittaessa() {
        varustaPelaaja(pelaaja);
        areena.aloitaUusiTaistelu();
        int vastustajanVoima = areena.getTaistelu().vastustaja().lyo();
        int vastustajanPuolustusVoima = areena.getTaistelu().vastustaja().suojaa();
        tapaPelaaja(pelaaja);
        areena.asetaTaistelunTulos();
        assertEquals(vastustajanVoima - 1, areena.getTaistelu().vastustaja().lyo());
        assertEquals(vastustajanPuolustusVoima - 1, areena.getTaistelu().vastustaja().suojaa());
    }

    @Test
    public void vastustajaHeikkeneeEnemmanHelpollaTasolla() {
        varustaPelaaja(pelaaja);
        taisteleJaHavia();
        varustaPelaaja(helppoPelaaja);
        helppoAreena.aloitaUusiTaistelu();
        tapaPelaaja(helppoPelaaja);
        helppoAreena.asetaTaistelunTulos();
        assertTrue(areena.getTaistelu().vastustaja().lyo() > helppoAreena.getTaistelu().vastustaja().lyo());
        assertTrue(areena.getTaistelu().vastustaja().suojaa() > helppoAreena.getTaistelu().vastustaja().suojaa());
    }

}
