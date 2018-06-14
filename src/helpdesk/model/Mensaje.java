/***********************************************************************
 * Module:  Mensaje.java
 * Author:  rcontreras
 * Purpose: Defines the Class Mensaje
 ***********************************************************************/

package helpdesk.model;

import java.util.*;

/** Mensajes que son enviados en el momento que se esta atendiendo una solicitud.
 *
 * Cuando una solicitud esta en proceso de atención, la persona que esta
 * atendiendo la solicitud, puede tener la necesidad de comunicarse con la
 * persona que generó la solicitud. Por tanto el sistema ofrece una funcionalidad
 * para lograr esta comunicación, en la cual esta clase va almacenar todos
 * estos mensajes que se envien.
 *
 *
 * @pdOid 68e7353d-6207-4f67-85de-0e4db45cad8f */
public class Mensaje {
   /** Código del mensaje enviado
    *
    * @pdOid a21407f6-9b11-4a6a-9e8b-97b2af47cf35 */
   private int codigo;
   /** Usuario que envia el mensaje
    *
    * @pdOid 47a9d45f-3fe9-4ffa-bd3e-18002a9ab71d */
   private String usuarioOrigen;
   /** Usuario para el cual va detinado el mensaje
    *
    * @pdOid 1edb3a34-b0b9-4619-b2ff-24d1d78bc506 */
   private String usuarioDestino;
   /** Asunto del mensaje que se envia
    *
    * @pdOid e3988743-36bd-43ea-a2a2-e84a3f686065 */
   private String asunto;
   /** Descripcion detallada del mensaje enviado
    *
    * @pdOid fc753093-b2b7-44d9-88b6-0d6044aaade5 */
   private String descripcion;
   /** Fecha y hora al momento en que se envio el mensaje
    *
    * @pdOid ebc49b0a-d596-4d6d-a7dc-627eb76f261f */
   private java.util.Date fecRegistro;
   /** Nombre del archivo que se adjunta. Este es el nombre que los usuarios del
    * sistema van a poder apreciar.
    *
    * @pdOid d7c481ad-f0f1-4427-abb5-6ae1dac9e6f0 */
   private String nombreAdjunto;
   /** Tipo de contenido del archivo adjunto. Permite conocer si el archivo adjuntado
    * es un PDF, Word, Excel, etc.
    *
    * @pdOid 5a69cca7-2f66-46fb-aaef-11387b8c6096 */
   private String tipoContenidoAdjunto;
   /** Nombre con el cual se graba el archivo adjunto en el disco.
    *
    * @pdOid b394786f-5397-4a3a-b8b5-05ecf8d42610 */
   private String archivoAdjunto;
   /** Código del tramite asociado al mensaje.
    *
    * @pdOid 765e3aea-0d99-4a6e-ae3a-f186dcf02459 */
   private int codTramite;

   /** @pdOid 2aef5635-5b88-4303-9544-45145978657a */
   public int getCodigo() {
      return codigo;
   }

   /** @param newCodigo
    * @pdOid e6f6446b-1aad-4806-affb-d058b04ac915 */
   public void setCodigo(int newCodigo) {

      if ( newCodigo <= 0 )
           throw new IllegalArgumentException("El codigo debe tener un valor mayor a cero");

      codigo = newCodigo;
      
   }

   /** @pdOid d3f40647-d760-462d-a181-9722467f0512 */
   public String getUsuarioOrigen() {
      return usuarioOrigen;
   }

   /** @param newUsuarioOrigen
    * @pdOid a864d574-57ad-4d6a-b706-a47217f04f69 */
   public void setUsuarioOrigen(String newUsuarioOrigen) {

       if ( newUsuarioOrigen == null || newUsuarioOrigen.trim().length() <= 0 )
               throw new IllegalArgumentException("Falta definir el usuario de origen");

      usuarioOrigen = newUsuarioOrigen;
      
   }

   /** @pdOid 74b040d9-1888-4e73-a250-953f9dfca909 */
   public String getUsuarioDestino() {
      return usuarioDestino;
   }

   /** @param newUsuarioDestino
    * @pdOid 5fa84653-cf44-45f7-861f-aade223fbf11 */
   public void setUsuarioDestino(String newUsuarioDestino) {

       if ( newUsuarioDestino == null || newUsuarioDestino.trim().length() <= 0 )
               throw new IllegalArgumentException("Falta definir el usuario de destino");

      usuarioDestino = newUsuarioDestino;
   }

