package helper;

public class GYJInfo {
	
	private String num;
	private String Hteam;
	private String Ateam;
	private String statue;
	private String sp;
	private String chance;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getHteam() {
		return Hteam;
	}
	public void setHteam(String hteam) {
		Hteam = hteam;
	}
	public String getAteam() {
		return Ateam;
	}
	public void setAteam(String ateam) {
		Ateam = ateam;
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
	@Override
	public String toString() {
		return "GYJInfo [num=" + num + ", Hteam=" + Hteam + ", Ateam=" + Ateam + ", statue=" + statue + ", sp=" + sp
				+ ", chance=" + chance + "]";
	}
}
