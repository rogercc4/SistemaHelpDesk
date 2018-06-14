/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk.model;

/**
 *
 * @author rcontreras
 */
public enum ValorTipoTramite {
    
    ENVIO_VISTO_BUENO(1), 
    DAR_VISTO_BUENO(2),
    ENVIO_ATENCION(3),
    ATENDER(4), 
    DERIVAR(5),
    DEVOLVER(6),
    RECHAZAR(7),
    FINALIZAR_ATENCION(8),
    CONFORMIDAD_USUARIO(9),
    CERRAR(10);
    
    private int codigo;

    ValorTipoTramite(int codigo) {
    this.codigo = codigo ;
    }

    public int getCodigo() {
    return this.codigo;
    }

}
