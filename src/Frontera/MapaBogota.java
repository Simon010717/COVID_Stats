/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontera;

import Control.Control_Mapa_Bogota;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author Juan Andres Gonzalez
 */
public class MapaBogota {
    public Control_Mapa_Bogota control =  new Control_Mapa_Bogota();
    public void ejecutar() {
        control.enviarAJS();
        // Creating and running Chromium engine
        System.setProperty("jxbrowser.license.key", "1BNDHFSC1FVOAGBDPDQNWEX7VJWL9OHK8SELTY9HXWWA0ZWONI9AOEPWVHXKDCD27N8OJL"); 
        Engine engine = Engine.newInstance(
        EngineOptions.newBuilder(HARDWARE_ACCELERATED)
                .licenseKey("1BNDHFSC1FVOAGBDPDQNWEX7VJWL9OHK8SELTY9HXWWA0ZWONI9AOEPWVHXKDCD27N8OJL")
                .build());
        
        Browser browser = engine.newBrowser();

        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance.
            BrowserView view = BrowserView.newInstance(browser);

            // Creating and displaying Swing app frame.
            JFrame frame = new JFrame("Mapa de contagio de Bogotá");
            // Close Engine and onClose app window
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    new Login().setVisible(true);
                    engine.close();
                }
            });
            
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            JTextField addressBar = new JTextField("file:///C:/Users/Juan%20Andres%20Gonzalez/Documents/La%20Nacho/Ingesoft/COVID_Stats/mapas/indexBog.html");
            addressBar.addActionListener(e ->
                    browser.navigation().loadUrl(addressBar.getText()));
            frame.add(addressBar, BorderLayout.NORTH);
            frame.add(view, BorderLayout.CENTER);
            frame.setSize(800, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}