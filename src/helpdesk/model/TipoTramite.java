/***********************************************************************
 * Module:  TipoTramite.java
 * Author:  Roger
 * Purpose: Defines the Class TipoTramite
 ***********************************************************************/

package helpdesk.model;

import java.util.*;

/** Representa los trámites por los cuales puede pasar una solicitud. Por ejemplo: Envio para visto bueno, envío para atención, derivar solicitud, rechazar solicitud.
 *
 * @pdOid afbcacf5-f17b-4715-8e10-09913cbc8f4b */
public class TipoTramite {
   /** Código del tramite
    *
    * @pdOid da72bc9d-8897-4dfb-b55a-e92253124833 */
   private int codigo;
   /** Nombre del tipo de trámite
    *
    * @pdOid bb4cb450-211b-4d7f-9a58-79a9397ee9e2 */
   private String nombre;   

   /** Permite obtener el código del tramite
    *
    * @pdOid 55594453-063d-4252-8963-b1f46baa96a6 */
   public int getCodigo() {
      return codigo;
   }

   /** Permite establecer el código del tramite
    *
    * @param newCodigo valor del código del tramite
    * @pdOid 24a94e13-0b5e-460a-85f5-ee0a739f9a27 */
   public void setCodigo(int newCodigo) {
       if (newCodigo<=0)
           throw new IllegalArgumentException("Ingrese un código de tramite valido");
      codigo = newCodigo;
   }

   /** @pdOid 7cbb0d1e-3238-4daf-b6d7-58b5a61dee14 */
   public String getNombre() {
      return nombre;
   }

   /** Permite establecer el nombre del tipo de trámite
    *
    * @param newNombre Valor del nombre del tipo de trámite
    * @pdOid 65254c99-67c8-423c-bb13-681af48efba6 */
   public void setNombre(String newNombre) {
        if ( newNombre == null || newNombre.trim().length() <= 0  )
           throw new IllegalArgumentException("Ingrese un nombre del tipo de tramite valido");

      nombre = newNombre.trim();
   }

   /** Permite obtener un objeto TipoTramite en base a la información registrada en la base de datos.
    *
    * @param codTramite Código usado para buscar un tipo de trámite en la base de datos
    * @pdOid 88f07bf3-f707-49e3-a3d2-99c71e37af78 */
   public static TipoTramite getTipoTramiteBD(int codTramite) {
      // TODO: implement
      String cadSql = "SELECT tipo_tramite_id, nombre " +
                      "  FROM helpdesk.tipo_tramite " +
                      " where tipo_tramite_id = " + codTramite ;

      helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

      if ( consulta.getNumFilas() <= 0 ) return null ;

      Object[][] resultado = consulta.getResultados();

      TipoTramite miTipoTram = new TipoTramite();

      for ( Object[] fila : resultado ) {
      miTipoTram.setCodigo(Integer.parseInt(fila[0].toString().trim()));
      miTipoTram.setNombre(fila[1].toString().trim());
      }

      return miTipoTram;

   }

    /** Permite obtener un objeto TipoTramite de la base de datos, en base a un tipo de trámite
    *
    * @param valorTipo Valor del tipo de trámite
    * @pdOid 44b87dbb-27f1-44b3-8a0f-3a8309fa6581 */
   public static TipoTramite getTipoTramiteBD(ValorTipoTramite valorTipo) {
      // TODO: implement
      return TipoTramite.getTipoTramiteBD(valorTipo.getCodigo());
   }

}