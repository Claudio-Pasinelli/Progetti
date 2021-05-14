/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babysitter;

/**
 *
 * @author User
 */
public class Ordinatore 
{
    /*public static void  scambia(Libro v[],int posizione1, int posizione2)
    {
        Libro c;
        c=v[posizione1];
        v[posizione1]=v[posizione2];
        v[posizione2]=c;
    }*/
    public static void  scambia(String v[],int posizione1, int posizione2)
    {
        String c;
        c=v[posizione1];
        v[posizione1]=v[posizione2];
        v[posizione2]=c;
    }
    
    public static void  scambia(int v[],int posizione1, int posizione2)
    {
        int c;
        c=v[posizione1];
        v[posizione1]=v[posizione2];
        v[posizione2]=c;
    }
    
    //Ordina un array di Libri in ordine di prezzo
    /*public static Libro[] selectionSortLibriPrezzoCrescente(Libro[] a)
    {
        Libro[] ordinato=new Libro[a.length];
        
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        //Applico l'algoritmo 
        //selection sort al vettore di copia
        for (int i=0;i<ordinato.length-1;i++)
        {
            for (int j=i+1;j<ordinato.length;j++)
            {
                if (ordinato[j].prezzo()<ordinato[i].prezzo())
                    scambia(ordinato,i,j);
            }
        }
        return ordinato;
    }
    
    //Ordina un array di Libri in ordine alfabetico di autore e a parità di autore in ordine alfabetico di titolo
    public static Libro[] selectionSortAlfabeticoAutoreTitolo(Libro[] a)
    {
        Libro[] ordinato=new Libro[a.length];
        
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        //Applico l'algoritmo 
        //selection sort al vettore di copia
        for (int i=0;i<ordinato.length-1;i++)
        {
            for (int j=i+1;j<ordinato.length;j++)
            {
                if (ordinato[j].getAutore().compareToIgnoreCase(ordinato[i].getAutore())<0 || (ordinato[j].getAutore().compareToIgnoreCase(ordinato[i].getAutore())==0 &&  ordinato[j].getTitolo().compareToIgnoreCase(ordinato[i].getTitolo())<0))
                    scambia(ordinato,i,j);
            }
        }
        return ordinato;
    }*/
    public static void  scambia(Intervento v[],int posizione1, int posizione2)
    {
        Intervento c;
        c=v[posizione1];
        v[posizione1]=v[posizione2];
        v[posizione2]=c;
    }
    public static Intervento[] selectionSortInterventiCronologici(Intervento[] a)
    {
        Intervento[] ordinato=new Intervento[a.length];
        
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        //Applico l'algoritmo 
        //selection sort al vettore di copia
        for (int i=0;i<ordinato.length-1;i++)
        {
            for (int j=i+1;j<ordinato.length;j++)
            {
                if (ordinato[j].getInizio().isBefore(ordinato[i].getInizio()))
                    scambia(ordinato,i,j);
            }
        }
        return ordinato;
    }
    //Ordina un array di Stringhe in ordine alfabetico
    public static String[] selectionSortCrescente(String[] a)
    {
        String[] ordinato=new String[a.length];
        
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        //Applico l'algoritmo 
        //selection sort al vettore di copia
        for (int i=0;i<ordinato.length-1;i++)
        {
            for (int j=i+1;j<ordinato.length;j++)
            {
                if (ordinato[j].compareToIgnoreCase(ordinato[i])<0)
                    scambia(ordinato,i,j);
            }
        }
        return ordinato;
    }
    
    //Ordina un array di Stringhe in ordine alfabetico inverso
    public static String[] selectionSortDecrescente(String[] a)
    {
        String[] ordinato=new String[a.length];
        
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        //Applico l'algoritmo 
        //selection sort al vettore di copia
        for (int i=0;i<ordinato.length-1;i++)
        {
            for (int j=i+1;j<ordinato.length;j++)
            {
                if (ordinato[j].compareToIgnoreCase(ordinato[i])>0)
                    scambia(ordinato,i,j);
            }
        }
        return ordinato;
    }
    
    
    public static int[] selectionSortCrescente(int[] a)
    {
        int[] ordinato=new int[a.length];
        
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        //Applico l'algoritmo 
        //selection sort al vettore di copia
        for (int i=0;i<ordinato.length-1;i++)
        {
            for (int j=i+1;j<ordinato.length;j++)
            {
                if (ordinato[j]<ordinato[i])
                    scambia(ordinato,i,j);
            }
        }
        return ordinato;
    }
    
     public static int[] selectionSortDecrescente(int[] a)
    {
        int[] ordinato=new int[a.length];
        
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        //Applico l'algoritmo 
        //selection sort al vettore di copia
        for (int i=0;i<ordinato.length-1;i++)
        {
            for (int j=i+1;j<ordinato.length;j++)
            {
                if (ordinato[j]>ordinato[i])
                    scambia(ordinato,i,j);
            }
        }
        return ordinato;
    }
}
