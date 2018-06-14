/***********************************************************************
 * Module:  TrabajadorInformatica.java
 * Author:  rcontreras
 * Purpose: Defines the Class TrabajadorInformatica
 ***********************************************************************/

package helpdesk.model;

import helpdesk.model.data.ConsultaData;
import helpdesk.model.data.OperacionData;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Representa a un trabajador del área de informatica
 *
 * @pdOid 27bf95d4-0a5d-4c8a-af27-06f33bd0f466 */
public class TrabajadorInformatica extends Trabajador {
   /** @param usuario
    * @param miCargo
    * @exception helpdesk.model.HelpDeskException
    * @pdOid 3c8daf0b-4f38-4b8f-a26d-acdb30d5ac4b */
   public TrabajadorInformatica(String usuario, Cargo miCargo)
    throws helpdesk.model.HelpDeskException {
      // TODO: implement
       if (usuario != null && usuario.trim().length()>0 && miCargo != null){

           Trabajador miTrabajador = Trabajador.getTrabajadorBD(usuario);

            this.setAnexo(miTrabajador.getAnexo());
            this.setApellido(miTrabajador.getApellido());
            this.setNombre(miTrabajador.getNombre());
            this.setCargo(miCargo);
            this.setCorreo(miTrabajador.getCorreo());
            this.setDni(miTrabajador.getDni());
            this.setCorreo(miTrabajador.getCorreo());
            this.setUsuario(miTrabajador.getUsuario());
            
       }
       else {
       throw new IllegalArgumentException("valor incorrecto en los argumentos");
       }
   }

   /** @param miSolicitud Solicitud a la cual se le desea dar atencion
    * @param valSubCategoria
    * @param valPrioridad
    * @exception helpdesk.model.HelpDeskException
    * @pdOid efca890f-21ca-445b-acf5-0429e79e4b74 */
   public void atenderSolicitud(Solicitud miSolicitud, SubCategoria valSubCategoria, Prioridad valPrioridad)
   throws helpdesk.model.HelpDeskException {
      // TODO: implement

   if (miSolicitud != null && valSubCategoria != null && valPrioridad != null){

       String cadSql="select * from helpdesk.clasificacion where solicitud_id=" + miSolicitud.getCodSolicitud();

       ConsultaData consulta= new ConsultaData(cadSql);

       if (consulta.getNumFilas()>0){
           String[] cadSql1= new String[3];

            cadSql1[0]="Delete from helpdesk.clasificacion where solicitud_id=" + miSolicitud.getCodSolicitud();

            cadSql1[1]="Insert into helpdesk.clasificacion(solicitud_id, subcategoria_id, prioridad_id) " +
               "values(" + miSolicitud.getCodSolicitud() + ", " + valSubCategoria.getCodigo() + ", " + 
               valPrioridad.getCodigo() + ") ";

            cadSql1[2]="INSERT INTO helpdesk.tramite( " +
                        "usuario_origen, " +
                        "fecha, " +
                        "tipo_tramite_id, " +
                        "solicitud_id, " +
                        "cargo_id_origen, " +
                        "usuario_detino, " +
                        "cargo_id_destino, " +
                        "detalle) " +
                        "VALUES (" +
                        "'" + this.getUsuario() + "', " +
                        "'" + new Date() + "', " +
                        "" + ValorTipoTramite.ATENDER.getCodigo() + ", " +
                        "" + miSolicitud.getCodSolicitud() + ", " +
                        "" + this.getCargo().getCodCargo() + ", " +
                        "'" + this.getUsuario() + "', " +
                        "" + this.getCargo().getCodCargo() + ", " +
                        "null )";

                    if (!OperacionData.ejecutarBloqueSQL(cadSql1))
                       throw new HelpDeskException ("No se pudo registrar la Atención a la Solicitud");

       }else {
            
            String[] cadSql2= new String[2];

            cadSql2[0]="Insert into helpdesk.clasificacion(solicitud_id, subcategoria_id, prioridad_id) " +
               "values(" + miSolicitud.getCodSolicitud() + ", " + valSubCategoria.getCodigo() + ", " +
               valPrioridad.getCodigo() + ") ";

            cadSql2[1]="INSERT INTO helpdesk.tramite( " +
                        "usuario_origen, " +
                        "fecha, " +
                        "tipo_tramite_id, " +
                        "solicitud_id, " +
                        "cargo_id_origen, " +
                        "usuario_detino, " +
                        "cargo_id_destino, " +
                        "detalle) " +
                        "VALUES (" +
                        "'" + this.getUsuario() + "', " +
                        "'" + new Date() + "', " +
                        "" + ValorTipoTramite.ATENDER.getCodigo() + ", " +
                        "" + miSolicitud.getCodSolicitud() + ", " +
                        "" + this.getCargo().getCodCargo() + ", " +
                        "'" + this.getUsuario() + "', " +
                        "" + this.getCargo().getCodCargo() + ", " +
                        "null )";

            if (!OperacionData.ejecutarBloqueSQL(cadSql2))
                       throw new HelpDeskException ("No se pudo registrar la Atención a la Solicitud");

            }
        }
    else
        throw new HelpDeskException ("valor nulo en parámetros de entrada del método atenderSolicitud");
   }

