/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.model.pattern;

import helpdesk.model.Area;
import helpdesk.model.Cargo;
import java.util.Map;

/**
 *
 * @author Roger
 */
public class CargoFlyweightFactory {
    
    private static Map<Integer, Cargo> mapCargosEnCache = new java.util.HashMap<Integer, Cargo>();
    
    private CargoFlyweightFactory() {
        
    }
    
    public static Cargo getCargo(int codigo) {
        Cargo cargoBuscado = mapCargosEnCache.get(codigo);
        
            if (cargoBuscado == null) {
                cargoBuscado = findCargoEnBD(codigo);
                mapCargosEnCache.put(codigo, cargoBuscado);
            }
        
        return cargoBuscado;
    }
    
    
    private static Cargo findCargoEnBD(int codigo) {

      if ( codigo <= 0) return null;

        String cadSql = "select cargo_id, nombre, area_id, jefe_area "
                        + "from cargo where cargo_id = " + codigo ;

        helpdesk.model.data.ConsultaData consulta = new helpdesk.model.data.ConsultaData(cadSql);

           if ( consulta.getNumFilas() <= 0 ) return null ;

        Cargo miCargo = new Cargo();

        Object[][] resultados = consulta.getResultados() ;


        miCargo.setArea(new Area( Integer.parseInt(resultados[0][2].toString()) ));
        miCargo.setCodCargo(codigo);
        miCargo.setJefe(Boolean.parseBoolean(resultados[0][3].toString()));
        miCargo.setNombre(resultados[0][1].toString());

        return miCargo;
    }
    
}