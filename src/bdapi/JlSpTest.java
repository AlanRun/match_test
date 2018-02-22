package bdapi;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.jdd.fm.core.exception.AesException;

import helper.AppReq;
import helper.DataUrls;
import helper.JlMatchSpInfo;
import helper.JzMatchSpInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.DBHelper;
import utils.DateAndTime;
import utils.HttpRequester;
import utils.HttpRespons;
import utils.LogWrite;
import utils.Similarity;

public class JlSpTest {
	static String gf_jl_url = "http://info.sporttery.cn/interface/interface_mixed.php?action=bk_list&_=";
	static String jdd_jl_url = DataUrls.url_bd;
	static String jl_params = DataUrls.params_2031;

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

	/**
	 * 获取jdd竞篮投注对阵赔率 action=2031
	 * 
	 * @return
	 * @throws IOException
	 * @throws AesException
	 */
	public static ArrayList<JlMatchSpInfo> getJLSpFromJdd() throws Exception {
		ArrayList<JlMatchSpInfo> list = new ArrayList<JlMatchSpInfo>();
		
		String json = AppReq.getResStr(jdd_jl_url, jl_params);
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
			 LogWrite.saveToFile(json);
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");
		for (int i = 0; i < data.size(); i++) {
			JSONObject wk = (JSONObject) data.get(i);
			String issue = wk.getString("issue");
			JSONArray Matches = wk.getJSONArray("Matches");
			for (int j = 0; j < Matches.size(); j++) {
				JlMatchSpInfo jlMatch = new JlMatchSpInfo();
				JSONObject match = (JSONObject) Matches.get(j);

				String MID = match.getString("MID");
				String NMm = match.getString("NMm");
				String matchStartTime = match.getString("ETime");
				String HTeam = match.getString("HTeam");
				String VTeam = match.getString("VTeam");
				String HomeRank = match.getString("HomeRank");
				String AwayRank = match.getString("AwayRank");
				String SpSF = match.getString("SpSF");
				String SpRFSF = match.getString("SpRFSF");
				String SpDXF = match.getString("SpDXF");
				String SpSFC = match.getString("SpSFC");
				String PSState = match.getString("PSState");

				jlMatch.setMID(MID);
				jlMatch.setNMm(NMm);
				jlMatch.setMatchStartTime(matchStartTime);
				jlMatch.setHTeam(HTeam);
				jlMatch.setVTeam(VTeam);
				jlMatch.setHomeRank(HomeRank);
				jlMatch.setAwayRank(AwayRank);
				jlMatch.setIssue(issue);

				jlMatch.setSpSF((SpSF));
				jlMatch.setSpRFSF((SpRFSF));
				jlMatch.setSpDXF((SpDXF));
				jlMatch.setSpSFC((SpSFC));
				jlMatch.setPSState(PSState);

				if (MID.equals("171019320")) {
					System.out.println(jlMatch.toString());
					LogWrite.saveToFile(jlMatch.toString());
				}
				
				
				list.add(jlMatch);
			}
		}
		return list;
	}

	public static String checkSpJdd(String value) {
		String tmp = "";
		String[] arr = value.split("\\|");
		for (int k = 0; k < arr.length; k++) {
			String s = arr[k];
			// 补齐小数点后两位
			for (int i = 0; i < 3; i++) {
				if (!s.contains(".")) {
					s = s + ".00";
				}
			}
			tmp = tmp + s;
			if (k != arr.length - 1) {
				tmp = tmp + "|";
			}
		}
		return tmp;
	}

