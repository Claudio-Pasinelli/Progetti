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
public class FileException extends Exception
{
    private String motivoEccezione;
    public FileException(String message)
    {
        motivoEccezione=message;
    }
    
    public String toString()
    {
        return motivoEccezione;
    }
}
