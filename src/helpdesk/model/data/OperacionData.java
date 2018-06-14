/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk.model.data;
import java.sql.*;

/**
 *
 * @author rcontreras
 */
public final class OperacionData {

/**
 * Procedimiento usado para el caso de que queramos realizar
 * operaciones de INSERT, UPDATE o DELETE
 * Devuelve "true" si se ejecuto de manera correcta la cadena SQL.
 * Devuelve "false" si  no se ejecuto de manera correcta la cadena SQL.
 * @param cadSQL Una cadena SQL.
 * @return Retorna verdadero, si la operación se ejecuto con éxito, en caso contrario devuelve falso.
 *
 */
public static  boolean ejecutarSQL(String cadSQL) {
Statement miSentencia;

boolean seEjecutoLaConsulta=false;

int filasAfectadas;

        try {        
        miSentencia = Conexion.getMiConexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                                ResultSet.CONCUR_UPDATABLE);
        
        filasAfectadas = miSentencia.executeUpdate(cadSQL);

            if ( filasAfectadas > 0 ) {
            seEjecutoLaConsulta=true;
            }

        miSentencia.close();
        
        }
        catch(SQLException ex){
        return false ; 
        }

return seEjecutoLaConsulta;

}
//*****************************************************************************
public static boolean ejecutarBloqueSQL(String[] CadSql) {
Statement miSentencia;
boolean seEjecutoLaConsulta=true;
int[] filasAfectadas;

        try {        
        miSentencia = Conexion.getMiConexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

        for (int i=0; i<CadSql.length; i++) {
            miSentencia.addBatch(CadSql[i]);
        }

        filasAfectadas=miSentencia.executeBatch();

        if (filasAfectadas.length>0) {

            for (int x=0; x<filasAfectadas.length; x++) {
                if (filasAfectadas[x]==miSentencia.EXECUTE_FAILED) {
                seEjecutoLaConsulta=false;
                }
            }
        }
        else {
        seEjecutoLaConsulta=false;
        }

        miSentencia.close();
        
        }
        catch(SQLException e){
        return false ; 
        }

return seEjecutoLaConsulta;

}

}