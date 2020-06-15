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
public class Control_Mapa_Colombia {
    private DAO dao;

    public Control_Mapa_Colombia() {
        this.dao = new DAO();
    }
    
    public int[][] casosColombia(){
        int[][] casos = dao.casosMapaColombia();
        int[][] completo = new int[33][4];
        
        int[] aux = new int[4];
        for (int i=0; i<casos.length/2; i++) {
            aux = casos[i];
            casos[i] = casos[casos.length-1-i];
            casos[casos.length-1-i]= aux;
        }   
        completo[0] = casos[0];
        completo[1] = casos[1];
        completo[2] = casos[2];
        completo[3] = casos[3];
        for(int j=0; j<4; j++){
            completo[4][j] = casos[6][j] + casos [11][j];
        }
        completo[5] = casos[7];
        completo[6] = casos[13];
        completo[7] = casos[14];
        completo[8] = casos[15];
        completo[9] = casos[9];
        completo[10] = casos[26];
        completo[11] = casos[10];
        completo[12] = casos[12];
        completo[13] = casos[17];
        completo[14] = casos[5];
        completo[15] = casos[18];
        completo[16] = casos[19];
        completo[17] = casos[20];
        completo[18] = casos[12];
        for(int j=0; j<4; j++){
            completo[19][j] = casos[22][j] + casos [30][j];
        }
        completo[20] = casos[23];
        completo[21] = casos[25];
        completo[22] = casos[24];
        completo[23] = casos[26];
        completo[24] = casos[27];
        completo[25] = casos[28];
        completo[26] = casos[31];
        completo[27] = casos[32];
        completo[28] = casos[29];
        completo[29] = casos[33];
        for(int j=0; j<4; j++){
            completo[30][j] = casos[34][j] + casos [8][j];
        }
        completo[31] = casos[35];
        completo[32] = casos[36];
        return completo;
    }
    
    public void enviaAJS(){
        String [] crecimiento = dao.crecimiento();
        int[][] casosCol = casosColombia();
        File f;
        f = new File("mapas/geocol.json");


        //Escritura
        try{
        FileWriter w = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);  
        
        wr.write("conf = '["); //confirmados
        for (int i = 0; i < casosCol.length-1; i++) {
            wr.write(String.valueOf(casosCol[i][0])+",");
        }
        wr.write(String.valueOf(casosCol[casosCol.length-1][0])+"]';\n");
        
        wr.write("act = '["); //activos
        for (int i = 0; i < casosCol.length-1; i++) {
            wr.write(String.valueOf(casosCol[i][1])+",");
        }
        wr.write(String.valueOf(casosCol[casosCol.length-1][1])+"]';\n");
        
        wr.write("rec = '[");//recuperados
        for (int i = 0; i < casosCol.length-1; i++) {
            wr.write(String.valueOf(casosCol[i][2])+",");
        }
        wr.write(String.valueOf(casosCol[casosCol.length-1][2])+"]';\n");
        
        wr.write("fall = '["); //fallecidos
        for (int i = 0; i < casosCol.length-1; i++) {
            wr.write(String.valueOf(casosCol[i][3])+",");
        }
        wr.write(String.valueOf(casosCol[casosCol.length-1][3])+"]';\n");
        
        wr.write("crec = '["); //crecimiento
        for (int i = 0; i < 5; i++) {
            wr.write('\"'+String.valueOf(crecimiento[i])+'\"'+",");
        }
        wr.write('\"'+String.valueOf(crecimiento[5])+'\"'+"]';\n");
        wr.close();
        bw.close();
        }catch(IOException e){};
    }
}
