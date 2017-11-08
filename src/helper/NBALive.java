package helper;

public class NBALive {
	private String Issue;

	private int LotteryId;

	private int No;

	private String Num;

	private int MatchId;

	private String TournamentABS;

	private String MatchDate;

	private String StandardMatchDate;

	private int StatusCode;

	private int IngCode;

	private String StatusString;

	private String HomeTeam;

	private int HomeTeamId;

	private int HomeRed;

	private int HomeYellow;

	private int HomeScore;

	private String AwayTeam;

	private int AwayTeamId;

	private int AwayRed;

	private int AwayYellow;

	private int AwayScore;

	private int HomeHalfScore;

	private int AwayHalfScore;

	private int IsLiveText;

	private boolean IsReverse;

	private int MatchId7m;

	private String Score1;

	private String Score2;

	private String Score3;

	private String Score4;

	private String Score5;

	private boolean IsNBA;

	public void setIssue(String Issue) {
		this.Issue = Issue;
	}

	public String getIssue() {
		return this.Issue;
	}

	public void setLotteryId(int LotteryId) {
		this.LotteryId = LotteryId;
	}

	public int getLotteryId() {
		return this.LotteryId;
	}

	public void setNo(int No) {
		this.No = No;
	}

	public int getNo() {
		return this.No;
	}

	public void setNum(String Num) {
		this.Num = Num;
	}

	public String getNum() {
		return this.Num;
	}

	public void setMatchId(int MatchId) {
		this.MatchId = MatchId;
	}

	public int getMatchId() {
		return this.MatchId;
	}

	public void setTournamentABS(String TournamentABS) {
		this.TournamentABS = TournamentABS;
	}

	public String getTournamentABS() {
		return this.TournamentABS;
	}

	public void setMatchDate(String MatchDate) {
		this.MatchDate = MatchDate;
	}

	public String getMatchDate() {
		return this.MatchDate;
	}

	public void setStandardMatchDate(String StandardMatchDate) {
		this.StandardMatchDate = StandardMatchDate;
	}

	public String getStandardMatchDate() {
		return this.StandardMatchDate;
	}

	public void setStatusCode(int StatusCode) {
		this.StatusCode = StatusCode;
	}

	public int getStatusCode() {
		return this.StatusCode;
	}

	public void setIngCode(int IngCode) {
		this.IngCode = IngCode;
	}

	public int getIngCode() {
		return this.IngCode;
	}

	public void setStatusString(String StatusString) {
		this.StatusString = StatusString;
	}

	public String getStatusString() {
		return this.StatusString;
	}

	public void setHomeTeam(String HomeTeam) {
		this.HomeTeam = HomeTeam;
	}

	public String getHomeTeam() {
		return this.HomeTeam;
	}

	public void setHomeTeamId(int HomeTeamId) {
		this.HomeTeamId = HomeTeamId;
	}

	public int getHomeTeamId() {
		return this.HomeTeamId;
	}

	public void setHomeRed(int HomeRed) {
		this.HomeRed = HomeRed;
	}

	public int getHomeRed() {
		return this.HomeRed;
	}

	public void setHomeYellow(int HomeYellow) {
		this.HomeYellow = HomeYellow;
	}

	public int getHomeYellow() {
		return this.HomeYellow;
	}

	public void setHomeScore(int HomeScore) {
		this.HomeScore = HomeScore;
	}

	public int getHomeScore() {
		return this.HomeScore;
	}

	public void setAwayTeam(String AwayTeam) {
		this.AwayTeam = AwayTeam;
	}

	public String getAwayTeam() {
		return this.AwayTeam;
	}

	public void setAwayTeamId(int AwayTeamId) {
		this.AwayTeamId = AwayTeamId;
	}

	public int getAwayTeamId() {
		return this.AwayTeamId;
	}

	public void setAwayRed(int AwayRed) {
		this.AwayRed = AwayRed;
	}

	public int getAwayRed() {
		return this.AwayRed;
	}

	public void setAwayYellow(int AwayYellow) {
		this.AwayYellow = AwayYellow;
	}

	public int getAwayYellow() {
		return this.AwayYellow;
	}

	public void setAwayScore(int AwayScore) {
		this.AwayScore = AwayScore;
	}

	public int getAwayScore() {
		return this.AwayScore;
	}

	public void setHomeHalfScore(int HomeHalfScore) {
		this.HomeHalfScore = HomeHalfScore;
	}

	public int getHomeHalfScore() {
		return this.HomeHalfScore;
	}

	public void setAwayHalfScore(int AwayHalfScore) {
		this.AwayHalfScore = AwayHalfScore;
	}

	public int getAwayHalfScore() {
		return this.AwayHalfScore;
	}

	public void setIsLiveText(int IsLiveText) {
		this.IsLiveText = IsLiveText;
	}

	public int getIsLiveText() {
		return this.IsLiveText;
	}

	public void setIsReverse(boolean IsReverse) {
		this.IsReverse = IsReverse;
	}

	public boolean getIsReverse() {
		return this.IsReverse;
	}

	public void setMatchId7m(int MatchId7m) {
		this.MatchId7m = MatchId7m;
	}

	public int getMatchId7m() {
		return this.MatchId7m;
	}

	public void setScore1(String Score1) {
		this.Score1 = Score1;
	}

	public String getScore1() {
		return this.Score1;
	}

	public void setScore2(String Score2) {
		this.Score2 = Score2;
	}

	public String getScore2() {
		return this.Score2;
	}

	public void setScore3(String Score3) {
		this.Score3 = Score3;
	}

	public String getScore3() {
		return this.Score3;
	}

	public void setScore4(String Score4) {
		this.Score4 = Score4;
	}

	public String getScore4() {
		return this.Score4;
	}

	public void setScore5(String Score5) {
		this.Score5 = Score5;
	}

	public String getScore5() {
		return this.Score5;
	}

	public void setIsNBA(boolean IsNBA) {
		this.IsNBA = IsNBA;
	}

	public boolean getIsNBA() {
		return this.IsNBA;
	}

	@Override
	public String toString() {
		return "NBALive [Issue=" + Issue + ", MatchId="
				+ MatchId + ", StandardMatchDate="
				+ StandardMatchDate + ", StatusCode=" + StatusCode + ", StatusString=" + StatusString + ", IngCode=" + IngCode + ", HomeTeam=" + HomeTeam + ", HomeTeamId=" + HomeTeamId
				+ ", HomeScore=" + HomeScore + ", AwayTeam=" + AwayTeam + ", AwayTeamId="
				+ AwayTeamId + ", AwayScore=" + AwayScore
				+ ", Score1=" + Score1 + ", Score2=" + Score2
				+ ", Score3=" + Score3 + ", Score4=" + Score4 + ", Score5=" + Score5 + ", IsNBA=" + IsNBA + "]";
	}
}
