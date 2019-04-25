package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import comp401sushi.Plate;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver {

	private Belt belt;
	private JLabel[] belt_labels;
	private JPanel belt_panel;

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		
		/*belt_labels = new JLabel[belt.getSize()];
		for (int i = 0; i < belt.getSize(); i++) {
			JLabel plabel = new JLabel("");
			plabel.setMinimumSize(new Dimension(300, 20));
			plabel.setPreferredSize(new Dimension(300, 20));
			plabel.setOpaque(true);
			plabel.setBackground(Color.GRAY);
			add(plabel);
			belt_labels[i] = plabel;
		}*/
		belt_panel = new JPanel();
		belt_panel.setLayout(new GridLayout(0,1));
		buildListPanel();
		JScrollPane belt_panel1 = new JScrollPane(belt_panel);
		belt_panel1.setMinimumSize(new Dimension(500,500));
		belt_panel1.setPreferredSize(new Dimension(500,500));
		add(belt_panel1, BorderLayout.CENTER);
		
	
		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}
	
	private void buildListPanel() {
		for (int i=0; i<belt.getSize(); i++) {
			PlateViewWidget plate_view_widget = new PlateViewWidget(belt, i);
			belt_panel.add(plate_view_widget);
			
		}				
	}

	private void refresh() {
		/*for (int i=0; i<belt.getSize(); i++) {
			Plate p = belt.getPlateAtPosition(i);
			JLabel plabel = belt_labels[i];

			if (p == null) {
				plabel.setText("");
				plabel.setBackground(Color.GRAY);
			} else {
				plabel.setText(p.toString());
				switch (p.getColor()) {
				case RED:
					plabel.setBackground(Color.RED); break;
				case GREEN:
					plabel.setBackground(Color.GREEN); break;
				case BLUE:
					plabel.setBackground(Color.BLUE); break;
				case GOLD:
					plabel.setBackground(Color.YELLOW); break;
				}
				plabel.setText(belt.getPlateAtPosition(i).getContents().getName());
			}
			*/
		belt_panel.removeAll();
		buildListPanel();
		belt_panel.revalidate();
		
	}
}
