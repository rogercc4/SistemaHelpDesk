/***********************************************************************
 * Module:  Buscador.java
 * Author:  rcontreras
 * Purpose: Defines the Class Buscador
 ***********************************************************************/

package helpdesk.model;

import helpdesk.model.data.ConsultaData;
import java.util.*;

/** Esta clase representa a un buscador, usado para realizar el compaginado de las busquedas
 *
 * @pdOid 3bbe9e4d-bc31-4c40-9809-9939b5ebac6e */
public class Buscador {
   /** Número de la página.
    *
    * @pdOid 36b69b74-62c3-4c2c-921a-f189d1f8b2e2 */
   private int pagina = 1;
   /** Número de registros por página
    *
    * @pdOid c3a5c831-5353-480c-9150-096864343e29 */
   private int tamanioPagina = 50;
   /** Número de registros que se han encontrado en la búqueda
    *
    * @pdOid ae3a81c4-a995-4bc2-a1e1-f9eba7d1392c */
   private int numRegistros = 0;
   /** Número de páginas encontradas
    *
    * @pdOid f89b36ba-fb79-46ad-9076-75db62798a75 */
   private int numPaginas = 0;

   /** Obtiene el valor de la página actual
    *
    * @pdOid 260b0f91-bfcd-42a9-a061-c55c2a5fcabe */
   public int getPagina() {
      return pagina;
   }

   /** Permite establecer el valor del número de página
    *
    * @param newPagina
    * @pdOid 52d88f22-878c-452f-b7ab-e46c45ea1a3d */
   public void setPagina(int newPagina) {
      pagina = newPagina;
   }

   /** Permite obtener el número de registros que existe por página
    *
    * @pdOid 66cd61a9-31dd-4c77-a537-8e97275cff46 */
   public int getTamanioPagina() {
      return tamanioPagina;
   }

   /** Permite definir el tamaño de la página
    *
    * @param newTamanioPagina
    * @pdOid 3e2db91d-ded1-434b-bab1-6cc99d25c0ee */
   public void setTamanioPagina(int newTamanioPagina) {
      tamanioPagina = newTamanioPagina;
   }

   /** Permite obtener el número de registros encontrados en la búsqueda
    *
    * @pdOid ec506e8c-e191-475e-be61-5377bc121f54 */
   public int getNumRegistros() {
      return numRegistros;
   }

   /** Permite obtener el número de paginas encontrados en la búsqueda
    *
    * @pdOid ceaec210-a92d-4bff-9814-9860d28e35a9 */
   public int getNumPaginas() {

       if ( this.numRegistros <= 0  ) return 0;
       if ( this.tamanioPagina <= 0 ) return 0;

       this.numPaginas = this.numRegistros / this.tamanioPagina;

       if ( this.numRegistros % this.tamanioPagina > 0 ) this.numPaginas++; 

      return this.numPaginas ;
   }

