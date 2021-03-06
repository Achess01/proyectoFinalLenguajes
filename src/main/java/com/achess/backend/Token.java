package com.achess.backend;

/**
 *
 * @author achess
 */
public class Token {    
    private TokenType type;
    private String lexeme;
    private int row;
    private int column;
    private String description;
    private int begin;
    private int end;    

    public Token(TokenType type, String lexeme, int row, int column, int index) {
        if(type.equals(TokenType.IDENTIFICADOR)){
            type = TokenType.isReserved(lexeme, type);
        }
        this.type = type;
        this.lexeme = lexeme;
        this.row = row;
        this.column = column;
        this.description = type.getType();
        this.calcBeginEnd(index);
    }
    
    public Token(TokenType type, String text, int row, int column, String description, int index) {
        this.type = type;
        this.lexeme = text;
        this.row = row;
        this.column = column;
        this.description = description;
        this.calcBeginEnd(index);
    }
           
    private void calcBeginEnd(int index){
        this.begin = index - this.lexeme.length();
        this.end = index;
    }

    public TokenType getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getDescription() {
        return description;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }    
    
    @Override
    public String toString() {
        return "Token{" + "type=" + type + ", lexeme=" + lexeme + ", row=" + row + ", column=" + column + ", description=" + description + ", begin=" + begin + ", end=" + end + '}';
    }
                        
    
}
