package com.mycompany._slangword;

import java.awt.Color;
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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;

public class ListSWFrame extends JFrame implements ActionListener, TableModelListener {

    JButton btnBack;
    JTable jt;
    Slang slangWord;
    String dataCopy[][];

    public ListSWFrame() throws Exception {
        Container con = this.getContentPane();
        slangWord = Slang.getInstance();

        JLabel titleLabel = new JLabel();
        titleLabel.setText("List Words");
        titleLabel.setForeground(Color.green);
        titleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
    
        JLabel jLabelObject = new JLabel();
        JLabel jLabelObject1 = new JLabel();
        jLabelObject1.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        jLabelObject1.setText("                    ");
        jLabelObject1.setAlignmentX(CENTER_ALIGNMENT);
        jLabelObject.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        jLabelObject.setIcon(new ImageIcon("image/slanglingo.png"));
        jLabelObject.setAlignmentX(CENTER_ALIGNMENT);
        // Label
        JLabel resultLabel = new JLabel();
        resultLabel.setForeground(Color.black);
        resultLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        resultLabel.setAlignmentX(CENTER_ALIGNMENT);
        // resultLabel.setBackground(Color.blue);
        // resultLabel.setOpaque(true);
        //ImageIcon icon= new ImageIcon("slanglingo1.png");
        //Image image =icon.getImage();
        //Image newimg = image.getScaledInstance(1100, 699,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        //icon = new ImageIcon(newimg); 
        // transform it back
        // List Slang Words
        JPanel panelTable = new JPanel();
        panelTable.setBackground(Color.GREEN);
        String data[][] = slangWord.getData();
        dataCopy = slangWord.getData();
        String column[] = {"STT", "Key", "Meaning"};
        resultLabel.setText("Slanglingo " + data.length + " words");
        jt = new JTable(data, column);
        jt.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jt.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jt.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jt.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        jt.getModel().addTableModelListener(this);

        JScrollPane sp = new JScrollPane(jt);
        panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.X_AXIS));
        panelTable.add(sp);

        JPanel bottomPanel = new JPanel();
        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
        btnBack.setFocusable(false);
        btnBack.setAlignmentX(CENTER_ALIGNMENT);
        bottomPanel.add(btnBack);

        JPanel pan = new JPanel();
        pan.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan.add(jLabelObject);
        pan.add(titleLabel);
        pan.add(jLabelObject1);
        pan.setAlignmentX(CENTER_ALIGNMENT);
        pan.setBackground(Color.WHITE);

        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(pan);
        con.add(Box.createRigidArea(new Dimension(0, 20)));
        con.add(resultLabel);
        con.add(Box.createRigidArea(new Dimension(0, 20)));
        con.add(panelTable);
        con.add(Box.createRigidArea(new Dimension(0, 20)));
        con.add(bottomPanel);
        con.setBackground(Color.WHITE);
        // Setting JFrame
        this.setTitle("List Words");
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
                Logger.getLogger(ListSWFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    public void tableChanged(TableModelEvent e) {
        int row = jt.getSelectedRow();
        int col = jt.getSelectedColumn();
        if (row == -1 || col == -1) {
            return;
        }
        String Data = (String) jt.getValueAt(row, col);
        if (col == 2) {
            System.out.println("Slangword is edited: \t" + row + "\t" + dataCopy[row][2]);
            slangWord.set((String) jt.getValueAt(row, 1), dataCopy[row][2], (String) jt.getValueAt(row, 2));
             UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("OptionPane.messagebackground", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);
                Icon icon=new ImageIcon(new ImageIcon("image/reset.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                JOptionPane.showMessageDialog(this, "Updated success.","",JOptionPane.YES_OPTION,icon);
        }
    }
}
