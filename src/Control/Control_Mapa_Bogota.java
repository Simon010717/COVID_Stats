/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.DAO;

/**
 *
 * @author Juan Andres Gonzalez
 */
public class Control_Mapa_Bogota {
    private DAO dao;

    public Control_Mapa_Bogota() {
        dao = new DAO();
    }
    
    public int[] CasosBogota(){
        int[] casos = dao.casosMapaBogota();
        
        return casos;
    }
    
    public static void main(String[] args) {
        Control_Mapa_Bogota control = new Control_Mapa_Bogota();
        
        for (int i = 0; i < control.CasosBogota().length; i++) {
            System.out.println(control.CasosBogota()[i]);
        }
    }
}
