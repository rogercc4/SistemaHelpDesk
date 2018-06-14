/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk.model;

import java.util.Date;
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
public class SolicitudTest {

    public SolicitudTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    Conexion miConexion = Conexion.getInstanceConexion();
    miConexion.setBaseDatos("helpdesk");
    miConexion.setClave("22639443");
    miConexion.setPuerto("5432");
    miConexion.setServidor("192.168.4.26");
    miConexion.setUsuario("rcontreras");
    miConexion.abrirConexion();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    /**
     * Test of getSolicitudBD method, of class Solicitud.
     */
    @Ignore
    @Test
    public void testGetArchivos() {

    Solicitud miSolic = new Solicitud();
    miSolic.setCodSolicitud(1);

    java.util.ArrayList<Archivo> misArch = miSolic.getArchivos();

    assertNull(misArch);
        
    }

    @Test
    public void testGetSubCategoria() {

    Solicitud miSolic = Solicitud.getSolicitudBD(20);
    SubCategoria subCat = miSolic.getSubCategoria();
    assertNotNull(subCat);

    System.out.println(subCat.getNombre() + " -- " + 
                       subCat.getCategoria().getNombre() + " -- " +
                       subCat.getCategoria().getActivo().getNombre() );

    System.out.println( miSolic.getPrioridad().getNombre() ) ; 

    }
    

}