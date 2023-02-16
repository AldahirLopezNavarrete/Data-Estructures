/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolesBinariosBusqueda;

import arboles.NodoBin;

/**
 *
 * @author aldahir
 */
public class NodoBB <T extends Comparable<T>> extends NodoBin<T>{
    NodoBB<T> izq,der,papa;
    int fe = 0;
    
    public NodoBB(T elem){
        super(elem);
        papa = null;
        fe = 0;
    }
    
    public void cuelga(NodoBB<T> hijo){
        if(hijo.getElem().compareTo(this.getElem())<=0)
            izq=hijo;
        else
            der=hijo;
        hijo.papa=this;
    }

    public void cuelgaDer(NodoBB<T> hijo){
        if(hijo == null){
            der = null;
            return;
        }
        der=hijo;
        hijo.papa=this;
    }
    
    public void cuelgaIzq(NodoBB<T> hijo){
        if(hijo == null){
            izq = null;
            return;
        }
        izq=hijo;
        hijo.papa=this;
    }
    
    public NodoBB<T> getIzq() {
        return izq;
    }

    public void setIzq(NodoBB<T> izq) {
        this.izq = izq;
    }

    public NodoBB<T> getDer() {
        return der;
    }

    public void setDer(NodoBB<T> der) {
        this.der = der;
    }
    
    public NodoBB<T> getPapa() {
        return papa;
    }

    public void setPapa(NodoBB<T> papa) {
        this.papa = papa;
    }

    public int getFe() {
        return fe;
    }
    
    public void setFe(int fe){
        this.fe= fe;
    }

    public void incrementaFe() {
        fe++;
    }
    
    public void decrementaFe() {
        fe--;
    }
    
    
  
}

