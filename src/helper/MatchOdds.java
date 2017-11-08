package helper;

public class MatchOdds {
	private long MatchID;

	private long MID;

	private String NMm;

	private String ETime;

	private String HTeam;

	private String VTeam;

	private String Rq;

	private String SpSPF;

	private String SpRQSPF;

	private String SpZJQ;

	private String SpCBF;

	private String SpBQC;

	private int DGSPFStatus;

	private int DGRQSPFStatus;

	private int DGZJQStatus;

	private int DGCBFStatus;

	private int DGBQCStatus;

	private int HomeRank;

	private int AwayRank;

	private String HAHistory;

	private String HomeHistory;

	private String AwayHistory;

	private String AvgSP;

	private String PSState;

	private OptionSPF OptionSPF;

	private OptionRQSPF OptionRQSPF;

	public void setMatchID(long MatchID) {
		this.MatchID = MatchID;
	}

	public long getMatchID() {
		return this.MatchID;
	}

	public void setMID(long MID) {
		this.MID = MID;
	}

	public long getMID() {
		return this.MID;
	}

	public void setNMm(String NMm) {
		this.NMm = NMm;
	}

	public String getNMm() {
		return this.NMm;
	}

	public void setETime(String ETime) {
		this.ETime = ETime;
	}

	public String getETime() {
		return this.ETime;
	}

	public void setHTeam(String HTeam) {
		this.HTeam = HTeam;
	}

	public String getHTeam() {
		return this.HTeam;
	}

	public void setVTeam(String VTeam) {
		this.VTeam = VTeam;
	}

	public String getVTeam() {
		return this.VTeam;
	}

	public void setRq(String Rq) {
		this.Rq = Rq;
	}

	public String getRq() {
		return this.Rq;
	}

	public void setSpSPF(String SpSPF) {
		this.SpSPF = SpSPF;
	}

	public String getSpSPF() {
		return this.SpSPF;
	}

	public void setSpRQSPF(String SpRQSPF) {
		this.SpRQSPF = SpRQSPF;
	}

	public String getSpRQSPF() {
		return this.SpRQSPF;
	}

	public void setSpZJQ(String SpZJQ) {
		this.SpZJQ = SpZJQ;
	}

	public String getSpZJQ() {
		return this.SpZJQ;
	}

	public void setSpCBF(String SpCBF) {
		this.SpCBF = SpCBF;
	}

	public String getSpCBF() {
		return this.SpCBF;
	}

	public void setSpBQC(String SpBQC) {
		this.SpBQC = SpBQC;
	}

	public String getSpBQC() {
		return this.SpBQC;
	}

	public void setDGSPFStatus(int DGSPFStatus) {
		this.DGSPFStatus = DGSPFStatus;
	}

	public int getDGSPFStatus() {
		return this.DGSPFStatus;
	}

	public void setDGRQSPFStatus(int DGRQSPFStatus) {
		this.DGRQSPFStatus = DGRQSPFStatus;
	}

	public int getDGRQSPFStatus() {
		return this.DGRQSPFStatus;
	}

	public void setDGZJQStatus(int DGZJQStatus) {
		this.DGZJQStatus = DGZJQStatus;
	}

	public int getDGZJQStatus() {
		return this.DGZJQStatus;
	}

	public void setDGCBFStatus(int DGCBFStatus) {
		this.DGCBFStatus = DGCBFStatus;
	}

	public int getDGCBFStatus() {
		return this.DGCBFStatus;
	}

	public void setDGBQCStatus(int DGBQCStatus) {
		this.DGBQCStatus = DGBQCStatus;
	}

	public int getDGBQCStatus() {
		return this.DGBQCStatus;
	}

	public void setHomeRank(int HomeRank) {
		this.HomeRank = HomeRank;
	}

	public int getHomeRank() {
		return this.HomeRank;
	}

	public void setAwayRank(int AwayRank) {
		this.AwayRank = AwayRank;
	}

	public int getAwayRank() {
		return this.AwayRank;
	}

	public void setHAHistory(String HAHistory) {
		this.HAHistory = HAHistory;
	}

	public String getHAHistory() {
		return this.HAHistory;
	}

	public void setHomeHistory(String HomeHistory) {
		this.HomeHistory = HomeHistory;
	}

	public String getHomeHistory() {
		return this.HomeHistory;
	}

	public void setAwayHistory(String AwayHistory) {
		this.AwayHistory = AwayHistory;
	}

	public String getAwayHistory() {
		return this.AwayHistory;
	}

	public void setAvgSP(String AvgSP) {
		this.AvgSP = AvgSP;
	}

	public String getAvgSP() {
		return this.AvgSP;
	}

	public void setPSState(String PSState) {
		this.PSState = PSState;
	}

	public String getPSState() {
		return this.PSState;
	}

	public void setOptionSPF(OptionSPF OptionSPF) {
		this.OptionSPF = OptionSPF;
	}

	public OptionSPF getOptionSPF() {
		return this.OptionSPF;
	}

	public void setOptionRQSPF(OptionRQSPF OptionRQSPF) {
		this.OptionRQSPF = OptionRQSPF;
	}

	public OptionRQSPF getOptionRQSPF() {
		return this.OptionRQSPF;
	}

	public class OptionRQSPF {
		private double WinRate;

		private double DrawRate;

		private double LossRate;

		public void setWinRate(double WinRate) {
			this.WinRate = WinRate;
		}

		public double getWinRate() {
			return this.WinRate;
		}

		public void setDrawRate(double DrawRate) {
			this.DrawRate = DrawRate;
		}

		public double getDrawRate() {
			return this.DrawRate;
		}

		public void setLossRate(double LossRate) {
			this.LossRate = LossRate;
		}

		public double getLossRate() {
			return this.LossRate;
		}

	}

	public class OptionSPF {
		private double WinRate;

		private double DrawRate;

		private double LossRate;

		public void setWinRate(double WinRate) {
			this.WinRate = WinRate;
		}

		public double getWinRate() {
			return this.WinRate;
		}

		public void setDrawRate(double DrawRate) {
			this.DrawRate = DrawRate;
		}

		public double getDrawRate() {
			return this.DrawRate;
		}

		public void setLossRate(double LossRate) {
			this.LossRate = LossRate;
		}

		public double getLossRate() {
			return this.LossRate;
		}
	}

	@Override
	public String toString() {
		return "MatchSP [MatchID=" + MatchID + ", MID=" + MID + ", NMm=" + NMm + ", ETime=" + ETime + ", HTeam=" + HTeam
				+ ", VTeam=" + VTeam + ", Rq=" + Rq + ", SpSPF=" + SpSPF + ", SpRQSPF=" + SpRQSPF + ", SpZJQ=" + SpZJQ
				+ ", SpCBF=" + SpCBF + ", SpBQC=" + SpBQC + ", DGSPFStatus=" + DGSPFStatus + ", DGRQSPFStatus="
				+ DGRQSPFStatus + ", DGZJQStatus=" + DGZJQStatus + ", DGCBFStatus=" + DGCBFStatus + ", DGBQCStatus="
				+ DGBQCStatus + ", HomeRank=" + HomeRank + ", AwayRank=" + AwayRank + ", HAHistory=" + HAHistory
				+ ", HomeHistory=" + HomeHistory + ", AwayHistory=" + AwayHistory + ", AvgSP=" + AvgSP + ", PSState="
				+ PSState + ", OptionSPF=" + OptionSPF + ", OptionRQSPF=" + OptionRQSPF + "]";
	}
	
	
}
