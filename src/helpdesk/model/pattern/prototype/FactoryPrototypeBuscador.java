/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.model.pattern.prototype;

import helpdesk.model.Buscador;
import helpdesk.model.Cargo;
import helpdesk.model.Trabajador;

/**
 *
 * @author Roger
 */
public class FactoryPrototypeBuscador {
    
    private  java.util.Map<TipoDeBusqueda, BuscadorTotalPorEstado> buscadoresMap = 
                        new java.util.HashMap<TipoDeBusqueda, BuscadorTotalPorEstado>();
    
    public FactoryPrototypeBuscador( Trabajador miTrab, Cargo miCargo ) {
        
      Buscador miBusqSolicReg = new Buscador();
      miBusqSolicReg.getSolicRegistradas(miTrab, miCargo);
      BuscadorTotalPorEstado miBusqueda = new BuscadorTotalPorEstado(TipoDeBusqueda.SOLICITUDES_REGISTRADA);
      miBusqueda.setNumRegistros(miBusqSolicReg.getNumRegistros());
      buscadoresMap.put(TipoDeBusqueda.SOLICITUDES_REGISTRADA, miBusqueda);

      Buscador miBusqReqProcAtencion = new Buscador();
      miBusqReqProcAtencion.getMisReqProcesoAtencion(miTrab, miCargo);
      miBusqueda = new BuscadorTotalPorEstado(TipoDeBusqueda.EN_PROCESO_ATENCION_REQUERIMIENTOS);
      miBusqueda.setNumRegistros(miBusqReqProcAtencion.getNumRegistros());
      buscadoresMap.put(TipoDeBusqueda.EN_PROCESO_ATENCION_REQUERIMIENTOS, miBusqueda);

      Buscador miBusqReqAsignados = new Buscador();
      miBusqReqAsignados.getMisReqAsignados(miTrab, miCargo);
      miBusqueda = new BuscadorTotalPorEstado(TipoDeBusqueda.ASIGNADAS);
      miBusqueda.setNumRegistros(miBusqReqAsignados.getNumRegistros());
      buscadoresMap.put(TipoDeBusqueda.ASIGNADAS, miBusqueda);

      Buscador miBusqReqDarConformidad = new Buscador();
      miBusqReqDarConformidad.getMisReqParaConformidad(miTrab, miCargo);
      miBusqueda = new BuscadorTotalPorEstado(TipoDeBusqueda.PARA_DAR_CONFORMIDAD);
      miBusqueda.setNumRegistros(miBusqReqDarConformidad.getNumRegistros());
      buscadoresMap.put(TipoDeBusqueda.PARA_DAR_CONFORMIDAD, miBusqueda);

      Buscador miBusqReqDevueltos = new Buscador();
      miBusqReqDevueltos.getMisReqDevueltos(miTrab, miCargo);
      miBusqueda = new BuscadorTotalPorEstado(TipoDeBusqueda.DEVUELTAS);
      miBusqueda.setNumRegistros(miBusqReqDevueltos.getNumRegistros());
      buscadoresMap.put(TipoDeBusqueda.DEVUELTAS, miBusqueda);

      Buscador miBusqReqRechazados = new Buscador();
      miBusqReqRechazados.getMisReqRechazados(miTrab, miCargo);
      miBusqueda = new BuscadorTotalPorEstado(TipoDeBusqueda.RECHAZADAS);
      miBusqueda.setNumRegistros(miBusqReqRechazados.getNumRegistros());
      buscadoresMap.put(TipoDeBusqueda.RECHAZADAS, miBusqueda);

      Buscador miBusqPendDarVistoBueno = new Buscador();
      miBusqPendDarVistoBueno.getSolicPendVistoBueno(miTrab, miCargo);
      miBusqueda = new BuscadorTotalPorEstado(TipoDeBusqueda.DAR_VISTO_BUENO);
      miBusqueda.setNumRegistros(miBusqPendDarVistoBueno.getNumRegistros());
      buscadoresMap.put(TipoDeBusqueda.DAR_VISTO_BUENO, miBusqueda);

      Buscador miBusqPendParaAtender = new Buscador();
      miBusqPendParaAtender.getSolicPendParaAtender(miTrab, miCargo);
      miBusqueda = new BuscadorTotalPorEstado(TipoDeBusqueda.PARA_ATENDER);
      miBusqueda.setNumRegistros(miBusqPendParaAtender.getNumRegistros());
      buscadoresMap.put(TipoDeBusqueda.PARA_ATENDER, miBusqueda);

      Buscador miBusqPendDerivadas = new Buscador();
      miBusqPendDerivadas.getSolicPendDerivadas(miTrab, miCargo);
      miBusqueda = new BuscadorTotalPorEstado(TipoDeBusqueda.DERIVADAS);
      miBusqueda.setNumRegistros(miBusqPendDerivadas.getNumRegistros());
      buscadoresMap.put(TipoDeBusqueda.DERIVADAS, miBusqueda);

      Buscador miBusqPendProcAtencion = new Buscador();
      miBusqPendProcAtencion.getSolicPendProcAtencion(miTrab, miCargo);
      miBusqueda = new BuscadorTotalPorEstado(TipoDeBusqueda.EN_PROCESO_ATENCION_PENDIENTES);
      miBusqueda.setNumRegistros(miBusqPendProcAtencion.getNumRegistros());
      buscadoresMap.put(TipoDeBusqueda.EN_PROCESO_ATENCION_PENDIENTES, miBusqueda);

      Buscador miBusqPendEnvConformidad = new Buscador();
      miBusqPendEnvConformidad.getSolicPendEnvConformidad(miTrab, miCargo);
      miBusqueda = new BuscadorTotalPorEstado(TipoDeBusqueda.ENVIAR_PARA_CONFORMIDAD);
      miBusqueda.setNumRegistros(miBusqPendEnvConformidad.getNumRegistros());
      buscadoresMap.put(TipoDeBusqueda.ENVIAR_PARA_CONFORMIDAD, miBusqueda);
    }
    
    public BuscadorTotalPorEstado  getPrototypeBuscador( TipoDeBusqueda tipoBusqueda ) {
        BuscadorTotalPorEstado busquedaTotal = buscadoresMap.get(tipoBusqueda);
        return (BuscadorTotalPorEstado)busquedaTotal.clone();
    }
}
