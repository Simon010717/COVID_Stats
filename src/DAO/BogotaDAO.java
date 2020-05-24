/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidad.CasoBogota;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase DAO que conecta con la tabla Bogota en la base de datos.
 * @author sebasdeloco
 */
public class BogotaDAO {
    private DBConexion db;
    
    public BogotaDAO(){
        this.db = new DBConexion();
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
            Logger.getLogger(BogotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
