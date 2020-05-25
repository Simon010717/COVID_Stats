/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.DAO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Juan Andres Gonzalez
 */
public class Control_Registro {
    private DAO dao;
    
    public Control_Registro() {
        dao = new DAO();
    }
    
   
    public int checkRegistro(String usuario, String correo, String contrasenia, String confirmar){
        if(dao.checkRegistro(usuario)==-1){ 
            return -1;                                              // retorna -1 si el usuario ingresado existe en la base de datos
        }else if(usuario.length() < 7 || usuario.length() > 15){
            return -2;                                              // retorna -2 si el usuario está fuera de los limites (6,15]
        }else if(correo.length() < 8 || correo.length() > 30){
            return -3;                                              // retorna -3 si el correo está fuera de los limites (8,30] 
        }else if(!validarFormatoCorreo(correo)){
            return -4;                                              // retorna -4 si el correo no cumple con el formato de un correo                                            
        }else if(contrasenia.length() < 8 || contrasenia.length() > 20){
            return -5;                                              // retorna -5 si la contraseña está fuera de los limites (8,20]
        }else if(!validarFormatoContrasenia(contrasenia)){
            return -6;                                              // retorna -6 si las contraseñas no cumple con el formato de contraseña
        }else if(confirmar.equals("")){
            return -8;                                              // retorna -8 si algún campo está vacío
        }else if(!contrasenia.equals(confirmar)){
            return -7;                                              // retorna -7 si las contraseñas no coinciden
        }
        return 0;                                                   // retorna 0 si todo esta bien
    }
    
   
    public boolean validarFormatoCorreo(String correo){
        Pattern pattern = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
        Matcher matcher = pattern.matcher(correo);
        
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
    
    public int registrarUsuario(String usuario, String correo, String contrasenia, String confirmar){
        if(checkRegistro(usuario, correo, contrasenia, confirmar)<0){
            return checkRegistro(usuario, correo, contrasenia, confirmar);
        }
        dao.registrarUsuario(usuario, correo, contrasenia, confirmar);
        return 0;
    }
    
    public boolean validarFormatoContrasenia(String contrasenia){
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{9,20}$");
        Matcher matcher = pattern.matcher(contrasenia);
        
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
    
    
}
