package com.achess.backend;
/**
 *
 * @author achess
 */
public enum Alphabet {   
    
    CERO("0"), DIGITO("Dígito"), SEPARADOR("Separador"), 
    CHR_LITERAL("Signo de literal"), LETRA("Letra"), DESCONOCIDO("Desconocido");
   private String id;
   private Alphabet(String id){
       this.id = id;
   }
    
   public String getId(){
       return this.id;
   }
   
   public static String getAlpabhet(char chr){       
        int value = (int)chr;
        //Using ascii
        if(value == 32 || value == 10 || value == 9 || value == 13 ||value == 12){
            return SEPARADOR.getId();
        }
        else if(value >= 65 && value <= 90 || value >= 97 && value <= 122){
            return LETRA.getId();
        }        
        else if(chr == '0'){
            return CERO.getId();
        }
        else if(Character.isDigit(chr)){
            return DIGITO.getId();
        }
        else if(chr == '+' ||chr == '-' ||chr == '/' ||
                chr == '(' ||chr == ')' ||chr == '_' ||
                chr == '<' ||chr == '>' ||chr == '+' ||
                chr == '\''||chr == ':' ||chr == '.' ||
                chr == ',' ||chr == ';' ||chr == '%' ||
                chr == '='){
            return CHR_LITERAL.getId();
        }
        
        return DESCONOCIDO.getId();
   }
}
