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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simon
 */
public class Control_Mapas {
    private DAO dao = new DAO();

    public Control_Mapas() {
    }
    
    public void hoyJSON(){
        String[] m = {"Colombia","Bogota"};
        String[] vars = {"hombres", "mujeres", "casa", "hospital", "uci",
                    "recuperados", "fallecidos", "cero", "diez", "veinte", "treinta",
                    "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"};
        String[] e = {"_confirmados", "_recuperados", "_fallecidos"};
        for(String X : m){
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("mapas/hoy"+X+".json")));
                int[][] datos = new int[(X.equals("Colombia") ? 34 : 21)][37];
                int[][] dat = (X.equals("Colombia") ? dao.distCasosHoyCol() : dao.distCasosHoyBog());
                if(X.equals("Colombia")){
                    datos[0] = dat[0];
                    datos[1] = dat[1];
                    datos[2] = dat[2];
                    datos[3] = dat[3];
                    for(int j=0; j<37; j++){
                        datos[4][j] = dat[6][j] + dat [11][j];
                    }
                    datos[5] = dat[7];
                    datos[6] = dat[13];
                    datos[7] = dat[14];
                    datos[8] = dat[15];
                    datos[9] = dat[9];
                    datos[10] = dat[26];
                    datos[11] = dat[10];
                    datos[12] = dat[12];
                    datos[13] = dat[17];
                    datos[14] = dat[5];
                    datos[15] = dat[18];
                    datos[16] = dat[19];
                    datos[17] = dat[20];
                    datos[18] = dat[12];
                    for(int j=0; j<37; j++){
                        datos[19][j] = dat[22][j] + dat [30][j];
                    }
                    datos[20] = dat[23];
                    datos[21] = dat[25];
                    datos[22] = dat[24];
                    datos[23] = dat[26];
                    datos[24] = dat[27];
                    datos[25] = dat[28];
                    datos[26] = dat[31];
                    datos[27] = dat[32];
                    datos[28] = dat[29];
                    datos[29] = dat[33];
                    for(int j=0; j<37; j++){
                        datos[30][j] = dat[34][j] + dat [8][j];
                    }
                    datos[31] = dat[35];
                    datos[32] = dat[36];
                    for (int i = 0; i < 33; i++) {
                        for (int j = 0; j < 37; j++) {
                            datos[33][j] += datos[i][j];
                        }
                    }
                } else {
                    for (int i = 0; i < 7; i++) {
                        datos[i] = dat[i];
                    }
                    for (int i = 7; i < 20; i++) {
                        datos[i] = dat[i+1];
                    }
                    for (int i = 0; i < 21; i++) {
                        for (int j = 0; j < 37; j++) {
                            datos[20][j] += datos[i][j];
                        }
                    }
                }
                for(int i=0;i<37; i++){
                    bw.write(vars[(i >= 17 ? i-(int)((i-7)/10)*10 : i)]+(i >= 7 ? e[(i-7)/10] : "")+" = '[");
                    for(int j=0; j<(X.equals("Colombia") ? 34 : 21); j++){
                        bw.write(datos[j][i]+(j == (X.equals("Colombia") ? 33 : 20) ? ""  : ","));
                    }
                    bw.write("]';\n");
                }/*
                for(int i=0;i<33;i++){
                    for(int j=0;j<37;j++){
                        System.out.print(datos[i][j]+" ");
                    }
                    System.out.println("");
                }*/
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Control_Mapas.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }
    
    public int[][] casosBogota(){
        int[][] casos = dao.casosMapaBogota();
        int[][] completos = new int[20][4];
        
        int[] aux = new int[4];
        for (int i=0; i<casos.length/2; i++) {
            aux = casos[i];
            casos[i] = casos[casos.length-1-i];
            casos[casos.length-1-i] = aux;
        }
        
        for (int i = 0; i < 7; i++) {
            completos[i] = casos[i];
        }
        for (int i = 7; i < 20; i++) {
            completos[i] = casos[i+1];
        }
        
        return completos;
    }
    
    public void geoBogJSON(){
        String[] crecimiento = dao.crecimiento();
        int[][] casosBog = casosBogota();
        File f;
        f = new File("mapas/geobog.json");


        //Escritura
        try{
        FileWriter w = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);  
        wr.write("conf = '[");
        for (int i = 0; i < casosBog.length-1; i++) {
            wr.write(String.valueOf(casosBog[i][0])+",");
        }
        wr.write(String.valueOf(casosBog[casosBog.length-1][0])+"]';\n");
        
        wr.write("act = '[");
        for (int i = 0; i < casosBog.length-1; i++) {
            wr.write(String.valueOf(casosBog[i][1])+",");
        }
        wr.write(String.valueOf(casosBog[casosBog.length-1][1])+"]';\n");
        
        wr.write("rec = '[");
        for (int i = 0; i < casosBog.length-1; i++) {
            wr.write(String.valueOf(casosBog[i][2])+",");
        }
        wr.write(String.valueOf(casosBog[casosBog.length-1][2])+"]';\n");
        
        wr.write("fall = '[");
        for (int i = 0; i < casosBog.length-1; i++) {
            wr.write(String.valueOf(casosBog[i][3])+",");
        }
        wr.write(String.valueOf(casosBog[casosBog.length-1][3])+"]';\n");
        
        wr.write("crec = '["); //crecimiento
        for (int i = 6; i < 11; i++) {
            wr.write('\"'+String.valueOf(crecimiento[i])+'\"'+",");
        }
        wr.write('\"'+String.valueOf(crecimiento[11])+'\"'+"]';\n");
        wr.close();
        bw.close();
        }catch(IOException e){};
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
    
    public void geoColJSON(){
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
    
    public int[][] graficasTemporalesBog(){
        int [][] grafica = new int [21][80]; 
        int [][] graficadef = new int [21][80]; 
        String[] subs = dao.subdivisionesBog();
        
        for (int i = 0; i < grafica.length; i++) {
            grafica[i] = dao.graficasTemporalesBog(subs[i]);
            
        }
        for (int i = 0; i < 7; i++) {graficadef[i] = grafica[i];}
        for (int i = 7; i < 20; i++) {graficadef[i] = grafica[i+1];}
        
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 80; j++) {
                graficadef[20][j] += graficadef[i][j];
            }
        }
        
        return graficadef;
    }
    
    public void tempBogJSON(){
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
    
    public int[][][] graficasTemporalesCol(){
        int[][] graficaSub;
        int [][][] grafica = new int [37][80][4];
        int [][][] graficadef = new int [34][80][4];
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
        for (int i = 0; i < 33; i++) {
            for (int j = 0; j < 80; j++) {
                for (int k = 0; k < 4; k++) {
                    graficadef[33][j][k] += graficadef[i][j][k];
                }
            }
        }
        
        return graficadef;
    }
    
    public void tempColJSON(){
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
