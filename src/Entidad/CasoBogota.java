/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

/**
 * Clase entidad que representa un registro de la tabla Bogota.
 * @author sebasdeloco
 */
public class CasoBogota {
    public String fecha_diagnostico;
    public String ciudad;
    public String localidad;
    public int edad;
    public char sexo;
    public String tipo;
    public String ubicacion;
    public String estado;

    public CasoBogota(String fecha_diagnostico, String ciudad, String localidad, int edad, char sexo, String tipo, String ubicacion, String estado) {
        this.fecha_diagnostico = fecha_diagnostico;
        this.ciudad = ciudad;
        this.localidad = localidad;
        this.edad = edad;
        this.sexo = sexo;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    public String getFecha_diagnostico() {
        return fecha_diagnostico;
    }

    public void setFecha_diagnostico(String fecha_diagnostico) {
        this.fecha_diagnostico = fecha_diagnostico;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
