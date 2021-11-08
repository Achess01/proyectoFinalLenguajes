package com.achess.backend.sintaxis.execute;

/**
 *
 * @author achess
 */
class Asignar extends Instruction{

    private String id;
    private Integer value;

    public Asignar(String id, Integer value) {
        this.id = id;
        this.value = value;
    }
    public Asignar(String id) {
        this.id = id;        
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    

    public String getId() {
        return id;
    }

    public Integer getValue() {
        return value;
    }

    
    
    @Override
    public String run() {
        return "";
    }
    
}
