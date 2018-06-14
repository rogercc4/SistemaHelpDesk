/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk.model;

import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import helpdesk.model.data.*;

/**
 *
 * @author Roger
 */
public class TipoTramiteTest {

    public TipoTramiteTest() throws SQLException {
    Conexion miConexion = Conexion.getInstanceConexion();
    miConexion.setBaseDatos("helpdesk");
    miConexion.setClave("22639443");
    miConexion.setPuerto("5432");
    miConexion.setServidor("localhost");
    miConexion.setUsuario("rcontreras");
    miConexion.abrirConexion();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getTipoTramiteBD method, of class TipoTramite.
     */
    @Test
    public void testGetTipoTramiteBD() {
        System.out.println("getTipoTramiteBD");
        
        int codTramite = 4;
        
        TipoTramite result = TipoTramite.getTipoTramiteBD(codTramite);
        
        assertNotNull(result);

        System.out.println( result.getCodigo() + " - " + result.getNombre() );       

        
    }

}