package sf.codingcompetition2020.structures;

public class Claim {
	private int claimId;
	private int customerId;
	private boolean closed;
	private int monthsOpen;

	private Claim() {
		// blank
	}

	public static Claim parseFromString(String line) {
		Claim claim = new Claim();
		String[] arr = line.split(",");
		claim.claimId = Integer.parseInt(arr[0]);
		claim.customerId = Integer.parseInt(arr[1]);
		claim.closed = Boolean.parseBoolean(arr[2]);
		claim.monthsOpen = Integer.parseInt(arr[3]);

		return claim;
	}

	public int getClaimId() {
		return claimId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public boolean isClosed() {
		return closed;
	}

	public int getMonthsOpen() {
		return monthsOpen;
	}
}
