package calen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Calen03 extends JFrame {
	DayList daylist;
	
	static final String WEEKTEXT[] = new String[] {"日","月","火","水","木","金","土"};
	Calendar calendar;

	private JPanel weekPanel;
	private JPanel calenPanel;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JPanel schedule;
	private JTextArea scheduleList;
	private JButton nextButton;
	private JLabel nowLabel;
	private JButton backButton;
	
	public static void main(String[] args) {
		Calen02 calen = new Calen02();
		calen.setSize(500,500);
		calen.setVisible(true);
	}
	
	public Calen03() {
		daylist = new DayList();
		
		//タイトルパネル
		titlePanel = new JPanel();
		nextButton = daylist.getNextButton();
		nowLabel = daylist.getNowButton();
		backButton = daylist.getBackButton();
		titleLabel = new JLabel();
		//レインアウト
		GridBagLayout layout = new GridBagLayout();
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.insets = new Insets(10, 3, 10, 20);
	    gbc.fill = GridBagConstraints.VERTICAL;
	    layout.setConstraints(backButton, gbc);
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.insets = new Insets(10, 0, 10, 0);
	    gbc.fill = GridBagConstraints.VERTICAL;
	    layout.setConstraints(titleLabel, gbc);
	    gbc.gridx = 2;
	    gbc.gridy = 0;
	    gbc.insets = new Insets(10, 3, 10, 0);
	    gbc.fill = GridBagConstraints.VERTICAL;
	    layout.setConstraints(nowLabel, gbc);
	    gbc.gridx = 3;
	    gbc.gridy = 0;
	    gbc.insets = new Insets(10, 20, 10, 3);
	    gbc.fill = GridBagConstraints.VERTICAL;
	    layout.setConstraints(nextButton, gbc);
	    titlePanel.setLayout(layout);
		titleLabel.setText("::スケジュール表::");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 18));
		nowLabel.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 18));
		nextButton.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 15));
		backButton.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 15));
		nextButton.addActionListener(e->daylist.nextMonth());
		backButton.addActionListener(e->daylist.backMonth());

		titlePanel.add(backButton);
		titlePanel.add(titleLabel);
		nowLabel.setBackground(Color.red);
		titlePanel.add(nowLabel);
		titlePanel.add(nextButton);
		
		//日付パネル
		weekPanel = new JPanel();
		weekPanel.setLayout(new GridLayout(1, 7, 2, 2));
		for(int i=0;i < WEEKTEXT.length;i++) {
			JLabel label = new JLabel(WEEKTEXT[i]);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
			if(i == 0) label.setForeground(Color.RED);
			if(i == 6) label.setForeground(Color.BLUE);
			weekPanel.add(label);
		}
		
		calenPanel = new JPanel();
		calenPanel.setLayout(new BoxLayout(calenPanel , BoxLayout.Y_AXIS));
		calenPanel.add(weekPanel);
		calenPanel.add(daylist.getDayPanel());
		
		//追加パネル
		schedule = new JPanel();
		JPanel addSchedulea = new JPanel();
		JTextField input = new JTextField();
		JButton addtext = new JButton("登録");
		scheduleList = new JTextArea();
		scheduleList.setPreferredSize(new Dimension(500, 150));
		addSchedulea.setLayout(new BoxLayout(addSchedulea , BoxLayout.X_AXIS));
		addSchedulea.add(Box.createRigidArea(new Dimension(10,10)));
		addSchedulea.add(input);
		addSchedulea.add(Box.createRigidArea(new Dimension(10,10)));
		addSchedulea.add(addtext);	
		addSchedulea.add(Box.createRigidArea(new Dimension(10,10)));
		schedule.setLayout(new BoxLayout(schedule , BoxLayout.Y_AXIS));
		schedule.add(Box.createRigidArea(new Dimension(500,20)));
		schedule.add(addSchedulea);
		schedule.add(Box.createRigidArea(new Dimension(500,10)));
		schedule.add(scheduleList);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().add(BorderLayout.NORTH, titlePanel);
		getContentPane().add(BorderLayout.CENTER, calenPanel);
		getContentPane().add(BorderLayout.SOUTH, schedule);
	}
}

class dayListener03 implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	} 
	
}
