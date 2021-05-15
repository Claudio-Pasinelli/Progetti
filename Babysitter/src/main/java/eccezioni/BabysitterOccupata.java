/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eccezioni;

import com.mycompany.babysitter.Babysitter;
import java.time.LocalDateTime;

/**
 *
 * @author Pc
 */
public class BabysitterOccupata extends Exception
{
    private Babysitter babysitter;
    LocalDateTime inizio;
    
    public BabysitterOccupata(Babysitter babysitter, LocalDateTime inizio)
    {
        this.babysitter=babysitter;
        this.inizio=inizio;
    }
    @Override
    public String toString()
    {
        int anno, mese, giorno,ora,minuti;
        anno=inizio.getYear();
        mese=inizio.getMonthValue();
        giorno=inizio.getDayOfMonth();
        ora=inizio.getHour();
        minuti=inizio.getMinute();
        return "\nLa babysitter \""+babysitter.toString()+"\" è già occupata in data: "+giorno+"/"+mese+"/"+anno+" - "+ora+":"+minuti+"\n";
    }
}
