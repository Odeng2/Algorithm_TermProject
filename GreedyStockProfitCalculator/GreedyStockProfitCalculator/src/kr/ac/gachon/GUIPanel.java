package kr.ac.gachon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int width, height, locationX, locationY, squareX, squareY, space;
	private int dialogWidth, dialogHeight;
	private Graph graph;
	
	public GUIPanel() {
		width = 500;
		height = 350;
		locationX = 0;
		locationY = 0;
		squareX = 450;
		squareY = 300;
		space = 25;
		dialogWidth = 200;
		dialogHeight = 200;
		graph = null;
		
		setBounds(locationX, locationY, width, height);
	}
	
	public void getStockPriceErrorDialog() {
		JDialog dialog = new JDialog();
		JLabel label = new JLabel("Please enter the stock price.");
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
	
	public void getDenialDialog() {
		JDialog dialog = new JDialog();
		JLabel label = new JLabel("Please clear the text area.");
		JButton button = new JButton("OK");
		
		dialog.setTitle("Access Denied");
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
	
	public void setGraph(Graph newGraph) {
		graph = newGraph;
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		drawBox(graphics);
		drawGraph(graphics);
	}
	
	public void clearGraph() {
		graph = null;
	}
	
	private void drawBox(Graphics graphics) {
		// edge lines
		graphics.drawLine(space, space - 1, squareX + space / 2 - 2, space - 1);
		graphics.drawLine(space, space, squareX + space / 2 - 2, space);
		graphics.drawLine(space, space + 1, squareX + space / 2 - 2, space + 1);
		graphics.drawLine(space - 1, space, space - 1, squareY + space);
		graphics.drawLine(space, space, space, squareY + space);
		graphics.drawLine(space + 1, space, space + 1, squareY + space);
		graphics.drawLine(space, squareY + space - 1, squareX + space / 2 - 2, squareY + space - 1);
		graphics.drawLine(space, squareY + space, squareX + space / 2 - 2, squareY + space);
		graphics.drawLine(space, squareY + space + 1, squareX + space / 2 - 2, squareY + space + 1);
		graphics.drawLine(squareX + space / 2 - 3, space, squareX + space / 2 - 3, squareY + space);
		graphics.drawLine(squareX + space / 2 - 2, space, squareX + space / 2 - 2, squareY + space);
		graphics.drawLine(squareX + space / 2 - 1, space, squareX + space / 2 - 1, squareY + space);
		
		// x axis, y axis
		graphics.drawLine(space * 2, squareY - 1, squareX - space / 2, squareY - 1);
		graphics.drawLine(space * 2, squareY, squareX - space / 2, squareY);
		graphics.drawLine(space * 2, squareY + 1, squareX - space / 2, squareY + 1);
		graphics.drawLine(space * 2 - 1, squareY, space * 2 - 1, space * 2);
		graphics.drawLine(space * 2, squareY, space * 2, space * 2);
		graphics.drawLine(space * 2 + 1, squareY, space * 2 + 1, space * 2);
		graphics.setColor(Color.YELLOW);
		graphics.drawLine(space * 2, ((squareY + (space * 2 + space / 2)) / 2), squareX - (space / 2), ((squareY + (space * 2 + space / 2)) / 2));
		graphics.drawLine(space * 2, space * 2, squareX - (space / 2), space * 2);
		graphics.setColor(Color.BLACK);
		
		// x axis arrow, y axis arrow
		graphics.drawLine(squareX - space / 2, squareY - 1, squareX - 18, squareY - 10);
		graphics.drawLine(squareX - space / 2, squareY, squareX - 18, squareY - 9);
		graphics.drawLine(squareX - space / 2, squareY + 1, squareX - 18, squareY - 8);
		graphics.drawLine(squareX - space / 2, squareY - 1, squareX - 18, squareY + 8);
		graphics.drawLine(squareX - space / 2, squareY, squareX - 18, squareY + 9);
		graphics.drawLine(squareX - space / 2, squareY + 1, squareX - 18, squareY + 10);
		graphics.drawLine(space * 2 -1, space * 2, space * 2 + 8, space * 2 + 9);
		graphics.drawLine(space * 2, space * 2, space * 2 + 9, space * 2 + 9);
		graphics.drawLine(space * 2 + 1, space * 2, space * 2 + 10, space * 2 + 9);
		graphics.drawLine(space * 2 - 1, space * 2, space * 2 - 10, space * 2 + 9);
		graphics.drawLine(space * 2, space * 2, space * 2 - 9, space * 2 + 9);
		graphics.drawLine(space * 2 + 1, space * 2, space * 2 - 8, space * 2 + 9);
		
		// 0%, 50%, 100%, Days, Price
		graphics.drawString("0%", space + space / 2, squareY + space / 2 + 2);
		graphics.drawString("50%", space + 1, (squareY + (space * 2 + space / 2)) / 2);
		graphics.drawString("100%", space + 2, space * 2 - 2);
		graphics.drawString("Days", squareX - (space * 2 + space / 2), squareY + space / 2 + 2);
		graphics.drawString("P", space + space / 2, space * 3 + space / 2);
		graphics.drawString("r", space + space / 2 + 1, space * 4);
		graphics.drawString("i", space + space / 2 + 1, space * 4 + space / 2);
		graphics.drawString("c", space + space / 2 - 1, space * 5);
		graphics.drawString("e", space + space / 2 - 1, space * 5 + space / 2);
	}
	
	private void drawGraph(Graphics graphics) {
		if (graph == null) return;
		
		int zoom = squareX / (graph.getListNum() + 2);
		
		int x1 = space * 2 + zoom;
		double y1 = graph.getPercent(0);
		
		for(int i = 1; i < graph.getListNum(); i++) {
			int x2 = space * 2 + (zoom * (i + 1));
			double y2 = graph.getPercent(i);
			
			if (y2 > y1) graphics.setColor(Color.RED);
			else if (y2 < y1) graphics.setColor(Color.BLUE);
			else graphics.setColor(Color.BLACK);
			
			graphics.drawLine(x1, (int) ((squareY + space * 2) - ((squareY + space * 2) * (y1 / 100))) - 1, 
					x2, (int) ((squareY + space * 2) - ((squareY + space * 2) * (y2 / 100))) - 1);
			graphics.drawLine(x1, (int) ((squareY + space * 2) - ((squareY + space * 2) * (y1 / 100))), 
					x2, (int) ((squareY + space * 2) - ((squareY + space * 2) * (y2 / 100))));
			graphics.drawLine(x1, (int) ((squareY + space * 2) - ((squareY + space * 2) * (y1 / 100))) + 1, 
					x2, (int) ((squareY + space * 2) - ((squareY + space * 2) * (y2 / 100))) + 1);
			
			x1 = x2;
			y1 = y2;
		}
	}
}
