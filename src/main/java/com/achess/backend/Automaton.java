package com.achess.backend;

import java.util.ArrayList;

/**
 *
 * @author achess
 */

public class Automaton implements Automatons{    
    private ArrayList<Token> tokens;
    private ArrayList<Token> errors;
    private static Automaton automaton;      
    private State q0;
    private Automaton(){
        tokens = new ArrayList<Token>();
        errors = new ArrayList<Token>();        
        q0 = new State();        
        State q1 = new State(TokenType.NUMERO);
        State q2 = new State();
        State q3 = new State(TokenType.NUMERO);
        State q4 = new State(TokenType.IDENTIFICADOR);
        State q5 = new State();
        State q6 = new State();
        State q7 = new State(TokenType.LITERAL);
        State q8 = new State();
        State q9 = new State(TokenType.COMENTARIO);
        State q10 = new State(TokenType.ASIGNACION);
        q10.setFinalState(true);
        State q11 = new State(TokenType.SUMA);
        q11.setFinalState(true);
        State q12= new State(TokenType.MULTIPLICACION);
        q12.setFinalState(true);
        State q13 = new State(TokenType.PARENTESIS_A);
        q13.setFinalState(true);
        State q14 = new State(TokenType.PARENTESIS_C);
        q14.setFinalState(true);
        
        q0.addNext(Alphabet.CERO, q1);
        q0.addNext(Alphabet.NUMERO, q3);
        q0.addNext(Alphabet.LETRA, q4);
        q0.addNext("-", q2);
        q0.addNext("_", q4);
        q0.addNext("\"", q5);
        q0.addNext("/", q8);
        q0.addNext("=", q10);
        q0.addNext("+", q11);
        q0.addNext("*", q12);
        q0.addNext("(", q13);
        q0.addNext(")", q14);
        q0.addNext(Alphabet.SEPARADOR, q0);
        
        q1.addNext(Alphabet.SEPARADOR, q0);
        
        q2.addNext(Alphabet.NUMERO, q3);
        q2.addNext(Alphabet.SEPARADOR, q0); // Tal vez estado final
        
        q3.addNext(Alphabet.CERO, q3);
        q3.addNext(Alphabet.NUMERO, q3);
        q3.addNext(Alphabet.SEPARADOR, q0);
        
        q4.addNext(Alphabet.CERO, q4);
        q4.addNext(Alphabet.NUMERO, q4);
        q4.addNext(Alphabet.LETRA, q4);
        q4.addNext("-", q4);
        q4.addNext("_", q4);
        q4.addNext(Alphabet.SEPARADOR, q0);
        
        q5.addNext(Alphabet.CERO, q6);
        q5.addNext(Alphabet.NUMERO, q6);
        q5.addNext(Alphabet.LETRA, q6);
        q5.addNext(Alphabet.CHR_LITERAL, q6);
        q5.addNext(" ", q6);
        q5.addNext(Alphabet.SEPARADOR, q6);
        
        q6.addNext(Alphabet.CERO, q6);
        q6.addNext(Alphabet.NUMERO, q6);
        q6.addNext(Alphabet.LETRA, q6);
        q6.addNext(Alphabet.CHR_LITERAL, q6);
        q6.addNext(" ", q6);
        q6.addNext("\"", q7);
        q6.addNext(Alphabet.SEPARADOR, q6);
        
        q7.addNext(Alphabet.SEPARADOR, q0); //Tal vez estado final
        
        q8.addNext("/", q9);
        
        q9.addNext(Alphabet.SEPARADOR, q0);
        q10.addNext(Alphabet.SEPARADOR, q0);
        q11.addNext(Alphabet.SEPARADOR, q0);
        q12.addNext(Alphabet.SEPARADOR, q0);
        q13.addNext(Alphabet.SEPARADOR, q0);
        q14.addNext(Alphabet.SEPARADOR, q0);
    }
    
    public static Automaton getAutomaton(){
        if(automaton == null){
            automaton = new Automaton();
        }
        return automaton;
    }       
    
    private void addToken(TokenType tokenType, String lexeme, int row, int column, int index){        
        Token tk = new Token(tokenType, lexeme, row, column, index);        
        tokens.add(tk);
    }    
    
    private void addTokenError(String lexeme, int row, int column, String description, int index){
        Token tk = new Token(TokenType.ERROR, lexeme, row, column, description, index);        
        errors.add(tk);
    }
    
    public void analize(String text){
        tokens.clear();
        errors.clear();
        int index = 0;
        int row = 1;
        int column = 1;
        String lexeme = "";        
        State aux;
        State aux1;        
        aux =  q0;
        while(index < text.length()){
            char character = text.charAt(index);
            aux1 = aux.getNext(character);                        
            if(aux1 == null){
                lexeme += character;
                String des = aux.nextValues();
                addTokenError(lexeme, row, column, des, index);
                lexeme = "";
                aux1 = q0;                
            }
            else if(aux1.equals(q0) && !aux.equals(q0)){                
                if(aux.isAcceptState()){
                    addToken(aux.getTokenType(), lexeme, row, column, index);
                }
                else{
                    lexeme += character;
                    String des = aux.nextValues();
                    addTokenError(lexeme, row, column, des, index);
                }
                lexeme = "";
                //aux1 = q0;
            }
            else if(aux1.isFinalState() || index == text.length() - 1){
                lexeme += character;
                if(aux1.isAcceptState()){                    
                    addToken(aux1.getTokenType(), lexeme, row, column, index);
                }else{
                    String des = aux1.nextValues();
                    addTokenError(lexeme, row, column, des, index);
                }
                lexeme = "";
                aux1 = q0;
            }else if(!aux1.equals(q0)){
                lexeme += character;
            }    
            aux = aux1;
            index++;
            column++;
            if(character == '\n'){
                row++;
                column = 1;
            }
            
        
        }
        
    }
    
    public ArrayList<Token> getTokens(){
        return tokens;
    }
    
    public ArrayList<Token> getErrors(){
        return errors;
    }
    
}