   /** Permite obtener las solicitudes recien registradas por los trabajadores. Una solicitud recien generada es aquella que acaba de ser registrada, y que ha sido enviada para su visto bueno por el jefe inmediato, o para su atención por parte de un service Desk.
    *
    * @param miTrabajador Trabajador que ha registrado la solicitud
    * @param miCargo Cargo con el cual el trabajador ha ingresado al sistema.
    * Lista de solicitudes recien registradas
    * @pdOid 4cb0e3ec-82cf-4420-bb24-d13dc802f453 */
   public java.util.ArrayList<Solicitud> getSolicRegistradas(Trabajador miTrabajador, Cargo miCargo) {
      // TODO: implement
       ArrayList<ValorTipoTramite> tiposTramites = new ArrayList<ValorTipoTramite>();
       tiposTramites.add(ValorTipoTramite.ENVIO_ATENCION);
       tiposTramites.add(ValorTipoTramite.ENVIO_VISTO_BUENO);
       tiposTramites.add(ValorTipoTramite.DAR_VISTO_BUENO);

       ArrayList<Solicitud> misSolicitudes = this.getMisRequerimientos(miTrabajador, miCargo, tiposTramites);

       return misSolicitudes;

       /*
            String cadena="from helpdesk.solicitud s " +
             "inner join helpdesk.tramite t on s.solicitud_id=t.solicitud_id " +
             "where s.solicitud_id in " +
             "(select solicitud_id from helpdesk.tramite " +
             "where cargo_id_origen=" + miCargo.getCodCargo() +
             "  and usuario_origen='" + miTrabajador.getUsuario() + "'" +
             " group by solicitud_id having count(solicitud_id)=1)";

            String cadSQL="select count(s.solicitud_id) " + cadena;

            ConsultaData consulta= new ConsultaData(cadSQL);

            int filas = consulta.getNumFilas();

            if ( filas <= 0 ) return null ; 

            this.numRegistros = Integer.parseInt( consulta.getResultados()[0][0].toString() ) ;

            if ( this.numRegistros <=0 ) return null ; 

            int puntero = (pagina - 1) * tamanioPagina ; 

            String cadSQL1="select s.solicitud_id " + cadena + " order by s.solicitud_id desc "
                                    + "LIMIT " + tamanioPagina + " OFFSET " + puntero ; 

            java.util.ArrayList<Solicitud> misSolicitudes = null;
            ConsultaData consulta1= new ConsultaData(cadSQL1);
            Object[][] resultados1=consulta1.getResultados();

            if (resultados1!=null)
                misSolicitudes=new java.util.ArrayList<Solicitud>();

            for(Object[] fila : resultados1){
               Solicitud miSolicitud=Solicitud.getSolicitudBD(Integer.parseInt(fila[0].toString()));
               misSolicitudes.add(miSolicitud);
            }
       return misSolicitudes;*/
   }

    /** Obtiene aquellas solicitudes que se encuentran pendientes para recibir el visto bueno.
    *
    * @param miTrabajador Trabajador que es el que puede dar el visto bueno.
    * @param miCargo Cargo con el cual puede dar el visto bueno
    * @pdOid c8cfaf80-0e1f-4b16-8187-8a92a7ec0cb4 */
   public java.util.ArrayList<Solicitud> getSolicPendVistoBueno(Trabajador miTrabajador, Cargo miCargo) {
      // TODO: implement
       /*
      if ( miTrabajador == null && miCargo == null ) return null;     

      
     String filtroSql = " solicitud_id in  " +
                        "(" +
                        "select solicitud_id from helpdesk.tramite " +
                        "where solicitud_id = s.solicitud_id and " +
                        "tipo_tramite_id = " + ValorTipoTramite.ENVIO_VISTO_BUENO.getCodigo() + " and " +
                        "(cargo_id_destino = null or cargo_id_destino = " + miCargo.getCodCargo() + ")" +
                        "(usuario_detino = null or usuario_detino = '" + miTrabajador.getUsuario() + "')" +
                        "order by tramite_id desc limit 1 offset 0 " + 
                        ")";
     
     String cadSql = "select count(*) from  helpdesk.solicitud as s where " + filtroSql ;     

     ConsultaData miConsulta = new ConsultaData(cadSql);

     if ( miConsulta.getNumFilas() <= 0 ) return null;

     this.numRegistros = Integer.parseInt(miConsulta.getResultados()[0][0].toString().trim()); 

     if ( this.numRegistros <= 0 ) return null ;

     int puntero = (pagina - 1) * tamanioPagina ;

     cadSql = "select solicitud_id from  helpdesk.tramite where " + filtroSql + 
                " order by tramite_id asc " +
                "LIMIT " + tamanioPagina + " OFFSET " + puntero ;     

     ArrayList<Solicitud> misSolicitudes = new ArrayList<Solicitud>();

     ConsultaData miConsulta2 = new ConsultaData(cadSql);

     Object[][] miRst = miConsulta2.getResultados();

        for (Object[] fila : miRst) {
        misSolicitudes.add(Solicitud.getSolicitudBD( Integer.parseInt(fila[0].toString())) );
        }

      return misSolicitudes;
      */

      ArrayList<Solicitud> miSolicitudes = this.getSolicitudesParaAtender(miTrabajador, miCargo, ValorTipoTramite.ENVIO_VISTO_BUENO);

      return miSolicitudes;

   }

