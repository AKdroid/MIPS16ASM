/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.mips16.core;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Akhil
 */
public class MemoryMapper {
    
    private int  memnc[];//,memc[];
    File infile,dumpFile;
    FileInputStream fin;
    FileWriter dumpout;
    
    public MemoryMapper(String outputdir,String filename){
        int i;
        //memc=new byte[65536];
        memnc=new int[65536];
        for(i=0;i<65536;i++){
          //  memc[i]=0;
            memnc[i]=0;
        }
        dumpFile=new File(outputdir,filename+".mmap");
        try {           
            dumpout=new FileWriter(dumpFile);
        } catch (IOException ex) {
            Logger.getLogger(MemoryMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int writeMemory(int address,int value){
        if(address>=0&&address<65536){
        memnc[address]=value;
        System.out.println("memnc["+address+"] =" + memnc[address]);
        return 1;}
        else return 0;
    }
    
    public int readMemory(int address){
        if(address>=0&&address<65536)
            return memnc[address];
        else
            return -1;
    
    }
    
    public void dump(){
        int i=0;
        
        try {
            dumpout=new FileWriter(dumpFile);
        for(i=0;i<65536;i++){
            
                dumpout.write(new Integer(memnc[i]).toString());
                dumpout.write(' ');
            } 
            dumpout.close();
        }
        catch (IOException ex) {
                Logger.getLogger(MemoryMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void setDumpfile(String directory,String filename){
        dumpFile = new File(directory,filename+".mmap");
        
    }
    public void setInfile(String directory,String filename){
        infile=new File(directory,filename);
    }
}
