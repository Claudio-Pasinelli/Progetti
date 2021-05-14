/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import eccezioni.FileException;

/**
 *
 * @author User
 */
public class TextFile 
{
    private char mode;
    private BufferedReader reader;
    private BufferedWriter writer;
    
    public TextFile(String filename, char mode) throws FileNotFoundException, IOException
    {
        this.mode='R';  //come default apriamo il file in lettura
        boolean append=false; //come default mettiamo append=false
                        
        if (mode=='w' || mode=='W')
            this.mode='W';
        
        if (mode=='R')
             reader=new BufferedReader(new FileReader(filename));
        else
            writer=new BufferedWriter(new FileWriter(filename,append));
    
    }
    
    //costruttore che consente d inserire anche il parametro append
    public TextFile(String filename,char mode, boolean append) throws FileNotFoundException, IOException
    {
        this.mode='R';  //come default apriamo il file in lettura
                              
        if (mode=='w' || mode=='W')
            this.mode='W';
        
        if (mode=='R')
             reader=new BufferedReader(new FileReader(filename));
        else
            writer=new BufferedWriter(new FileWriter(filename,append));
    
    }
    
    public void toFile(String line) throws FileException, IOException
    {
        if (mode=='R')
            throw new FileException("Impossibile scrivere. File aperto in lettura!");
        writer.write(line);
        writer.newLine();
    }
    
    public String fromFile() throws FileException, IOException
    {
        String line=null;
        if (mode=='W')
            throw new FileException("Impossibile leggere da file. File aperto in scrittura!");
        
        line=reader.readLine();
        //se non viene letto nulla significa che non ci sono più righe da leggere nel file
        //sollevo l'eccezione che verrà utilizzata nel metodo chiamante per terminare il ciclo di lettura
        if (line==null)
            throw new FileException("Fine del file!");
        return line;
    }
    
    public void close() throws IOException
    {
        if (mode=='R')
            reader.close();
        else
            writer.close();
    } 
}
