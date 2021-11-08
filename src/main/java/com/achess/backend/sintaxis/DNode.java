package com.achess.backend.sintaxis;

import com.achess.backend.Token;
import java.util.ArrayList;

/**
 *
 * @author achess
 */
public class DNode {
    private ArrayList<DNode> children = new ArrayList<DNode>();
    private Token token = null;
    private String symbol;
    private DNode father = null;
    boolean terminal;
    public DNode(String symbol, DNode father){
        terminal = false;
        this.symbol = symbol;
        this.father = father;
    }
        
    public void setToken(Token token) {
        this.terminal = true;
        this.token = token;
    }
    
    public void addChildren(String[] productions){                    
        for(String prod : productions){
            add(new DNode(prod, this));            
        }                    
    }
    
    public DNode getNode(String symbol){
        for(DNode node: children){
            if(node.symbol.equals(symbol)){
                return node;
            }
        }
        return null;
    }
    
    public void add(DNode nodo){
        this.children.add(nodo);
    }

    public ArrayList<DNode> getChildren() {
        return children;
    }

    public Token getToken() {
        return token;
    }

    public String getSymbol() {
        return symbol;
    }

    public DNode getFather() {
        return father;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void print(){
        children.forEach(ch -> {
            System.out.println(ch);
            ch.print();
        });
    }
    @Override
    public String toString() {
        String fs = father != null? father.getSymbol() : "";
        String s = token != null? token.getLexeme(): symbol;
        return "DNode{" + "father=" + fs + ", symbol=" + s + '}';
    }
    
    
}
