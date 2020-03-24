/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J
 */
public class KassapaateTest {
    
    Kassapaate kp;
    
    @Before
    public void setUp() {
        kp = new Kassapaate();
    }
    
    // luodun kassapäätteen rahamäärä ja myytyjen lounaiden määrä on oikea (rahaa 1000, lounaita myyty 0)
    @Test
    public void luodunKassapaatteenRahamaaraOnOikea() {
        assertEquals(100000, kp.kassassaRahaa());
    }
    
    @Test
    public void luodunKassapaatteenMyytyjenEdullistenLounaidenMaaraOnOikea() {
        assertEquals(0, kp.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void luodunKassapaatteenMyytyjenMaukkaidenLounaidenMaaraOnOikea() {
        assertEquals(0, kp.maukkaitaLounaitaMyyty());
    }
    
    // käteisosto toimii sekä edullisten että maukkaiden lounaiden osalta
        // jos maksu riittävä: kassassa oleva rahamäärä kasvaa lounaan hinnalla ja vaihtorahan suuruus on oikea
    @Test
    public void edullinenKateisostoToimiiEliJosMaksuRiittavaVaihtorahanSuuruusOnOikea() {
        assertEquals(60, kp.syoEdullisesti(300));
    }
    
    @Test
    public void maukasKateisostoToimiiEliJosMaksuRiittavaVaihtorahanSuuruusOnOikea() {
        assertEquals(100, kp.syoMaukkaasti(500));
    }
    
    @Test
    public void edullinenKateisostoToimiiEliJosMaksuRiittavaKassaKasvaaLounaanHinnalla() {
        kp.syoEdullisesti(300);
        assertEquals(100240, kp.kassassaRahaa());
    }
    
    @Test
    public void MaukasKateisostoToimiiEliJosMaksuRiittavaKassaKasvaaLounaanHinnalla() {
        kp.syoMaukkaasti(500);
        assertEquals(100400, kp.kassassaRahaa());
    }
    
        // jos maksu on riittävä: myytyjen lounaiden määrä kasvaa
    @Test
    public void edullinenKateisostoToimiiEliJosRiittavaMaksuNiinMyytyjenLounaidenMaaraKasvaa() {
        kp.syoEdullisesti(300);
        assertEquals(1, kp.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void MaukasKateisostoToimiiEliJosRiittavaMaksuNiinMyytyjenLounaidenMaaraKasvaa() {
        kp.syoMaukkaasti(500);
        assertEquals(1, kp.maukkaitaLounaitaMyyty());
    }
    
        //jos maksu ei ole riittävä: kassassa oleva rahamäärä ei muutu, 
        // kaikki rahat palautetaan vaihtorahana ja myytyjen lounaiden määrässä ei muutosta
    @Test
    public void edullinenKateisostoToimiiEliJosMaksuEiOleRiittavaNiinKassaEiMuutu() {
        kp.syoEdullisesti(200);
        assertEquals(100000, kp.kassassaRahaa());
    }
    
    @Test
    public void MaukasKateisostoToimiiEliJosMaksuEiOleRiittavaNiinKassaEiMuutu() {
        kp.syoMaukkaasti(200);
        assertEquals(100000, kp.kassassaRahaa());
    }
    
    @Test
    public void edullinenKateisostoToimiiEliJosMaksuEiOleRiittavaNiinRahatPalautetaan () {
        assertEquals(200, kp.syoEdullisesti(200));
    }
    
    @Test
    public void MaukasKateisostoToimiiEliJosMaksuEiOleRiittavaNiinRahatPalautetaan () {
        assertEquals(200, kp.syoMaukkaasti(200));
    }
    
    @Test
    public void edullinenKateisostoToimiiEliJosMaksuEiOleRiittavaNiinMyytyjenMaaraEiMuutu () {
        kp.syoEdullisesti(200);
        assertEquals(0, kp.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void MaukasKateisostoToimiiEliJosMaksuEiOleRiittavaNiinMyytyjenMaaraEiMuutu () {
        kp.syoMaukkaasti(200);
        assertEquals(0, kp.maukkaitaLounaitaMyyty());
    }
    
    // korttiosto toimii sekä edullisten että maukkaiden lounaiden osalta 
        // jos kortilla on tarpeeksi rahaa, veloitetaan summa kortilta ja palautetaan true
    @Test
    public void edullinenKorttiostoToimiiEliJosKortillaOnTarpeeksiRahaaNiinPalautetaanTrue() {
        Maksukortti kortti = new Maksukortti(10000);
        assertEquals(true, kp.syoEdullisesti(kortti));
    }
    
    @Test
    public void MaukasKorttiostoToimiiEliJosKortillaOnTarpeeksiRahaaNiinPalautetaanTrue() {
        Maksukortti kortti = new Maksukortti(10000);
        assertEquals(true, kp.syoMaukkaasti(kortti));
    }
    
        // jos kortilla on tarpeeksi rahaa, myytyjen lounaiden määrä kasvaa
    @Test
    public void edullinenKorttiostoToimiiEliJosKortillaOnTarpeeksiRahaaNiinLounaidenMaaraKasvaa() {
        Maksukortti kortti = new Maksukortti(10000);
        kp.syoEdullisesti(kortti);
        assertEquals(1, kp.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void MaukasKorttiostoToimiiEliJosKortillaOnTarpeeksiRahaaNiinLounaidenMaaraKasvaa() {
        Maksukortti kortti = new Maksukortti(10000);
        kp.syoMaukkaasti(kortti);
        assertEquals(1, kp.maukkaitaLounaitaMyyty());
    }
    
        // jos kortilla ei ole tarpeeksi rahaa, kortin rahamäärä ei muutu, 
        // myytyjen lounaiden määrä muuttumaton ja palautetaan false
    @Test
    public void EdullinenKorttiostoToimiiEliJosKortillaEiOleTarpeeksiRahaaNiinLounaidenMaaraEiKasva() {
        Maksukortti kortti = new Maksukortti(10);
        kp.syoEdullisesti(kortti);
        assertEquals(0, kp.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void MaukasKorttiostoToimiiEliJosKortillaEiOleTarpeeksiRahaaNiinLounaidenMaaraEiKasva() {
        Maksukortti kortti = new Maksukortti(10);
        kp.syoMaukkaasti(kortti);
        assertEquals(0, kp.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void EdullinenKorttiostoToimiiEliJosKortillaEiOleTarpeeksiRahaaNiinLounaidenPalautetaanFalse() {
        Maksukortti kortti = new Maksukortti(10);
        assertEquals(false, kp.syoEdullisesti(kortti));
    }
    
    @Test
    public void MaukasKorttiostoToimiiEliJosKortillaEiOleTarpeeksiRahaaNiinLounaidenPalautetaanFalse() {
        Maksukortti kortti = new Maksukortti(10);
        assertEquals(false, kp.syoMaukkaasti(kortti));
    }
    
        // kassassa oleva rahamäärä ei muutu kortilla ostettaessa
    @Test
    public void EdullinenKorttiostoToimiiElikassaEiMuutuKortillaOstettaessa() {
        Maksukortti kortti = new Maksukortti(10000);
        kp.syoEdullisesti(kortti);
        assertEquals(100000, kp.kassassaRahaa());
    }
    
    @Test
    public void MaukasKorttiostoToimiiElikassaEiMuutuKortillaOstettaessa() {
        Maksukortti kortti = new Maksukortti(10000);
        kp.syoMaukkaasti(kortti);
        assertEquals(100000, kp.kassassaRahaa());
    }
    
    // kortille rahaa ladattaessa kortin saldo muuttuu ja kassassa oleva rahamäärä kasvaa ladatulla summalla
    @Test
    public void korttiaLadattaessaSaldoMuuttuuJaKassaKasvaaLadatullaSummalla() {
        Maksukortti kortti = new Maksukortti(0);
        kp.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, kp.kassassaRahaa());
    }
    
    @Test
    public void korttiaLadattaessaNegatiivisellaMaarallaEIKasvaKassa() {
        Maksukortti kortti = new Maksukortti(0);
        kp.lataaRahaaKortille(kortti, -2);
        assertEquals(100000, kp.kassassaRahaa());
    }
    
}
