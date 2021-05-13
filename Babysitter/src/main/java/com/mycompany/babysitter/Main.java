/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babysitter;
import eccezioni.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.*;

/**
 *
 * @author Pc
 */
public class Main
{
    static long idProssimoIntervento=1;
    static Scanner tastiera=new Scanner(System.in);
    public static void main(String[] args) throws InterventoNonTrovato
    {
        Babysitter babysitter;
        String nomeBabysitter;
        String cognomeBabysitter;
        String nomeCliente;
        String cognomeCliente;
        String indirizzoCliente;
        Intervento intervento=null;
        Azienda a=new Azienda();
        long idIntervento=0;
        int annoInizio=2021, meseInizio=1, giornoInizio=1, oraInizio=18, minutiInizio=30;
        int annoFine=2021, meseFine=1, giornoFine=2, oraFine=20, minutiFine=30;
        
        String[] elencoVociMenu= new String[7];
        elencoVociMenu[0]="Termina il programma";
        elencoVociMenu[1]="Aggiungi un intervento";
        elencoVociMenu[2]="Elimina un intervento";
        elencoVociMenu[3]="Termina un intervento";
        elencoVociMenu[4]="Visualizza tutti gli interventi dell'azienda";
        elencoVociMenu[5]="Visualizza tutti gli interventi di una determinata babysitter";
        elencoVociMenu[6]="Visualizza tutti gli interventi di una data";
        
        int sceltaUtente=-1, esitoOperazione;
        Menu menu=new Menu(elencoVociMenu);
        
        do
        {
            try
            {
                sceltaUtente=menu.sceltaMenu();
                switch(sceltaUtente)
                {
                    case 0: //esci
                    {
                        System.out.println("L'applicazione verrà terminata");
                        tastiera.nextLine();
                        break;
                    }
                    case 1: //aggiungi intervento
                    {
                        boolean dataCorretta;
                        System.out.println("Nome del cliente --> ");
                        nomeCliente=tastiera.nextLine();
                        System.out.println("Cognome del cliente --> ");
                        cognomeCliente=tastiera.nextLine();
                        System.out.println("Indirizzo del cliente --> ");
                        indirizzoCliente=tastiera.nextLine();
                        System.out.println("Nome della babysitter --> ");
                        nomeBabysitter=tastiera.nextLine();
                        System.out.println("Cognome della babysitter --> ");
                        cognomeBabysitter=tastiera.nextLine();
                        babysitter=new Babysitter(nomeBabysitter,cognomeBabysitter);
                        //data inizio
                        do
                        {
                            System.out.println("\nAnno dell'inizio dell'intervento --> ");
                            annoInizio=tastiera.nextInt();
                            System.out.println("Mese dell'inizio dell'intervento --> ");
                            meseInizio=tastiera.nextInt();
                            System.out.println("Giorno dell'inizio dell'intervento --> ");
                            giornoInizio=tastiera.nextInt();
                            System.out.println("Ora dell'inizio dell'intervento --> ");
                            oraInizio=tastiera.nextInt();
                            System.out.println("Minuti dell'inizio dell'intervento --> ");
                            minutiInizio=tastiera.nextInt();
                            dataCorretta=isDataValida(giornoInizio,meseInizio,annoInizio,oraInizio,minutiInizio);
                            if(dataCorretta)
                                break;
                            else
                                System.out.println("\nData d'inizio intervento non valida. Reinserire la data. "+giornoInizio+"/"+meseInizio+"/"+annoInizio+" - "+oraInizio+":"+minutiInizio+"\n");
                        }while(!dataCorretta);
                        dataCorretta=false;
                        //data fine
                        do
                        {
                            System.out.println("\nAnno della fine dell'intervento --> ");
                            annoFine=tastiera.nextInt();
                            System.out.println("Mese della fine dell'intervento --> ");
                            meseFine=tastiera.nextInt();
                            System.out.println("Giorno della fine dell'intervento --> ");
                            giornoFine=tastiera.nextInt();
                            System.out.println("Ora della fine dell'intervento --> ");
                            oraFine=tastiera.nextInt();
                            System.out.println("Minuti della fine dell'intervento --> ");
                            minutiFine=tastiera.nextInt();
                            dataCorretta=isDataValida(giornoFine,meseFine,annoFine,oraFine,minutiFine);
                            if(dataCorretta)
                                break;
                            else
                                System.out.println("\nData di fine intervento non valida. Reinserire la data. "+giornoFine+"/"+meseFine+"/"+annoFine+" - "+oraFine+":"+minutiFine+"\n");
                        }while(!dataCorretta);
                        try
                        {
                            intervento=new Intervento(nomeCliente,cognomeCliente,indirizzoCliente,idProssimoIntervento,babysitter,annoInizio,meseInizio,giornoInizio,oraInizio,minutiInizio,annoFine, meseFine, giornoFine, oraFine, minutiFine);
                            a.aggiungiIntervento(intervento);
                            System.out.println("\nL'intervento è stato compilato e datato correttamente\n");
                            System.out.println(a.getIntervento(idProssimoIntervento));
                            idProssimoIntervento++;
                            System.out.println("Premi un pulsante per continuare.");
                            tastiera.nextLine();
                            tastiera.nextLine();
                        }
                        catch (DateException ex)
                        {
                            System.out.println(ex.toString());
                        }
                        catch (DateTimeException ex)
                        {
                            System.out.println("Hai inserito una data errata.");
                        }
                        break;
                    }
                    case 2: //elimina intervento
                    {
                        System.out.println("Inserisci il codice dell'intervento da eliminare --> ");
                        idIntervento=tastiera.nextInt();
                        try
                        {
                            a.eliminaIntervento(idIntervento);
                            System.out.println("L'intervento: "+idIntervento+" è stato eliminato.");
                        }
                        catch(InterventoNonTrovato e1)
                        {
                            System.out.println(e1.toString());
                        }
                        catch(InterventiNonTrovati e2)
                        {
                            System.out.println(e2.toString());
                        }
                        System.out.println("Premi un pulsante per continuare.");
                        tastiera.nextLine();
                        tastiera.nextLine();
                        break;
                    }
                    case 3: //termina intervento (deve essere ancora attivo)
                    {
                        System.out.println("Inserisci il codice dell'intervento da terminare --> ");
                        idIntervento=tastiera.nextInt();
                        esitoOperazione=a.modificaStatusIntervento(idIntervento);
                        if(esitoOperazione==-1)
                        {
                            System.out.println("Non è stato possibile terminare l'intervento numero: "+idIntervento);
                            System.out.println("Premi un pulsante per continuare.");
                            tastiera.nextLine();
                            tastiera.nextLine();
                            break;
                        }
                        else if(esitoOperazione==-2)
                        {
                            System.out.println("L'intervento: "+idIntervento+" è già stato terminato.");
                            System.out.println("Premi un pulsante per continuare.");
                            tastiera.nextLine();
                            tastiera.nextLine();
                            break;
                        }
                        else
                        {
                            System.out.println("L'intervento: "+idIntervento+" è stato terminato.");
                            System.out.println(a.getIntervento(idIntervento));
                            System.out.println("Premi un pulsante per continuare.");
                            tastiera.nextLine();
                            tastiera.nextLine();
                            break;
                        }
                    }
                    case 4: //elenco di tutti gli interventi dell'azienda
                    {
                        System.out.println(a.elencoInterventi());
                        break;
                    }
                    case 5: //elenco di tutti gli interventi di una determinata babysitter (da rivedere)
                    {
                        System.out.println("Inserisci il nome della babysitter da cercare --> ");
                        nomeBabysitter=tastiera.nextLine();
                        System.out.println("Inserisci il cognome della babysitter da cercare --> ");
                        cognomeBabysitter=tastiera.nextLine();
                        String[] elencoRicerca=a.elencoInterventiBabysitter(nomeBabysitter, cognomeBabysitter);
                        if(elencoRicerca==null)
                            System.out.println("\nLa babysitter: \""+nomeBabysitter+" "+cognomeBabysitter+"\" non è stata trovata");
                        else
                        {
                            for(int i=0; i<elencoRicerca.length;i++)
                                System.out.println(elencoRicerca[i].toString());
                        }
                        System.out.println("Premi un pulsante per continuare.");
                        tastiera.nextLine();
                        break;
                    }
                    case 6: //elenco di tutti gli interventi di una determinata data
                    {
                        boolean dataCorretta;
                        int contatoreTentativi=2;
                        do
                        {
                            System.out.println("Anno dell'intervento --> ");
                            annoInizio=tastiera.nextInt();
                            System.out.println("Mese dell'intervento --> ");
                            meseInizio=tastiera.nextInt();
                            System.out.println("Giorno dell'intervento --> ");
                            giornoInizio=tastiera.nextInt();
                            dataCorretta=isDataValida(giornoInizio,meseInizio,annoInizio,oraInizio,minutiInizio);
                            if(dataCorretta)
                                break;
                            else
                            {
                                if(contatoreTentativi!=0)
                                    System.out.println("\nLa data da te inserita per la ricerca degli intervent: \""+giornoInizio+"/"+meseInizio+"/"+annoInizio+"\" non è corretta. Reinserirla.");
                                if(contatoreTentativi==1)
                                    System.out.println("Hai ancora "+contatoreTentativi+" tentativo.\n");
                                else if(contatoreTentativi>1)
                                    System.out.println("Hai ancora "+contatoreTentativi+" tentativi.\n");
                                else if(contatoreTentativi==0)
                                    break;
                                contatoreTentativi--;
                            }
                        }while(!dataCorretta && contatoreTentativi!=-1);
                        try
                        {
                            String[] elencoRicerca=a.elencoInterventiData(annoInizio, meseInizio,giornoInizio);
                            if(elencoRicerca==null)
                                System.out.println("\nNon ci sono interventi in data: \""+giornoInizio+"/"+meseInizio+"/"+annoInizio+"\"");
                            else
                            {
                                for(int i=0; i<elencoRicerca.length;i++)
                                    System.out.println(elencoRicerca[i].toString());
                            }
                            System.out.println("Premi un pulsante per continuare.");
                            tastiera.nextLine();
                            tastiera.nextLine();
                        }
                        catch (DateTimeException ex)
                        {
                            System.out.println("Hai inserito in modo errato la data.");
                        }
                        break;
                    }
                }
            }
            catch(InputMismatchException | NumberFormatException e1)
            {
                tastiera.nextLine();
                System.out.println("L'inserimento non è corretto.");
            }
        }while (sceltaUtente!=0);
    }
    private static boolean isDataValida(int giorno, int mese, int anno, int ora, int minuti)
    {
        if(giorno<0 || giorno>31)
            return false;
        if(mese<0 || mese>12)
            return false;
        if(anno<0 || anno>9999)
            return false;
        if(ora<0 || ora >23)
            return false;
        if(minuti<0 || minuti>59)
            return false;
        else
            return true;
    }
}
