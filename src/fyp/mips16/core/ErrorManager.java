/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.mips16.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides Methods for Generating Error and Warning Messages
 * 
 * @author Akhil
 */
public class ErrorManager {
    
    
    public static int INVALID_OPCODE=-2;
    public static int LESS_OPERANDS=-3;
    public static int MORE_OPERANDS=-4;
    public static int UNKNOWN_IDENTIFIER=-5;
    public static int INVALID_IMMEDIATE=-7;
    public static int INVALID_NUMERAL=-100000;
    
    public static int NUMERAL_OVERFLOW=-100001;
    public static int MEMORY_CODE_OVERLAP=-100002;
    
    public static int MESSAGE_ASSEMBLING=-100003;
    public static int MESSAGE_ASSEMBLING_FAILED=-100004;
    public static int MESSAGE_GENERATING_DUMP=-100005;
    public static int MESSAGE_SAVING_ASM=-100006;
    public static int MESSAGE_SUCCESS=-1000007;
    
    
    public Map<Integer,String> ErrMessages;
    public ArrayList<Error> messagequeue;
    public int errcnt=0,wrncnt=0;
    
    public ErrorManager(){
        ErrMessages=new HashMap<Integer,String>();
        messagequeue=new ArrayList<Error>();
        // Error Messages
        ErrMessages.put(INVALID_OPCODE, "Unknown Opcode");
        ErrMessages.put(LESS_OPERANDS, "Opcode requires more operands");
        ErrMessages.put(MORE_OPERANDS, "Opcode requires less operands");
        ErrMessages.put(UNKNOWN_IDENTIFIER, "Unknown Identifier");
        ErrMessages.put(INVALID_IMMEDIATE, "Immediate value should begin with #");
        ErrMessages.put(INVALID_NUMERAL, "Invalid Numeric value");
        //Warning Messages
        ErrMessages.put(NUMERAL_OVERFLOW,"Exceeds range");
        ErrMessages.put(MEMORY_CODE_OVERLAP , "Part of memory and code will be overwritten");
        // Log Messages
        ErrMessages.put(MESSAGE_ASSEMBLING,"Assembling and generating machine code for");
        ErrMessages.put(MESSAGE_ASSEMBLING_FAILED," There were errors.Assembling failed");
        ErrMessages.put(MESSAGE_GENERATING_DUMP,"Generating memory map dump file ");
        ErrMessages.put(MESSAGE_SAVING_ASM,"Saving the assembly code ");
        ErrMessages.put(MESSAGE_SUCCESS,"Assembly Successful");
    }
    public void add_message(int type,int Line_no,int error_code,String extras){
        if(type==1)wrncnt++;
        if(type==2)errcnt++;
        messagequeue.add(new Error(type,Line_no,error_code,extras));
    }
    public void clear(){
        wrncnt=0;
        errcnt=0;
        messagequeue.clear();
    }

    
}
