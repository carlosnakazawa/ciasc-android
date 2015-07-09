package br.gov.sc.ciasc.churrasco.dto;

import java.io.Serializable;

public class ChurrascoDto implements Serializable {

    private int carne;
    private int linguica;
    private int refrigerante;

    public int getCarne() {
        return carne;
    }

    public void setCarne(int carne) {
        this.carne = carne;
    }

    public int getLinguica() {
        return linguica;
    }

    public void setLinguica(int linguica) {
        this.linguica = linguica;
    }

    public int getRefrigerante() {
        return refrigerante;
    }

    public void setRefrigerante(int refrigerante) {
        this.refrigerante = refrigerante;
    }
}
