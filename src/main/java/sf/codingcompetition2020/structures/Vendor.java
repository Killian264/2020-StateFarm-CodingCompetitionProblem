package sf.codingcompetition2020.structures;

public class Vendor {
	private int vendorId;
	private String area;
	private int vendorRating;
	private boolean inScope;

	private Vendor() {
		// blank
	}

	public static Vendor parseFromString(String line) {
		Vendor vendor = new Vendor();
		String[] arr = line.split(",");
		vendor.vendorId = Integer.parseInt(arr[0]);
		vendor.area = arr[1];
		vendor.vendorRating = Integer.parseInt(arr[2]);
		vendor.inScope = Boolean.parseBoolean(arr[3]);

		return vendor;
	}

	public int getVendorId() {
		return vendorId;
	}

	public String getArea() {
		return area;
	}

	public int getVendorRating() {
		return vendorRating;
	}

	public boolean isInScope() {
		return inScope;
	}

}
