/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babysitter;

import eccezioni.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * La classe rappresenta un'azienda.<br>
 * I suoi suoi attributi sono:<br>
 * N_MAX_INTERVENTI: 1000, rappresenta il numero massimo di interventi che l'azienda può memorizzare <br>
 * elencoInterventi è l'array di interventi dell'azienda<br>
 * nInterventiPresenti è il numero di interventi presenti nell'array di interventi dell'azienda
 * @author Pc
 */
public class Azienda
{
    private final int N_MAX_INTERVENTI=1000;
    private Intervento[] elencoInterventi;
    private int nInterventiPresenti;

    /**
     * Costruttore della classe Azienda.
     * Viene istanziato l'array vuoto di interventi dell'azienda
     */
    public Azienda()
    {
        elencoInterventi=new Intervento[N_MAX_INTERVENTI];
        for (int i=0; i<N_MAX_INTERVENTI; i++)
        {
            elencoInterventi[i]=new Intervento();
        }
    }
    /**
     * Costruttore di copia della classe Azienda
     * @param a azienda da cui verrà istanziata al nuova azienda. 
     * L'azienda istanziata sarà una copia dell'Azienda a.<br>
     * Viene istanziato l'array di interventi dell'azienda grazie a quello dell'azienda "a" da copiare
     */
    public Azienda(Azienda a)
    {
        elencoInterventi=new Intervento[N_MAX_INTERVENTI]; //1 ripiano contenente un array di 5 mensole contenenti 15 libri
        Intervento t;
        for (int i=0; i<getNumMaxInterventi(); i++)
        {
            t=elencoInterventi[i];
            if(elencoInterventi[i]!=null)
                elencoInterventi[i]=t;
        }
    }
    /**
     * Metodo usato per impostare l'array di interventi dell'azienda
     * @param elencoInterventi è l'array di interventi che deve essere impostato
     */
    public void setElencoInterventi(Intervento[] elencoInterventi) {
        this.elencoInterventi = elencoInterventi;
    }
    /**
     * Metodo usato per impostare il numero massimo d'interventi presenti nell'array di interventi dell'azienda
     * @param nInterventiPresenti è il numero di interventi presenti che deve essere impostato
     */
    public void setnInterventiPresenti(int nInterventiPresenti) {
        this.nInterventiPresenti = nInterventiPresenti;
    }
    /**
     * Metodo che ritorna il numero massimo di interventi che l'azienda può avere
     * @return N_MAX_INTERVENTI è il numero massimi di interventi che l'azienda può avere (1000)
     */
    public int getNumMaxInterventi() {
        return N_MAX_INTERVENTI;
    }
    /**
     * Metodo usato per ottenere l'array di interventi dell'azienda
     * @return elencoInterventi
     */
    public Intervento[] getElencoInterventi() {
        return elencoInterventi;
    }
    /**
     * Metodo usato per ottenere il numero di interventi presenti nell'array di interventi dell'azienda
     * @return nInterventiPresenti
     */
    public int getNumInterventiPresenti() {
        return nInterventiPresenti;
    }
    /**
     * Metodo usato per ottenere un intervento tramite il suo id
     * @param id è il codice univoco dell'intervento da ottenere
     * @return Intervento è l'intervento cercato tramite il suo id
     * @return null se non è stato trovato l'intervento
     */
    public Intervento getIntervento(long id)
    {
        Intervento t;
        for (int i=0;i<nInterventiPresenti;i++)
        {
            if (elencoInterventi[i]!=null)
            {
                t=elencoInterventi[i];
                if (t.getIdIntervento()==id)
                {
                    return new Intervento(t);
                }
            }
        }
        return null;
    }
    /**
     * Metodo usato per ottenere un intervento tramite la sua posizione
     * @param posizioneIntervento è la posizione dell'intervento da ottenere
     * @return Intervento nella posizione specificata all'interno dell'array di interventi dell'azienda
     * @return null se non è stato trovato
     */
    public Intervento getIntervento(int posizioneIntervento)
    {
        if (elencoInterventi[posizioneIntervento]!=null)
            return new Intervento(elencoInterventi[posizioneIntervento]);
        else
            return null;
    }
    /**
     * Metodo usato per aggiungere un nuovo intervento all'array di interventi dell'azienda
     * @param t pè l'intervento da aggiungere all'interno dell'array di interventi dell'azienda
     * @return -1 se il numero di interventi presenti nell'array di interventi dell'azienda sono arrivati al massimo (N_MAX_INTERVENTI=1000)
     * @return 0 se l'aggiunta dell'intervento è andata a buon fine
     */
    public int aggiungiIntervento(Intervento t)
    {
        if (nInterventiPresenti>=N_MAX_INTERVENTI)
            return -1;
        elencoInterventi[nInterventiPresenti]=new Intervento(t);
        nInterventiPresenti++;
        return 0;         
    }
    /**
     * Metodo usato per cancellare un intervento tramite il suo id
     * @param id è il codice dell'intervento
     */
    public int eliminaIntervento(long id) throws InterventoNonTrovato, InterventiNonTrovati
    {
        if(this.getNumMaxInterventi()==0)
            throw new eccezioni.InterventiNonTrovati();
        for(int i=0;i<getNumMaxInterventi();i++)
        {
            if(elencoInterventi[i]!=null)
            {
                if(elencoInterventi[i].getIdIntervento()==id)
                {
                    for(int j=i;j<getNumMaxInterventi()-1;j++)
                    {
                        elencoInterventi[j]=elencoInterventi[j+1];
                    }
                    elencoInterventi[nInterventiPresenti-1]=null;
                    nInterventiPresenti--;
                    return 0;
                }
            }
        }
        throw new eccezioni.InterventoNonTrovato(id);
    }

