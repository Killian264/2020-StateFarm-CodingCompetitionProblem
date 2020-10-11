package sf.codingcompetition2020;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import codingcompetition2019.AllNaturalDisasters;
import sf.codingcompetition2020.structures.Agent;

public class HomeScreen {
	
	private static final String agentFilePath = "src/main/resources/DataFiles/agents.csv";
	private static final String claimFilePath = "src/main/resources/DataFiles/claims.csv";
	private static final String customerFilePath = "src/main/resources/DataFiles/customers.csv";
	private static final String vendorFilePath = "src/main/resources/DataFiles/vendors.csv";

	public static void main(String args[]) {
		AreasScreen screen = new AreasScreen();
		screen.setVisible(true);
		
	}

	
	
	public static JFrame ViewAgentsByArea(CodingCompCsvUtil csvUtil, String area) {
		
		JFrame frame = new JFrame();
		
		int total = csvUtil.getAgentCountInArea(agentFilePath ,area);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new GridLayout(2, 2));
		pane.add(Box.createVerticalGlue());
		
		JLabel headerLabel = new JLabel("Number of agents in area:");
		headerLabel.setVerticalAlignment(JLabel.TOP);
		frame.add(headerLabel);
		
		return frame;
		
		
	}

}
