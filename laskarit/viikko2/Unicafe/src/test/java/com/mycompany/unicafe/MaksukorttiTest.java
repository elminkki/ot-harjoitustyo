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
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void kortinLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.10", kortti.toString());
    }
    
    @Test
    public void kortiltaVoiOttaaRahaa() {
        assertTrue(kortti.otaRahaa(5));
    }
    
    @Test
    public void saldoEiMeneNegatiiviseksi() {
        assertFalse(kortti.otaRahaa(15));
    }
    
    @Test
    public void saldonHakeminenToimii() {
        assertEquals(10, kortti.saldo());
    }
}
