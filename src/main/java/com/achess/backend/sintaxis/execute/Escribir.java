package com.achess.backend.sintaxis.execute;

/**
 *
 * @author achess
 */
public class Escribir extends Instruction{
    String text;
    public Escribir(String text){
        this.text = text;
    }

    @Override
    public String run() {
        return text;
    }

    @Override
    public String toString() {
        return "Escribir{" + "text=" + text + '}';
    }
    
    
}
