package com.mycompany._slangword;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
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

public class RandomFrame extends JFrame implements ActionListener {
	JButton btnBack, btnReset;
	Slang slangWord = Slang.getInstance();
	JLabel lb2, lb4, lb6;

	RandomFrame() {
		Container con = this.getContentPane();

		JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel();
                titleLabel.setIcon(new ImageIcon(new ImageIcon("image/tittle.png").getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT)));
		titleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
		titlePanel.add(titleLabel);
		titlePanel.setBackground(Color.white);
		titleLabel.setForeground(Color.white);
		titlePanel.setMaximumSize(new Dimension(700, 300));

		String s[] = slangWord.random();
		JPanel slangPanel = new JPanel();
		JLabel lb1 = new JLabel("Slang: \t");
                lb6 =new JLabel();
                lb6.setIcon(new ImageIcon(new ImageIcon("image/1.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		lb6.setAlignmentX(LEFT_ALIGNMENT);
                lb2 = new JLabel(s[0]);
		JLabel lb3 = new JLabel("\tMeaning: \t");
		lb4 = new JLabel(s[1]);
		lb2.setForeground(Color.green);
		lb4.setForeground(Color.red);
		lb1.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
		lb2.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
		lb3.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
		lb4.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
		slangPanel.add(lb6,BorderLayout.PAGE_START);
                slangPanel.add(lb1);
		slangPanel.add(lb2);
		slangPanel.add(lb3);
		slangPanel.add(lb4);
                slangPanel.setBackground(Color.WHITE);

		btnReset = new JButton("Reset");
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnReset.addActionListener(this);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(btnReset);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(btnBack);

		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.add(titlePanel);
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(slangPanel);
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(buttonPane);
                con.setBackground(Color.WHITE);
		
		this.setTitle("Ramdom Slangwords");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(700, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnBack) {
			this.dispose();
                    try {
                        new MenuFrame();
                    } catch (IOException ex) {
                        Logger.getLogger(RandomFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
		} else if (e.getSource() == btnReset) {
			String s[] = slangWord.random();
			lb2.setText(s[0]);
			lb4.setText(s[1]);
                        int i = (int)(Math.random()*(8-1+1)+1);
                        lb6.setIcon(new ImageIcon(new ImageIcon("image/" +i+".png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        
		}
	}

}
