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
public class Control_Registro {
    private UsuarioDAO usuariosdao;
    
    public Control_Registro() {
        usuariosdao = new UsuarioDAO();
    }
    
    public int registrarUsuario(String usuario, String correo, String contrasenia, String confirmar){
        if(!contrasenia.equals(confirmar)){
            return -1;
        }
        usuariosdao.registrarUsuario(usuario, correo, contrasenia, confirmar);
        return 0;
    }
    
}