   /** @pdOid 18bde803-6c0e-415a-ba37-8b415b62fe0e */
   public String getAsunto() {
      return asunto;
   }

   /** @param newAsunto
    * @pdOid e557e439-fb2c-4993-8879-fec339b93c5c */
   public void setAsunto(String newAsunto) {

       if ( newAsunto == null || newAsunto.trim().length() <= 0 )
               throw new IllegalArgumentException("Falta definir el asunto del mensaje");

       asunto = newAsunto;
   }

   /** @pdOid c3595b60-99bc-4012-9012-c80212a113e4 */
   public String getDescripcion() {
      return descripcion;
   }

   /** @param newDescripcion
    * @pdOid 5e2589b1-da5a-42a1-9ea7-db0b78f93b22 */
   public void setDescripcion(String newDescripcion) {

       if ( newDescripcion == null || newDescripcion.trim().length() <= 0 )
               throw new IllegalArgumentException("Falta especificar una descripcion para el mensaje");

      descripcion = newDescripcion;
   }

   /** @pdOid 2cd9c1c9-9aa5-47ed-ad6e-b9d5a84ed535 */
   public java.util.Date getFecRegistro() {
      return fecRegistro;
   }

   /** @param newFecRegistro
    * @pdOid fb5d2f76-dc09-4baa-bee8-7acb32dcd53c */
   public void setFecRegistro(java.util.Date newFecRegistro) {

       if ( newFecRegistro == null  )
               throw new IllegalArgumentException("Falta especificar la fecha de registro del mensaje");
       
      fecRegistro = newFecRegistro;
   }

   /** @pdOid 314092a4-fd62-4993-91af-2c09bbb296b9 */
   public String getNombreAdjunto() {
      return nombreAdjunto;
   }

   /** @param newNombreAdjunto
    * @pdOid bb20f548-e511-41d8-8f9d-c6f160285ac1 */
   public void setNombreAdjunto(String newNombreAdjunto) {

       if ( newNombreAdjunto == null || newNombreAdjunto.trim().length() <= 0 )
           newNombreAdjunto = null; 

      nombreAdjunto = newNombreAdjunto;
   }

   /** @pdOid 301df724-fd1f-4ae2-b7fc-8b353ba3bbed */
   public String getTipoContenidoAdjunto() {
      return tipoContenidoAdjunto;
   }

   /** @param newTipoContenidoAdjunto
    * @pdOid d901083f-0f30-49ae-85c8-928d4e986568 */
   public void setTipoContenidoAdjunto(String newTipoContenidoAdjunto) {

       if ( newTipoContenidoAdjunto == null || newTipoContenidoAdjunto.trim().length() <= 0 )
           newTipoContenidoAdjunto = null;

      tipoContenidoAdjunto = newTipoContenidoAdjunto;
   }

   /** @pdOid 94a6280c-0359-44e9-a306-727e7855a730 */
   public String getArchivoAdjunto() {
      return archivoAdjunto;
   }

   /** @param newArchivoAdjunto
    * @pdOid a7ab4e2c-19ea-4ce3-93cf-4d98c81dbe32 */
   public void setArchivoAdjunto(String newArchivoAdjunto) {

       if ( newArchivoAdjunto == null || newArchivoAdjunto.trim().length() <= 0 )
           newArchivoAdjunto = null;

      archivoAdjunto = newArchivoAdjunto;
   }

   /** @pdOid 22d7d823-b118-497b-95ab-d894a2d63ed4 */
   public int getCodTramite() {
      return codTramite;
   }

   /** @param newCodTramite
    * @pdOid 4562473f-0da7-41ee-85dc-167d2bff05b6 */
   public void setCodTramite(int newCodTramite) {

       if ( newCodTramite <= 0 )
           throw new IllegalArgumentException("El codigo del tramite debe tener un valor mayor a cero");

       codTramite = newCodTramite;

   }

