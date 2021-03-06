package com.achess.backend.sintaxis;
import com.achess.backend.TokenType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author achess
 */
public class PDAState {
    private String[] freshProductions;
    private HashMap<String, StatePush> nextStates;
    private ArrayList<String> validInputs;
    private boolean endState;
    private boolean move;
    public PDAState(){
        nextStates = new HashMap<String, StatePush>();
        validInputs = new ArrayList<String>();
        endState = false;
    }

    public boolean move() {
        return move;
    }

    public String[] getFreshProductions() {
        return freshProductions;
    }
    
    
    
    public boolean isEndState() {
        return endState;
    }
   
    public void setEnd(){
        endState = true;
    }  
               
    /**
     * @param input input del para transición
     * @param stackPop El elemento que se busca en el tope de la pila
     * @param push Los elementos que ingresarán a la pila
     * @param state El estado de la transición
     *
    **/
    public void addTransition(String input, String stackPop,String push, PDAState state){
        String stackPush[] = push.split(",");        
        nextStates.put(getKey(input, stackPop), new StatePush(stackPush, state));
        addInput(input);
    }
    /**
     * @param input input del para transición
     * @param stackPop El elemento que se busca en el tope de la pila   
     * @param state El estado de la transición
     * Esta función crea una transición sin push
    **/
    public void addTransition(String input, String stackPop,PDAState state){
        String stackPush[] = {};
        nextStates.put(getKey(input, stackPop), new StatePush(stackPush, state));
        addInput(input);
    }
    
    
    /**
     * @param input input del para transición
     * @param stackPop El elemento que se busca en el tope de la pila   
     * @param state El estado de la transición
     * @param move Obtener siguiente token
     * Esta función crea una transición sin push
    **/
    public void addTransition(String input, String stackPop,PDAState state, boolean move){
        String stackPush[] = {};
        nextStates.put(getKey(input, stackPop), new StatePush(stackPush, state, move));
        addInput(input);
    }
    
    private void addInput(String input){
        if(!validInputs.contains(input)){
            validInputs.add(input);            
        }
    }
    
    private String getKey(String input, String stackPop){
        return input +","+stackPop;
    }
    
    private void pushElements(Stack<String> stack, String stackPush[]){        
        int len = stackPush.length;        
        for (int i = len -1; i >= 0; i--) {
            stack.push(stackPush[i]);            
        }        
    }
   
    public PDAState getState(String input, Stack<String> stack){        
        StatePush stateP = nextStates.get(getKey(input, TokenType.EPSILON.toString()));        
        if(stateP != null){
            this.move = stateP.move;
            pushElements(stack, stateP.stackPush);            
            return stateP.state;            
        }
        else{
            String top = stack.peek();
            stateP = nextStates.get(getKey(input, top));
            if(stateP != null){
                this.move = stateP.move;
                stack.pop();
                pushElements(stack, stateP.stackPush);
                this.freshProductions = stateP.stackPush;
                return stateP.state;
            }
        }                        
        return null;
    }
            
    private class StatePush{        
        private boolean move;
        private String stackPush[];
        private PDAState state;

        public StatePush(String stackPush[], PDAState state) {            
            this.move = false;
            this.stackPush = stackPush;
            this.state = state;            
        }                
        
        public StatePush(String stackPush[], PDAState state, boolean move) {            
            this.stackPush = stackPush;
            this.state = state;            
            this.move = move;
        }  
    }
    
    public String getValidInputs(){
        String text = "";
        for(String input : validInputs){
            text += input + ",";            
        }
        int last = text.lastIndexOf(",");
        if(last > 0){
           text = text.substring(0, last);
        }        
        return text;
    }
}
