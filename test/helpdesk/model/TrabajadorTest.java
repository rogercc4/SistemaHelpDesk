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
public class TrabajadorTest {

    public TrabajadorTest() {
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
    
    @Test
    @Ignore
    public void testGenerarSolicitud() throws HelpDeskException {
    System.out.println("testGenerarSolicitud");

    Trabajador miTrabajador = new Trabajador();
    
    Solicitud miSolic = new Solicitud();
    miSolic.setAsunto("Asunto");
    miSolic.setCargo( Cargo.getCargoBD(6) );
    miSolic.setDetalle("detalle");
    miSolic.setFecha(new java.util.Date());
    miSolic.setTipoSolicitud( new TipoSolicitud(1) );
    miSolic.setTrabajador(Trabajador.getTrabajadorBD("dobando"));

    java.util.ArrayList<Archivo> misArchivos = new java.util.ArrayList<Archivo>();
    Archivo miArchivo = null ;

        for (int i=1; i<=2; i++ )  {
        miArchivo = new Archivo();
        miArchivo.setArchivo("Archivo " + i);
        miArchivo.setCodArchivo(i);
        miArchivo.setFecha(new java.util.Date());
        miArchivo.setNombre("Nombre archivo " + i);
        miArchivo.setTipoContenido("Tipo contenido " + i);
        misArchivos.add(miArchivo);
        }
    
    miTrabajador.guardarSolicitud(miSolic, misArchivos, true);
    }

    @Test
    @Ignore
    public void testDevolverSolicitud() throws HelpDeskException {
    System.out.println("testDevolverSolicitud");
    System.out.println("==========================");
    System.out.println("");

    int codSolic = 15;
    int codCargo = 2 ;

    Solicitud par1 = Solicitud.getSolicitudBD(codSolic);
    Cargo par2 = Cargo.getCargoBD(codCargo);
    String par3 = null ;

    Trabajador instance = Trabajador.getTrabajadorBD("rcontreras");
    instance.devolverSolicitud(par1, par2, par3, null);

    }


    @Ignore
    @Test//(expected=helpdesk.model.HelpDeskException.class)
    public void testRechazarSolicitud() throws HelpDeskException {
    System.out.println("rechazarSolicitud");
    System.out.println("=======================");
    System.out.println("");

    Trabajador instance = new Trabajador();
    instance.setUsuario("rcelis");

    Solicitud par1 = new Solicitud();
    par1.setCodSolicitud(17);
    par1.setCargo(Cargo.getCargoBD(2));
    par1.setTrabajador(Trabajador.getTrabajadorBD("rcontreras")); 
    
    Cargo par2 = new Cargo();
    par2.setCodCargo(5);

    String par3 = null ;
    par3 = "motivo por el cual se rechaza la solicitud ....";

    TramiteArchivo par4 = null ;
    par4 = new TramiteArchivo();
    par4.setArchivo("archivo 1");
    par4.setCodTramite(200);
    par4.setNombre("Nombre archivo 01");
    par4.setTipoContenido("Tipo de contenido "); 
    
    instance.rechazarSolicitud(par1, par2, par3, par4); 
    }

    @Ignore
    @Test
    public void testCerrarSolicitud() throws helpdesk.model.HelpDeskException {

    Trabajador instance = new Trabajador();
    Solicitud miSolic = new Solicitud();
    miSolic.setCodSolicitud(20);
    miSolic.setTrabajador(Trabajador.getTrabajadorBD("rcelis"));
    miSolic.setCargo(Cargo.getCargoBD(5)); 


    miSolic = Solicitud.getSolicitudBD(25); 
    String detalle = "detalle del cierre";

    instance.cerrarSolicitud(miSolic, detalle);

    }

    @Test
    @Ignore
    public void testDevolverConformidad() throws helpdesk.model.HelpDeskException {

    Solicitud miSolic =  Solicitud.getSolicitudBD(25);
    
    String detalle = "detalle de la devolucion";

    Trabajador instance = new Trabajador();

    TramiteArchivo miArchivo = null; 
    miArchivo = new TramiteArchivo();
    miArchivo.setArchivo("Archivo");
    miArchivo.setNombre("nombre");
    miArchivo.setTipoContenido("Tipo contenido");
    
    instance.devolverConformidad(miSolic, detalle, miArchivo);
    }



}