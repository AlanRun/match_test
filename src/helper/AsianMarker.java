package helper;

public class AsianMarker {
	private int BookMarkerId;

	private String BookMarkerName;

	private double Hcap;

	private double FirstHome;

	private double FirstAway;

	private double LastHcap;

	private double LastHome;

	private double LastAway;

	private int LastHomeChange;

	private int LastAwayChange;

	public void setBookMarkerId(int BookMarkerId) {
		this.BookMarkerId = BookMarkerId;
	}

	public int getBookMarkerId() {
		return this.BookMarkerId;
	}

	public void setBookMarkerName(String BookMarkerName) {
		this.BookMarkerName = BookMarkerName;
	}

	public String getBookMarkerName() {
		return this.BookMarkerName;
	}

	public void setHcap(double Hcap) {
		this.Hcap = Hcap;
	}

	public double getHcap() {
		return this.Hcap;
	}

	public void setFirstHome(double FirstHome) {
		this.FirstHome = FirstHome;
	}

	public double getFirstHome() {
		return this.FirstHome;
	}

	public void setFirstAway(double FirstAway) {
		this.FirstAway = FirstAway;
	}

	public double getFirstAway() {
		return this.FirstAway;
	}

	public void setLastHcap(double LastHcap) {
		this.LastHcap = LastHcap;
	}

	public double getLastHcap() {
		return this.LastHcap;
	}

	public void setLastHome(double LastHome) {
		this.LastHome = LastHome;
	}

	public double getLastHome() {
		return this.LastHome;
	}

	public void setLastAway(double LastAway) {
		this.LastAway = LastAway;
	}

	public double getLastAway() {
		return this.LastAway;
	}

	public void setLastHomeChange(int LastHomeChange) {
		this.LastHomeChange = LastHomeChange;
	}

	public int getLastHomeChange() {
		return this.LastHomeChange;
	}

	public void setLastAwayChange(int LastAwayChange) {
		this.LastAwayChange = LastAwayChange;
	}

	public int getLastAwayChange() {
		return this.LastAwayChange;
	}

	@Override
	public String toString() {
		return "BookMarker [BookMarkerId=" + BookMarkerId + ", BookMarkerName=" + BookMarkerName + ", Hcap=" + Hcap
				+ ", FirstHome=" + FirstHome + ", FirstAway=" + FirstAway + ", LastHcap=" + LastHcap + ", LastHome="
				+ LastHome + ", LastAway=" + LastAway + ", LastHomeChange=" + LastHomeChange + ", LastAwayChange="
				+ LastAwayChange + "]";
	}
}
