package com.mycompany._slangword;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class MenuFrame extends JFrame implements ActionListener {

    JButton b1, b2, b3, b4, b5, b6, b7, b8;
    Slang slangWord;

    MenuFrame() throws IOException {
        slangWord = Slang.getInstance();
       
        JLabel label = new JLabel("Slanglingo");
        label.setForeground(Color.green);
        label.setFont(new Font("HelveticaNeue", Font.PLAIN, 50));
        label.setAlignmentX(CENTER_ALIGNMENT);
       
        b1 = new JButton("List Slang Words");
        b1.addActionListener(this);
        b1.setFont(new Font("HelveticaNeue", Font.PLAIN, 18));
        b1.setFocusable(false);

        b2 = new JButton("Find Slang word");
        b2.addActionListener(this);
        b2.setFont(new Font("HelveticaNeue", Font.PLAIN, 18));
        b2.setFocusable(false);

        b3 = new JButton("Add slang word");
        b3.addActionListener(this);
        b3.setFont(new Font("HelveticaNeue", Font.PLAIN, 18));
        b3.setFocusable(false);

        b4 = new JButton("Random Slang Words");
        b4.addActionListener(this);
        b4.setFont(new Font("HelveticaNeue", Font.PLAIN, 18));
        b4.setFocusable(false);

        b5 = new JButton("History");
        b5.addActionListener(this);
        b5.setFont(new Font("HelveticaNeue", Font.PLAIN, 18));
        b5.setFocusable(false);

        b6 = new JButton("Delete Slang Word");
        b6.addActionListener(this);
        b6.setFont(new Font("HelveticaNeue", Font.PLAIN, 18));
        b6.setFocusable(false);

        b7 = new JButton("Reset Slang Word");
        b7.addActionListener(this);
        b7.setFont(new Font("HelveticaNeue", Font.PLAIN, 18));
        b7.setFocusable(false);

        b8 = new JButton("Quiz");
        b8.addActionListener(this);
        b8.setFont(new Font("HelveticaNeue", Font.PLAIN, 18));
        b8.setFocusable(false);

        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(Color.GREEN);
        panelCenter.setLayout(new GridLayout(0, 1, 10, 10));
        panelCenter.add(b1);
        panelCenter.add(b2);
        panelCenter.add(b3);
        panelCenter.add(b4);
        panelCenter.add(b5);
        panelCenter.add(b6);
        panelCenter.add(b7);
        panelCenter.add(b8);

        Dimension size2 = new Dimension(600, 400);
        panelCenter.setMaximumSize(size2);
        panelCenter.setPreferredSize(size2);
        panelCenter.setMinimumSize(size2);

        //JLabel imgLabel = new JLabel(new ImageIcon("Untitled.png"));
        //imgLabel.setPreferredSize(new Dimension(10, 5));
        //imgLabel.setAlignmentX(CENTER_ALIGNMENT);

        ImageIcon icon= new ImageIcon("image/slanglingo1.png");
        Image image =icon.getImage();
        Image newimg = image.getScaledInstance(1100, 699,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back
        setContentPane(new JLabel(icon));
        Container con = this.getContentPane();
        
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(label);
        con.add(Box.createRigidArea(new Dimension(0, 40)));
        con.add(panelCenter);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Slanglingo");
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == b1) {
            this.dispose();
            try {
                new ListSWFrame();
            } catch (Exception e1) {
               
                e1.printStackTrace();
            }
        } else if (e.getSource() == b2) {
            System.out.println("Change Actitity");
            this.dispose();
            try {
                new FindSWFrame();
            } catch (Exception e1) {
               
                e1.printStackTrace();
            }

        } else if (e.getSource() == b3) {
            
            this.dispose();
            new AddWordFrame();

        } else if (e.getSource() == b4) {
            this.dispose();
            new RandomFrame();

        } else if (e.getSource() == b5) {
            this.dispose();
            new HistoryFrame();

        } else if (e.getSource() == b6) {
            this.dispose();
            try {
                new DeleteFrame();
            } catch (Exception e1) {
                
                e1.printStackTrace();
            }
        } else if (e.getSource() == b7) {
            
                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("OptionPane.messagebackground", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);
                Icon icon1=new ImageIcon(new ImageIcon("image/6.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            int n = JOptionPane.showConfirmDialog(this, "Do you really want to reset Slang Word?", "",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,icon1);
            if (n == 0) {
                slangWord.reset();
                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("OptionPane.messagebackground", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);
                Icon icon=new ImageIcon(new ImageIcon("image/reset.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                JOptionPane.showMessageDialog(this, "Reset success.","",JOptionPane.YES_NO_OPTION,icon);
            }
        } else if (e.getSource() == b8) {
            this.dispose();
            new QuizFrame();
        }
    }

}
