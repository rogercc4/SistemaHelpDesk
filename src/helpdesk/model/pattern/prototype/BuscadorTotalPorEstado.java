/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.model.pattern.prototype;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roger
 */
public class BuscadorTotalPorEstado implements Cloneable {
    
    private TipoDeBusqueda tipoBusqueda;
    
    private int numRegistros;
    
    public BuscadorTotalPorEstado (TipoDeBusqueda newTipoBusqueda) {
        this.tipoBusqueda = newTipoBusqueda;
    }
    
    /**
     * @return the numeroRegistros
     */
    public int getNumRegistros() {
        return numRegistros;
    }

    /**
     * @param numeroRegistros the numeroRegistros to set
     */
    public void setNumRegistros(int numeroRegistros) {
        this.numRegistros = numeroRegistros;
    }

    /**
     * @return the tipoBusqueda
     */
    public TipoDeBusqueda getTipoBusqueda() {
        return tipoBusqueda;
    }

    /**
     * @param tipoBusqueda the tipoBusqueda to set
     */
    public void setTipoBusqueda(TipoDeBusqueda tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }
    
    @Override
    public Object clone(){
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(BuscadorTotalPorEstado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }    
    
}