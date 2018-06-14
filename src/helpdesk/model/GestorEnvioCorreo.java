/***********************************************************************
 * Module:  GestorEnvioCorreo.java
 * Author:  rcontreras
 * Purpose: Defines the Class GestorEnvioCorreo
 ***********************************************************************/

package helpdesk.model;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Permite la gestión del envio de mensajes de correo.
 *
 * @pdOid 0cc740ac-d90b-4fb8-82d6-2769952ef49b */
public class GestorEnvioCorreo {
   /** Sesion con la que cual se puede inciar una conversación con el servidor SMTP
    *
    * @pdOid bb4bad01-73dc-4d35-ae59-6a9522933a79 */
   private static javax.mail.Session mailSesion;
   /** Direccion e-mail desde donde se desea enviar el correo.
    *
    * @pdOid 5fb9caa5-6fa9-4a81-af9c-0e7c6c7e00a9 */
   private String mailOrigen;
   /** Nombre de la persona que va a enviar el mensaje de correo.
    *
    * @pdOid 98180803-e32b-415b-b3e0-3ebda1f8b50b */
   private String nombreOrigen;
   /** Direcciones de correo de las personas que van a recibir los mensajes de correo
    *
    * @pdOid 9d889078-7b05-418a-be98-bf9b2aae28a2 */
   private ArrayList<String> destinosTO;
   /** Direcciones de correo de las personas que van a recibir una copia del mensaje de correo
    *
    * @pdOid 1f18fdb7-0bcf-4c5f-a241-db6262776171 */
   private ArrayList<String> destinosCC;
   /** Mensaje que se desea enviar por correo.
    *
    * @pdOid 15bee337-d438-4a6c-9652-fce616c7aefe */
   private String mensaje;
   /** Asunto del mensaje de correo que se desea enviar
    *
    * @pdOid 27f8374e-5d25-4251-a5bc-3b00f9650101 */
   private String asunto;

