package sushigame.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import comp401sushi.*;
import sushigame.model.Belt;



public class PlateViewWidget extends JPanel implements ActionListener {
	private Belt belt;
	private int index;
	private JLabel slabel = new JLabel();

	private JPanel button_panel = new JPanel();

	public PlateViewWidget(Belt b, int index) {
		this.belt = b;
		this.index = index;
		this.setBorder(BorderFactory.createEtchedBorder());

		setLayout(new BorderLayout());

		if (belt.getPlateAtPosition(index) != null) {
			
			if (belt.getPlateAtPosition(index).getContents().getName().contains("Roll")) {
				String roll_ingredients_string = belt.getPlateAtPosition(index).getContents().getName();
				JButton detail_button = new JButton("Ingredients");
				detail_button.setActionCommand("ingredients");
				detail_button.addActionListener(this);
				button_panel.add(detail_button);
				
				slabel.setText(roll_ingredients_string);
				
			} else {
				slabel.setText(belt.getPlateAtPosition(index).getContents().getName());
			}
			slabel.setMinimumSize(new Dimension(1000, 50));
			slabel.setPreferredSize(new Dimension(1000, 50));
			slabel.setOpaque(true);
			switch (belt.getPlateAtPosition(index).getColor()) {
			case RED: 
				slabel.setBackground(Color.RED);
				slabel.setText(slabel.getText() + " on red plate");
				break;
			case GREEN: 
				slabel.setBackground(Color.GREEN);
				slabel.setText(slabel.getText() + " on green plate");
				break;
			case BLUE: 
				slabel.setBackground(Color.BLUE);
				slabel.setForeground(Color.WHITE);
				slabel.setText(slabel.getText() + " on blue plate");
				break;
			case GOLD: 
				slabel.setBackground(Color.YELLOW);
				slabel.setText(slabel.getText() + " on gold plate");
				break;
			}
			
			JButton detail_button = new JButton("Details");
			detail_button.setActionCommand("details");
			detail_button.addActionListener(this);
			button_panel.add(detail_button);
			
			add(slabel, BorderLayout.CENTER);
			add(button_panel, BorderLayout.WEST);
		} else {
			JLabel slabel = new JLabel();
			slabel.setText("null");
			slabel.setMinimumSize(new Dimension(1000, 50));
			slabel.setPreferredSize(new Dimension(1000, 50));
			add(slabel, BorderLayout.CENTER);
		}


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("details")) {
			slabel.setText(belt.getPlateAtPosition(index).getContents().getName() + " made by Chef " + 
		belt.getPlateAtPosition(index).getChef().getName() + " " + belt.getAgeOfPlateAtPosition(index) + " turns ago");
		} else if (cmd.equals("ingredients")) {
			String roll_ingredients_string = "";
			IngredientPortion[] roll_ingredients = belt.getPlateAtPosition(index).getContents().getIngredients();
			for (int i = 0; i < roll_ingredients.length; i++) {
				if (i == roll_ingredients.length - 1) {
					roll_ingredients_string += String.format("%.2f", roll_ingredients[i].getAmount()) + " " + roll_ingredients[i].getName();
				} else {
					roll_ingredients_string += String.format("%.2f", roll_ingredients[i].getAmount()) + " "  + roll_ingredients[i].getName() + ", ";
				}
			}
			slabel.setText(roll_ingredients_string);
			revalidate();
			
		}
	}

}
