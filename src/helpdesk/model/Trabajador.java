/***********************************************************************
 * Module:  Trabajador.java
 * Author:  Roger
 * Purpose: Defines the Class Trabajador
 ***********************************************************************/

package helpdesk.model;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import helpdesk.model.data.*;
import helpdesk.model.pattern.TrabajadorFlyweightFactory;

/** Representa a cualquier trabajador de la institución
 *
 * @pdOid 342a9bd6-1816-4b18-80c4-b3d8ea44fbe7 */
public class Trabajador {
   /** Usuario  con la cual puede iniciar sesión en el sistema.
    *
    * @pdOid 7e7e61da-c02c-4a1b-9fd3-9ca78e8253bd */
   private java.lang.String usuario;
   /** Nombre del trabajador
    *
    * @pdOid 8538b2c3-94e4-4958-96b6-40b78ecb26c1 */
   private java.lang.String nombre;
   /** Apellido del trabajador
    *
    * @pdOid 5c278f77-1cae-422c-ac1a-16a6ac543517 */
   private java.lang.String apellido;
   /** Número de DNI del trabajador
    *
    * @pdOid 4906953a-086e-42f7-ac34-e59022602511 */
   private String dni;
   /** Correo del trabajador
    *
    * @pdOid eabb29e8-e33b-4bad-839d-3effcafc1b2a */
   private String correo;
   /** Número de anexo o teléfonos del trabajador
    *
    * @pdOid 8d74e89d-7b8d-4ea1-8bdd-0a57c058066b */
   private String anexo;
   /** Cargo fijo del trabajador
    *
    * @pdOid b53bf34c-f8ac-4eca-8aff-180c1a367901 */
   private Cargo cargo;

   /** Permite obtener el nombre del usuario con el cual el trabajador inicia sesión.
    *
    * @pdOid f30d3361-1731-4887-a6df-e528e4e1aa13 */
   public java.lang.String getUsuario() {
      return usuario;
   }

   /** Permite establecer el nombre del usuario con el cual el trabajador inicia sesión.
    *
    * @param newUsuario Valor del nombre de usuario
    * @pdOid 98df0e67-3950-44db-b696-1c65ef37b01c */
   public void setUsuario(java.lang.String newUsuario) {
       if (newUsuario==null || newUsuario.trim().length()<=0)
           throw new IllegalArgumentException ("Ingrese un nombre de usuario");

      usuario = newUsuario;
   }

   /** Permite obtener los nombres del trabajador.
    *
    * @pdOid 76a7a928-f29b-4735-a3a0-7441457d03ae */
   public java.lang.String getNombre() {
      return nombre;
   }

   /** Permite establecer  los nombres del trabajador
    *
    * @param newNombre nombres del trabajador
    * @pdOid 277638c8-3084-499c-aa3b-49bbdebb0cd7 */
   public void setNombre(java.lang.String newNombre) {
       if (newNombre==null || newNombre.trim().length()<=0)
           throw new IllegalArgumentException ("Ingrese el Nombre del trabajador");

      nombre = newNombre;
   }

   /** Permite obtener los apellidos del trabajador.
    *
    * @pdOid 8ebb169b-c88c-4c64-a477-75b6e69e8e94 */
   public java.lang.String getApellido() {
      return apellido;
   }

   /** Permite establecer los apellidos del trabajador.
    *
    * @param newApellido Apellidos del trabajador
    * @pdOid 1b03e144-9e93-4c57-82e2-36cec818c213 */
   public void setApellido(java.lang.String newApellido) {
       if (newApellido==null || newApellido.trim().length()<=0)
           throw new IllegalArgumentException ("Ingrese el Apellido del trabajador");

      apellido = newApellido;
   }

   /** Permite obtener el número de DNI del trabajador
    *
    * @pdOid 4e1ffe6c-b501-4339-837d-e2ca27aaa220 */
   public String getDni() {
      return dni;
   }

   /** Permite establecer el número de DNI del trabajador
    *
    * @param newDni Número del DNI
    * @pdOid 56eda97c-f6ff-4914-a5b1-3ec0350d6ad3 */
   public void setDni(String newDni) {
       if (newDni==null || newDni.trim().length()!=8)
            throw new IllegalArgumentException ("Ingrese un DNI valido");

        Pattern p = Pattern.compile("[^0-9]");
        Matcher  m = p.matcher(newDni.trim());

        if(m.find())
            throw new IllegalArgumentException("DNI tiene caracteres prohibidos");
        else
            this.dni = newDni;
   }

   /** Permite obtener la dirección de correo del trabajador
    *
    * @pdOid f9d08724-478b-464d-9bd6-194b677a581a */
   public String getCorreo() {
      return correo;
   }

   /** Permite establecer  la dirección de correo del trabajador
    *
    * @param newCorreo Dirección de correo
    * @pdOid ee10d1a2-47df-466f-87df-9e352194eabd */
   public void setCorreo(String newCorreo) {
    String regex = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(newCorreo);

    if (!matcher.matches())
        throw new IllegalArgumentException ("Ingrese un E-mail valido");
        
        correo = newCorreo;


   }

   /** Permite obtener el anexo y/o números de contacto del trabajador
    *
    * @pdOid 4a2fec77-e894-4751-b9f4-fcab0307ca8d */
   public String getAnexo() {
      return anexo;
   }

   /** Permite establecer el anexo y/o números de contacto del trabajador
    *
    * @param newAnexo valor de anexo y/o números de contacto del trabajador
    * @pdOid 195425e2-400e-4cde-855e-33efd1c9f446 */
   public void setAnexo(String newAnexo) {
      if (newAnexo==null || newAnexo.trim().length()<=0)
         anexo = null ;
      else
      anexo = newAnexo;

   }

   /** Permite obtener el cargo del trabajador.
    *
    * @pdOid 77c3977f-b7a2-4ffe-83f4-55711a0c74c1 */
   public Cargo getCargo() {
      return cargo;
   }

   /** Permite establecer  el cargo del trabajador.
    *
    * @param newCargo cargo del trabajador
    * @pdOid 027d5219-c4e7-4a60-9059-91e2e9331826 */
   public void setCargo(Cargo newCargo) {
       if ((newCargo==null)||(newCargo.getCodCargo()<=0)
            ||(newCargo.getNombre().trim().length()<=0))
           throw new IllegalArgumentException("Ingrese un Cargo valido");
           
      cargo = newCargo;
   }