   /** Obtiene las solicitudes a las cuales un usuario puede darle atención
    * en base a la información que se tiene del último tipo de tramite registrado.
    *
    * @param miTrabajador Trabajador que puede atender las solicitudes
    * @param miCargo Cargo que debe tener el trabajador para poder atender las solicitudes
    * @param miTipoTramite Último tipo de trámite que debe tener la solicitud para que pueda ser atendida
    * @pdOid bead5f6f-dd33-404c-acae-63222eb56262 */
   private java.util.ArrayList<Solicitud> getSolicitudesParaAtender(Trabajador miTrabajador, Cargo miCargo, ValorTipoTramite miTipoTramite) {
      // TODO: implement
     if ( miTrabajador == null && miCargo == null ) return null;

    String filtroSql = "(select tipo_tramite_id from helpdesk.tramite where solicitud_id = s.solicitud_id " +
                                "order by tramite_id desc limit 1 offset 0) = " + miTipoTramite.getCodigo() +
                                "and " +
                                "( " +
                                "(select cargo_id_destino from helpdesk.tramite where solicitud_id = s.solicitud_id " +
                                "order by tramite_id desc limit 1 offset 0)  = " + miCargo.getCodCargo() + " or " +
                                "(select cargo_id_destino from helpdesk.tramite where solicitud_id = s.solicitud_id " +
                                "order by tramite_id desc limit 1 offset 0)  is null " +
                                ") " +
                                "and " +
                                "( " +
                                "(select usuario_detino from helpdesk.tramite where solicitud_id = s.solicitud_id " +
                                "order by tramite_id desc limit 1 offset 0) = '" + miTrabajador.getUsuario() + "' " +
                                "or " +
                                "(select usuario_detino from helpdesk.tramite where solicitud_id = s.solicitud_id " +
                                "order by tramite_id desc limit 1 offset 0) is null " +
                                ") " ;


        switch ( miTipoTramite ) {

            case ENVIO_ATENCION:
            filtroSql = " ( ( select count(*) from helpdesk.services_desks where " +
                        " ( usuario is null or usuario = '" + miTrabajador.getUsuario().trim() + "' ) " +   
                        " and cargo_id = " + miCargo.getCodCargo() + " and " +  
                        " tipo_solicitud_id = s.tipo_solicitud_id and con_visto_bueno = true ) > 0 and " +
                        " (select tipo_tramite_id from helpdesk.tramite where solicitud_id = s.solicitud_id " + 
                        " order by tramite_id desc limit 1 offset 0) = " + miTipoTramite.getCodigo() + " and " +
                        " ( select tipo_tramite_id from helpdesk.tramite where solicitud_id = s.solicitud_id " +
                        " order by tramite_id desc limit 1 offset 1 ) = " + ValorTipoTramite.DAR_VISTO_BUENO.getCodigo() + " ) " ;


            filtroSql = filtroSql + " OR " + 
                        " ( ( select count(*) from helpdesk.services_desks where " +    
                            " ( usuario is null or usuario = '" + miTrabajador.getUsuario().trim() + "' ) " +
                            " and cargo_id = " + miCargo.getCodCargo() + " and " +
                            " tipo_solicitud_id = s.tipo_solicitud_id and con_visto_bueno = false ) > 0 and " +
                            " (select tipo_tramite_id from helpdesk.tramite where solicitud_id = s.solicitud_id " +
                            " order by tramite_id desc limit 1 offset 0) = " + miTipoTramite.getCodigo() + " and " +
                            " (( select tipo_tramite_id from helpdesk.tramite where solicitud_id = s.solicitud_id " +
                            " order by tramite_id desc limit 1 offset 1 ) != " + ValorTipoTramite.DAR_VISTO_BUENO.getCodigo() + " OR " +
                            " ( select tipo_tramite_id from helpdesk.tramite where solicitud_id = s.solicitud_id " +
                            " order by tramite_id desc limit 1 offset 1 ) is null ) ) " ;

            break;

        }

     String cadSql = "select count(*) from  helpdesk.solicitud as s where " + filtroSql ;
     
     ConsultaData miConsulta = new ConsultaData(cadSql);

     if ( miConsulta.getNumFilas() <= 0 ) return null;

     this.numRegistros = Integer.parseInt(miConsulta.getResultados()[0][0].toString().trim());

     if ( this.numRegistros <= 0 ) return null ;

     int puntero = (pagina - 1) * tamanioPagina ;

     cadSql = "select solicitud_id from helpdesk.solicitud as s where " + filtroSql +
                " order by s.solicitud_id  asc " +
                "LIMIT " + tamanioPagina + " OFFSET " + puntero ;

     ArrayList<Solicitud> misSolicitudes = new ArrayList<Solicitud>();

     ConsultaData miConsulta2 = new ConsultaData(cadSql);

     Object[][] miRst = miConsulta2.getResultados();

        for (Object[] fila : miRst) {
        misSolicitudes.add(Solicitud.getSolicitudBD( Integer.parseInt(fila[0].toString())) );
        }

      return misSolicitudes;

   }

