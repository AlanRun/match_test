package helper;

import java.util.HashMap;
import java.util.Map;

public class DataHelper {

	Map<String, String> SC = new HashMap<String, String>();
	Map<String, String> IC = new HashMap<String, String>();
	Map<String, String> SS = new HashMap<String, String>();
	Map<String, String> MID = new HashMap<String, String>();

	public void setSC() {
		SC.put("0", "未开始");
		SC.put("6", "进行中");
		SC.put("7", "进行中");
		SC.put("31", "进行中");
		SC.put("32", "进行中");
		SC.put("33", "进行中");
		SC.put("34", "进行中");
		SC.put("40", "进行中");
		SC.put("41", "进行中");
		SC.put("42", "进行中");
		SC.put("50", "进行中");
		SC.put("60", "未开始");
		SC.put("61", "未开始");
		SC.put("70", "未开始");
		SC.put("90", "未开始");
		SC.put("100", "未开始");
		SC.put("110", "已完成");
		SC.put("120", "已完成");
	}

	public Map<String, String> getSC() {
		setSC();
		return SC;
	}

	public void setIC() {
		SC.put("-2", "未开始");
		SC.put("-1", "进行中");
		SC.put("0", "进行中");
		SC.put("1", "进行中");
		SC.put("2", "进行中");
	}

	public Map<String, String> getIC() {
		setIC();
		return IC;
	}

}
