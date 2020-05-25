/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidad.CasoBogota;
import Entidad.CasoColombia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simon
 */
public class DAO {
    DBConexion db;
    
    public DAO(){
        try {
            db = new DBConexion();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public int contarUsuarios() {
        int nUsuarios = 0;
        try {        
            Statement statement = db.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery("select count(*) FROM EstadisticasCOVID.Usuario where administrador = false");
            if(resultado.next()){
                nUsuarios = resultado.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public String[] crecimiento(){
        String [] subs = new String[12];
        int i = 0;
         try {        
            Statement statementCol = db.getConexion().createStatement();
            ResultSet resultadoCol = statementCol.executeQuery("call mayorCrecimiento ('Colombia')");
            while(resultadoCol.next()){
                subs[i] = resultadoCol.getString(1);
                //System.out.println(subs[i]);
                i++;
                
            }
            Statement statementBog = db.getConexion().createStatement();
            ResultSet resultadoBog = statementBog.executeQuery("call mayorCrecimiento ('Bogotá')");
            while(resultadoBog.next()){
                subs[i] = resultadoBog.getString(1);
                //System.out.println(subs[i]);
                i++;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subs;
    }
    
    public String[] masBuscados(){
        String [] subs = new String[6];
        int i = 0;
         try {        
            Statement statementCol = db.getConexion().createStatement();
            ResultSet resultadoCol = statementCol.executeQuery("call masBuscados ('Colombia')");
            while(resultadoCol.next()){
                subs[i] = resultadoCol.getString(1);
                //System.out.println(subs[i]);
                i++;
                
            }
            Statement statementBog = db.getConexion().createStatement();
            ResultSet resultadoBog = statementBog.executeQuery("call masBuscados ('Bogotá')");
            while(resultadoBog.next()){
                subs[i] = resultadoBog.getString(1);
                //System.out.println(subs[i]);
                i++;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subs;
    }
    
    
    public void ayerHoy(){
        int i = 0;
         try {        
            Statement statement = db.getConexion().createStatement();
            statement.executeUpdate("call hoyAyer()");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ingresarCaso(CasoColombia caso){
        try {
            PreparedStatement statement = db.getConexion().prepareStatement("insert into INS values("
                    +"'"+caso.id_de_caso
                    +"',"+ (caso.fecha_de_notificacion != null ? "'"+caso.fecha_de_notificacion+"'" : "NULL")
                    +",'"+caso.codigo_divipola
                    +"','"+caso.ciudad_de_ubicacion
                    +"','"+caso.departamento
                    +"','"+caso.atencionn
                    +"','"+caso.edad
                    +"','"+caso.sexo
                    +"','"+caso.tipo
                    +"','"+caso.estado
                    +"','"+caso.pais_de_procedencia
                    +"',"+(caso.fis != null ? "'"+caso.fis+"'" : "NULL")
                    +","+(caso.fecha_de_muerte != null ? "'"+caso.fecha_de_muerte+"'" : "NULL")
                    +",'"+caso.fecha_diagnostico
                    +"',"+(caso.fecha_recuperado != null ? "'"+caso.fecha_recuperado+"'" : "NULL")
                    +",'"+caso.fecha_reporte_web+"')");
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ingresarCaso(CasoBogota caso){
        try {
            PreparedStatement statement = db.getConexion().prepareStatement("insert into Bogota values(NULL,"
                    +"'"+caso.fecha_diagnostico
                    +"','"+caso.ciudad
                    +"','"+caso.localidad
                    +"','"+caso.edad
                    +"','"+caso.sexo
                    +"','"+caso.tipo
                    +"','"+caso.ubicacion
                    +"','"+caso.estado+"')");
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