    /**
     * Metodo usato per impostare a true l'attributo boolean dell'intervento cercato contenuto nell'array di interventi dell'azienda tramite un id
     * @param idIntervento è l'id, codice univoco dell'intervento da andare a terminare
     * @return -1 se l'intervento non è stato trovato
     * @return -2 se l'intervento cercato è già stato terminato
     * @return 0 per confermare che la terminazione dell'intervento è andata a buon fine
     */
    public int modificaStatusIntervento(long idIntervento)
    {
        int posizioneInterventoArray;
        posizioneInterventoArray=posizioneIntervento(idIntervento);
        if (posizioneInterventoArray==-1)
            return -1;
        else if(elencoInterventi[posizioneInterventoArray].isTerminato())
            return -2;
        elencoInterventi[posizioneInterventoArray].setTerminato(true);
        return 0;      
    }
    /**
     * Metodo privato che ritorna la posizione di un intervento contenuto nell'array di interventi dell'azienda
     * @param idIntervento è l'id, codice univoco dell'intervento da andare a trovare
     * @return i è la posizione dell'intervento cercato contenuto nell'array di interventi dell'azienda
     * @return -1 se l'intervento non è presente
     */
    private int posizioneIntervento(long idIntervento)
    {
        Intervento t;
        
        for (int i=0;i<nInterventiPresenti;i++)
        {
            if (elencoInterventi[i]!=null)
            {
                t=elencoInterventi[i];
                if (t.getIdIntervento()==idIntervento)
                    return i;
            }
        }
        return -1;  //intervento non presente
    }
        /**
    * Metodo che ritorna tutti gli eventuali interventi di una babysitter specificata.<br>
    * @param nome è il nome della babysitter da cercare
    * @param cognome è il cognome della babysitter da cercare
    * @return ritorna elencoInterventiBabysitter che è un array di intervernti dell'azienda
    */
    public String[] elencoInterventiBabysitter(String nome, String cognome)
    {
        int numeroInterventiBabysitter=0;
        Intervento intervento;
        Babysitter babysitter;
        
        //conto il numero di interventi presenti per la babysitter
        for(int i=0;i<getNumMaxInterventi();i++)
        {
            if(elencoInterventi[i]!=null)
            {
                intervento=getIntervento(i);
                babysitter=intervento.getBabysitter();
                if(babysitter.getNome().equalsIgnoreCase(nome) && babysitter.getCognome().equalsIgnoreCase(cognome))
                        numeroInterventiBabysitter++;
            }
        }
        //se non ci sono interventi per quella babysitter, return null
        if(numeroInterventiBabysitter==0)
            return null;
        
        //istanzio un array lungo numeroInterventiBabysitter in cui inserisco gli interventi della babysitter
        int posizioneIntervento=0;
        String[] elencoInterventiBabysitter=new String[numeroInterventiBabysitter];
        for(int i=0;i<getNumMaxInterventi();i++)
        {
            if(elencoInterventi[i]!=null)
            {
                intervento=getIntervento(i);
                babysitter=intervento.getBabysitter();
                if(babysitter.getNome().equalsIgnoreCase(nome) && babysitter.getCognome().equalsIgnoreCase(cognome))
                {
                    elencoInterventiBabysitter[posizioneIntervento]=intervento.toString();
                    posizioneIntervento++;
                }  
            }
        }
        return elencoInterventiBabysitter;
    }
    /**
    * Metodo che ritorna tutti gli eventuali interventi presenti nella data specificata.<br>
    * @param anno anno della data in cui andare a cercare gli interventi
    * @param mese mese della data in cui andare a cercare gli interventi
    * @param giorno giorno della data in cui andare a cercare gli interventi
    * @return ritorna elencoInterventiData che è un array di intervernti dell'azienda
    */
    public String[] elencoInterventiData(int anno, int mese, int giorno)
    {
        int numeroInterventiData=0;
        int annoIntervento, meseIntervento, giornoIntervento;
        Intervento intervento;
        
        LocalDateTime dataIntervento;
        
        //conto il numero di interventi presenti per la babysitter
        for(int i=0;i<getNumMaxInterventi();i++)
        {
            if(elencoInterventi[i]!=null)
            {
                intervento=getIntervento(i);
                dataIntervento=intervento.getInizio();
                annoIntervento=dataIntervento.getYear();
                meseIntervento=dataIntervento.getMonthValue();
                giornoIntervento=dataIntervento.getDayOfMonth();
                if(anno==annoIntervento && mese==meseIntervento && giorno==giornoIntervento)
                        numeroInterventiData++;
            }
        }
        //se non ci sono interventi per quella data, return null
        if(numeroInterventiData==0)
            return null;
        
        //istanzio un array lungo numeroInterventiData in cui inserisco gli interventi di quella data
        int posizioneIntervento=0;
        String[] elencoInterventiData=new String[numeroInterventiData];
        for(int i=0;i<getNumMaxInterventi();i++)
        {
            if(elencoInterventi[i]!=null)
            {
                intervento=getIntervento(i);
                dataIntervento=intervento.getInizio();
                annoIntervento=dataIntervento.getYear();
                meseIntervento=dataIntervento.getMonthValue();
                giornoIntervento=dataIntervento.getDayOfMonth();
                if(anno==annoIntervento && mese==meseIntervento && giorno==giornoIntervento)
                {
                    elencoInterventiData[posizioneIntervento]=intervento.toString();
                    posizioneIntervento++;
                }
            }
        }
        return elencoInterventiData;
    }
    /**
     * Metodo usato per mostrare un elenco di tutti gli interventi presenti nell'azienda
     * @return attaccato: è una stringa contenente i dati dell'intervento.<br>
     * Se non ci fossero interventi si avrebbe come messaggio:"Nessun intervento presente"
     */
    public String elencoInterventi()
    {
        String attaccato="";
        Intervento intervento;
        if (nInterventiPresenti==0)
            attaccato+="Nessun intervento presente nell'archivio dell'azienda";
        else
        {
            for (int i=0;i<nInterventiPresenti;i++)
            {
                intervento=getIntervento(i);
                attaccato+=intervento.toString()+"\n";
            }
        }
        return attaccato;
    }
}
