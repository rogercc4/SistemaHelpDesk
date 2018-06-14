/***********************************************************************
 * Module:  Tarea.java
 * Author:  rcontreras
 * Purpose: Defines the Class Tarea
 ***********************************************************************/

package helpdesk.model;

import helpdesk.model.data.ConsultaData;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Tareas que se registran para dar atencion a una solicitud
 *
 * @pdOid b4405a58-0260-4ff5-9925-4854cc68b72c */
public class Tarea {
   /** Código de la tarea
    *
    * @pdOid 0f4f5289-56aa-45a6-8954-ef9d56db84fb */
   private int codigo;
   /** Area en la cual laboraba el trabajador al momento de registrar la solicitud
    *
    * @pdOid 0bc85f4d-38c5-4806-8e8c-c57b5dfcce64 */
   private String area;
   /** Descripcion de la tarea.
    *
    * @pdOid dcc149d2-75de-4b8a-b21d-6ccf39c8de9a */
   private String descripcion;
   /** Cargo que tenía el trabajador en el momento de registrar la tarea
    *
    * @pdOid affef4b5-c561-4951-8466-df6e5218fce8 */
   private String cargo;
   /** Nombre del trabajador.
    *
    * @pdOid b0146125-959a-4e3d-8384-4e6af340b280 */
   private String nombreTrabajador;
   /** Fecha en la que se dió inicio a la tarea.
    *
    * @pdOid 447b782b-59d4-4bdb-8dd7-b57008c445a1 */
   private Date fechaInicio;
   /** Fecha en la que se termino de realizar la tarea
    *
    * @pdOid 95e8ede9-608c-4693-bd8f-d862cf8232ea */
   private Date fechaFin;
   /** Código de la solicitud a la cual pertenece la tarea
    *
    * @pdOid 74a26fbf-2a19-4388-9746-a635a52ff67d */
   private int codTramite;
   /** Nombre del usuario que registro la tarea
    *
    * @pdOid ad1b6a85-0f40-4dc1-908f-d15fc6265601 */
   private String usuario;

   /** @pdOid a9cbe0db-1d68-4b94-b448-ddcadcd8c952 */
   public int getCodigo() {
      return codigo;
   }

   /** @param newCodigo
    * @pdOid a7250537-d319-4644-9633-fb324883d2e9 */
   public void setCodigo(int newCodigo) {
       if (newCodigo <= 0)
           throw new IllegalArgumentException ("Ingresar un código  de tarea valido");

      codigo = newCodigo;
   }

   /** @pdOid 7d3b9878-7a63-4af4-a1b3-8790447451f9 */
   public String getArea() {
      return area;
   }

   /** @param newArea
    * @pdOid d0a12451-eeb9-4302-96ff-7d8f1c72b33d */
   public void setArea(String newArea) {
        if (newArea == null || newArea.trim().length() <= 0)
           throw new IllegalArgumentException ("Ingresar nombre de Area");

      area = newArea;
   }

   /** @pdOid 70f5d014-7bc7-49d1-a78b-7b372a97bb19 */
   public String getDescripcion() {
      return descripcion;
   }

   /** @param newDescripcion
    * @pdOid 9cd31c26-ebdd-4bfb-be8b-0c780b135504 */
   public void setDescripcion(String newDescripcion) {
        if (newDescripcion == null || newDescripcion.trim().length() <= 0)
           throw new IllegalArgumentException ("Debe ingresar una descripción para la tarea");

      descripcion = newDescripcion;
   }

   /** @pdOid 69fdd597-0b0d-4271-9bf9-2e57fc3a262b */
   public String getCargo() {
      return cargo;
   }

   /** @param newCargo
    * @pdOid 1279ebd4-ebda-4c3f-931a-95bc4c713486 */
   public void setCargo(String newCargo) {
        if (newCargo == null || newCargo.trim().length() <= 0)
           throw new IllegalArgumentException ("Debe seleccionar un cargo valido para el trabajador");
      cargo = newCargo;
   }

   /** @pdOid 0877c5f9-b23f-4ae6-8386-f50eab65f6ee */
   public String getNombreTrabajador() {
      return nombreTrabajador;
   }

   /** @param newNombreTrabajador
    * @pdOid 9a62ac98-da55-41b3-924d-ee4346f72ef5 */
   public void setNombreTrabajador(String newNombreTrabajador) {
        if (newNombreTrabajador == null || newNombreTrabajador.trim().length() <= 0)
           throw new IllegalArgumentException ("El trabajador seleccionado no es valido");
      nombreTrabajador = newNombreTrabajador;
   }

