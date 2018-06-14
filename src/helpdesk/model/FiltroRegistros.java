/***********************************************************************
 * Module:  FiltroRegistros.java
 * Author:  Roger
 * Purpose: Defines the Class FiltroRegistros
 ***********************************************************************/

package helpdesk.model;

import java.util.*;

/** Representa un filtro utilizado al momento de realizar consultas de objetos registrado en la base de datos.
 *
 * Este filtro permite diferenciar aquellos objetos que se encuentran marcados como visibles o no en la base de datos.
 *
 * @pdOid 73daad89-8eb8-426b-88c0-4fa9ec83d044 */
public enum FiltroRegistros {
   /** Un filtro para indicar que sólo queremos aquellos registros de los objetos en la base de datos que son visibles. */
   ACTIVADO,
   /** Un filtro para indicar que sólo queremos aquellos registros de los objetos en la base de datos que no son visibles. */
   DESACTIVADO,
   /** Un filtro para indicar que queremos todos los registros de los objetos en la base de datos, sin importar si son o no visibles. */
   TODOS;

}