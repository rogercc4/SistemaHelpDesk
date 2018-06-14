/***********************************************************************
 * Module:  Tramite.java
 * Author:  Roger
 * Purpose: Defines the Class Tramite
 ***********************************************************************/

package helpdesk.model;

import java.util.*;

/** Representa los trámites por los cuales pasa una solicitud.
 *
 * @pdOid 47c01ec2-1a87-4cee-b449-ea79edc038a2 */
public class Tramite {
   /** Código que identifica de manera única un trámite
    *
    * @pdOid 72f56311-36b1-4c56-99bf-877f197f10eb */
   private int codTramite;
   /** Nombre de usuario del trabajador que generó el trámite.
    *
    * Por ejemplo si el trámite es enviar la solicitud para que reciba un visto bueno, entonces aquí debe ir el valor del nombre de usuario que envía esta solicitud.
    *
    * @pdOid f0dd49c5-22c8-4deb-89a0-d263cf2f1be0 */
   private String usuarioOrigen;
   /** Nombre de usuario del trabajador al cual se destina para atención del trámite iniciado.
    *
    * Por ejemplo; si el trámite es enviar la solicitud para que reciba un visto bueno, entonces aquí debe ir el valor del nombre de usuario al cual se le envía la solicitud para que de el visto bueno.
    *
    * @pdOid a5120fbc-0fb1-4411-91af-7079bf9ac500 */
   private String usuarioDestino;
   /** Código del cargo de la persona que envío la solicitud para que le den trámite.
    *
    * @pdOid 06f697cb-8ba0-4499-9012-1c2e75f14517 */
   private int codCargoOrigen;
   /** Código del cargo de la persona a la que se le envío la solicitud para que le de atención.
    *
    * @pdOid cddb4afd-a0f0-4b73-a37b-47bececfae44 */
   private int codCargoDestino;
   /** Fecha en la que se registro el trámite
    *
    * @pdOid ccd244ff-9353-47cc-bb9c-372c08bbd8f9 */
   private java.util.Date fecha;
   /** detalle del trámite
    *
    * @pdOid bab03353-2f84-42fd-b178-610d394ae961 */
   private java.lang.String detalle;
   /** Código de la solicitud a la que le ha dado trámite
    *
    * @pdOid 528cc8e5-10d1-4403-acbb-8ffae26eebbf */
   private int codSolicitud;
   /** Código del tipo de trámite
    *
    * @pdOid c129eb39-e64c-47c9-a843-a33f6f2c328e */
   private int codTipoTramite;

   /** Permite obtener el código del trámite
    *
    * @pdOid 4d211a29-c595-43f3-b7f4-36fd9c71122e */
   public int getCodTramite() {
      return codTramite;
   }

   /** Permite establecer el código del trámite
    *
    * @param newCodTramite Valor del código de trámite
    * @pdOid 3e22078e-9922-4d29-bfe0-b94d14739355 */
   public void setCodTramite(int newCodTramite) {
       if (newCodTramite<=0)
           throw new IllegalArgumentException("Ingrese un código de tramite valido");

      codTramite = newCodTramite;
   }

   /** Permite obtener la fecha en la que se registro el trámite
    *
    * @pdOid 7b5d490e-bd17-4286-9736-bab0b98634fe */
   public java.util.Date getFecha() {
      return fecha;
   }

   /** Permite establecer  la fecha en la que se registro el trámite
    *
    * @param newFecha valor de la  fecha en la que se registro el trámite
    * @pdOid c3a103aa-0840-4b75-91f9-17799cd97996 */
   public void setFecha(java.util.Date newFecha) {
       if (newFecha==null)
           throw new IllegalArgumentException("Ingrese una fecha de trámite valida");

      fecha = newFecha;
   }

   /** Permite obtener el detalle del trámite
    *
    * @pdOid bd02bf80-d669-4413-81d1-18483784936d */
   public java.lang.String getDetalle() {
      return detalle;
   }

   /** Permite establecer el detalle del trámite
    *
    * @param newDetalle valor del detalle del trámite
    * @pdOid 88cc37ef-4131-4480-a7ee-40bb85ef16ff */
   public void setDetalle(java.lang.String newDetalle) {
       if ( newDetalle==null || newDetalle.trim().length()<=0 )
            this.detalle = null ;
      else 
            detalle = newDetalle.trim();
   }

