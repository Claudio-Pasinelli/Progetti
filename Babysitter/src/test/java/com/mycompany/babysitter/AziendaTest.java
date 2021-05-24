/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babysitter;

import eccezioni.*;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



/**
 *
 * @author Claudio Pasinelli
 */
public class AziendaTest
{
    Azienda a,a2;
    Intervento t1,t2,t3;
    Babysitter b1,b2;
    LocalDateTime inizio1,fine1,inizio2,fine2,inizio3,fine3;
    String nomeFileTesto;
    String nomeFileBinario;
    
    @Before
    public void Inizializzazione() throws DateException
    {
        a=new Azienda();
        b1=new Babysitter("Alice","De Bernardin");
        b2=new Babysitter("Nicolè","Inversini");
        inizio1=LocalDateTime.of(2021,1,1,1,1); //2021-1-1
        fine1=LocalDateTime.of(2021,1,2,1,1);   //2021-1-2
        inizio2=LocalDateTime.of(2021,1,3,1,1); //2021-1-3
        fine2=LocalDateTime.of(2021,1,4,1,1);   //2021-1-4
        inizio3=LocalDateTime.of(2021,1,3,1,1); //2021-1-5
        fine3=LocalDateTime.of(2021,1,4,1,1);   //2021-1-6
        t1=new Intervento("Claudio","Pasinelli","asdf",1,b1,inizio1,fine1);  //2021-1-1 / 2021-1-2
        t2=new Intervento("Carlo","Carlino","asdfmovie",2,b2,inizio2,fine2); //2021-1-3 / 2021-1-4
        t3=new Intervento("Dina","Lampa","asdfmovie3",3,b1,inizio3,fine3); //2021-1-5 / 2021-1-6
        nomeFileTesto="InterventiAzienda.txt";
        nomeFileBinario="Azienda.bin";
    }
    /**
     * Test of getNumMaxInterventi method, of class Azienda.
     */
    @Test
    public void testGetNumMaxInterventi()
    {
        int attuale, atteso;
        attuale=1000;
        atteso=a.getNumMaxInterventi();
        assertEquals("Il numero di interventi massimi che l'azienda può archiviare è 1000",attuale,atteso);
    }
    

    /**
     * Test of getNumInterventiPresenti method, of class Azienda.
     */
    @Test
    public void testGetNumInterventiPresenti() throws MaxInterventiRaggiunto, BabysitterOccupata
    {
        int nInterventiPresentiAttesi,nInterventiPresentiAzienda;
        nInterventiPresentiAttesi=0;
        nInterventiPresentiAzienda=a.getNumInterventiPresenti();
        assertEquals("L'azienda non ha interventi presenti",nInterventiPresentiAttesi,nInterventiPresentiAzienda);
        
        nInterventiPresentiAttesi=1;
        a.aggiungiIntervento(t1);
        nInterventiPresentiAzienda=a.getNumInterventiPresenti();
        assertEquals("L'azienda ha un singolo intervento",nInterventiPresentiAttesi,nInterventiPresentiAzienda);
    }

    /**
     * Test of getIntervento method, of class Azienda.
     */
    @Test
    public void testGetIntervento_long() throws MaxInterventiRaggiunto, BabysitterOccupata
    {
        Intervento interventoAtteso=new Intervento(t1),interventoAttuale;
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        interventoAttuale=new Intervento(a.getIntervento(0));
        assertEquals("L'azienda ha 2 interventi ma viene trovato il primo tramite l'id",interventoAtteso,interventoAttuale);
        
        interventoAtteso=new Intervento();
        interventoAttuale=new Intervento(a.getIntervento(3));
        assertEquals("L'azienda ha 2 interventi ma si cerca l'intervento con id 3",interventoAtteso,interventoAttuale);
    }

    /**
     * Test of getIntervento method, of class Azienda.
     */
    @Test
    public void testGetIntervento_int() throws MaxInterventiRaggiunto, BabysitterOccupata 
    {
        Intervento[] atteso=new Intervento[1000],attuale=new Intervento[1000];
        
        a.aggiungiIntervento(t1);
        atteso[0]=t1;
        attuale[0]=a.getIntervento(0);
        Assert.assertArrayEquals("L'azienda contiene un intervento", atteso, attuale);
    }
    @Test(expected=NullPointerException.class)
    public void testGetInterventoSbagliato_int() 
    {
        Intervento[] atteso=new Intervento[1000],attuale=new Intervento[1000];
        
        atteso=a.getElencoInterventi();
        attuale[0]=a.getIntervento(1);
        for(int i=0; i<1000;i++)
        {
            assertEquals("L'azienda non contiene interventi", atteso[i], attuale[i]);
        }
    }

