/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk.model;

import org.junit.Ignore;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import helpdesk.model.*;
import helpdesk.model.data.*;

/**
 *
 * @author rcontreras
 */
public class LoginTest {
private static Conexion miConexion;

    public LoginTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    miConexion = new Conexion();
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
     * Test of getClave method, of class Login.
     */
    @Ignore
    @Test
    public void testGetClave() {
        System.out.println("getClave");
        Login instance = null;
        String expResult = "";
        String result = instance.getClave();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClave method, of class Login.
     */
     @Ignore
    @Test
    public void testSetClave() {
        System.out.println("setClave");
        String newClave = "";
        Login instance = null;
        instance.setClave(newClave);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaquina method, of class Login.
     */
     @Ignore
    @Test
    public void testGetMaquina() {
        System.out.println("getMaquina");
        Login instance = null;
        String expResult = "";
        String result = instance.getMaquina();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaquina method, of class Login.
     */
     @Ignore
    @Test
    public void testSetMaquina() {
        System.out.println("setMaquina");
        String newMaquina = "";
        Login instance = null;
        instance.setMaquina(newMaquina);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuario method, of class Login.
     */
     @Ignore
    @Test
    public void testGetUsuario() {
        System.out.println("getUsuario");
        Login instance = null;
        String expResult = "";
        String result = instance.getUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     
    @Ignore
    @Test //(expected=HelpDeskException.class)
    public void probarLogin() throws HelpDeskException {
    String user = "cinonan";   

    Login miLogin = new Login("cinonan");
    assertEquals(user, miLogin.getUsuario() );
    
    miLogin.setClave("a#6");
    assertEquals("a#6", miLogin.getClave() );

    miLogin.setMaquina("192.168.4.29");

    miLogin.validarLogin();
    
    //miLogin.setClave("");
    
    }

    @Ignore
    @Test
    public void testCambiarClave() throws helpdesk.model.HelpDeskException {

    Login trab = new Login("rcelis");
    trab.setClave("4564aaaAABBHH787");

    trab.cambiarClave("4564aaaAABBHH787");
    


    }


}