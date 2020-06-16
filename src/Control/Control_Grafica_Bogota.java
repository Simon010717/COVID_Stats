/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.DAO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Juan Andres Gonzalez
 */
public class Control_Grafica_Bogota {
    private DAO dao;

    public Control_Grafica_Bogota() {
        dao = new DAO();
    }
    
    public int[][] graficasTemporalesBog(){
        int[] graficaSub;
        int [][] grafica = new int [21][80]; 
        int [][] graficadef = new int [20][80]; 
        String[] subs = dao.subdivisionesBog();
        
        for (int i = 0; i < grafica.length; i++) {
            graficaSub = dao.graficasTemporalesBog(subs[i]);
            grafica[i] = graficaSub;
            
        }
        for (int i = 0; i < 7; i++) {graficadef[i] = grafica[i];}
        for (int i = 7; i < 20; i++) {graficadef[i] = grafica[i+1];}
        
        return graficadef;
    }
    
    public void enviarAJS(){
        int[][] graficas = graficasTemporalesBog();
        String maxFecha = dao.maxFecha("Usme");
        File f;
        f = new File("mapas/tempbog.json");


        //Escritura
        try{
        FileWriter w = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);  
        
        wr.write("total = \"["); 
        for (int i = 0; i < graficas.length-1; i++) {
            wr.write("["); 
            for (int j = 0; j < graficas[i].length-1; j++) {
                wr.write(String.valueOf(graficas[i][j]) + ",");
            }wr.write(String.valueOf(graficas[i][graficas[i].length-1])+"],");
            //wr.write(""); 
        }
        wr.write("["); 
        for (int j = 0; j < 79; j++) {
                wr.write(String.valueOf(graficas[graficas.length-1][j]) + ",");
        }
        wr.write(String.valueOf(graficas[graficas.length-1][79]) + "]]\"\n");
        
        wr.write("fecha = \""+ maxFecha + "\"\n"); 
        
        wr.close();
        bw.close();
        }catch(IOException e){};
    }
    public static void main(String[] args) {
        Control_Grafica_Bogota control = new Control_Grafica_Bogota();
        control.enviarAJS();
    }
    
}
