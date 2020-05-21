/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidad.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebasdeloco
 */
public class UsuarioDAO {
    
    DBConexion db;
    
    public UsuarioDAO(){
        try {
            db = new DBConexion();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {        
            Statement statement = db.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery("select * from estadusticascovid.usuario");
            while(resultado.next()){
                usuarios.add(new Usuario( resultado.getString(1),
                        resultado.getString(2), resultado.getString(3), resultado.getBoolean(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
    
    public int contarUsuarios() {
        int nUsuarios = 0;
        try {        
            Statement statement = db.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery("select count(*) FROM estadisticascovid.usuario where administrador = false");
            if(resultado.next()){
                nUsuarios = resultado.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nUsuarios;
    }
    
    public int checkRegistro(String user, String correo, String contrasenia, String confirmar){
        try {        
            Statement statement = db.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery("select * FROM EstadisticasCOVID.Usuario where usuario='"+user+"'");
            if(resultado.next()){
                return -1;                                                  // retorna -1 si el usuario ingresado existe en la base de datos
            }else if(!contrasenia.equals(confirmar)){
                return -2;                                                  // retorna -2 si las contraseñas no coinciden
            }else if(user.equals("")||correo.equals("")|| contrasenia.equals("")||confirmar.equals("")){
                return -3;                                                  // retorna -3 si algún campo está vacío
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;                                                           // retorna 0 si todo está bien
    }

    public void registrarUsuario(String user, String correo, String contrasenia, String confirmar){
        try {        
            Statement statement = db.getConexion().createStatement();
            if(contrasenia.equals(confirmar)){
                statement.executeUpdate("insert into EstadisticasCOVID.Usuario values('"+user+"','"+correo+"','"+contrasenia+"',false)");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
