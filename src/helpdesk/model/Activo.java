/***********************************************************************
 * Module:  Activo.java
 * Author:  rcontreras
 * Purpose: Defines the Class Activo
 ***********************************************************************/

package helpdesk.model;

import java.util.*;

/** Representa los activos de la institución
 *
 * @pdOid b02b7f80-77a7-463b-8a00-4634289250f2 */
public class Activo {
   
      /** Código del activo.
    *
    * @pdOid 0c9c04e1-3f74-4f06-9d29-bf7eaa60b946 */
   private int codigo2;
   /** Nombre del activo
    *
    * @pdOid 657ae72b-070c-439b-8e90-f3a99c1bfbb1 */
   private String nombre;
   /** Categorias que tiene el activo
    *
    * @pdOid 09fe3a27-70a7-48a8-a1f8-4f3ab6dcdf9e */
   private java.util.ArrayList<Categoria> categorias;

   /** @pdOid 5b0e78b4-c76b-4b68-9398-60fe0a38864d */
   public int getCodigo() {
      return codigo;
   }

   /** @param newCodigo
    * @pdOid 36bc314a-f8b6-42b4-ba34-ef48761f3263 */
   public void setCodigo(int newCodigo) {
      codigo = newCodigo;
   }

   /** @pdOid b76c16a5-0bd2-4ae4-889c-783dc7714736 */
   public String getNombre() {
      return nombre;
   }

   /** @param newNombre
    * @pdOid 6c492d9b-899a-4f26-b0da-7b05d88e3da3 */
   public void setNombre(String newNombre) {
      nombre = newNombre;
   }

   /** @pdOid 2f0039bc-6db6-4eab-9f85-9b06a6d17150 */
   public java.util.ArrayList<Categoria> getCategorias() {

       if ( this.getCodigo() <= 0  ) return null ; 

      String cadSql = "select categoria_id from helpdesk.categoria "
                        + " where activo_id = " + this.getCodigo() 
                        + " and visible = true order by nombre asc ";   

     helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

     if ( consulta.getNumFilas() <= 0 ) return null ;

     Object[][] rstFilas = consulta.getResultados() ;

     int codCategoria = 0;
     this.categorias = new ArrayList<Categoria>();

        for ( Object[] fila : rstFilas  ) {
        codCategoria = Integer.parseInt(fila[0].toString().trim());
        this.categorias.add(Categoria.getCategoriaBD(codCategoria));
        }

      return categorias;
   }

   /** Obtiene un activo que haya sido registrado en la base de datos.
    *
    * @param codigo Código con el cual se registró el activo en la base de datos.
    * @pdOid 7664eb66-a46a-43c2-9624-89ea94c72982 */
   public static Activo getActivoBD(int codigo) {
      // TODO: implement

      if ( codigo <= 0 ) return null ; 

      String cadSql = "select activo_id, nombre from helpdesk.activo where activo_id = " + codigo ;

      helpdesk.model.data.ConsultaData consultaData = new helpdesk.model.data.ConsultaData(cadSql);

      if (consultaData.getNumFilas() <= 0) return null ;

      Activo miActivo = new Activo();
      miActivo.setCodigo(codigo);
      miActivo.setNombre(consultaData.getResultados()[0][1].toString().trim()); 
      return miActivo ;
      
   }

   /** Obtiene el listado de todos los activos que se encuentran registrados en la base de datos
    *
    * @param filtro Filtro usado para obtener los registros
    * @pdOid 750d08d9-d421-4fa2-bb38-3baadc98b126 */
   public static java.util.ArrayList<Activo> getListaActivosBD(FiltroRegistros filtro) {
      // TODO: implement

       String cadSql = null;
       switch (filtro) {

           case ACTIVADO:
               cadSql = " SELECT activo_id FROM helpdesk.activo where visible = true "
                       + " order by nombre asc ";
               break;

           case DESACTIVADO:
               cadSql = " SELECT activo_id FROM helpdesk.activo where visible = false "
                       + " order by nombre asc ";
               break;

           default:
               cadSql = " SELECT activo_id FROM helpdesk.activo "   
                       + " order by nombre asc ";
               break; 
       }

       helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

       if ( consulta.getNumFilas() <= 0 ) return null ;

       Object[][] rstFilas = consulta.getResultados() ;

       ArrayList<Activo> resultados = new ArrayList<Activo>();
       int codActivo = 0; 

            for ( Object[] fila : rstFilas ) {
            codActivo = Integer.parseInt(fila[0].toString().trim()); 
            resultados.add(Activo.getActivoBD(codActivo));
            }

      return resultados;
      
   }


   /** Retorna un array que contiene el número de solicitudes comprenido entre dos fechas por cada tipo de activo en el cual se ha hecho la clasificación de solicitudes.
    *
    * @param fechaInicio Fecha de inicio para la busqueda de solicitudes.
    * @param fechaFin Fecha de fin para la busqueda de solicitudes.
    * @pdOid 8ac33aa9-81c1-44d6-8b9d-756622186c9d */
   public static Object[][] getNumSolicitudesPorActivo(java.util.Date fechaInicio, java.util.Date fechaFin) {
      // TODO: implement

   String cadSql = "select helpdesk.activo.activo_id, " +
                    "(select nombre from helpdesk.activo as acti where " +
                    "acti.activo_id = helpdesk.activo.activo_id ) as nombre_activo, " +
                    "count(helpdesk.activo.activo_id) as total " +
                    "from helpdesk.solicitud inner join helpdesk.clasificacion on " +
                    "( helpdesk.solicitud.solicitud_id = helpdesk.clasificacion.solicitud_id ) inner join " +
                    "helpdesk.subcategoria on ( helpdesk.clasificacion.subcategoria_id = " +
                    "helpdesk.subcategoria.subcategoria_id ) " +
                    "inner join helpdesk.categoria on ( helpdesk.subcategoria.categoria_id = helpdesk.categoria.categoria_id ) " +
                    "inner join helpdesk.activo on ( helpdesk.categoria.activo_id = helpdesk.activo.activo_id )  " +
                    "where helpdesk.solicitud.fecha::date >= '" + fechaInicio + "'::date  " +
                    " AND helpdesk.solicitud.fecha::date <= '" + fechaFin + "'::date  " +
                    "group by helpdesk.activo.activo_id order by helpdesk.activo.activo_id asc ";

   helpdesk.model.data.ConsultaData miConsulta = new helpdesk.model.data.ConsultaData(cadSql);

   if ( miConsulta.getNumFilas() <= 0 ) return null ; 

   return miConsulta.getResultados() ;

   }

}