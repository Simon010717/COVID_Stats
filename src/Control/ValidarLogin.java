/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.UsuarioDAO;
import Entidad.Usuario;

/**
 *
 * @author sebasdeloco
 */
public class ValidarLogin {
    private UsuarioDAO usuariosdao;

    public ValidarLogin() {
        usuariosdao = new UsuarioDAO();
    }
    
    public boolean validarUsuario(String user, String password){
        for(Usuario x : usuariosdao.getUsuarios()){
            if(user.equals(x.getUsuario()) && password.equals(x.getPassword())) return true;
        }
        return false;
    }
    
}
