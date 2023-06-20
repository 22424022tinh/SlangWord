package com.mycompany._slangword;

import java.awt.BorderLayout;
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
import java.awt.event.KeyEvent;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class FindSWFrame extends JFrame implements ActionListener, TableModelListener {
	JButton btnBack, btnFind;
	JTextField textField;
	JTable jt;
	JLabel titleLabel1;
	DefaultTableModel model;
	Slang slangword;
	String[][] result;
	final JOptionPane optionPane = new JOptionPane("The only way to close this dialog is by\n"
			+ "pressing one of the following buttons.\n" + "Do you understand?", JOptionPane.QUESTION_MESSAGE,
			JOptionPane.YES_NO_OPTION);
	String data[][] = { { "", "", "" } };

	FindSWFrame() throws Exception {
		Container con = this.getContentPane();
		slangword = Slang.getInstance();
		
		JLabel titleLabel = new JLabel();
		titleLabel.setText("Search Slang Words");
		titleLabel.setForeground(Color.green);
		titleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
                //
                JLabel jLabelObject = new JLabel();
                jLabelObject.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
                jLabelObject.setIcon(new ImageIcon(new ImageIcon("image/DuoDetective.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
                jLabelObject.setAlignmentX(CENTER_ALIGNMENT);
                jLabelObject.setPreferredSize(new Dimension(200, 150));
		// Result Label
		titleLabel1 = new JLabel();
		titleLabel1.setText("Enter slang word to search out meaning ");
		titleLabel1.setForeground(Color.black);
		titleLabel1.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
		titleLabel1.setAlignmentX(CENTER_ALIGNMENT);

		
		JPanel form = new JPanel();
		JLabel formLabel = new JLabel("Slang word");
                formLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
                formLabel.setForeground(Color.green);
		textField = new JTextField();
		btnFind = new JButton(new ImageIcon(((new ImageIcon(
            "image/search.png").getImage()
            .getScaledInstance(32, 32,
                    java.awt.Image.SCALE_SMOOTH)))));
                
		btnFind.addActionListener(this);
		btnFind.setMnemonic(KeyEvent.VK_ENTER);
		// SpringLayout layout = new SpringLayout();
		form.setLayout(new BorderLayout(10, 10));

		form.add(formLabel, BorderLayout.LINE_START);
		form.add(textField, BorderLayout.CENTER);
		form.add(btnFind, BorderLayout.LINE_END);
		Dimension size = new Dimension(700, 50);
		form.setMaximumSize(size);
		form.setPreferredSize(size);
		form.setMinimumSize(size);
		
		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.black);

		String column[] = { "STT", "Key", "Meaning" };

		jt = new JTable(new DefaultTableModel(column, 0));
		jt.setRowHeight(30);
		model = (DefaultTableModel) jt.getModel();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		jt.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jt.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		jt.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		jt.getModel().addTableModelListener(this);
		JScrollPane sp = new JScrollPane(jt);

		panelTable.setLayout(new GridLayout(1, 1));
		panelTable.add(sp);

	
		JPanel bottomPanel = new JPanel();
		btnBack = new JButton("Back ");
		//btnBack.addActionListener(this);
		btnBack.setFocusable(false);
		bottomPanel.add(btnBack);
		btnBack.addActionListener(this);
		btnBack.setAlignmentX(CENTER_ALIGNMENT);

                JPanel pan=new JPanel();
                pan.setLayout(new FlowLayout(FlowLayout.CENTER));
                pan.add(jLabelObject);
                pan.add(titleLabel);
                pan.setAlignmentX(CENTER_ALIGNMENT);
                pan.setBackground(Color.WHITE);
		
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
                con.add(pan);
		con.add(titleLabel1);
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(form);
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(panelTable);
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(btnBack);
		
		this.setTitle("Search Slang Words");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(700, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnFind) {
			String key = textField.getText();
			
			if (key.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please input slang word you want to find", "Inane error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Object[] options = { "Search by slangword", "Search by definition" };
                        UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("OptionPane.messagebackground", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);
                Icon icon=new ImageIcon(new ImageIcon("image/4.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
			int n = JOptionPane.showOptionDialog(this, "Choose mode " + "you want to excute?", "Choose mode find",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, null);
			String[][] temp = null;
			if (n == 0) {
				this.clearTable();
				//long startTime = System.currentTimeMillis();
				temp = slangword.getMeaning(key);
				//long endTime = System.currentTimeMillis();
				//long timeElapsed = endTime - startTime;+ String.valueOf(timeElapsed) + " ms"
				if (temp != null)
					titleLabel1.setText("Found "+ temp.length + " results"
							);
				else {
					titleLabel1.setText("Can't not find that slangWord");
					return;
				}
				result = temp;
				for (int i = 0; i < result.length; i++) {
					String ss[] = result[i];
					model.addRow(ss);
				}

			} else if (n == 1) {
				this.clearTable();
				long startTime = System.currentTimeMillis();
				temp = slangword.findDefinition(key);
				long endTime = System.currentTimeMillis();
				long timeElapsed = endTime - startTime;
				if (temp != null)
					titleLabel1.setText("Found " + temp.length + " results"
							);
				else {
					titleLabel1.setText("Can't not find that slangWord");
					return;
				}
				result = temp;
				for (int i = 0; i < result.length; i++) {
					String ss[] = result[i];
					model.addRow(ss);
				}
			}
			try {
				for (int ii = 0; ii < temp.length; ii++)
					slangword.saveHistory(temp[ii][1], temp[ii][2]);
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		} else if (e.getSource() == btnBack) {
			this.dispose();
                    try {
                        new MenuFrame();
                    } catch (IOException ex) {
                        Logger.getLogger(FindSWFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = jt.getSelectedRow();
		int col = jt.getSelectedColumn();
		if (row == col && row == -1)
			return;
		String Data = (String) jt.getValueAt(row, col);
		System.out.println("Table element selected is: " + row + col + " : " + Data);
		if (col == 2) {
			
			slangword.set((String) jt.getValueAt(row, 1), result[row][2], (String) jt.getValueAt(row, 2));
			JOptionPane.showMessageDialog(this, "Updated a row.");
		}
		jt.setFocusable(false);
	}

	void clearTable() {
		int rowCount = model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}
}