	/**
	 * 获取官方竞篮投注对阵赔率
	 * 
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<JlMatchSpInfo> getJLSpFromGF() throws IOException {
		Date d = new Date();
		ArrayList<JlMatchSpInfo> list = new ArrayList<JlMatchSpInfo>();

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("gbk");
		HttpRespons hr = request.sendGet(gf_jl_url + d.getTime());
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {

		}
		String[] s1 = json.split(";");
		String[] s2 = s1[2].split("=");
		String data = s2[1];

		JSONArray matchArr = JSONArray.fromObject(data);
		System.out.println(data);
		for (int i = 0; i < matchArr.size(); i++) {
			JSONArray match = (JSONArray) matchArr.get(i);
			JlMatchSpInfo jlMatch = new JlMatchSpInfo();
			String sfState = "0";
			String rfsfState = "0";
			String dxfState = "0";
			String sfcState = "0";
			for (int j = 0; j < match.size(); j++) {
				String value = match.get(j).toString();
				value = value.replaceAll("\"", "");
				value = value.substring(1, value.length() - 1);
				// LogWrite.saveToFile(value);
				switch (j) {
				case 0:
					String[] info = value.split(",");
					String MID = info[0];
					String NMm = info[1];
					String matchStartTime = "20" + info[4] + ":00";
					String HTeam = info[9];
					String VTeam = info[8];
					String HomeRank = info[10];
					String AwayRank = info[11];
					String issue = info[12].replaceAll("-", "");
					MID = issue.substring(2, issue.length()) + MID.substring(2, MID.length());

					jlMatch.setMID(MID);
					jlMatch.setNMm(NMm);
					jlMatch.setMatchStartTime(matchStartTime);
					jlMatch.setHTeam(HTeam);
					jlMatch.setVTeam(VTeam);
					jlMatch.setHomeRank(HomeRank);
					jlMatch.setAwayRank(AwayRank);
					jlMatch.setIssue(issue);
					break;
				case 1:
					if (value.equals(",,")) {
						sfState = "2";
						value = "0.00,0.00";
					} else {
						sfState = value.substring(value.length() - 1, value.length());
						value = value.substring(0, value.length() - 2);
					}

					value = checkSp(value);

					String SpSF = value.replaceAll(",", "|");
					jlMatch.setSpSF(SpSF);
					break;
				case 2:
					if (value.equals(",,,")) {
						rfsfState = "2";
						value = "0.00,0.00,0.00";
					} else {
						rfsfState = value.substring(value.length() - 1, value.length());
						value = value.substring(0, value.length() - 2);
					}

					value = checkSp(value);

					String SpRFSF = value.replaceAll(",", "|");
					jlMatch.setSpRFSF(SpRFSF);
					break;
				case 3:
					if (value.equals(",,,")) {
						dxfState = "2";
						value = "0.00,0.00,0.00";
					} else {
						dxfState = value.substring(value.length() - 1, value.length());
						value = value.substring(0, value.length() - 2);
					}

					value = checkSp(value);

					String SpDXF = value.replaceAll(",", "|");
					jlMatch.setSpDXF(SpDXF);
					break;
				case 4:
					if (value.equals(",,,,,,,,,,,,0,")) {
						dxfState = "2";
						value = "0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00";
					} else {
						sfcState = value.substring(value.length() - 1, value.length());
						value = value.substring(0, value.length() - 4);
					}

					value = checkSp(value);

					String SpSFC = value.replaceAll(",", "|");
					jlMatch.setSpSFC(SpSFC);
					break;
				default:
					break;
				}
			}
			String PSState = rfsfState + "," + sfState + "," + sfcState + "," + dxfState;
			jlMatch.setPSState(PSState);
			list.add(jlMatch);
		}
		return list;
	}

	/**
	 * 比对竞篮投注赔率jdd vs 官方
	 * @throws IOException
	 * @throws AesException
	 * @throws ParseException 
	 */
	public static void compareJddSpToGf_JL() throws Exception {
		LogWrite.saveToFile("竞篮对阵、及时sp验证");
		ArrayList<JlMatchSpInfo> jddList = getJLSpFromJdd();
		if (jddList.size() == 0) {
			LogWrite.saveToFile("jdd无竞篮对阵数据");
			return;
		}
		ArrayList<JlMatchSpInfo> gfList = getJLSpFromGF();
		LogWrite.saveToFile("jddCount=" + jddList.size() + " gfCount=" + gfList.size());
		for (int i = 0; i < jddList.size(); i++) {
			JlMatchSpInfo jdd = jddList.get(i);
			
			for (int j = 0; j < gfList.size(); j++) {
				JlMatchSpInfo gf = gfList.get(j);
				if (jdd.getMID().equals(gf.getMID())) {
					LogWrite.saveToFile(jdd.toString());
					LogWrite.saveToFile(gf.toString());
					try {
						LogWrite.saveToFile(Similarity.SimilarDegree(jdd.getNMm(), gf.getNMm())  + "\t" + Similarity.SimilarDegree(jdd.getHTeam(), gf.getHTeam() )  + "\t" + Similarity.SimilarDegree(jdd.getVTeam(), gf.getVTeam()));
					} catch (Exception e) {
						
					}
					
					String time = DateAndTime.getEndTime(gfList.get(j).getMatchStartTime(), 30);
					if (!jdd.getMatchStartTime().equals(time)) {
						LogWrite.saveToFile("matchtime not match");
						LogWrite.saveToFile("jdd=" + jdd.getMatchStartTime());
						LogWrite.saveToFile("gf =" + time);
					}

					if (!jdd.getIssue().equals(gf.getIssue())) {
						LogWrite.saveToFile("Issue not match");
						LogWrite.saveToFile("jdd=" + jdd.toString());
						LogWrite.saveToFile("gf =" + gf.toString());
					}

					String jddSF = jdd.getSpSF();
					String jddRFSF = jdd.getSpRFSF();
					String jddDXF = jdd.getSpDXF();
					String jddSFC = jdd.getSpSFC();
					String jddPSState = jdd.getPSState();

					String gfSF = gf.getSpSF();
					String gfRFSF = gf.getSpRFSF();
					String gfDXF = gf.getSpDXF();
					String gfSFC = gf.getSpSFC();
					String gfPsstate = gf.getPSState();
					
					if (!jddPSState.equals(gfPsstate)) {
						LogWrite.saveToFile("Psstate not match");
						LogWrite.saveToFile("jdd=" + jddPSState);
						LogWrite.saveToFile("gf =" + gfPsstate);
					}
					
					if (!jddSF.equals(gfSF)) {
						LogWrite.saveToFile("SPSF not match");
						LogWrite.saveToFile("jdd=" + jddSF);
						LogWrite.saveToFile("gf =" + gfSF);
					}

					if (!jddRFSF.equals(gfRFSF)) {
						LogWrite.saveToFile("SPRFSF not match");
						LogWrite.saveToFile("jdd=" + jddRFSF);
						LogWrite.saveToFile("gf =" + gfRFSF);
					}
					
					if (!jddDXF.equals(gfDXF)) {
						LogWrite.saveToFile("SPDXF not match");
						LogWrite.saveToFile("jdd=" + jddDXF);
						LogWrite.saveToFile("gf =" + gfDXF);
					}

					if (!jddSFC.equals(gfSFC)) {
						LogWrite.saveToFile("SPSFC not match");
						LogWrite.saveToFile("jdd=" + jddSFC);
						LogWrite.saveToFile("gf =" + gfSFC);
					}
				}
			}
			LogWrite.saveToFile("\n");
		}
	}
	
