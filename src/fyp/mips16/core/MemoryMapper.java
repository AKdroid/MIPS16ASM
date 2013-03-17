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
    
    private byte  memnc[];//,memc[];
    File outfile,infile;
    FileInputStream fin;
    FileWriter fout;
    
    public MemoryMapper(String outputdir,String filename){
        int i;
        //memc=new byte[65536];
        memnc=new byte[65536];
        for(i=0;i<65536;i++){
          //  memc[i]=0;
            memnc[i]=0;
        }
        setOutfile(outputdir,filename);
        try {
            fout=new FileWriter(outfile);
        } catch (IOException ex) {
            Logger.getLogger(MemoryMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int writeMemory(int address,byte value){
        if(address>=0&&address<65536){
        memnc[address]=value;
        return 1;}
        else return 0;
    }
    
    public byte readMemory(int address){
        if(address>=0&&address<65536)
            return memnc[address];
        else
            return -1;
    
    }
    
    public void dump(){
        int i=0;
        for(i=0;i<65536;i++){
            try {
                fout.write(new Byte(memnc[i]).toString());
                fout.write(' ');
            } catch (IOException ex) {
                Logger.getLogger(MemoryMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void setOutfile(String directory,String filename){
        outfile = new File(directory,filename);
        
    }
    public void setInfile(String directory,String filename){
        infile=new File(directory,filename);
    }
}
