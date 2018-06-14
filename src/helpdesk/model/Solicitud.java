/***********************************************************************
 * Module:  Solicitud.java
 * Author:  Roger
 * Purpose: Defines the Class Solicitud
 ***********************************************************************/

package helpdesk.model;

import helpdesk.model.*;
import java.util.*;

/** Esta clase representa una solicitud de atención de servicio informático
 *
 * @pdOid ed0ee89f-8374-4bb8-8d5e-bc576e83bc7b */
public class Solicitud {
   /** Código que de la solicitud
    *
    * @pdOid 51eda311-08eb-4afb-bb98-a5f2d2f59221 */
   private int codSolicitud;
   /** Trabajador que genera la solicitud
    *
    * @pdOid cb7434e1-cb5b-4fd3-9bd8-e13bf18e65a1 */
   private Trabajador trabajador;
   /** Cargo que tiene la persona al momento de generar la solicitud
    *
    * @pdOid 88c4f715-c874-497f-bebd-4196590a04f8 */
   private Cargo cargo;
   /** Asunto de lo que trata la solicitud
    *
    * @pdOid 5bebe154-b62b-47c2-81fc-e4d1f0a70cfe */
   private java.lang.String asunto;
   /** Fecha en la que la solicitud fue generada
    *
    * @pdOid f263fff1-96bf-4007-b670-dfa5b953f6b7 */
   private java.util.Date fecha;
   /** Detalle de la solicitud
    *
    * @pdOid ee5a6396-98e0-42d0-87da-76b46cac62cd */
   private java.lang.String detalle;
   /** Tipo al cual pertenece la solicitud
    *
    * @pdOid 118b8710-93ff-47b3-abee-a82ac31906c1 */
   private TipoSolicitud tipoSolicitud;
   /** Descripción de documentos o elementos físicos que se adjuntan con la solicitud. Por ejemplo: CD's, notas, informes físicos.
    *
    * @pdOid 8064d7ec-5017-4dc5-a5c0-cb4bae956bfb */
   private String adjuntos;

   /** Permite obtener  el Código que de la solicitud
    *
    * @pdOid 72290dda-1432-4d2f-a02c-be70ec50265d */
   public int getCodSolicitud() {
      return codSolicitud;
   }

   /** Permite establecer  el Código que de la solicitud
    *
    * @param newCodSolicitud Valor para el código de la solicitud
    * @pdOid a288badd-b948-4cdd-94da-3a06984f1d10 */
   public void setCodSolicitud(int newCodSolicitud) {
       if (newCodSolicitud<=0)
           throw new IllegalArgumentException("Ingrese un código valido");

      codSolicitud = newCodSolicitud;
   }

   /** Permite obtener el Trabajador que genera la solicitud
    *
    * @pdOid 40711d86-953f-4ab5-b46c-90055f0424b3 */
   public Trabajador getTrabajador() {
      return trabajador;
   }

   /** Permite establecer el Trabajador que genera la solicitud
    *
    * @param newTrabajador Trabajador que genera la solicitud
    * @pdOid 5d3cb70c-50e8-4d5f-9698-91bb08ee16b2 */
   public void setTrabajador(Trabajador newTrabajador) {
      if ((newTrabajador==null) || (newTrabajador.getUsuario().trim().length()<=0) ||
           (newTrabajador.getCargo().getCodCargo()<=0))
           throw new IllegalArgumentException("Actualize datos del trabajador");

      trabajador = newTrabajador;
   }

   /** Permite obtener el cargo con el cual el trabajador registro la solicitud
    *
    * @pdOid 9fe8df99-32cf-486e-90bc-b4d617395f6f */
   public Cargo getCargo() {
      return cargo;
   }

   /** Permite establecer  el cargo con el cual el trabajador registro la solicitud
    *
    * @param newCargo Cargo con el cual el trabajador registro la solicitud
    * @pdOid 3152373e-4492-47b9-983c-74c2d0498362 */
   public void setCargo(Cargo newCargo) {
       if ((newCargo==null ) || (newCargo.getCodCargo()<=0) )
           throw new IllegalArgumentException("Actualize datos de cargo del trabajador");
      cargo = newCargo;
   }

   /** Permite obtener el Asunto de lo que trata la solicitud
    *
    * @pdOid 529d9408-6656-4c79-97e6-a1136f3dce5b */
   public java.lang.String getAsunto() {
      return asunto;
   }

   /** Permite establecer el Asunto de lo que trata la solicitud
    *
    * @param newAsunto Asunto de lo que trata la solicitud
    * @pdOid 69698892-37b8-4ae3-8ee2-78794d03eb4d */
   public void setAsunto(java.lang.String newAsunto) {
       if ((newAsunto==null) || (newAsunto.trim().length()<=0))
           throw new IllegalArgumentException("Ingrese un asunto valido");

      asunto = newAsunto;
   }

   /** Permite obtener la Fecha en la que la solicitud fue generada
    *
    * @pdOid 0e62ab4c-3f28-43ea-acc3-a802f956303c */
   public java.util.Date getFecha() {
      return fecha;
   }

