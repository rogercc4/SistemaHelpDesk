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
public class BuscadorEstadoInicial implements Cloneable  {
    
    private String usuarioTrabajador;
    private int codigoCargo;
    
    private int registradas; 
    private int asignadas;
    private int devueltas;
    private int requerimientosEnProcesoAtencion;
    private int rechazadas;
    private int darVistoBueno;
    private int derivadas;
    private int enviarParaConformidad;
    private int paraAtender;
    private int pendientesEnProcesoAtencion;
    
    @Override
    public Object clone(){
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(BuscadorEstadoInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }     

    /**
     * @return the registradas
     */
    public int getRegistradas() {
        return registradas;
    }

    /**
     * @param registradas the registradas to set
     */
    public void setRegistradas(int registradas) {
        this.registradas = registradas;
    }

    /**
     * @return the asignadas
     */
    public int getAsignadas() {
        return asignadas;
    }

    /**
     * @param asignadas the asignadas to set
     */
    public void setAsignadas(int asignadas) {
        this.asignadas = asignadas;
    }

    /**
     * @return the devueltas
     */
    public int getDevueltas() {
        return devueltas;
    }

    /**
     * @param devueltas the devueltas to set
     */
    public void setDevueltas(int devueltas) {
        this.devueltas = devueltas;
    }

    /**
     * @return the requerimientosEnProcesoAtencion
     */
    public int getRequerimientosEnProcesoAtencion() {
        return requerimientosEnProcesoAtencion;
    }

    /**
     * @param requerimientosEnProcesoAtencion the requerimientosEnProcesoAtencion to set
     */
    public void setRequerimientosEnProcesoAtencion(int requerimientosEnProcesoAtencion) {
        this.requerimientosEnProcesoAtencion = requerimientosEnProcesoAtencion;
    }

    /**
     * @return the rechazadas
     */
    public int getRechazadas() {
        return rechazadas;
    }

    /**
     * @param rechazadas the rechazadas to set
     */
    public void setRechazadas(int rechazadas) {
        this.rechazadas = rechazadas;
    }

    /**
     * @return the darVistoBueno
     */
    public int getDarVistoBueno() {
        return darVistoBueno;
    }

    /**
     * @param darVistoBueno the darVistoBueno to set
     */
    public void setDarVistoBueno(int darVistoBueno) {
        this.darVistoBueno = darVistoBueno;
    }

    /**
     * @return the derivadas
     */
    public int getDerivadas() {
        return derivadas;
    }

    /**
     * @param derivadas the derivadas to set
     */
    public void setDerivadas(int derivadas) {
        this.derivadas = derivadas;
    }

    /**
     * @return the enviarParaConformidad
     */
    public int getEnviarParaConformidad() {
        return enviarParaConformidad;
    }

    /**
     * @param enviarParaConformidad the enviarParaConformidad to set
     */
    public void setEnviarParaConformidad(int enviarParaConformidad) {
        this.enviarParaConformidad = enviarParaConformidad;
    }

    /**
     * @return the paraAtender
     */
    public int getParaAtender() {
        return paraAtender;
    }

    /**
     * @param paraAtender the paraAtender to set
     */
    public void setParaAtender(int paraAtender) {
        this.paraAtender = paraAtender;
    }

    /**
     * @return the pendientesEnProcesoAtencion
     */
    public int getPendientesEnProcesoAtencion() {
        return pendientesEnProcesoAtencion;
    }

    /**
     * @param pendientesEnProcesoAtencion the pendientesEnProcesoAtencion to set
     */
    public void setPendientesEnProcesoAtencion(int pendientesEnProcesoAtencion) {
        this.pendientesEnProcesoAtencion = pendientesEnProcesoAtencion;
    }

    /**
     * @return the usuarioTrabajador
     */
    public String getUsuarioTrabajador() {
        return usuarioTrabajador;
    }

    /**
     * @param usuarioTrabajador the usuarioTrabajador to set
     */
    public void setUsuarioTrabajador(String usuarioTrabajador) {
        this.usuarioTrabajador = usuarioTrabajador;
    }

    /**
     * @return the codigoCargo
     */
    public int getCodigoCargo() {
        return codigoCargo;
    }

    /**
     * @param codigoCargo the codigoCargo to set
     */
    public void setCodigoCargo(int codigoCargo) {
        this.codigoCargo = codigoCargo;
    }
    
        @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.usuarioTrabajador != null ? this.usuarioTrabajador.hashCode() : 0);
        hash = 47 * hash + this.codigoCargo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BuscadorEstadoInicial other = (BuscadorEstadoInicial) obj;
        if ((this.usuarioTrabajador == null) ? (other.usuarioTrabajador != null) : !this.usuarioTrabajador.equals(other.usuarioTrabajador)) {
            return false;
        }
        if (this.codigoCargo != other.codigoCargo) {
            return false;
        }
        return true;
    }
    
}