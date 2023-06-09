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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class QuestionFrame extends JFrame implements ActionListener {
	JButton b1, b2, b3, b4, btnBack;
	// final int type;
	String s[];

	QuestionFrame(int type) {
		s = (Slang.getInstance()).quiz(type);
		JLabel label = new JLabel("Find out the correct answer");
		label.setForeground(Color.green);
		label.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setAlignmentY(-100);
		JLabel questionLabel;
		if (type == 1)
			questionLabel = new JLabel("Slang " + "`" + s[0] + "`" + " mean to be?");
		else
			questionLabel = new JLabel("`" + s[0] + "`" + " have slang word is?");
		questionLabel.setForeground(Color.black);
		questionLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
		questionLabel.setAlignmentX(CENTER_ALIGNMENT);
		questionLabel.setAlignmentY(-100);

		b1 = new JButton("A." + s[1]);
		b1.addActionListener(this);
		b1.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		b2 = new JButton("B." + s[2]);
		b2.addActionListener(this);
		b2.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		b3 = new JButton("C." + s[3]);
		b3.addActionListener(this);
		b3.setFont(new Font("Gill Sans MTg", Font.PLAIN, 14));
		b4 = new JButton("D. " + s[4]);
		b4.addActionListener(this);
		b4.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
                
                JLabel jLabelObject = new JLabel();
                jLabelObject.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
                jLabelObject.setIcon(new ImageIcon(new ImageIcon("image/DuoDetective.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
                jLabelObject.setAlignmentX(CENTER_ALIGNMENT);
                jLabelObject.setPreferredSize(new Dimension(200, 150));
                
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(2, 2, 10, 10));
		panelCenter.add(b1);
		panelCenter.add(b2);
		panelCenter.add(b3);
		panelCenter.add(b4);
		Dimension size2 = new Dimension(600, 200);
		panelCenter.setMaximumSize(size2);
		panelCenter.setPreferredSize(size2);
		panelCenter.setMinimumSize(size2);
                //
             
                JPanel pan=new JPanel();
                pan.setLayout(new FlowLayout(FlowLayout.CENTER));
                pan.add(jLabelObject);
                pan.add(label);
                pan.setAlignmentX(CENTER_ALIGNMENT);
                pan.setBackground(Color.WHITE);
                //
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);

		JPanel buttonPane = new JPanel();
		buttonPane.add(btnBack);

		Container con = this.getContentPane();
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.add(pan);
		con.add(questionLabel);
		con.add(Box.createRigidArea(new Dimension(0, 50)));
		con.add(panelCenter);
		con.add(Box.createRigidArea(new Dimension(0, 50)));
		con.add(buttonPane);
                con.setBackground(Color.WHITE);
		this.setTitle("Slanglingo Quiz");
		this.setVisible(true);
		this.setSize(700, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == b1) {
			this.answer(1);
		} else if (e.getSource() == b2) {
			this.answer(2);
		} else if (e.getSource() == b3) {
			this.answer(3);
		} else if (e.getSource() == b4) {
			this.answer(4);
		} else if (e.getSource() == btnBack) {
			this.dispose();
			new QuizFrame();
		}
	}
	public void answer(int ans) {
                Icon icon=new ImageIcon(new ImageIcon("image/mark.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
                Icon icon2=new ImageIcon(new ImageIcon("image/correct.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));

                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("OptionPane.messagebackground", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);
		if (s[ans].equals(s[5])) {
			// default title and icon
			JOptionPane.showMessageDialog(this, "Corect Answer","Corect Answer",JOptionPane.ERROR_MESSAGE,icon2);

		} else {
			JOptionPane.showMessageDialog(this, "Wrong Answer", "Wrong Answer", JOptionPane.ERROR_MESSAGE,icon);
			if (ans == 1)
				b1.setBackground(Color.red);
			else if (ans == 2)
				b2.setBackground(Color.red);
			else if (ans == 3)
				b3.setBackground(Color.red);
			else if (ans == 4)
				b4.setBackground(Color.red);
		}
		if (s[1].equals(s[5])) {
			b1.setEnabled(false);
			b1.setBackground(Color.green);
		} else {
			b1.setEnabled(false);
		}
		if (s[2].equals(s[5])) {
			b2.setEnabled(false);
			b2.setBackground(Color.green);
		} else {
			b2.setEnabled(false);
		}
		if (s[3].equals(s[5])) {
			b3.setEnabled(false);
			b3.setBackground(Color.green);
		} else {
			b3.setEnabled(false);
		}
		if (s[4].equals(s[5])) {
			b4.setEnabled(false);
			b4.setBackground(Color.green);
		} else {
			b4.setEnabled(false);
		}
	}
}