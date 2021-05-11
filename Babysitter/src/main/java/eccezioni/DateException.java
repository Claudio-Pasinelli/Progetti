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
public class DateException extends Exception
{
    private String messaggio;

    public DateException(String messaggio)
    {
        this.messaggio=messaggio;
    }

    @Override
    public String toString()
    {
        return messaggio;
    }
}
