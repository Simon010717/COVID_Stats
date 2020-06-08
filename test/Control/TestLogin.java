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
public class TestLogin {
    private static Control_Login controlLogin = new Control_Login();
    
    private int DATOS_INCORRECTOS = -1;
    private int ES_UN_ADMIN = 1;
    private int ES_UN_USUARIO = 0;
    private int DATOS_VACIOS = -3;
    public TestLogin() {
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
        String usuario2 ="";
        String contrasenia ="Contra$eña4";
        String contrasenia2 ="";
        assertEquals(controlLogin.verificarUsuario(usuario2, contrasenia),DATOS_VACIOS);
        assertEquals(controlLogin.verificarUsuario(usuario, contrasenia2),DATOS_VACIOS);
        assertEquals(controlLogin.verificarUsuario(usuario2, contrasenia2),DATOS_VACIOS);
    }
    
    @Test
    public void testDatos() {
        String usuario ="JuanAndres";
        String usuario2 ="JuanAndras";
        String contrasenia ="Contra$eña4";
        String contrasenia2 ="Contra$eña5";
        assertEquals(controlLogin.verificarUsuario(usuario, contrasenia2),DATOS_INCORRECTOS);
        assertEquals(controlLogin.verificarUsuario(usuario2, contrasenia),DATOS_INCORRECTOS);
    }

    @Test
    public void testAdmin() {
        String usuario ="JuanAndres";
        String contrasenia ="Contra$eña4";
        assertEquals(controlLogin.verificarUsuario(usuario, contrasenia),ES_UN_ADMIN);
    }
    
    @Test
    public void testUsuario() {
        String usuario ="SimonAparicio";
        String contrasenia ="Contra$eña5";
        assertEquals(controlLogin.verificarUsuario(usuario, contrasenia),ES_UN_USUARIO);
    }
    
    
}