    /** Permite filtrar los requerimientos o solicitudes de un trabajador, en base a la
    * información del ultimo tipo de tramite que deben tener estas solicitudes.
    *
    * Por ejemplo: Si se desea filtrar los requerimientos o solicitudes cuyo último tramite pueda ser "Envio para visto bueno" o "Envio para atención".
    *
    * @param miTrabajador Trabajador que hizo el requerimiento.
    * @param miCargo Cargo con el cual el trabajador hizo su requerimiento o solicitud
    * @param opcionUltimoTramite Indica los posibles valores del último tipo de tramite que puede tener registrado la solicitud o requrimiento.
    * @pdOid deb4cb9f-0de3-4c7c-9eff-380ad31393a7 */
   private java.util.ArrayList<Solicitud> getMisRequerimientos(Trabajador miTrabajador, Cargo miCargo, java.util.ArrayList<ValorTipoTramite> opcionUltimoTramite) {
      // TODO: implement
       if (miTrabajador == null || miTrabajador.getUsuario().trim().length()<=0)
           return null;
       if (miCargo == null || miCargo.getCodCargo()<=0)
           return null;
       if (opcionUltimoTramite.isEmpty())
           return null;

       Iterator<ValorTipoTramite> valTramites=opcionUltimoTramite.iterator();

       String cadenaCods="";
       while (valTramites.hasNext()){
           int codigo=valTramites.next().getCodigo();
           cadenaCods= cadenaCods + codigo + ",";
       }

       cadenaCods=cadenaCods.trim().substring(0, cadenaCods.trim().length()-1);
       String filtroSql1="(select tipo_tramite_id from helpdesk.tramite " +
                           "where solicitud_id = s.solicitud_id " +
                           "order by tramite_id desc limit 1 offset 0) in (" + cadenaCods + ")";

       String cadSql = "select count(*) from  helpdesk.solicitud as s where " + filtroSql1 +
                       " and usuario='" + miTrabajador.getUsuario() + "'" +
                       " and cargo_id=" + miCargo.getCodCargo();

       ConsultaData miConsulta = new ConsultaData(cadSql);

       if ( miConsulta.getNumFilas() <= 0 ) return null;

       this.numRegistros = Integer.parseInt(miConsulta.getResultados()[0][0].toString().trim());

       if ( this.numRegistros <= 0 ) return null ;

       int puntero = (pagina - 1) * tamanioPagina ;

       cadSql = "select solicitud_id from helpdesk.solicitud as s where " + filtroSql1 +
                " and usuario='" + miTrabajador.getUsuario() + "' and cargo_id=" + miCargo.getCodCargo() +
                " order by s.solicitud_id  asc " +
                "LIMIT " + tamanioPagina + " OFFSET " + puntero ;

       ArrayList<Solicitud> misSolicitudes = new ArrayList<Solicitud>();

       ConsultaData miConsulta2 = new ConsultaData(cadSql);

       Object[][] miRst = miConsulta2.getResultados();

       for (Object[] fila : miRst) {
            misSolicitudes.add(Solicitud.getSolicitudBD( Integer.parseInt(fila[0].toString())) );
       }

       return misSolicitudes;
   }


