/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achess.backend;

import java.util.ArrayList;

/**
 *
 * @author achess
 */
public enum TokenType {
    IDENTIFICADOR("Identificador"), NUMERO("Número entero"), LITERAL("Literal"), COMENTARIO("Comentario"),
    ASIGNACION("Asignación"), SUMA("Suma"), MULTIPLICACION("Multiplicación"),
    PARENTESIS_A("Paréntesis abrir"),PARENTESIS_C("Paréntesis cerrar"),
    ERROR("Error"), PALABRA("Palabra encontrada"),
    ESCRIBIR("ESCRIBIR"), FIN("FIN"), REPETIR("REPETIR"), INICIAR("INICIAR"),
    SI("SI"), VERDADERO("VERDADERO"), FALSO("FALSO"), ENTONCES("ENTONCES"),
    EPSILON("epsilon"), $("$");
    
    
    private static ArrayList<TokenType> reservedWords = null;
    public static TokenType isReserved(String lexeme, TokenType tk){
        if(reservedWords == null){
            reservedWords = new ArrayList<TokenType>();
            reservedWords.add(ESCRIBIR);
            reservedWords.add(FIN);
            reservedWords.add(REPETIR);
            reservedWords.add(INICIAR);
            reservedWords.add(SI);
            reservedWords.add(VERDADERO);
            reservedWords.add(FALSO);
            reservedWords.add(ENTONCES);
        }
        
        for(TokenType type: reservedWords){
            if(type.getType().equals(lexeme)){
                return type;
            }
        }
        return tk;
    }
    
    private String type;    
    private TokenType(String type){
        this.type = type;
    }
    
    public String getType(){
        return this.type;
    }
}
