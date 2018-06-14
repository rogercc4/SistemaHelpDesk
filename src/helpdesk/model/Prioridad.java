/***********************************************************************
 * Module:  Prioridad.java
 * Author:  rcontreras
 * Purpose: Defines the Class Prioridad
 ***********************************************************************/

package helpdesk.model;

import helpdesk.model.data.ConsultaData;
import java.util.*;

/** Representa la prioridad de una solicitud
 *
 * @pdOid 019dc356-5624-4a1a-aead-0f96482f1b5b */
public class Prioridad {
   /** Código de la prioridad
    *
    * @pdOid 714f57ca-810d-4bb6-9dcf-4aafc17a0b12 */
   private int codigo;
   /** Nombre descriptivo de la prioridad
    *
    * @pdOid 2f4a3303-e379-4e15-9857-1f0c4f1af5bf */
   private String nombre;
   /** Indica el nivel de importancia de la prioridad. Por ejemplo un orden de valor "1" tiene mas importancia que uno de valor 2.
    *
    * @pdOid a8bd1e29-f3d3-44b6-9ca5-1c5a82ee4292 */
   private int orden;
   /** Retorna el código de la prioridad
    *
    * @pdOid 214abb00-b727-4689-84ab-79e6ec8ce7d9 */
   public int getCodigo() {
      return codigo;
   }

   /** Establece el valor del código de la prioridad
    *
    * @param newCodigo
    * @pdOid 82437927-c19b-4e8b-961f-6529e1051404 */
   public void setCodigo(int newCodigo) {
      codigo = newCodigo;
   }

   /** Retorna el nombre de la prioridad
    *
    * @pdOid 7d357977-6aa0-4731-b301-0f8b98be68a9 */
   public String getNombre() {
      return nombre;
   }

   /** Establece el nombre de la prioridad
    *
    * @param newNombre
    * @pdOid 75b1c185-28e3-441f-8752-a2d57173e47b */
   public void setNombre(String newNombre) {
      nombre = newNombre;
   }

      /** @pdOid 01aa012d-9aae-4383-a017-7d4473577a4c */
   public int getOrden() {
      return orden;
   }

   /** @param newOrden
    * @pdOid f69d0ea5-0184-466a-95a4-4b56c78c88f1 */
   public void setOrden(int newOrden) {
      orden = newOrden;
   }

   /** Obtiene el listado de las prioridades
    *
    * @pdOid b621c495-a4ef-4042-8c51-a6f95e947d60 */
   public static java.util.ArrayList<Prioridad> getPrioridades() {
      // TODO: implement
      String cadSql = "select prioridad_id from helpdesk.prioridad order by orden asc";

      ConsultaData consulta = new ConsultaData(cadSql);

      if (consulta.getNumFilas() <= 0) return null;

      Object[][] resultados = consulta.getResultados();
      ArrayList<Prioridad> prioridades = new ArrayList<Prioridad>(); 

        for(Object[] fila:resultados) {
        prioridades.add(Prioridad.getPrioridadBD(Integer.parseInt(fila[0].toString().trim())));
        }

      return prioridades;
      
   }

   /** Obtiene el listado de las prioridades de acuerdo a lo registrado en la base de datos.
    *
    * @param codigo Código con el que se registro la prioridad en la base de datos.
    * @pdOid 3089c760-43d9-4c2c-8700-9fce03ee2ba4 */
   public static Prioridad getPrioridadBD(int codigo) {
      // TODO: implement

       if (codigo<0) return null;

       String cadSql="select prioridad_id, nombre, orden from helpdesk.prioridad " +
                    "where prioridad_id=" + codigo;

       ConsultaData consulta = new ConsultaData(cadSql);

       if (consulta.getNumFilas()<=0) return null;

       Prioridad miPrioridad = new Prioridad();
       miPrioridad.setCodigo(codigo);
       miPrioridad.setNombre(consulta.getResultados()[0][1].toString());
       miPrioridad.setOrden(Integer.parseInt(consulta.getResultados()[0][2].toString()));

       return miPrioridad;
   }

}