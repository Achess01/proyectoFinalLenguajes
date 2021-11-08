package com.achess.backend.sintaxis.execute;

import com.achess.backend.TokenType;

/**
 *
 * @author achess
 */
class Operation extends Instruction{
    private String type;
    private String valueStr;
    private Operation left;
    private Operation right;    

    public Operation(String type, Operation left, Operation right) {                
        this.type = type;
        this.left = left;
        this.right = right;        
    }
    
    public Operation(String type, String valueStr) {
        this.type = type;   
        this.valueStr = valueStr;
    }       

    public void setLeft(Operation left) {
        this.left = left;
    }

    public void setRight(Operation right) {
        this.right = right;
    }

    public String getType() {
        return type;
    }

    public String getValueStr() {
        return valueStr;
    }

    public Operation getLeft() {
        return left;
    }

    public Operation getRight() {
        return right;
    }
    
    

    @Override
    public String run() {        
        if(type.equals(TokenType.NUMERO.toString())){
            return valueStr;
        }
        else if(type.equals(TokenType.IDENTIFICADOR.toString())){
            String txt = Run.symbolTable.get(valueStr) != null ? String.valueOf(Run.symbolTable.get(valueStr)) : "0";
            return txt;
        }
        else if(type.equals(TokenType.MULTIPLICACION.toString())){
            String mul = String.valueOf(Integer.parseInt(left.run()) * Integer.parseInt(right.run()));
            return mul;
        }
        else if(type.equals(TokenType.SUMA.toString())){
            String sum = String.valueOf(Integer.parseInt(left.run()) + Integer.parseInt(right.run()));
            return sum;
        }
        return "0";
    }

    @Override
    public String toString() {
        return "Operation{" + "type=" + type + ", valueStr=" + valueStr + ", left=" + left + ", right=" + right + '}';
    }
    
    
    
}
