/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk.model.data;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase es utilizada para realizar consultas a la base de datos
 * @author Roger
 */
public final class ConsultaData {
private int numFilas = 0;
private int numColumnas = 0;

private ResultSet miRst = null;

    /**
     * Constructor de la clase
     * @param cadSql -- Cadena SQL utilizada para consultar a la base de datos
     *
     */
    public ConsultaData( String cadSql ) {
    Statement miSentencia = null;
    java.sql.ResultSetMetaData rsmd = null;
    
            try {
                miSentencia = Conexion.getMiConexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                                       ResultSet.CONCUR_READ_ONLY);
                cadSql = cadSql.trim();
                this.miRst = miSentencia.executeQuery(cadSql);

                    if ( this.miRst != null ) {
                    rsmd = this.miRst.getMetaData();
                    this.numColumnas = rsmd.getColumnCount();
                    this.miRst.last();
                    this.numFilas = this.miRst.getRow();
                    this.miRst.beforeFirst();
                    }

            } catch (SQLException ex) {
                Logger.getLogger(ConsultaData.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }

    /**
     * Resultado de la consulta a la base de datos como un array bidimensional. En donde
     * la primera dimensión indica la fila (Empieza desde 0 para la primera fila) y la segunda
     * dimension representa a la columna (Empieza desde 0 para la primera columna)
     * @return Un valor NULL si no hay registros. Un array bidimensional en caso existan registros
     */
    public Object[][] getResultados( ) {
    Object[][] resultados = null ;
    
        try {
        ResultSetMetaData rsmd = this.miRst.getMetaData();
        //rsmd.getColumnClassName(numColumnas)

        if ( this.numFilas <= 0 ) return null; 

            if ( this.miRst != null ) {

                if ( this.miRst.isAfterLast() == true ) this.miRst.beforeFirst();

            resultados = new Object[this.numFilas][this.numColumnas];
            int row = 0;
            
                while (this.miRst.next()) {
                
                    for (int col = 1; col <= this.numColumnas; col++ ) {
                    resultados[row][col-1] = this.miRst.getObject(col);
                    }
                    
                row++;
                }
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaData.class.getName()).log(Level.SEVERE, null, ex);
            return null; 
        } catch (Exception ex) {
            Logger.getLogger(ConsultaData.class.getName()).log(Level.SEVERE, null, ex);
            return null ; 
        }
        

     return resultados; 

    }


    /**
     * Permite calcular el numero de filas que tiene la consulta
     * @return numero de filas de la consulta
     */
    public int getNumFilas() {
    return this.numFilas; 
    }

    /**
     * Permite calcular el numero de columnas que tiene la consulta
     * @return numero de columnas de la consulta
     */
    public int getNumColumnas() {
    return this.numColumnas; 
    }

    /**
     * Permite obtener el objeto ResultSet asociado a la consulta a la base de datos
     * @return Objeto ResultSet de la consulta
     */
    public ResultSet getMiResultSet() {
    return this.miRst;
    }    

}