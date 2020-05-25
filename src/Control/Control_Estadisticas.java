/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.DAO;
import java.util.Arrays;

/**
 *
 * @author Juan Andres Gonzalez
 */
public class Control_Estadisticas {
    private DAO dao;

    public Control_Estadisticas() {
        dao = new DAO();
    }
    
    public int contarUsuarios(){
        return dao.contarUsuarios();
    }
    
    public String[] crecimiento(){
        return dao.crecimiento();
    }
    
    public String[] masBuscados(){
        return dao.masBuscados();
    }
    
    public void ayerHoy(){
        dao.ayerHoy();
    }
    
    public void actualizarBD(){
        Actualizacion act = new Actualizacion();
        //act.descargarDatos();
        act.cargarBogota();
        act.cargarColombia();
    }
   
}