   /** Permite saber si el trabajador puede derivar esta solicitud
    *
    * @param miSolicitud Solicitud de la cual se desea conocer si puede ser derivada
    * @pdOid cd984ea1-bdfb-4936-8717-d00b9343d333 */
   public boolean puedeDerivarSolic(Solicitud miSolicitud) {
      // TODO: implement
      if (miSolicitud != null && miSolicitud.getCodSolicitud()>0){

           String cadSql="Select * From helpdesk.services_desks " +
               "where cargo_id=" + this.getCargo().getCodCargo() + " and " +
               "tipo_solicitud_id=" + miSolicitud.getTipoSolicitud().getCodigo() + " and " +
               "fecha_venc>=now()::date and " +
               "(usuario is null or usuario='" + this.getUsuario() + "') and " +
               "puede_derivar=true";
       
            ConsultaData consulta=new ConsultaData(cadSql);

            if (consulta.getNumFilas()>0)
                return true;
           else
                return false;
       }
       else
            return false;
    }

   /** Permite derivar una solicitud para que sea atendida por otra persona
    *
    * @param miTramite Tramite que corresponde al proceso de derivación de una solicitud
    * @param archivoTramite Archivo que se adjunta al tramite
    * @exception helpdesk.model.HelpDeskException
    * @pdOid 3fd77cbd-abdb-4b70-886e-78e3cd3f3513 */
   public void derivarSolicitud(Tramite miTramite, TramiteArchivo archivoTramite) throws helpdesk.model.HelpDeskException {
      // TODO: implement
       miTramite.setUsuarioOrigen(this.getUsuario());
       miTramite.setCodCargoOrigen(this.getCargo().getCodCargo());
       miTramite.setCodTipoTramite(ValorTipoTramite.DERIVAR.getCodigo());
       miTramite.setFecha(new Date());

       if ( miTramite.getSolicitud() == null )
           throw new HelpDeskException("Falta especificar la solicitud");

       if ( miTramite.getCargoDestino() == null )
           throw new HelpDeskException("Falta especificar el cargo de la persona que recibe la solicitud");

       if ( miTramite.getUsuarioDestino() == null )
           throw new HelpDeskException("Falta especificar el trabajador al que se deriva la solicitud");    

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
                        "'" + miTramite.getUsuarioOrigen() + "', " +
                        "'" + miTramite.getFecha() + "', " + 
                        "" + miTramite.getTipoTramite().getCodigo() + ", " +
                        "" + miTramite.getCodSolicitud() + ", " +
                        "" + miTramite.getCodCargoOrigen() + ", " +
                        "'" + miTramite.getUsuarioDestino() + "', " +
                        "" + miTramite.getCodCargoDestino() + ", " +
                        (( miTramite.getDetalle() == null ) ? "null" : "'" + miTramite.getDetalle() + "'" ) + ") "
                        + " RETURNING tramite_id " ;

       
      ConsultaData consulta = new ConsultaData(cadSql);
      Object[][] rstFilas = consulta.getResultados();

            if (rstFilas == null)
                 throw new HelpDeskException("No se pudo derivar la solicitud");

      int codTramiteRegistrado = Integer.parseInt(rstFilas[0][0].toString().trim());

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
                
                throw new HelpDeskException("No se pudo adjuntar el archivo. No se pudo derivar la solicitud");
                
                }

            }
       
   }

    /** Permite clasificar una solicitud, lo cual significa especificar la sub categoria y prioridad a la que pertenece la solicitud
    *
    * @param solicitud Solicitud que se desea clasificar
    * @param subCategoria Sub categoría a la cual se desea que pertenezca la solicitud
    * @param prioridad Prioridad de la solicitud
    * @exception helpdesk.model.HelpDeskException
    * @pdOid 38ced74e-2c33-4deb-b221-371c869c2353 */
   public void clasificarSolicitud(Solicitud solicitud, SubCategoria subCategoria, Prioridad prioridad) throws helpdesk.model.HelpDeskException {
      // TODO: implement
       
      if ( solicitud == null || subCategoria == null || prioridad == null )
            throw new IllegalArgumentException("Falta ingresar datos para clasificar la solicitud");

      if ( solicitud.getCodSolicitud() <= 0 && subCategoria.getCodigo() <= 0 && prioridad.getCodigo() <= 0 )
            throw new IllegalArgumentException("Falta ingresar datos para clasificar la solicitud");

      String[] cadSql = new String[2]; 

      cadSql[0] = "delete from helpdesk.clasificacion where solicitud_id = " + solicitud.getCodSolicitud(); 

      cadSql[1] =       "insert into helpdesk.clasificacion( " +
                        "solicitud_id, " +
                        "subcategoria_id, " +
                        "prioridad_id) " +
                        "values(" +
                        solicitud.getCodSolicitud() + ", " +
                        subCategoria.getCodigo() + ", " +
                        prioridad.getCodigo() + ") ";

      if (!OperacionData.ejecutarBloqueSQL(cadSql))
          throw new IllegalArgumentException("No se pudo clasificar la solicitud");
      

   }

   /** Permite finalizar la atencion de una solicitud, este es un paso previo
    * antes del envio para la conformidad del usuario.
    *
    * @param solicitud Solicitud que se desea finalizar la atencion
    * @param detalle Detalle informativo que se ingresa al momento de finalizar la atencion de la solicitud
    * @exception helpdesk.model.HelpDeskException
    * @pdOid 0babdf6f-3e69-4a7e-8f49-8168afa4257a */
   public void finalizarAtencion(Solicitud solicitud, String detalle) throws helpdesk.model.HelpDeskException {
      // TODO: implement

     if (solicitud == null)
         throw new IllegalArgumentException("Falta especificar la solicitud ...");

     if ( this.getUsuario() == null || this.getCargo() == null ) {
        throw new helpdesk.model.HelpDeskException("Falta completar datos ...");
     }

      ArrayList<Tramite> misTramites = solicitud.getTramites();

      if ( misTramites == null )
          throw new helpdesk.model.HelpDeskException("No se puede finalizar "   
                    + "una solicitud que no ha tenido tramites previos ...");

      Tramite ultimoTramite = solicitud.getUltimoTramite();

      if ( ultimoTramite.getCodTipoTramite() != ValorTipoTramite.ATENDER.getCodigo() ) 
          throw new helpdesk.model.HelpDeskException("No se puede finalizar "
                    + "una solicitud que no haya sido atendida  ...");
      

      Iterator<Tramite> iTramites = misTramites.iterator();
      Tramite tramiteReferencia = null;

        while (iTramites.hasNext()) {
        tramiteReferencia = iTramites.next();

            if (tramiteReferencia.getCodTipoTramite() == ValorTipoTramite.ENVIO_ATENCION.getCodigo()) {
            tramiteReferencia = iTramites.next();
            break;
            }

        }

      if (tramiteReferencia == null)
          throw new helpdesk.model.HelpDeskException("No se puede finalizar la atencion de la solicitud. ");
      
      Tramite miTramite = new Tramite();
      miTramite.setCodCargoDestino(tramiteReferencia.getCodCargoOrigen());
      miTramite.setCodCargoOrigen(this.getCargo().getCodCargo());
      miTramite.setCodSolicitud(solicitud.getCodSolicitud());
      miTramite.setCodTipoTramite(ValorTipoTramite.FINALIZAR_ATENCION.getCodigo());
      miTramite.setDetalle(detalle);
      miTramite.setFecha(new Date());
      miTramite.setUsuarioDestino(tramiteReferencia.getUsuarioOrigen());
      miTramite.setUsuarioOrigen(this.getUsuario()); 

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
                        "'" + miTramite.getUsuarioOrigen() + "', " +
                        "'" + miTramite.getFecha() + "', " +
                        "" + miTramite.getTipoTramite().getCodigo() + ", " +
                        "" + miTramite.getCodSolicitud() + ", " +
                        "" + miTramite.getCodCargoOrigen() + ", " +
                        "'" + miTramite.getUsuarioDestino() + "', " +
                        "" + miTramite.getCodCargoDestino() + ", " +
                        (( miTramite.getDetalle() == null ) ? "null" : "'" + miTramite.getDetalle() + "'" ) + ") "
                        + " RETURNING tramite_id " ;
      

    ConsultaData miConsulta = new ConsultaData(cadSql);

        if ( miConsulta.getNumFilas() <= 0 )
            throw new helpdesk.model.HelpDeskException("No se pudo finalizar la atencion de la solicitud ...");


   }

   /** Envia una solicitud para que reciba la conformidad del usuario que la generó.
    *
    * @param solicitud Solicitud que es enviada para recibir conformidad.
    * @param detalle Texto descriptivo que se coloca al momento de enviar la solicitud para recibir la conformidad
    * @exception helpdesk.model.HelpDeskException
    * @pdOid 06dcd957-90cf-4d17-a716-5428590df522 */
   public void enviarParaConformidad(Solicitud solicitud, String detalle) throws helpdesk.model.HelpDeskException {
      // TODO: implement
       
     if (solicitud == null)
         throw new IllegalArgumentException("Falta especificar la solicitud ...");

     if ( this.getUsuario() == null || this.getCargo() == null ) 
        throw new helpdesk.model.HelpDeskException("Falta completar datos ...");

     if (solicitud.getCargo() == null)
         throw new IllegalStateException("Falta especificar el cargo en la solicitud enviada");

     if (solicitud.getTrabajador() == null)
         throw new IllegalStateException("Falta especificar el trabajador que registro la solicitud");

     Tramite ultimoTramite = solicitud.getUltimoTramite();

     if ( ultimoTramite == null )
        throw new helpdesk.model.HelpDeskException("La solicitud no ha tenido tramites previos");

     if ( ultimoTramite.getTipoTramite().getCodigo() != ValorTipoTramite.FINALIZAR_ATENCION.getCodigo() )
         throw new helpdesk.model.HelpDeskException("No se puede enviar para conformidad, si no se "
                                                    + "ha finalizado la atencion de la solicitud ...");


     Tramite nuevoTramite = new Tramite();
     nuevoTramite.setCodCargoDestino(solicitud.getCargo().getCodCargo());
     nuevoTramite.setCodCargoOrigen(this.getCargo().getCodCargo());
     nuevoTramite.setCodSolicitud(solicitud.getCodSolicitud());
     nuevoTramite.setCodTipoTramite(ValorTipoTramite.CONFORMIDAD_USUARIO.getCodigo());
     nuevoTramite.setDetalle(detalle);
     nuevoTramite.setFecha(new Date());
     nuevoTramite.setUsuarioDestino(solicitud.getTrabajador().getUsuario());
     nuevoTramite.setUsuarioOrigen(this.getUsuario());


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
                        + " RETURNING tramite_id " ;


    ConsultaData miConsulta = new ConsultaData(cadSql);

        if ( miConsulta.getNumFilas() <= 0 )
            throw new helpdesk.model.HelpDeskException("No se pudo enviar para conformidad la solicitud ...");

   }


   /** Permite devolver una solicitud con atención finalizada
    *
    * @param solicitud Solicitud que ha sido devuelta para finalizar atencion
    * @param detalle Detalle de la devolucion
    * @param archivoTramite Archivo que se adjunta al momento de devolver la solicitud
    * @exception helpdesk.model.HelpDeskException
    * @pdOid 3704e636-24fc-4db9-ba14-1e13f4fcdddd */
   public void devolverSolicitudFinalizada(Solicitud solicitud, String detalle, TramiteArchivo archivoTramite) throws helpdesk.model.HelpDeskException {
      // TODO: implement
       
     if (solicitud == null)
         throw new IllegalArgumentException("Falta especificar la solicitud ...");

     if ( this.getUsuario() == null || this.getCargo() == null )
        throw new helpdesk.model.HelpDeskException("Falta completar datos ...");

     if (solicitud.getCargo() == null)
         throw new IllegalStateException("Falta especificar el cargo en la solicitud enviada");

     if (solicitud.getTrabajador() == null)
         throw new IllegalStateException("Falta especificar el trabajador que registro la solicitud");


      Tramite ultimoTramite = solicitud.getUltimoTramite();

     if ( ultimoTramite == null )
        throw new helpdesk.model.HelpDeskException("La solicitud no ha tenido tramites previos");

      if ( ultimoTramite.getTipoTramite().getCodigo() != ValorTipoTramite.FINALIZAR_ATENCION.getCodigo() )
          throw new helpdesk.model.HelpDeskException("No se puede devolver una solicitud que no ha sido finalizada ...");


      Tramite miTramite = new Tramite();

      miTramite.setCodCargoDestino(ultimoTramite.getCodCargoOrigen());
      miTramite.setCodCargoOrigen( this.getCargo().getCodCargo() );
      miTramite.setCodSolicitud(solicitud.getCodSolicitud());
      miTramite.setCodTipoTramite(ValorTipoTramite.DERIVAR.getCodigo());
      miTramite.setDetalle(detalle);
      miTramite.setFecha(new Date());
      miTramite.setUsuarioDestino(ultimoTramite.getUsuarioOrigen());
      miTramite.setUsuarioOrigen(this.getUsuario());

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
                        "'" + miTramite.getUsuarioOrigen() + "', " +
                        "'" + miTramite.getFecha() + "', " +
                        "" + miTramite.getTipoTramite().getCodigo() + ", " +
                        "" + miTramite.getCodSolicitud() + ", " +
                        "" + miTramite.getCodCargoOrigen() + ", " +
                        "'" + miTramite.getUsuarioDestino() + "', " +
                        "" + miTramite.getCodCargoDestino() + ", " +
                        (( miTramite.getDetalle() == null ) ? "null" : "'" + miTramite.getDetalle() + "'" ) + ") "
                        + " RETURNING tramite_id " ;


      ConsultaData consulta = new ConsultaData(cadSql);
      Object[][] rstFilas = consulta.getResultados();

            if (rstFilas == null)
                 throw new HelpDeskException("No se pudo devolver la solicitud");

      int codTramiteRegistrado = Integer.parseInt(rstFilas[0][0].toString().trim());

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

   /** Permite registrar una tarea
    *
    * @param solicitud Solicitud a la cual se le desea registrar la tarea
    * @param descripcion Descripcion de la tarea que se esta agregando
    * @param fechaInicio Fecha en la que se dio inicio con la tarea
    * @param fechaFin Fecha en la que se finalizo de hacer la tarea
    * @exception helpdesk.model.HelpDeskException
    * @pdOid e1d63afa-d6ec-4357-8eb9-fff4f30d1118 */
   public void registrarTarea(Solicitud solicitud, String descripcion, java.util.Date fechaInicio, java.util.Date fechaFin) throws helpdesk.model.HelpDeskException {
            // TODO: implement
            if (solicitud == null) {
                throw new IllegalArgumentException("Falta especificar la solicitud");
            }
            Tramite miTramite = solicitud.getUltimoTramite();
            if (miTramite == null) {
                throw new IllegalStateException("No hay tramites previamente registrados");
            }
            if (miTramite.getTipoTramite().getCodigo() != ValorTipoTramite.ATENDER.getCodigo()) {
                throw new IllegalStateException("No se puede registrar la tarea si la solicitud " + "no se encuentra en proceso de atencion");
            }
            Tarea miTarea = new Tarea();
            miTarea.setArea(this.getCargo().getArea().getNombre());
            miTarea.setCargo(this.getCargo().getNombre());
            miTarea.setCodTramite(miTramite.getCodTramite());
            miTarea.setDescripcion(descripcion);
            miTarea.setFechaFin(fechaFin);
            miTarea.setFechaInicio(fechaInicio);
            miTarea.setNombreTrabajador(this.getNombre() + " " + this.getApellido());
            miTarea.setUsuario(this.getUsuario());            
            
            String cadSql = "INSERT INTO helpdesk.tarea( "
            + "descripcion, "
            + "fecha_inicio, "
            + "fecha_fin, "
            + "nombre, "
            + "cargo, "
            + "area, "
            + "tramite_id, "
            + "usuario ) "
            + "VALUES ( "
            + "'" + miTarea.getDescripcion() + "', "
            + "'" + miTarea.getFechaInicio() + "', "
            + "'" + miTarea.getFechaFin() + "', "
            + "'" + miTarea.getNombreTrabajador() + "', "
            + "'" + miTarea.getCargo() + "', "
            + "'" + miTarea.getArea() + "', "
            + "" + miTarea.getCodTramite() + ", "
            + "'" + miTarea.getUsuario() + "' "
            + ") RETURNING tarea_id ";
            ConsultaData consulta = new ConsultaData(cadSql);
            
            if (consulta.getNumFilas() <= 0) {
                throw new IllegalStateException("No se puede registrar la tarea");
            }



   }

   /** Permite eliminar una tarea de la base de datos.
    *
    * @param tarea Tarea que se desea eliminar
    * @exception helpdesk.model.HelpDeskException
    * @pdOid ce2dbce1-125a-492a-9916-33219955b57f */
   public void eliminarTarea(Tarea tarea) throws helpdesk.model.HelpDeskException {
      // TODO: implement
      if ( tarea == null )
          throw new IllegalStateException("Falta especificar la tarea que se desea eliminar");

      if ( tarea.getCodigo() <= 0 )
          throw new IllegalStateException("Código incorrecto de la tarea");      

      String cadSql = "delete from helpdesk.tarea where tarea_id = " + tarea.getCodigo() ;

      OperacionData.ejecutarSQL(cadSql);

      cadSql = "select * from helpdesk.tarea where tarea_id = " + tarea.getCodigo() ;

      ConsultaData consulta = new ConsultaData(cadSql);

      if ( consulta.getNumFilas() > 0 )
          throw new helpdesk.model.HelpDeskException("La tarea no pudo ser eliminada");

   }

   /** Permite editar una tarea
    *
    * @param tarea Tarea que se desea editar
    * @param descripcion Descripcion de la tarea que se esta editando
    * @param fechaInicio Fecha en la que se dio inicio con la tarea
    * @param fechaFin Fecha en la que se finalizo de hacer la tarea
    * @exception helpdesk.model.HelpDeskException
    * @pdOid bc899f82-9aeb-4e11-bd5d-7fe32a8f678a */
   public void editarTarea(Tarea tarea, String descripcion, java.util.Date fechaInicio, java.util.Date fechaFin) throws helpdesk.model.HelpDeskException {
      // TODO: implement      
      if ( tarea == null )
          throw new IllegalArgumentException("Falta especificar la tarea que se desea editar");

      if ( descripcion == null || descripcion.trim().length() <= 0 )
          throw new IllegalArgumentException("Falta especificar la descripcion");

      if ( fechaInicio == null )
          throw new IllegalArgumentException("Falta especificar la fecha de inicio");

      if ( fechaFin == null )
          throw new IllegalArgumentException("Falta especificar la fecha de finalizacion");

     String cadSql = "UPDATE helpdesk.tarea SET descripcion='" + descripcion.trim() + "', "
                     + " fecha_inicio='" + fechaInicio + "', fecha_fin='" + fechaFin + "' "
                     + " WHERE tarea_id = " + tarea.getCodigo() ;

      System.out.println(cadSql);

      OperacionData.ejecutarSQL(cadSql);      

   }
   
}
