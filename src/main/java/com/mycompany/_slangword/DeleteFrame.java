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
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DeleteFrame extends JFrame implements ActionListener, ListSelectionListener {
	JButton btnBack;
	JTable jt;
	Slang slangWord;
	DefaultTableModel model;
	String data[][];
        
	public DeleteFrame() throws Exception {
		Container con = this.getContentPane();
		slangWord = Slang.getInstance();

		// Label
		JLabel titleLabel = new JLabel();
		titleLabel.setText("Choose a Slang Word you want to delete");
		titleLabel.setForeground(Color.green);
		titleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		// titleLabel.setBackground(Color.black);
		// titleLabel.setOpaque(true);
                //
                //
                JLabel jLabelObject = new JLabel();
                jLabelObject.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
                jLabelObject.setIcon(new ImageIcon(new ImageIcon("image/duo200.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
                jLabelObject.setAlignmentX(CENTER_ALIGNMENT);
                jLabelObject.setPreferredSize(new Dimension(200, 150));
		// Label
		JLabel resultLabel = new JLabel();
		resultLabel.setForeground(Color.black);
		resultLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
		resultLabel.setAlignmentX(CENTER_ALIGNMENT);
		// resultLabel.setBackground(Color.blue);
		// resultLabel.setOpaque(true);

		// List Slang Words
		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.black);
		data = slangWord.getData();
		String column[] = { "STT", "Key", "Meaning" };
		resultLabel.setText("Sanglingo "+data.length+" slang words");
                resultLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
		jt = new JTable(new DefaultTableModel(column, 0));
		model = (DefaultTableModel) jt.getModel();
		jt.setRowHeight(30);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		jt.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jt.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		jt.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		ListSelectionModel selectionModel = jt.getSelectionModel();

		selectionModel.addListSelectionListener(this);
		JScrollPane sp = new JScrollPane(jt);
		panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.X_AXIS));
		panelTable.add(sp);

                //
                
		// Button Back
		JPanel bottomPanel = new JPanel();
		btnBack = new JButton("Back ");
		btnBack.addActionListener(this);
		btnBack.setFocusable(false);
		btnBack.setAlignmentX(CENTER_ALIGNMENT);
		bottomPanel.add(btnBack);

                //
                JPanel pan=new JPanel();
                pan.setLayout(new FlowLayout(FlowLayout.CENTER));
                pan.add(jLabelObject);
                pan.add(titleLabel);
                pan.setAlignmentX(CENTER_ALIGNMENT);
                pan.setBackground(Color.WHITE);
		// Add to con
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		
		con.add(pan);
		
		con.add(resultLabel);
		con.add(Box.createRigidArea(new Dimension(0, 20)));
		con.add(panelTable);
		con.add(Box.createRigidArea(new Dimension(0, 20)));
		con.add(bottomPanel);
		// Setting JFrame
		this.setTitle("List Slang Words");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(700, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		addRow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnBack) {
			this.dispose();
                    try {
                        new MenuFrame();
                    } catch (IOException ex) {
                        Logger.getLogger(DeleteFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
	}

	public void addRow() {
		int size = data.length;
		for (int i = 0; i < size; i++) {
			String ss[] = data[i];
			model.addRow(ss);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		int row = jt.getSelectedRow();
		int col = jt.getSelectedColumn();
		if (row == -1 || col == -1)
			return;
		String Data = (String) jt.getValueAt(row, 1);

		System.out.println("Table element selected is: " + Data);
                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("OptionPane.messagebackground", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);
                Icon icon=new ImageIcon(new ImageIcon("image/mark.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		int n = JOptionPane.showConfirmDialog(this, "Would you like to delete this slang word?", "Select an option",
				JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,icon);
                
		if (n == 0) {
			slangWord.delete(Data, (String) jt.getValueAt(row, 2));
			// default title and icon
			model.removeRow(row);
			JOptionPane.showMessageDialog(this, "Deleted success","",JOptionPane.YES_NO_OPTION,icon);

		}
	}
}
