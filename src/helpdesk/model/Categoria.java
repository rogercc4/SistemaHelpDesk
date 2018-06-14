/***********************************************************************
 * Module:  Categoria.java
 * Author:  rcontreras
 * Purpose: Defines the Class Categoria
 ***********************************************************************/

package helpdesk.model;

import java.util.*;

/** Categorias en las que se clasifican los activos
 *
 * @pdOid fe8a2fbe-6e4d-44ab-8ed4-8da91aeceacb */
public class Categoria {
   /** Código de la categoría.
    *
    * @pdOid 3c91cfd1-560f-47e5-b31d-57ba3a8deddc */
   private int codigo;
   /** Nombre de la categoría
    *
    * @pdOid ddf08b8d-ceab-471e-9957-5f7856886a7e */
   private java.lang.String nombre;
   /** @pdOid 17c57db2-70fd-482c-9c4f-7ed88e74d398 */
   private Activo activo;
   /** @pdOid 12d2804f-ba22-4540-8941-950375ab88a8 */
   private java.util.ArrayList<SubCategoria> subCategorias;

   /** @pdOid 8b9cb1d1-95d7-4e31-97d6-8f9d2caf67af */
   public int getCodigo() {
      return codigo;
   }

   /** @param newCodigo
    * @pdOid c8c19ff2-1714-4577-bc8e-cb7bf508dc31 */
   public void setCodigo(int newCodigo) {
      codigo = newCodigo;
   }

   /** @pdOid b8cb3b58-4b76-4135-8040-fdf3c93873b2 */
   public java.lang.String getNombre() {
      return nombre;
   }

   /** @param newNombre
    * @pdOid e56f0282-1704-4d89-aeed-c0a28e483dfd */
   public void setNombre(java.lang.String newNombre) {
      nombre = newNombre;
   }

   /** @pdOid 4a2dbf9e-bd55-4a87-a11c-758e6d91c26c */
   public Activo getActivo() {

   if ( this.getCodigo() <= 0 ) return null ;

    String cadSql = " select activo_id from helpdesk.categoria " +
                    " where  categoria_id = " + this.getCodigo();
    
    helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

    if ( consulta.getNumFilas() <= 0 ) return null ;

    int codActivo = Integer.parseInt(consulta.getResultados()[0][0].toString().trim());
    this.activo = Activo.getActivoBD(codActivo);

    return activo;
   }

   /** @pdOid d043eda4-f04d-4c04-b47c-1a232ec06473 */
   public java.util.ArrayList<SubCategoria> getSubCategorias() {

       if ( this.getCodigo() <= 0 ) return null ;

      String cadSql = "select subcategoria_id from helpdesk.subcategoria "
                        + " where categoria_id = " + this.getCodigo()
                        + " and visible = true order by nombre asc ";

     helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

     if ( consulta.getNumFilas() <= 0 ) return null ;

     Object[][] rstFilas = consulta.getResultados() ;

     int codSubCategoria = 0;
     this.subCategorias = new ArrayList<SubCategoria>();

        for ( Object[] fila : rstFilas  ) {
        codSubCategoria = Integer.parseInt(fila[0].toString().trim());
        this.subCategorias.add(SubCategoria.getSubCategoriaBD(codSubCategoria));
        }

      return subCategorias;
   }

   /** Obtiene una categoría que haya sido registada en la base de datos
    *
    * @param codigo Código con el cual se registró la categoría en la base de datos.
    * @pdOid 046fc38d-f53a-46e0-aa4b-b1245a2deaae */
   public static Categoria getCategoriaBD(int codigo) {
      // TODO: implement

      if ( codigo <= 0 ) return null ;

      String cadSql = " select categoria_id, nombre from helpdesk.categoria " +
                      " where  categoria_id=" + codigo;

      helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);


      if (consulta.getNumFilas()<0)
          return null;

      Object [][] resultados = consulta.getResultados();
      
      Categoria miCategoria = new Categoria();
      miCategoria.setCodigo(codigo);
      miCategoria.setNombre(resultados[0][1].toString());
    
      return miCategoria;
      
   }

}