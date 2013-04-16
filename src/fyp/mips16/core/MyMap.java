/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.mips16.core;

import java.util.*;

/**
 * Stores opcodes for proper mnemonics with their Format Encoding.
 * Format of the opcode value is no. of operands(1 digit) the location of operands (3 digits) and the actual opcode (2 digits).
 * @author Akhil
 */
public class MyMap {
    
    public Map<String, Integer> registers ; //Stores Register addresses
    public Map<String, Integer> opcodes ;
    public Map<String, Integer> opcodes_tail ;
    public Map<String,Integer> Labels; 
    public MyMap(){
        int i;
        registers=new HashMap<String,Integer>();
        opcodes=new HashMap<String,Integer>();
        opcodes_tail=new HashMap<String,Integer>();
        Labels=new HashMap<String,Integer>();
        for(i=0;i<8;i++){
            registers.put("r"+i,i);
            registers.put("R"+i,i);
        }
        // Arithmetic Instructions
        opcodes.put("ADD",311100);   opcodes_tail.put("ADD",0);
        opcodes.put("add",311100);   opcodes_tail.put("add",0);
        opcodes.put("ADC",311100);   opcodes_tail.put("ADC",1);
        opcodes.put("adc",311100);   opcodes_tail.put("adc",1);
        opcodes.put("SUB",311101);   opcodes_tail.put("SUB",0);
        opcodes.put("sub",311101);   opcodes_tail.put("sub",0);
        opcodes.put("SBB",311101);   opcodes_tail.put("SBB",1);
        opcodes.put("sbb",311101);   opcodes_tail.put("sbb",1);
        //Logical Instructions
        opcodes.put("AND",311102);   opcodes_tail.put("AND",0);   
        opcodes.put("and",311102);   opcodes_tail.put("and",0);
        opcodes.put("OR",311102);    opcodes_tail.put("OR",1);
        opcodes.put("or",311102);    opcodes_tail.put("or",1);
        opcodes.put("NOT",210102);   opcodes_tail.put("NOT",2);
        opcodes.put("not",210102);   opcodes_tail.put("not",2);
        opcodes.put("XOR",311102);   opcodes_tail.put("XOR",3);
        opcodes.put("xor",311102);   opcodes_tail.put("xor",3);
        
        // Shift instructions
        
        opcodes.put("SHIFTL",210103);   opcodes_tail.put("SHIFTL",0);
        opcodes.put("SHIFTR",210103);   opcodes_tail.put("SHIFTR",1);
        
        //MOVE Instruction
        opcodes.put("MOV",201104);
        
        opcodes.put("ADDI",311205);
        opcodes.put("SUBI",311206);
        opcodes.put("MOVSP",110007);
        opcodes.put("MVIL",201308);
        opcodes.put("MVIH",201309);
        opcodes.put("LDIDR",311210);
        opcodes.put("STIDR",311211);
        opcodes.put("STC",12);
        opcodes.put("CLC",13);
        opcodes.put("PUSH",101016);
        opcodes.put("JAL",100417);
        opcodes.put("CALL",101018);
        opcodes.put("POP",101019);  //opcodes_tail.put("POP",1);
        opcodes.put("RET",20);      //opcodes_tail.put("RET",1);
        opcodes.put("IE",21);
        opcodes.put("ID",22);
        opcodes.put("JMP",100423);
        opcodes.put("JMPI",210224);
        opcodes.put("JGEO",311227);
        opcodes.put("JLEO",311226);
        opcodes.put("JCO",100228);
        opcodes.put("JEO",311225);
        opcodes.put("HLT",29);  //opcodes_tail.put("HLT",0);
        opcodes.put("NOP",30);
        
        //opcodes.put("LDIDX",311113);
        //opcodes.put("STIDX",311114);
        opcodes.put("RST",31); // opcodes_tail.put("RST",1);
        
        
    }
    
    
    public void clearLabels(){
          Labels.clear();  
    }
    
    public String[] ExtractLabels(String code,int address){
        String lines[],separated[];
        int pc=address,i,j=0;
        InstructionDecoder dec=new InstructionDecoder();
        ArrayList<String> filtered=new ArrayList<String>();
        lines=code.split("\n");
        //filtered=new String[lines.length];
        Labels.clear();
        for(i=0;i<lines.length;i++){
            separated=lines[i].split("//");     //separate comments
            separated[0]=separated[0].trim();
            if(separated[0].length()==0)
                continue;
            if(separated[0].length()>1 && separated[0].charAt(0)=='@'){
                pc=dec.NumberParser(separated[0].substring(1));
                if(pc==ErrorManager.INVALID_NUMERAL)
                    return null;
                
            }
            else if(separated[0].contains(":")){
                separated=separated[0].split(":");
                Labels.put(separated[0].toUpperCase().trim(),pc);
                if(separated.length==2)
                {
                    filtered.add(separated[1].trim());
                    pc+=2;
                }
            }else{
                filtered.add(separated[0]);
                pc+=2;
            }
        }
        Iterator iterator = Labels.keySet().iterator();  
   
        while (iterator.hasNext()) {  
            String key = iterator.next().toString();  
            String value = Labels.get(key).toString();  
            System.out.println(key + " " + value);  
        }
        String filteredarray[]=new String[filtered.size()];
        for(j=0;j<filtered.size();j++){
            filteredarray[j]=filtered.get(j);
            System.out.println(filtered.get(j));
        }
        return filteredarray;
    }
    
    
}
