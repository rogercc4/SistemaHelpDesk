/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk.model;

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
public class TrabajadorInformaticaTest {

    public TrabajadorInformaticaTest() {
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
    
    @Ignore
    @Test
    public void testClasificarSolicitud() throws Exception {
    System.out.println("clasificarSolicitud()");
    System.out.println("=================================");
    System.out.println("");
    TrabajadorInformatica instance = new TrabajadorInformatica("rcelis", Cargo.getCargoBD(5));
    
    instance.clasificarSolicitud(Solicitud.getSolicitudBD(8), SubCategoria.getSubCategoriaBD(1), Prioridad.getPrioridadBD(1));
        
    }

    /**
     * Test of derivarSolicitud method, of class TrabajadorInformatica.
     */
    @Ignore
    @Test
    public void testDerivarSolicitud() throws Exception {
        System.out.println("derivarSolicitud");
        System.out.println("=================================");
        System.out.println("");

        Tramite miTramite = new Tramite();  
        TramiteArchivo archivoTramite = null;
        SubCategoria sc = new SubCategoria(); 
        TrabajadorInformatica instance = new TrabajadorInformatica("rcelis", Cargo.getCargoBD(5));
        miTramite.setCodSolicitud(26);
        miTramite.setCodCargoDestino(2);
        miTramite.setUsuarioDestino("cinonan");
        miTramite.setDetalle("Detalle de la solicitud"); 
        
        instance.derivarSolicitud(miTramite, archivoTramite);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
    @Ignore
    @Test
    public void testFinalizarAtencion() throws Exception {

    Solicitud miSolic = Solicitud.getSolicitudBD(26); 
    String detalle = "Este es el detalle";

    Cargo miCargo = Cargo.getCargoBD(8);
    
    TrabajadorInformatica trab = new TrabajadorInformatica("rcontreras", miCargo);
    trab.finalizarAtencion(miSolic, detalle);
        
    }
    
    @Ignore
    @Test
    public void testEnviarParaConformidad() throws Exception {
    
    Cargo miCargo = Cargo.getCargoBD(5); 
    Solicitud miSolic = Solicitud.getSolicitudBD(2);
    String detalle = "Detalle"; 
        
    TrabajadorInformatica instancie = new TrabajadorInformatica("rcelis", miCargo);
    instancie.enviarParaConformidad(miSolic, detalle);
        
    }

    @Ignore
    @Test
    public void testRegistrarTarea() throws Exception {
    /*
    String nombreUsuario = "dobando";
    Cargo miCargo = Cargo.getCargoBD(8);
    Solicitud miSolic = Solicitud.getSolicitudBD(18);
    String descripcion = "descripcion";
    java.util.Date fechaInicio = new java.util.Date();
    java.util.Date fechaFin = new java.util.Date();

    TrabajadorInformatica miTrab = new TrabajadorInformatica(nombreUsuario, miCargo);
    miTrab.registrarTarea(miSolic, descripcion, fechaInicio, fechaFin);
    */

    java.util.Locale loc = new java.util.Locale("es", "PE");

    java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.MEDIUM,
                            java.text.DateFormat.SHORT, loc);

    String cadenaFecha = "25/09/2010 9:0 AM";
    java.util.Date d = df.parse(cadenaFecha);
    //System.out.println(df.format(new java.util.Date()));
    System.out.println(d.toString());
        
    }

   
    @Test
    public void testGetMensaje() throws Exception {

        Mensaje miMensaje = Mensaje.getMensajeBD(3);
        assertNotNull(miMensaje);

        System.out.println( miMensaje.getCodigo() );
        System.out.println( miMensaje.getUsuarioOrigen() );
        System.out.println( miMensaje.getUsuarioDestino() );
        System.out.println( miMensaje.getAsunto() );
        System.out.println( miMensaje.getDescripcion() );
        System.out.println( miMensaje.getFecRegistro() );
        System.out.println( miMensaje.getNombreAdjunto() );
        System.out.println( miMensaje.getTipoContenidoAdjunto() );
        System.out.println( miMensaje.getArchivoAdjunto() );
        System.out.println( miMensaje.getCodTramite() );
        System.out.println( miMensaje.getTrabajadorOrigen().getNombre() );
        System.out.println( miMensaje.getTrabajadorDestino().getNombre() );

        
        

        

    }

}