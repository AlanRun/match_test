package datePush;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import com.jdd.fm.core.exception.AesException;

import helper.AppReq;
import helper.DataUrls;
import helper.JzMatchSpInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.DBHelper;
import utils.HttpRequester;
import utils.HttpRespons;
import utils.LogWrite;
import utils.Similarity;

public class CtSpTest {

	static String jdd_jz_url = DataUrls.url_bd;
	static String jz_params = DataUrls.params_204;
	static String gf_ct_url =   "http://i.sporttery.cn/wap/fb_lottery/fb_lottery_match?key=wilo&f_callback=getDataBack&num=";
	static String gf_ct_issue = "http://i.sporttery.cn/wap/fb_lottery/fb_lottery_nums?key=wilo&f_callback=getNumBack&num=";

	/**
	 * 获取jdd传统投注对阵 action=204
	 * 
	 * @return
	 * @throws IOException
	 * @throws AesException
	 */
	public static ArrayList<JzMatchSpInfo> getCtSpFromJdd() throws Exception {
		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();
		
		String json = AppReq.getResStr(jdd_jz_url, jz_params);
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
			 LogWrite.saveToFile(json);
		}

		if (json.contains("无期次数据")) {
			return list;
		}
		
		JSONObject obj = JSONObject.fromObject(json);

