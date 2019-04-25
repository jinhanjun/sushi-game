package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel game_model;
	private JLabel display;
	private JPanel button_panel = new JPanel();
	
	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		
		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.TOP);
		setLayout(new BorderLayout());
		display.setText(makeScoreboardHTMLChef());
		
		button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.Y_AXIS));
		
		JButton balance_button = new JButton("Sort by Balance");
		balance_button.setActionCommand("balance");
		balance_button.addActionListener(this);
		button_panel.add(balance_button);
		
		JButton consumed_button = new JButton("Sort by Weight Consumed");
		consumed_button.setActionCommand("consumed");
		consumed_button.addActionListener(this);
		button_panel.add(consumed_button);
		
		JButton spoiled_button = new JButton("Sort by Weight Spoiled");
		spoiled_button.setActionCommand("spoiled");
		spoiled_button.addActionListener(this);
		button_panel.add(spoiled_button);
		
		button_panel.add(display, BorderLayout.CENTER);
		
		add(button_panel, BorderLayout.WEST);
		
	}

	private String makeScoreboardHTMLChef() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new HighToLowBalanceComparator());
		
		for (Chef c : chefs) {
			sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
		}
		return sb_html;
	}

	private String makeScoreboardHTMLSold() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new HighToLowConsumerComparator());
		
		for (Chef c : chefs) {
			sb_html += c.getName() + " (" + String.format("%.2f", c.getAmountConsumed()) + " oz) <br>";
		}
		return sb_html;
	}
	
	private String makeScoreboardHTMLSpoiled() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new LowToHighSpoiledComparator());
		
		for (Chef c : chefs) {
			sb_html += c.getName() + " (" + String.format("%.2f", c.getAmountSpoiled()) + " oz) <br>";

		}
		return sb_html;
	}
	
	public void refresh() {
		display.setText(makeScoreboardHTMLChef());		
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "spoiled":
			display.setText(makeScoreboardHTMLSpoiled());
			break;
		case "balance":
			display.setText(makeScoreboardHTMLChef());
			break;
		case "consumed":
			display.setText(makeScoreboardHTMLSold());
			break;
		}
	}

}
