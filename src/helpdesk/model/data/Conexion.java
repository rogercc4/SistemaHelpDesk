/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk.model.data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rcontreras
 */
public final class Conexion {
private final String nombreDriver="org.postgresql.Driver";

private String servidor;
private String puerto;
private String usuario;
private String clave;
private String baseDatos;

private static Connection miConexion;

/**
   * Constructor de la clase, primer método que se ejecutalocalhost al crear este objeto
   */
  public Conexion() {
        try {
            Class.forName(nombreDriver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
 }

  public void setServidor( String ipServer ) {
  this.servidor = ipServer.trim();
  }

  public void setPuerto( String miPuerto ) {
  this.puerto = miPuerto.trim();
  }

  public void setUsuario( String miUsuario ) {
  this.usuario = miUsuario.trim();
  }

  public void setClave( String miClave ) {
  this.clave = miClave.trim();
  }

  public void setBaseDatos( String miBaseDatos ) {
  this.baseDatos = miBaseDatos.trim();
  }

  public void setConexion() throws SQLException {

      if ( miConexion == null ) {
      //final String url = "jdbc:postgresql://" + this.servidor + ":" + this.puerto + "/" + this.baseDatos + "&ssl=true";
          final String url = "jdbc:postgresql://" + 
                                this.servidor + ":" + 
                                this.puerto + "/" + 
                                this.baseDatos + "?user=" + 
                                this.usuario + "&password=" +
                                this.clave + "&ssl=true";
          
      //miConexion = DriverManager.getConnection(url, this.usuario, this.clave);
          miConexion = DriverManager.getConnection(url);
      }
  
  }

    /**
    * Esta función devuelve la conexion a la base de datos
    */
  public static Connection getMiConexion() {
  return miConexion;
  }

  /**
  * Método que permite cerrar la conexion con la base de datos.
  */
  public void cerrarConexion() throws SQLException {
  //Usado para cerrar el objeto conexion
  miConexion.close();
  }

}