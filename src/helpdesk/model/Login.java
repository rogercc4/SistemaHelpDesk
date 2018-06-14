/***********************************************************************
 * Module:  Login.java
 * Author:  Roger
 * Purpose: Defines the Class Login
 ***********************************************************************/

package helpdesk.model;
import helpdesk.model.data.*;
import java.util.*;
import java.util.regex.*;

/** @pdOid 4035165a-31a9-42c2-aab9-0ba66ae76214 */
public class Login {
   /** Nombre de la cuenta de usuario
    *
    * @pdOid 9ee8768e-f883-4a39-87c7-2532592ae0c0 */
   private java.lang.String usuario;
   /** Clave del usuario
    *
    * @pdOid 7832aec1-79e9-4011-8053-391fdc86c9a5 */
   private java.lang.String clave;
   /** IP de la máquina desde donde se puede hacer login
    *
    * @pdOid f1b7e9c9-c65e-4c37-afde-2b638309a826 */
   private java.lang.String maquina;

   /** Constructor de la clase que permite crear una instancia de esta clase en base al nombre de la cuenta de usuario
    *
    * @param miUser Nombre de la cuenta de usuario
    * @exception helpdesk.model.HelpDeskException
    * @pdOid 1b681235-d88c-49be-b063-4599f3f1a47b */
   public Login(java.lang.String miUser) throws helpdesk.model.HelpDeskException {
      // TODO: implement
       if ( miUser != null && miUser.trim().length() > 0 )  {
            // comprueba que no contenga caracteres prohibidos
            Pattern p = Pattern.compile("[^A-Za-z0-9]");
            Matcher  m = p.matcher(miUser.trim());
            if(m.find())
                throw new HelpDeskException("nombre de usuario contiene caracteres prohibidos");
             else
                 this.usuario = miUser.trim();
       }
       else
            throw new HelpDeskException("Falta ingresar el nombre del usuario");
  }

   /** @pdOid ee1713ad-8980-41a4-b8f6-29fabb22eabf */
   public java.lang.String getClave() {
      return clave;
   }

   /** @param newClave
    * @pdOid e69d828a-fcdf-4076-b163-68225dd5ca2e */
   public void setClave(java.lang.String newClave) {

   String regex = "[0-9a-zA-ZñÑ]+";
   Pattern p = Pattern.compile(regex);
   Matcher m = p.matcher(newClave);

   if ( newClave == null || newClave.trim().length() <= 0 )
          throw new IllegalArgumentException("Falta especificar la clave");

    newClave = newClave.trim();

      if ( newClave.trim().length() <= 5 )
          throw new IllegalArgumentException("La clave debe tener como minimo 6 caracteres");

       if ( !m.matches()  ) {
         throw new IllegalArgumentException("La clave debe tener valores formados por numeros y letras");
       }

    this.clave = newClave.trim();

   }

   /** @pdOid 498818d7-08c4-4452-957c-95bfb5d48c77 */
   public java.lang.String getMaquina() {
      return maquina;
   }

   /** @param newMaquina
    * @pdOid e09695e5-d6fc-4ba4-9d12-87b7ed5d5f1b */
   public void setMaquina(java.lang.String newMaquina) {
    if ( newMaquina != null && newMaquina.trim().length() > 0  ) {
      maquina = newMaquina;
     }
   }

   /** @pdOid c4d59e5d-ce1f-4106-8635-2e8851c51d1b */
   public java.lang.String getUsuario() {
      return usuario;
   }