   /** Retorna los requerimientos que se encuentra en proceso de atencion
    *
    * @param miTrabajador Trabajador que hizo el requerimiento.
    * @param miCargo Cargo con el cual el trabajador hizo su requerimiento o solicitud
    * @pdOid e7289492-75f1-4b70-a0de-a0c46076be88 */
   public java.util.ArrayList<Solicitud> getMisReqProcesoAtencion(Trabajador miTrabajador, Cargo miCargo) {
      // TODO: implement
       ArrayList<ValorTipoTramite> tiposTramite = new ArrayList<ValorTipoTramite>();
       ArrayList<Solicitud> misSolicitudes = null;

       tiposTramite.add(ValorTipoTramite.ATENDER);
       tiposTramite.add(ValorTipoTramite.FINALIZAR_ATENCION);

       misSolicitudes = this.getMisRequerimientos(miTrabajador, miCargo, tiposTramite);


      return misSolicitudes;
   }

   /** Retorna los requerimientos o solicitudes que han sido asignados
    * para que que se les de su respectiva atencion
    *
    * @param miTrabajador Trabajador que hizo el requerimiento.
    * @param miCargo Cargo con el cual el trabajador hizo su requerimiento o solicitud
    * @pdOid d08595cb-e351-4689-aa92-d19be4e91500 */
   public java.util.ArrayList<Solicitud> getMisReqAsignados(Trabajador miTrabajador, Cargo miCargo) {
      // TODO: implement
       ArrayList<ValorTipoTramite> tiposTramite = new ArrayList<ValorTipoTramite>();
       ArrayList<Solicitud> misSolicitudes = null;

       tiposTramite.add(ValorTipoTramite.DERIVAR);

       misSolicitudes = this.getMisRequerimientos(miTrabajador, miCargo, tiposTramite);

      return misSolicitudes;
   }

   /** Retorna los requerimientos que se encuentran listos para dar la respectiva
    * conformidad.
    *
    * @param miTrabajador Trabajador que hizo el requerimiento.
    * @param miCargo Cargo con el cual el trabajador hizo su requerimiento o solicitud
    * @pdOid a9bceada-0ad2-446c-a01f-ac974a375f64 */
   public java.util.ArrayList<Solicitud> getMisReqParaConformidad(Trabajador miTrabajador, Cargo miCargo) {
      // TODO: implement
      ArrayList<ValorTipoTramite> tiposTramite = new ArrayList<ValorTipoTramite>();
      ArrayList<Solicitud> misSolicitudes = null;
       
      tiposTramite.add(ValorTipoTramite.CONFORMIDAD_USUARIO);

      misSolicitudes = this.getMisRequerimientos(miTrabajador, miCargo, tiposTramite);

      return misSolicitudes;
      
   }

   /** Retorna los requerimientos que han sido devueltos
    *
    * @param miTrabajador Trabajador que hizo el requerimiento.
    * @param miCargo Cargo con el cual el trabajador hizo su requerimiento o solicitud
    * @pdOid b5f99cc6-c5aa-45e3-8b5e-76d2958358dc */
   public java.util.ArrayList<Solicitud> getMisReqDevueltos(Trabajador miTrabajador, Cargo miCargo) {
      // TODO: implement
      ArrayList<ValorTipoTramite> tiposTramite = new ArrayList<ValorTipoTramite>();
      ArrayList<Solicitud> misSolicitudes = null;

      tiposTramite.add(ValorTipoTramite.DEVOLVER);

      misSolicitudes = this.getMisRequerimientos(miTrabajador, miCargo, tiposTramite);

      return misSolicitudes;
      
   }

