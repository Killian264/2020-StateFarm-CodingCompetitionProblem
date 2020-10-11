package sf.codingcompetition2020.structures;

public class Agent {

	private int agentId;
	private String area;
	private String language;
	private String firstName;
	private String lastName;

	private Agent() {
		// blank
	}

	public static Agent parseFromString(String line) {
		Agent agent = new Agent();
		String[] arr = line.split(",");
		agent.agentId = Integer.parseInt(arr[0]);
		agent.area = arr[1];
		agent.language = arr[2];
		agent.firstName = arr[3];
		agent.lastName = arr[4];

		return agent;
	}

	public int getAgentId() {
		return agentId;
	}

	public String getArea() {
		return area;
	}

	public String getLanguage() {
		return language;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
