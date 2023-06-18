/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javalang;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author khang
 */
public class NewClass {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel statusLabel;
    private JPanel controlPanel;
    String b1="b1", b2="b2", b3="b3", b4, b5, b6, b7, b8;
    SlangWord slangWord;
    public NewClass() {
        prepareGUI();
    }
 
//    public static void main(String[] args) {
//        NewClass demo = new NewClass();
//        demo.showMenuDemo();
//    }
 
    private void prepareGUI() {
        mainFrame = new JFrame("Slang word");
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new GridLayout(3, 1));
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JPanel();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }
 
    private void showMenuDemo() {
        // Tao mot menu bar
        final JMenuBar menuBar = new JMenuBar(); // Tao cac menu
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        final JMenu aboutMenu = new JMenu("About");
        final JMenu linkMenu = new JMenu("Links");
 
        // tao cac item
        JMenuItem newMenuItem = new JMenuItem("b1");
        newMenuItem.setMnemonic(KeyEvent.VK_N);
        newMenuItem.setActionCommand("b1");
        
        JMenuItem openMenuItem = new JMenuItem("b2");
        openMenuItem.setActionCommand("b2");
        
        JMenuItem saveMenuItem = new JMenuItem("b3");
        saveMenuItem.setActionCommand("b3");
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setActionCommand("Exit");
        
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.setActionCommand("Cut");
        
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.setActionCommand("Copy");
        
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.setActionCommand("Paste");
        
        MenuItemListener menuItemListener = new MenuItemListener();
        newMenuItem.addActionListener((ActionListener) menuItemListener);
        openMenuItem.addActionListener(menuItemListener);
        saveMenuItem.addActionListener(menuItemListener);
        exitMenuItem.addActionListener(menuItemListener);
        cutMenuItem.addActionListener(menuItemListener);
        copyMenuItem.addActionListener(menuItemListener);
        pasteMenuItem.addActionListener(menuItemListener);
        final JCheckBoxMenuItem showWindowMenu = new JCheckBoxMenuItem("Show About", true);
        showWindowMenu.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (showWindowMenu.getState()) {
                    menuBar.add(aboutMenu);
                } else {
                    menuBar.remove(aboutMenu);
                }
            }
        });
        final JRadioButtonMenuItem showLinksMenu = new JRadioButtonMenuItem("Show Links", true);
        showLinksMenu.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (menuBar.getMenu(3) != null) {
                    menuBar.remove(linkMenu);
                    mainFrame.repaint();
                } else {
                    menuBar.add(linkMenu);
                    mainFrame.repaint();
                }
            }
        }); // Them item toi cac menu
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(showWindowMenu);
        fileMenu.addSeparator();
        fileMenu.add(showLinksMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem); // Them menu toi menubar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(aboutMenu);
        menuBar.add(linkMenu); // Them menubar toi frame
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setVisible(true);
    }
 
    class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if ( e.getActionCommand().equals(b1)) {
			//this.dispose();
			try {
		JButton btnBack;
                JTable jt;
                SlangWord slangWord;
                String dataCopy[][];
		slangWord = SlangWord.getInstance();

		// Label
		JLabel titleLabel = new JLabel();
		titleLabel.setText("List Slang Words");
		titleLabel.setForeground(Color.green);
		titleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		// titleLabel.setBackground(Color.black);
		// titleLabel.setOpaque(true);

		// Label
		JLabel resultLabel = new JLabel();
		resultLabel.setForeground(Color.black);
		resultLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
		resultLabel.setAlignmentX(CENTER_ALIGNMENT);
		// resultLabel.setBackground(Color.blue);
		// resultLabel.setOpaque(true);

		// List Slang Words
		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.black);
		String data[][] = slangWord.getData();
		dataCopy = slangWord.getData();
		String column[] = { "STT", "Slag", "Meaning" };
		resultLabel.setText("We have " + data.length + " slang words");
		jt = new JTable(data, column);
		jt.setRowHeight(30);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		jt.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jt.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		jt.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		// jt.putClientProperty("terminateEditOnFocusLost", true);
		jt.getModel().addTableModelListener((TableModelListener) this);

		JScrollPane sp = new JScrollPane(jt);
		panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.X_AXIS));
		panelTable.add(sp);

		// Button Back
		JPanel bottomPanel = new JPanel();
//		btnBack = new JButton("Back ");
//		btnBack.addActionListener(this);
//		btnBack.setFocusable(false);
//		btnBack.setAlignmentX(CENTER_ALIGNMENT);
//		bottomPanel.add(btnBack);

		// Add to con
		statusLabel.setLayout(new BoxLayout(statusLabel, BoxLayout.Y_AXIS));
		statusLabel.add(Box.createRigidArea(new Dimension(0, 10)));
		statusLabel.add(titleLabel);
		statusLabel.add(Box.createRigidArea(new Dimension(0, 20)));
		statusLabel.add(resultLabel);
		statusLabel.add(Box.createRigidArea(new Dimension(0, 20)));
		statusLabel.add(panelTable);
		statusLabel.add(Box.createRigidArea(new Dimension(0, 20)));
		statusLabel.add(bottomPanel);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals(b2)) {
			System.out.println("Change Actitity");
			//this.dispose();
			try {
				new FindSWFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getActionCommand().equals(b3)){
			// Add a slang word
			//this.dispose();
			new AddWordFrame();

		} else if (e.getSource() == b4) {
			//this.dispose();
			//new RandomFrame();

		} else if (e.getSource() == b5) {
			//this.dispose();
			//new HistoryFrame();

		} else if (e.getSource() == b6) {
			//this.dispose();
			try {
				//new DeleteFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
        }
    }
}

