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
public class InterventiNonTrovati extends Exception{
    public InterventiNonTrovati() {
    }
    @Override
    public String toString() {
        String attaccato="Non è stato trovato nessun intervento su cui compiere l'azione.";
        return attaccato;
    }
}