    /**
     * Test of getIdUltimoIntervento method, of class Azienda.
     */
    @Test
    public void testGetIdUltimoIntervento() throws MaxInterventiRaggiunto, BabysitterOccupata
    {
        long idAtteso,idAttuale;
        idAttuale=a.getIdUltimoIntervento();
        idAtteso=1;
        assertEquals("Ricerca dell'ultimo id (1)",idAtteso,idAttuale);
        
        a.aggiungiIntervento(t1);
        idAttuale=a.getIdUltimoIntervento();
        idAtteso=1;
        assertEquals("Ricerca dell'ultimo id (1, perché c'è un intervento)",idAtteso,idAttuale);
        
        a.aggiungiIntervento(t2);
        idAttuale=a.getIdUltimoIntervento();
        idAtteso=2;
        assertEquals("Ricerca dell'ultimo id (2, perché ci sono 2 interventi)",idAtteso,idAttuale);
    }

    /**
     * Test of aggiungiIntervento method, of class Azienda.
     */
    @Test
    public void testAggiungiIntervento() throws Exception
    {
        int nInterventiPresentiAttesi=2,nInterventiPresentiAzienda;
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        nInterventiPresentiAzienda=a.getNumInterventiPresenti();
        assertEquals("L'azienda non ha interventi e gliene aggiungo 2",nInterventiPresentiAttesi,nInterventiPresentiAzienda);
    }
    @Test(expected=BabysitterOccupata.class)
    public void testAggiungiInterventoBabysitterOccupata() throws MaxInterventiRaggiunto, BabysitterOccupata
    {
        int nInterventiPresentiAttesi=1,nInterventiPresentiAzienda;
        a.aggiungiIntervento(t1);
        t2=new Intervento(t1);
        a.aggiungiIntervento(t2);
        nInterventiPresentiAzienda=a.getNumInterventiPresenti();
        assertEquals("L'azienda ha un intervento e cerco di inserirne uno uguale ad uno preesistente",nInterventiPresentiAttesi,nInterventiPresentiAzienda);
    }
    /*@Test(expected=MaxInterventiRaggiunto.class) 
    public void testAggiungiInterventoMaxInterventiRaggiunto() throws Exception
    {
        int nInterventiPresentiAttesi=1000,nInterventiPresentiAzienda;
        
        for(int i=0; i<1000; i++)
            a.aggiungiIntervento(new Intervento());
        a.aggiungiIntervento(t1);
        nInterventiPresentiAzienda=a.getNumInterventiPresenti();
        assertEquals("L'azienda non ha interventi e gliene aggiungo 2",nInterventiPresentiAttesi,nInterventiPresentiAzienda);
    }*/
    /**
     * Test of eliminaIntervento method, of class Azienda.
     */
    @Test
    public void testEliminaIntervento() throws Exception
    {
        int nInterventiPresentiAttesi=1,nInterventiPresentiAzienda;
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        
        a.eliminaIntervento(1);
        nInterventiPresentiAzienda=a.getNumInterventiPresenti();
        assertEquals("L'azienda ha 2 interventi e gliene tolgo 1",nInterventiPresentiAttesi,nInterventiPresentiAzienda);
        
        a.eliminaIntervento(2);
        nInterventiPresentiAttesi=0;
        nInterventiPresentiAzienda=a.getNumInterventiPresenti();
        assertEquals("L'azienda ha 1 interventi e gliene tolgo 1",nInterventiPresentiAttesi,nInterventiPresentiAzienda);
    }
    @Test (expected = InterventoNonTrovato.class)
    public void testEliminaInterventoNonTrovato() throws Exception
    {
        int nInterventiPresentiAttesi=1,nInterventiPresentiAzienda;
        a.aggiungiIntervento(t1);
        a.eliminaIntervento(3);
        nInterventiPresentiAzienda=a.getNumInterventiPresenti();
        assertEquals("L'azienda ha un intervento ma ne elimino uno tramite un id inesistente",nInterventiPresentiAttesi,nInterventiPresentiAzienda);
    }
        @Test (expected = InterventiNonTrovati.class)
    public void testEliminaInterventoNoInterventi() throws Exception
    {
        int nInterventiPresentiAttesi=0,nInterventiPresentiAzienda;
        a.eliminaIntervento(1);
        nInterventiPresentiAzienda=a.getNumInterventiPresenti();
        assertEquals("L'azienda non ha interventi e provo ad eliminarne uno",nInterventiPresentiAttesi,nInterventiPresentiAzienda);
    }

