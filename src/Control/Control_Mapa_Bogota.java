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
public class Control_Mapa_Bogota {
    private DAO dao;

    public Control_Mapa_Bogota() {
        dao = new DAO();
    }
    
    public int[] casosBogota(){
        int[] casos = dao.casosMapaBogota();
        int[] completos = new int[20];
        
        int aux = 0;
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
    
    public void enviarAJS(){
        String[] crecimiento = dao.crecimiento();
        int[] casosBog = casosBogota();
        File f;
        f = new File("mapas/bogota.json");


        //Escritura
        try{
        FileWriter w = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);  
        wr.write("data = '[");
        for (int i = 0; i < casosBog.length-1; i++) {
            wr.write(String.valueOf(casosBog[i])+",");
        }
        wr.write(String.valueOf(casosBog[casosBog.length-1])+"]';\n");
        wr.write("dato = '[");
        for (int i = 6; i < 11; i++) {
            wr.write('\"'+String.valueOf(crecimiento[i])+'\"'+",");
        }
        wr.write('\"'+String.valueOf(crecimiento[11])+'\"'+"]';");
        wr.close();
        bw.close();
        }catch(IOException e){};
    }
}