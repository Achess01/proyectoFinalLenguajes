package com.achess.backend.sintaxis.execute;

import com.achess.backend.TokenType;
import java.util.ArrayList;

/**
 *
 * @author achess
 */
class Si extends Instruction{
    private ArrayList<Escribir> stmtEscribir;
    private String value;
    private boolean bool;
    public Si(String value){
        stmtEscribir = new ArrayList<Escribir>();
        this.value = value;
        this.bool = false;
        if(this.value.equals(TokenType.VERDADERO.toString())){
            this.bool = true;
        }
    }
    
    public void addEscribir(Escribir escribir){
        stmtEscribir.add(escribir);
    }

    @Override
    public String run() {
        String text = "";
        if(bool){
            for(Escribir e : stmtEscribir){
                text += e.run();
            }
        }        
        return text;
    }
}