   /** Este método permite enviar una solicitud para que reciba el visto bueno del jefe inmediato de la persona que lo envía.
    *
    * @param tramite Tramite que representa el visto bueno que desea hacerse
    * @pdOid 40ec8295-5cfc-4f42-8f33-b0ad3aeeb9b7 */
   public boolean isEnviadoVistoBueno(Tramite tramite) {
      // TODO: implement

       if ( tramite.getUsuarioOrigen() == null 
            || tramite.getCodTipoTramite() != ValorTipoTramite.ENVIO_VISTO_BUENO.getCodigo()
            || tramite.getCodSolicitud() <= 0  || tramite.getCodCargoOrigen() <= 0  
            || tramite.getFecha() == null || tramite.getCodCargoDestino() <= 0  )   {
            return false; 
       }

      String cadSql = "INSERT INTO helpdesk.tramite( " +
                        "usuario_origen, " +
                        "fecha, " +
                        "tipo_tramite_id, " +
                        "solicitud_id, " +
                        "cargo_id_origen, " +
                        "usuario_detino, " +
                        "cargo_id_destino, " +
                        "detalle) " +
                        "VALUES (" +
                        "'" + tramite.getUsuarioOrigen() + "', " +
                        "'" + tramite.getFecha() + "', " +
                        "" + tramite.getCodTipoTramite() + ", " +
                        "" + tramite.getCodSolicitud() + ", " +
                        "" + tramite.getCodCargoOrigen() + ", " +
                        (( tramite.getUsuarioDestino() == null ) ? "null" : "'" + tramite.getUsuarioDestino().trim() + "'") + ", " +
                        "" + tramite.getCodCargoDestino() + ", " +
                        (( tramite.getDetalle() == null ) ? "null" : "'" + tramite.getDetalle().trim() + "'") + ")";

      

      return helpdesk.model.data.OperacionData.ejecutarSQL(cadSql);

   }

   /** Este método permite enviar a una solicitud para su atención por parte del Service Desk.
    *
    * El Service Desk es aquella persona que atiende en primera instancia la solicitud.
    *
    * @param miTramite Detalle del tramite ha realizar con la solicitud
    * @pdOid e9738a83-ed22-4a5e-89d6-7be77921c2fd */
   public boolean isEnviadoParaAtencion(Tramite miTramite) {
      // TODO: implement

      if ( miTramite.getCodCargoOrigen() <= 0 || miTramite.getCodSolicitud() <= 0 ||
           miTramite.getCodTipoTramite() != ValorTipoTramite.ENVIO_ATENCION.getCodigo() || 
           miTramite.getFecha() == null || miTramite.getUsuarioOrigen() == null ) { 
      return false ; 
      }
      
      String cadSql = "INSERT INTO helpdesk.tramite( " +    
                        "usuario_origen, " +
                        "fecha, " +
                        "tipo_tramite_id, " +
                        "solicitud_id, " +
                        "cargo_id_origen, " +
                        "usuario_detino, " +
                        "cargo_id_destino, " +
                        "detalle) " +
                        "VALUES (" +
                        "'" + miTramite.getUsuarioOrigen() + "', " +
                        "'" + miTramite.getFecha() + "', " +
                        "" + miTramite.getCodTipoTramite() + ", " +
                        "" + miTramite.getCodSolicitud() + ", " +
                        "" + miTramite.getCodCargoOrigen() + ", " +
                        (( miTramite.getUsuarioDestino() == null ) ? "null" : "'" + miTramite.getUsuarioDestino().trim() + "'") + ", " +    
                        (( miTramite.getCodCargoDestino() <= 0 ) ? "null" : "" + miTramite.getCodCargoDestino() + "") + ", " + 
                        (( miTramite.getDetalle() == null ) ? "null" : "'" + miTramite.getDetalle().trim() + "'") + ")";

      //System.out.println(cadSql);

      return helpdesk.model.data.OperacionData.ejecutarSQL(cadSql) ;
      
   }
   
   /** Permite obtener el jefe inmediato del trabajador
    *
    * @param cargo
    * @pdOid 84d93a03-2fb2-4d75-ac57-5af60b604562 */
   public Trabajador getJefeInmediato(Cargo cargo) {
      // TODO: implement

     if ( cargo == null ) return null ;

     Cargo cargoJefe = cargo.getCargoJefe();

     if ( cargoJefe == null ) return null ;

     ArrayList<Trabajador> trabajadores = cargoJefe.getTrabajadores();

     if ( trabajadores == null ) return null ;

     if ( trabajadores.size() <= 0 ) return null ;

     Trabajador trabajadorFijo = trabajadores.get(0);

     Trabajador trabRempl = trabajadorFijo.getTrabajadorRemplazo();

     if ( trabRempl == null  ) return trabajadorFijo;

      return trabRempl ;

   }

   /** Retorna las solicitudes que se encuentran pendientes de atención
    *
    * @pdOid e86d5615-b33b-4866-865a-2631416ec06f */
   public java.util.ArrayList<Solicitud> getSolicPendAtencion() {
      // TODO: implement
      return null;
   }

   /** Permite registrar una solicitud en el sistema
    *
    * @param solicitud Solicitud que se desea ser registrada
    * @param archivos Archivos que se desean que sean adjuntados a la solicitud que se esta enviando.
    * @param conVisto Permite saber si la solicitud antes va a ser enviada para que reciba el visto bueno del jefe inmediato de la persona que generó la solicitud
    * @exception helpdesk.model.HelpDeskException
    * @pdOid ae9a0a49-7b83-4e79-b85e-8a369d54582d */
   public void guardarSolicitud(Solicitud solicitud, java.util.ArrayList<Archivo> archivos, boolean conVisto) throws helpdesk.model.HelpDeskException {
      // TODO: implement

      if ( solicitud == null ) throw new HelpDeskException("Falta especificar la solicitud");

        if (!((solicitud.getTrabajador()!=null)&&(solicitud.getCargo()!= null) &&
                (solicitud.getAsunto()!= null)&&(solicitud.getFecha()!= null) &&
                (solicitud.getDetalle()!= null)&&(solicitud.getTipoSolicitud()!= null)&&
                (solicitud.getTrabajador().getUsuario()!=null)))
            throw new HelpDeskException("Falta completar datos en la solicitud");

        //java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd k:m:s");
      
       java.sql.Connection cnx = helpdesk.model.data.Conexion.getMiConexion();
       java.sql.PreparedStatement ps;

        String insertSQL= " INSERT INTO helpdesk.solicitud( "
                            + "usuario, "
                            + "asunto, "
                            + "fecha, "
                            + "detalle, "
                            + "tipo_solicitud_id, "
                            + "adjuntos, "
                            + "cargo_id) "
                            + "VALUES ( "
                            + "?, "
                            + "?, "
                            + "?, "
                            + "?, "
                            + "?, "
                            + "?, "
                            + "? ) RETURNING solicitud_id ";        
        
        java.sql.ResultSet rstConsulta = null ;
        int codSolicReg = 0 ; 
        
        try {
            ps = cnx.prepareStatement(insertSQL);
            ps.setString(1, solicitud.getTrabajador().getUsuario());
            ps.setString(2, solicitud.getAsunto());
            ps.setTimestamp(3, new java.sql.Timestamp(solicitud.getFecha().getTime()));
            ps.setString(4, solicitud.getDetalle());
            ps.setInt(5, solicitud.getTipoSolicitud().getCodigo());
            ps.setString(6, ( (solicitud.getAdjuntos() == null) ? null : solicitud.getAdjuntos() ));
            ps.setInt(7, solicitud.getCargo().getCodCargo());            
            rstConsulta = ps.executeQuery();
            rstConsulta.next();
            codSolicReg = rstConsulta.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Trabajador.class.getName()).log(Level.SEVERE, null, ex);
            throw new HelpDeskException("La solicitud no pudo ser registrada");
        }
       
