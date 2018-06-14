/***********************************************************************
 * Module:  HelpDeskException.java
 * Author:  rcontreras
 * Purpose: Defines the Class HelpDeskException
 ***********************************************************************/

package helpdesk.model;

import java.util.*;

/** Esta clase sirve para manejar nuestras propias excepciones.
 * 
 * @pdOid 0d877a76-bc14-4fe6-9b8d-6e814d01d43c */
public class HelpDeskException extends Exception {

    public HelpDeskException(String mensaje) {
    super(mensaje);
    }

}