		JSONObject data = obj.getJSONObject("data");
		String issue = data.getString("issue");
		JSONArray Matches = data.getJSONArray("Matches");
		for (int j = 0; j < Matches.size(); j++) {
			JzMatchSpInfo jzMatch = new JzMatchSpInfo();
			JSONObject match = (JSONObject) Matches.get(j);

			String No = match.getString("No");
			String NMm = match.getString("NMm");
			String MatchTime = match.getString("MatchTime");
			String HTeam = match.getString("HTeam");
			String VTeam = match.getString("VTeam");
			String MatchID = match.getString("MatchID");
			String OuOdds = match.getString("OuOdds");
			
			jzMatch.setMID(No);
			jzMatch.setNMm(NMm);
			jzMatch.setMatchStartTime(MatchTime);
			jzMatch.setHTeam(HTeam);
			jzMatch.setVTeam(VTeam);
			jzMatch.setIssue(issue);
			jzMatch.setMatchID(MatchID);
			jzMatch.setOuOdds(OuOdds);
			
			list.add(jzMatch);
		}
		return list;
	}
	
	public static void getIssueInfo(String num) throws IOException{
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("gbk");
		HttpRespons hr = request.sendGet(gf_ct_issue + num);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
			
		}

		json = json.trim();
		json = json.substring(11, json.length() - 2);
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject result = obj.getJSONObject("result");
		
		String issue = result.getString("num");
		String start = result.getString("start");
		String end = result.getString("end");
		String last = result.getString("last");
		String next = result.getString("next");
		String prize = result.getString("prize");
		
		LogWrite.saveToFile(issue + " start=" + start + " end=" + end + " prize=" + prize + " next=" + next + " last=" + last);
	}

	public static String unicode2String(String unicode) {
		if (StringUtils.isBlank(unicode))
			return null;
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;

		while ((i = unicode.indexOf("\\u", pos)) != -1) {
			sb.append(unicode.substring(pos, i));
			if (i + 5 < unicode.length()) {
				pos = i + 6;
				sb.append((char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
			}
		}

		return sb.toString();
	}

	/**
	 * 获取官方传统投注对阵
	 * 
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<JzMatchSpInfo> getCtSpFromGF(String num) throws IOException {
		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("gbk");
		HttpRespons hr = request.sendGet(gf_ct_url + num);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
			// LogWrite.saveToFile(json);
		}

		json = json.trim();
		json = json.substring(12, json.length() - 2);
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject result = obj.getJSONObject("result");

		Iterator<?> keys = result.keys();
		while (keys.hasNext()) {
			String key = keys.next().toString();
			JSONObject match = result.getJSONObject(key);
			JzMatchSpInfo jzMatch = new JzMatchSpInfo();

			// String No = match.getString(key);
			String NMm = match.getString("league");
			String MatchDate = match.getString("date");
			String matchStartTime = MatchDate + " " + match.getString("time");
			String HTeam = match.getString("h_cn");
			String VTeam = match.getString("a_cn");

			String MatchID = match.getString("mid");
			
			String h = match.getString("h");
			String d = match.getString("d");
			String a = match.getString("a");
			
			String OuOdds = h + "," + d + "," + a;

			jzMatch.setNMm(NMm);
			jzMatch.setMatchStartTime(matchStartTime);
			jzMatch.setHTeam(HTeam);
			jzMatch.setVTeam(VTeam);
			jzMatch.setMatchID(MatchID);
			jzMatch.setOuOdds(OuOdds);

			list.add(jzMatch);
		}
		return list;
	}

	/**
	 * 比对传统投注对阵 jdd vs 官方
	 * 
	 * @throws IOException
	 * @throws AesException
	 */
	public static void compareJddSpToGf_Ct() throws Exception {
		LogWrite.saveToFile("传统对阵验证");
		ArrayList<JzMatchSpInfo> jddList = getCtSpFromJdd();
		
		if (jddList.size() == 0) {
			LogWrite.saveToFile("jdd无期传统次数据");
			return;
		}
		
		String issue = jddList.get(0).getIssue();
		issue = issue.substring(2);
		getIssueInfo(issue);
		ArrayList<JzMatchSpInfo> gfList = getCtSpFromGF(issue);
		for (int i = 0; i < jddList.size(); i++) {
			JzMatchSpInfo jdd = jddList.get(i);
			JzMatchSpInfo gf = gfList.get(i);
			
			if (!jdd.getMatchStartTime().equals(gf.getMatchStartTime())) {
				LogWrite.saveToFile("start time not match");
			}
			
			LogWrite.saveToFile("jdd=["+jdd.getMID() + "\t" + jdd.getMatchStartTime() + "\t" + jdd.getNMm() + "\t" + jdd.getHTeam() + "\t" + jdd.getVTeam() + "\t" + jdd.getOuOdds());
			LogWrite.saveToFile("gf =["+jdd.getMID()  + "\t" + gf.getMatchStartTime() + "\t" + gf.getNMm() + "\t" + gf.getHTeam() + "\t" + gf.getVTeam() + "\t" + gf.getOuOdds());
			try {
				LogWrite.saveToFile(Similarity.SimilarDegree(gf.getNMm(), jdd.getNMm())  + "\t" + Similarity.SimilarDegree(gf.getHTeam(), jdd.getHTeam())  + "\t" + Similarity.SimilarDegree(gf.getVTeam(), jdd.getVTeam()));
			} catch (Exception e) {
			}
			
			LogWrite.saveToFile("\n");
		}
	}

	public static String checkSp(String value) {
		String tmp = "";
		if (value.substring(0, 1).equals("+")) {
			value = value.substring(1, value.length());
		}

		String[] arr = value.split(",");

		for (int k = 0; k < arr.length; k++) {
			String s = arr[k];

			if (!s.contains(".")) {
				s = s + ".00";
			}

			// 补齐小数点后两位
			if (!s.substring(s.length() - 3, s.length() - 2).equals(".")) {
				s = s + "0";
			}
			tmp = tmp + s;
			if (k != arr.length - 1) {
				tmp = tmp + ",";
			}
		}
		return tmp;
	}
	
	public static ArrayList<JzMatchSpInfo> getFinalSpFromData(String issue){
		LogWrite.saveToFile("获取jdd传统比分: " + issue);
		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();
		
		String db = "jdd_basedata";
		String sql = "SELECT s_match_no, s_host_team, s_visit_team, s_full_score, s_match_result FROM `basedata_match_ctzc` where s_issue_name = " + issue + " and n_lottery_id = 1 order by cast(s_match_no as signed);";
		DBHelper db1 = new DBHelper(db, sql);
		ResultSet ret = null;
		LogWrite.saveToFile("IssueNo \t HTeam \t VTeam \t full_soccer \t match_result");
		System.out.println("IssueNo \t HTeam \t VTeam \t full_soccer \t match_result");
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				JzMatchSpInfo match = new JzMatchSpInfo();
				String IssueNo = ret.getString(1);
				String HTeam = ret.getString(2);
				String VTeam = ret.getString(3);
				String full_soccer = ret.getString(4);
				String match_result = ret.getString(5);
				
				LogWrite.saveToFile(IssueNo + "\t" + HTeam + "\t" + VTeam + "\t" + full_soccer + "\t" + match_result);
				System.out.println(IssueNo + "\t" + HTeam + "\t" + VTeam + "\t" + full_soccer + "\t" + match_result);
				
				match.setIssue(IssueNo);
				match.setHTeam(HTeam);
				match.setVTeam(VTeam);
				match.setSoccer(full_soccer);
				match.setFinalSp(full_soccer);
				list.add(match);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public static void main(String[] args) throws Exception {
//		getFinalSpFromData("2017135");
//		getFinalSpFromData("2017136");
		compareJddSpToGf_Ct();
	}

}
