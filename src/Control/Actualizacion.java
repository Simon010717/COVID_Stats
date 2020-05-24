/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.BogotaDAO;
import DAO.ColombiaDAO;
import Entidad.CasoBogota;
import Entidad.CasoColombia;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que actualiza la base de datos de la aplicación.
 * @author sebasdeloco
 */
public class Actualizacion {
    private String dir;
    private SimpleDateFormat formato1;
    private SimpleDateFormat formato2;
    
    public Actualizacion(){
        this.dir = System.getProperty("user.dir")+"/DB";
        this.formato1 = new SimpleDateFormat("dd/MM/yyyy");
        this.formato2 = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    public static void main(String[] args) {
        Actualizacion act = new Actualizacion();
        //act.descargarDatos();
        act.cargarBogota();
        //act.cargarColombia();
        //System.out.println(act.arreglarFecha("13/03/2020"));
        
    }
    
    /**
     * Método que ejecuta los scripts que descargan los datos actualizados desde las páginas oficiales.
     */
    public void descargarDatos(){
        try {
            System.out.println("Descargando datos....");
            Process p = Runtime.getRuntime().exec("./descargarDatos.sh "+this.dir, null, new File(dir));
            p.waitFor();
            System.out.println("Datos descargados...");
        } catch (IOException ex) {
            Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    
    /**
     * Método que carga los datos actualizados a partir del archivo csv descargado para la información de bogota.
     */
    public void cargarBogota(){
        BogotaDAO bogota = new BogotaDAO();
        try {
            Scanner scan = new Scanner(new File(this.dir+"/datosBogota.csv"));
            scan.useDelimiter(",|\n");
            //Saltamos la primera linea del archivo que contiene los encabezados de las columnas.
            for (int i = 0; i < 8; i++) {
                scan.next();
            }
            while(scan.hasNext()){
                String fecha = scan.next(), ciudad = scan.next(), localidad = scan.next(), tipo, ubicacion, estado;
                int edad = scan.nextInt();
                char sexo = scan.next().charAt(0);
                tipo = scan.next(); ubicacion = scan.next(); estado = scan.next().trim();
                CasoBogota aux = new CasoBogota(arreglarFecha(fecha), ciudad,
                    localidad, edad, sexo
                    , tipo, (ubicacion.equals("Fallecido (No aplica") ? "Fallecido": ubicacion ),// bug en los datos >:(
                    (estado.equals(" No causa Directa)") ? "Fallecido": estado ));
                if(fecha!=null) bogota.ingresarCaso(aux);
                if(ubicacion.equals("Fallecido (No aplica")){ scan.next(); scan.next();}
            }
            System.out.println("Datos de Bogota cargados exitosamente!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método que carga los datos actualizados a partir del archivo csv descargado para la información de Colombia.
     */
    public void  cargarColombia(){
        ColombiaDAO colombia = new ColombiaDAO();
        try {
            Scanner scan = new Scanner(new File(this.dir + "/datoscolombia.csv"));
            scan.useDelimiter(",|\n");
            //Saltamos la primera linea del archivo que contiene los encabezados de las columnas.
            for (int i = 0; i < 16; i++) {
                scan.next();
            }
            
            //Ciclo para cargar todos los registros del archivo.
            while(scan.hasNext()){
                //Leemos el registro del archivo
                int id_de_caso = scan.nextInt(); //id de caso
                String fecha_de_notificacion = scan.next();//fecha de notificacion
                int codigo_divipola = scan.nextInt(); //codigo divipola
                String ciudad_de_ubicacion = scan.next(); //ciudad de ubicacion
                String departamento = scan.next(); //departamento
                String atencionn = scan.next(); //atencion
                int edad = scan.nextInt(); //edad 
                char sexo = scan.next().charAt(0); //Sexo
                String tipo = scan.next(); //tipo
                String estado = scan.next(); //estado
                String pais_de_procedencia = scan.next(); //pais de procedencia
                String fis = scan.next(); //fis
                String fecha_de_muerte = scan.next();//fecha de muerte
                String fecha_diagnostico = scan.next(); //fecha de diagnostico
                if(fecha_diagnostico==null) fecha_diagnostico = fecha_de_notificacion;
                String fecha_recuperado = scan.next(); //fecha de recuperado
                String fecha_reporte_web = scan.next(); //fecha de reporte web

                //creamos una instancia de CasoColombia
                CasoColombia caso = new CasoColombia(id_de_caso,
                    (fecha_de_notificacion.length() < 9 ? null : fecha_de_notificacion.substring(0, 10)), //si la fecha no es nula tomamos solo la primera parte.
                    codigo_divipola,
                    ciudad_de_ubicacion,
                    departamento,
                    atencionn,
                    edad,
                    sexo,
                    tipo,
                    estado,
                    pais_de_procedencia,
                    (fis.length() < 9 ? "null" : fis.substring(0, 10)),
                    (fecha_de_muerte.length() < 9 ? null : fecha_de_muerte.substring(0, 10)),
                    (fecha_diagnostico.length() < 9 ? null : fecha_diagnostico.substring(0, 10)),
                    (fecha_recuperado.length() < 9 ? null : fecha_recuperado.substring(0, 10)),
                    (fecha_reporte_web.length() < 9 ? null : fecha_reporte_web.substring(0, 10))
                );
                //Ingresamos el registro por medio de la clase DAO
                if (fecha_de_notificacion != null) colombia.ingresarCaso(caso);
            }
            System.out.println("Datos de Colombia cargados exitosamente!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    /**
     * Método que cambia el formato de la fecha para que sea compatible con Mysql.
     * @param fecha
     * @return 
     */
    public String arreglarFecha(String fecha){
        try {
            return formato2.format(formato1.parse(fecha));
        } catch (ParseException ex) {
            Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
