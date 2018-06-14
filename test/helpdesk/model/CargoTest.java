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
 * @author rcontreras
 */
public class CargoTest {

    public CargoTest() throws SQLException {
    Conexion miConexion = new Conexion();
    miConexion.setBaseDatos("helpdesk");
    miConexion.setClave("22639443");
    miConexion.setPuerto("5432");
    miConexion.setServidor("localhost");
    miConexion.setUsuario("rcontreras");
    miConexion.setConexion();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getCodCargo method, of class Cargo.
     */
    @Test
    public void testGetCargoBD() {
        System.out.println("testGetCargoBD");
        
        int codigo = 7;
        
        Cargo instance = Cargo.getCargoBD(codigo);
        assertNotNull(instance);

        System.out.println("Cod. Cargo: " + instance.getCodCargo());
        System.out.println("Cargo: " + instance.getNombre());
        System.out.println("Area: " + instance.getArea().getNombre());
        System.out.println("Jefe de area: " + instance.getJefe());
        System.out.println("Area superior: " + instance.getArea().getAreaSuperior().getNombre());
        
        if ( instance.getCargoJefe() != null ) 
        System.out.println("Cargo Jefe: " + instance.getCargoJefe().getNombre());

        
        
    }

}