package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);   
}

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));         

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   
    }

    @Test
    public void kahdenEriTuotteenOstaminen() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 1));          

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("olli", "6789");

        verify(pankki).tilisiirto(eq("olli"), eq(42), eq("6789"), eq("33333-44455"), eq(6));   
    }

    @Test
    public void samaaTuotettaUseampiKappale() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));             

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(10));   
    }

    @Test
    public void kahdenEriTuotteenOstaminenKunToinenLoppuVarastosta() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 1));          

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("olli", "6789");

        verify(pankki).tilisiirto(eq("olli"), eq(42), eq("6789"), eq("33333-44455"), eq(5));   
    }
    
    @Test
    public void aloitaAsiointiTyhjentaaOstoskorin() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));          

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.aloitaAsiointi();

        k.tilimaksu("olli", "6789");

        verify(pankki, times(0)).tilisiirto(eq("olli"), eq(42), eq("6789"), eq("33333-44455"), eq(5));   
    }

    @Test
    public void maksuunLuodaanUusiViite() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));          

        k.aloitaAsiointi();
        k.lisaaKoriin(1);

        k.tilimaksu("olli", "6789");
        k.tilimaksu("juhani", "1234");

        verify(viite, times(2)).uusi();   
    }

    @Test
    public void ostoskoristaPoistaminenPalauttaaTuotteenVarastoon() {
        Tuote tuote = new Tuote(1, "maito", 5);
        when(varasto.haeTuote(1)).thenReturn(tuote);          

        k.poistaKorista(1);

        verify(varasto).haeTuote(eq(1));
        verify(varasto).palautaVarastoon(eq(tuote));   
    }
}