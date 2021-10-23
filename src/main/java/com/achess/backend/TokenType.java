/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achess.backend;

/**
 *
 * @author achess
 */
public enum TokenType {
    IDENTIFICADOR("Identificador"), NUMERO("Número entero"), LITERAL("Literal"), COMENTARIO("Comentario"),
    ASIGNACION("Asignación"), SUMA("Suma"), MULTIPLICACION("Multiplicación"),
    PARENTESIS_A("Paréntesis abrir"),PARENTESIS_C("Paréntesis cerrar"),
    ERROR("Error"), PALABRA("Palabra encontrada");
    
    private String type;    
    private TokenType(String type){
        this.type = type;
    }
    
    public String getType(){
        return this.type;
    }
}
