package calen05.mainFrame.titlePanel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePalel extends JPanel {
	private JButton prevButton = new JButton();
	private JLabel nowLabel = new JLabel();
	private JButton nextButton = new JButton();
	
	public TitlePalel(LocalDateTime date){
		add(prevButton);
		add(nowLabel);
		add(nextButton);
		updateMonth(date);
		setLayout();
	}
	
	public void addPrevActionListener(ActionListener actionListener) {
		prevButton.addActionListener(actionListener);
	}
	public void addNextActionListener(ActionListener actionListener) {
		nextButton.addActionListener(actionListener);
	}
	public void updateMonth(LocalDateTime date) {
		prevButton.setText(date.minusMonths(1).getMonthValue() + "月");
		nowLabel.setText(date.getYear() + "年" + date.getMonthValue() + "月");
		nextButton.setText(date.plusMonths(1).getMonthValue() + "月");
	}
	
	public void setLayout(){
		//レインアウト
		GridBagLayout layout = new GridBagLayout();
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridy = 0;
	    gbc.fill = GridBagConstraints.VERTICAL;
	    
	    gbc.gridx = 2;
	    gbc.insets = new Insets(10, 3, 10, 0);
	    layout.setConstraints(nowLabel, gbc);
	    gbc.gridx = 3;
	    gbc.insets = new Insets(10, 20, 10, 3);
	    layout.setConstraints(nextButton, gbc);
	    gbc.gridx = 0;
	    gbc.insets = new Insets(10, 3, 10, 20);
	    layout.setConstraints(prevButton, gbc);
	    setLayout(layout);
		nowLabel.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 18));
		nextButton.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 15));
		prevButton.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 15));
	}
}
