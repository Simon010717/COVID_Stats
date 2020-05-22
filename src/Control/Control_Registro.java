/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.UsuarioDAO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if(usuariosdao.checkRegistro(usuario)==-1){ 
            return -1;                                              // retorna -1 si el usuario ingresado existe en la base de datos
        }else if(usuario.length() < 7 || usuario.length() > 15){
            return -2;                                              // retorna -2 si el usuario está fuera de los limites (6,15]
        }else if(!verificarCorreo(correo)){
            return -3;                                          // retorna -3 si el correo está fuera de los limites (6,15] y si no cumple con el formato de un correo                                            
        }else if(contrasenia.length() < 8 || contrasenia.length() > 20){
            return -4;                                              // retorna -4 si la contraseña está fuera de los limites (8,20]
        }else if(!contrasenia.equals(confirmar)){
            return -5;                                              // retorna -5 si las contraseñas no coinciden
        }else if(usuario.equals("")||correo.equals("")|| contrasenia.equals("")||confirmar.equals("")){
            return -6;                                              // retorna -6 si algún campo está vacío
        }
        return 0;                                                   // retorna 0 si todo esta bien
    }
    
    public boolean verificarCorreo(String correo){
        if((correo.length() > 7 && correo.length() <= 30)){
            if(validarFormatoCorreo(correo)==-1){
                return false;
            }
            return true;
        }
        return false;
    }
    public int validarFormatoCorreo(String correo){
        Pattern pattern = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
        Matcher matcher = pattern.matcher(correo);
        
        if (!matcher.matches()) {
            return -1;
        }
        return 0;
    }
    
    public int registrarUsuario(String usuario, String correo, String contrasenia, String confirmar){
        if(checkRegistro(usuario, correo, contrasenia, confirmar)<0){
            return checkRegistro(usuario, correo, contrasenia, confirmar);
        }
        usuariosdao.registrarUsuario(usuario, correo, contrasenia, confirmar);
        return 0;
    }
    
}
