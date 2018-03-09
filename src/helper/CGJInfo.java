package helper;

public class CGJInfo {
	
	private String num;
	private String team;
	private String statue;
	private String sp;
	private String chance;
	private String tournamentName;
	private String endTime;
	private String selOption;
	private String matchResult;
	
	public String getTournamentName() {
		return tournamentName;
	}



	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}



	public String getEndTime() {
		return endTime;
	}



	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	public String getSelOption() {
		return selOption;
	}



	public void setSelOption(String selOption) {
		this.selOption = selOption;
	}



	public String getMatchResult() {
		return matchResult;
	}



	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}



	public String getNum() {
		return num;
	}



	public void setNum(String num) {
		this.num = num;
	}



	public String getTeam() {
		return team;
	}



	public void setTeam(String team) {
		this.team = team;
	}



	public String getStatue() {
		return statue;
	}



	public void setStatue(String statue) {
		this.statue = statue;
	}



	public String getSp() {
		return sp;
	}



	public void setSp(String sp) {
		this.sp = sp;
	}



	public String getChance() {
		return chance;
	}



	public void setChance(String chance) {
		this.chance = chance;
	}



	public static void main(String[] args) {
		
	}



	@Override
	public String toString() {
		return "CGJInfo [num=" + num + ", team=" + team + ", statue=" + statue + ", sp=" + sp + ", chance=" + chance
				+ ", tournamentName=" + tournamentName + ", endTime=" + endTime + ", selOption=" + selOption
				+ ", matchResult=" + matchResult + "]";
	}


}
