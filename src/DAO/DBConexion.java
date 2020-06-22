/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebasdeloco
 */
public class DBConexion {
    static String bd = "EstadisticasCOVID";
    static String login = "root";
    static String password = "Password1234!";
    static String url = "jdbc:mysql://35.199.107.44/"+bd;
    
    static Connection conexion = null;
    static DBConexion instance = null;
    
    public DBConexion(){}

    /**
     * metodo que retorna la conexion a la bd
     * @return 
     */
    public Connection getConexion(){
        return conexion;
    }
    
    public DBConexion getInstance(){
        if(instance == null){
            instance = new DBConexion();
            try{
                Class.forName("com.mysql.jdbc.Driver");
                instance.conexion = DriverManager.getConnection(url, login, password);
                System.out.println(instance.conexion == null ? "Conexión fallida" : "Conexión Exitosa");
            }catch(SQLException e){
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBConexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }

    /**
     * metodo que desconecta la base de datos
     */
    public void desconectar(){
        conexion = null;
    }
    
    
}
