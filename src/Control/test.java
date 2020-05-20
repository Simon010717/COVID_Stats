/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

/**
 *
 * @author Juan Andres Gonzalez
 */
public class test {
    public static void main(String[] args) {
        Control_Estadisticas control = new Control_Estadisticas();
        for (String i : control.crecimiento()) {
            System.out.println(i);
        }
        System.out.println(control.crecimiento());
    }
}
