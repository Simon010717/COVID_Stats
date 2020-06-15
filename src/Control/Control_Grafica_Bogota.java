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
    
    public int[][][] graficasTemporalesBog(){
        int[][] graficaSub;
        int [][][] grafica = new int [21][81][4]; 
        int [][][] graficadef = new int [20][81][4]; 
        String[] subs = dao.subdivisionesBog();
        
        for (int i = 0; i < grafica.length; i++) {
            graficaSub = dao.graficasTemporales(subs[i]);
            grafica[i] = graficaSub;
            
        }
        for (int i = 0; i < 7; i++) {graficadef[i] = grafica[i];}
        for (int i = 7; i < 20; i++) {graficadef[i] = grafica[i+1];}
        
        return graficadef;
    }
    
    public void enviarAJS(){
        int[][][] graficas = graficasTemporalesBog();
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
                wr.write(String.valueOf(graficas[i][j][0]) + ",");
            }wr.write(String.valueOf(graficas[i][graficas[i].length-1][0])+"],");
            //wr.write(""); 
        }
        wr.write("["); 
        for (int j = 0; j < 79; j++) {
                wr.write(String.valueOf(graficas[graficas.length-1][j][0]) + ",");
        }
        wr.write(String.valueOf(graficas[graficas.length-1][79][0]) + "]]\"\n");
        
        wr.write("activ = \"["); 
        for (int i = 0; i < graficas.length-1; i++) {
            wr.write("["); 
            for (int j = 0; j < graficas[i].length-1; j++) {
                wr.write(String.valueOf(graficas[i][j][1]) + ",");
            }wr.write(String.valueOf(graficas[i][graficas[i].length-1][1])+"],");
            //wr.write(""); 
        }
        wr.write("["); 
        for (int j = 0; j < 79; j++) {
                wr.write(String.valueOf(graficas[graficas.length-1][j][1]) + ",");
        }
        wr.write(String.valueOf(graficas[graficas.length-1][79][1]) + "]]\"\n");
        
        wr.write("recup = \"["); 
        for (int i = 0; i < graficas.length-1; i++) {
            wr.write("["); 
            for (int j = 0; j < graficas[i].length-1; j++) {
                wr.write(String.valueOf(graficas[i][j][2]) + ",");
            }wr.write(String.valueOf(graficas[i][graficas[i].length-1][2])+"],");
            //wr.write(""); 
        }
        wr.write("["); 
        for (int j = 0; j < 79; j++) {
                wr.write(String.valueOf(graficas[graficas.length-1][j][2]) + ",");
        }
        wr.write(String.valueOf(graficas[graficas.length-1][79][2]) + "]]\"\n");
        
        wr.write("falle = \"["); 
        for (int i = 0; i < graficas.length-1; i++) {
            wr.write("["); 
            for (int j = 0; j < graficas[i].length-1; j++) {
                wr.write(String.valueOf(graficas[i][j][3]) + ",");
            }wr.write(String.valueOf(graficas[i][graficas[i].length-1][3])+"],");
            //wr.write(""); 
        }
        wr.write("["); 
        for (int j = 0; j < 79; j++) {
                wr.write(String.valueOf(graficas[graficas.length-1][j][3]) + ",");
        }
        wr.write(String.valueOf(graficas[graficas.length-1][79][3]) + "]]\"");
        wr.close();
        bw.close();
        }catch(IOException e){};
    }
    
}
