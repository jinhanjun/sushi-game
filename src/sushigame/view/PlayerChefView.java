package sushigame.view;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import comp401sushi.AvocadoPortion;
import comp401sushi.CrabPortion;
import comp401sushi.EelPortion;
import comp401sushi.IngredientPortion;
import comp401sushi.Nigiri;
import comp401sushi.Plate;
import comp401sushi.RedPlate;
import comp401sushi.RicePortion;
import comp401sushi.Roll;
import comp401sushi.Sashimi;
import comp401sushi.SeaweedPortion;
import comp401sushi.ShrimpPortion;
import comp401sushi.Sushi;
import comp401sushi.TunaPortion;
import comp401sushi.YellowtailPortion;

public class PlayerChefView extends JPanel implements ActionListener {

	private List<ChefViewListener> listeners;
	private Sushi custom_sushi;
	private int belt_size;
	private JTextField plate_field;
	private JTextField plate_index_field;
	private JTextField sushi_type_field;
	private JTextField sashimi_type_field;
	private JTextField nigiri_type_field;
	private JTextField roll_name_field;
	private JTextField avocado;
	private JTextField seaweed;
	private JTextField rice;
	private JTextField crab;
	private JTextField shrimp;
	private JTextField tuna;
	private JTextField eel;
	private JTextField yellowtail;
	private JTextField gold_price;
	private IngredientPortion[] firstIngArray;
	private IngredientPortion[] finalIngArray;
	private int portion_counter;
	private JLabel error_label = new JLabel();
	private JPanel intro_panel = new JPanel();
	private JPanel sushi_panel = new JPanel();
	private JPanel ingr_panel = new JPanel();
	private JPanel whole_panel = new JPanel();

	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		whole_panel.setMaximumSize(new Dimension(700, 600));
		whole_panel.setPreferredSize(new Dimension(700, 500));
		whole_panel.setLayout(new BoxLayout(whole_panel, BoxLayout.Y_AXIS));

		plate_field = new JTextField(5);
		plate_field.setMaximumSize(new Dimension(150, 50));
		intro_panel.add(new JLabel("Plate Color: "), BorderLayout.WEST);
		intro_panel.add(plate_field, BorderLayout.EAST);

		plate_index_field = new JTextField(5);
		plate_index_field.setMaximumSize(new Dimension(100, 50));
		intro_panel.add(new JLabel("Plate location: "), BorderLayout.WEST);
		intro_panel.add(plate_index_field, BorderLayout.EAST);

		sushi_type_field = new JTextField(5);
		sushi_type_field.setMaximumSize(new Dimension(150, 50));
		intro_panel.add(new JLabel("Sushi Type: Sashimi, Nigiri, Roll "));
		intro_panel.add(sushi_type_field);

		whole_panel.add(intro_panel);

		sashimi_type_field = new JTextField(5);
		sashimi_type_field.setMaximumSize(new Dimension(150, 50));
		sushi_panel.add(new JLabel("Sashimi Type: "), BorderLayout.WEST);
		sushi_panel.add(sashimi_type_field, BorderLayout.WEST);

		nigiri_type_field = new JTextField(5);
		nigiri_type_field.setMaximumSize(new Dimension(150, 50));
		sushi_panel.add(new JLabel("Nigiri Type:"), BorderLayout.SOUTH);
		sushi_panel.add(nigiri_type_field, BorderLayout.SOUTH);

		roll_name_field = new JTextField(5);
		roll_name_field.setMaximumSize(new Dimension(150, 50));
		sushi_panel.add(new JLabel("Roll name (ex: KMP Roll):  "), BorderLayout.EAST);
		sushi_panel.add(roll_name_field, BorderLayout.EAST);

		whole_panel.add(sushi_panel);

		ingr_panel.setLayout(new BoxLayout(ingr_panel, BoxLayout.Y_AXIS));

		JLabel roll_instruct_label = new JLabel();
		roll_instruct_label.setText("Choose Amounts (cannot excede 1.5): ");
		ingr_panel.add(roll_instruct_label, BorderLayout.WEST);

		avocado = new JTextField(5);
		avocado.setMaximumSize(new Dimension(100, 50));
		ingr_panel.add(new JLabel("Avocado: "), BorderLayout.WEST);
		ingr_panel.add(avocado, BorderLayout.EAST);

