package com.achess.backend.sintaxis.execute;

import com.achess.backend.TokenType;

/**
 *
 * @author achess
 */
public class Escribir extends Instruction{
    String type;
    String text;
    public Escribir(String text, String type){
        this.text = text;
        this.type = type;
    }

    @Override
    public String run() {
        if(type.equals(TokenType.IDENTIFICADOR.toString())){
            String val = String.valueOf(Run.symbolTable.get(text));
            return val;
        }
        return text;
    }

    @Override
    public String toString() {
        return "Escribir{" + "text=" + text + '}';
    }
    
    
}
