package sf.codingcompetition2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import sf.codingcompetition2020.structures.Agent;
import sf.codingcompetition2020.structures.Claim;
import sf.codingcompetition2020.structures.Customer;
import sf.codingcompetition2020.structures.Vendor;

public class CodingCompCsvUtil {

	/*
	 * #1 readCsvFile() -- Read in a CSV File and return a list of entries in that
	 * file.
	 * 
	 * @param filePath -- Path to file being read in.
	 * 
	 * @param classType -- Class of entries being read in.
	 * 
	 * @return -- List of entries being returned.
	 */
	public <T> List<T> readCsvFile(String filePath, Class<T> classType) {

		Class myClass = classType.getClass();
		List<T> list = new ArrayList<T>();

		File in = new File(filePath);
		Scanner readMe = new Scanner(System.in);
		try {
			readMe = new Scanner(in);
			String line = "";
			if (readMe.hasNextLine()) {
				line = readMe.nextLine();
			}
			while (readMe.hasNextLine()) {
				line = readMe.nextLine();

				if (classType.equals(Agent.class)) {
					list.add((T) Agent.parseFromString(line));
				} else if (classType.equals(Claim.class)) {
					list.add((T) Claim.parseFromString(line));
				} else if (classType.equals(Customer.class)) {
					list.add((T) Customer.parseFromString(line));
				} else if (classType.equals(Vendor.class)) {
					list.add((T) Vendor.parseFromString(line));
				} else {
					throw new InvalidClassException("Cannot convert to class: " + classType.toString());
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("No file found at " + filePath);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		readMe.close();
		return list;
	}

	private <T> List<T> getStructInArea(String filePath, String area, Class<T> classType) throws InvalidClassException {
		List<T> list = readCsvFile(filePath, classType);
		List<T> listarea = new ArrayList<T>();
		String s = "";
		for (T struct : list) {
			if (classType.equals(Agent.class)) {
				s = ((Agent) struct).getArea();
			} else if (classType.equals(Claim.class)) {
				throw new InvalidClassException("Cannot get on area of class" + classType.toString());
			} else if (classType.equals(Customer.class)) {
				s = ((Customer) struct).getArea();
			} else if (classType.equals(Vendor.class)) {
				s = ((Vendor) struct).getArea();
			}
			if (s.equals(area)) {
				listarea.add(struct);
			}
		}
		return listarea;
	}

	/*
	 * #2 getAgentCountInArea() -- Return the number of agents in a given area.
	 * 
	 * @param filePath -- Path to file being read in.
	 * 
	 * @param area -- The area from which the agents should be counted.
	 * 
	 * @return -- The number of agents in a given area
	 */
	public int getAgentCountInArea(String filePath, String area) {
		int result = 0;
		try {
			result = (int) getStructInArea(filePath, area, Agent.class).size();
		} catch (InvalidClassException e) {
			System.out.println("Should Not Happen: " + e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return result;
	}

	/*
	 * #3 getAllAgentLanguages() -- Return a list of areas
	 * area, that speak a certain language.
	 * 
	 * @param filePath -- Path to file being read in.
	 * 
	 * @return -- Unique areas
	 */
	public List<String> getAllAgentAreas(String agentFilePath) {
		List<Agent> agents = readCsvFile(agentFilePath, Agent.class);
		
		// Boolean will go unused
		HashMap<String, Boolean> areaMap = new HashMap<String, Boolean>();
		
		for(Agent agent : agents) {
			areaMap.put(agent.getArea(), true);
		}
		
		ArrayList<String> areas = new ArrayList<String>();

	    for(String key : areaMap.keySet()) {
	    	areas.add(key);
	    }
	    
	    return areas;
	}
	
	/*
	 * #3 getAllAgentLanguages() -- Return a list of languages spoken 
	 * area, that speak a certain language.
	 * 
	 * @param filePath -- Path to file being read in.
	 * 
	 * @param area -- The area from which the agents languages should be added.
	 * 
	 * @return -- The languages spoken in the area
	 */
	public List<String> getAllAgentLanguagesInArea(String agentFilePath, String area) {
		List<Agent> agents = readCsvFile(agentFilePath, Agent.class);
		
		// Boolean will go unused
		HashMap<String, Boolean> areaMap = new HashMap<String, Boolean>();
		
		for(Agent agent : agents) {
			if(!agent.getArea().equals(area)) {
				continue;
			}
			areaMap.put(agent.getLanguage(), true);
		}
		
		ArrayList<String> areas = new ArrayList<String>();

	    for(String key : areaMap.keySet()) {
	    	areas.add(key);
	    }
	    
	    return areas;
	}

	/*
	 * #3 getAgentsInAreaThatSpeakLanguage() -- Return a list of agents from a given
	 * area, that speak a certain language.
	 * 
	 * @param filePath -- Path to file being read in.
	 * 
	 * @param area -- The area from which the agents should be counted.
	 * 
	 * @param language -- The language spoken by the agent(s).
	 * 
	 * @return -- The number of agents in a given area
	 */
	public List<Agent> getAgentsInAreaThatSpeakLanguage(String filePath, String area, String language) {
		List<Agent> agents = new ArrayList<Agent>();
		try {
			agents = getStructInArea(filePath, area, Agent.class);
		} catch (Exception e) {
			System.out.println("Should Not Happen: " + e.getMessage());
		}
		List<Agent> result = new ArrayList<Agent>();
		for (Agent agent : agents) {
			if (agent.getLanguage().equals(language)) {
				result.add(agent);
			}
		}
		return result;
	}

	/*
	 * #4 countCustomersFromAreaThatUseAgent() -- Return the number of individuals
	 * from an area that use a certain agent.
	 * 
	 * @param filePath -- Path to file being read in.
	 * 
	 * @param customerArea -- The area from which the customers should be counted.
	 * 
	 * @param agentFirstName -- First name of agent.
	 * 
	 * @param agentLastName -- Last name of agent.
	 * 
	 * @return -- The number of customers that use a certain agent in a given area.
	 */
	public short countCustomersFromAreaThatUseAgent(Map<String, String> csvFilePaths, String customerArea,
			String agentFirstName, String agentLastName) {
		return 0;
	}

	/*
	 * #5 getCustomersRetainedForYearsByPlcyCostAsc() -- Return a list of customers
	 * retained for a given number of years, in ascending order of their policy
	 * cost.
	 * 
	 * @param filePath -- Path to file being read in.
	 * 
	 * @param yearsOfServeice -- Number of years the person has been a customer.
	 * 
	 * @return -- List of customers retained for a given number of years, in
	 * ascending order of policy cost.
	 */
	public List<Customer> getCustomersRetainedForYearsByPlcyCostAsc(String customerFilePath, short yearsOfService) {
		return null;
	}

	/*
	 * #6 getLeadsForInsurance() -- Return a list of individuals who’ve made an
	 * inquiry for a policy but have not signed up. *HINT* -- Look for customers
	 * that currently have no policies with the insurance company.
	 * 
	 * @param filePath -- Path to file being read in.
	 * 
	 * @return -- List of customers who’ve made an inquiry for a policy but have
	 * not signed up.
	 */
	public List<Customer> getLeadsForInsurance(String filePath) {
		return null;
	}

	/*
	 * #7 getVendorsWithGivenRatingThatAreInScope() -- Return a list of vendors
	 * within an area and include options to narrow it down by: a. Vendor rating b.
	 * Whether that vendor is in scope of the insurance (if inScope == false, return
	 * all vendors in OR out of scope, if inScope == true, return ONLY vendors in
	 * scope)
	 * 
	 * @param filePath -- Path to file being read in.
	 * 
	 * @param area -- Area of the vendor.
	 * 
	 * @param inScope -- Whether or not the vendor is in scope of the insurance.
	 * 
	 * @param vendorRating -- The rating of the vendor.
	 * 
	 * @return -- List of vendors within a given area, filtered by scope and vendor
	 * rating.
	 */
	public List<Vendor> getVendorsWithGivenRatingThatAreInScope(String filePath, String area, boolean inScope,
			int vendorRating) {
		return null;
	}

	/*
	 * #8 getUndisclosedDrivers() -- Return a list of customers between the age of
	 * 40 and 50 years (inclusive), who have: a. More than X cars b. less than or
	 * equal to X number of dependents.
	 * 
	 * @param filePath -- Path to file being read in.
	 * 
	 * @param vehiclesInsured -- The number of vehicles insured.
	 * 
	 * @param dependents -- The number of dependents on the insurance policy.
	 * 
	 * @return -- List of customers filtered by age, number of vehicles insured and
	 * the number of dependents.
	 */
	public List<Customer> getUndisclosedDrivers(String filePath, int vehiclesInsured, int dependents) {
		return null;
	}

	/*
	 * #9 getAgentIdGivenRank() -- Return the agent with the given rank based on
	 * average customer satisfaction rating. *HINT* -- Rating is calculated by
	 * taking all the agent rating by customers (1-5 scale) and dividing by the
	 * total number of reviews for the agent.
	 * 
	 * @param filePath -- Path to file being read in.
	 * 
	 * @param agentRank -- The rank of the agent being requested.
	 * 
	 * @return -- Agent ID of agent with the given rank.
	 */
	public int getAgentIdGivenRank(String filePath, int agentRank) {
		return 0;
	}

	/*
	 * #10 getCustomersWithClaims() -- Return a list of customers who’ve filed a
	 * claim within the last <numberOfMonths> (inclusive).
	 * 
	 * @param filePath -- Path to file being read in.
	 * 
	 * @param monthsOpen -- Number of months a policy has been open.
	 * 
	 * @return -- List of customers who’ve filed a claim within the last
	 * <numberOfMonths>.
	 */
	public List<Customer> getCustomersWithClaims(Map<String, String> csvFilePaths, short monthsOpen) {
		return null;
	}

}