   /** Verifica si se puede iniciar sesion
    *
    * @exception helpdesk.model.HelpDeskException
    * @pdOid 3fdf1008-c662-43a3-9d24-0d2c18524d20 */
   public void validarLogin() throws helpdesk.model.HelpDeskException {
      // TODO: implement
        //if ((usuario != null && usuario.trim().length() > 0) && (clave != null && clave.trim().length() > 0)){
       if ( this.getUsuario() != null && this.getClave() != null ) {
            String selectSql5="Select * from login where usuario ='" + this.getUsuario() + "'";
            ConsultaData consulta5 = new ConsultaData(selectSql5);
            if (consulta5.getNumFilas()<=0)
                throw new HelpDeskException("Usuario y/o clave incorrecta");

            String selectSql6="Select * from login where usuario ='" + this.getUsuario() + "' and " +
                    " inactivo=true";
            ConsultaData consulta6=new ConsultaData(selectSql6);
            if (consulta6.getNumFilas()>0)
                throw new HelpDeskException("Login de usuario esta inactivado");

            String selectSql="Select * from login where usuario ='" + this.getUsuario() + "' and " +
                            "clave=md5('" + this.getClave() + "')";

            ConsultaData consulta=new ConsultaData(selectSql);
            if (consulta.getNumFilas()>0){
                if ( this.getMaquina() != null ){
                    //se verifica si existe usuario, clave y maquina
                    String selectSql1=selectSql + " and "
                             + " (maquina like '%" + this.getMaquina() + "%' or maquina is null) ";
                    ConsultaData consulta1=new ConsultaData(selectSql1);
                    if (consulta1.getNumFilas()>0){
                       //verificar si trabajador no se encuentra desactivado
                       String selectSql2="select * from trabajador where usuario='" +
                                            this.getUsuario() + "' and eliminado=false";
                       ConsultaData consulta2=new ConsultaData(selectSql2);

                       if (consulta2.getNumFilas()>0){
                            //verifica que trabajador no tenga reemplazo vigente
                            String selectSql4="select * from remplazo where usuario='" +
                                  this.getUsuario() + "' and fecha_inicio<now() and fecha_fin>now()";
                            ConsultaData consulta4=new ConsultaData(selectSql4);
                            if (consulta4.getNumFilas()==0){
                                String insertSQL1="insert into login_historico (usuario, fecha, ip, exito)" +
                                                    "values('" + this.getUsuario() + "',now(),'" + this.getMaquina() + "', false)";

                                    if ( OperacionData.ejecutarSQL(insertSQL1) == false ) {
                                            System.out.println("No se pudo guardar el registro histórico");
                                    }

                             }
                             else
                                   throw new HelpDeskException("La cuenta de usuario esta desactivada");
                        }
                        else
                              throw new HelpDeskException("La cuenta de usuario esta desactivada");
                  }
                  else
                        throw new HelpDeskException("El usuario no puede acceder desde esta máquina");
                }

            }
            else{
                //guardar un registro en historico de login
                String insertSQL="insert into helpdesk.login_historico (usuario, fecha, ip, exito)" +
                                 " values('" + this.getUsuario() + "',now(), " +
                                 (( this.getMaquina() == null ) ? " NULL " : "'" + this.getMaquina() + "'" ) +
                                 ", false)";
                //System.out.println(selectSql);
                System.out.println(insertSQL);
                if (OperacionData.ejecutarSQL(insertSQL)){
                    //verifica si es el 4to registro erroneo del login para ese dia
                      String selectSql3 =  "select * from helpdesk.login_historico where usuario = '" + this.getUsuario() + "'" +
                                            " and exito=false and (fecha > (CURRENT_TIMESTAMP - interval '900 seconds'))";

                    ConsultaData consulta3=new ConsultaData(selectSql3);

                      if ( consulta3.getNumFilas() >= 15 ) {
                        //inactivar login de usuario
                            String updateSQL="update login set inactivo = true " +
                                    "where usuario = '" + this.getUsuario() + "'";

                                if (OperacionData.ejecutarSQL(updateSQL))
                                    throw new HelpDeskException("Su cuenta ha sido desactivada, debida a que usted a ingresado muchas veces una clave incorrecta");
                      }

                      throw new HelpDeskException("Usuario y/o clave incorrecta");
                }

            }

   }
    else
     throw new HelpDeskException("Falta indicar el usuario o la clave");
}

   /** Cargos con los cuales puede iniciar sesion el usuario
    *
    * @pdOid f745e0bd-3b10-4bdf-b569-372304a794c2 */
   public ArrayList<Cargo> getCargosLogin() {
      // TODO: implement
        ArrayList<Cargo> misCargos = null;

       String selectSql = "select cargo_id from trabajador where usuario in "+
               "(select usuario from remplazo where usuario_remplazo='" + this.getUsuario() +
               "' and fecha_inicio<=now()::date and fecha_fin>=now()::date)"+
               " union select cargo_id from trabajador where usuario='" + this.getUsuario() + "'" ;

       Object[][] consulta = new ConsultaData(selectSql).getResultados();

       if ( consulta == null ) return null ;

       misCargos = new ArrayList<Cargo>();

       for (Object[] miFila : consulta ) {
       Cargo miCargo = Cargo.getCargoBD(Integer.parseInt(miFila[0].toString()));
            if   ( miCargo != null) misCargos.add(miCargo);
       }

      return misCargos;

   }

   /** Permite cambiar la clave del trabajador
    *
    * @param clave Clave que se desea tener
    * @exception helpdesk.model.HelpDeskException
    * @pdOid fa69615a-d311-40df-86ec-30af15b2d4ed */
   public void cambiarClave(String clave) throws helpdesk.model.HelpDeskException {
      // TODO: implement

      Login loginNuevo = new Login(this.getUsuario());
      loginNuevo.setClave(clave);

      this.validarLogin();

      String cadSql = "UPDATE login SET clave = md5('" + loginNuevo.getClave() + "') "
                        + " WHERE usuario = '" + loginNuevo.getUsuario() + "'";

      helpdesk.model.data.OperacionData.ejecutarSQL(cadSql);
      loginNuevo.validarLogin();

   }

}