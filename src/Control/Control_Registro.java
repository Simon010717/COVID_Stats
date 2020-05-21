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
    
    public int checkRegistro(String usuario, String correo, String contrasenia, String confirmar){
        if(usuariosdao.checkRegistro(usuario, correo, contrasenia, confirmar)==-1){ // retorna -1 si el usuario ingresado existe en la base de datos
            return -1;
        }else if(usuariosdao.checkRegistro(usuario, correo, contrasenia, confirmar)==-2){// retorna -2 si las contraseñas no coinciden
            return -2;
        }else if(usuariosdao.checkRegistro(usuario, correo, contrasenia, confirmar)==-3){// retorna -3 si algún campo está vacío
            return -3;
        }
        return 0;                                                                        // retorna 0 si todo esta bien
    }
    
    public void registrarUsuario(String usuario, String correo, String contrasenia, String confirmar){
        usuariosdao.registrarUsuario(usuario, correo, contrasenia, confirmar);
    }
    
}
