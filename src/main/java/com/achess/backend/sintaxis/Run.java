package com.achess.backend.sintaxis;

import com.achess.backend.TokenType;
import com.achess.backend.sintaxis.execute.Escribir;
import com.achess.backend.sintaxis.execute.Instruction;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author achess
 */
public class Run {
    private static Run runCode;
    private HashMap<String, Integer> symbolTable;
    ArrayList<Instruction> instructions;
    public static Run getRunCode(){
        if(runCode == null){
            runCode = new Run();
        }
        return runCode;
    }
    
    private Run(){
        
    }
    
    public String run(DNode head){        
        String text = "";
        getInstructions(head);
        System.out.println(instructions);
        for(Instruction i : instructions){            
            text += i.run() + "\n";
            System.out.println(text);
        }
        return text;
    }
    
    private void getInstructions(DNode head){
        instructions = new ArrayList<Instruction>();        
        DNode actualNode, searchNode;
        actualNode = head;        
        searchNode = actualNode;
        while(searchNode != null){
            String symbol = searchNode.getSymbol();
            if(symbol.equals("E")){
                Escribir e = searchEscribir(searchNode);                       
                instructions.add(e);                                        
            }            
            searchNode = actualNode.getLeftMostNoTerminal();            
        }                      
    }
    
    public Escribir searchEscribir(DNode node){
        DNode aux = node.getLeftMostTerminal();
        TokenType tk = aux.getToken().getType();
        while(true){            
            if(tk == TokenType.IDENTIFICADOR ||
                tk == TokenType.NUMERO ||
                tk == TokenType.LITERAL){                
                break;
            }                        
            aux = node.getLeftMostTerminal();
            tk = aux.getToken().getType();
        }
        Escribir escribir = new Escribir(aux.getToken().getLexeme());
        return escribir;
    }
    
}
