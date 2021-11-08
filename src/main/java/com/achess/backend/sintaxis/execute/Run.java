package com.achess.backend.sintaxis.execute;

import com.achess.backend.TokenType;
import com.achess.backend.sintaxis.DNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author achess
 */
public class Run {
    private static Run runCode;
    public static HashMap<String, Integer> symbolTable;
    private ArrayList<Instruction> instructions;
    public static Run getRunCode(){
        if(runCode == null){
            runCode = new Run();
        }
        return runCode;
    }
    
    private Run(){
        
    }
    
    public String run(DNode head){
        symbolTable = new HashMap<String, Integer>();                    
        String text = "";
        getInstructions(head);       
        for(Instruction i : instructions){            
            text += i.run() + "\n";            
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
            else if(symbol.equals("A")){
                Asignar a = searchAsignar(searchNode);
                symbolTable.put(a.getId(),a.getValue());
            }
            searchNode = actualNode.getLeftMostNoTerminal();            
        }                      
    }
    
    private Asignar searchAsignar(DNode node){
        DNode aux = node.getLeftMostChild();        
        TokenType tk = aux.getToken().getType();
        Asignar asign = new Asignar(aux.getToken().getLexeme());        
        aux = node.getLeftMostChild();        
        aux = node.getLeftMostChild();        
        Operation o = searchOperation(aux);
        //System.out.println(o);
        Integer val = Integer.parseInt(o.run());
        asign.setValue(val);
        return asign;
    }    
    
    private Operation searchOperation(DNode node){        
        DNode aux;              
        aux = node.getLeftMostChild();
        Operation opAuxLeft = null, opAuxRight = null;
        String op = "";        
        while(aux != null){
            String symbol = aux.getSymbol();
            System.out.println(symbol);
            if(symbol.equals("O") ||symbol.equals("B") || symbol.equals("D") || symbol.equals("I")){                
                opAuxLeft = searchOperation(aux);
                if(op != "" && opAuxLeft != null){
                    opAuxLeft = new Operation(op, null, opAuxLeft);
                }
            }
            else if(symbol.equals("OP") ||symbol.equals("BP")){
                opAuxRight = searchOperation(aux);
            }
            else if (symbol.equals(TokenType.NUMERO.toString()) || symbol.equals(TokenType.IDENTIFICADOR.toString())){
                System.out.println(aux.getToken().getLexeme());
                return new Operation(symbol, aux.getToken().getLexeme());
            }
            else if(symbol.equals(TokenType.SUMA.toString()) || symbol.equals(TokenType.MULTIPLICACION.toString())){
                op = symbol;                
            }            
            aux = node.getLeftMostChild();            
        }
        
        if(opAuxLeft != null){            
            if(opAuxRight != null){
                //if(!opAuxRight.getType().equals(TokenType.NUMERO.toString())  || !opAuxRight.getType().equals(TokenType.IDENTIFICADOR.toString())){
                opAuxRight.setLeft(opAuxLeft);
                return opAuxRight;
                //}
                //return new Operation(op, opAuxLeft, opAuxRight);
            }
            return opAuxLeft;
        }                            
        return null;
    }
    
    private Escribir searchEscribir(DNode node){
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
        Escribir escribir = new Escribir(aux.getToken().getLexeme(), tk.toString());
        return escribir;
    }
    
}

