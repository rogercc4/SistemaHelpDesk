/***********************************************************************
 * Module:  PerfilUsuario.java
 * Author:  rcontreras
 * Purpose: Defines the Class PerfilUsuario
 ***********************************************************************/

package helpdesk.model;

import java.util.*;

/** Especifica el perfil del usuario, el cual le va a dar ciertas propiedades al usuario
 *
 * @pdOid f2cdef14-a187-46ef-b6f0-eaf5ecf0af9d */
public enum PerfilUsuario {
   /** Representa al perfil del jefe de informática.  */
   JEFE_INFORMATICA(1),
   /** Representa al perfil del jefe de soporte informático */
   JEFE_SOPORTE_INFORMATICO(2),
   /** Representa a las personas encargadas de atender las solicitudes. */
   SERVICE_DESK(3),
   /** Representa a un trabajador del área de informática */
   TRABAJADOR_INFORMATICA(4);

   /** Código del perfil
    *
    * @pdOid 3f5f6d97-9401-4f27-88ff-cd2a0a00b9a8 */
   private int codigo;

   /** @param codigo Valor del código de perfil
    * @pdOid 0ccc992e-bf33-4425-94cb-74cc321319a5 */
   PerfilUsuario(int codigo) {
      // TODO: implement
       this.codigo = codigo; 
   }

   /** Obtiene el código del perfil
    *
    * @pdOid fecea2ee-b88f-47f8-abf7-b9d283a5a929 */
   public int getCodigo() {
      return codigo;
   }

}