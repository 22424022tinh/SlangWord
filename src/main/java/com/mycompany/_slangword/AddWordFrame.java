package com.mycompany._slangword;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class AddWordFrame extends JFrame implements ActionListener {

    Slang slangWord;
    JButton btnBack, btnAdd;
    JTextField textFieldMeaning, textFieldSlang;

    AddWordFrame() {

        slangWord = Slang.getInstance();
        Container con = this.getContentPane();
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Add Slang Words");
        titleLabel.setForeground(Color.green);
        titleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setPreferredSize(new Dimension(300, 100));

        
        JLabel jLabelObject = new JLabel();
        jLabelObject.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        jLabelObject.setIcon(new ImageIcon(new ImageIcon("image/slanglinggo.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
        jLabelObject.setAlignmentX(CENTER_ALIGNMENT);
        jLabelObject.setPreferredSize(new Dimension(200, 150));
        
        JPanel form = new JPanel();

        JPanel slagPanel = new JPanel();
        form.setBackground(Color.WHITE);
        SpringLayout layout = new SpringLayout();
        slagPanel.setLayout(layout);
        JLabel labelForSlang = new JLabel("Slang word: ");
        textFieldSlang = new JTextField("", 20);
        labelForSlang.setPreferredSize(new Dimension(100, 20));
        slagPanel.add(labelForSlang);
        slagPanel.add(textFieldSlang);
        layout.putConstraint(SpringLayout.WEST, labelForSlang, 6, SpringLayout.WEST, slagPanel);
        layout.putConstraint(SpringLayout.NORTH, labelForSlang, 6, SpringLayout.NORTH, slagPanel);
        layout.putConstraint(SpringLayout.WEST, textFieldSlang, 6, SpringLayout.EAST, labelForSlang);
        layout.putConstraint(SpringLayout.NORTH, textFieldSlang, 6, SpringLayout.NORTH, slagPanel);
        layout.putConstraint(SpringLayout.EAST, slagPanel, 6, SpringLayout.EAST, textFieldSlang);
        layout.putConstraint(SpringLayout.SOUTH, slagPanel, 6, SpringLayout.SOUTH, textFieldSlang);

        JPanel meaningPanel = new JPanel();
        SpringLayout layout1 = new SpringLayout();
        meaningPanel.setLayout(layout1);
        JLabel labelForMeaning = new JLabel("Meaning: ");
        labelForMeaning.setPreferredSize(new Dimension(100, 20));
        textFieldMeaning = new JTextField("", 20);
        meaningPanel.add(labelForMeaning);
        meaningPanel.add(textFieldMeaning);
        layout1.putConstraint(SpringLayout.WEST, labelForMeaning, 6, SpringLayout.WEST, meaningPanel);
        layout1.putConstraint(SpringLayout.NORTH, labelForMeaning, 6, SpringLayout.NORTH, meaningPanel);
        layout1.putConstraint(SpringLayout.WEST, textFieldMeaning, 6, SpringLayout.EAST, labelForMeaning);
        layout1.putConstraint(SpringLayout.NORTH, textFieldMeaning, 6, SpringLayout.NORTH, meaningPanel);
        layout1.putConstraint(SpringLayout.EAST, meaningPanel, 6, SpringLayout.EAST, textFieldMeaning);
        layout1.putConstraint(SpringLayout.SOUTH, meaningPanel, 6, SpringLayout.SOUTH, textFieldMeaning);

        JPanel pan = new JPanel();
        pan.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan.add(jLabelObject);
        pan.add(titleLabel);
        pan.setAlignmentX(CENTER_ALIGNMENT);
        pan.setBackground(Color.WHITE);
        
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        form.add(slagPanel);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        form.add(meaningPanel);

        JPanel bottomPanel = new JPanel();
        btnBack = new JButton("Back ");

        btnBack.setFocusable(false);
        btnBack.addActionListener(this);
        btnBack.setAlignmentX(CENTER_ALIGNMENT);
        btnAdd = new JButton("Add");

        btnAdd.setFocusable(false);
        btnAdd.addActionListener(this);
        btnAdd.setAlignmentX(CENTER_ALIGNMENT);
        bottomPanel.add(btnBack);
        bottomPanel.add(btnAdd);

        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(pan);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(form);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(bottomPanel);

        this.setTitle("Add Slang word");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnBack) {
            this.dispose();
            try {
                new MenuFrame();
            } catch (IOException ex) {
                Logger.getLogger(AddWordFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnAdd) {
            String slag = textFieldSlang.getText();
            String meaning = textFieldMeaning.getText();
            UIManager.put("OptionPane.background", Color.WHITE);
            UIManager.put("OptionPane.messagebackground", Color.WHITE);
            UIManager.put("Panel.background", Color.WHITE);
            Icon icon=new ImageIcon(new ImageIcon("image/1.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            Icon icon1=new ImageIcon(new ImageIcon("image/2.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            Icon icon2=new ImageIcon(new ImageIcon("image/3.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));

            if (slag.isEmpty() || meaning.isEmpty()) {
                // custom title, error icon
                JOptionPane.showMessageDialog(this, "Slag and Meaning maybe empty", "Inane error",
                        JOptionPane.ERROR_MESSAGE,icon);
                return;
            }
            System.out.println(slag + " = " + meaning);

            if (slangWord.checkSlang(slag)) {

                Object[] options = {"Overwrite", "Duplicate"};
                int n = JOptionPane.showOptionDialog(this,
                        "Slang `" + slag + "` have already exist on  SlangWord  List", "A Silly Question",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, null);
                if (n == 0) {

                    slangWord.addOverwrite(slag, meaning);
                    JOptionPane.showMessageDialog(this, "Overwrite Slang Word Success.","",JOptionPane.QUESTION_MESSAGE, icon1);
                } else if (n == 1) {

                    slangWord.addDuplicate(slag, meaning);
                    JOptionPane.showMessageDialog(this, "Dupilicate Slang Word Success.","",JOptionPane.QUESTION_MESSAGE, icon2);
                }
            } else {

                slangWord.addNew(slag, meaning);
                JOptionPane.showMessageDialog(this, "Adding new Slang Word Success.","",JOptionPane.QUESTION_MESSAGE, icon1);
            }
            textFieldSlang.setText("");
            textFieldMeaning.setText("");
        }
    }

}
