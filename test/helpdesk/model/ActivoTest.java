/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import helpdesk.model.data.*;

/**
 *
 * @author rcontreras
 */
public class ActivoTest {

    public ActivoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    Conexion miConexion = Conexion.getInstanceConexion();
    miConexion.setBaseDatos("helpdesk");
    miConexion.setClave("22639443");
    miConexion.setPuerto("5432");
    miConexion.setServidor("localhost");
    miConexion.setUsuario("rcontreras");
    miConexion.abrirConexion();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getListaActivosBD method, of class Activo.
     */
    @Test
    public void testGetActivoBD() {
        System.out.println("==============================");
        System.out.println("getActivoBD()");
        System.out.println("==============================");
        SubCategoria objSubCat = SubCategoria.getSubCategoriaBD(1);
        assertNotNull(objSubCat);

        System.out.println(objSubCat.getCodigo());
        System.out.println(objSubCat.getNombre());
        System.out.println(objSubCat.getCategoria().getCodigo());
        System.out.println(objSubCat.getCategoria().getNombre());
        System.out.println(objSubCat.getCategoria().getActivo().getCodigo());
        System.out.println(objSubCat.getCategoria().getActivo().getNombre());
        
    }
    /**
     * Test of getListaActivosBD method, of class Activo.
     */
    @Test
    public void testGetListaActivosBD() {
    System.out.println("=================");
    System.out.println("getListaActivosBD()");
    System.out.println("=================");

    ArrayList<Activo> misActivos = Activo.getListaActivosBD(FiltroRegistros.ACTIVADO);
    assertNotNull(misActivos);

    Iterator<Activo> itActivos = misActivos.iterator();
    Activo miActivo = null; 

        while ( itActivos.hasNext()  ) {
        miActivo = itActivos.next();
        System.out.println(miActivo.getCodigo());
        System.out.println(miActivo.getNombre());
        }
    
    }

}