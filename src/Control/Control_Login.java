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
public class Control_Login {
    private DAO dao;

    public Control_Login() {
        this.dao = new DAO();
    }
    
    public int verificarUsuario(String usuario, String contrasenia){
        if(usuario.equals("") || contrasenia.equals("")){
            return -3;
        }else if(dao.verificarUsuario(usuario, contrasenia)==false){
            return -1;
        }
        return 0;
    } 
    
}
