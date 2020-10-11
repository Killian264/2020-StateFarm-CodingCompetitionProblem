package sf.codingcompetition2020;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;

import sf.codingcompetition2020.structures.Agent;

public class AgentReport extends JFrame{
	
	int width = 2400;
	int height = 400;

	
	public AgentReport(String area, String agentsFilePath) {
		
		CodingCompCsvUtil csvUtil = new CodingCompCsvUtil();
		
		int total = csvUtil.getAgentCountInArea(agentsFilePath, area);
		
		this.setLayout(new GridLayout(10, 1));
		
		this.setSize(width, height);
		
		this.setTitle("Area Report for: " + area);
		
		
		
		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(10, 1));
		
		int areaCount = csvUtil.getAgentCountInArea(agentsFilePath, area);

		JLabel agentHeaderLabel = new JLabel("Number of agents in area : " + Integer.toString(areaCount));
		pane.add(agentHeaderLabel);
		
		
		// language to agents speaking it in area
		HashMap<String, List<Agent>> languageToAgents = new HashMap<String, List<Agent>>();
		
		List<String> languages = csvUtil.getAllAgentLanguagesInArea(agentsFilePath, area);
		
		for(String language : languages) {
			csvUtil.getAgentsInAreaThatSpeakLanguage(agentsFilePath, area, language);
			List<Agent> agentsSpeakignLanugage = csvUtil.getAgentsInAreaThatSpeakLanguage(agentsFilePath, area, language);
			languageToAgents.put(language, agentsSpeakignLanugage);
		}
		
    	JLabel languageTableLabel = new JLabel("Language : Amount Spoken in Area : COLOR");
    	pane.add(languageTableLabel);
    	
	    // This is an abomination I apologize in advance, haha
	    
	    Color[] colors = new Color[] {Color.RED, Color.GREEN, Color.gray, Color.CYAN, Color.black, Color.BLUE, Color.PINK, Color.MAGENTA, Color.YELLOW};
	    HashMap<Color, String> colorToString = new HashMap<Color, String>(){{
	    	put(Color.RED, "RED");
	    	put(Color.GREEN, "GREEN");
	    	put(Color.gray, "GRAY");
	    	put(Color.CYAN, "CYAN");
	    	put(Color.black, "BLACK");
	    	put(Color.BLUE, "BLUE");
	    	put(Color.PINK, "PINK");
	    	put(Color.MAGENTA, "MAGENTA");
	    	put(Color.YELLOW, "YELLOW");
	    }};
	    
		int currentColor = 0;
		int maxColor = 0;
		
	    for(String language : languageToAgents.keySet()) {
	    	int amountSpoken = languageToAgents.get(language).size();
	    	JLabel languageLabel = new JLabel( language + " : " + Integer.toString(amountSpoken) + " : " + colorToString.get(colors[currentColor]));
	    	pane.add(languageLabel);
	    	
	    	currentColor++;
	    	if(currentColor == colors.length) {
	    		currentColor = 0;
	    	}
	    }
	    
	    
	    
	    BarGraph graph = new BarGraph(55, 55);
	    
		this.getContentPane().add(graph);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		
		currentColor = 0;
		maxColor = 0;
		
	    for(String language : languageToAgents.keySet()) {
	    	
	    	int amountSpoken = languageToAgents.get(language).size();
	    	graph.addBar(colors[currentColor], amountSpoken);
	    	currentColor++;
	    	if(currentColor == colors.length) {
	    		currentColor = 0;
	    	}
	    }
	    
	    
		
	}
	
}
