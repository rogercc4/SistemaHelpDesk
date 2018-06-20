/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.model.pattern;

import helpdesk.model.Cargo;
import helpdesk.model.Trabajador;
import java.util.Map;

/**
 *
 * @author Roger
 */
public class TrabajadorFlyweightFactory {
    
   private static Map<String, Trabajador> mapTrabajadoresEnCache = new java.util.HashMap<String, Trabajador>();
    
   private TrabajadorFlyweightFactory() {
       
   }
    
   
   public static Trabajador getTrabajador(String usuario) {
       Trabajador trabajador = mapTrabajadoresEnCache.get(usuario);
       
       if ( trabajador == null ) {
           trabajador = findTrabajadorBD(usuario);
           mapTrabajadoresEnCache.put(usuario, trabajador);
       }
       
       return trabajador;
       
   }
    
   private static Trabajador findTrabajadorBD(String usuario) {
       if ( usuario == null || usuario.trim().length() <= 0 ) return null;

       usuario = usuario.trim();

      String cadSql = "select anexousuario, apellido, correo, dni, nombre, usuario, cargo_id  "
                    + "from trabajador where usuario='" + usuario + "'" ;

      helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

       if ( consulta.getNumFilas() <= 0 ) return null;

       Object[][] resultado = consulta.getResultados() ;

       Trabajador miTrabajador = new Trabajador();

       for ( Object[] fila : resultado  ) {

           if ( fila[0] != null )
           miTrabajador.setAnexo(fila[0].toString().trim());

           miTrabajador.setApellido(fila[1].toString().trim());

           miTrabajador.setCorreo(fila[2].toString().trim());

           miTrabajador.setDni(fila[3].toString().trim());

           miTrabajador.setNombre(fila[4].toString().trim());

           miTrabajador.setUsuario(fila[5].toString().trim());

           miTrabajador.setCargo( Cargo.getCargoBD(Integer.parseInt(fila[6].toString().trim())) );

       }

      return miTrabajador ;

   }
    
}
