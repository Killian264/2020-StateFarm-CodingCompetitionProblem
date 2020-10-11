package sf.codingcompetition2020.structures;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Customer {
	private int customerId;
	private String firstName;
	private String lastName;
	private int age;
	private String area;
	private int agentId;
	private short agentRating;
	private String primaryLanguage;
	private List<Dependent> dependents;
	private boolean homePolicy;
	private boolean autoPolicy;
	private boolean rentersPolicy;
	private String totalMonthlyPremium;
	private short yearsOfService;
	private Integer vehiclesInsured;

	private Customer() {
		// blank
	}

	public static Customer parseFromString(String line) {
		Customer customer = new Customer();
		// https://stackoverflow.com/questions/18893390/splitting-on-comma-outside-quotes
		// for regex
		String[] arr = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		Gson son = new Gson();
		customer.customerId = Integer.parseInt(arr[0]);
		customer.firstName = arr[1];
		customer.lastName = arr[2];
		customer.age = Integer.parseInt(arr[3]);
		customer.area = arr[4];
		customer.agentId = Integer.parseInt(arr[5]);
		customer.agentRating = Short.parseShort(arr[6]);
		customer.primaryLanguage = arr[7];
		customer.dependents = new ArrayList<Dependent>();
		try {
			customer.dependents = son.fromJson(arr[8], ArrayList.class);
		} catch (JsonSyntaxException e) {
			System.out.print(arr[8]);
		}
		customer.homePolicy = Boolean.parseBoolean(arr[9]);
		customer.autoPolicy = Boolean.parseBoolean(arr[10]);
		customer.rentersPolicy = Boolean.parseBoolean(arr[11]);
		customer.totalMonthlyPremium = arr[12];
		customer.yearsOfService = Short.parseShort(arr[13]);
		customer.vehiclesInsured = Integer.parseInt(arr[14]);

		return customer;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getArea() {
		return area;
	}

	public int getAgentId() {
		return agentId;
	}

	public short getAgentRating() {
		return agentRating;
	}

	public String getPrimaryLanguage() {
		return primaryLanguage;
	}

	public List<Dependent> getDependents() {
		return dependents;
	}

	public boolean isHomePolicy() {
		return homePolicy;
	}

	public boolean isAutoPolicy() {
		return autoPolicy;
	}

	public boolean isRentersPolicy() {
		return rentersPolicy;
	}

	public String getTotalMonthlyPremium() {
		return totalMonthlyPremium;
	}

	public short getYearsOfService() {
		return yearsOfService;
	}

	public Integer getVehiclesInsured() {
		return vehiclesInsured;
	}

}