    /**
     * Test of eliminaInterventoDataAntecedente method, of class Azienda.
     */
    @Test
    public void testEliminaInterventoDataAntecedente() throws Exception
    {
        LocalDate data=LocalDate.of(2021,1,2);
        int atteso=0,attuale;
        
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        attuale=a.eliminaInterventoDataAntecedente(data);
        assertEquals("Vado ad eliminare gli interventi antecedenti alla data 2021-1-2 (t1)",atteso, attuale);
    }
    @Test(expected=InterventiNonTrovati.class)
    public void testEliminaInterventoDataAntecedenteVuota() throws Exception
    {
        LocalDate data=LocalDate.of(2021,1,1);
        int atteso=0,attuale;
        
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        attuale=a.eliminaInterventoDataAntecedente(data);
        assertEquals("Vado ad eliminare gli interventi antecedenti alla data 2021-1-1 (non ci sono)",atteso, attuale);
    }

    /**
     * Test of eliminaInterventoDataUguale method, of class Azienda.
     */
    @Test
    public void testEliminaInterventoDataUguale() throws Exception
    {
        LocalDate data=LocalDate.of(2021,1,1);
        int atteso=0,attuale;
        
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        attuale=a.eliminaInterventoDataUguale(data);
        assertEquals("Vado ad eliminare gli interventi in data 2021-1-1 (t1)",atteso, attuale);
    }
    @Test(expected=InterventiNonTrovati.class)
    public void testEliminaInterventoDataUgualeSbagliata() throws Exception
    {
        LocalDate data=LocalDate.of(2021,1,2);
        int atteso = 0,attuale;
        
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        attuale=a.eliminaInterventoDataUguale(data);
        assertEquals("Vado ad eliminare gli interventi in data 2021-1-2 (non ci sono)",atteso, attuale);
    }

    /**
     * Test of modificaStatusIntervento method, of class Azienda.
     */
    @Test
    public void testModificaStatusIntervento() throws Exception
    {
        boolean terminato;
        a.aggiungiIntervento(t1);
        a.modificaStatusIntervento(a.getIntervento(0).getIdIntervento());
        terminato=a.getIntervento(0).isTerminato();
        assertEquals("L'azienda ha 1 intervento terminato",terminato,true);
    }
    @Test(expected = ImpossibileTerminareIntervento.class)
    public void testModificaStatusInterventoInesistente() throws Exception
    {
        boolean atteso=false, attuale;

        a.modificaStatusIntervento(3);
        attuale=a.getIntervento(3).isTerminato();
        assertEquals("Cerco di terminare l'intervento con id 3 (ma il massimo è 2)",atteso, attuale);
    }
    @Test(expected = InterventoGiaTerminato.class)
    public void testModificaStatusInterventoTerminato() throws Exception
    {
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        a.modificaStatusIntervento(1);
        a.modificaStatusIntervento(1);
    }

    /**
     * Test of terminaInterventoDataAntecedente method, of class Azienda.
     */
    @Test
    public void testTerminaInterventoDataAntecedente() throws Exception
    {
        LocalDate data=LocalDate.of(2021,1,2);
        int atteso=0,attuale;
        
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        attuale=a.terminaInterventoDataAntecedente(data);
        assertEquals("Vado a terminare gli interventi in data 2021-1-1 (t1)",atteso, attuale);
    }
    @Test(expected=ImpossibileTerminareIntervento.class)
    public void testTerminaInterventoDataAntecedenteSbagliata() throws Exception
    {
        LocalDate data=LocalDate.of(2021,1,1);
        int atteso=0,attuale;
        
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        attuale=a.terminaInterventoDataAntecedente(data);
        assertEquals("Vado a terminare gli interventi in data 2021-1-1 (non ci sono)",atteso, attuale);
    }

    /**
     * Test of terminaInterventoDataUguale method, of class Azienda.
     */
    @Test
    public void testTerminaInterventoDataUguale() throws Exception
    {
        LocalDate data;
        
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        data=a.getIntervento(1).getInizioCorto();
        a.terminaInterventoDataUguale(data);
        assertFalse("L'azienda ha 2 interventi",t1.equals(t2));
    }
    @Test(expected = ImpossibileTerminareIntervento.class)
    public void testTerminaInterventoDataUgualeSbagliata() throws InterventiNonTrovati, ImpossibileTerminareIntervento, MaxInterventiRaggiunto, BabysitterOccupata
    {
        LocalDate data;
        a.aggiungiIntervento(t1);
        data=LocalDate.of(2021,5,19);
        a.terminaInterventoDataUguale(data);
        assertFalse("L'azienda ha 1 intervento",t1.equals(t2));
    }

    /**
     * Test of elencoInterventiCronologiciBabysitter method, of class Azienda.
     */
    @Test
    public void testElencoInterventiCronologiciBabysitter() throws MaxInterventiRaggiunto, BabysitterOccupata
    {
        a.aggiungiIntervento(t1);
        String[] atteso=a.elencoInterventiCronologiciBabysitter(t1.getBabysitter().getNome(),t1.getBabysitter().getCognome());
        String[] attuale=a.elencoInterventiCronologiciBabysitter("Alice","De Bernardin");
        Assert.assertArrayEquals("L'azienda contiene un intervento della babysitter :\"Alice De Bernardin\"", atteso, attuale);
        
        a.aggiungiIntervento(t3);
        String[] atteso2=a.elencoInterventiCronologiciBabysitter(t1.getBabysitter().getNome(),t1.getBabysitter().getCognome());
        String[] attuale2=a.elencoInterventiCronologiciBabysitter("Alice","De Bernardin");
        Assert.assertArrayEquals("L'azienda contiene 2 interventi della babysitter :\"Alice De Bernardin\"", atteso2, attuale2);
    }

