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
            ResultSet resultado = statement.executeQuery("select * from estadisticascovid.usuario");
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
    
    public int checkRegistro(String user){
        try {        
            Statement statement = db.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery("select * FROM EstadisticasCOVID.Usuario where usuario='"+user+"'");
            if(resultado.next()){
                return -1;                                                  // retorna -1 si el usuario ingresado existe en la base de datos
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
    
    public boolean verificarUsuario(String user, String contrasenia){
        try {        
            Statement statement = db.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery("call verificarUsuario('"+user+"','"+contrasenia+"')");
            while(resultado.next()){
                if(resultado.getBoolean(1)==false){
                    return false;                          // retorna false si no hay un usuario registrado con ese nombre y esa contraseña
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
