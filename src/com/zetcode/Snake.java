package com.zetcode;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Snake extends JFrame {

    public Snake() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
               
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        AudioPlayer eatingsound = new AudioPlayer("src/resources/eating_sound.wav");
        try {
        	eatingsound.play();
        	System.out.println("played");
        }catch(Exception e) {System.out.println("error");}
        //        EventQueue.invokeLater(() -> {
//            JFrame ex = new Snake();
//            ex.setVisible(true);
//        });
    }
}
