package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    // kortin saldo alussa oikein    
    @Test
    public void saldoOnAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    } 
    
    // rahan lataaminen kasvattaa saldoa oikein
    @Test
    public void lataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(90);
        assertEquals("saldo: 1.0", kortti.toString());
    }
    
    // rahan ottaminen toimii 
    @Test
    public void rahanOttaminenKortiltaToimiiEliSaldoVaheneeOikeinJosRahaaOnTarpeeksi () {
        kortti.lataaRahaa(590);
        kortti.otaRahaa(600);
        assertEquals("saldo: 0.0", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenKortiltaToimiiEliSaldoEiMuutuJosRahaaEiOleTarpeeksi () {
        kortti.otaRahaa(600);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenKortiltaToimiiEliMetodiPalauttaaTrueJosRahatRiittivätJaMuutenFalse () {
        assertEquals(true, kortti.otaRahaa(10));
    }
    
    @Test
    public void saldoOnOikea() {
        assertEquals(10, kortti.saldo());
    }
}
