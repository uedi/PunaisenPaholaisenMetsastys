
package com.ued1.punaisenpaholaisenmetsastys.gui;

import com.ued1.punaisenpaholaisenmetsastys.Paikka;
import com.ued1.punaisenpaholaisenmetsastys.hahmot.Pelaaja;
import com.ued1.punaisenpaholaisenmetsastys.logiikka.Asepaja;
import com.ued1.punaisenpaholaisenmetsastys.logiikka.HaarniskaKauppa;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TarinaOsa extends JPanel {
        
    private Pelaaja pelaaja;
    private Asepaja asepaja;
    private HaarniskaKauppa haarniskaKauppa;
    private JTextArea eka;
    private JTextArea toka;
    
    public TarinaOsa(Pelaaja pelaaja) {
        super(new GridLayout(2,1));
        this.pelaaja = pelaaja;
        this.asepaja = new Asepaja();
        this.haarniskaKauppa = new HaarniskaKauppa();
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        eka = new JTextArea();
        toka = new JTextArea();
        paivita();
        eka.setFocusable(false);
        toka.setFocusable(false);
        add(eka);
        add(toka);
    }
    
    
    
    public void paivita() {
        if(pelaaja.getPaikka() == Paikka.KYLA) {
            asetaKyla();
        } else if(pelaaja.getPaikka() == Paikka.ASEPAJA) {
            asetaAsepaja();
        } else if(pelaaja.getPaikka() == Paikka.HAARNISKAKAUPPA) {
            asetaHaarniskaKauppa();
        } else if(pelaaja.getPaikka() == Paikka.METSA) {
            asetaMetsa();
        } else if(pelaaja.getPaikka() == Paikka.ASEENOSTO) {
            asetaAseenOsto();
        } else if(pelaaja.getPaikka() == Paikka.ASEENMYYNTI) {
            asetaAseenMyynti();
        }
        
        
    }
    
    private void asetaKyla() {
        eka.setText("KYLÄ");
        toka.setText("kylä");
    }
    
    private void asetaAsepaja() {
        eka.setText("ASEPAJA");
        toka.setText(asepaja.hinnastoMerkkijonona());
    }
    
    private void asetaHaarniskaKauppa() {
        eka.setText("HAARNISKAKAUPPA");
        toka.setText(haarniskaKauppa.hinnastoMerkkijonona());
    }
    
    private void asetaMetsa() {
        eka.setText("METSÄ");
        toka.setText("metsä");
    }
    
    private void asetaAseenOsto() {
        eka.setText("ASEENOSTO");
        toka.setText("ostettavat aseet...");
    }
    
    private void asetaAseenMyynti() {
        eka.setText("ASEENMYYNTI");
        if(asepaja.voikoMyydaAseen(pelaaja)) {
            toka.setText("ohje aseen myyntiin");
        } else {
            toka.setText("Et voi myydä omaa nyrkkiäsi");
        }
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
        
}
