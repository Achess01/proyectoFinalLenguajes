package com.achess.backend.sintaxis.execute;

import com.achess.backend.TokenType;
import java.util.ArrayList;

/**
 *
 * @author achess
 */
class Repetir extends Instruction{
    private ArrayList<Escribir> stmtEscribir;
    private String rangeType;
    private String range;
    private int numericRange;
    
    public Repetir(String rangeType, String range){
        this.rangeType = rangeType;
        this.range = range;
        if(rangeType.equals(TokenType.IDENTIFICADOR.toString())){
            Integer r = Run.symbolTable.get(range);
            numericRange = r != null ? Math.abs(r) : 0;
        }else{
            numericRange = Integer.parseInt(range);
        }
        
        stmtEscribir = new ArrayList<Escribir>();
    }
    
    public void addEscribir(Escribir escribir){
        stmtEscribir.add(escribir);
    }
    
    
    @Override
    public String run() {
        String text = "";
        for (int i = 0; i < numericRange; i++) {            
            for(Escribir e : stmtEscribir){
                text += e.run();
            }
        }
        
        return text;
    }
    
}
