/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.mips16.core;

/**
 *
 * @author Akhil
 */
public class Error {
    
     int type,LineNo;
     String message;
     String extras;
     
     
     public Error(int _type,int _LineNo,int error_code,String _extras){
         type=_type;
         LineNo=_LineNo;
         ErrorManager em=new ErrorManager();
         if(em.ErrMessages.containsKey(error_code))
             message=em.ErrMessages.get(error_code);
         else
             message="Unknown Error/Warning";
         extras=_extras;
     }
     
     @Override
     public String toString(){
         String x="";
         if(type==0)
             x="Message:";
         else if(type==1)
             x="Warning:";
         else if(type==2)
             x="Error:";
         x+="Line "+LineNo;
         x+=message+" "+extras;
         return x;
     }
    
}