   /** Obtiene un mensaje que haya sido registrado en la base de datos
    *
    * @param codMensaje Código del mensaje que se desea consultar en la base de datos.
    * @pdOid c34b72b6-039a-4e91-b829-e3b3238f6b97 */
   public static Mensaje getMensajeBD(int codMensaje) {
      // TODO: implement
      String cadSql = "SELECT mensaje_id, descripcion, fec_registro, usuario_origen, usuario_destino, " +
                      "tipo_contenido_adjunto, nombre_adjunto, archivo_adjunto, tramite_id, " +
                      "asunto FROM helpdesk.mensaje where mensaje_id=" + codMensaje ;

      helpdesk.model.data.ConsultaData miConsulta = new helpdesk.model.data.ConsultaData(cadSql);

      Mensaje resultado = null ;      

      if ( miConsulta.getNumFilas() <= 0 )
          return resultado ; 

      resultado = new Mensaje(); 
      Object[][] rstFilas = miConsulta.getResultados();
      
      resultado.setArchivoAdjunto( (rstFilas[0][7] == null) ? null : rstFilas[0][7].toString().trim() );
      resultado.setAsunto(rstFilas[0][9].toString());
      resultado.setCodTramite( Integer.parseInt(rstFilas[0][8].toString()) );
      resultado.setCodigo(codMensaje);
      resultado.setDescripcion(rstFilas[0][1].toString());
      resultado.setFecRegistro((Date)rstFilas[0][2]);
      resultado.setNombreAdjunto( (rstFilas[0][6] == null) ? null : rstFilas[0][6].toString().trim() );
      resultado.setTipoContenidoAdjunto( (rstFilas[0][5] == null) ? null : rstFilas[0][5].toString().trim() );
      resultado.setUsuarioDestino(rstFilas[0][4].toString());
      resultado.setUsuarioOrigen(rstFilas[0][3].toString());

      return resultado;
   }

   /** Obtiene el tramite que se encuentra asociado al mensaje
    *
    * @pdOid 146c6dec-be24-4f01-a549-0092132649c3 */
   public Tramite getTramite() {
      // TODO: implement
       
      return Tramite.getTramiteBD(this.getCodTramite());
      
   }

   /** Obtiene el trabajador que origino el mensaje
    *
    * @pdOid a8bdd676-4ebd-4223-87e1-6ace2a13b96b */
   public Trabajador getTrabajadorOrigen() {
      // TODO: implement
      return Trabajador.getTrabajadorBD( this.getUsuarioOrigen() );
      
   }

   /** Obtiene el trabajador al cual se destino el mensaje
    *
    * @pdOid f370b549-723e-4281-96e7-586efdf870d0 */
   public Trabajador getTrabajadorDestino() {
      // TODO: implement
      return Trabajador.getTrabajadorBD( this.getUsuarioDestino() );
   }

   /** Retorna la fecha de registro en el formato dd/mm/YYYY hh:mm:ss AM/PM
    *
    * @pdOid e92d9a8e-a816-407d-8e60-25e1a193b077 */
   public java.lang.String getFecRegistroFormateada() {
      // TODO: implement
      
      if ( this.getFecRegistro() == null )
          return null; 

      java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy h:m:s a");
      String resultado = sdf.format(this.getFecRegistro());

      return resultado ;

   }

   /** Retorna el fichero que ha sido adjuntado al momento de enviar el mensaje
    *
    * @param ruta Ruta en donde se encuentra almacenado los archivos
    * @pdOid 5f3265a0-6b4d-4a37-8907-d8a47fa0c286 */
   public java.io.InputStream getFichero(String ruta) {
   // TODO: implement

       if ( ruta == null || ruta.trim().length() <= 0 )
           throw new IllegalArgumentException("Falta especificar la ruta");

       if ( this.getCodigo() <= 0 )
           throw new IllegalStateException("Falta especificar el codigo del mensaje");

       if ( this.getArchivoAdjunto() == null || this.getArchivoAdjunto().trim().length() <= 0 || 
            this.getTipoContenidoAdjunto() == null || this.getTipoContenidoAdjunto().trim().length() <= 0 ) 
             return null;

        java.io.File fd = new java.io.File(ruta);

        if ( fd.isDirectory()==false )  
            throw new IllegalStateException("No es un directorio valido");

        java.io.File fil = new java.io.File ( fd, this.getArchivoAdjunto() );

        if ( fil.isFile() == false )
            throw new IllegalStateException("No es un archivo valido");

        java.io.FileInputStream in = null;

          try {
                if ( fil.exists() == false  ) return null ;

                in = new java.io.FileInputStream(fil);
          }
          catch( java.io.IOException e ) {
                return null ;
          }

        return in ;
      
   }

}