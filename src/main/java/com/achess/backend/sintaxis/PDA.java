package com.achess.backend.sintaxis;
import com.achess.backend.Token;
import com.achess.backend.TokenType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author achess
 */
public class PDA {    
    private static PDA automaton;
    private String error;    
    private HashMap<String, Integer> symbolTable;
    ArrayList<Token> tokens;
    private PDAState q0;
    private DNode head;
    private PDA(){
        head = new DNode("S", null);
        error = "";
        q0 = new PDAState();
        PDAState q1 = new PDAState();
        PDAState q2 = new PDAState();
        PDAState q3 = new PDAState();        
        q3.setEnd();                
        q0.addTransition(TokenType.EPSILON.toString(), TokenType.EPSILON.toString(), TokenType.$.toString(), q1);
        
        q1.addTransition(TokenType.EPSILON.toString(), TokenType.EPSILON.toString(), "S", q2);
        
        
        for(TokenType tk: TokenType.values()){
            if(!tk.equals(TokenType.$) && !tk.equals(TokenType.EPSILON) 
                    && !tk.equals(TokenType.COMENTARIO) && !tk.equals(TokenType.ERROR)){
                q2.addTransition(tk.toString(), tk.toString(), q2, true);
            }
        }
        q2.addTransition(TokenType.$.toString(), TokenType.$.toString(), q3, true);        
        
        q2.addTransition(TokenType.ESCRIBIR.toString(),"S","E,S", q2);
        q2.addTransition(TokenType.IDENTIFICADOR.toString(),"S","A,S", q2);
        q2.addTransition(TokenType.REPETIR.toString(),"S","R,S", q2);
        q2.addTransition(TokenType.SI.toString(),"S","C,S", q2);
        q2.addTransition(TokenType.$.toString(),"S", q2);
        
        String escribir = TokenType.ESCRIBIR.toString()+",IP," + TokenType.FIN.toString();
        q2.addTransition(TokenType.ESCRIBIR.toString(),"E",escribir, q2);
        
        q2.addTransition(TokenType.LITERAL.toString(),"IP",TokenType.LITERAL.toString(), q2);
        q2.addTransition(TokenType.NUMERO.toString(),"IP","I", q2);
        q2.addTransition(TokenType.IDENTIFICADOR.toString(),"IP","I", q2);
        
        q2.addTransition(TokenType.NUMERO.toString(),"I",TokenType.NUMERO.toString(), q2);
        q2.addTransition(TokenType.IDENTIFICADOR.toString(),"I",TokenType.IDENTIFICADOR.toString(), q2);
        
        String repetir = TokenType.REPETIR.toString() +",I,"+ TokenType.INICIAR.toString() +",EP,"+ TokenType.FIN.toString();        
        q2.addTransition(TokenType.REPETIR.toString(),"R",repetir, q2);
        
        q2.addTransition(TokenType.ESCRIBIR.toString(),"EP","E,EP", q2);
        q2.addTransition(TokenType.FIN.toString(),"EP", q2);
        
        String si = TokenType.SI.toString() +",V,"+ TokenType.ENTONCES.toString() +",EP,"+ TokenType.FIN.toString();
        q2.addTransition(TokenType.SI.toString(),"C",si, q2);
        
        q2.addTransition(TokenType.VERDADERO.toString(),"V",TokenType.VERDADERO.toString(), q2);
        q2.addTransition(TokenType.FALSO.toString(),"V",TokenType.FALSO.toString(), q2);
        
        q2.addTransition(TokenType.NUMERO.toString(),"O","B,OP", q2);
        q2.addTransition(TokenType.IDENTIFICADOR.toString(),"O","B,OP", q2);
        q2.addTransition(TokenType.PARENTESIS_A.toString(),"O","B,OP", q2);
        
        q2.addTransition(TokenType.FIN.toString(),"OP", q2);
        q2.addTransition(TokenType.PARENTESIS_C.toString(),"OP", q2);
        String suma = TokenType.SUMA.toString() + ",B,OP";
        q2.addTransition(TokenType.SUMA.toString(),"OP",suma, q2);
        
        q2.addTransition(TokenType.NUMERO.toString(),"B", "D,BP", q2);
        q2.addTransition(TokenType.IDENTIFICADOR.toString(),"B", "D,BP", q2);
        q2.addTransition(TokenType.PARENTESIS_A.toString(),"B", "D,BP", q2);
        
        q2.addTransition(TokenType.FIN.toString(),"BP", q2);
        q2.addTransition(TokenType.SUMA.toString(),"BP", q2);
        q2.addTransition(TokenType.PARENTESIS_C.toString(),"BP", q2);
        String multiplicacion = TokenType.MULTIPLICACION.toString() + ",D,BP";
        q2.addTransition(TokenType.MULTIPLICACION.toString(),"BP", multiplicacion, q2);
                
        q2.addTransition(TokenType.NUMERO.toString(),"D", "I", q2);
        q2.addTransition(TokenType.IDENTIFICADOR.toString(),"D", "I", q2);
        String parentesis = TokenType.PARENTESIS_A + ",O,"+ TokenType.PARENTESIS_C;
        q2.addTransition(TokenType.PARENTESIS_A.toString(),"D", parentesis, q2);
        
        String asignar = TokenType.IDENTIFICADOR + ","+TokenType.ASIGNACION.toString()+",O,"+TokenType.FIN.toString();
        q2.addTransition(TokenType.IDENTIFICADOR.toString(),"A", asignar, q2);
    }
           
