/***********************************************************************
 * Module:  Area.java
 * Author:  Roger
 * Purpose: Defines the Class Area
 ***********************************************************************/

package helpdesk.model;

import java.util.*;

/** Área o unidad organizacional
 *
 * @pdOid fe519e1b-2a0d-462d-8ba8-a014858e402c */
public class Area {
   /** Código que identifica de manera única el área o unidad organizacional
    *
    * @pdOid 6df9188e-af20-4342-b164-9b640d3cfd3b */
   private int codArea;
   /** Nombre del área o unidad organizacional
    *
    * @pdOid d09ef88d-6096-43f5-912c-6bc9cb4144d3 */
   private String nombre;

   /** @param codigo Código del área
    * @pdOid 4c2fc689-c244-4b42-a1aa-0aeb803236e2 */
   public Area(int codigo) {
      // TODO: implement

       if ( codigo > 0 )  {
       
       String cadSql = "select nombre from area where area_id = " + codigo ;
       Object[][] resultado = new helpdesk.model.data.ConsultaData(cadSql).getResultados() ;

            if ( resultado != null ) {
            this.codArea = codigo ;
            this.nombre = resultado[0][0].toString().trim() ;
            }
       
       }
   }

   /** @pdOid 3716589a-3345-4e91-bb59-045de35ffa3a */
   public int getCodArea() {
      return codArea;
   }

   /** @pdOid 0b6a54ca-20ed-4387-8f94-79fcfa09e3f7 */
   public String getNombre() {
      return nombre;
   }

   /** @pdOid dd4a52c0-2187-4ebe-8252-d10e4faa1eb6 */
   public Area getAreaSuperior() {
      // TODO: implement

       if ( this.codArea <= 0 ) return null ;

      String cadSql = "select cod_top_area from area where area_id =  " + this.codArea ;

      helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

      if ( consulta.getNumFilas() <= 0 ) return null ;

      Object[][] resultados = consulta.getResultados();

      if ( resultados[0][0] == null ) return null ;

      Area miArea = new Area(Integer.parseInt(resultados[0][0].toString()));

      return miArea ;
   }

   /** @pdOid ba1c8133-0d19-4231-938b-b6b2d1e7ce9c */
   public java.util.ArrayList<Cargo> getCargos( FiltroRegistros filtro ) {
      // TODO: implement
      String cadSql = null;

       if ( this.codArea <= 0 ) return null;

       if ( filtro == FiltroRegistros.ACTIVADO ) {
       cadSql = "select cargo_id, nombre, area_id, jefe_area from "
                      + "cargo where area_id = " + this.codArea + " and visible = true" ;
       }
       else if ( filtro == FiltroRegistros.DESACTIVADO ) {
       cadSql = "select cargo_id, nombre, area_id, jefe_area from "
                      + "cargo where area_id = " + this.codArea + " and visible = false" ;
       }
       else if ( filtro == FiltroRegistros.TODOS ) {
       cadSql = "select cargo_id, nombre, area_id, jefe_area from "
                      + "cargo where area_id = " + this.codArea ;
       }

       if ( cadSql == null ) return null ;

      helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

      if ( consulta.getNumFilas() <= 0 ) return null ;

      ArrayList<Cargo> misCargos = new ArrayList<Cargo>();

      Object[][] resultados = consulta.getResultados() ;

        for ( Object[] fila : resultados ) {
        Cargo miCargo = new Cargo();
        miCargo.setArea(new Area( Integer.parseInt(fila[2].toString()) ));
        miCargo.setCodCargo( Integer.parseInt(fila[0].toString()) );
        miCargo.setJefe(Boolean.parseBoolean(fila[3].toString()));
        miCargo.setNombre(fila[1].toString());
        misCargos.add(miCargo);
        }

      return misCargos;

   }

}