   /** Retorna aquellos requerimientos que han sido rechazados
    *
    * @param miTrabajador Trabajador que hizo el requerimiento.
    * @param miCargo Cargo con el cual el trabajador hizo su requerimiento o solicitud
    * @pdOid 82a565ca-fca0-496a-b581-f5525b607fc6 */
   public java.util.ArrayList<Solicitud> getMisReqRechazados(Trabajador miTrabajador, Cargo miCargo) {
      // TODO: implement
      ArrayList<ValorTipoTramite> tiposTramite = new ArrayList<ValorTipoTramite>();
      ArrayList<Solicitud> misSolicitudes = null;

      tiposTramite.add(ValorTipoTramite.RECHAZAR);

      misSolicitudes = this.getMisRequerimientos(miTrabajador, miCargo, tiposTramite);

      return misSolicitudes;
   }

   /** Retorna aquellas solicitudes pendientes para dar atención.
    *
    * @param miTrabajador Trabajador que puede atender las solicitudes
    * @param miCargo Cargo que debe tener el trabajador para poder atender las solicitudes
    * @pdOid 31b37401-c1eb-4fc6-bbb7-a179fe6f663d */
   public java.util.ArrayList<Solicitud> getSolicPendParaAtender(Trabajador miTrabajador, Cargo miCargo) {
      // TODO: implement
      ArrayList<Solicitud> miSolicitudes = this.getSolicitudesParaAtender(miTrabajador, miCargo, ValorTipoTramite.ENVIO_ATENCION);

      return miSolicitudes;
   }

   /** Retorna aquellas solicitudes que han sido derivadas para que se les de
    * la respectiva atención
    *
    * @param miTrabajador Trabajador que puede atender las solicitudes
    * @param miCargo Cargo que debe tener el trabajador para poder atender las solicitudes
    * @pdOid b5288b99-86f0-4b8c-b838-45fd07dbc363 */
   public java.util.ArrayList<Solicitud> getSolicPendDerivadas(Trabajador miTrabajador, Cargo miCargo) {
      // TODO: implement
      ArrayList<Solicitud> miSolicitudes = this.getSolicitudesParaAtender(miTrabajador, miCargo, ValorTipoTramite.DERIVAR);

      return miSolicitudes;
   }

   /** Retorna aquellas solicitudes enviadas para su atención y que se encuentran
    * en proceso de atención
    *
    * @param miTrabajador Trabajador que puede atender las solicitudes
    * @param miCargo Cargo que debe tener el trabajador para poder atender las solicitudes
    * @pdOid 11c708fa-5918-4ab9-a551-19e74fb2c5d7 */
   public java.util.ArrayList<Solicitud> getSolicPendProcAtencion(Trabajador miTrabajador, Cargo miCargo) {
      // TODO: implement
        ArrayList<Solicitud> miSolicitudes = this.getSolicitudesParaAtender(miTrabajador, miCargo, ValorTipoTramite.ATENDER);

      return miSolicitudes;
   }

   /** Retorna aquellas solicitudes pendientes para que se envien a
    * la persona que lo generó para que esta de el visto bueno
    *
    * @param miTrabajador Trabajador que puede atender las solicitudes
    * @param miCargo Cargo que debe tener el trabajador para poder atender las solicitudes
    * @pdOid 2431f1ae-2020-4a38-8d71-2f9ae4bb3483 */
   public java.util.ArrayList<Solicitud> getSolicPendEnvConformidad(Trabajador miTrabajador, Cargo miCargo) {
      // TODO: implement
        ArrayList<Solicitud> miSolicitudes = this.getSolicitudesParaAtender(miTrabajador, miCargo, ValorTipoTramite.FINALIZAR_ATENCION);

      return miSolicitudes;
   }