   /** Permite obtener el nombre de usuario del trabajador que generó el trámite.
    *
    * Por ejemplo si el trámite fue enviar la solicitud para que reciba un visto bueno, entonces aquí se obtiene el valor del nombre de usuario que envío esta solicitud.
    *
    * @pdOid 605214fa-9384-43f7-aab3-21831dff57de */
   public String getUsuarioOrigen() {
      return usuarioOrigen;
   }

   /** Permite establecer  el nombre de usuario del trabajador que generó el trámite.
    *
    * Por ejemplo si el trámite fue enviar la solicitud para que reciba un visto bueno, entonces aquí se coloca el valor del nombre de usuario que envío esta solicitud.
    *
    * @param newUsuarioOrigen Valor del nombre de usuario
    * @pdOid c3545d50-5e84-48f3-9132-3c29cfd3f36f */
   public void setUsuarioOrigen(String newUsuarioOrigen) {
       if (newUsuarioOrigen==null || newUsuarioOrigen.trim().length()<=0)
            throw new IllegalArgumentException("Ingrese un Nombre de Usuario de origen valido");

      usuarioOrigen = newUsuarioOrigen;
   }

   /** Permite obtener el nombre de usuario del trabajador al cual se destina para atención del trámite iniciado.
    *
    * Por ejemplo; si el trámite es enviar la solicitud para que reciba un visto bueno, entonces aquí se obtiene el nombre de usuario al cual se le envía la solicitud para que de el visto bueno.
    *
    * @pdOid 269d52d7-3f4c-406d-8636-daeabd0df918 */
   public String getUsuarioDestino() {
      return usuarioDestino;
   }

   /** Permite establecer el nombre de usuario del trabajador al cual se destina para atención del trámite iniciado.
    *
    * Por ejemplo; si el trámite es enviar la solicitud para que reciba un visto bueno, entonces aquí se coloca el nombre de usuario al cual se le envía la solicitud para que de el visto bueno.
    *
    * @param newUsuarioDestino Nombre del usuario de destino
    * @pdOid 051ed2b7-58e7-4953-b108-2ddfa95ba38f */
   public void setUsuarioDestino(String newUsuarioDestino) {
       if (newUsuarioDestino==null || newUsuarioDestino.trim().length()<=0)
            usuarioDestino = null;
       else
            usuarioDestino = newUsuarioDestino.trim();
      
   }

   /** Permite obtener el código de cargo de la persona que envío la solicitud para que le den trámite.
    *
    * @pdOid ec9759f8-5e57-4356-9c49-03cc07a6eb38 */
   public int getCodCargoOrigen() {
      return codCargoOrigen;
   }

   /** Permite establecer el código de cargo de la persona que envío la solicitud para que le den trámite.
    *
    * @param newCodCargoOrigen Valor del código de cargo de la persona que envío la solicitud para que le den trámite.
    * @pdOid bcb3892e-4c1d-4d67-9c55-495955df5db5 */
   public void setCodCargoOrigen(int newCodCargoOrigen) {
   if (newCodCargoOrigen<=0)
        throw new IllegalArgumentException("Falta especificar el codigo del cargo para el origen del tramite");

      codCargoOrigen = newCodCargoOrigen;
   }

   /** Permite obtener el código del cargo de la persona a la que se le envío la solicitud para que le de atención.
    *
    * @pdOid 7cc42dbd-e693-4150-b3ef-608274798bb1 */
   public int getCodCargoDestino() {    
      return codCargoDestino;
   }

   /** Permite establecer el código del cargo de la persona a la que se le envío la solicitud para que le de atención.
    *
    * @param newCodCargoDestino Código del cargo de la persona a la que se le envío la solicitud para que le de atención.
    * @pdOid 0e1c8822-2726-47cd-a3ee-8e932018f711 */
   public void setCodCargoDestino(int newCodCargoDestino) {
       
       if (newCodCargoDestino <= 0)
            codCargoDestino = 0;
       else
          codCargoDestino = newCodCargoDestino;
       
   }

   /** Permite obtener el código de la solicitud a la que le ha dado trámite
    *
    * @pdOid 3a00e37d-25b9-471c-9bbd-9388876c68c8 */
   public int getCodSolicitud() {
      return codSolicitud;
   }

