package br.gov.sc.ciasc.churrasco.dto;

import java.io.Serializable;

public class ChurrascoDto implements Serializable {

    private int carne;
    private int linguica;
    private int refrigerante;
    private int pessoa;

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

    public int getPessoa() {
        return pessoa;
    }

    public void setPessoa(int pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return ((carne * pessoa) + "g carne\n") + (linguica * pessoa) + " linguiças\n" + (refrigerante * pessoa) + "ml refrigerante";
    }
}
