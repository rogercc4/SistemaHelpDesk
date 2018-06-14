/***********************************************************************
 * Module:  SubCategoria.java
 * Author:  rcontreras
 * Purpose: Defines the Class SubCategoria
 ***********************************************************************/

package helpdesk.model;

import java.util.*;
import helpdesk.model.data.*;

/** Sub categoría en la que se clasifica una categoría
 *
 * @pdOid 3b42bfbd-cca5-4312-b1cd-3c58fdd5f8ec */
public class SubCategoria {
   /** Código de la sub categoría
    *
    * @pdOid 5954de6e-6cb6-40cb-b7ed-9536a4d253f2 */
   private int codigo;
   /** Nombre de la subcategoría
    *
    * @pdOid 7a625539-b527-4100-85c6-963e5873cd29 */
   private String nombre;
   /** @pdOid 094eda4c-dc78-4ebc-96ff-3c5974eeb223 */
   private Categoria categoria;

   /** @pdOid 2f34a737-fef1-4705-9930-4b985d11a386 */
   public int getCodigo() {
      return codigo;
   }

   /** @param newCodigo
    * @pdOid ab3e4292-773a-4cc1-9951-dbb069a81367 */
   public void setCodigo(int newCodigo) {
      codigo = newCodigo;
   }

   /** @pdOid d3acd2aa-c3c1-4af9-9ffb-d79cb48a2576 */
   public String getNombre() {
      return nombre;
   }

   /** @param newNombre
    * @pdOid d6b981ee-d93b-4bb6-893d-aa2786777d3f */
   public void setNombre(String newNombre) {
      nombre = newNombre;
   }

   /** @pdOid 95cc083d-7ffc-4b88-ae11-afa30d852420 */
   public Categoria getCategoria() {

    String cadSql = " SELECT categoria_id " +   
                        " FROM helpdesk.subcategoria " +    
                        " WHERE subcategoria_id = " + this.getCodigo() ;

   ConsultaData consulta = new ConsultaData(cadSql);
   
   if ( consulta.getNumFilas() < 0 ) return null;

   Object[][] resultados = consulta.getResultados();

   this.categoria = Categoria.getCategoriaBD(Integer.parseInt(resultados[0][0].toString().trim())); 

    return this.categoria;
   }

   /** Obtiene una sub categoría que haya sido registrada en la base de datos.
    *
    * @param codigo Código con el cual se registró la sub categría en la base de datos.
    * @pdOid 1f3f32b8-8d84-45f3-81a4-2616b3b15aa3 */
   public static SubCategoria getSubCategoriaBD(int codigo) {
      // TODO: implement

       if ( codigo <= 0 ) return null;

       String cadSql = " SELECT subcategoria_id, nombre " + 
                        " FROM helpdesk.subcategoria WHERE subcategoria_id = " + codigo ;

       ConsultaData consulta = new ConsultaData(cadSql);
       if ( consulta.getNumFilas() < 0 ) return null;

       Object[][] resultados = consulta.getResultados();

       SubCategoria miSubCateg = new SubCategoria();
       miSubCateg.setCodigo(codigo);
       miSubCateg.setNombre(resultados[0][1].toString());

       return miSubCateg;
      
   }

   /** Obtiene los cargos responsables de dar atencion a una solicitud que se encuentre dentro de una determinada sub-categoria
    *
    * @pdOid 30a7891e-6c62-4e66-9f66-d6db066d82ee */
   public ArrayList<Cargo> getCargosResponsable() {
      // TODO: implement

   String cadSql = "select cargo_id from helpdesk.responsable_atencion "
                        + "where subcategoria_id=" + this.getCodigo() + " order by cargo_id asc";
     
     ConsultaData miConsulta = new ConsultaData(cadSql);

     if ( miConsulta.getNumFilas() <= 0 ) return null;

     Object[][] filas = miConsulta.getResultados();
     ArrayList<Cargo> cargos = new ArrayList<Cargo>(); 


        for ( Object[] fila : filas) {
        cargos.add(Cargo.getCargoBD(Integer.parseInt(fila[0].toString())));
        }
       
      return cargos;
   }
   
}