   /** Permite establecer el código de la solicitud a la que le ha dado trámite
    *
    * @param newCodSolicitud código de la solicitud a la que le ha dado trámite
    * @pdOid 0c30f11c-ba64-49ef-80d2-ff81f1a32429 */
   public void setCodSolicitud(int newCodSolicitud) {
       if (newCodSolicitud<=0)
           throw new IllegalArgumentException("Falta definir el codigo de la solicitud para el tramite");

      codSolicitud = newCodSolicitud;
   }

   /** Permite obtener el código del tipo de trámite
    *
    * @pdOid 83e02cab-36bf-44c3-a45f-031563e16063 */
   public int getCodTipoTramite() {
      return codTipoTramite;
   }

   /** Permite establecer el código del tipo de trámite
    *
    * @param newCodTipoTramite Valor del código del tipo de trámite
    * @pdOid 7aa18848-4341-49a3-aba8-2bdf5dae9595 */
   public void setCodTipoTramite(int newCodTipoTramite) {
       if (newCodTipoTramite<=0)
           throw new IllegalArgumentException("Establecer un tipo de trámite valido");
      codTipoTramite = newCodTipoTramite;
   }

   /** Permite obtener un trámite registrado en la base de datos del sistema
    *
    * @param codigo Código del tramite que se desea obtener de la base de datos
    * @pdOid 1f220716-7b5e-4799-80b3-a5c01d49bce7 */
   public static Tramite getTramiteBD(int codigo) {
      // TODO: implement
      
     if ( codigo <= 0 ) return null ;
     
     String cadSql = "SELECT tramite_id, usuario_origen, fecha, tipo_tramite_id, solicitud_id, " +
                    "cargo_id_origen, usuario_detino, cargo_id_destino, detalle " +
                    "FROM helpdesk.tramite where tramite_id = " + codigo;

     helpdesk.model.data.ConsultaData miConsulta = new helpdesk.model.data.ConsultaData(cadSql);

     if ( miConsulta.getNumFilas() <= 0 ) return null ;

     Object[][] miRst = miConsulta.getResultados() ;
     Tramite miTramite = new Tramite(); 
     

        for ( Object[] fila : miRst ) {

            if ( fila[7] != null )
                miTramite.setCodCargoDestino(Integer.parseInt(fila[7].toString()));

            if ( fila[5] != null )
                miTramite.setCodCargoOrigen(Integer.parseInt(fila[5].toString()));

            miTramite.setCodSolicitud(Integer.parseInt(fila[4].toString()));

            miTramite.setCodTipoTramite(Integer.parseInt(fila[3].toString()));

            miTramite.setCodTramite(Integer.parseInt(fila[0].toString()));

            if ( fila[8] != null )
                miTramite.setDetalle(fila[8].toString());

            miTramite.setFecha((Date)fila[2]);

            if ( fila[6] != null )
                miTramite.setUsuarioDestino(fila[6].toString());
            
           if ( fila[1] != null )
                miTramite.setUsuarioOrigen(fila[1].toString());
            
        }

      return miTramite;
   }

   /** Permite obtener el objeto que representa al archivo asociado a este trámite.
    *
    * @pdOid 715281c6-a0f5-49de-a6b4-db5175eeb48f */
   public TramiteArchivo getArchivo() {
      // TODO: implement

      String cadSql = "select tramite_id, nombre, archivo, tipo_contenido from " +  
                      "helpdesk.tramite_archivo where tramite_id=" + this.codTramite ;

      helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

      if ( consulta.getNumFilas() <= 0 ) return null ;

      Object[][] resultado = consulta.getResultados() ;

      TramiteArchivo miArchivo = new TramiteArchivo();

          for ( Object[] fila : resultado ) {
          miArchivo.setArchivo(fila[2].toString().trim());
          miArchivo.setCodTramite(Integer.parseInt(fila[0].toString().trim()));
          miArchivo.setNombre(fila[1].toString().trim());
          miArchivo.setTipoContenido(fila[3].toString().trim());
          }
      

      return miArchivo;
   }

    /** Permite eliminar un trámite de la base de datos.
    *
    * @param codigo Código del trámite de la base de datos
    * @pdOid f30cc3cf-cbdd-4ffd-bf2b-5fc5dce9bb4a */
   public static boolean eliminarBD(int codigo) {
      // TODO: implement
      String[] cadSql = new String[2];

      cadSql[0] = "delete from helpdesk.tramite_archivo where tramite_id=" + codigo ;
      cadSql[1] = "delete from helpdesk.tramite where tramite_id=" + codigo ;
      
      return helpdesk.model.data.OperacionData.ejecutarBloqueSQL(cadSql);
      
   }

