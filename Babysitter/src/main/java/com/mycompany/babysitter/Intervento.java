/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babysitter;

import eccezioni.DateException;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * La classe rappresenta un intervento.<br>
 * I suoi suoi attributi sono:<br>
 * nomeCliente<br>
 * cognomeCliente<br>
 * indirizzoCliente<br>
 * idIntervento<br>
 * babysitter: è la babysitter che si occupa di eseguire l'intervento. Ha un nome ed un cognome.<br>
 * inizio: è l'inizio dell'intervento (ha anno/mese/giorno - ora:minuti come parametri)<br>
 * fine: è la fine dell'intervento (ha anno/mese/giorno - ora:minuti come parametri). Deve essere tassativamente postuma all'inizio.<br>
 * terminato: boolean che indica se l'intervento è finito o no. Di default è impostato su false.
 * @author Claudio Pasinelli
 */
public class Intervento implements Serializable
{
    private String nomeCliente;
    private String cognomeCliente;
    private String indirizzoCliente;
    private long idIntervento;
    private Babysitter babysitter;
    private LocalDateTime inizio;
    private LocalDateTime fine;
    private boolean terminato;
    /**
     * Costruttore della classe Intervento. Consente di istanziare un nuovo intervento grazie ai seguenti parametri:
     * @param nomeCliente è una stringa contenente il nome del cliente<br>
     * @param cognomeCliente è una stringa contenente il cognome del cliente<br>
     * @param indirizzo è una stringa contenente l'indirizzo del cliente<br>
     * @param id è l'id, codice univoco dell'intervento.<br>
     * @param b1 è la babysitter che si occupa dell'intervento. Ha nome è cognome.<br>
     * @param annoInizio è l'anno d'inizio dell'intervento<br>
     * @param meseInizio è il mese d'inizio dell'intervento<br>
     * @param giornoInizio è il giorno d'inizio dell'intervento<br>
     * @param oraInizio è l'ora d'inizio dell'intervento<br>
     * @param minutiInizio sono i minuti d'inizio dell'intervento<br>
     * @param annoFine è l'anno di fine dell'intervento<br>
     * @param meseFine è il mese di fine dell'intervento<br>
     * @param giornoFine è il giorno di fine dell'intervento<br>
     * @param oraFine è l'ora di fine dell'intervento<br>
     * @param minutiFine sono i minuti di fine dell'intervento
     * @throws DateException è l'eccezione che avviene in caso d'errore d'inserimento delle date
     */
    public Intervento(String nomeCliente, String cognomeCliente, String indirizzo, long id, Babysitter b1, int annoInizio, int meseInizio, int giornoInizio, int oraInizio, int minutiInizio, int annoFine, int meseFine, int giornoFine, int oraFine, int minutiFine) throws DateException
    {
        setNomeCliente(nomeCliente);
        setCognomeCliente(cognomeCliente);
        setIndirizzoCliente(indirizzo);
        idIntervento=id;
        setBabysitter(b1);
        setInizio(annoInizio,meseInizio,giornoInizio,oraInizio,minutiInizio);
        setFine(annoFine, meseFine, giornoFine, oraFine, minutiFine);
        terminato=false;
    }
    /**
     * Costruttore della classe Intervento. Consente di istanziare un nuovo intervento grazie ai seguenti parametri:
     * @param nomeCliente è una stringa contenente il nome del cliente<br>
     * @param cognomeCliente è una stringa contenente il cognome del cliente<br>
     * @param indirizzo è una stringa contenente l'indirizzo del cliente<br>
     * @param id è l'id, codice univoco dell'intervento.<br>
     * @param b1 è la babysitter che si occupa dell'intervento. Ha nome è cognome.<br>
     * @param inizioIntervento è la data d'inizio dell'intervento
     * @param fineIntervento è la data di fine dell'intervento
     * @throws DateException è l'eccezione che avviene in caso d'errore d'inserimento delle date
     */
    public Intervento(String nomeCliente, String cognomeCliente, String indirizzo, long id, Babysitter b1, LocalDateTime inizioIntervento, LocalDateTime fineIntervento) throws DateException
    {
        setNomeCliente(nomeCliente);
        setCognomeCliente(cognomeCliente);
        setIndirizzoCliente(indirizzo);
        idIntervento=id;
        setBabysitter(b1);
        setInizio(inizioIntervento);
        setFine(fineIntervento);
        terminato=false;
    }
    /**
     * Costruttore di copia della classe Intervento.
     * @param intervento intervento da cui verrà istanziato il nuovo intervento. 
     * L'intervento istanziato sarà una copia dell'Intervento intervento.
     */
    public Intervento(Intervento intervento)
    {
        setNomeCliente(intervento.getNomeCliente());
        setCognomeCliente(intervento.getCognomeCliente());
        setIndirizzoCliente(intervento.getIndirizzoCliente());
        idIntervento=intervento.getIdIntervento();
        setBabysitter(intervento.getBabysitter());
        setFine(intervento.getFine());
        setInizio(intervento.getInizio());
        setTerminato(intervento.isTerminato());
    }
    /**
     * Costruttore vuoto della classe Intervento.
     * Consente di istanziare un intervento impostandone i seguenti valori di default:<br>
     * nomeCliente=stringa vuota<br>
     * cognomeCliente=stringa vuota<br>
     * idIntervento=0<br>
     * inizio: 1 gennaio 2021<br>
     * fine: 2 gennaio 2021<br>
     * babysitter tramite il costruttore vuoto della classe Babysitter<br>
     * terminato=false
     */
    public Intervento()
    {
        nomeCliente="";
        cognomeCliente="";
        idIntervento=0;
        inizio=LocalDateTime.of(2021,1,1,1,1);
        fine=LocalDateTime.of(2021,1,2,1,1);
        setBabysitter();
        terminato=false;
    }
    /**
     * Metodo usato per impostare il nome del cliente
     * @param nomeCliente nome del cliente
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    /**
     * Metodo usato per impostare il cognome del cliente
     * @param cognomeCliente cognome del cliente
     */
    public void setCognomeCliente(String cognomeCliente) {
        this.cognomeCliente = cognomeCliente;
    }
    /**
     * Metodo usato per impostare l'indirizzo del cliente
     * @param indirizzoCliente indirizzo del cliente
     */
    public void setIndirizzoCliente(String indirizzoCliente) {
        this.indirizzoCliente = indirizzoCliente;
    }
    /**
     *  Metodo usato per impostare l'inizio dell'intervento usando una data formata da: anno, mese, giorno, ore e minuti
     * @param anno anno in cui deve iniziare l'intervento
     * @param mese mese in cui deve iniziare l'intervento
     * @param giorno giorno in cui deve iniziare l'intervento
     * @param ora ora in cui deve iniziare l'intervento
     * @param minuti minuti in cui deve iniziare l'intervento
     */
    public void setInizio(int anno, int mese, int giorno, int ora, int minuti) throws DateTimeException
    {
        inizio=LocalDateTime.of(anno,mese,giorno,ora,minuti);
    }
    /**
     * Metodo usato per impostare l'inizio dell'intervento tramite una data già formata
     * @param inizio inizio
     */
    public void setInizio(LocalDateTime inizio) throws DateTimeException
    {
        this.inizio=inizio;
    }
    /**
     * Metodo usato per impostare la fine dell'intervento usando una data formata da: anno, mese, giorno, ore e minuti
     * @param annoFine anno in cui deve finire l'intervento
     * @param meseFine mese in cui deve finire l'intervento
     * @param giornoFine giorno in cui deve finire l'intervento
     * @param oraFine ora in cui deve finire l'intervento
     * @param minutiFine minuti in cui deve finire l'intervento
     */
    public void setFine(int annoFine, int meseFine, int giornoFine, int oraFine, int minutiFine) throws DateException
    {
        LocalDateTime dataFineFinta;
        try
        {
            dataFineFinta=LocalDateTime.of(annoFine,meseFine,giornoFine,oraFine,minutiFine);
        }
        catch(DateTimeException e1)
        {
            throw new eccezioni.DateException("Data della fine dell'intervento non corretta.");
        }
        if(fine==null)
        {
            if(dataFineFinta.isBefore(this.getInizio()))
                throw new eccezioni.DateException("Data di fine antecedente a quella d'inizio.");
            else if(dataFineFinta.isEqual(this.getInizio()))
                throw new eccezioni.DateException("Data di fine uguale a quella d'inizio.");
            else
                fine=LocalDateTime.of(annoFine,meseFine,giornoFine,oraFine,minutiFine);
        }
    }
    /**
     * Metodo usato per impostare la fine dell'intervento tramite una data già formata
     * @param fine fine dell'intervento
     */
    public void setFine(LocalDateTime fine) {
        this.fine=fine;
    }
    /**
     * Metodo usato per modificare lo status dell'intervento
     * @param terminato boolean che indica se l'intervento è stato terminato o no
     */
    public void setTerminato(boolean terminato) {
        this.terminato = terminato;
    }
    /**
     * Metodo usato per terminare un intervento allo scopo di indicare che è già stato svolto
     * @return -1 se è già stato terminato
     * @return 0 se non era già stato terminato
     */
    public int terminaIntervento()
    {
        if(isTerminato())
            return -1;
        else
        {
            terminato=true;
            return 0;
        }
    }
    /**
     * Metodo che istanzia una nuova babysitter a partire da una preesistente
     * @param babysitter babysitter che verrà copiata per quella dell'intervento
     */
    public void setBabysitter (Babysitter babysitter) {
        this.babysitter=new Babysitter(babysitter);
    }
    /**
     * Metodo che istanzia una nuova babysitter a partire da un nome ed un cognome
     * @param nome nome della babysitter da istanziare
     * @param cognome cognome della babysitter da istanziare
     */
    public void setBabysitter (String nome, String cognome) {
        babysitter=new Babysitter(nome, cognome);
    }
    /**
     * Metodo che istanzia una nuova babysitter vuota.
     */
    public void setBabysitter () {
        babysitter=new Babysitter();
    }
    /**
     * Metodo usato per ottenere il nome del cliente
     * @return nomeCliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }
    /**
     * Metodo usato per ottenere il cognome del cliente
     * @return cognomeCliente
     */
    public String getCognomeCliente() {
        return cognomeCliente;
    }
    /**
     * Metodo usato per ottenere l'indirizzo del cliente
     * @return indirizzoCliente
     */
    public String getIndirizzoCliente() {
        return indirizzoCliente;
    }
    /**
     * Metodo usato per ottenere l'id univoco dell'intervento
     * @return idIntervento
     */
    public long getIdIntervento() {
        return idIntervento;
    }
    /**
     * Metodo usato per ottenere la data d'inizio dell'intervento
     * @return inizio
     */
    public LocalDateTime getInizio() {
        return inizio;
    }
    public LocalDate getInizioCorto()
    {
        LocalDate inizioCorto;
        int anno, mese, giorno;
        anno=inizio.getYear();
        mese=inizio.getMonthValue();
        giorno=inizio.getDayOfMonth();
        inizioCorto=LocalDate.of(anno,mese,giorno);
        return inizioCorto;
    }
    /**
     * Metodo usato per ottenere la data in cui finisce l'intervento
     * @return fine
     */
    public LocalDateTime getFine() {
        return fine;
    }
    /**
     * Metodo usato per ottenere lo un boolean che indica lo status dell'intervento (per capire se è terminato o meno)
     * @return terminato
     */
    public boolean isTerminato() {
        return terminato;
    }
    /**
     * Metodo usato per ottenrere la babysitter che si occupa di questo intervento
     * @return babysitter
     */
    public Babysitter getBabysitter() {
        return new Babysitter(babysitter);
    }
    /**
     * Metodo usato per visualizzare i dati dell'intervento, come:
     * il codice del'intervento;<br>
     * il nome, cognome e l'indirizzo del cliente;<br>
     * il nome e cognome della babysitter;<br>
     * l'inizio e la fine dell'intervento;<br>
     * lo status dell'intervento (terminato o meno).<br>
     * @return attaccato: una stringa contenente i dati dell'intervento
     */
    public String toString()
    {
        String attaccato="";
        attaccato+="---------------------------------------------\n";
        attaccato+="Codice dell'intervento: "+getIdIntervento()+"\nCliente: "+getNomeCliente()+" "+getCognomeCliente()+"\nIndirizzo del cliente: "+getIndirizzoCliente()+"\nBabysitter: "+getBabysitter().toString()+"\nData d'inizio intervento: "+getInizio().getDayOfMonth()+"/"+getInizio().getMonthValue()+"/"+getInizio().getYear()+" - "+getInizio().getHour()+":"+getInizio().getMinute()+"\nData di fine intervento: "+getFine().getDayOfMonth()+"/"+getFine().getMonthValue()+"/"+getFine().getYear()+" - "+getFine().getHour()+":"+getFine().getMinute()+"\n";
        if(isTerminato())
            attaccato+="Status dell'intervento: terminato.\n---------------------------------------------";
        else if(!isTerminato())
            attaccato+="Status dell'intervento: non terminato.\n---------------------------------------------";
        return attaccato;
    }
    public boolean equals(Object o)
    {
        Intervento intervento=(Intervento)o;
        if(getBabysitter().getCognome().equalsIgnoreCase(intervento.getBabysitter().getCognome()) && getBabysitter().getNome().equalsIgnoreCase(intervento.getBabysitter().getNome()) && getIdIntervento()==intervento.getIdIntervento() && getNomeCliente().equalsIgnoreCase(intervento.getNomeCliente()) && getCognomeCliente().equalsIgnoreCase(intervento.getCognomeCliente()) && getInizio().equals(intervento.getInizio()) && getFine().equals(intervento.getFine()))
            return true;
        else if((toString().compareTo(intervento.toString()))==0)
            return true;
        else
            return false;
    }

}