		seaweed = new JTextField(5);
		seaweed.setMaximumSize(new Dimension(100, 50));
		ingr_panel.add(new JLabel("Seaweed: "), BorderLayout.WEST);
		ingr_panel.add(seaweed, BorderLayout.EAST);

		eel = new JTextField(5);
		eel.setMaximumSize(new Dimension(100, 50));
		ingr_panel.add(new JLabel("Eel: "), BorderLayout.WEST);
		ingr_panel.add(eel, BorderLayout.EAST);

		rice = new JTextField(5);
		rice.setMaximumSize(new Dimension(100, 50));
		ingr_panel.add(new JLabel("Rice: "), BorderLayout.WEST);
		ingr_panel.add(rice, BorderLayout.EAST);

		crab = new JTextField(5);
		crab.setMaximumSize(new Dimension(100, 50));
		ingr_panel.add(new JLabel("Crab: "), BorderLayout.WEST);
		ingr_panel.add(crab, BorderLayout.EAST);

		shrimp = new JTextField(5);
		shrimp.setMaximumSize(new Dimension(100, 50));
		ingr_panel.add(new JLabel("Shrimp: "), BorderLayout.WEST);
		ingr_panel.add(shrimp, BorderLayout.EAST);

		tuna = new JTextField(5);
		tuna.setMaximumSize(new Dimension(100, 50));
		ingr_panel.add(new JLabel("Tuna: "));
		ingr_panel.add(tuna, BorderLayout.EAST);

		yellowtail = new JTextField(5);
		yellowtail.setMaximumSize(new Dimension(100, 50));
		ingr_panel.add(new JLabel("Yellowtail: "), BorderLayout.WEST);
		ingr_panel.add(yellowtail, BorderLayout.EAST);

		gold_price = new JTextField(5);
		gold_price.setMaximumSize(new Dimension(200, 100));
		ingr_panel.add(new JLabel("If Gold plate, enter price between $5 and $10: "), BorderLayout.EAST);
		ingr_panel.add(gold_price, BorderLayout.EAST);

		whole_panel.add(ingr_panel);

		JButton add_button = new JButton("Add plate");
		add_button.setActionCommand("add_plate");
		add_button.addActionListener(this);
		whole_panel.add(add_button);

		JLabel error_label = new JLabel();
		whole_panel.add(error_label, BorderLayout.WEST);

