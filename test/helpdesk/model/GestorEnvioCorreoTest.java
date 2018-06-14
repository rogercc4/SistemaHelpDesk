/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk.model;

import org.junit.Ignore;
import java.util.ArrayList;
import java.util.Map;
import javax.mail.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import helpdesk.model.data.*;

/**
 *
 * @author rcontreras
 */
public class GestorEnvioCorreoTest {

    public GestorEnvioCorreoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    java.util.HashMap parametros = new java.util.HashMap() ;
    parametros.put("mail.smtp.host", "192.168.4.12");
    GestorEnvioCorreo.setSesionCorreo(parametros);


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
     * Test of enviarMsgDespuesTramite method, of class GestorEnvioCorreo.
     */
    @Ignore
    @Test
    public void testEnviarMensaje() {
    System.out.println("testEnviarMensaje");
    GestorEnvioCorreo miCorreo = new GestorEnvioCorreo();

    miCorreo.setMailOrigen("rcontreras@satch.gob.pe");
    miCorreo.setNombreOrigen("Roger Contreras");

    java.util.ArrayList<String> misDestinos = new java.util.ArrayList<String>();
    misDestinos.add("cinonan@satch.gob.pe");
    misDestinos.add("msalomon@satch.gob.pe");
    miCorreo.setDestinosTO(misDestinos); 

    java.util.ArrayList<String> misDestinosCC = new java.util.ArrayList<String>();
    misDestinosCC.add("dobando@satch.gob.pe");
    misDestinosCC.add("rcontreras@satch.gob.pe");
    miCorreo.setDestinosCC(misDestinosCC);

    miCorreo.setMensaje("Este es un mensaje de prueba");
    
    //miCorreo.enviarMensaje();
    miCorreo.enviarMsgDespuesTramite(null);
    
    }
    

    @Test
    public void testEnviarMensaje2() {
    System.out.println("testEnviarMensaje2");
    GestorEnvioCorreo miCorreo = new GestorEnvioCorreo();
    miCorreo.enviarMsgDespuesTramite(Solicitud.getSolicitudBD(1));
    System.out.println(miCorreo.getAsunto());
    System.out.println(miCorreo.getMailOrigen() + " - " + miCorreo.getNombreOrigen() );
    java.util.Iterator<String> destinos = miCorreo.getDestinosTO().iterator();

        while ( destinos.hasNext() ) {
        System.out.println(destinos.next());
        }

    System.out.println(miCorreo.getMensaje());
    
    }

    @Ignore
    @Test
    public void testGetMensajeHTML() {
    GestorEnvioCorreo miCorreo = new GestorEnvioCorreo();
    StringBuilder sb = miCorreo.getMensajeHTML(Solicitud.getSolicitudBD(1));
    System.out.println(sb);
    }




}