package helper;

public class NBAOdds {

	private long MID;

	private String NMm;

	private String ETime;

	private String HTeam;

	private String VTeam;

	private String SpRFSF;

	private String SpSF;

	private String SpSFC;

	private String SpDXF;

	private int DGRFSFStatus;

	private int DGSFStatus;

	private int DGDXFStatus;

	private int DGSFCStatus;
	
	private int Hd;
	
	private String Reason;

	private String PSDetailState;
	
	private String PSState;
	
	public long getMID() {
		return MID;
	}

	public void setMID(long mID) {
		MID = mID;
	}

	public String getNMm() {
		return NMm;
	}

	public void setNMm(String nMm) {
		NMm = nMm;
	}

	public String getETime() {
		return ETime;
	}

	public void setETime(String eTime) {
		ETime = eTime;
	}

	public String getHTeam() {
		return HTeam;
	}

	public void setHTeam(String hTeam) {
		HTeam = hTeam;
	}

	public String getVTeam() {
		return VTeam;
	}

	public void setVTeam(String vTeam) {
		VTeam = vTeam;
	}

	public String getSpRFSF() {
		return SpRFSF;
	}

	public void setSpRFSF(String spRFSF) {
		SpRFSF = spRFSF;
	}

	public String getSpSF() {
		return SpSF;
	}

	public void setSpSF(String spSF) {
		SpSF = spSF;
	}

	public String getSpSFC() {
		return SpSFC;
	}

	public void setSpSFC(String spSFC) {
		SpSFC = spSFC;
	}

	public String getSpDXF() {
		return SpDXF;
	}

	public void setSpDXF(String spDXF) {
		SpDXF = spDXF;
	}

	public int getDGRFSFStatus() {
		return DGRFSFStatus;
	}

	public void setDGRFSFStatus(int dGRFSFStatus) {
		DGRFSFStatus = dGRFSFStatus;
	}

	public int getDGSFStatus() {
		return DGSFStatus;
	}

	public void setDGSFStatus(int dGSFStatus) {
		DGSFStatus = dGSFStatus;
	}

	public int getDGDXFStatus() {
		return DGDXFStatus;
	}

	public void setDGDXFStatus(int dGDXFStatus) {
		DGDXFStatus = dGDXFStatus;
	}

	public int getDGSFCStatus() {
		return DGSFCStatus;
	}

	public void setDGSFCStatus(int dGSFCStatus) {
		DGSFCStatus = dGSFCStatus;
	}

	public int getHd() {
		return Hd;
	}

	public void setHd(int hd) {
		Hd = hd;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public String getPSDetailState() {
		return PSDetailState;
	}

	public void setPSDetailState(String pSDetailState) {
		PSDetailState = pSDetailState;
	}

	public String getPSState() {
		return PSState;
	}

	public void setPSState(String pSState) {
		PSState = pSState;
	}

	public int getHomeRank() {
		return HomeRank;
	}

	public void setHomeRank(int homeRank) {
		HomeRank = homeRank;
	}

	public int getAwayRank() {
		return AwayRank;
	}

	public void setAwayRank(int awayRank) {
		AwayRank = awayRank;
	}

	public String getHAHistory() {
		return HAHistory;
	}

	public void setHAHistory(String hAHistory) {
		HAHistory = hAHistory;
	}

	public String getHomeHistory() {
		return HomeHistory;
	}

	public void setHomeHistory(String homeHistory) {
		HomeHistory = homeHistory;
	}

	public String getAwayHistory() {
		return AwayHistory;
	}

	public void setAwayHistory(String awayHistory) {
		AwayHistory = awayHistory;
	}

	public String getAvgSP() {
		return AvgSP;
	}

	public void setAvgSP(String avgSP) {
		AvgSP = avgSP;
	}

	public OptionSPF getOptionSPF() {
		return OptionSPF;
	}

	public void setOptionSPF(OptionSPF optionSPF) {
		OptionSPF = optionSPF;
	}

	public OptionRQSPF getOptionRQSPF() {
		return OptionRQSPF;
	}

	public void setOptionRQSPF(OptionRQSPF optionRQSPF) {
		OptionRQSPF = optionRQSPF;
	}

	private int HomeRank;

	private int AwayRank;

	private String HAHistory;

	private String HomeHistory;

	private String AwayHistory;

	private String AvgSP;

	private OptionSPF OptionSPF;

	private OptionRQSPF OptionRQSPF;


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
		return "NBAOdds [MID=" + MID + ", NMm=" + NMm + ", ETime=" + ETime + ", HTeam=" + HTeam + ", VTeam=" + VTeam
				+ ", SpRFSF=" + SpRFSF + ", SpSF=" + SpSF + ", SpSFC=" + SpSFC + ", SpDXF=" + SpDXF + ", DGRFSFStatus="
				+ DGRFSFStatus + ", DGSFStatus=" + DGSFStatus + ", DGDXFStatus=" + DGDXFStatus + ", DGSFCStatus="
				+ DGSFCStatus + ", Hd=" + Hd + ", PSState=" + PSState + "]";
	}
}
