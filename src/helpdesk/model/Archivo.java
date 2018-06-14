/***********************************************************************
 * Module:  Archivo.java
 * Author:  Roger
 * Purpose: Defines the Class Archivo
 ***********************************************************************/

package helpdesk.model;

import java.io.*;
import java.util.*;

/** Cuando una solicitud es registrada, el usuario puede agregar uno o mas archivos. Esta clase permite representar a estos archivos.
 *
 * @pdOid f4ddb732-86f7-4480-852f-5a5672dff1ac */
public class Archivo {
   /** Código del archivo
    *
    * @pdOid fad64178-7c75-4f44-8522-354d5fac2e96 */
   private int codArchivo;
   /** Nombre descriptivo del archivo que se va a publicar
    *
    * @pdOid f6077dc4-31fc-4185-989b-b6fb74d3cdf6 */
   private java.lang.String nombre;
   /** Nombre del fichero que se va almacenar en el disco duro.
    *
    * @pdOid a1a0150b-3748-44e9-bf3d-db0025769e9c */
   private String archivo;
   /** Fecha en la que el archivo fue registrado en el sistema
    *
    * @pdOid c2ecd372-475a-437e-b624-bb883f232033 */
   private java.util.Date fecha;
   /** Tipo de contenido del archivo adjuntado. Esto permite saber si el archivo es un PDF, WORD, Excel, etc.
    *
    * @pdOid 3edf5b96-bbd0-4d44-8a06-b9752ddca6a4 */
   private java.lang.String tipoContenido;
   /** Código de la solicitud asociada al archivo
    *
    * @pdOid d4192205-380e-481b-af6b-fa3474ce64b3 */
   private int codSolicitud;

   /** Retornar el codigo del archivo.
    *
    * @pdOid 27a9b0a6-b62f-4719-99d8-8e3e891ce2e8 */
   public int getCodArchivo() {
      return codArchivo;
   }

   /** Establece el valor de la propiedad codigo de archivo.
    *
    * @param newCodArchivo código que se desea que tenga el archivo
    * @pdOid a1cd320f-cdc6-4b49-baf8-e269dd6cc674 */
   public void setCodArchivo(int newCodArchivo) {
       if (newCodArchivo<=0)
           throw new IllegalArgumentException ("Ingrese un código correcto");

      codArchivo = newCodArchivo;
   }

   /** Retorna el nombre del archivo.
    *
    * @pdOid 53b346cb-8501-4fb6-a81f-d24fdc75814d */
   public java.lang.String getNombre() {
      return nombre;
   }

   /** Define el nombre que va a tener el archivo.
    *
    * @param newNombre Nombre que se desea dar al archivo
    * @pdOid 6c2b710f-31a5-4d22-8a55-aad7aa59fff2 */
   public void setNombre(java.lang.String newNombre) {
       if ( newNombre == null ||  newNombre.trim().length() <= 0 )
           throw new IllegalArgumentException("Ingrese un nombre correcto");

       nombre = newNombre.trim() ;

   }

   /** Obtiene el nombre del nombre del fichero que se almacenará en el disco.
    *
    * @pdOid 3a7679c9-d204-4885-a7f4-4e186e8b85a5 */
   public String getArchivo() {
      return archivo;
   }

   /** Define el nombre del fichero que se almacenrá en el disco duro.
    *
    * @param newArchivo Nombre que se desea que tenga el fichero que se guardará en el disco duro.
    * @pdOid 0388ac4c-4dad-486f-bf10-fe220c7fdbf3 */
   public void setArchivo(String newArchivo) {

       if ( newArchivo == null ||  newArchivo.trim().length() <= 0 )
           throw new IllegalArgumentException("Ingrese un nombre de archivo correcto");

       archivo = newArchivo.trim();

   }

   /** Fecha y hora en la que el archivo fue registrado
    *
    * @pdOid 7b068eeb-ba63-4fb2-b29a-12374b9959aa */
   public java.util.Date getFecha() {
      return fecha;
   }

