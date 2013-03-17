/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.mips16.core;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Akhil
 */
public class MyMap {
    
    public Map<String, Integer> registers ; //Stores Register addresses
    public Map<String, Integer> opcodes ;
    public Map<String, Integer> opcodes_tail ;
    public MyMap(){
        int i;
        registers=new HashMap<String,Integer>();
        opcodes=new HashMap<String,Integer>();
        opcodes_tail=new HashMap<String,Integer>();
        for(i=0;i<8;i++){
            registers.put("r"+i,i);
            registers.put("R"+i,i);
        }
        // Arithmetic Instructions
        opcodes.put("ADD",0);   opcodes_tail.put("ADD",0);
        opcodes.put("add",0);   opcodes_tail.put("add",0);
        opcodes.put("ADC",0);   opcodes_tail.put("ADC",1);
        opcodes.put("adc",0);   opcodes_tail.put("adc",1);
        opcodes.put("SUB",1);   opcodes_tail.put("SUB",0);
        opcodes.put("sub",1);   opcodes_tail.put("sub",0);
        opcodes.put("SBB",1);   opcodes_tail.put("SBB",1);
        opcodes.put("sbb",1);   opcodes_tail.put("sbb",1);
        //Logical Instructions
        opcodes.put("AND",2);   opcodes_tail.put("AND",0);   
        opcodes.put("and",2);   opcodes_tail.put("and",0);
        opcodes.put("OR",2);    opcodes_tail.put("OR",1);
        opcodes.put("or",2);    opcodes_tail.put("or",1);
        opcodes.put("NOT",2);   opcodes_tail.put("NOT",2);
        opcodes.put("not",2);   opcodes_tail.put("not",2);
        opcodes.put("XOR",2);   opcodes_tail.put("XOR",3);
        opcodes.put("xor",2);   opcodes_tail.put("xor",3);
        
        // Shift instructions
        
        opcodes.put("SHIFTL",3);   opcodes_tail.put("SHIFTL",0);
        opcodes.put("SHIFTR",3);   opcodes_tail.put("SHIFTR",1);
        
        //MOVE Instruction
        opcodes.put("MOV",4);
        
        opcodes.put("ADDI",5);
        opcodes.put("SUBI",6);
        opcodes.put("MVIH",8);
        opcodes.put("MVIH",9);
        opcodes.put("LDIDR",10);
        opcodes.put("STIDR",11);
        opcodes.put("STC",12);
        
        opcodes.put("PUSH",16);
        opcodes.put("JAL",17);
        opcodes.put("CALL",18);
        opcodes.put("POP",19);
        opcodes.put("RET",20);
        opcodes.put("IE",21);
        opcodes.put("ID",22);
        opcodes.put("JMP",23);
        opcodes.put("JMPI",24);
        opcodes.put("JGEO",25);
        opcodes.put("JLEO",26);
        opcodes.put("JCO",27);
        opcodes.put("RST",28);
        opcodes.put("HLT",29);
        opcodes.put("NOP",30);
        opcodes.put("MOVSP",31);
        opcodes.put("LDIDX",13);
        opcodes.put("STIDX",14);
        
        
        
    }
    
    
    
}
