/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk.model;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import helpdesk.model.data.*;
import org.junit.Ignore;

/**
 *
 * @author Roger
 */
public class TipoSolicitudTest {

    public TipoSolicitudTest() {
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    Conexion miConexion = new Conexion();
    miConexion.setBaseDatos("helpdesk");
    miConexion.setClave("22639443");
    miConexion.setPuerto("5432");
    miConexion.setServidor("localhost");
    miConexion.setUsuario("rcontreras");
    miConexion.setConexion();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Ignore
    @Test
    public void testGetTiposSolicitud() {
    ArrayList<TipoSolicitud> tipos = TipoSolicitud.getTiposSolicitud(FiltroRegistros.DESACTIVADO);
    assertNotNull(tipos);   
    java.util.Iterator<TipoSolicitud> it = tipos.iterator();
    TipoSolicitud t = null;
    
        while (it.hasNext()) {
        t = it.next();
        System.out.println(t.getCodigo() + " - " + t.getNombre() + " - " + t.getDescripcion());
        }
    
    }

    @Test
    public void testGetFormatosTramite() {
    int codigo = 1;

    TipoSolicitud t = new TipoSolicitud(codigo);
    ArrayList<FormatoTramite> formatos = t.getFormatosTramite(FiltroRegistros.TODOS);
    assertNotNull(formatos);
    
    java.util.Iterator<FormatoTramite> it = formatos.iterator();
    FormatoTramite ft = null; 

        while (it.hasNext()) {
        ft = it.next();
        System.out.println( ft.getCodFormato() + " - " + ft.getNombre() + " - " + ft.getExtension());
        }

    }

}