/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidad.CasoColombia;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase DAO que conecta con la tabla INS de la base de datos.
 * @author sebasdeloco
 */
public class ColombiaDAO {
    private DBConexion db;
    
    public ColombiaDAO(){
        this.db =  new DBConexion();
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
            Logger.getLogger(BogotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
