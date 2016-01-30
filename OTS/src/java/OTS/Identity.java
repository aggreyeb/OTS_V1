/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS;

import java.util.Random;
import java.util.UUID;

/**
 *
 * @author MEA
 */
public final class Identity {
    public String ID;
     public Identity(UUID id){
        this.ID=id.toString();
    }
    
    public Identity (String id){
    
      this.ID=id;
    }
    public static Identity NewGiudIdentity(){
        return new Identity(UUID.randomUUID());
    }
    
    public static Identity NewIdentity(){
        return new Identity(GenerateUniqueKey(5));
    }
    public static String GenerateUniqueKey(int length){
         String Code = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
         Random rnd = new Random();
         StringBuilder sb = new StringBuilder( length );
       for( int i = 0; i < length; i++ ) 
        sb.append( Code.charAt( rnd.nextInt(Code.length()) ) );
        return sb.toString();
    }
    
    public static String FiveDigitKey(){
        
        return GenerateUniqueKey(5);
    }
    
    @Override
    public boolean equals(Object obj) {
       if (!(obj instanceof Identity))
            return false;
        if (obj == this)
            return true;
          return equals((Identity)obj);
    }

    @Override
    public  int hashCode(){
    
      return this.ID.hashCode();
    }
}
