package com.mycompany.unicafe;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author elmo
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void kassassaRahaaPalauttaaSaldon() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaitaMyytyPalauttaaSaldon() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisiaMyytyPalauttaaSaldon() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void voiSyodaEdullisesti() {
        assertEquals(60, kassa.syoEdullisesti(300));
    }
    
    @Test
    public void voiSyodaMaukkaasti() {
        assertEquals(100, kassa.syoMaukkaasti(500));
    }
    
    @Test
    public void eiVoiSyodaEdullisestiIlmanRahaa() {
        assertEquals(200, kassa.syoEdullisesti(200));
    }
    
    @Test
    public void eiVoiSyodaMaukkaastiIlmanRahaa() {
        assertEquals(300, kassa.syoMaukkaasti(300));
    }
    
    @Test
    public void syoEdullisestiKasvattaaSaldoa() {
        kassa.syoEdullisesti(300);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKasvattaaSaldoa() {
        kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiKasvattaaMyytyja() {
        kassa.syoEdullisesti(300);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKasvattaaMyytyja() {
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());    
    }
    
    @Test
    public void edullisetEiRahaaEiMuutaKassaa() {
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaatEiRahaaEiMuutaKassaa() {
        kassa.syoMaukkaasti(300);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisetEiRahaaEiMuutaMyytyja() {
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaatEiRahaaEiMuutaMyytyja() {
        kassa.syoMaukkaasti(300);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void voiSyodaEdullisestiKortilla() {
        assertTrue(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void voiSyodaMaukkaastiKortilla() {
        assertTrue(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void edullisetKortiltaOtetaanRahaa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void maukkaatKortiltaOtetaanRahaa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void edullisetKortillaKasvattaaMyytyja() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaatKortillaKasvattaaMyytyja() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisetKorttiKassaEiMuutu() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaatKorttiKassaEiMuutu() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisetKorttiEiRahaaFalse() {
        kortti.otaRahaa(1000);
        assertFalse(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void maukkaatKorttiEiRahaaFalse() {
        kortti.otaRahaa(1000);
        assertFalse(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void edullisetKorttiEiRahaaKorttiaEiVeloiteta() {
        kortti.otaRahaa(800);
        kassa.syoEdullisesti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void maukkaatKorttiEiRahaaKorttiaEiVeloiteta() {
        kortti.otaRahaa(700);
        kassa.syoMaukkaasti(kortti);
        assertEquals(300, kortti.saldo());
    }
    
    @Test
    public void edullisetKorttiEiRahaaMyydytEiMuutu() {
        kortti.otaRahaa(800);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaatKorttiEiRahaaMyydytEiMuutu() {
        kortti.otaRahaa(700);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(1500, kortti.saldo());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivista() {
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void kortinLataaminenKasvattaaKassaa() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassa.kassassaRahaa());
    }


}
