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
public class Control_Grafica_Colombia {
    private DAO dao;

    public Control_Grafica_Colombia() {
        dao = new DAO();
    }
    
    public int[][][] graficasTemporalesCol(){
        int[][] graficaSub;
        int [][][] grafica = new int [37][80][4];
        int [][][] graficadef = new int [33][80][4];
        String[] subs = dao.subdivisionesCol();
        
        for (int i = 0; i < grafica.length; i++) {
            graficaSub = dao.graficasTemporalesCol(subs[i]);
            grafica[i] = graficaSub;
            
        }
        
        graficadef[4] = grafica[5];
        graficadef[6] = grafica[7];
        graficadef[7] = grafica[9];
        graficadef[8] = grafica[10];
        
        for (int i = 0; i < 3; i++) {graficadef[i] = grafica[i];}
        for (int i = 9; i < 27; i++) {graficadef[i] = grafica[i+3];}
        for (int i = 27; i < 33; i++) {graficadef[i] = grafica[i+4];}
        
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 4; j++) {
                graficadef[3][i][j] = grafica[3][i][j] + grafica[4][i][j];
                graficadef[5][i][j] = grafica[6][i][j] + grafica[11][i][j];
                graficadef[30][i][j] = grafica[34][i][j] + grafica[8][i][j];
                graficadef[19][i][j] = grafica[22][i][j] + grafica[30][i][j];
            }
        }
        return graficadef;
    }
    
    public void enviarAJS(){
        int[][][] graficas = graficasTemporalesCol();
        String maxFecha = dao.maxFecha("Vichada");
        File f;
        f = new File("mapas/tempcol.json");


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
        wr.write(String.valueOf(graficas[graficas.length-1][79][3]) + "]]\"\n");
        
        wr.write("fecha = \""+ maxFecha + "\"\n"); 
        wr.close();
        bw.close();
        }catch(IOException e){};
    }
    
    
}