   /** Permite establecer la Fecha en la que la solicitud fue generada
    *
    * @param newFecha
    * @pdOid 73c6122e-7f88-45a9-9e72-263832c3a641 */
   public void setFecha(java.util.Date newFecha) {
      if (newFecha==null)
          throw new IllegalArgumentException("Ingrese una fecha valida");

      fecha = newFecha;
   }

   /** Permite obtener el detalle de la solicitud.
    *
    * @pdOid 6fea05a7-9e1c-4ef9-ad42-fdb003b7ae17 */
   public java.lang.String getDetalle() {
      return detalle;
   }

   /** Permite establecer el detalle de la solicitud.
    *
    * @param newDetalle Valor para el detalle de la solicitud
    * @pdOid 580a378e-9126-45d9-9439-d98c10f2f4d9 */
   public void setDetalle(java.lang.String newDetalle) {
     if ((newDetalle==null)||(newDetalle.trim().length()<=0))
       throw new IllegalArgumentException("Ingrese un detalle valido");
      detalle = newDetalle;
   }

   /** Permite establecer el Tipo al cual pertenece la solicitud
    *
    * @pdOid 12fbb742-3e07-4c9f-828c-08fbb95a44be */
   public TipoSolicitud getTipoSolicitud() {
      return tipoSolicitud;
   }

   /** @param newTipoSolicitud Tipo de la solicitud
    * @pdOid db3c36cf-589b-4d5c-bc6e-48030f907c96 */
   public void setTipoSolicitud(TipoSolicitud newTipoSolicitud) {
       if ((newTipoSolicitud==null)||(newTipoSolicitud.getDescripcion().trim().length()<=0 ||
               newTipoSolicitud.getNombre().trim().length()<=0))
           throw new IllegalArgumentException("Ingrese un tipo de solictud valido");

      tipoSolicitud = newTipoSolicitud;
   }

   /** Permite obtener la descripción de documentos o elementos físicos que se adjuntan con la solicitud. Por ejemplo: CD's, notas, informes físicos.
    *
    * @pdOid d94f21d3-3877-4ffa-afe6-568f776a42c9 */
   public String getAdjuntos() {
      return adjuntos;
   }

   /** Permite establecer la descripción de documentos o elementos físicos que se adjuntan con la solicitud. Por ejemplo: CD's, notas, informes físicos.
    *
    * @param newAdjuntos Descripción de los documentos que se van adjuntar a la solicitud
    * @pdOid 4cdb4310-7380-4325-9728-954601a8abc9 */
   public void setAdjuntos(String newAdjuntos) {

       if ( newAdjuntos == null || newAdjuntos.trim().length() <= 0 )
           newAdjuntos = null ;

      adjuntos = newAdjuntos;
      
   }

   /** Elimina una solicitud de la base de datos.
    *
    * @param codigo Código de la solicitud
    * @pdOid 68a7de73-7998-4ae6-95f0-892a6a639bac */
   public static boolean eliminarBD(int codigo) {
      // TODO: implement
      String[] cadSql = new String[4];


      cadSql[0] = "delete from helpdesk.solicitud where solicitud_id = " + codigo ;
      
      cadSql[1] = "delete from helpdesk.solicitud where tramite_id in "
                     + " ( select tramite_id from helpdesk.tramite "
                     + "where solicitud_id = " + codigo  + ") " ;

      cadSql[2] = "delete from helpdesk.tramite where solicitud_id=" + codigo ;

      cadSql[3] = " delete from  helpdesk.archivo where solicitud_id=" + codigo ;
       
      return helpdesk.model.data.OperacionData.ejecutarBloqueSQL(cadSql);
   }

   /** Sirve para poder encontrar una solicitud en base al código de identificación. Esta solicitud debe ser una que ya se encuentre registrada en la base de datos.
    *
    * @param codSolic Código de identificación de la solicitud
    * @pdOid bdf13c84-cf46-4d80-a2d8-bbdfdb900872 */
   public static Solicitud getSolicitudBD(int codSolic) {
      // TODO: implement
      // TODO: implement
    String cadSql = " select solicitud_id, usuario, asunto, fecha, detalle, " +
                    " tipo_solicitud_id, adjuntos, cargo_id " +
                    " from helpdesk.solicitud where solicitud_id = " + codSolic ;

   helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

   if ( consulta.getNumFilas() <= 0 ) return null ;

   Object[][] resultados = consulta.getResultados();

   Solicitud miSolic = new Solicitud();

        for ( Object[] fila : resultados ) {

            if ( fila[6] != null ) miSolic.setAdjuntos(fila[6].toString());

            if ( fila[2] != null ) miSolic.setAsunto(fila[2].toString());

            miSolic.setCargo( Cargo.getCargoBD(Integer.parseInt(fila[7].toString())) );

            miSolic.setCodSolicitud(Integer.parseInt(fila[0].toString()));

            if ( fila[4] != null ) miSolic.setDetalle(fila[4].toString());

            miSolic.setFecha( (Date)fila[3] );

            miSolic.setTipoSolicitud( new TipoSolicitud( Integer.parseInt(fila[5].toString())) );

            miSolic.setTrabajador( Trabajador.getTrabajadorBD( fila[1].toString() ) );

        }

   return miSolic ; 

   }

