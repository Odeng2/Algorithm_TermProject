package kr.ac.gachon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Screen {
	private int width, height, space;
	private int dialogWidth, dialogHeight;
	private String stockName;
	
	public Screen() {
		width = 500;
		height = 800;
		space = 25;
		dialogWidth = 200;
		dialogHeight = 200;
		
		JFrame frame = new JFrame("Stock Profit Calculator");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		GUIPanel panel = new GUIPanel();
		
		JPanel textPanel = new JPanel();
		JTextArea textArea = new JTextArea();
		JPanel controlPanel = new JPanel();
		JButton clear = new JButton("Clear");
		JButton calculate = new JButton("Calculate");
		JButton rename = new JButton("Rename Stock");
		
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				textArea.requestFocus();
				panel.clearGraph();
				panel.repaint();
			}
		});
		
		calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textArea.getText().equals("")) {
					if (!textArea.getText().contains("\n")) {
						panel.getStockPriceErrorDialog();
					} else {
						Stock stock = new Stock(stockName);
						String[] array = textArea.getText().split("\n");
						
						for (String record : array) stock.addTradingRecords(record);
						
						ArrayList<String> result = stock.calculateProfit();
						
						if (result == null) {
							textArea.append("\n");
							textArea.append("====================\n");
							textArea.append("No trading records found for potential profit in " + stockName + "\n");
							textArea.append("====================\n");
						} else {
							textArea.append("\n");
							textArea.append("====================\n");
							textArea.append("Stock Name: " + stockName + "\n");
							textArea.append("Buy Price: " + result.get(0) + " at " + result.get(1) + "\n");
							textArea.append("Sell Price: " + result.get(2) + " at " + result.get(3) + "\n");
							textArea.append("Max Profit: " + result.get(4) + "\n");
							textArea.append("====================");
							
							ArrayList<Double> prices = stock.getPrices();
							
							panel.clearGraph();
							panel.setGraph(new Graph(prices));
							panel.repaint();
						}
					}
				} else panel.getStockPriceErrorDialog();
			}
		});
		
		rename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getStockName();
			}
		});
		
		textPanel.add(textArea);
		textPanel.setBackground(Color.WHITE);
		controlPanel.add(clear);
		controlPanel.add(calculate);
		controlPanel.add(rename);

		textPanel.setBounds(space, 350, width - (space * 2 + space / 2), height - 350 - (space * 4));
		controlPanel.setBounds(space, height - (space * 3 + space / 2), width - space * 2, space * 2 + space / 2);
		
		frame.add(panel);
		frame.add(textPanel);
		frame.add(controlPanel);
		frame.setVisible(true);
	}
	
	public void getStockName() {
		JDialog dialog = new JDialog();
		JLabel label = new JLabel("Enter Stock Name");
		JTextField textField = new JTextField(15);
		JButton button = new JButton("Enter");
		
		dialog.setTitle("Need User Input");
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setSize(dialogWidth, dialogHeight);
		dialog.setLocationRelativeTo(null);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) getStockNameErrorDialog();
				else {
					stockName = textField.getText();
					
					dialog.dispose();
				}
			}
		});
		
		dialog.getContentPane().add(BorderLayout.NORTH, label);
		dialog.getContentPane().add(BorderLayout.CENTER, textField);
		dialog.getContentPane().add(BorderLayout.SOUTH, button);
		dialog.setVisible(true);
	}
	
	public void getStockNameErrorDialog() {
		JDialog dialog = new JDialog();
		JLabel label = new JLabel("Please enter the stock name.");
		JButton button = new JButton("OK");
		
		dialog.setTitle("Error Occurred");
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setSize(dialogWidth, dialogHeight);
		dialog.setLocationRelativeTo(null);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		dialog.getContentPane().add(BorderLayout.CENTER, label);
		dialog.getContentPane().add(BorderLayout.SOUTH, button);
		dialog.setVisible(true);
	}
}
