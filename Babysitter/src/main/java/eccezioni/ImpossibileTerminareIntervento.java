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
public class ImpossibileTerminareIntervento extends Exception
{
    private long id;
    private String motivoEccezione;
    public ImpossibileTerminareIntervento(long id, String motivoEccezione)
    {
        this.id=id;
        this.motivoEccezione=motivoEccezione;
    }
    public ImpossibileTerminareIntervento(String motivoEccezione)
    {
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