   /** Permite saber si la solicitud esta con visto bueno o no.
    *
    * @pdOid 85f719ce-fb34-47c7-9d39-dcadb92be516 */
   public boolean isConVistoBueno() {
      // TODO: implement

       if ( this.codSolicitud <= 0 ) return false ;

       String cadSql = " select * from helpdesk.tramite where solicitud_id = " + this.codSolicitud +    
                       " where tipo_tramite_id = " + ValorTipoTramite.DAR_VISTO_BUENO.getCodigo() ;

       helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

            if ( consulta.getNumFilas() <= 0 ) return false ;

      return true;
   }

   /** Retorna los archivos adjuntos de la solicitud
    *
    * @pdOid aa3570b4-511d-4c39-9474-75b87838f050 */
   public java.util.ArrayList<Archivo> getArchivos() {
      // TODO: implement
      String cadSQL="Select archivo_id from helpdesk.archivo " +
                    "where solicitud_id=" + this.codSolicitud;

      helpdesk.model.data.ConsultaData consulta=new helpdesk.model.data.ConsultaData(cadSQL);
      Object[][] resultado=consulta.getResultados();

      if ( resultado == null ) return null ; 

      java.util.ArrayList<Archivo> misArchivos=null;

      if (resultado != null)
            misArchivos=new java.util.ArrayList<Archivo>();

      for (Object[] fila: resultado){
          Archivo miArchivo=Archivo.getArchivoBD(Integer.parseInt(fila[0].toString()));
          misArchivos.add(miArchivo);
      }

      return misArchivos;
   }

   /** Obtiene los tramites que han sido registrados para esta solicitud
    *
    * @pdOid 2f6f96b8-f04d-4508-92cf-8a7dabcdbb9d */
   public java.util.ArrayList<Tramite> getTramites() {
      // TODO: implement

     if ( this.codSolicitud <= 0 ) return null ; 

      String cadSql = "select tramite_id from helpdesk.tramite "
                      + " where solicitud_id=" + this.codSolicitud  
                      + " order by tramite_id asc";

      helpdesk.model.data.ConsultaData miConsulta = new helpdesk.model.data.ConsultaData(cadSql);

      if ( miConsulta.getNumFilas() <= 0 ) return null ;

      ArrayList<Tramite> misTramites = new ArrayList<Tramite>();

      Object[][] miRst = miConsulta.getResultados() ;

        for( Object[] fila : miRst ) {
        misTramites.add(Tramite.getTramiteBD(Integer.parseInt(fila[0].toString())));
        }

      return misTramites;
      
   }

   /** Retorna el último tramite generado
    *
    * @pdOid a93f371a-2455-4242-9fa3-3454d3895cac */
   public Tramite getUltimoTramite() {
      // TODO: implement

      if ( this.getCodSolicitud() <= 0 ) return null ; 

      String cadSql = "select tramite_id from helpdesk.tramite " +
                        "where solicitud_id=" + this.getCodSolicitud()  +
                        " order by tramite_id desc limit 1 offset 0";

      helpdesk.model.data.ConsultaData consultaData = new helpdesk.model.data.ConsultaData(cadSql);

      if ( consultaData.getNumFilas() <= 0 ) return null ;

      return Tramite.getTramiteBD(Integer.parseInt(consultaData.getResultados()[0][0].toString().trim()));
      
   }

   /** Retorna la sub-categoria en la cual esta clasificada la solicitud.
    *
    *
    * @pdOid 04d463d4-32ca-48a9-99cb-c27e81aef564 */
   public SubCategoria getSubCategoria() {
      // TODO: implement

       if ( this.getCodSolicitud() <= 0 ) return null ;

       String cadSql = "select subcategoria_id from helpdesk.clasificacion "
                        + " where solicitud_id=" + this.getCodSolicitud();

      helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

      if (consulta.getNumFilas() <= 0) return null ;
      int codSolic = Integer.parseInt(consulta.getResultados()[0][0].toString().trim()) ;
      SubCategoria resultado = SubCategoria.getSubCategoriaBD(codSolic); 

      return resultado;
   }

   /** Retorna la prioridad en la cual esta clasificada la solicitud
    *
    * @pdOid 9e1e5fca-d86f-496b-a669-c13da41ee2e1 */
   public Prioridad getPrioridad() {
      // TODO: implement
       if ( this.getCodSolicitud() <= 0 ) return null ;

       String cadSql = "select prioridad_id from helpdesk.clasificacion "
                        + " where solicitud_id=" + this.getCodSolicitud();

      helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

      if (consulta.getNumFilas() <= 0) return null ;
      int codSolic = Integer.parseInt(consulta.getResultados()[0][0].toString().trim()) ;
      Prioridad resultado = Prioridad.getPrioridadBD(codSolic);

      return resultado;

   }

   
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