/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

/**
 * Clase entidad que representa un registro de la tabla INS.
 * @author sebasdeloco
 */
public class CasoColombia {
    public int id_de_caso;
    public String fecha_de_notificacion;
    public int codigo_divipola;
    public String ciudad_de_ubicacion;
    public String departamento;
    public String atencionn;
    public int edad;
    public char sexo;
    public String tipo;
    public String estado;
    public String pais_de_procedencia;
    public String fis;
    public String fecha_de_muerte;
    public String fecha_diagnostico;
    public String fecha_recuperado;
    public String fecha_reporte_web;

    public CasoColombia(int id_de_caso, String fecha_de_notificacion, int codigo_divipola, String ciudad_de_ubicacion, String departamento, String atencionn, int edad, char sexo, String tipo, String estado, String pais_de_procedencia, String fis, String fecha_de_muerte, String fecha_diagnostico, String fecha_recuperado, String fecha_reporte_web) {
        this.id_de_caso = id_de_caso;
        this.fecha_de_notificacion = fecha_de_notificacion;
        this.codigo_divipola = codigo_divipola;
        this.ciudad_de_ubicacion = ciudad_de_ubicacion;
        this.departamento = departamento;
        this.atencionn = atencionn;
        this.edad = edad;
        this.sexo = sexo;
        this.tipo = tipo;
        this.estado = estado;
        this.pais_de_procedencia = pais_de_procedencia;
        this.fis = fis;
        this.fecha_de_muerte = fecha_de_muerte;
        this.fecha_diagnostico = fecha_diagnostico;
        this.fecha_recuperado = fecha_recuperado;
        this.fecha_reporte_web = fecha_reporte_web;
    }
    
    @Override
    public String toString() {
        return "CasoColombia{" + "id_de_caso=" + id_de_caso + ", fecha_de_notificacion=" + fecha_de_notificacion + ", codigo_divipola=" + codigo_divipola + ", ciudad_de_ubicacion=" + ciudad_de_ubicacion + ", departamento=" + departamento + ", atencionn=" + atencionn + ", edad=" + edad + ", sexo=" + sexo + ", tipo=" + tipo + ", estado=" + estado + ", pais_de_procedencia=" + pais_de_procedencia + ", fis=" + fis + ", fecha_de_muerte=" + fecha_de_muerte + ", fecha_diagnostico=" + fecha_diagnostico + ", fecha_recuperado=" + fecha_recuperado + ", fecha_reporte_web=" + fecha_reporte_web + '}';
    }
}
