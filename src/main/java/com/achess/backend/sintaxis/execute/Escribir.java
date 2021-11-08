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
        String text = "\n";
        if(type.equals(TokenType.IDENTIFICADOR.toString())){
            String val = String.valueOf(Run.symbolTable.get(this.value));
            text += val;
        }
        else{
            text += this.value;
        }
        
        return text;
    }

    @Override
    public String toString() {
        return "Escribir{" + "text=" + value + '}';
    }
    
    
}
