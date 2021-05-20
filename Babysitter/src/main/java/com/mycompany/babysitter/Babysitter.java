/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babysitter;

import java.io.Serializable;

/**
 * La classe rappresenta una babysitter.<br>
 * I suoi suoi attributi sono:<br>
 * nome <br>
 * cognome
 * @author Claudio Pasinelli
 */
public class Babysitter implements Serializable
{
    private String nome;
    private String cognome;
    
    /**
     * Costruttore della classe Babysitter. Consente di istanziare una nuova babysitter
     * @param nome nome della babysitter
     * @param cognome cognome della babysitterr
     */
    public Babysitter(String nome, String cognome)
    {
        setNome(nome);
        setCognome(cognome);
    }
    /**
     * Costruttore di copia della classe Babysitter. Consente di istanziare una nuova babysitter
     * @param babysitter babysitter da cui verrà istanziata la nuova babysitter. 
     * La babysitter istanziata sarà una copia della Babysitter babysitter
     */
    public Babysitter(Babysitter babysitter)
    {
        setNome(babysitter.getNome());
        setCognome(babysitter.getCognome());
    }
    /**
     * Costruttore vuoto della classe Babysitter.
     * Consente di istanziare una nuova babysitter avente i seguenti valori di default:<br>
     * nome: stringa vuota <br>
     * cognome: stringa vuota <br>
     */
    public Babysitter()
    {
        nome="";
        cognome="";
    }
    /**
     * Metodo che ritorna il nome della babysitter
     * @return nome
     */
    public String getNome() {
        return nome;
    }
    /**
     * Metodo che ritorna il cognome della babysitter
     * @return cognome
     */
    public String getCognome() {
        return cognome;
    }
    /**
     * Metodo che consente di dare un nome alla babysitter
     * @param nome nome della babysitter
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Metodo che consente di dare un cognome alla babysitter
     * @param cognome cognome della babysitter
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    /**
     * Metodo che ritorna il nome e cognome della babysitter
     * @return attaccato, una stringa contenente il nome e cognome della babysitter
     */
    public String toString()
    {
        String attaccato="";
        attaccato=attaccato+getNome()+" "+getCognome();
        return attaccato;
    }
    /**
     * Metodo che verifica se una babysitter è uguale a quella cercata (tramite il nome ed il cognome)
     * @param o è un tipico oggetto
     * @return true se il paragone tra le babysitter è stato verificato
     * @return false se le babysitter sono diverse
     */
    public boolean equals(Object o)
    {
        Babysitter babysitter=(Babysitter)o;
        if(getNome().equalsIgnoreCase(babysitter.getNome()) && getCognome().equalsIgnoreCase(babysitter.getCognome()))
            return true;
        else
            return false;
    }
}
