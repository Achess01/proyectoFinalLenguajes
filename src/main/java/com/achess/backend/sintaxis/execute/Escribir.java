package com.achess.backend.sintaxis.execute;

import com.achess.backend.TokenType;

/**
 *
 * @author achess
 */
public class Escribir extends Instruction{
    String type;
    String value;

    public Escribir(String value, String type){
        this.value = value;
        this.type = type;
    }

    @Override
    public String run() {
        String text = "";
        if(type.equals(TokenType.IDENTIFICADOR.toString())){
            Integer id = Run.symbolTable.get(this.value);
            String val = id != null ? String.valueOf(id) : "";            
            if(val.equals("")){
                return "";
            }
            text += val;
        }
        else{
            text += this.value;
        }
        
        return text + "\n";
    }

    @Override
    public String toString() {
        return "Escribir{" + "text=" + value + '}';
    }
    
    
}
