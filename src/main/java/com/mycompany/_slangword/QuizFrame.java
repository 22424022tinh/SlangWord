package com.mycompany._slangword;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuizFrame extends JFrame implements ActionListener {
	JButton b1, b2, btnBack;

	QuizFrame() {
		JLabel label = new JLabel("Sanglingo Quizs");
		label.setForeground(Color.green);
		label.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setAlignmentY(-100);
		
                JLabel jLabelObject = new JLabel();
                jLabelObject.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
                jLabelObject.setIcon(new ImageIcon(new ImageIcon("image/quizs.png").getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT)));
                jLabelObject.setAlignmentX(CENTER_ALIGNMENT);
               
		b1 = new JButton("Find Definition");
                b1.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange, Color.red, Color.blue)); // Two Colors Outer Bevel
                b1.setBackground(Color.white);
		b1.addActionListener(this);
		b1.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
                
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(1, 2, 10, 10));
		panelCenter.add(b1);
                
		b2 = new JButton("Find SlangWord");
                b2.setBackground(Color.white);
                b2.setBorder(BorderFactory.createBevelBorder(0, Color.red, Color.orange, Color.MAGENTA, Color.blue)); // Two Colors Outer Bevel
		b2.addActionListener(this);
		b2.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
		panelCenter.add(b2);
		
                Dimension size2 = new Dimension(500, 90);
		panelCenter.setMaximumSize(size2);
		panelCenter.setPreferredSize(size2);
		panelCenter.setMinimumSize(size2);
                panelCenter.setBackground(Color.white);

		btnBack = new JButton("Back");
		btnBack.addActionListener(this);

		JPanel buttonPane = new JPanel();
		buttonPane.add(btnBack);
               
                JPanel pan=new JPanel();
                pan.setLayout(new FlowLayout(FlowLayout.CENTER));
                pan.add(jLabelObject);
                pan.add(label);
                pan.setAlignmentX(CENTER_ALIGNMENT);
                pan.setBackground(Color.WHITE);

		Container con = this.getContentPane();
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.add(Box.createRigidArea(new Dimension(0, 100)));
		con.add(pan);
		con.add(Box.createRigidArea(new Dimension(0, 100)));
		con.add(panelCenter);
		con.add(Box.createRigidArea(new Dimension(0, 100)));
		con.add(buttonPane);
                con.setBackground(Color.WHITE);
		this.setTitle("Quiz choose mode");
		this.setVisible(true);
		this.setSize(700, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == b1) {
			this.dispose();
			new QuestionFrame(1);
		} else if (e.getSource() == b2) {
			this.dispose();
			new QuestionFrame(2);
		} else if (e.getSource() == btnBack) {
			this.dispose();
                    try {
                        new MenuFrame();
                    } catch (IOException ex) {
                        Logger.getLogger(QuizFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
	}
}