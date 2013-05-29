/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.mips16.core;

import fyp.mips16.graphics.MIPS16Window;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Akhil
 */
public class InstructionDecoder {
 
    MyMap m;
    Map <String,Integer> Labels;
    public InstructionDecoder(){
        m=new MyMap();
        //Labels=new HashMap<String,Integer>();
        
    }
    public void SetMap(MyMap m_){
        m=m_;
    }
    //Decodes Line By Line
    public int DecodeLine(String ln,int lnno,int address){
            
        String temp,opcde;
        String operands[]=new String[1];
        int x,value,NumOp,divider,p,op[],i;
        x=0; //machine code
        op=new int[3]; //stores operands
        ln=ln.trim();
        if(ln.length()==0)return -1;        //ignore empty lines
        if(ln.contains(" "))
        opcde=ln.substring(0, ln.indexOf(' ')).trim().toUpperCase();
        else
            opcde=ln.toUpperCase();
       // System.out.println("opcde="+opcde+"\n");
        
        
        
        if(m.opcodes.containsKey(opcde)){   //check for invalid opcodes
            value=m.opcodes.get(opcde);
            x+=(value%100)*2048;            //assign opcode value.
            
            //opcode value
            if(m.opcodes_tail.containsKey(opcde))       
            x+=m.opcodes_tail.get(opcde);               // assign opcode trailer value
            //separate operand part
            
            value=value/100;
            divider=1000;
            //Obtain number of parameters and the operand instruction word attributes
            NumOp=value/1000;
            value=value%1000;
            if(NumOp>0){
            temp=ln.substring(ln.indexOf(' '));
            temp=temp.trim();
            operands=temp.split(",");
            //get individual operands and remove leading and trailing whitespaces.
            for(i=0;i<operands.length;i++){
                operands[i]=operands[i].trim();
                //System.out.println("operands " + i + operands[i]);
            }
            }
            divider=100;
            for(i=0;i<3;i++){
                op[i]=value/divider;
                value=value%divider;
                divider=divider/10;
            }
            i=p=0;
            // Check for different errors and warnings 
            //if found add it to the ErrorManager Queue
            if(NumOp==0)return x;
            System.out.println("values= "+NumOp+" "+operands.length);
            if(NumOp>operands.length){
                MIPS16Window.em.add_message(2,lnno , ErrorManager.LESS_OPERANDS, "opcode="+opcde+" required="+NumOp+" provided="+operands.length);
                return ErrorManager.LESS_OPERANDS;
            }
                
            if(NumOp<operands.length){
                MIPS16Window.em.add_message(2,lnno , ErrorManager.MORE_OPERANDS, "opcode="+opcde+" required="+NumOp+" provided="+operands.length);
                return ErrorManager.MORE_OPERANDS;
            }
                
            while(NumOp>0 && i<3){
                
                if(op[i]==1){
                    NumOp--;
                    if(m.registers.containsKey(operands[p]))
                        x+=m.registers.get(operands[p])*Math.pow(2, 8-3*i);
                    else{
                        MIPS16Window.em.add_message(2, lnno, ErrorManager.UNKNOWN_IDENTIFIER, operands[p]);
                        return ErrorManager.UNKNOWN_IDENTIFIER;
                    }
                    p++;
                }
                
                else if(op[i]==2&& i== 2){
                    NumOp--;
                    
                    if(operands[p].charAt(0)=='#'){
                        temp=operands[p].substring(1);
                        value=NumberParser(temp);
                        if(value==ErrorManager.INVALID_NUMERAL){
                            MIPS16Window.em.add_message(2, lnno,ErrorManager.INVALID_NUMERAL ,operands[p]);
                            return ErrorManager.INVALID_NUMERAL;
                        }
                        else{
                            if(value>32){
                                MIPS16Window.em.add_message(1,i+1,ErrorManager.NUMERAL_OVERFLOW,operands[p]);
                            }
                        if(value<0)value=32+value%17;    
                        x+=value%32; 
                        }   
                    }else{
                        if(m.Labels.containsKey(operands[p].toUpperCase())){
                            value=CalculateOffset(address,m.Labels.get(operands[p].toUpperCase()));
                            if(value<0)value=32+value%17;    
                            x+=value%32;
                        }
                        else{
                        MIPS16Window.em.add_message(2, lnno, ErrorManager.INVALID_IMMEDIATE, operands[p]);
                        return ErrorManager.INVALID_IMMEDIATE;
                        }
                    }
                    p++;
                }else if(op[i]==3&&i==2){
                    NumOp--;
                    
                    if(operands[p].charAt(0)=='#'){
                        temp=operands[p].substring(1);
                        value=NumberParser(temp);
                        
                        if(value==ErrorManager.INVALID_NUMERAL){
                            MIPS16Window.em.add_message(2, lnno,ErrorManager.INVALID_NUMERAL ,operands[p]);
                            return ErrorManager.INVALID_NUMERAL;
                        }
                        else{
                        if(value>255){
                                MIPS16Window.em.add_message(1,i+1,ErrorManager.NUMERAL_OVERFLOW,operands[p]);
                            } 
                        if(value<0)value=256+value%129;
                        x+=value%32;
                        x+=((value/32)*256);
                        }    
                    }else{
                        MIPS16Window.em.add_message(2, lnno, ErrorManager.INVALID_IMMEDIATE, operands[p]);   
                        return ErrorManager.INVALID_IMMEDIATE;
                    }
                    p++;
                }
                else if(op[i]==4 && i==2){
                    NumOp--;
                    
                    if(operands[p].charAt(0)=='#'){
                        temp=operands[p].substring(1);
                        value=NumberParser(temp);
                        if(value==ErrorManager.INVALID_NUMERAL)
                        {
                            MIPS16Window.em.add_message(2, lnno,ErrorManager.INVALID_NUMERAL ,operands[p]);
                            return ErrorManager.INVALID_NUMERAL;
                        }
                        else{
                            if(value>2047){
                                MIPS16Window.em.add_message(1,i+1,ErrorManager.NUMERAL_OVERFLOW,operands[p]);
                            }
                        if(value<0)value=2048+value%1025;    
                        x+=value%2048;
                        }
                    }else{
                        if(m.Labels.containsKey(operands[p].toUpperCase())){
                            value=CalculateOffset(address,m.Labels.get(operands[p].toUpperCase()));
                            if(value<0)value=2048+value%1025;    
                                x+=value%2048;
                        }
                        else{
                        MIPS16Window.em.add_message(2, lnno, ErrorManager.INVALID_IMMEDIATE, operands[p]);
                        return ErrorManager.INVALID_IMMEDIATE;
                        }
                    }
                    p++;
                }
                i++;
            }
            
            
        }else{
            MIPS16Window.em.add_message(2,lnno , ErrorManager.INVALID_OPCODE, opcde);
            return ErrorManager.INVALID_OPCODE;
        }
        
        
        return x;
    }
    public static boolean isNumeric(String str)
    {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }
    public int NumberParser(String str){
        // Parses valid number for different formats from Strings 
        // used for parsing numerical arguments like immediate values and 
        // address jumps and memory initializations
        int x=0;
        String temp="";
        if(str.length()>2)
        temp=str.substring(0,2);
       // System.out.println("temp parsing "+temp);
        if(isNumeric(str)){
                 try{
                    x=Integer.parseInt(str,10);
                }catch (NumberFormatException e){
                        return ErrorManager.INVALID_NUMERAL;
                }
              
        }
        else if(temp.equals("0x"))
        {
            temp=str.substring(2);
            try{
                x=Integer.parseInt(temp,16);
            }catch (NumberFormatException e){
                     //  System.out.println("Temp="+temp); 
                       return ErrorManager.INVALID_NUMERAL;
            }
            }else if(temp.equals("0b")){
                     temp=str.substring(2);
                     try{
                       x=Integer.parseInt(temp,2);
                     }catch (NumberFormatException e){
                         return ErrorManager.INVALID_NUMERAL;
                     }
             }else if(temp.equals("0d")){
                temp=str.substring(2);
                try{
                    x=Integer.parseInt(temp,10);
                }catch (NumberFormatException e){
                        return ErrorManager.INVALID_NUMERAL;
                }
                }
             
             else return ErrorManager.INVALID_NUMERAL; 
             
        return x; //return the 16 bit instruction word
    }
    public int CalculateOffset(int oldaddress,int newaddress){
        //Calculates offset in case of labels
        int x=0;
        x=(newaddress-oldaddress)/2-1;
        return x;
    }
    
}
