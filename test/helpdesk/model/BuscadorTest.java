/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk.model;

import java.util.*;
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
public class BuscadorTest {

    public BuscadorTest() {
    
        
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
     * Test of getSolicitudesParaAtender method, of class Buscador.
     */
    @Ignore
    @Test
    public void testGetSolicitudesParaAtender() {
        System.out.println("getSolicitudesParaAtender");
        
    }

    @Test
    public void testGetMisRequerimientos() {
    Buscador busq = new Buscador();

    Trabajador trab = Trabajador.getTrabajadorBD("dobando");
    TipoTramite tipo = TipoTramite.getTipoTramiteBD(ValorTipoTramite.ATENDER);

    Calendar c = Calendar.getInstance();
    c.set(2011, 9, 6);
    Date fec1 = c.getTime();
    c.set(2011, 9, 6);
    Date fec2 = c.getTime();

    Date[] misFechas = new Date[2];
    misFechas[0] = fec1; misFechas[1] = fec2;
    java.util.ArrayList<Solicitud> misSolic = busq.getMisReqPorUltimoTramite(trab, null, misFechas);

    assertNotNull(misSolic); 
        
    }


}