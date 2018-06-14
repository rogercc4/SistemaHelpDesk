/***********************************************************************
 * Module:  FormatoTramite.java
 * Author:  Roger
 * Purpose: Defines the Class FormatoTramite
 ***********************************************************************/

package helpdesk.model;

import java.util.*;

/** Formatos que pueden ser usados para dar una mayor información al momento de generar una solicitud.
 *
 * Estos formatos serán archivos que se adjunten al momento de generar una solicitud, y estos se encuentran clasificados de acuerdo al tipo de solicitud.
 *
 * @pdOid 2d0131b4-fd48-4831-800f-35fc39d00eb0 */
public class FormatoTramite {
   /** Código del formato.
    *
    * @pdOid 53d22d98-bdf3-449d-a224-3710f223f561 */
   private String codFormato;
   /** Nombre del formato.
    *
    * @pdOid e566bca9-6542-4493-b139-bc3ae858d1a8 */
   private String nombre;
   /** Es el tipo de contenido del archivo almacenado.
    *
    * @pdOid 65cfe5a6-b0a3-4c53-87a2-4d124277496c */
   private String extension;

   /** Obtiene el código del formato.
    *
    * @pdOid d1aab194-aa3e-4489-ad34-3f8e0d28907b */
   public String getCodFormato() {
      return codFormato;
   }

   /** Establece el código del formato.
    *
    * @param newCodFormato Valor del código del formato
    * @pdOid 7f750ccd-4c51-46dc-beb4-acbf45a54976 */
   public void setCodFormato(String newCodFormato) {
       if ((newCodFormato==null) || (newCodFormato.trim().length()<=0))
            throw new IllegalArgumentException ("Ingrese un código de formato valido");

      codFormato = newCodFormato;
   }

   /** Obtiene el nombre del formato.
    *
    * @pdOid ff3d7d3e-e709-49c7-a9b3-85734b8fdd84 */
   public String getNombre() {
      return nombre;
   }

   /** Establece el nombre del formato.
    *
    * @param newNombre Valor para el nombre del formato.
    * @pdOid 942662bb-ebd0-4ae9-aabd-0bb188c4de0d */
   public void setNombre(String newNombre) {    
       if ((newNombre==null) || (newNombre.trim().length()<=0))
           throw new IllegalArgumentException ("Ingrese un nombre de formato valido");

      nombre = newNombre.trim() ;
   }

   /** Permite obtener el tipo de contenido del archivo almacenado.
    *
    * @pdOid aede424d-efca-4a53-9fce-66ddaa9e1b96 */
   public String getExtension() {
      return extension;
   }

   /** Permite establecer el tipo de contenido del archivo almacenado.
    *
    * @param newExtension
    * @pdOid c542876f-9a4e-4053-88b1-adec903f803c */
   public void setExtension(String newExtension) {
    if ((newExtension==null) || (newExtension.trim().length()<=0))
           throw new IllegalArgumentException ("Ingrese un tipo de contenido de archivo valido");

      extension = newExtension.trim() ;
   }

   /** Permite obtener un formato de trámite de los que se encuentran almacenados en la base de datos.
    *
    * @param codigo Código del formato de trámite
    * @pdOid d1207534-1bcb-43fd-92b7-0268acb3ce9f */
   public static FormatoTramite getFormatoTramiteBD(String codigo) {
      // TODO: implement
      FormatoTramite miFormato = null;

      codigo = codigo.trim();
      if ( codigo == null ) return null ;
      if ( codigo.trim().length() <= 0 ) return null ;

      String cadSql = "select formato_id, nombre, extension "
                        + "from helpdesk.formato_tramite "
                        + "where formato_id ='" + codigo + "'" ;

      Object[][] resultado = new helpdesk.model.data.ConsultaData(cadSql).getResultados();

        if ( resultado != null ) {

            for ( Object[] fila : resultado ) {
            miFormato = new FormatoTramite();
            miFormato.setCodFormato(fila[0].toString());
            miFormato.setExtension(fila[2].toString());
            miFormato.setNombre(fila[1].toString());
            }

        }

      return miFormato;
      
   }

   /** Permite obtener el archivo almacenado del objeto que representa la instancia actual del formato de trámite
    *
    * @pdOid 919c6cb3-6535-4026-8318-d3e2f135e897 */
   public java.io.InputStream getArchivo() {
      // TODO: implement  

   if ( this.codFormato == null ) throw new IllegalStateException("Falta definir el código del formato");

   java.io.InputStream is = null ;

   String cadSql = "select archivo from helpdesk.formato_tramite "
                    + "where formato_id ='" + this.codFormato + "'" ;
   
   java.sql.ResultSet miRst = new helpdesk.model.data.ConsultaData(cadSql).getMiResultSet();

        if ( miRst == null ) return null ;

        try {

            while (miRst.next()) {
            is = miRst.getBinaryStream(1);
            }

        } catch (java.sql.SQLException ex) {
          return null;
        }

   return is ;
   
   }

}
