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
public class Control_Login {
    private UsuarioDAO usuariodao;

    public Control_Login() {
        this.usuariodao = new UsuarioDAO();
    }
    
    public int verificarUsuario(String usuario, String contrasenia){
        if(usuario.equals("") || contrasenia.equals("")){
            return -3;
        }else if(usuariodao.verificarUsuario(usuario, contrasenia)==false){
            return -1;
        }
        return 0;
    } 
    
}
