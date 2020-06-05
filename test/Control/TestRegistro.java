/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

//import static Frontera.Registro;

/**
 *
 * @author Juan Andres Gonzalez
 */
public class TestRegistro {
    private static Control_Registro controlRegistro = new Control_Registro();
    
    private int NOMBRE_NO_DISPONIBLE = -1;
    private int LONG_NOMBRE_INCORRECTA = -2;
    private int LONG_CORREO_INCORRECTA = -3;
    private int FORMATO_CORREO_INCORRECTO = -4;
    private int LONG_CONTRASENIA_INCORRECTA = -5;
    private int FORMATO_CONTRASENIA_INCORRECTO = -6;
    private int EQUIVALENCIA_CONTRASENIAS = -7;
    private int DATOS_VACIOS = -8;
    private int USUARIO_AUTORIZADO = 0;
    
    public TestRegistro() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void testDatosVacios() {
        String usuario ="JuanAndres";
        String correo ="juanandres@universidad.co";
        String contrasenia ="Contra$eña4";
        String confirmar ="";
        assertEquals(controlRegistro.registrarUsuario(usuario, correo, contrasenia, confirmar), DATOS_VACIOS);
    }
    @Test
    public void testLongitudUsuario() {
        String usuario1 ="R";
        String usuario2 ="JuanAndresGonzalez";
        String correo ="juanandres@universidad.co";
        String contrasenia ="Contra$eña4";
        String confirmar ="Contra$eña4";
        assertEquals(controlRegistro.registrarUsuario(usuario1, correo, contrasenia, confirmar), LONG_NOMBRE_INCORRECTA);
        assertEquals(controlRegistro.registrarUsuario(usuario2, correo, contrasenia, confirmar), LONG_NOMBRE_INCORRECTA);
    }
    
    @Test
    public void testUsuarioUnico() {
        String usuario ="maria11";
        String correo ="juanandres@universidad.co";
        String contrasenia ="Contra$eña4";
        String confirmar ="Contra$eña4";
        assertEquals(controlRegistro.registrarUsuario(usuario, correo, contrasenia, confirmar), NOMBRE_NO_DISPONIBLE);
    
    }
    
    @Test
    public void testLongitudCorreo() {
        String usuario ="JuanAndres";
        String correo1 ="t@a.com";
        String correo2 ="juanandresgonzalezarias@universidadnacional.edu.co";
        String contrasenia ="Contra$eña4";
        String confirmar ="Contra$eña4";
        assertEquals(controlRegistro.registrarUsuario(usuario, correo1, contrasenia, confirmar), LONG_CORREO_INCORRECTA);
        assertEquals(controlRegistro.registrarUsuario(usuario, correo2, contrasenia, confirmar), LONG_CORREO_INCORRECTA);
    }
    
    @Test
    public void testFormatoCorreo() {
        String usuario ="JuanAndres";
        String correo1 ="juanandres.com";
        String correo2 ="juanandres@universidad";
        String contrasenia ="Contra$eña4";
        String confirmar ="Contra$eña4";
        assertEquals(controlRegistro.registrarUsuario(usuario, correo1, contrasenia, confirmar), FORMATO_CORREO_INCORRECTO);
        assertEquals(controlRegistro.registrarUsuario(usuario, correo2, contrasenia, confirmar), FORMATO_CORREO_INCORRECTO);
    }
    
    @Test
    public void testLongitudContrasenia() {
        String usuario ="JuanAndres";
        String correo ="juanandres@universidad.co";
        String contrasenia1 ="2S$";
        String contrasenia2 ="JSHASundefinedROJASASD";
        String confirmar ="Contra$eña4";
        assertEquals(controlRegistro.registrarUsuario(usuario, correo, contrasenia1, confirmar), LONG_CONTRASENIA_INCORRECTA);
        assertEquals(controlRegistro.registrarUsuario(usuario, correo, contrasenia2, confirmar), LONG_CONTRASENIA_INCORRECTA);
    }
    
    @Test
    public void testFormatoContrasenia() {
        String usuario ="JuanAndres";
        String correo ="juanandres@universidad.co";
        String contrasenia1 ="contra$eña4";
        String contrasenia2 ="Contra$eñaa";
        String contrasenia3 ="Contraseña4";
        String confirmar ="Contra$eña4";
        assertEquals(controlRegistro.registrarUsuario(usuario, correo, contrasenia1, confirmar), FORMATO_CONTRASENIA_INCORRECTO);
        assertEquals(controlRegistro.registrarUsuario(usuario, correo, contrasenia2, confirmar), FORMATO_CONTRASENIA_INCORRECTO);
        assertEquals(controlRegistro.registrarUsuario(usuario, correo, contrasenia3, confirmar), FORMATO_CONTRASENIA_INCORRECTO);
    }
    
    @Test
    public void testEquivalenciaContrasenia() {
        String usuario ="JuanAndres";
        String correo ="juanandres@universidad.co";
        String contrasenia ="Contra$eña6";
        String confirmar ="Contra$eña4";
        assertEquals(controlRegistro.registrarUsuario(usuario, correo, contrasenia, confirmar), EQUIVALENCIA_CONTRASENIAS);
    }
    /*
    @Test
    public void testTodoCorrecto() {
        String usuario ="JuanAndres";
        String correo ="juanandres@universidad.co";
        String contrasenia ="Contra$eña4";
        String confirmar ="Contra$eña4";
        assertEquals(controlRegistro.registrarUsuario(usuario, correo, contrasenia, confirmar), USUARIO_AUTORIZADO);
    }*/
}
