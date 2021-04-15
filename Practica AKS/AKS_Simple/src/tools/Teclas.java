package tools;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Teclas extends JFrame implements KeyListener{
    BorderLayout borde = new BorderLayout();
    JLabel mensaje = new JLabel("Para comenzar pulse una tecla cualquiera:");
   
    JLabel tecla = new JLabel("");
    public Teclas(){
        super("Demostración de KeyListener");
        setSize(350,100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setLayout(borde);
        add(mensaje, BorderLayout.NORTH);
       
        add(tecla, BorderLayout.WEST);
        setVisible(true);
    }
    public static void main(String[] dario) throws AWTException{
    	
    	Robot robot = new Robot();    	
        new Teclas();
        for (int i = 0; i < 10000; i++) {
        	
        }
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        
    }
    public void keyTyped(KeyEvent e) {
            System.out.println("Tecla Pulsada = '" + e.getKeyChar()+ "'");
    }
    public void keyPressed(KeyEvent e) {
       
    }
    public void keyReleased(KeyEvent e) {
       
    }
}
