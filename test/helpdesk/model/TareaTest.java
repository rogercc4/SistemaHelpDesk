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
public class TareaTest {

    public TareaTest() {
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
     * Test of getTareaBD method, of class Tarea.
     */
    @Ignore
    @Test
    public void testGetTareaBD() {
        System.out.println("getTareaBD");
        int codTarea = 1;
        
        Tarea result = Tarea.getTareaBD(codTarea);
        assertNotNull(result);

        System.out.println("Area: " + result.getArea());
        System.out.println("Cargo: " + result.getCargo());
        System.out.println("Cod. Tramite: " + result.getCodTramite());
        System.out.println("Codigo: " + result.getCodigo());
        System.out.println("Descripcion: " + result.getDescripcion());
        System.out.println("Fecha Fin: " + result.getFechaFin());
        System.out.println("Fecha Inicio: " + result.getFechaInicio());
        System.out.println("Nombre trabajador: " + result.getNombreTrabajador());
        System.out.println("Usuario: " + result.getUsuario());
        
        
        
    }

    
    @Test
    public void testGetMensajes() throws HelpDeskException {

    Tramite miTram = Tramite.getTramiteBD(108);
    assertNotNull(miTram);

    java.util.ArrayList<Mensaje> misMensajes = miTram.getMensajes();
    assertNotNull(misMensajes);

    java.util.Iterator<Mensaje> iMsg = misMensajes.iterator() ;
    Mensaje msg = null; 

            while ( iMsg.hasNext() ) {
            msg = iMsg.next(); 
            System.out.println(msg.getAsunto());
            System.out.println(msg.getArchivoAdjunto());
            System.out.println(msg.getDescripcion());

            }

    Trabajador miTrab = new Trabajador();
    msg.setDescripcion("', select 20");

    miTrab.guardarMensaje(msg);


    }

}