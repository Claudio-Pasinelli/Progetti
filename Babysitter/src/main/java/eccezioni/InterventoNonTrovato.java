/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eccezioni;

/**
 *
 * @author Pc
 */
public class InterventoNonTrovato extends Exception
{
    private long idIntervento;
    public InterventoNonTrovato(long idIntervento)
    {
        this.idIntervento=idIntervento;
    }

    public long getIdIntervento() {
        return idIntervento;
    }
    public String toString()
    {
        String attaccato="";
        attaccato+="L'intervento con codice: "+getIdIntervento()+" non è stato trovato.";
        return attaccato;
    }
}