	public static ArrayList<JzMatchSpInfo> getFinalSpFromData(String issue){
		LogWrite.saveToFile("获取jdd竞篮比分: " + issue);
		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();
		
		String db = "jdd_basedata";
		String sql = "SELECT s_issue_match_name, s_host_team,s_visit_team,s_full_score FROM `basedata_match_jclq` where s_issue_name=" + issue +" order by cast(s_issue_match_name as signed);";
		DBHelper db1 = new DBHelper(db, sql);
		ResultSet ret = null;
		
		LogWrite.saveToFile("IssueNo \t HTeam \t VTeam \t full_soccer");
		System.out.println("IssueNo \t HTeam \t VTeam \t full_soccer");

		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				JzMatchSpInfo match = new JzMatchSpInfo();
				String IssueNo = ret.getString(1);
				String HTeam = ret.getString(2);
				String VTeam = ret.getString(3);
				String full_score = ret.getString(4);
				
				LogWrite.saveToFile(IssueNo + "\t" + HTeam + "\t" + VTeam + "\t" + full_score);
				System.out.println(IssueNo + "\t" + HTeam + "\t" + VTeam + "\t" + full_score);
				
				match.setIssue(IssueNo);
				match.setHTeam(HTeam);
				match.setVTeam(VTeam);
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
//		for (int i = 0; i < 1000; i++) {
//			getJLSpFromJdd();
//			Thread.sleep(2000);
//		}
//		getFinalSpFromData("20170917");
		compareJddSpToGf_JL();
	}
}