    /** Obtener el trabajador que genero el tramite
    *
    * @pdOid 08bcbfa1-0940-4d6b-a4af-5a625fbe9afa */
   public Trabajador getTrabajadorOrigen() {
      // TODO: implement
      return Trabajador.getTrabajadorBD(this.usuarioOrigen);
   }

   /** Obtiene el trabajador al cual se deriva la solicitud para su atencion
    *
    * @pdOid 6af1206e-68d7-4715-b604-a5cbeff1a879 */
   public Trabajador getTrabajadorDestino() {
      // TODO: implement
      return Trabajador.getTrabajadorBD(this.usuarioDestino);
   }

   /** Obtiene el cargo con el cual se registro el trámite
    *
    * @pdOid 69abd338-2607-41d6-b20f-13ee8c03046b */
   public Cargo getCargoOrigen() {
      // TODO: implement
      return Cargo.getCargoBD(this.codCargoOrigen);
   }

   /** Obtiene el cargo que debe tener el trabajador para dar atencion a la solicitud
    *
    * @pdOid aff28b19-5050-4653-b543-082cb8935f65 */
   public Cargo getCargoDestino() {
      // TODO: implement
      return Cargo.getCargoBD(this.codCargoDestino);
   }

   /** Obtiene la solicitud a la cual se le desea dar trámite
    *
    * @pdOid 5ecf37c3-7416-4ea5-883a-b3cc259279f0 */
   public Solicitud getSolicitud() {
      // TODO: implement
      return Solicitud.getSolicitudBD(this.codSolicitud);
   }

   /** Obtiene el tipo de tramite al cual corresponde
    *
    * @pdOid e937ffe0-ba91-4ca1-9ecf-8f13087faf77 */
   public TipoTramite getTipoTramite() {
      // TODO: implement
      return TipoTramite.getTipoTramiteBD(this.codTipoTramite);
   }

   /** Obtiene las tareas que se han registro para este tramite
    *
    * @pdOid 3eae32b4-2372-4471-a2ac-ef5ec9615916 */
   public java.util.ArrayList<Tarea> getTareas() {
      // TODO: implement
      if (this.getCodTramite() <= 0)
          return null;

      String cadSql = "SELECT tarea_id from helpdesk.tarea where tramite_id = " + this.getCodTramite() + " " +
                      "order by tarea_id asc ";

      helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

      if ( consulta.getNumFilas() <= 0 )
          return null;


      Object[][] resultados = consulta.getResultados();

      java.util.ArrayList<Tarea> misTareas = new java.util.ArrayList<Tarea>(); 

      for (Object[] fila : resultados) {
      misTareas.add(Tarea.getTareaBD(Integer.parseInt(fila[0].toString().trim())));
      }

      return misTareas;
      
   }

   /** Obtiene los mensajes que han sido registrados.
    *
    * @pdOid 326ba9ff-f535-4eb1-ab0a-ed5478c65980 */
   public java.util.ArrayList<Mensaje> getMensajes() {
      // TODO: implement
      if (this.getCodTramite() <= 0)
          return null;

      String cadSql = "SELECT mensaje_id from helpdesk.mensaje where tramite_id = " + this.getCodTramite() + " " +
                      "order by mensaje_id asc ";

      helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

      if ( consulta.getNumFilas() <= 0 )
          return null;

      Object[][] resultados = consulta.getResultados();

      java.util.ArrayList<Mensaje> misMensajes = new java.util.ArrayList<Mensaje>();

      for (Object[] fila : resultados) {
      misMensajes.add(Mensaje.getMensajeBD(Integer.parseInt(fila[0].toString().trim())));
      }

      return misMensajes ;

   }

   /** Permite obtener la fecha en la cual se registro el tramite,
    * en el formato dd/mm/yyyy hh:mm AM/PM
    *
    * @pdOid 87522892-e090-4827-84d1-5ed6d945aaa3 */
   public String getFechaFormateada() {
      // TODO: implement

      if ( this.getFecha() == null )
          return null ;

      Locale loc = new Locale("es", "PE"); 

      java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.MEDIUM,
                                                                         java.text.DateFormat.SHORT, 
                                                                         loc);

      return df.format(this.getFecha());
   }
   
}