   /** @pdOid bcef7d5b-1a13-4917-8103-bce5f17817e4 */
   public Date getFechaInicio() {
      return fechaInicio;
   }

   /** @param newFechaInicio
    * @pdOid 50727636-e6c8-410c-9aec-9c46fa6b6ca7 */
   public void setFechaInicio(Date newFechaInicio) {
       if (newFechaInicio == null)
            throw new IllegalArgumentException("Ingrese una fecha de inicio valida");

      fechaInicio = newFechaInicio;
   }

   /** @pdOid 258dd9dc-1c97-4ae2-adda-d481bc23abf5 */
   public Date getFechaFin() {
      return fechaFin;
   }

   /** @param newFechaFin
    * @pdOid 7b09e5ab-f00a-4aa2-94fe-4279ddf0937c */
   public void setFechaFin(Date newFechaFin) {
       if (newFechaFin == null)
            throw new IllegalArgumentException("Ingrese una fecha de fin valida");
       
      fechaFin = newFechaFin;
      
   }

   /** @pdOid ffdddba8-89eb-4cd8-80b2-c5af78dd33bb */
   public int getCodTramite() {
      return codTramite;
   }

   /** @param newCodTramite
    * @pdOid 16f01002-f0f3-42a6-966e-20560c302f17 */
   public void setCodTramite(int newCodTramite) {
       if (newCodTramite <= 0)
           throw new IllegalArgumentException("Debe seleccionar una solicitud");
       
      codTramite = newCodTramite;
   }

   /** @pdOid fa68d1e1-3bbc-4a5a-85e7-018d769715bc */
   public String getUsuario() {
      return usuario;
   }

   /** @param newUsuario
    * @pdOid c9cdd315-bff0-41fb-bb9f-3d20e768bb43 */
   public void setUsuario(String newUsuario) {
        if (newUsuario == null || newUsuario.trim().length() <= 0)
           throw new IllegalArgumentException ("El usuario seleccionado no es valido");

      usuario = newUsuario;
   }

   /** Obtiene la solicitud que se encuentra asociada a esta tarea
    *
    * @pdOid 239d338c-9c06-4a28-a750-a4e19e6a2231 */
   public Tramite getTramite() {
      // TODO: implement
      String cadSQL="select tramite_id " +
                    "from helpdesk.tarea where tarea_id=" + this.codigo;

      ConsultaData consulta = new ConsultaData(cadSQL);

      if (consulta.getNumFilas() <= 0) return null;

      Tramite miTramite = Tramite.getTramiteBD(Integer.parseInt(consulta.getResultados()[0][0].toString()));

      return miTramite;
   }

   /** Obtiene una tarea que ha sido registrada en la base de datos.
    *
    * @param codTarea Código de la tarea que se desea consultar a la base de datos.
    * @pdOid 3ec76f8d-1190-408b-8824-d1717fd3fdff */
   public static Tarea getTareaBD(int codTarea) {
            // TODO: implement
            if (codTarea <= 0)
                return null;
            
            String CadSQL = "select tarea_id, descripcion, fecha_inicio, fecha_fin, nombre, cargo, " +
                            "area, tramite_id, usuario from helpdesk.tarea where tarea_id = " + codTarea ;
            
            ConsultaData consulta = new ConsultaData(CadSQL);
            
            if (consulta.getNumFilas() <= 0)
                return null;

            Tarea miTarea = new Tarea();
            miTarea.setArea(consulta.getResultados()[0][6].toString());
            miTarea.setCargo(consulta.getResultados()[0][5].toString());
            miTarea.setCodTramite( Integer.parseInt(consulta.getResultados()[0][7].toString()) );;
            miTarea.setCodigo(Integer.parseInt(consulta.getResultados()[0][0].toString()));
            miTarea.setDescripcion(consulta.getResultados()[0][1].toString());

            Date fechaF = (Date) consulta.getResultados()[0][3];
            Date fechaI = (Date) consulta.getResultados()[0][2];
            miTarea.setFechaFin(fechaF);
            miTarea.setFechaInicio(fechaI);

            miTarea.setNombreTrabajador(consulta.getResultados()[0][4].toString());
            miTarea.setUsuario(consulta.getResultados()[0][8].toString());

            return miTarea;

    }
}
