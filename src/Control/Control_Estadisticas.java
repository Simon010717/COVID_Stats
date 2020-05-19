/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.UsuarioDAO;

/**
 *
 * @author Juan Andres Gonzalez
 */
public class Control_Estadisticas {
    private UsuarioDAO usuariosdao;

    public Control_Estadisticas() {
        usuariosdao = new UsuarioDAO();
    }
    
    public int contarUsuarios(){
        return usuariosdao.contarUsuarios();
    }
    
    
}
