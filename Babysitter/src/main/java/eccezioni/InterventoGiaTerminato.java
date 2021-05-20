/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eccezioni;

/**
 *
 * @author Claudio Pasinelli
 */
public class InterventoGiaTerminato extends Exception
{
    private long id;
    private String motivoEccezione;
    public InterventoGiaTerminato(long id, String motivoEccezione)
    {
        this.id=id;
        this.motivoEccezione=motivoEccezione;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return motivoEccezione;
    }
    
}