   /** Filtra los requerimientos o solicitudes de un determinado usuario en base
    * a la información del último trámite registrado.
    *
    * @param trabajador Trabajador del cual se desea conocer sus requerimientos o solicitudes.
    * @param tipoTramite Último tipo de trámite que debe tener registrado las solicitudes hechas
    * por un trabajador
    * @param fechas Fechas a las cuales se restringe la busqueda
    * @pdOid 9be03fd0-1416-47b3-a633-5bc4c5bf92a9 */
   public java.util.ArrayList<Solicitud> getMisReqPorUltimoTramite(Trabajador trabajador, TipoTramite tipoTramite, Date[] fechas) {
       // TODO: implement

       if (trabajador == null || trabajador.getUsuario().trim().length()<=0 )
           throw new IllegalArgumentException("Falta especificar el trabajador");

      if ( fechas == null || fechas.length <= 0 )
           throw new IllegalArgumentException("Falta especificar las fechas de busqueda");

       if ( fechas.length != 2 )
            throw new IllegalArgumentException("Solo se debe especificar como maximo dos fechas");

       if ( fechas[0].after(fechas[1]) )
            throw new IllegalArgumentException("Espefique correctamente las fechas de busqueda");
       

       String filtroSql1 = null; 

       if (tipoTramite != null && tipoTramite.getCodigo() > 0  ) {
       filtroSql1 = "(select tipo_tramite_id from helpdesk.tramite " +  
                           "where solicitud_id = s.solicitud_id " + 
                           "order by tramite_id desc limit 1 offset 0) = (" + tipoTramite.getCodigo() + ")";
       }

       String cadSql = null; 

       if ( filtroSql1 == null ) {
       cadSql = "select count(*) from  helpdesk.solicitud as s where " +
                       " usuario='" + trabajador.getUsuario() + "' " +
                       " and fecha::date >= '" + fechas[0] + "'::date " +
                       " and fecha::date <= '" + fechas[1] +"'::date " ; 
       }
       else {
        cadSql = "select count(*) from  helpdesk.solicitud as s where " + filtroSql1 +
                               " and usuario='" + trabajador.getUsuario() + "' " +
                               " and fecha::date >= '" + fechas[0] + "'::date " +
                               " and fecha::date <= '" + fechas[1] +"'::date " ; 
       }

       ConsultaData miConsulta = new ConsultaData(cadSql);

       if ( miConsulta.getNumFilas() <= 0 ) return null;

       this.numRegistros = Integer.parseInt(miConsulta.getResultados()[0][0].toString().trim());

       if ( this.numRegistros <= 0 ) return null ;

       int puntero = (pagina - 1) * tamanioPagina ;

       if ( filtroSql1 == null ) {
       cadSql = "select solicitud_id from helpdesk.solicitud as s where " + 
                " usuario='" + trabajador.getUsuario() + "' " +
                " and fecha::date >= '" + fechas[0] + "'::date " +
                " and fecha::date <= '" + fechas[1] + "'::date " +
                " order by s.solicitud_id  asc " +
                " LIMIT " + tamanioPagina + " OFFSET " + puntero ;
       }
       else {
       cadSql = "select solicitud_id from helpdesk.solicitud as s where " + filtroSql1 +
                " and usuario='" + trabajador.getUsuario() + "' " +
                " and fecha::date >= '" + fechas[0] + "'::date " +
                " and fecha::date <= '" + fechas[1] + "'::date " +
                " order by s.solicitud_id  asc " +
                " LIMIT " + tamanioPagina + " OFFSET " + puntero ;
       }



       ArrayList<Solicitud> misSolicitudes = new ArrayList<Solicitud>();

       ConsultaData miConsulta2 = new ConsultaData(cadSql);

       Object[][] miRst = miConsulta2.getResultados();

       for (Object[] fila : miRst) {
            misSolicitudes.add(Solicitud.getSolicitudBD( Integer.parseInt(fila[0].toString())) );
       }

       return misSolicitudes;
       
   }

}