       if ( archivos != null &&  archivos.size() > 0 ) {

           Iterator<Archivo> i = archivos.iterator();
           String[] cadSql = new String[archivos.size()];

           int indice = 0 ;
           
            String cadSqlFile = " INSERT INTO helpdesk.archivo( " +
                             "archivo, " +
                             "nombre, " +
                             "solicitud_id, " +
                             "tipo_contenido, " +
                             "fecha) " +
                             "VALUES ( " +
                             "?, " +
                             "?, " +
                             "?, " +
                             "?, " +
                             "?) ";            

            while ( i.hasNext() ) {
            Archivo miArchivo = i.next();

            if ( miArchivo.getArchivo() == null || miArchivo.getTipoContenido() == null ||
                 miArchivo.getFecha() == null || miArchivo.getNombre() == null ) {            
            Solicitud.eliminarBD(codSolicReg);
            throw new HelpDeskException("Falta completar datos en los archivos que se desea subir");
            }          
            
            miArchivo.setCodSolicitud(codSolicReg);

            cadSql[indice] = " INSERT INTO helpdesk.archivo( " +
                             "archivo, " +
                             "nombre, " +
                             "solicitud_id, " +
                             "tipo_contenido, " +
                             "fecha) " +
                            "VALUES ( " +
                            "'" + miArchivo.getArchivo() + "', " +
                            "'" + miArchivo.getNombre() + "', " +
                            "" +  miArchivo.getCodSolicitud() + ", " +
                            "'" + miArchivo.getTipoContenido() + "', " +
                            "'" + miArchivo.getFecha() + "') ";

            indice++;
            }

            if ( OperacionData.ejecutarBloqueSQL(cadSql) == false  ) {
            Solicitud.eliminarBD(codSolicReg);
            throw new HelpDeskException("El archivo enviado no pudo ser guardado");
           }

       }       

