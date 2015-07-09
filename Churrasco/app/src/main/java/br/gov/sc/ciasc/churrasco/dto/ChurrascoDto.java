package br.gov.sc.ciasc.churrasco.dto;

import java.io.Serializable;

public class ChurrascoDto implements Serializable {

    private int carne;

    public int getCarne() {
        return carne;
    }

    public void setCarne(int carne) {
        this.carne = carne;
    }
}