    /**
     * Test of elencoInterventiData method, of class Azienda.
     */
    @Test
    public void testElencoInterventiData() throws MaxInterventiRaggiunto, BabysitterOccupata
    {
        int anno=2021, mese=2, giorno=1;
        
        String[] atteso=a.elencoInterventiData(2021,2,1);
        String[] attuale=a.elencoInterventiData(anno, mese,giorno);
        Assert.assertArrayEquals("L'azienda non contiene interventi", atteso, attuale);
        
        a.aggiungiIntervento(t1);  //2021-1-1 / 2021-1-2
        a.aggiungiIntervento(t2);  //2021-1-3 / 2021-1-4
        mese=1;
        a.elencoInterventiData(anno, mese, giorno);
        String[] atteso2=a.elencoInterventiData(2021, 1,1);
        String[] attuale2=a.elencoInterventiData(anno, mese,giorno);
        Assert.assertArrayEquals("L'azienda contiene un intervento in data 2021-1-1", atteso2, attuale2);
    }

    /**
     * Test of elencoInterventi method, of class Azienda.
     */
    @Test
    public void testElencoInterventi() throws MaxInterventiRaggiunto, BabysitterOccupata
    {
        String atteso="",attuale="";
        
        atteso="\nNessun intervento presente nell'archivio dell'azienda\n";
        attuale=a.elencoInterventi();
        assertEquals("L'azienda non ha nessun intervento e vado ad usare elencoInterventi",atteso, attuale);
        
        a.aggiungiIntervento(t1);
        atteso=t1.toString()+"\n";
        attuale=a.elencoInterventi();
        assertEquals("L'azienda ha un intervento e vado a cercarlo con elencoInterventi",atteso, attuale);
    }

    /**
     * Test of esportaInterventiCSV method, of class Azienda.
     */
    @Test
    public void testEsportaInterventiCSV() throws Exception
    {
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        a.esportaInterventiCSV(nomeFileTesto);
    }

    /**
     * Test of salvaAzienda method, of class Azienda.
     */
    @Test
    public void testSalvaAzienda() throws Exception
    {
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        a.salvaAzienda(nomeFileBinario);
        a=a.caricaAzienda(nomeFileBinario);
        a2=new Azienda(a);
        Assert.assertArrayEquals("La copiatura degli interventi di un'altra azienda ",a2.getElencoInterventi(),a.getElencoInterventi());
    }

    /**
     * Test of caricaAzienda method, of class Azienda.
     */
    @Test
    public void testCaricaAzienda() throws Exception
    {
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        a.salvaAzienda(nomeFileBinario);
        a=a.caricaAzienda(nomeFileBinario);
        a2=new Azienda(a);
        Assert.assertArrayEquals("La copiatura degli interventi di un'altra azienda ",a2.getElencoInterventi(),a.getElencoInterventi());
    }

    /**
     * Test of setElencoInterventi method, of class Azienda.
     */
    @Test
    public void testSetElencoInterventi()
    {
        Intervento[] atteso=new Intervento[1000],attuale=new Intervento[1000];
        
        a2=new Azienda(a);
        a2.setElencoInterventi(atteso);
        a.setElencoInterventi(attuale);
        Assert.assertArrayEquals("Un archivio di un'azienda viene copiata nella tua",a2.getElencoInterventi(),a.getElencoInterventi());
    }

    /**
     * Test of setnInterventiPresenti method, of class Azienda.
     */
    @Test
    public void testSetnInterventiPresenti() throws MaxInterventiRaggiunto, BabysitterOccupata
    {
        int atteso=2, attuale;
        
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        a.setnInterventiPresenti(atteso);
        attuale=a.getNumInterventiPresenti();
        assertEquals("Il numero di interventi archiviati è 2",atteso,attuale);
    }

    /**
     * Test of getElencoInterventi method, of class Azienda.
     */
    @Test
    public void testGetElencoInterventi()
    {
        Intervento[] atteso=new Intervento[1000],attuale=new Intervento[1000];
        
        a2=new Azienda(a);
        atteso=a2.getElencoInterventi();
        attuale=a.getElencoInterventi();
        Assert.assertArrayEquals("L'azienda ha un archivio che puo' contenere 1000 interventi",atteso,attuale);
    }
    
}
