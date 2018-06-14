/***********************************************************************
 * Module:  TramiteArchivo.java
 * Author:  Roger
 * Purpose: Defines the Class TramiteArchivo
 ***********************************************************************/

package helpdesk.model;

import java.util.*;

/** Representa al archivo que se puede adjuntar cuando se esta dando atención a una solicitud. Por ejemplo si se da atención a una solicitud indicando que se rechaza la misma, sería adecuado adjuntar un archivo en donde se de mas información de los motivos.
 *
 * @pdOid d32bbe47-5153-4359-af1d-2eeddf02f25e */
public class TramiteArchivo {
   /** Código del tramite de una solicitud
    *
    * @pdOid 5813aae7-1839-4a2c-b78a-b3ba4889ef3a */
   private int codTramite;
   /** Nombre descriptivo del archivo
    *
    * @pdOid 10631d2c-6d7b-4b67-9baf-8221aa3d03e4 */
   private String nombre;
   /** Nombre del archivo que se almacena en el disco
    *
    * @pdOid 4a2e7875-98f2-4811-bd9c-920f88ecf44e */
   private String archivo;
   /** Tipo de contenido del archivo almacenado en disco
    *
    * @pdOid 564e7a96-26f2-4ed7-8a89-4efa9baf68b4 */
   private String tipoContenido;

   /** Obtiene el código del tramite de una solicitud
    *
    * @pdOid e23386aa-c5a2-4433-bed0-143e1fcecf69 */
   public int getCodTramite() {
      return codTramite;
   }

   /** Establece  el código del tramite de una solicitud
    *
    * @param newCodTramite Código del trámite
    * @pdOid b5a320fc-71e1-4f99-be87-275d6bacfbb0 */
   public void setCodTramite(int newCodTramite) {
       if (newCodTramite<=0)
           throw new IllegalArgumentException("Establezca un trámite valido");

      codTramite = newCodTramite;
   }

   /** Obtiene el nombre descriptivo del archivo
    *
    * @pdOid 6fef9e18-1b20-4286-829a-9d6485492239 */
   public String getNombre() {
      return nombre;
   }

   /** Establece el nombre descriptivo del archivo
    *
    * @param newNombre valor del nombre descriptivo del archivo
    * @pdOid e9f05d45-9896-4dcb-8e66-b2547fb40e2e */
   public void setNombre(String newNombre) {
       if (newNombre.trim().length()<=0 || newNombre==null)
           throw new IllegalArgumentException("Establezca un nombre descriptivo para el archivo");

      nombre = newNombre.trim();
   }

   /** Permite obtener el nombre del archivo que se almacena en el disco
    *
    * @pdOid 8187db7b-f0db-4e18-890f-47b71a95977a */
   public String getArchivo() {
      return archivo;
   }

   /** Establece el nombre del archivo que se almacena en el disco
    *
    * @param newArchivo Valor del nombre del archivo almacenado en el disco
    * @pdOid 3300b923-a594-44e1-98d7-f47a3bd595ed */
   public void setArchivo(String newArchivo) {
       if (newArchivo==null || newArchivo.trim().length()<=0)
           throw new IllegalArgumentException("Estableza un nombre para archivo almacenado en disco");
      archivo = newArchivo;
   }

   /** Permite obtener  el tipo de contenido del archivo almacenado en disco.
    *
    * @pdOid c25ffd87-e403-401b-8ee4-3275aa7d45fe */
   public String getTipoContenido() {
      return tipoContenido;
   }

   /** Permite establecer  el tipo de contenido del archivo almacenado en disco.
    *
    * @param newTipoContenido Valor del tipo de contenido del archivo almacenado en disco.
    * @pdOid c62739a9-3aec-462a-9a63-96523bca015c */
   public void setTipoContenido(String newTipoContenido) {
       if (newTipoContenido==null || newTipoContenido.trim().length()<=0)
           throw new IllegalArgumentException("Establezca el tipo de archivo almacenado en el disco");

      tipoContenido = newTipoContenido.trim();
   }

   /** Obtiene el fichero almacenado en el disco duro
    *
    * @param ruta Ubicación del disco donde se almacena el fichero
    * @pdOid 6be768e4-9c83-4f49-b37f-dc5f7c636b58 */
   public java.io.InputStream getFichero(String ruta) {
      // TODO: implement

       if ( ruta == null || ruta.trim().length() <= 0 )
          throw new IllegalArgumentException("Falta especificar la ruta");

       if ( this.getCodTramite() <= 0 || this.getTipoContenido() == null  || this.getArchivo() == null )
             throw new IllegalStateException("Falta información del archivo");

        java.io.File fd=new java.io.File(ruta);

        if (fd.isDirectory() == false)
            throw new IllegalStateException("No es un directorio valido");

        java.io.File fil=new java.io.File (fd, this.getArchivo());
        if (fil.isFile()==false)
            throw new IllegalStateException("No es un archivo valido");

        java.io.FileInputStream in = null;

          try {
                if (fil.exists() == false  ) return null ;
                in=new java.io.FileInputStream(fil);
          }
          catch( java.io.IOException e ) {
                return null ;
          }

        return in ;
        
   }

}