       if ( conVisto == true ) {

           try {

           Tramite miTramite = new Tramite();
           Cargo cargoJefe = solicitud.getCargo().getCargoJefe() ;

                if ( cargoJefe != null )    
                miTramite.setCodCargoDestino( cargoJefe.getCodCargo() );
                else
                miTramite.setCodCargoDestino( solicitud.getCargo().getCodCargo() );
           
           miTramite.setCodCargoOrigen(solicitud.getCargo().getCodCargo());

           miTramite.setCodSolicitud(codSolicReg);
           miTramite.setCodTipoTramite(ValorTipoTramite.ENVIO_VISTO_BUENO.getCodigo());
           miTramite.setFecha( solicitud.getFecha() );
           /*
           Trabajador jefe = solicitud.getTrabajador().getJefeInmediato(solicitud.getCargo());

                if ( jefe != null )
                miTramite.setUsuarioDestino( jefe.getUsuario() );
                else
                miTramite.setUsuarioDestino(solicitud.getTrabajador().getUsuario());
           */
           miTramite.setUsuarioOrigen(solicitud.getTrabajador().getUsuario());

               if ( !this.isEnviadoVistoBueno(miTramite) ) {
               Solicitud.eliminarBD(codSolicReg);
               throw new HelpDeskException("El archivo enviado no pudo ser enviado para visto bueno");               
               }

           }
           catch(NullPointerException e ) {
           Solicitud.eliminarBD(codSolicReg);
           //System.out.println(e.getMessage());
           throw new HelpDeskException("El archivo enviado no pudo ser enviado para visto bueno");
           }

       }
       else {

           try {

           Tramite miTramite = new Tramite();
           miTramite.setCodCargoOrigen(solicitud.getCargo().getCodCargo());
           miTramite.setCodSolicitud(codSolicReg);
           miTramite.setCodTipoTramite(ValorTipoTramite.ENVIO_ATENCION.getCodigo());
           miTramite.setDetalle("Enviado para su atención por algún Service Desk");
           miTramite.setFecha( solicitud.getFecha() );
           miTramite.setUsuarioOrigen(solicitud.getTrabajador().getUsuario());

                if ( !this.isEnviadoParaAtencion(miTramite) ) {
                Solicitud.eliminarBD(codSolicReg);
                throw new HelpDeskException("El archivo enviado no pudo ser enviado para su atención");                
                }

           }
           catch( NullPointerException e  ) {
           Solicitud.eliminarBD(codSolicReg);
           //System.out.println(e.getMessage());
           throw new HelpDeskException("El archivo enviado no pudo ser enviado para su atención");
           }

       }

   }

   /** Permite obtener un trabajador que se ha registrado en la base de datos.
    *
    * @param usuario Nombre del usuario.
    * @pdOid 21a2e38b-6a6a-4635-9306-34e01fbafab5 */
   public static Trabajador getTrabajadorBD(String usuario) {
       return TrabajadorFlyweightFactory.getTrabajador(usuario);
   }

   /** Permite obtener el trabajador que lo esta remplazando de manera temporal.
    *
    * @pdOid 54bc3224-963c-461e-879a-9c93cb8710c2 */
   public Trabajador getTrabajadorRemplazo() {
      // TODO: implement

      if ( this.usuario == null ) throw new IllegalStateException("Falta especificar el nombre del usuario del trabajador") ;

      if ( this.cargo == null ) throw new IllegalStateException("Falta especificar el cargo del trabajador") ;

      String cadSql = "select usuario_remplazo from remplazo where "
                        + " usuario='" + this.usuario + "' "
                        + " and now()::date <= fecha_fin::date " ;

     helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

     if ( consulta.getNumFilas() <= 0 ) return null ;

     String usuarioRemplazo = consulta.getResultados()[0][0].toString().trim();

     if ( usuarioRemplazo.length() <= 0 ) return null;

     Trabajador trab = Trabajador.getTrabajadorBD(usuarioRemplazo);

     //trab.setCargo( this.cargo );

     return trab;

   }

   /** Permite devolver una solicitud.
    *
    * @param solicitud Solicitud que se desea derivar
    * @param cargo Cargo con el cual el trabajador esta devolviendo la solicitud
    * @param detalle Detalle que se desea especificar al momento que se devuelve la solicitud
    * @param archivo detalle del archivo que explica porque la solicitud fue devuelta
    * @exception helpdesk.model.HelpDeskException
    * @pdOid 9e838a92-3d74-4ab2-8874-d5e62b5be0bc */
   public void devolverSolicitud(Solicitud solicitud, Cargo cargo, String detalle, TramiteArchivo archivoTramite) throws helpdesk.model.HelpDeskException {
      // TODO: implement

       if ( this.getUsuario() == null ) {
           throw new helpdesk.model.HelpDeskException("Falta especificar el nombre del usuario que devuelve la solicitud ");
       }


       if ( solicitud == null ) {
           throw new helpdesk.model.HelpDeskException("Falta especificar la solicitud");
       }

   Tramite ultimoTramite = solicitud.getUltimoTramite();
   ArrayList<Tramite> misTramites = solicitud.getTramites(); 

   
        if ( ultimoTramite == null ) {
            throw new helpdesk.model.HelpDeskException("Esta solicitud no tiene tramites previos");
        }

   String cadSql = null ;
   Tramite nuevoTramite = null; 

       /* if ( ultimoTramite.getTipoTramite().getCodigo() == ValorTipoTramite.ENVIO_VISTO_BUENO.getCodigo() ||
             ultimoTramite.getTipoTramite().getCodigo() == ValorTipoTramite.ENVIO_ATENCION.getCodigo() ||
             ultimoTramite.getTipoTramite().getCodigo() == ValorTipoTramite.DERIVAR.getCodigo() ) {*/
        nuevoTramite = new Tramite(); 
        nuevoTramite.setCodCargoDestino( solicitud.getCargo().getCodCargo() );
        nuevoTramite.setCodCargoOrigen( cargo.getCodCargo() );
        nuevoTramite.setCodSolicitud( solicitud.getCodSolicitud() );
        nuevoTramite.setCodTipoTramite(ValorTipoTramite.DEVOLVER.getCodigo());
        nuevoTramite.setDetalle(detalle);
        nuevoTramite.setFecha(new Date());
        nuevoTramite.setUsuarioDestino( solicitud.getTrabajador().getUsuario() );
        nuevoTramite.setUsuarioOrigen(this.getUsuario());
        /*}
        else {*/
        /*nuevoTramite = new Tramite();
        nuevoTramite.setCodCargoDestino( ultimoTramite.getCodCargoOrigen() );
        nuevoTramite.setCodCargoOrigen( cargo.getCodCargo() );
        nuevoTramite.setCodSolicitud( solicitud.getCodSolicitud() ) ;        
        nuevoTramite.setCodTipoTramite(ValorTipoTramite.DEVOLVER.getCodigo());        
        nuevoTramite.setDetalle(detalle);
        nuevoTramite.setFecha(new Date());
        nuevoTramite.setUsuarioDestino( ultimoTramite.getUsuarioOrigen() );
        nuevoTramite.setUsuarioOrigen(this.getUsuario());*/
        
        /*}*/

           cadSql = "INSERT INTO helpdesk.tramite( " +
                     "usuario_origen, " +
                    "fecha, " +
                    "tipo_tramite_id, " +
                    "solicitud_id, " +
                    "cargo_id_origen, " +
                    "usuario_detino, " +
                    "cargo_id_destino, " +
                    "detalle) " +
                    "VALUES ( " +
                    "'" + nuevoTramite.getUsuarioOrigen() + "', " +
                    "'" + nuevoTramite.getFecha() + "', " +
                    "" + nuevoTramite.getTipoTramite().getCodigo() + ", " +
                    "" + nuevoTramite.getCodSolicitud() + ", " +
                    "" + nuevoTramite.getCodCargoOrigen() + ", " +
                    "'" + nuevoTramite.getUsuarioDestino() + "', " +
                    "" + nuevoTramite.getCodCargoDestino() + ", " +
                    (( nuevoTramite.getDetalle() == null ) ? "null" : "'" + nuevoTramite.getDetalle() + "'" ) + ") "
                    + " RETURNING tramite_id " ;

           ConsultaData miConsulta = new ConsultaData(cadSql);

           if ( miConsulta.getNumFilas() <= 0 ) {
           throw new helpdesk.model.HelpDeskException("La solicitud no pudo ser devuelta ...");
           }

           int codTramiteRegistrado = Integer.parseInt(miConsulta.getResultados()[0][0].toString().trim());

            if ( archivoTramite != null ) {
            archivoTramite.setCodTramite(codTramiteRegistrado);

                if (archivoTramite.getArchivo() == null || archivoTramite.getNombre() == null
                     || archivoTramite.getTipoContenido() == null )
                    throw new HelpDeskException("Falta completar datos en el archivo que se desea adjuntar");

            cadSql = " INSERT INTO helpdesk.tramite_archivo( " +
                             "archivo, " +
                             "nombre, " +
                             "tramite_id, " +
                             "tipo_contenido) " +
                            "VALUES ( " +
                            "'" + archivoTramite.getArchivo() + "', " +
                            "'" + archivoTramite.getNombre() + "', " +
                            "" +  archivoTramite.getCodTramite() + ", " +
                            "'" + archivoTramite.getTipoContenido() + "') ";

                if ( !OperacionData.ejecutarSQL(cadSql)) {
                cadSql = "from helpdesk.tramite where tramite_id=" + codTramiteRegistrado;

                    while ( new ConsultaData("select * " + cadSql).getNumFilas() > 0  )
                        OperacionData.ejecutarSQL("delete " + cadSql);

                throw new HelpDeskException("No se pudo adjuntar el archivo. No se pudo devolver la solicitud");

                }

            }

       
   }

    /** @param solicitud Solicitud que se desea rechazar
    * @param cargo Cargo con el cual el trabajador se esta rechazando la solicitud
    * @param detalle Detalle que se desea especificar al momento que se rechaza  la solicitud
    * @param archivoTramite detalle del archivo que explica porque la solicitud fue rechazada
    * @exception helpdesk.model.HelpDeskException
    * @pdOid efd7d8d8-04d5-48f1-89c4-de28509bcfa0 */
   public void rechazarSolicitud(Solicitud solicitud, Cargo cargo, String detalle, TramiteArchivo archivoTramite) throws helpdesk.model.HelpDeskException {
    // TODO: implement

       if ( solicitud == null )
           throw new helpdesk.model.HelpDeskException("Falta especificar la solicitud ...");

       if (Solicitud.getSolicitudBD(solicitud.getCodSolicitud()) == null ) 
           throw new helpdesk.model.HelpDeskException("La solicitud especificada no existe ...");

       if ( solicitud.getCargo() == null )
           throw new helpdesk.model.HelpDeskException("Falta especificar el cargo con la que la solicitud fue registrada ...");

       if ( solicitud.getTrabajador() == null )
           throw new helpdesk.model.HelpDeskException("Falta especificar el trabajador que registro la solicitud ...");

       if ( detalle == null || detalle.trim().length() <= 0 )
            throw new helpdesk.model.HelpDeskException("Falta especificar el motivo por el cual se rechaza la solicitud ...");

       if ( cargo == null )
           throw new helpdesk.model.HelpDeskException("Falta especificar el cargo con el que se rechaza la solicitud ...");

       if ( this.getUsuario() == null )
           throw new helpdesk.model.HelpDeskException("Falta especificar el nombre de usuario que rechaza la solicitud ...");
       
   Tramite ultimoTramite = solicitud.getUltimoTramite();

        if ( ultimoTramite == null ) {
            throw new helpdesk.model.HelpDeskException("La solicitud no ha tenido tramites previos, no puede ser rechazada ...");
        }

        if ( !(ultimoTramite.getTipoTramite().getCodigo() == ValorTipoTramite.ENVIO_VISTO_BUENO.getCodigo() ||
             ultimoTramite.getTipoTramite().getCodigo() == ValorTipoTramite.ENVIO_ATENCION.getCodigo()) )
            throw new helpdesk.model.HelpDeskException("La solicitud solo puede ser rechazada cuando ha sido enviada para recibir atención o visto bueno ...");

   String cadSql = null ;
   Tramite nuevoTramite = null;

        nuevoTramite = new Tramite();
        nuevoTramite.setCodCargoDestino( solicitud.getCargo().getCodCargo() );
        nuevoTramite.setCodCargoOrigen( cargo.getCodCargo() );
        nuevoTramite.setCodSolicitud( solicitud.getCodSolicitud() ) ;
        nuevoTramite.setCodTipoTramite(ValorTipoTramite.RECHAZAR.getCodigo());
        nuevoTramite.setDetalle(detalle);
        nuevoTramite.setFecha(new Date());
        nuevoTramite.setUsuarioDestino( solicitud.getTrabajador().getUsuario() );
        nuevoTramite.setUsuarioOrigen(this.getUsuario());

           cadSql = "INSERT INTO helpdesk.tramite( " +
                     "usuario_origen, " +
                    "fecha, " +
                    "tipo_tramite_id, " +
                    "solicitud_id, " +
                    "cargo_id_origen, " +
                    "usuario_detino, " +
                    "cargo_id_destino, " +
                    "detalle) " +
                    "VALUES ( " +
                    "'" + nuevoTramite.getUsuarioOrigen() + "', " +
                    "'" + nuevoTramite.getFecha() + "', " +
                    "" + nuevoTramite.getTipoTramite().getCodigo() + ", " +
                    "" + nuevoTramite.getCodSolicitud() + ", " +
                    "" + nuevoTramite.getCodCargoOrigen() + ", " +
                    "'" + nuevoTramite.getUsuarioDestino() + "', " +
                    "" + nuevoTramite.getCodCargoDestino() + ", " +
                    (( nuevoTramite.getDetalle() == null ) ? "null" : "'" + nuevoTramite.getDetalle() + "'" ) + ") "
                    + " RETURNING tramite_id ";

           ConsultaData miConsulta = new ConsultaData(cadSql);

           if ( miConsulta.getNumFilas() <= 0 ) {
           throw new helpdesk.model.HelpDeskException("La solicitud no pudo ser rechazada ...");
           }

           int codTramiteRegistrado = Integer.parseInt(miConsulta.getResultados()[0][0].toString().trim());

            if ( archivoTramite != null ) {
            archivoTramite.setCodTramite(codTramiteRegistrado);

                if (archivoTramite.getArchivo() == null || archivoTramite.getNombre() == null
                     || archivoTramite.getTipoContenido() == null ) {

                    cadSql = "from helpdesk.tramite where tramite_id=" + codTramiteRegistrado;

                    while ( new ConsultaData("select * " + cadSql).getNumFilas() > 0  )
                        OperacionData.ejecutarSQL("delete " + cadSql);  

                throw new HelpDeskException("Falta completar datos en el archivo que se desea adjuntar"); 

                }
                    

            cadSql = " INSERT INTO helpdesk.tramite_archivo( " +
                             "archivo, " +
                             "nombre, " +
                             "tramite_id, " +
                             "tipo_contenido) " +
                            "VALUES ( " +
                            "'" + archivoTramite.getArchivo() + "', " +
                            "'" + archivoTramite.getNombre() + "', " +
                            "" +  archivoTramite.getCodTramite() + ", " +
                            "'" + archivoTramite.getTipoContenido() + "') ";

                if ( !OperacionData.ejecutarSQL(cadSql)) {
                cadSql = "from helpdesk.tramite where tramite_id=" + codTramiteRegistrado;

                    while ( new ConsultaData("select * " + cadSql).getNumFilas() > 0  )
                        OperacionData.ejecutarSQL("delete " + cadSql);

                throw new HelpDeskException("No se pudo adjuntar el archivo. No se pudo devolver la solicitud");

                }

            }
           

   }

   /** Permite reenviar una solicitud. La soliicitud cuando es reenviada, se enviar para que se de la atención
    * respectiva por parte del service desk, o se envia para que reciba nuevamente el visto bueno.
    * Para tomar en cuenta esta decisión al momento de reenviar se observa el primer tramite registrado.
    *
    * @param solicitud Solicitud que se desea reenviar
    * @param detalle Detalle que se desea especificar al momento de reenviar  la solicitud
    * @param archivoTramite detalle del archivo que da información adicional al momento de reenviar la solicitud
    * @exception helpdesk.model.HelpDeskException
    * @pdOid 7bf47784-9745-4db4-a0a6-f5b487d9640c */
   public void reenviarSolicitud(Solicitud solicitud, String detalle, TramiteArchivo archivoTramite)
    throws helpdesk.model.HelpDeskException {
      // TODO: implement
      if (solicitud == null || detalle == null )
          throw new IllegalArgumentException("Falta completar informacion");

      if (detalle.trim().length() <= 0)
          throw new IllegalArgumentException("Falta especificar el detalle del cierre de la solicitud");

      if ( solicitud.getCodSolicitud() <= 0 )
          throw new IllegalArgumentException("Falta indica el codigo de la solicitud");

      if ( solicitud.getTrabajador() == null )
          throw new IllegalArgumentException("Falta especificar el trabajador que genero la solicitud");

      if ( solicitud.getTrabajador().getUsuario() == null  )
          throw new IllegalArgumentException("Falta especificar el nombre  de usuario "
                                                    + " trabajador que genero la solicitud");

      if ( solicitud.getCargo() == null  )
          throw new IllegalArgumentException("Falta especificar el cargo que tuvo el trabajador "
                                                + "al momento de generar la solicitud");

      if ( solicitud.getCargo().getCodCargo() <= 0  )
          throw new IllegalArgumentException("Falta especificar el código del cargo que tuvo el trabajador "
                                                + "al momento de generar la solicitud");

      if ( archivoTramite != null && (archivoTramite.getArchivo() == null || 
           archivoTramite.getNombre() == null || archivoTramite.getTipoContenido() == null)  )
          throw new IllegalArgumentException("Falta completar datos en el archivo que se desea adjuntar");

       Tramite ultimoTramite = solicitud.getUltimoTramite();
   
       if ( ultimoTramite == null )
            throw new helpdesk.model.HelpDeskException("Esta solicitud no tiene tramites previos");

        Tramite nuevoTramite = null;

        if ( ultimoTramite.getTipoTramite().getCodigo() != ValorTipoTramite.DEVOLVER.getCodigo()){
            throw new helpdesk.model.HelpDeskException("El ultimo tramite de la solicitud no ha sido una Devolución");
        }
            
        String cadSql1=" select tramite_id " +
                        " from helpdesk.tramite where solicitud_id=" + solicitud.getCodSolicitud() +
                        " order by tramite_id asc limit 1 offset 0";


        ConsultaData consulta = new ConsultaData(cadSql1);

        Tramite primerTramite=Tramite.getTramiteBD(Integer.parseInt(consulta.getResultados()[0][0].toString()));

        nuevoTramite = new Tramite();
        nuevoTramite.setCodCargoDestino( primerTramite.getCodCargoDestino());
        nuevoTramite.setCodCargoOrigen( solicitud.getCargo().getCodCargo());
        nuevoTramite.setCodSolicitud( solicitud.getCodSolicitud());
        nuevoTramite.setCodTipoTramite(primerTramite.getCodTipoTramite());
        nuevoTramite.setDetalle(detalle);
        nuevoTramite.setFecha(new Date());
        nuevoTramite.setUsuarioDestino(primerTramite.getUsuarioDestino());
        nuevoTramite.setUsuarioOrigen(solicitud.getTrabajador().getUsuario());            

        String cadSql = null;
        cadSql = "INSERT INTO helpdesk.tramite( " +
                     "usuario_origen, " +
                    "fecha, " +
                    "tipo_tramite_id, " +
                    "solicitud_id, " +
                    "cargo_id_origen, " +
                    "usuario_detino, " +
                    "cargo_id_destino, " +
                    "detalle) " +
                    "VALUES ( " +
                    "'" + nuevoTramite.getUsuarioOrigen() + "', " +
                    "'" + nuevoTramite.getFecha() + "', " +
                    "" + nuevoTramite.getTipoTramite().getCodigo() + ", " +
                    "" + nuevoTramite.getCodSolicitud() + ", " +
                    "" + nuevoTramite.getCodCargoOrigen() + ", " +
                    (( nuevoTramite.getUsuarioDestino() == null ) ? "null" : "'" + nuevoTramite.getUsuarioDestino() + "'" ) + ", " +
                    (( nuevoTramite.getCodCargoDestino() <= 0 ) ? "null" : nuevoTramite.getCodCargoDestino() ) + ", " +
                    (( nuevoTramite.getDetalle() == null ) ? "null" : "'" + nuevoTramite.getDetalle() + "'" ) + ") "
                    + " RETURNING tramite_id " ;

           ConsultaData miConsulta = new ConsultaData(cadSql);

            if ( miConsulta.getNumFilas() <= 0 )
               throw new helpdesk.model.HelpDeskException("La solicitud no pudo ser reenviada ...");

            int codTramiteRegistrado = Integer.parseInt(miConsulta.getResultados()[0][0].toString().trim());

            if ( archivoTramite != null ) {
                archivoTramite.setCodTramite(codTramiteRegistrado);

                if (archivoTramite.getArchivo() == null || archivoTramite.getNombre() == null
                     || archivoTramite.getTipoContenido() == null )
                        throw new HelpDeskException("Falta completar datos en el archivo que se desea adjuntar");

                cadSql = " INSERT INTO helpdesk.tramite_archivo( " +
                             "archivo, " +
                             "nombre, " +
                             "tramite_id, " +
                             "tipo_contenido) " +
                            "VALUES ( " +
                            "'" + archivoTramite.getArchivo() + "', " +
                            "'" + archivoTramite.getNombre() + "', " +
                            "" +  archivoTramite.getCodTramite() + ", " +
                            "'" + archivoTramite.getTipoContenido() + "') ";

                if ( !OperacionData.ejecutarSQL(cadSql)) {
                    cadSql = "from helpdesk.tramite where tramite_id=" + codTramiteRegistrado;

                    while ( new ConsultaData("select * " + cadSql).getNumFilas() > 0  )
                        OperacionData.ejecutarSQL("delete " + cadSql);

                        throw new HelpDeskException("No se pudo adjuntar el archivo. No se pudo reenviar la solicitud");

                }

            }

       
   }

   /** @param solicitud Solicitud que se desea cerrar
    * @param detalle Detalle informativo al momento del cierre de la solicitud
    * @exception helpdesk.model.HelpDeskException
    * @pdOid 5c7ab7ab-777a-493e-a759-66fe74cc4e10 */
   public void cerrarSolicitud(Solicitud solicitud, String detalle) throws helpdesk.model.HelpDeskException {
      // TODO: implement

      if (solicitud == null || detalle == null )
          throw new IllegalArgumentException("Falta completar informacion");

      if (detalle.trim().length() <= 0)
          throw new IllegalArgumentException("Falta especificar el detalle del cierre de la solicitud");

      if ( solicitud.getCodSolicitud() <= 0 )
          throw new IllegalArgumentException("Falta indica el codigo de la solicitud");

      if ( solicitud.getTrabajador() == null )
          throw new IllegalArgumentException("Falta especificar el trabajador que genero la solicitud");

      if ( solicitud.getTrabajador().getUsuario() == null  )
          throw new IllegalArgumentException("Falta especificar el nombre  de usuario "
                                                    + " trabajador que genero la solicitud");

      if ( solicitud.getCargo() == null  )
          throw new IllegalArgumentException("Falta especificar el cargo que tuvo el trabajador "
                                                + "al momento de generar la solicitud");

      if ( solicitud.getCargo().getCodCargo() <= 0  )
          throw new IllegalArgumentException("Falta especificar el codigo del cargo que tuvo el trabajador "
                                                + "al momento de generar la solicitud");



     Tramite miUltimoTramite = solicitud.getUltimoTramite();

     if ( miUltimoTramite == null )
         throw new helpdesk.model.HelpDeskException("No se puede cerrar una solicitud que no "
                                                    + "ha tenido tramites previos");

     if ( miUltimoTramite.getTipoTramite().getCodigo() != ValorTipoTramite.CONFORMIDAD_USUARIO.getCodigo()  )
        throw new helpdesk.model.HelpDeskException("Solo se pueden cerrar la solicitudes que han sido enviadas "
                                                    + "para recibir conformidad del usuario");

    Tramite nuevoTramite = new Tramite(); 
    nuevoTramite.setCodCargoDestino( miUltimoTramite.getCodCargoOrigen() );
    nuevoTramite.setCodCargoOrigen( solicitud.getCargo().getCodCargo() );
    nuevoTramite.setCodSolicitud( solicitud.getCodSolicitud() ) ;
    nuevoTramite.setCodTipoTramite( ValorTipoTramite.CERRAR.getCodigo());
    nuevoTramite.setDetalle(detalle);
    nuevoTramite.setFecha(new Date());
    nuevoTramite.setUsuarioDestino( miUltimoTramite.getUsuarioOrigen() );
    nuevoTramite.setUsuarioOrigen( solicitud.getTrabajador().getUsuario() );

    String cadSql = "INSERT INTO helpdesk.tramite( " +
                     "usuario_origen, " +
                    "fecha, " +
                    "tipo_tramite_id, " +
                    "solicitud_id, " +
                    "cargo_id_origen, " +
                    "usuario_detino, " +
                    "cargo_id_destino, " +
                    "detalle) " +
                    "VALUES ( " +
                    "'" + nuevoTramite.getUsuarioOrigen() + "', " +
                    "'" + nuevoTramite.getFecha() + "', " +
                    "" + nuevoTramite.getTipoTramite().getCodigo() + ", " +
                    "" + nuevoTramite.getCodSolicitud() + ", " +
                    "" + nuevoTramite.getCodCargoOrigen() + ", " +
                    "'" + nuevoTramite.getUsuarioDestino() + "', " +
                    "" + nuevoTramite.getCodCargoDestino() + ", " +
                    (( nuevoTramite.getDetalle() == null ) ? "null" : "'" + nuevoTramite.getDetalle() + "'" ) + ") "
                    + " RETURNING tramite_id ";


    ConsultaData miConsulta = new ConsultaData(cadSql);

        if  ( miConsulta.getNumFilas() <= 0 )
           throw new helpdesk.model.HelpDeskException("La solicitud no ha podido ser cerrada");
       
   }

   /** Permite devolver una solicitud que ha sido enviada para dar conformidad de usuario.
    *
    * @param solicitud Solicitud que se desea devolver
    * @param detalle Detalle que se desea especificar al momento de devolver la solicitud
    * @param archivoTramite detalle del archivo que da información adicional al momento de devolver la solicitud
    * @exception helpdesk.model.HelpDeskException
    * @pdOid a40ea620-9bf8-4c23-b93c-fd50efe19562 */
   public void devolverConformidad(Solicitud solicitud, String detalle, TramiteArchivo archivoTramite) throws helpdesk.model.HelpDeskException {
      // TODO: implement

      if (solicitud == null || detalle == null )
          throw new IllegalArgumentException("Falta completar informacion");

      if (detalle.trim().length() <= 0)
          throw new IllegalArgumentException("Falta especificar el detalle del cierre de la solicitud");

      if ( solicitud.getCodSolicitud() <= 0 )
          throw new IllegalArgumentException("Falta indica el codigo de la solicitud");

      if ( solicitud.getTrabajador() == null )
          throw new IllegalArgumentException("Falta especificar el trabajador que genero la solicitud");

      if ( solicitud.getTrabajador().getUsuario() == null  )
          throw new IllegalArgumentException("Falta especificar el nombre  de usuario "
                                                    + " trabajador que genero la solicitud");

      if ( solicitud.getCargo() == null  )
          throw new IllegalArgumentException("Falta especificar el cargo que tuvo el trabajador "
                                                + "al momento de generar la solicitud");

      if ( solicitud.getCargo().getCodCargo() <= 0  )
          throw new IllegalArgumentException("Falta especificar el codigo del cargo que tuvo el trabajador "
                                                + "al momento de generar la solicitud");

      if ( archivoTramite != null && (archivoTramite.getArchivo() == null || 
           archivoTramite.getNombre() == null || archivoTramite.getTipoContenido() == null)  )
          throw new IllegalArgumentException("Falta completar datos en el archivo que se desea adjuntar");

     Tramite miUltimoTramite = solicitud.getUltimoTramite();

     if ( miUltimoTramite == null )
         throw new helpdesk.model.HelpDeskException("No se puede cerrar una solicitud que no "
                                                    + "ha tenido tramites previos");

     if ( miUltimoTramite.getTipoTramite().getCodigo() != ValorTipoTramite.CONFORMIDAD_USUARIO.getCodigo()  )
        throw new helpdesk.model.HelpDeskException("Solo se pueden devolver la solicitudes que han sido enviadas "
                                                    + "para recibir conformidad del usuario");

   String cadSql = null ;   

        Tramite nuevoTramite = new Tramite();
        nuevoTramite.setCodCargoDestino( miUltimoTramite.getCodCargoOrigen() );
        nuevoTramite.setCodCargoOrigen( solicitud.getCargo().getCodCargo() );
        nuevoTramite.setCodSolicitud( solicitud.getCodSolicitud() ) ;
        nuevoTramite.setCodTipoTramite( ValorTipoTramite.ENVIO_ATENCION.getCodigo());
        nuevoTramite.setDetalle(detalle);
        nuevoTramite.setFecha(new Date());
        nuevoTramite.setUsuarioDestino( miUltimoTramite.getUsuarioOrigen() );
        nuevoTramite.setUsuarioOrigen( solicitud.getTrabajador().getUsuario() );

           cadSql = "INSERT INTO helpdesk.tramite( " +
                     "usuario_origen, " +
                    "fecha, " +
                    "tipo_tramite_id, " +
                    "solicitud_id, " +
                    "cargo_id_origen, " +
                    "usuario_detino, " +
                    "cargo_id_destino, " +
                    "detalle) " +
                    "VALUES ( " +
                    "'" + nuevoTramite.getUsuarioOrigen() + "', " +
                    "'" + nuevoTramite.getFecha() + "', " +
                    "" + nuevoTramite.getTipoTramite().getCodigo() + ", " +
                    "" + nuevoTramite.getCodSolicitud() + ", " +
                    "" + nuevoTramite.getCodCargoOrigen() + ", " +
                    "'" + nuevoTramite.getUsuarioDestino() + "', " +
                    "" + nuevoTramite.getCodCargoDestino() + ", " +
                    (( nuevoTramite.getDetalle() == null ) ? "null" : "'" + nuevoTramite.getDetalle() + "'" ) + ") "
                    + " RETURNING tramite_id ";

           ConsultaData miConsulta = new ConsultaData(cadSql);

           if ( miConsulta.getNumFilas() <= 0 ) {
           throw new helpdesk.model.HelpDeskException("La solicitud no pudo ser devuelta ...");
           }

           int codTramiteRegistrado = Integer.parseInt(miConsulta.getResultados()[0][0].toString().trim());

            if ( archivoTramite != null ) {
            archivoTramite.setCodTramite(codTramiteRegistrado);

                if (archivoTramite.getArchivo() == null || archivoTramite.getNombre() == null
                     || archivoTramite.getTipoContenido() == null ) {

                    cadSql = "from helpdesk.tramite where tramite_id=" + codTramiteRegistrado;

                    while ( new ConsultaData("select * " + cadSql).getNumFilas() > 0  )
                        OperacionData.ejecutarSQL("delete " + cadSql);

                throw new HelpDeskException("Falta completar datos en el archivo que se desea adjuntar");

                }


            cadSql = " INSERT INTO helpdesk.tramite_archivo( " +
                             "archivo, " +
                             "nombre, " +
                             "tramite_id, " +
                             "tipo_contenido) " +
                            "VALUES ( " +
                            "'" + archivoTramite.getArchivo() + "', " +
                            "'" + archivoTramite.getNombre() + "', " +
                            "" +  archivoTramite.getCodTramite() + ", " +
                            "'" + archivoTramite.getTipoContenido() + "') ";

                if ( !OperacionData.ejecutarSQL(cadSql)) {
                cadSql = "from helpdesk.tramite where tramite_id=" + codTramiteRegistrado;

                    while ( new ConsultaData("select * " + cadSql).getNumFilas() > 0  )
                        OperacionData.ejecutarSQL("delete " + cadSql);

                throw new HelpDeskException("No se pudo adjuntar el archivo. No se pudo devolver la solicitud");

                }

            }

   }
   
   /** Registra un mensaje en la base de datos.
    *
    * @param mensaje Mensaje que se desea grabar en la base de datos
    * @exception helpdesk.model.HelpDeskException
    * @pdOid 24025103-b242-4aa0-b58c-075446cb8c01 */
   public void guardarMensaje(Mensaje mensaje) throws helpdesk.model.HelpDeskException {
        try {
            // TODO: implement

            if ( mensaje.getAsunto() == null || mensaje.getAsunto().trim().length() <=0 )
                throw new IllegalStateException("Falta especificar el asunto del mensaje");

            if ( mensaje.getCodTramite() <= 0  )
                  throw new IllegalStateException("Falta especificar el codigo del tramite");

            if ( mensaje.getTramite() == null )
                  throw new IllegalStateException("El tramite especificado no existe");

            if ( mensaje.getTramite().getTipoTramite().getCodigo() != ValorTipoTramite.ATENDER.getCodigo() )
                   throw new IllegalStateException("Solo se puede registrar si la solicitud esta en proceso de atencion");

            if ( mensaje.getDescripcion() == null || mensaje.getDescripcion().trim().length() <= 0  )
                  throw new IllegalStateException("Falta especificar la descripcion del mensaje");

            if ( mensaje.getFecRegistro() == null )
                  throw new IllegalStateException("Falta especificar la fecha de registro del mensaje");

            if ( mensaje.getNombreAdjunto() == null || mensaje.getNombreAdjunto().trim().length() <= 0 ) {

                if ( ( mensaje.getArchivoAdjunto() != null && mensaje.getArchivoAdjunto().trim().length() > 0 ) ||
                     ( mensaje.getTipoContenidoAdjunto() != null && mensaje.getTipoContenidoAdjunto().trim().length() > 0 ) )
                    throw new IllegalStateException("Falta especificar el nombre del archivo adjunto");

            }


            if ( mensaje.getArchivoAdjunto() == null || mensaje.getArchivoAdjunto().trim().length() <= 0 ) {

                if ( ( mensaje.getNombreAdjunto() != null && mensaje.getNombreAdjunto().trim().length() > 0 ) ||
                     ( mensaje.getTipoContenidoAdjunto() != null && mensaje.getTipoContenidoAdjunto().trim().length() > 0 ) )
                    throw new IllegalStateException("Falta especificar el archivo que se desea adjuntar");

            }

            if ( mensaje.getTipoContenidoAdjunto() == null || mensaje.getTipoContenidoAdjunto().trim().length() <= 0 ) {

                if ( ( mensaje.getNombreAdjunto() != null && mensaje.getNombreAdjunto().trim().length() > 0 ) ||
                      ( mensaje.getArchivoAdjunto() != null && mensaje.getArchivoAdjunto().trim().length() > 0 ) )
                    throw new IllegalStateException("Falta especificar el tipo de contenido del archivo que se desea adjuntar");
            }

            if ( mensaje.getFecRegistro() == null )
                  throw new IllegalStateException("Falta especificar la fecha de registro del mensaje");

            String cadSql = "INSERT INTO helpdesk.mensaje( " + "descripcion, " + "fec_registro, " + "usuario_origen, " + "usuario_destino, " + "tipo_contenido_adjunto, " + "nombre_adjunto, " + "archivo_adjunto, " + "tramite_id, " + "asunto) " + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            java.sql.Connection cnx = helpdesk.model.data.Conexion.getMiConexion();
            java.sql.PreparedStatement ps = cnx.prepareStatement(cadSql);

            ps.setString(1, mensaje.getDescripcion());
            ps.setTimestamp(2, new java.sql.Timestamp(mensaje.getFecRegistro().getTime()));
            ps.setString(3, mensaje.getUsuarioOrigen());
            ps.setString(4, mensaje.getUsuarioDestino());
            ps.setString(5, mensaje.getTipoContenidoAdjunto());
            ps.setString(6, mensaje.getNombreAdjunto());
            ps.setString(7, mensaje.getArchivoAdjunto());
            ps.setInt(8, mensaje.getCodTramite());
            ps.setString(9, mensaje.getAsunto());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Trabajador.class.getName()).log(Level.SEVERE, null, ex);
            throw new helpdesk.model.HelpDeskException("No se pudo registrar el mensaje");
        }
    


   }  
   
}