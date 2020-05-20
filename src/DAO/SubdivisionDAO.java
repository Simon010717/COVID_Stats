/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

//import Entidad.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Andres Gonzalez
 */
public class SubdivisionDAO {
    DBConexion db;
    
    public SubdivisionDAO(){
        try {
            db = new DBConexion();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public String[] crecimiento(){
        String [] subs = new String[6];
        int i = 0;
         try {        
            Statement statement = db.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery("call mayorCrecimiento (date('2020-04-01'))");
            while(resultado.next()){
                subs[i] = resultado.getString(1);
                //System.out.println(subs[i]);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subs;
    } 
}
