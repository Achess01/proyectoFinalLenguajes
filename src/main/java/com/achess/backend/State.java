package com.achess.backend;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author achess
 */
public class State{
    private boolean acceptState;    
    private boolean finalState = false;
    private TokenType tokenType;    
    HashMap<String,State> nextStates = new HashMap<String, State>();
    ArrayList<String> nextAlphabet = new ArrayList();        
    
    public State() {        
        this.acceptState = false;        
        this.tokenType = TokenType.ERROR;
    }
    
    public State(TokenType tokenType){                
        this.acceptState = true;
        this.tokenType = tokenType;
    }

   
    public void addNext(Alphabet alphabet, State state){
        nextStates.put(alphabet.getId(), state);
        nextAlphabet.add(alphabet.getId());
    }
    
    public void addNext(String key, State state){
        nextStates.put(key, state); 
        nextAlphabet.add(key);
    }
    
    public State getNext(char character){        
        State st =  nextStates.get(String.valueOf(character));
        if(st != null){
            return st;
        }
        String alp = Alphabet.getAlpabhet(character);
        st = nextStates.get(alp);        
        return st;
    }
    
    public boolean isAcceptState(){
        return this.acceptState;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public boolean isFinalState() {
        return finalState;
    }

    public void setFinalState(boolean finalState) {
        this.finalState = finalState;
    }
    
        
    
    public String nextValues(){
        String sig = "Se esperaba ";
        for(String alphabet : nextAlphabet){
            sig += alphabet + ", ";
        }
        int last = sig.lastIndexOf(",");
        if(last > 0){
            sig = sig.substring(0, last - 1);
        }
        return sig;
    }
}