   /** Permite obtener el mensaje HTML con la información de la solicitud que se desea enviar por correo.
    *
    * @param miSolicitud Es la solcitud a la que hace referencia el mensaje que se desea enviar
    * @pdOid 31be04b2-07b7-4ae2-947a-015f7843ce5e */
   public StringBuilder getMensajeHTML(Solicitud miSolicitud) {
            // TODO: implement
       StringBuilder sb = null ;
       
        try {
            java.io.InputStream fis = GestorEnvioCorreo.class.getResourceAsStream("/recursos/formatoCorreo.msg");

            if ( fis == null )
                throw new IllegalStateException("No se ha encontrado el formato HTML para el envio del mensaje de correo");

            java.io.BufferedReader br = null;
            java.io.InputStreamReader isr = new java.io.InputStreamReader(fis);
            br = new java.io.BufferedReader(isr);
            String lineaTexto = null;
            sb = new StringBuilder();
            
            while ((lineaTexto = br.readLine()) != null) sb.append(lineaTexto);

            System.out.println(sb.indexOf("###USUARIO_SOLICITUD###"));

            String cadBusqueda = "###USUARIO_SOLICITUD###" ; 

            sb.replace(sb.indexOf(cadBusqueda), 
                       sb.indexOf(cadBusqueda) + cadBusqueda.length(),  
                       miSolicitud.getTrabajador().getNombre() + " " +     
                       miSolicitud.getTrabajador().getApellido()    
                       );

            cadBusqueda = "###TIPO_SOLICITUD###" ;

            sb.replace(sb.indexOf(cadBusqueda),
                       sb.indexOf(cadBusqueda) + cadBusqueda.length(),
                       miSolicitud.getTipoSolicitud().getNombre()
                       );

            cadBusqueda = "###FECHA_SOLICITUD###" ;

            sb.replace(sb.indexOf(cadBusqueda),
                       sb.indexOf(cadBusqueda) + cadBusqueda.length(),
                       miSolicitud.getFecha().toString()
                       );

            cadBusqueda = "###AREA_SOLICITUD###" ;

            sb.replace(sb.indexOf(cadBusqueda),
                       sb.indexOf(cadBusqueda) + cadBusqueda.length(),
                       miSolicitud.getCargo().getArea().getNombre()
                       );

            cadBusqueda = "###CARGO_SOLICITUD###";

            sb.replace(sb.indexOf(cadBusqueda),
                       sb.indexOf(cadBusqueda) + cadBusqueda.length(),
                       miSolicitud.getCargo().getNombre()
                       );

            cadBusqueda = "###ASUNTO_SOLICITUD###";

            sb.replace(sb.indexOf(cadBusqueda),
                       sb.indexOf(cadBusqueda) + cadBusqueda.length(),
                       miSolicitud.getAsunto()
                       );
            
        } catch (IOException ex) {
            Logger.getLogger(GestorEnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
            return null ; 
        }
        
   return sb;
   
   }
   
   /** Permite configurar la sesion con el servidor de correo
    *
    * @param parametros
    * @pdOid d6d01118-aa12-4cb5-b574-50ecdca4604d */
   public static void setSesionCorreo(java.util.Map parametros) {
      // TODO: implement
      Properties props = System.getProperties();      
      props.putAll(parametros);
      mailSesion = Session.getDefaultInstance(props, null);      
   }

   /** Obtiene la direccion e-mail desde donde se desea enviar el correo.
    *
    * @pdOid 964abf8f-8781-4e48-aec8-1114d1e22bc4 */
   public String getMailOrigen() {
      return mailOrigen;
   }

   /** Establece el valor de  la direccion e-mail desde donde se desea enviar el correo.
    *
    * @param newMailOrigen
    * @pdOid 847ba134-8e63-40a8-a86c-457a07c0b0c2 */
   public void setMailOrigen(String newMailOrigen) {

    String regex = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
    java.util.regex.Matcher matcher = pattern.matcher(newMailOrigen);
    
    if (!matcher.matches())
        throw new IllegalArgumentException ("Ingrese un E-mail valido");

      mailOrigen = newMailOrigen.trim();
   }

   /** Obtiene el nombre de la persona que va a enviar el mensaje de correo.
    *
    * @pdOid 45e2bdc8-0413-41db-bc6d-61a48a9f472f */
   public String getNombreOrigen() {
      return nombreOrigen;
   }

   /** Define el valor del nombre de la persona que va a enviar el mensaje de correo.
    *
    * @param newNombreOrigen
    * @pdOid dcc505c6-8e8f-4933-9f0b-7fb716a1593e */
   public void setNombreOrigen(String newNombreOrigen) {
      nombreOrigen = newNombreOrigen;
   }

   /** Obtiene las direcciones de correo de las personas que van a recibir los mensajes de correo
    *
    * @pdOid 223dfa29-8ab2-4332-9922-0e98088c8cae */
   public ArrayList<String> getDestinosTO() {
      return destinosTO;
   }

   /** Establece el valor  las direcciones de correo de las personas que van a recibir los mensajes de correo
    *
    * @param newDestinosTO
    * @pdOid aa53c691-6b22-40e9-a392-c3e1ed1ac17f */
   public void setDestinosTO(ArrayList<String> newDestinosTO) {
      destinosTO = newDestinosTO;
   }

   /** Obtiene las direcciones de correo de las personas que van a recibir una copia del mensaje de correo
    *
    * @pdOid 8635e38d-2e33-49c3-8031-8e2bb6b458b9 */
   public ArrayList<String> getDestinosCC() {
      return destinosCC;
   }

   /** Establece el valor de las direcciones de correo de las personas que van a recibir una copia del mensaje de correo
    *
    * @param newDestinosCC
    * @pdOid 1799d06f-7cba-42dd-b586-0f0350866ae9 */
   public void setDestinosCC(ArrayList<String> newDestinosCC) {
      destinosCC = newDestinosCC;
   }

   /** Obtiene el mensaje que se desea enviar por correo.
    *
    * @pdOid d2c510c0-a85e-4f4e-a93f-149836fbf277 */
   public String getMensaje() {
      return mensaje;
   }

   /** Establece el valor del mensaje que se desea enviar por correo.
    *
    * @param newMensaje
    * @pdOid 0e07861a-5138-4e60-8d92-8381df69aa23 */
   public void setMensaje(String newMensaje) {
      mensaje = newMensaje;
   }

   /** Obtiene el asunto del mensaje de correo que se desea enviar
    *
    * @pdOid a13cb7ea-6630-4c57-9fc9-414814bbf62d */
   public String getAsunto() {
      return asunto;
   }

   /** Permite definir el asunto del mensaje de correo que se desea enviar
    *
    * @param newAsunto
    * @pdOid fdc5dc20-6a6e-4f4e-94ec-825354eef874 */
   public void setAsunto(String newAsunto) {
      asunto = newAsunto;
   }

    /** Permite enviar un mensaje de correo.
    *
    * @pdOid 37e676b1-7015-4d0a-af60-ce29c75713c6 */
   public void enviarMensaje() {
      // TODO: implement

       if ( mailSesion == null)
            throw new IllegalStateException("No se ha iniciado la sesion con el servidor de correo");

      MimeMessage msg = new MimeMessage( GestorEnvioCorreo.mailSesion );
      try {

          if ( this.mailOrigen == null )
              throw new IllegalStateException("Falta especificar el correo del emisario");

          if ( this.nombreOrigen == null || this.nombreOrigen.trim().length() <= 0 )
            msg.setFrom( new InternetAddress( this.mailOrigen ) );

      msg.setFrom( new InternetAddress( this.mailOrigen, this.nombreOrigen ) );

            if ( this.destinosTO == null || this.destinosTO.size() <= 0 )
                throw new IllegalStateException("Falta especificar la dirección del destinatario");

      Iterator<String> correosDest = this.destinosTO.iterator();

            while ( correosDest.hasNext() )
                msg.addRecipient( Message.RecipientType.TO, new InternetAddress( correosDest.next() ) );

            if ( this.destinosCC != null && this.destinosCC.size() > 0 ) {
            Iterator<String> correosCC = this.destinosCC.iterator();

                while (correosCC.hasNext()) {
                msg.addRecipient( Message.RecipientType.CC, new InternetAddress( correosCC.next() ) );
                }
            
            }

            if ( this.asunto == null || this.asunto.trim().length() <= 0 ) {
            msg.setSubject("Sin asunto");
            }
            else {
            msg.setSubject(this.asunto);
            }

      msg.setText(this.mensaje);
      Transport.send(msg);
      }
      catch ( Exception ex ) {
      Logger.getLogger(GestorEnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
      }

   }

    /** Envia un mensaje de correo, de acuerdo a la información que se tiene del  último trámite registrado en la solicitud.
    *
    * @param miSolicitud Solicitud involucrada en el trámite
    * @pdOid 97f7d48e-1a5c-4806-9b24-90f68af275c8 */
   public void enviarMsgDespuesTramite(Solicitud miSolicitud) {
            // TODO: implement
      
    try {
      
       if ( miSolicitud == null ) throw new IllegalStateException("Falta indicar la solicitud");

      if ( mailSesion == null)
            throw new IllegalStateException("No se ha iniciado la sesion con el servidor de correo");

      MimeMessage msg = new MimeMessage( GestorEnvioCorreo.mailSesion );

      Tramite lastTramite = miSolicitud.getUltimoTramite();

      if ( lastTramite == null )
            throw new IllegalStateException("No se ha registrado tramites en esta solicitud");       

       if ( this.getMailOrigen() == null ) {
       this.setMailOrigen(lastTramite.getTrabajadorOrigen().getCorreo());
       }

       if ( this.getNombreOrigen() == null ) {
       this.setNombreOrigen(lastTramite.getTrabajadorOrigen().getNombre() + " " +
                                        lastTramite.getTrabajadorOrigen().getApellido());
       }

       if ( this.getNombreOrigen() != null ) {
       msg.setFrom( new InternetAddress( this.getMailOrigen(), this.getNombreOrigen() ) );
       }
       else {
       msg.setFrom( new InternetAddress( this.getMailOrigen() ) );
       }

       if ( this.getDestinosTO() == null || this.getDestinosTO().size() <= 0 ) {

           if ( lastTramite.getTrabajadorDestino() == null ) {

              if ( lastTramite.getCargoDestino() != null ) {
              ArrayList<Trabajador> misTrab = lastTramite.getCargoDestino().getTrabajadores();

                    if ( misTrab != null && misTrab.size() > 0 ) {
                    Iterator<Trabajador> iTrabDest = misTrab.iterator();
                    java.util.ArrayList<String> misDestinosTO = new java.util.ArrayList<String>();
                    
                        while ( iTrabDest.hasNext() ) {                        
                        misDestinosTO.add(iTrabDest.next().getCorreo());                        
                        }
                    
                    this.setDestinosTO(misDestinosTO);
                    }
                    else {
                    return ; 
                    }
              
              }
              else {
              //Codigo para tratar cuando no se tenga informacion del destino
              return ; 
              }
           
           }
           else {
           java.util.ArrayList<String> misDestinosTO = new java.util.ArrayList<String>();
           misDestinosTO.add(lastTramite.getTrabajadorDestino().getCorreo());
           this.setDestinosTO(misDestinosTO);
           }

       }

       Iterator<String> correosDest = this.getDestinosTO().iterator();

            while( correosDest.hasNext() )
                msg.addRecipient( Message.RecipientType.TO, new InternetAddress( correosDest.next() ) );

       if ( this.getDestinosCC() != null && this.getDestinosCC().size() > 0 ) {
           
             Iterator<String> correosCC = this.destinosCC.iterator();

                while (correosCC.hasNext()) {
                msg.addRecipient( Message.RecipientType.CC, new InternetAddress( correosCC.next() ) );
                }
             
       }

       if ( this.getAsunto() == null ) {
       this.setAsunto(lastTramite.getTipoTramite().getNombre());
       }

       msg.setSubject(this.getAsunto());
            
       if ( this.getMensaje() == null ) {
       this.setMensaje( this.getMensajeHTML(miSolicitud).toString() );
       }
       
            msg.setContent(this.getMensaje(), "text/html");
            Transport.send(msg);

        } catch (MessagingException ex) {
            Logger.getLogger(GestorEnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestorEnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }

   }


}