		add(whole_panel);

	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "add_plate":
			switch (sushi_type_field.getText().toLowerCase()) {
			case "nigiri":
				switch (nigiri_type_field.getText().toLowerCase()) {
				case "tuna":
					custom_sushi = new Nigiri(Nigiri.NigiriType.TUNA);
					break;
				case "yellowtail":
					custom_sushi = new Nigiri(Nigiri.NigiriType.YELLOWTAIL);
					break;
				case "eel":
					custom_sushi = new Nigiri(Nigiri.NigiriType.EEL);
					break;
				case "crab":
					custom_sushi = new Nigiri(Nigiri.NigiriType.CRAB);
					break;
				case "shrimp":
					custom_sushi = new Nigiri(Nigiri.NigiriType.SHRIMP);
					break;
				}
				break;
			case "sashimi":
				switch (sashimi_type_field.getText().toLowerCase()) {
				case "tuna":
					custom_sushi = new Sashimi(Sashimi.SashimiType.TUNA);
					break;
				case "yellowtail":
					custom_sushi = new Sashimi(Sashimi.SashimiType.YELLOWTAIL);
					break;
				case "eel":
					custom_sushi = new Sashimi(Sashimi.SashimiType.EEL);
					break;
				case "crab":
					custom_sushi = new Sashimi(Sashimi.SashimiType.CRAB);
					break;
				case "shrimp":
					custom_sushi = new Sashimi(Sashimi.SashimiType.SHRIMP);
					break;
				default:
					error_label.setText("Invalid sashimi");
					break;
				}
				break;
			case "roll":
				firstIngArray = new IngredientPortion[8];
				portion_counter = 0;
				try {
				if ((Double.parseDouble(avocado.getText()) != 0.0) && (Double.parseDouble(avocado.getText()) < 1.5)) {
					AvocadoPortion avocadoPortion = new AvocadoPortion(Double.parseDouble(avocado.getText()));
					firstIngArray[portion_counter] = avocadoPortion;
					portion_counter++;
				}
				if ((Double.parseDouble(seaweed.getText()) != 0.0) && (Double.parseDouble(seaweed.getText()) < 1.5)) {
					SeaweedPortion seaweedPortion = new SeaweedPortion(Double.parseDouble(seaweed.getText()));
					firstIngArray[portion_counter] = seaweedPortion;
					portion_counter++;
				}
				if ((Double.parseDouble(eel.getText()) != 0.0) && (Double.parseDouble(eel.getText()) < 1.5)) {
					EelPortion eelPortion = new EelPortion(Double.parseDouble(eel.getText()));
					firstIngArray[portion_counter] = eelPortion;
					portion_counter++;
				}
				if ((Double.parseDouble(rice.getText()) != 0.0) && (Double.parseDouble(rice.getText()) < 1.5)) {
					RicePortion ricePortion = new RicePortion(Double.parseDouble(rice.getText()));
					firstIngArray[portion_counter] = ricePortion;
					portion_counter++;
				}
				if ((Double.parseDouble(crab.getText()) != 0.0) && (Double.parseDouble(crab.getText()) < 1.5)) {
					CrabPortion crabPortion = new CrabPortion(Double.parseDouble(crab.getText()));
					firstIngArray[portion_counter] = crabPortion;
					portion_counter++;
				}
				if ((Double.parseDouble(shrimp.getText()) != 0.0) && (Double.parseDouble(shrimp.getText()) < 1.5)) {
					ShrimpPortion shrimpPortion = new ShrimpPortion(Double.parseDouble(shrimp.getText()));
					firstIngArray[portion_counter] = shrimpPortion;
					portion_counter++;
				}
				if ((Double.parseDouble(tuna.getText()) != 0.0) && (Double.parseDouble(tuna.getText()) < 1.5)) {
					TunaPortion tunaPortion = new TunaPortion(Double.parseDouble(tuna.getText()));
					firstIngArray[portion_counter] = tunaPortion;
					portion_counter++;
				}
				if ((Double.parseDouble(yellowtail.getText()) != 0.0) && (Double.parseDouble(yellowtail.getText()) < 1.5)) {
					YellowtailPortion yellowtailPortion = new YellowtailPortion(Double.parseDouble(yellowtail.getText()));
					firstIngArray[portion_counter] = yellowtailPortion;
					portion_counter++;
				}
				finalIngArray = new IngredientPortion[portion_counter];
				for (int i = 0; i < portion_counter; i++) {
					finalIngArray[i] = firstIngArray[i];
				}
				custom_sushi = new Roll(roll_name_field.getText() + " Roll", finalIngArray);
				} catch(Exception exception) {
					error_label.setText("Invalid");
				}
				break;
			} 
			try {
				switch (plate_field.getText().toLowerCase()) {
				case "red":
					makeRedPlateRequest(custom_sushi, Integer.parseInt(plate_index_field.getText()));
					break;
				case "green":
					makeGreenPlateRequest(custom_sushi, Integer.parseInt(plate_index_field.getText()));
					break;
				case "blue":
					makeBluePlateRequest(custom_sushi, Integer.parseInt(plate_index_field.getText()));
					break;
				case "gold":
					if ((Double.parseDouble(gold_price.getText()) < 10.0) && (Double.parseDouble(gold_price.getText()) > 5.0)) {
						makeGoldPlateRequest(custom_sushi, Integer.parseInt(plate_index_field.getText()),Double.parseDouble(gold_price.getText()));
					}
					break;
				default:
					error_label.setText("Invalid");
					break;
				}
			} catch (Exception exception) {
				error_label.setText("Invalid");
			}
			plate_field.setText("");
			plate_index_field.setText("");
			sushi_type_field.setText("");
			sashimi_type_field.setText("");
			nigiri_type_field.setText("");
			roll_name_field.setText("");
			avocado.setText("");
			seaweed.setText("");
			rice.setText("");
			crab.setText("");
			shrimp.setText("");
			tuna.setText("");
			eel.setText("");
			yellowtail.setText("");
			gold_price.setText("");
		}

	}
}

