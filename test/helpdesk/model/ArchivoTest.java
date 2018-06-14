/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk.model;

import java.io.InputStream;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import helpdesk.model.data.*;

/**
 *
 * @author Roger
 */
public class ArchivoTest {

    public ArchivoTest() {
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

    /**
     * Test of getFichero method, of class Archivo.
     */
    @Test
    public void testGetFichero() {

    Archivo miArchivo = Archivo.getArchivoBD(20);

    assertNotNull(miArchivo);

    System.out.println( miArchivo.getNombre() + " - " + miArchivo.getTipoContenido() + " - " + miArchivo.getFecha() );
        
    }

}