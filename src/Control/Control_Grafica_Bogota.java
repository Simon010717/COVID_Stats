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
        String[] subs = dao.subdivisionesBog();
        
        for (int i = 0; i < grafica.length; i++) {
            graficaSub = dao.graficasTemporales(subs[i]);
            grafica[i] = graficaSub;
            
        }
        /*for (int i = 0; i < 37; i++) {
            for (int j = 0; j < 81; j++) {
                //for (int k = 0; k < 4; k++) {
                    System.out.print(grafica[i][j][1] + " ");
                //}
            }System.out.println("");
        }System.out.println("");*/
        return grafica;
    }
    
    public void enviarAJS(){
        int[][][] graficas = graficasTemporalesBog();
        File f;
        f = new File("mapas/tembog.json");


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
        for (int j = 0; j < 80; j++) {
                wr.write(String.valueOf(graficas[graficas.length-1][j][0]) + ",");
        }
        wr.write(String.valueOf(graficas[graficas.length-1][80][0]) + "]]\"\n");
        
        wr.write("activ = \"["); 
        for (int i = 0; i < graficas.length-1; i++) {
            wr.write("["); 
            for (int j = 0; j < graficas[i].length-1; j++) {
                wr.write(String.valueOf(graficas[i][j][1]) + ",");
            }wr.write(String.valueOf(graficas[i][graficas[i].length-1][1])+"],");
            //wr.write(""); 
        }
        wr.write("["); 
        for (int j = 0; j < 80; j++) {
                wr.write(String.valueOf(graficas[graficas.length-1][j][1]) + ",");
        }
        wr.write(String.valueOf(graficas[graficas.length-1][80][1]) + "]]\"\n");
        
        wr.write("recup = \"["); 
        for (int i = 0; i < graficas.length-1; i++) {
            wr.write("["); 
            for (int j = 0; j < graficas[i].length-1; j++) {
                wr.write(String.valueOf(graficas[i][j][2]) + ",");
            }wr.write(String.valueOf(graficas[i][graficas[i].length-1][2])+"],");
            //wr.write(""); 
        }
        wr.write("["); 
        for (int j = 0; j < 80; j++) {
                wr.write(String.valueOf(graficas[graficas.length-1][j][2]) + ",");
        }
        wr.write(String.valueOf(graficas[graficas.length-1][80][2]) + "]]\"\n");
        
        wr.write("falle = \"["); 
        for (int i = 0; i < graficas.length-1; i++) {
            wr.write("["); 
            for (int j = 0; j < graficas[i].length-1; j++) {
                wr.write(String.valueOf(graficas[i][j][3]) + ",");
            }wr.write(String.valueOf(graficas[i][graficas[i].length-1][3])+"],");
            //wr.write(""); 
        }
        wr.write("["); 
        for (int j = 0; j < 80; j++) {
                wr.write(String.valueOf(graficas[graficas.length-1][j][3]) + ",");
        }
        wr.write(String.valueOf(graficas[graficas.length-1][80][3]) + "]]\"");
        wr.close();
        bw.close();
        }catch(IOException e){};
    }
    
    public static void main(String[] args) {
        Control_Grafica_Bogota control = new Control_Grafica_Bogota();
        control.enviarAJS();
        /*for (int i = 0; i < 37; i++) {
            for (int j = 0; j < 81; j++) {
                //for (int k = 0; k < 4; k++) {
                    System.out.print(control.graficasTemporalesCol()[i][j][0] + " ");
                //}
            }System.out.println("");
        }System.out.println("");*/
    }
}
