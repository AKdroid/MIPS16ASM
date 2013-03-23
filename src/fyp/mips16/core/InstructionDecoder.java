/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.mips16.core;

import java.text.NumberFormat;
import java.text.ParsePosition;

/**
 *
 * @author Akhil
 */
public class InstructionDecoder {
 
    MyMap m;
    ErrorManager em; 
    public InstructionDecoder(ErrorManager _em){
        m=new MyMap();
        em= _em;
    }
    public int DecodeLine(String ln){
            
        String temp,opcde;
        String operands[]=new String[1];
        int x,value,NumOp,divider,p,op[],i;
        x=0;
        op=new int[3];
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
            if(NumOp==0)return x;
            //System.out.println("values= "+NumOp+" "+op[0]+" "+op[1]+" "+op[2]);
            if(NumOp>operands.length)
                return -3;
            if(NumOp<operands.length)
                return -4;
            while(NumOp>0 && i<3){
                
                if(op[i]==1){
                    NumOp--;
                    if(m.registers.containsKey(operands[p]))
                        x+=m.registers.get(operands[p])*Math.pow(2, 8-3*i);
                    else 
                        return -7;
                    p++;
                }
                
                else if(op[i]==2&& i== 2){
                    NumOp--;
                    
                    if(operands[p].charAt(0)=='#'){
                        temp=operands[p].substring(1);
                        value=NumberParser(temp);
                        if(value==-100000)return -100000;
                        else
                        x+=value%32;    
                    }else{
                        return -5;
                    }
                    p++;
                }else if(op[i]==3&&i==2){
                    NumOp--;
                    
                    if(operands[p].charAt(0)=='#'){
                        temp=operands[p].substring(1);
                        value=NumberParser(temp);
                        
                        if(value==-100000)return -100000;
                        else{
                        x+=value%32;
                        x+=((value/32)*256);
                        }    
                    }else{
                        return -5;
                    }
                    p++;
                }
                else if(op[i]==4 && i==2){
                    NumOp--;
                    
                    if(operands[p].charAt(0)=='#'){
                        temp=operands[p].substring(1);
                        value=NumberParser(temp);
                        if(value==-100000)return -100000;
                        else
                        x+=value%2048;    
                    }else{
                        return -5;
                    }
                    p++;
                }
                i++;
            }
            
            
        }else{
            return -2;
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
        int x=0;
        String temp="";
        if(str.length()>2)
        temp=str.substring(0,2);
       // System.out.println("temp parsing "+temp);
        if(isNumeric(str)){
                 try{
                    x=Integer.parseInt(str,10);
                }catch (NumberFormatException e){
                        return -100000;
                }
              
        }
        else if(temp.equals("0x"))
        {
            temp=str.substring(2);
            try{
                x=Integer.parseInt(temp,16);
            }catch (NumberFormatException e){
                     //  System.out.println("Temp="+temp); 
                       return -100000;
            }
            }else if(temp.equals("0b")){
                     temp=str.substring(2);
                     try{
                       x=Integer.parseInt(temp,2);
                     }catch (NumberFormatException e){
                         return -100000;
                     }
             }else if(temp.equals("0d")){
                temp=str.substring(2);
                try{
                    x=Integer.parseInt(temp,10);
                }catch (NumberFormatException e){
                        return -100000;
                }
                }
             
             else return -100000;  
        return x;
    }
}
