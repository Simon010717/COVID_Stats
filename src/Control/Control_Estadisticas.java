/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.SubdivisionDAO;
import DAO.UsuarioDAO;
import java.util.Arrays;

/**
 *
 * @author Juan Andres Gonzalez
 */
public class Control_Estadisticas {
    private UsuarioDAO usuariosdao;
    private SubdivisionDAO subdivisiondao;

    public Control_Estadisticas() {
        usuariosdao = new UsuarioDAO();
        subdivisiondao = new SubdivisionDAO();
    }
    
    public int contarUsuarios(){
        return usuariosdao.contarUsuarios();
    }
    
    public String[] crecimiento(){
        return subdivisiondao.crecimiento();
    }
   
}
