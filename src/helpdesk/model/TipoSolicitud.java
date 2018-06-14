/***********************************************************************
 * Module:  TipoSolicitud.java
 * Author:  Roger
 * Purpose: Defines the Class TipoSolicitud
 ***********************************************************************/

package helpdesk.model;

import java.util.*;
import java.util.ArrayList;

/** Tipos de solicitudes
 * 
 * @pdOid 32c04f55-ee0d-4dbf-83c7-6d555ae0c6e1 */
public class TipoSolicitud {
   /** @pdOid 94a7e718-4597-4e43-9717-ec84e4584ab1 */
   private int codigo;
   /** @pdOid 09c809e4-4729-4afa-b0ff-fdeaa0c6c416 */
   private String nombre;
   /** @pdOid 451babeb-8104-4719-bde8-5dbd10f23779 */
   private String descripcion;
   
   /** @param codSolic
    * @pdOid acd9ca7f-3e83-4d61-8597-5c355c7c4dcc */
   public TipoSolicitud(int newValCodigo) {
        
            // TODO: implement
            this.codigo = newValCodigo ;
            
            String cadSql = "select nombre, descripcion from helpdesk.tipo_solicitud " +
                            " where tipo_solicitud_id= " + newValCodigo;
   
            helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);
            Object[][] resultados = consulta.getResultados() ;
            
                if ( resultados != null ) {
                this.nombre = resultados[0][0].toString();
                this.descripcion = resultados[0][1].toString();
                }
   
   }
   
   /** @pdOid 0628655d-d087-41b0-a61a-1e87f8a82b6d */
   public int getCodigo() {
      return codigo;
   }
   
   /** @pdOid 3d100d4b-1d88-4237-b75f-3eb4dd0224ed */
   public String getNombre() {
      return nombre;
   }
   
   /** @pdOid d0819e2f-0545-45d9-84f9-50aed2e0537e */
   public String getDescripcion() {
      return descripcion;
   }
   
   /** @param filtro
    * @pdOid d9f74656-e201-420c-aa7a-ee08cfb0f8f7 */
   public java.util.ArrayList<FormatoTramite> getFormatosTramite(FiltroRegistros filtro) {
   
   java.util.ArrayList<FormatoTramite> misFormatos = null;
   String cadSql = null ;
   
            // TODO: implement            
            if ( filtro ==  FiltroRegistros.ACTIVADO ) {
            cadSql = "select formato_id, nombre, extension from "
                            + " helpdesk.formato_tramite "
                            + " where tipo_solicitud_id=" + this.getCodigo()
                            + " and visible = true order by formato_id asc " ;
            }
            else if ( filtro ==  FiltroRegistros.DESACTIVADO ) {
            cadSql = "select formato_id, nombre, extension from "
                            + " helpdesk.formato_tramite "
                            + " where tipo_solicitud_id=" + this.getCodigo()
                            + " and visible = false order by formato_id asc " ;
            }
            else if ( filtro ==  FiltroRegistros.TODOS ) {
            cadSql = "select formato_id, nombre, extension from "
                            + " helpdesk.formato_tramite "
                            + " where tipo_solicitud_id=" + this.getCodigo()
                            + " order by formato_id asc " ;
            }
   
            if ( cadSql == null ) return null; 
   
            helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);
            Object[][] resultados = consulta.getResultados(); 

            if ( resultados != null  ) {
            misFormatos = new java.util.ArrayList<FormatoTramite>();            

                for ( Object[] miFila : resultados ) {
                FormatoTramite ft = new FormatoTramite();
                ft.setCodFormato(miFila[0].toString());
                ft.setExtension(miFila[2].toString());
                ft.setNombre(miFila[1].toString());
                misFormatos.add(ft);
                }
   
            }
   
   return misFormatos; 
   }
   
   /** @param filtro
    * @pdOid 54b59c86-6940-4959-bdaa-98f4d33dda01 */
   public static java.util.ArrayList<TipoSolicitud> getTiposSolicitud(FiltroRegistros filtro) {
   // TODO: implement
   java.util.ArrayList<TipoSolicitud> resultados = null;
   String cadSql = null; 
            
            if ( filtro == FiltroRegistros.ACTIVADO ) {
            cadSql = "select tipo_solicitud_id from helpdesk.tipo_solicitud "
                            + " where visible = true order by orden asc ";
            }
            else if ( filtro == FiltroRegistros.DESACTIVADO ) {
            cadSql = "select tipo_solicitud_id from helpdesk.tipo_solicitud "
                            + " where visible = false order by orden asc ";
            }
            else if ( filtro == FiltroRegistros.TODOS ) {
            cadSql = "select tipo_solicitud_id from helpdesk.tipo_solicitud "
                            + " order by orden asc ";
            }

            if ( cadSql == null ) return null ; 

            Object[][] consulta = new helpdesk.model.data.ConsultaData(cadSql).getResultados();

            if ( consulta != null ) {
            resultados = new java.util.ArrayList<TipoSolicitud>();
            
                for (Object[] miFila : consulta ) {
                resultados.add(new TipoSolicitud( Integer.parseInt(miFila[0].toString())));
                }
   
            }
                
   return resultados;
   
   }

}