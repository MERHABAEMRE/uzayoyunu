/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzayoyunu;

import javax.swing.JFrame;

/**
 *
 * @author aliem
 */
public class Uzayoyunu extends JFrame{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame ekran = new JFrame("UZAY OYUNU");
        ekran.setResizable(false); // yeniden boyutlandırılabilir
        ekran.setFocusable(false);  // focus jpanelde olması lazım
        
        ekran.setSize(800,600);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        Oyun oyun = new Oyun();
        
        oyun.requestFocus(); // tuşları anlaması için focus ver
        
        oyun.addKeyListener(oyun); 
        oyun.setFocusable(true); // odağı jpanele ver
        
        oyun.setFocusTraversalKeysEnabled(false); //odağın başka yere gitmesini engeller
        
        ekran.add(oyun);
        
        ekran.setVisible(true);
        
        
        
        
    }
    
}
