/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babysitter;

import eccezioni.BabysitterOccupata;
import eccezioni.DateException;
import eccezioni.ImpossibileTerminareIntervento;
import eccezioni.InterventoGiaTerminato;
import eccezioni.MaxInterventiRaggiunto;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Pc
 */
public class AziendaTest
{
    Azienda a;
    Intervento t1,t2;
    Babysitter b1,b2;
    LocalDateTime inizio1,fine1,inizio2,fine2;
    
    @Before
    public void Inizializzazione() throws DateException
    {
        a=new Azienda();
        b1=new Babysitter("Alice","De Bernardin");
        b2=new Babysitter("Nicolè","Inversini");
        inizio1=LocalDateTime.of(2021,1,1,1,1);
        fine1=LocalDateTime.of(2021,1,2,1,1);
        inizio2=LocalDateTime.of(2021,1,3,1,1);
        fine2=LocalDateTime.of(2021,1,4,1,1);
        t1=new Intervento("Claudio","Pasinelli","asdf",1,b1,inizio1,fine1);
        t2=new Intervento("Carlo","Carlino","asdfmovie",2,b2,inizio2,fine2);
    }
    @Test
    public void testEquals() throws MaxInterventiRaggiunto, BabysitterOccupata, ImpossibileTerminareIntervento, InterventoGiaTerminato
    {
        a.aggiungiIntervento(t1);
        a.aggiungiIntervento(t2);
        assertFalse("Azienda con due interventi non terminati", t1.equals(t2));
        
        a.modificaStatusIntervento(1);
        assertFalse("Azienda con un intervento terminato ed uno no", t1.equals(t2));
        
        a.modificaStatusIntervento(2);
        assertFalse("Azienda con due interventi terminati", t1.equals(t2));
        
        t2=new Intervento(t1);
        assertTrue("Azienda con due interventi uguali", t1.equals(t2));
    }
}
