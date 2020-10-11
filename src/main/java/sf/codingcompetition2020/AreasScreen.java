package sf.codingcompetition2020;

import javax.swing.JFrame;
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

public class AreasScreen extends JFrame{

	private final String agentFilePath = "src/main/resources/DataFiles/agents.csv";
	private final String claimFilePath = "src/main/resources/DataFiles/claims.csv";
	private final String customerFilePath = "src/main/resources/DataFiles/customers.csv";
	private final String vendorFilePath = "src/main/resources/DataFiles/vendors.csv";
	
	private CodingCompCsvUtil csvUtil;
	private JComboBox areaComboBox;
	private ArrayList<String> areas;
	
	public AreasScreen() {
		csvUtil = new CodingCompCsvUtil();
		
		this.setTitle("AgentReport");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setSize(400, 100);
		
		csvUtil = new CodingCompCsvUtil();

		
		JLabel areaLabel = new JLabel("View data in area: ");
		areaLabel.setVerticalAlignment(JLabel.TOP);
		this.add(areaLabel);
		
		areas = (ArrayList<String>) csvUtil.getAllAgentAreas(agentFilePath);
		
		areas.add(0, "Choose an area.");
	
		this.areaComboBox = new JComboBox(areas.toArray());
		this.setLayout(new FlowLayout()); 
		this.add(this.areaComboBox);

		JButton viewDataButton = new JButton("View Data");
		this.getContentPane().add(viewDataButton); // Adds Button to content pane of frame
		
		
		this.setVisible(true);
		
		viewDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(areaComboBox.getSelectedIndex() == 0) {
					return;
				}
				String area = areas.get(areaComboBox.getSelectedIndex() - 1);
				new AgentReport(area, agentFilePath).setVisible(true);
				
			}
		});
	}
}



//package sf.codingcompetition2020;
//
//import java.awt.Container;
//import java.awt.FlowLayout;
//import java.awt.GridLayout;
//import java.awt.Label;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Stream;
//
//import javax.swing.Box;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//
//import codingcompetition2019.AllNaturalDisasters;
//import sf.codingcompetition2020.structures.Agent;
//
//public class HomeScreen {
//	
//	private static final String agentFilePath = "src/main/resources/DataFiles/agents.csv";
//	private static final String claimFilePath = "src/main/resources/DataFiles/claims.csv";
//	private static final String customerFilePath = "src/main/resources/DataFiles/customers.csv";
//	private static final String vendorFilePath = "src/main/resources/DataFiles/vendors.csv";
//
//	public static void main(String args[]) {
//		JFrame frame = new JFrame("Agent Report");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLocationRelativeTo(null);
//		
//		frame.setSize(400, 100);
//		
////		JLabel headerLabel = new JLabel("Choose an Agent to view a report:");
////		headerLabel.setVerticalAlignment(JLabel.TOP);
////		frame.add(headerLabel);
////		
//		CodingCompCsvUtil csvUtil = new CodingCompCsvUtil();
////		
////		ArrayList<Agent> agents = (ArrayList<Agent>) csvUtil.readCsvFile(agentFilePath, Agent.class);
////		
////				
////		ArrayList<String> agentNames = new ArrayList<String>();
////		
////		agentNames.add("Ghoose an Agent:");
////		
//		
////		for(Agent agent : agents) {
////			agentNames.add(agent.getFirstName() + " " +  agent.getLastName());
////		}
//		
//		JLabel areaLabel = new JLabel("View data in area: ");
//		areaLabel.setVerticalAlignment(JLabel.TOP);
//		frame.add(areaLabel);
//		
//		ArrayList<String> areas = new ArrayList<String>();
//		
////		ArrayList<String> areas = (ArrayList<Agent>) csvUtil.getAllAreas(agentFilePath);
//		
//		areas.add(0, "Choose an area.");
//	
//		JComboBox areaComboBox = new JComboBox(areas.toArray());
//		frame.setLayout(new FlowLayout()); 
//		frame.add(areaComboBox);
//
//		JButton button = new JButton("View Data");
//		frame.getContentPane().add(button); // Adds Button to content pane of frame
//		
//		
//		frame.setVisible(true);
//		
//		btnGetData.addActionListener(new ActionListener() {
//			public void buttonClick(ActionEvent e) {
//				String area = areas.get(areaComboBox.getSelectedIndex() - 1);
//				JFrame agentAreaFrame = this.ViewAgentsByArea(csvUtil, area);
//				agentAreaFrame.setVisible(true);
//
//			}
//		});
//		
//		
//	}
//
//	
//	
//	public static JFrame ViewAgentsByArea(CodingCompCsvUtil csvUtil, String area) {
//		
//		JFrame frame = new JFrame();
//		
//		int total = csvUtil.getAgentCountInArea(agentFilePath ,area);
//		
//		Container pane = frame.getContentPane();
//		pane.setLayout(new GridLayout(2, 2));
//		pane.add(Box.createVerticalGlue());
//		
//		JLabel headerLabel = new JLabel("Number of agents in area:");
//		headerLabel.setVerticalAlignment(JLabel.TOP);
//		frame.add(headerLabel);
//		
//		return frame;
//		
//		
//	}
//
//}
