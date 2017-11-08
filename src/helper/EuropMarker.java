package helper;

public class EuropMarker {
	private int BookMarkerId;

	private String BookMarkerName;

	private double FirstWin;

	private double FirstDraw;

	private double FirstLoss;

	private double LastWin;

	private double LastDraw;

	private double LastLoss;

	private int LastChangeWin;

	private int LastChangeDraw;

	private int LastChangeLoss;

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

	public void setFirstWin(double FirstWin) {
		this.FirstWin = FirstWin;
	}

	public double getFirstWin() {
		return this.FirstWin;
	}

	public void setFirstDraw(double FirstDraw) {
		this.FirstDraw = FirstDraw;
	}

	public double getFirstDraw() {
		return this.FirstDraw;
	}

	public void setFirstLoss(double FirstLoss) {
		this.FirstLoss = FirstLoss;
	}

	public double getFirstLoss() {
		return this.FirstLoss;
	}

	public void setLastWin(double LastWin) {
		this.LastWin = LastWin;
	}

	public double getLastWin() {
		return this.LastWin;
	}

	public void setLastDraw(double LastDraw) {
		this.LastDraw = LastDraw;
	}

	public double getLastDraw() {
		return this.LastDraw;
	}

	public void setLastLoss(double LastLoss) {
		this.LastLoss = LastLoss;
	}

	public double getLastLoss() {
		return this.LastLoss;
	}

	public void setLastChangeWin(int LastChangeWin) {
		this.LastChangeWin = LastChangeWin;
	}

	public int getLastChangeWin() {
		return this.LastChangeWin;
	}

	public void setLastChangeDraw(int LastChangeDraw) {
		this.LastChangeDraw = LastChangeDraw;
	}

	public int getLastChangeDraw() {
		return this.LastChangeDraw;
	}

	public void setLastChangeLoss(int LastChangeLoss) {
		this.LastChangeLoss = LastChangeLoss;
	}

	public int getLastChangeLoss() {
		return this.LastChangeLoss;
	}

	@Override
	public String toString() {
		return "Marker [BookMarkerId=" + BookMarkerId + ", BookMarkerName=" + BookMarkerName + ", FirstWin=" + FirstWin
				+ ", FirstDraw=" + FirstDraw + ", FirstLoss=" + FirstLoss + ", LastWin=" + LastWin + ", LastDraw="
				+ LastDraw + ", LastLoss=" + LastLoss + ", LastChangeWin=" + LastChangeWin + ", LastChangeDraw="
				+ LastChangeDraw + ", LastChangeLoss=" + LastChangeLoss + "]";
	}
}
