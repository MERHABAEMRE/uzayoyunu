/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzayoyunu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Ates{

        private int x;
        private int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
       
}


public class Oyun extends JPanel implements KeyListener , ActionListener{

    Timer timer = new Timer(5,this);
    
    private int gecenSure = 0;
    private int harcananAtes = 0;
    
    private BufferedImage image;
    
    private ArrayList<Ates> atesler = new ArrayList<Ates>();
    
    private int atesdirY = 1;
    private int topX = 0;
    private int topdirX = 2;
    private int uzaygemisiX = 0;
    private int dirUzayX =20;

    public boolean kontrolEt(){
        for (Ates ates : atesler) {
            if(new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(topX,0,20,20))){
            return true;
            }
        }
        return false;
    }
    
    public Oyun() {
        try {
            image = ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setBackground(Color.BLACK);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        gecenSure+=5;
        
        
        g.setColor(Color.red);
        g.fillOval(topX, 0, 20, 20);
        //top
        
        g.drawImage(image,uzaygemisiX , 490,image.getWidth()/10,image.getHeight()/10,this);
        //uzay gemisi
          
        for(Ates ates : atesler){
            if(ates.getY() < 0){
                atesler.remove(ates);
            }
        }
        
        g.setColor(Color.BLUE);
        
        for (Ates ates : atesler) {
            g.fillRect(ates.getX(), ates.getY(), 10, 20);
        }
        
        //atesler
        
        if(kontrolEt()){
            timer.stop();
            String message = "KAZANDINIZ...\n" + "Harcanan ateş : " + harcananAtes +
                             "\nGeçen süre : " + gecenSure / 1000.0 + " saniye";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
        
        
    }
        //her repaint çağrıldığında paintte çağrılıyor.tekrardan oluşturma (repaint)
    @Override
    public void repaint() {
        super.repaint(); 
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {    
    }
    
    //tuş basma olayı
    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        
        if(c == KeyEvent.VK_LEFT){
            if(uzaygemisiX <= 0){
                uzaygemisiX = 0;
            }else{
                uzaygemisiX -= dirUzayX;
            }
        }
        else if(c == KeyEvent.VK_RIGHT){
            if(uzaygemisiX >= 740){
                uzaygemisiX = 740;
            }else{
                uzaygemisiX += dirUzayX;
            }
        }
        else if(c == KeyEvent.VK_CONTROL){
            atesler.add(new Ates(uzaygemisiX + 15 ,490));
            
            harcananAtes++;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // döngü olduğu ve sürekli çalıştığı için ateş yukarı gidiyor
        for (Ates ates : atesler) {
            ates.setY(ates.getY() - atesdirY);
        }
        
        //topun hareketi timer ile
        topX += topdirX;
        
        if(topX >= 740){
        topdirX= -topdirX;
        }
        if(topX <= 0){
        topdirX= -topdirX;
        }
        
        repaint();
    }
    
}
