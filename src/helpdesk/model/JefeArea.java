/***********************************************************************
 * Module:  JefeArea.java
 * Author:  rcontreras
 * Purpose: Defines the Class JefeArea
 ***********************************************************************/

package helpdesk.model;

import java.util.*;

/** Representa al jefe de alguna unidad organizacional
 *
 * @pdOid 709e41aa-5e02-47c6-ac21-68b9820bd2a1 */
public class JefeArea extends Trabajador {
   /** Representa a un jefe de alguna unidad organizacional
    *
    * @param miUsuario Nombre de usuario
    * @param miCargo Cargo del trabajador con el que desea trabajar
    * @exception helpdesk.model.HelpDeskException
    * @pdOid 4ec11605-d261-435f-8cd0-5b160fa92597 */
   public JefeArea(java.lang.String miUsuario, Cargo miCargo)
    throws helpdesk.model.HelpDeskException {
      // TODO: implement
      
    if (miUsuario != null && miUsuario.trim().length()>0 && miCargo != null){

        if ( miCargo.getJefe() == false )
              throw new helpdesk.model.HelpDeskException("El trabajador no es un jefe de area");

          Trabajador miTrab = Trabajador.getTrabajadorBD(miUsuario);
          this.setAnexo(miTrab.getAnexo());
          this.setApellido(miTrab.getApellido());
          this.setCargo(miCargo);
          this.setCorreo(miTrab.getCorreo());
          this.setDni(miTrab.getDni());
          this.setNombre(miTrab.getNombre());
          this.setUsuario(miTrab.getUsuario());
        }
    }
   /** Agrega el visto bueno a una determinada solicitud
    *
    * @param miSolicitud Solicitud a la cual se le desea dar el visto bueno
    * @param detalle Detalle referente al visto bueno de la solicitud
    * @exception helpdesk.model.HelpDeskException
    * @pdOid c750cd5d-a6be-4709-998f-d7a98bd2acd2 */
   public boolean darVistoBueno(Solicitud miSolicitud, String detalle){
      // TODO: implement
        Tramite miTramite=new Tramite();

        miTramite.setDetalle(detalle);
        miTramite.setCodSolicitud(miSolicitud.getCodSolicitud());
        miTramite.setFecha(new Date());
        miTramite.setUsuarioOrigen( this.getUsuario() );
        miTramite.setCodCargoOrigen( this.getCargo().getCodCargo());
        miTramite.setCodTipoTramite(ValorTipoTramite.DAR_VISTO_BUENO.getCodigo());
        miTramite.setUsuarioDestino(miSolicitud.getTrabajador().getUsuario());
        miTramite.setCodCargoDestino(miSolicitud.getCargo().getCodCargo());

        if ( miTramite.getCodTipoTramite() != ValorTipoTramite.DAR_VISTO_BUENO.getCodigo()){
            return false ;
        }

        String[] cadSql = new String[2];
        cadSql[0] = "INSERT INTO helpdesk.tramite( " +
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

            cadSql[1] = "INSERT INTO helpdesk.tramite( " +
                    "usuario_origen, " +
                    "fecha, " +
                    "tipo_tramite_id, " +
                    "solicitud_id, " +
                    "cargo_id_origen, " +
                    "usuario_detino, " +
                    "cargo_id_destino, " +
                    "detalle) " +
                    "VALUES (" +
                    "'" + miSolicitud.getTrabajador().getUsuario() + "', " +
                    "'" + miTramite.getFecha() + "', " +
                    "" + ValorTipoTramite.ENVIO_ATENCION.getCodigo() + ", " +
                    "" + miSolicitud.getCodSolicitud() + ", " +
                    "" + miSolicitud.getCargo().getCodCargo() + ", " +
                    "null, " +
                    "null, " +
                    "'" + detalle + "')";

          return helpdesk.model.data.OperacionData.ejecutarBloqueSQL(cadSql);

   }
}