   /** Permite establecer la fecha y hora en la que se registró el archivo.
    *
    * @param newFecha Valor de la fecha y hora
    * @pdOid 89072964-dd9f-4c4a-8088-3a8acffe0fdb */
   public void setFecha(java.util.Date newFecha) {
       if  ( newFecha == null )
           throw new IllegalArgumentException("Valor incorrecto para fecha");


      fecha = newFecha;
   }

   /** Permite obtener el tipo de contenido que tiene el fichero que se guardó en disco duro.
    *
    * @pdOid cd2d306c-94ee-4d28-8b83-97b039656e73 */
   public java.lang.String getTipoContenido() {
      return tipoContenido;
   }

   /** Establece el tipo de contenido que tiene el fichero que se guardó en disco duro.
    *
    * @param newTipoContenido Valor para el tipo de contenido
    * @pdOid 11efd0a5-2ba6-46c6-9213-d4a834a5b029 */
   public void setTipoContenido(java.lang.String newTipoContenido) {
       if ( newTipoContenido == null || newTipoContenido.trim().length() <= 0 )
            throw new IllegalArgumentException("Valor incorrecto para tipo de contenido");

      tipoContenido = newTipoContenido;

   }

   /** Permite obtener el código de la solicitud asociado al archivo.
    *
    * @pdOid a31f820a-61fe-4b5c-8f0b-ce31fb5ce000 */
   public int getCodSolicitud() {
      return codSolicitud;
    }

   /** Establece el código de la solicitud asociado al archivo.
    *
    * @param newCodSolicitud valor para el código de la solicitud asociada al archivo
    * @pdOid f4cd11ca-65e9-430c-b448-bf8708c7f586 */
   public void setCodSolicitud(int newCodSolicitud) {
      codSolicitud = newCodSolicitud;
   }

   /** Permite obtener el fichero como una representación de bytes. Este método es de utilidad al momento de que se desee obtener el archivo físico que ha sido almacenado en el disco duro.
    *
    * @param ruta Ruta en la que se encuentra almacenados los archivos.
    * @pdOid 4f3ca3ac-024c-4ed7-9fd4-e099925645fb */
   public java.io.InputStream getFichero(java.lang.String ruta) {
      // TODO: implement

       if ( ruta == null || ruta.trim().length() <= 0 )
           throw new IllegalArgumentException("Falta especificar la ruta");       

       if ( this.codArchivo <= 0 || this.fecha == null  || this.nombre == null )
             throw new IllegalStateException("Falta información del archivo");

       if ( this.archivo == null || this.tipoContenido == null )
            throw new IllegalStateException("Falta especificar el archivo que se desea leer");

        File fd=new File(ruta);

        if (fd.isDirectory()==false)
            throw new IllegalStateException("No es un directorio valido");
        
        File fil=new File (fd, this.archivo);
        if (fil.isFile()==false)
            throw new IllegalStateException("No es un archivo valido");
      
        FileInputStream in = null;
      
          try {
                if (fil.exists() == false  ) return null ;
                in=new FileInputStream(fil);
          }
          catch( IOException e ) {
                return null ;
          }

        return in ;

   }

   /** Sirve para obtener un objeto Archivo que ya ha sido registrado en la base de datos.
    *
    * @param codigo Código del archivo
    * @pdOid a153b1a0-5b97-45cf-9f07-285df5005df6 */
   public static Archivo getArchivoBD(int codigo) {
      // TODO: implement
      String cadSql = "SELECT archivo_id, archivo, nombre, solicitud_id, tipo_contenido, fecha " +
                        "FROM helpdesk.archivo where archivo_id = " + codigo ;

      helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

      if ( consulta.getNumFilas() <= 0 ) return null ;

      Object[][] resultado = consulta.getResultados() ;

      Archivo miArchivo = new Archivo();

        for ( Object[] fila : resultado ) {
        miArchivo.setArchivo( fila[1].toString().trim() );
        miArchivo.setCodArchivo(Integer.parseInt(fila[0].toString().trim()));
        miArchivo.setCodSolicitud(Integer.parseInt(fila[3].toString().trim()));
        miArchivo.setFecha( (Date)fila[5] );
        miArchivo.setNombre( fila[2].toString().trim() );
        miArchivo.setTipoContenido( fila[4].toString().trim() );
        }

      return miArchivo ;

   }

}