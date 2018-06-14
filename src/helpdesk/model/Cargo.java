/***********************************************************************
 * Module:  Cargo.java
 * Author:  Roger
 * Purpose: Defines the Class Cargo
 ***********************************************************************/

package helpdesk.model;

import java.util.*;

/** @pdOid 5b282116-df28-4952-b47a-7e82d6ca3a49 */
public class Cargo {
   /** Código del cargo
    *
    * @pdOid f47f88e4-ebc5-4f26-98a4-f89d9b243ba7 */
   private int codCargo;
   /** Nombre del cargo
    *
    * @pdOid 26d51dfd-d9d0-49ca-bc7b-5b917acbe6bc */
   private String nombre;
   /** @pdOid cafbe88f-984a-4020-943d-c7ec73dd9744 */
   private Area area;
   /** @pdOid 112bbf97-2f44-45a8-9e43-c3321ab7e22c */
   private boolean jefe;

   /** @pdOid fdf43800-26a4-46d2-b6e0-ad5034ceb704 */
   public int getCodCargo() {
      return codCargo;
   }

   /** @param newCodCargo
    * @pdOid 171ed236-de19-4df4-91b7-edb6829350fb */
   public void setCodCargo(int newCodCargo) {
       if (newCodCargo<=0)
           throw new IllegalArgumentException("Ingresar un código de cargo valido");

        codCargo = newCodCargo;
   }

   /** @pdOid 5c6ce9cb-c419-47d5-96fb-4e503d8da559 */
   public String getNombre() {
      return nombre;
   }

   /** @param newNombre
    * @pdOid 2b02a30e-27b4-494d-b135-acb159124489 */
   public void setNombre(String newNombre) {
       if (newNombre==null || newNombre.trim().length()<=0)
               throw new IllegalArgumentException("Ingresar un nombre de cargo");

      nombre = newNombre.trim();
   }

   /** @pdOid aeaeb973-96a7-430e-8fa1-e66018275501 */
   public Area getArea() {
      return area;
   }

   /** @param newArea
    * @pdOid 48d3f0b1-ab80-4677-af16-1c0eca975012 */
   public void setArea(Area newArea) {
       if ((newArea==null) || (newArea.getCodArea()<=0))
            throw new IllegalArgumentException("Debe seleccionar un Area");
              
      area = newArea;
   }

   /** @pdOid 8c171681-5e97-4166-a704-4b5ad7cdc20b */
   public boolean getJefe() {
      return jefe;
   }

   /** @param newJefe
    * @pdOid de2cec1c-f386-4f98-9c4e-61dbc0ea056f */
   public void setJefe(boolean newJefe) {
      jefe = newJefe;
   }

   /** Obtener un cargo que se encuentra registrado en la base de datos
    *
    * @param codigo Código del cargo
    * @pdOid 554ae19d-9bb1-487c-8fe6-86e1dd2a517d */
   public static Cargo getCargoBD(int codigo) {
      // TODO: implement

      Cargo miCargo = null ;

      if ( codigo <= 0) return null;

   String cadSql = "select cargo_id, nombre, area_id, jefe_area "
                   + "from cargo where cargo_id = " + codigo ;

   helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

      if ( consulta.getNumFilas() <= 0 ) return null ;

   miCargo = new Cargo();

   Object[][] resultados = consulta.getResultados() ;


   miCargo.setArea(new Area( Integer.parseInt(resultados[0][2].toString()) ));
   miCargo.setCodCargo(codigo);
   miCargo.setJefe(Boolean.parseBoolean(resultados[0][3].toString()));
   miCargo.setNombre(resultados[0][1].toString());

   return miCargo;

   }

   /** @pdOid 7fb55ba5-9398-4fc8-8ca1-a30d17359c72 */
   public Cargo getCargoJefe() {
      // TODO: implement
   ArrayList<Cargo> cargos = null   ;

        if ( this.getJefe() == true ) {
        Area areaTop = this.getArea().getAreaSuperior();

            if ( areaTop == null ) return null ;

        cargos = areaTop.getCargos(FiltroRegistros.ACTIVADO);

        }
        else {

       cargos = this.getArea().getCargos(FiltroRegistros.ACTIVADO);

        }

        if ( cargos == null ) return null ;

        Iterator<Cargo> i = cargos.iterator();

            while ( i.hasNext() ) {
            Cargo c = i.next();
                if ( c.getJefe() == true ) return c ;
            }

   return null;

   }

    /** Trabajadores que se encuentran con ese cargo actualmente
    *
    * @pdOid 0a7c2815-c9fa-4a83-87ef-57af941cfadd */
   public java.util.ArrayList<Trabajador> getTrabajadores() {
      // TODO: implement
      if ( this.codCargo <= 0  ) return null ;


      String cadSql = "select usuario from trabajador where cargo_id=" + this.codCargo
                      + " and eliminado = false order by usuario asc "  ;

      helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

      if ( consulta.getNumFilas() <= 0 ) return null ;

      Object[][] resultado = consulta.getResultados() ;

      ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();

            for ( Object[] fila : resultado ) {
            Trabajador miTrabajador = Trabajador.getTrabajadorBD(fila[0].toString().trim());
            trabajadores.add(miTrabajador);

                if ( miTrabajador.getTrabajadorRemplazo() != null ) {
                trabajadores.add(miTrabajador.getTrabajadorRemplazo());
                }
            
            }

      return trabajadores ;

   }
}