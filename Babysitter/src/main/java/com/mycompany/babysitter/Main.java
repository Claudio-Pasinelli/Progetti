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
 * @author Claudio Pasinelli
 */
public class Main
{
    static Scanner tastiera=new Scanner(System.in);
    public static void main(String[] args) throws InterventoNonTrovato, InterventiNonTrovati, ImpossibileTerminareIntervento, InterventoGiaTerminato, MaxInterventiRaggiunto, BabysitterOccupata
    {
        long idProssimoIntervento=1;
        long idIntervento=0;
        int annoInizio, meseInizio, giornoInizio, oraInizio, minutiInizio;
        int annoFine, meseFine, giornoFine, oraFine, minutiFine;
        Babysitter babysitter=null;
        String nomeBabysitter="";
        String cognomeBabysitter="";
        String nomeCliente="";
        String cognomeCliente="";
        String indirizzoCliente="";
        Intervento intervento=null;
        Azienda a=new Azienda();
        LocalDateTime oggi;
        
        String nomeFileTesto="InterventiAzienda.txt";
        String nomeFileBinario="Azienda.bin";
        
        String[] elencoVociMenu= new String[13];
        elencoVociMenu[0]="Termina il programma";
        elencoVociMenu[1]="Aggiungi un intervento";
        elencoVociMenu[2]="Elimina un intervento";
        elencoVociMenu[3]="Elimina tutti gli interventi precedenti ad una data";
        elencoVociMenu[4]="Elimina tutti gli interventi di una data";
        elencoVociMenu[5]="Termina un intervento";
        elencoVociMenu[6]="Termina tutti gli interventi precedenti ad una data";
        elencoVociMenu[7]="Termina tutti gli interventi di una data";
        elencoVociMenu[8]="Visualizza tutti gli interventi dell'azienda";
        elencoVociMenu[9]="Visualizza tutti gli interventi in ordine cronologico di una determinata babysitter";
        elencoVociMenu[10]="Visualizza tutti gli interventi di una data";
        elencoVociMenu[11]="Esporta i dati in CSV";
        elencoVociMenu[12]="Salva i dati";
        
        int sceltaUtente=-1, esitoOperazione;
        Menu menu=new Menu(elencoVociMenu);
        
        try 
        {
            a=a.caricaAzienda(nomeFileBinario);
            idProssimoIntervento=a.getIdUltimoIntervento()+1; //se ci dovessero essere degli interventi già salvati si andrebbe a prendere l'id dell'ultimo intervento memorizzato per poi incrementarlo di 1
            System.out.println("Dati caricati correttamente");
        } 
        catch (IOException ex) 
        {
            System.out.println("Impossibile accedere al file in lettura. I dati non sono stati caricati");
        } 
        catch (FileException ex) 
        {
            System.out.println(ex.toString());
        }
        
        do
        {
            try
            {
                sceltaUtente=menu.sceltaMenu();
                switch(sceltaUtente)
                {
                    case 0: //esci
                    {
                        System.out.println("\nL'applicazione verrà terminata");
                        tastiera.nextLine();
                        break;
                    }
                    case 1: //aggiungi intervento (c'è il seguente controllo: la babysitter inserita è già occupata in quell'orario?)
                    {
                        boolean dataCorretta,inputCorretto=false;
                        do
                        {
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
                            inputCorretto=isInputCorretto(nomeCliente,cognomeCliente,indirizzoCliente,nomeBabysitter,cognomeBabysitter);
                            if(inputCorretto)
                                break;
                            else
                            {
                                System.out.println("\nHai inserito male delle informazioni:\n---------------------------------------------\nCliente: "+nomeCliente+ " "+cognomeCliente+"\nIndirizzo del cliente: "+indirizzoCliente+"\nBabysitter: "+nomeBabysitter+" "+cognomeBabysitter+"\n---------------------------------------------\n");
                                System.out.println("Premi un tasto per reinserire le informazioni dell'intervento.");
                                tastiera.nextLine();
                            }
                        }while(!inputCorretto);
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
                            {
                                oggi=LocalDateTime.now();
                                System.out.println("\nLa data d'inizio intervento: "+giornoInizio+"/"+meseInizio+"/"+annoInizio+" - "+oraInizio+":"+minutiInizio+" non è valida.\nOggi è il: "+oggi.getDayOfMonth()+"/"+oggi.getMonthValue()+"/"+oggi.getYear()+" - "+oggi.getHour()+":"+oggi.getMinute());
                                System.out.println("Premi un tasto per reinserire le informazioni dell'intervento.");
                                tastiera.nextLine();
                            }
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
                                System.out.println("\nData di fine intervento: "+giornoFine+"/"+meseFine+"/"+annoFine+" - "+oraFine+":"+minutiFine+" non è valida. Reinserire la data.");
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
                        catch (BabysitterOccupata e1)
                        {
                            System.out.println(e1.toString());
                        }
                        catch (MaxInterventiRaggiunto e2)
                        {
                            System.out.println(e2.toString());
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
                    case 3: //elimina tutti gli interventi precedenti ad una determianta data
                    {
                        boolean dataCorretta;
                        LocalDate dataLimite;
                        int contatoreTentativi=2;
                        do
                        {
                            System.out.println("Anno dell'intervento --> ");
                            annoInizio=tastiera.nextInt();
                            System.out.println("Mese dell'intervento --> ");
                            meseInizio=tastiera.nextInt();
                            System.out.println("Giorno dell'intervento --> ");
                            giornoInizio=tastiera.nextInt();
                            dataCorretta=isDataValida(giornoInizio,meseInizio,annoInizio);
                            if(dataCorretta)
                                break;
                            else
                            {
                                if(contatoreTentativi!=0)
                                    System.out.println("\nLa data da te inserita per la ricerca degli interventi: \""+giornoInizio+"/"+meseInizio+"/"+annoInizio+"\" non è corretta. Reinserirla.");
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
                            dataLimite=LocalDate.of(annoInizio,meseInizio,giornoInizio);
                            esitoOperazione=a.eliminaInterventoDataAntecedente(dataLimite);
                            if(esitoOperazione==0)
                                System.out.println("Tutti gli interventi precedenti alla data: \""+giornoInizio+"/"+meseInizio+"/"+annoInizio+"\" sono stati eliminati");
                        }
                        catch(InterventiNonTrovati e1)
                        {
                            System.out.println(e1.toString());
                        }
                        catch(DateTimeException e2)
                        {
                            System.out.println("\nLa data da te inserita per la ricerca degli interventi: \""+giornoInizio+"/"+meseInizio+"/"+annoInizio+"\" non è corretta. Reinserirla.");
                        }
                        System.out.println("Premi un pulsante per continuare.");
                        tastiera.nextLine();
                        tastiera.nextLine();
                        break;
                    }
                    case 4: //elimina tutti gli interventi di una determianta data
                    {
                        boolean dataCorretta;
                        LocalDate dataLimite;
                        int contatoreTentativi=2;
                        do
                        {
                            System.out.println("Anno dell'intervento --> ");
                            annoInizio=tastiera.nextInt();
                            System.out.println("Mese dell'intervento --> ");
                            meseInizio=tastiera.nextInt();
                            System.out.println("Giorno dell'intervento --> ");
                            giornoInizio=tastiera.nextInt();
                            dataCorretta=isDataValida(giornoInizio,meseInizio,annoInizio);
                            if(dataCorretta)
                                break;
                            else
                            {
                                if(contatoreTentativi!=0)
                                    System.out.println("\nLa data da te inserita per la ricerca degli interventi: \""+giornoInizio+"/"+meseInizio+"/"+annoInizio+"\" non è corretta. Reinserirla.");
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
                            dataLimite=LocalDate.of(annoInizio,meseInizio,giornoInizio);
                            esitoOperazione=a.eliminaInterventoDataUguale(dataLimite);
                            if(esitoOperazione==0)
                                System.out.println("Tutti gli interventi in data: \""+giornoInizio+"/"+meseInizio+"/"+annoInizio+"\" sono stati eliminati");
                        }
                        catch(InterventiNonTrovati e1)
                        {
                            System.out.println(e1.toString());
                        }
                        catch(DateTimeException e2)
                        {
                            System.out.println("\nLa data da te inserita per la ricerca degli interventi: \""+giornoInizio+"/"+meseInizio+"/"+annoInizio+"\" non è corretta. Reinserirla.");
                        }
                        System.out.println("Premi un pulsante per continuare.");
                        tastiera.nextLine();
                        tastiera.nextLine();
                        break;
                    }
                    case 5: //termina intervento (deve essere ancora attivo)
                    {
                        try
                        {
                            System.out.println("Inserisci il codice dell'intervento da terminare --> ");
                            idIntervento=tastiera.nextInt();
                            a.modificaStatusIntervento(idIntervento);
                            System.out.println("L'intervento: "+idIntervento+" è stato terminato.");
                            System.out.println(a.getIntervento(idIntervento));
                            System.out.println("Premi un pulsante per continuare.");
                            tastiera.nextLine();
                            tastiera.nextLine();
                            break;
                        }
                        catch(InterventoGiaTerminato e1)
                        {
                            System.out.println(e1.toString());
                            System.out.println("Premi un pulsante per continuare.");
                            tastiera.nextLine();
                            tastiera.nextLine();
                            break;
                        }
                        catch(ImpossibileTerminareIntervento e2)
                        {
                            System.out.println(e2.toString());
                            System.out.println("Premi un pulsante per continuare.");
                            tastiera.nextLine();
                            tastiera.nextLine();
                            break;
                        }
                    }
                    case 6: //termina tutti gli interventi antecedenti ad una determianta data
                    {
                        boolean dataCorretta;
                        LocalDate dataLimite;
                        int contatoreTentativi=2;
                        do
                        {
                            System.out.println("Anno dell'intervento --> ");
                            annoInizio=tastiera.nextInt();
                            System.out.println("Mese dell'intervento --> ");
                            meseInizio=tastiera.nextInt();
                            System.out.println("Giorno dell'intervento --> ");
                            giornoInizio=tastiera.nextInt();
                            dataCorretta=isDataValida(giornoInizio,meseInizio,annoInizio);
                            if(dataCorretta)
                                break;
                            else
                            {
                                if(contatoreTentativi!=0)
                                    System.out.println("\nLa data da te inserita per la ricerca degli interventi: \""+giornoInizio+"/"+meseInizio+"/"+annoInizio+"\" non è corretta. Reinserirla.");
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
                            dataLimite=LocalDate.of(annoInizio,meseInizio,giornoInizio);
                            esitoOperazione=a.terminaInterventoDataAntecedente(dataLimite);
                            if(esitoOperazione==0)
                                System.out.println("Tutti gli interventi precedenti alla data: \""+giornoInizio+"/"+meseInizio+"/"+annoInizio+"\" sono stati terminati");
                        }
                        catch(InterventiNonTrovati e1)
                        {
                            System.out.println(e1.toString());
                        }
                        catch(ImpossibileTerminareIntervento e2)
                        {
                            System.out.println(e2.toString());
                            System.out.println("Premi un pulsante per continuare.");
                            tastiera.nextLine();
                            tastiera.nextLine();
                            break;
                        }
                        catch(DateTimeException e3)
                        {
                            System.out.println("\nLa data da te inserita per la ricerca degli interventi: \""+giornoInizio+"/"+meseInizio+"/"+annoInizio+"\" non è corretta. Reinserirla.");
                        }
                        System.out.println("Premi un pulsante per continuare.");
                        tastiera.nextLine();
                        tastiera.nextLine();
                        break;
                    }
                    case 7: //termina tutti gli interventi di una determianta data
                    {
                        boolean dataCorretta;
                        LocalDate dataLimite;
                        int contatoreTentativi=2;
                        do
                        {
                            System.out.println("Anno dell'intervento --> ");
                            annoInizio=tastiera.nextInt();
                            System.out.println("Mese dell'intervento --> ");
                            meseInizio=tastiera.nextInt();
                            System.out.println("Giorno dell'intervento --> ");
                            giornoInizio=tastiera.nextInt();
                            dataCorretta=isDataValida(giornoInizio,meseInizio,annoInizio);
                            if(dataCorretta)
                                break;
                            else
                            {
                                if(contatoreTentativi!=0)
                                    System.out.println("\nLa data da te inserita per la ricerca degli interventi: \""+giornoInizio+"/"+meseInizio+"/"+annoInizio+"\" non è corretta. Reinserirla.");
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
                            dataLimite=LocalDate.of(annoInizio,meseInizio,giornoInizio);
                            esitoOperazione=a.terminaInterventoDataUguale(dataLimite);
                            if(esitoOperazione==0)
                                System.out.println("Tutti gli interventi in data: \""+giornoInizio+"/"+meseInizio+"/"+annoInizio+"\" sono stati terminati");
                        }
                        catch(InterventiNonTrovati e1)
                        {
                            System.out.println(e1.toString());
                        }
                        catch(ImpossibileTerminareIntervento e2)
                        {
                            System.out.println(e2.toString());
                            System.out.println("Premi un pulsante per continuare.");
                            tastiera.nextLine();
                            tastiera.nextLine();
                            break;
                        }
                        catch(DateTimeException e3)
                        {
                            System.out.println("\nLa data da te inserita per la ricerca degli interventi: \""+giornoInizio+"/"+meseInizio+"/"+annoInizio+"\" non è corretta. Reinserirla.");
                        }
                        System.out.println("Premi un pulsante per continuare.");
                        tastiera.nextLine();
                        tastiera.nextLine();
                        break;
                    }
                    case 8: //elenco di tutti gli interventi dell'azienda
                    {
                        System.out.println(a.elencoInterventi());
                        break;
                    }
                    case 9: //elenco di tutti gli interventi in ordine cronologico di una determinata babysitter
                    {
                        boolean inputCorretto=false;
                        int contatoreTentativi=2;
                        do
                        {
                            System.out.println("Inserisci il nome della babysitter da cercare --> ");
                            nomeBabysitter=tastiera.nextLine();
                            System.out.println("Inserisci il cognome della babysitter da cercare --> ");
                            cognomeBabysitter=tastiera.nextLine();
                            inputCorretto=isInputCorretto(nomeBabysitter,cognomeBabysitter);
                            if(inputCorretto)
                                break;
                            else
                            {
                                if(contatoreTentativi!=0)
                                    System.out.println("\nBisogna reinserire le informazioni sulla babysitter perché sono errate:\n---------------------------------------------\nBabysitter: "+nomeBabysitter+" "+cognomeBabysitter+"\n---------------------------------------------");
                                if(contatoreTentativi==1)
                                    System.out.println("Hai ancora "+contatoreTentativi+" tentativo.\n");
                                else if(contatoreTentativi>1)
                                    System.out.println("Hai ancora "+contatoreTentativi+" tentativi.\n");
                                else if(contatoreTentativi==0)
                                    break;
                                contatoreTentativi--;
                            }
                        }while(!inputCorretto && contatoreTentativi!=-1);
                        String[] elencoRicerca=a.elencoInterventiCronologiciBabysitter(nomeBabysitter, cognomeBabysitter);
                        if(elencoRicerca==null)
                            System.out.println("\nLa babysitter: \""+nomeBabysitter+" "+cognomeBabysitter+"\" non è stata trovata");
                        else
                        {
                            for(int i=0; i<elencoRicerca.length;i++)
                                System.out.println(elencoRicerca[i]);
                        }
                        System.out.println("Premi un pulsante per continuare.");
                        tastiera.nextLine();
                        elencoRicerca=null;
                        break;
                    }
                    case 10: //elenco di tutti gli interventi di una determinata data
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
                            dataCorretta=isDataValida(giornoInizio,meseInizio,annoInizio);
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
                    case 11:
                    {
                        try
                        {
                            a.esportaInterventiCSV(nomeFileTesto);
                            System.out.println("Gli interventi sono stati esportati correttamente");
                        }
                        catch(IOException e1)
                        {
                            System.out.println("Impossibile accedere al file");
                        }
                        catch(FileException e2)
                        {
                            System.out.println(e2.toString());
                        }
                        break;
                    }
                    case 12:
                    {
                        try 
                        {
                            a.salvaAzienda(nomeFileBinario);
                            System.out.println("Dati salvati correttamente");
                        }
                        catch (IOException ex) 
                        {
                            System.out.println("Impossibile accedere al file in scrittura");
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
        if(giorno==0 && mese==0 && anno==0 && ora==0 && minuti==0)
            return false;
        
        else
        {
            LocalDateTime oggi,dataInserita;

            oggi=LocalDateTime.now();
            dataInserita=LocalDateTime.of(anno,mese,giorno,ora,minuti);
            if(dataInserita.isBefore(oggi))
                return false;
            else
                return true;
        }
    }
    private static boolean isDataValida(int giorno, int mese, int anno)
    {
        if(giorno<0 || giorno>31)
            return false;
        if(mese<0 || mese>12)
            return false;
        if(anno<0 || anno>9999)
            return false;
        if(giorno==0 && mese==0 && anno==0)
            return false;
        else
            return true;
    }
    public static boolean isInputCorretto(String nome, String cognome, String indirizzo, String nomeB, String cognomeB)
    {
        if(nome.length()==0 || cognome.length()==0 || indirizzo.length()==0 || nomeB.length()==0 || cognomeB.length()==0)
            return false;
        return true;
    }
    public static boolean isInputCorretto(String nomeB, String cognomeB)
    {
        if(nomeB.length()==0 || cognomeB.length()==0)
            return false;
        return true;
    }
}
