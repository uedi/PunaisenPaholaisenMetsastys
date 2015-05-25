package com.ued1.punaisenpaholaisenmetsastys.gui.logiikka;

import com.ued1.punaisenpaholaisenmetsastys.Paikka;
import com.ued1.punaisenpaholaisenmetsastys.hahmot.Pelaaja;
import com.ued1.punaisenpaholaisenmetsastys.logiikka.Asepaja;
import com.ued1.punaisenpaholaisenmetsastys.logiikka.HaarniskaKauppa;
import java.awt.event.KeyEvent;

public class KomennonValidoija {

    private Pelaaja pelaaja;
    private Asepaja asepaja;
    private HaarniskaKauppa haarniskaKauppa;

    public KomennonValidoija(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
        this.asepaja = new Asepaja();
        this.haarniskaKauppa = new HaarniskaKauppa();
    }

    public boolean onkoKomento(Paikka paikka, int koodi) {
        if (paikka == Paikka.KYLA) {
            if (koodi == KeyEvent.VK_M || koodi == KeyEvent.VK_A || koodi == KeyEvent.VK_H || koodi == KeyEvent.VK_T) {
                return true;
            }
        } else if (paikka == Paikka.ASEPAJA) {
            if (koodi == KeyEvent.VK_O || koodi == KeyEvent.VK_M || koodi == KeyEvent.VK_T) {
                return true;
            }
        } else if (paikka == Paikka.HAARNISKAKAUPPA) {
            if (koodi == KeyEvent.VK_O || koodi == KeyEvent.VK_T) {
                return true;
            }
        } else if (paikka == Paikka.METSA) {
            if (koodi == KeyEvent.VK_E || koodi == KeyEvent.VK_L || koodi == KeyEvent.VK_T) {
                return true;
            }
        } else if (paikka == Paikka.MONSTERITAISTELU) {
            if (koodi == KeyEvent.VK_L || koodi == KeyEvent.VK_J) {
                return true;
            }
        } else if (paikka == Paikka.TAISTELUAREENA) {
            if (koodi == KeyEvent.VK_T || koodi == KeyEvent.VK_A) {
                return true;
            }
        } else if (paikka == Paikka.ASEENOSTO) {
            if (asepaja.voikoPelaajaOstaaAseenNumero(pelaaja, koodi-48) || koodi == KeyEvent.VK_T) {
                return true;
            }
        } else if(paikka == Paikka.ASEENMYYNTI) {
            if ((koodi == KeyEvent.VK_K || koodi == KeyEvent.VK_E) && asepaja.voikoMyydaAseen(pelaaja)) {
                return true;
            } else if (koodi == KeyEvent.VK_T) {
                return true;
            }
        } else if(paikka == Paikka.HAARNISKANOSTO) {
            if(haarniskaKauppa.voikoOstaaHaarniskanNumero(pelaaja, koodi-48) || koodi == KeyEvent.VK_T) {
                return true;
            }
        } else if(paikka == Paikka.MONSTERITAISTELUTAPPIO) {
            
            // TODO: Q - lopeta peli
            
            if (koodi == KeyEvent.VK_J) {
                return true;
            }
        } else if(paikka == Paikka.TAISTELUAREENATULOS) {
            
            if (koodi == KeyEvent.VK_J) {
                return true;
            }
        } else if(paikka == Paikka.TAISTELUAREENAEI) {
            
            if (koodi == KeyEvent.VK_T) {
                return true;
            }
        } else if(paikka == Paikka.AREENATAISTELU) {
            if (koodi == KeyEvent.VK_L) {
                return true;
            }
        }
        
        
        
        return false;
    }

}