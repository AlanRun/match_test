package helper;

public class FootballMatch {
	// 期次
	private String I;

	// 玩法
	private int LId;

	// 序号
	private int NO;

	// 序号
	private String NUM;

	// 比赛ID
	private String MId;

	// 赛事简称
	private String TABS;

	// 比赛时间 毫秒
	private String MD;

	// 比赛时间 yyyy-MM-dd HH:mm
	private String SMD;

	// 状态 6 进行中，100完场
	private int SC;

	// 比赛状态，-2 其他，-1 无直播，0 未开始， 1 完成， 2 进行中
	private int IC;

	// 状态说明
	private String SS;

	// 主队名
	private String HT;

	// 主队ID
	private int HTD;

	// 主队红牌
	private int HR;

	// 主队黄牌
	private int HY;

	// 主队比分
	private int HS;

	// 客队名
	private String AT;

	// 客队ID
	private int ATD;

	// 客队红牌
	private int AR;

	// 客队黄牌
	private int AY;

	// 客队比分
	private int AS;

	// 主队半场得分
	private int HHS;

	// 客队半场得分
	private int AHS;

	// 有无文字直播，1 有，0 无
	private int IT;

	// true 主客颠倒， false
	private boolean IR;

	// 数据源， 1 雷达， 2 opta 3 NBA
	private int SO;
	
	private String scoreHalf;
	
	private String scoreNormal;

	public String getScoreHalf() {
		return scoreHalf;
	}

	public void setScoreHalf(String scoreHalf) {
		this.scoreHalf = scoreHalf;
	}

	public String getScoreNormal() {
		return scoreNormal;
	}

	public void setScoreNormal(String scoreNormal) {
		this.scoreNormal = scoreNormal;
	}

	public String getI() {
		return I;
	}

	public void setI(String i) {
		I = i;
	}

	public int getLId() {
		return LId;
	}

	public void setLId(int lId) {
		LId = lId;
	}

	public int getNO() {
		return NO;
	}

	public void setNO(int nO) {
		NO = nO;
	}

	public String getNUM() {
		return NUM;
	}

	public void setNUM(String nUM) {
		NUM = nUM;
	}

	public String getMId() {
		return MId;
	}

	public void setMId(String mId) {
		MId = mId;
	}

	public String getTABS() {
		return TABS;
	}

	public void setTABS(String tABS) {
		TABS = tABS;
	}

	public String getMD() {
		return MD;
	}

	public void setMD(String mD) {
		MD = mD;
	}

	public String getSMD() {
		return SMD;
	}

	public void setSMD(String sMD) {
		SMD = sMD;
	}

	public int getSC() {
		return SC;
	}

	public void setSC(int sC) {
		SC = sC;
	}

	public int getIC() {
		return IC;
	}

	public void setIC(int iC) {
		IC = iC;
	}

	public String getSS() {
		return SS;
	}

	public void setSS(String sS) {
		SS = sS;
	}

	public String getHT() {
		return HT;
	}

	public void setHT(String hT) {
		HT = hT;
	}

	public int getHTD() {
		return HTD;
	}

	public void setHTD(int hTD) {
		HTD = hTD;
	}

	public int getHR() {
		return HR;
	}

	public void setHR(int hR) {
		HR = hR;
	}

	public int getHY() {
		return HY;
	}

	public void setHY(int hY) {
		HY = hY;
	}

	public int getHS() {
		return HS;
	}

	public void setHS(int hS) {
		HS = hS;
	}

	public String getAT() {
		return AT;
	}

	public void setAT(String aT) {
		AT = aT;
	}

	public int getATD() {
		return ATD;
	}

	public void setATD(int aTD) {
		ATD = aTD;
	}

	public int getAR() {
		return AR;
	}

	public void setAR(int aR) {
		AR = aR;
	}

	public int getAY() {
		return AY;
	}

	public void setAY(int aY) {
		AY = aY;
	}

	public int getAS() {
		return AS;
	}

	public void setAS(int aS) {
		AS = aS;
	}

	public int getHHS() {
		return HHS;
	}

	public void setHHS(int hHS) {
		HHS = hHS;
	}

	public int getAHS() {
		return AHS;
	}

	public void setAHS(int aHS) {
		AHS = aHS;
	}

	public int getIT() {
		return IT;
	}

	public void setIT(int iT) {
		IT = iT;
	}

	public boolean isIR() {
		return IR;
	}

	public void setIR(boolean iR) {
		IR = iR;
	}

	public int getSO() {
		return SO;
	}

	public void setSO(int sO) {
		SO = sO;
	}

	@Override
	public String toString() {
		return "FootballMatch [I=" + I + ", LId=" + LId + ", NUM=" + NUM + ", MId=" + MId + ", TABS="
				+ TABS + ", SMD=" + SMD + ", SC=" + SC + ", IC=" + IC + ", SS=" + SS + ", HT=" + HT + ", HTD=" + HTD
				+ ", AT=" + AT + ", ATD=" + ATD + ", IT=" + IT + ", IR=" + IR + ", SO=" + SO + ", scoreHalf="
				+ scoreHalf + ", scoreNormal=" + scoreNormal + "]";
	}
	
}