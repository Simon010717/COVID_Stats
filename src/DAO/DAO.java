/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    
    
    public void registrarAdmin(String user, String correo, String contrasenia, String confirmar){
        try {        
            Statement statement = db.getConexion().createStatement();
            if(contrasenia.equals(confirmar)){
                statement.executeUpdate("insert into EstadisticasCOVID.Usuario values('"+user+"','"+correo+"','"+contrasenia+"',true)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int verificarUsuario(String user, String contrasenia){
        try {        
            Statement statement = db.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery("call verificarUsuario('"+user+"','"+contrasenia+"')");
            if(resultado.next()){
                if(resultado.getBoolean(1)==true){
                    return 1; //administrador encontrado
                }
                return 0; //usuario encontrado
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1; //usuario ni administrado encontrado
    }
    
    public boolean verificarAdmin(String user, String contrasenia){
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
    
    public int[][] casosMapaBogota(){
        int[][] casos = new int[21][4];
        int i = 0;
        try {
            Statement statement = db.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery("call casosHoy ('Bogotá')");
            while(resultado.next()){
                casos [i][0] = resultado.getInt(2);
                casos [i][1] = resultado.getInt(3);
                casos [i][2] = resultado.getInt(4);
                casos [i][3] = resultado.getInt(5);
                i++;
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return casos;
    }
    
    public int[][] casosMapaColombia(){
        int[][] casos = new int[37][4];
        int i = 0;
        try {
            Statement statementCol = db.getConexion().createStatement();
            ResultSet resultadoCol = statementCol.executeQuery("call casosHoy ('Colombia')");
            while(resultadoCol.next()){
                casos [i][0] = resultadoCol.getInt(2);
                casos [i][1] = resultadoCol.getInt(3);
                casos [i][2] = resultadoCol.getInt(4);
                casos [i][3] = resultadoCol.getInt(5);
                i++;
            }           
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return casos;
    }
    
        public String[] subdivisionesCol(){
        String[] casos = new String[37];
        int i = 0;
        try {
            Statement statement = db.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery("select idSubdivision FROM EstadisticasCOVID.Subdivision where idMapa = 'Colombia'");
            while(resultado.next()){
                casos [i] = resultado.getString(1);
                i++;
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return casos;
    }
    
     public String[] subdivisionesBog(){
        String[] casos = new String[21];
        int i = 0;
        try {
            Statement statement = db.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery("select idSubdivision FROM EstadisticasCOVID.Subdivision where idMapa = 'Bogotá'");
            while(resultado.next()){
                casos [i] = resultado.getString(1);
                i++;
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return casos;
    }
    
    public int[][] graficasTemporales(String sub){
        int[][] casos = new int[80][4];
        int i = 0;
        try {
            Statement statementCol = db.getConexion().createStatement();
            ResultSet resultadoCol = statementCol.executeQuery("call graficasTemporales('" + sub + "')");
            while(resultadoCol.next()){
                casos [i][0] = resultadoCol.getInt(1);
                casos [i][1] = resultadoCol.getInt(2);
                casos [i][2] = resultadoCol.getInt(3);
                casos [i][3] = resultadoCol.getInt(4);
                i++;
            }           
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return casos;
    }
    
    public static void main(String[] args) {
        DAO dao = new DAO();
        
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(dao.casosMapaBogota()[i][j] + " ");
            }System.out.println("");
        }System.out.println("");
    }
    
}
