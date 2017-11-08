package helper;

public class IssueObject {
	private String Issue;

	private String StartTime;

	private String EndTime;

	private int IsCross;

	public void setIssue(String Issue) {
		this.Issue = Issue;
	}

	public String getIssue() {
		return this.Issue;
	}

	public void setStartTime(String StartTime) {
		this.StartTime = StartTime;
	}

	public String getStartTime() {
		return this.StartTime;
	}

	public void setEndTime(String EndTime) {
		this.EndTime = EndTime;
	}

	public String getEndTime() {
		return this.EndTime;
	}

	public void setIsCross(int IsCross) {
		this.IsCross = IsCross;
	}

	public int getIsCross() {
		return this.IsCross;
	}
}