    public static PDA getAutomaton(ArrayList<Token> tokens){
        if(automaton == null){
            automaton = new PDA();
        }
        automaton.setTokens(tokens);
        return automaton;
    }
    
    public void printTree(){
        System.out.println("---------------");
        System.out.println(head);
        head.print();
        System.out.println("---------------");
    }
    private void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;        
        this.tokens.add(new Token(TokenType.$, "$", 0,0,0));
        symbolTable = new HashMap<String, Integer>();
    }        
        
    public String getError() {
        return error;
    }
                    
    public void analize(){                        
        head = new DNode("S", null);
        Stack<String> stack = new Stack<String>();        
        error = "";
        int index = 0;
        PDAState aux, aux1;
        DNode treeAux, treeAux1;
        treeAux = head;        
        aux = q0;        
        while(index < tokens.size()){
            aux1 = aux.getState(TokenType.EPSILON.toString(), stack);
            if(aux1 == null){
                Token tk = tokens.get(index);                
                aux1 = aux.getState(tk.getType().toString(), stack);                                
                if(aux1 == null){
                    //String valid = aux.getValidInputs();
                    tk = tokens.get(index - 1);
                    error = "Error ln:"  + tk.getRow() + " col:"+tk.getColumn()+" "+tk.getLexeme(); //+"\nSe esperaba " + valid;
                    break;
                }
                else if(aux1.move()){
                    index++;
                    treeAux.setToken(tk);                    
                }else{
                    treeAux.addChildren(aux.getFreshProductions());
                }
                
                
                if(!stack.empty()){
                    String top = stack.peek();
                    treeAux1 = treeAux;
                    while(treeAux1 != null){
                        if(treeAux1.getNode(top) != null){
                            treeAux = treeAux1.getNode(top);
                            //System.out.println(top);
                            break;
                        }
                        treeAux1 = treeAux1.getFather();
                    }
                }
                
                
            }
            if(stack.empty()){                            
                break;
            }else{
                //System.out.println(stack);
            }
            
            
            /*
            if(index == tokens.size()){                
                if(!aux1.isEndState()){
                    Token tk = tokens.get(index - 2);
                    //String valid = aux.getValidInputs();
                    error = "Error ln:"  + tk.getRow() + " col:"+tk.getColumn() + " "+ tk.getLexeme();//+"\nSe esperaba " + valid;
                    break;
                }
            }
            */
           aux = aux1;
        }        
        runCode();
    }
    
    public void runCode(){
        
    }
}
