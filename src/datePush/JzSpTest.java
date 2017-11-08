package datePush;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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

public class JzSpTest {
	static String gf_jz_url = "http://info.sporttery.cn/interface/interface_mixed.php?action=fb_list&_=";
	static String jdd_jz_url = DataUrls.bd_url;
	static String jz_params = DataUrls.params_202;;
	
	/**
	 * 获取jdd竞足投注对阵赔率 action=202
	 * @return
	 * @throws IOException
	 * @throws AesException
	 */
	public static ArrayList<JzMatchSpInfo> getJZSpFromJdd() throws IOException, AesException {
		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();
		
		String json = AppReq.getResStr(jdd_jz_url, jz_params);
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
				JzMatchSpInfo jzMatch = new JzMatchSpInfo();
				JSONObject match = (JSONObject) Matches.get(j);

				String MID = match.getString("MID");
				String NMm = match.getString("NMm");
				String Rq = match.getString("Rq");
				String matchStartTime = match.getString("matchStartTime");
				String HTeam = match.getString("HTeam");
				String VTeam = match.getString("VTeam");
//				String HomeRank = match.getString("HomeFieldRank");
//				String AwayRank = match.getString("AwayFieldRank");
				String SpBQC = match.getString("SpBQC");
				String SpCBF = match.getString("SpCBF");
				String SpRQSPF = match.getString("SpRQSPF");
				String SpSPF = match.getString("SpSPF");
				String SpZJQ = match.getString("SpZJQ");
				String PSState = match.getString("PSState");
				String ETime = match.getString("ETime");

				jzMatch.setMID(MID);
				jzMatch.setNMm(NMm);
				jzMatch.setRq(Rq);
				jzMatch.setMatchStartTime(matchStartTime);
				jzMatch.setETime(ETime);
				jzMatch.setHTeam(HTeam);
				jzMatch.setVTeam(VTeam);
//				jzMatch.setHomeRank(HomeRank);
//				jzMatch.setAwayRank(AwayRank);
				jzMatch.setIssue(issue);
				jzMatch.setPSState(PSState);
				
				jzMatch.setSpRQSPF(checkSpJdd(SpRQSPF));
				jzMatch.setSpCBF(checkSpJdd(SpCBF));
				jzMatch.setSpZJQ(checkSpJdd(SpZJQ));
				jzMatch.setSpBQC(checkSpJdd(SpBQC));
				jzMatch.setSpSPF(checkSpJdd(SpSPF));
				list.add(jzMatch);
			}
		}
		return list;
	}

	/**
	 * 获取官方竞足投注赔率
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<JzMatchSpInfo> getJZSpFromGF() throws IOException {
		Date d = new Date();
		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("gbk");
		HttpRespons hr = request.sendGet(gf_jz_url + d.getTime());
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
//			System.out.println(json);
		}
		String[] s1 = json.split(";");
		String[] s2 = s1[2].split("=");
		String data = s2[1];

//		LogWrite.saveToFile(data);
		JSONArray matchArr = JSONArray.fromObject(data);
		for (int i = 0; i < matchArr.size(); i++) {
			JSONArray match = (JSONArray) matchArr.get(i);
			JzMatchSpInfo jzMatch = new JzMatchSpInfo();
			String spfState = "0";
			String bqcState = "0";
			String rqspfState = "0";
			String cbfState = "0";
			String zjqState = "0";
			for (int j = 0; j < match.size(); j++) {
				String value = match.get(j).toString();
				value = value.replaceAll("\"", "");
				value = value.substring(1, value.length() - 1);
//				LogWrite.saveToFile(value);
				switch (j) {
				case 0:
					String[] info = value.split(",");
					String MID = info[0];
					String NMm = info[1];
					String[] vs = info[2].split("\\$");
					String Rq = (vs[1]);
					if (Rq.contains("+")) {
						Rq = Rq.substring(1, Rq.length());
					}
					String matchStartTime = "20" + info[3] + ":00";
					String HTeam = info[7];
					String VTeam = info[8];
					String HomeRank = info[9];
					String AwayRank = info[10];
					String issue = info[11].replaceAll("-", "");
					MID = issue.substring(2, issue.length()) + MID.substring(2, MID.length());

					jzMatch.setMID(MID);
					jzMatch.setNMm(NMm);
					jzMatch.setRq(Rq);
					jzMatch.setMatchStartTime(matchStartTime);
					jzMatch.setHTeam(HTeam);
					jzMatch.setVTeam(VTeam);
					jzMatch.setHomeRank(HomeRank);
					jzMatch.setAwayRank(AwayRank);
					jzMatch.setIssue(issue);
					break;
				case 1:
					if (value.equals(",,,")) {
						rqspfState = "2";
						value = "0.00,0.00,0.00";
					} else {
						rqspfState = value.substring(value.length() - 1, value.length());
						value = value.substring(0, value.length() - 2);
					}
					value = checkSp(value);

					String SpRQSPF = value.replaceAll(",", "|");
					jzMatch.setSpRQSPF(SpRQSPF);
					break;
				case 2:
					
					if (value.equals(",,,")) {
						cbfState = "2";
						value = "0.00,0.00,0.00";
					} else {
						cbfState = value.substring(value.length() - 1, value.length());
						value = value.substring(0, value.length() - 4);
					}

					value = checkSp(value);

					String SpCBF = value.replaceAll(",", "|");
					jzMatch.setSpCBF(SpCBF);
					break;
				case 3:
					
					if (value.equals(",,,,,,,,")) {
						zjqState = "2";
						value = "0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00";
					} else {
						zjqState = value.substring(value.length() - 1, value.length());
						value = value.substring(0, value.length() - 2);
					}

					value = checkSp(value);

					String SpZJQ = value.replaceAll(",", "|");
					jzMatch.setSpZJQ(SpZJQ);
					break;
				case 4:
					if (value.equals(",,,,,,,,,")) {
						bqcState = "2";
						value = "0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00";
					} else {
						bqcState = value.substring(value.length() - 1, value.length());
						value = value.substring(0, value.length() - 2);
					}

					value = checkSp(value);

					String SpBQC = value.replaceAll(",", "|");
					jzMatch.setSpBQC(SpBQC);
					break;
				case 5:
					
					if (value.equals(",,,")) {
						spfState = "2";
						value = "0.00,0.00,0.00";
					} else {
						spfState = value.substring(value.length() - 1, value.length());
						value = value.substring(0, value.length() - 2);
					}
					
					String SpSPF = value.replaceAll(",", "|");
					jzMatch.setSpSPF(SpSPF);
					break;
				default:
					break;
				}
			}
			String PSState = rqspfState + "," + zjqState + "," + cbfState + "," + bqcState + "," + spfState;
			jzMatch.setPSState(PSState);
			list.add(jzMatch);
		}
		return list;
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
	 * 比对竞足投注赔率jdd vs 官方
	 * @throws IOException
	 * @throws AesException
	 */
	public static void compareJddSpToGf_JZ() throws IOException, AesException{
		LogWrite.saveToFile("竞足对阵、及时sp验证");
		ArrayList<JzMatchSpInfo> jddList = getJZSpFromJdd();
		if (jddList.size() == 0) {
			LogWrite.saveToFile("jdd无竞足对阵数据");
			return;
		}
		ArrayList<JzMatchSpInfo> gfList = getJZSpFromGF();
		LogWrite.saveToFile("jddCount=" + jddList.size() + " gfCount=" + gfList.size());
		
		for (int i = 0; i < jddList.size(); i++) {
			JzMatchSpInfo jdd = jddList.get(i);
			LogWrite.saveToFile(jdd.getMID());
			LogWrite.saveToFile(jdd.toString());
			
			for (int j = 0; j < gfList.size(); j++) {
				JzMatchSpInfo gf = gfList.get(j);
				if (jdd.getMID().equals(gf.getMID())) {
					LogWrite.saveToFile(gf.toString());
					try {
						LogWrite.saveToFile(Similarity.SimilarDegree(gf.getNMm(), jdd.getNMm())  + "\t" + Similarity.SimilarDegree(gf.getHTeam(), jdd.getHTeam())  + "\t" + Similarity.SimilarDegree(gf.getVTeam(), jdd.getVTeam()));
					} catch (Exception e) {
						
					}
					
					if (!jdd.getMatchStartTime().equals(gf.getMatchStartTime())) {
						LogWrite.saveToFile("start time not match");
						LogWrite.saveToFile("jdd=[" + jdd.toString());
						LogWrite.saveToFile("gf =[" + gf.toString());
					}

					if (!jdd.getRq().equals(gf.getRq())) {
						LogWrite.saveToFile("RQ not match");
						LogWrite.saveToFile("jdd=[" +jdd.toString());
						LogWrite.saveToFile("gf =[" +gf.toString());
					}

					if (!jdd.getIssue().equals(gf.getIssue())) {
						LogWrite.saveToFile("Issue not match");
						LogWrite.saveToFile("jdd=[" +jdd.toString());
						LogWrite.saveToFile("gf =[" +gf.toString());
					}

					String jddBQC = jdd.getSpBQC();
					String jddCBF = jdd.getSpCBF();
					String jddRQSPF = jdd.getSpRQSPF();
					String jddSPF = jdd.getSpSPF();
					String jddZJQ = jdd.getSpZJQ();
					String jddPSState = jdd.getPSState();

					String gfBQC = gf.getSpBQC();
					String gfCBF = gf.getSpCBF();
					String gfRQSPF = gf.getSpRQSPF();
					String gfSPF = gf.getSpSPF();
					String gfZJQ = gf.getSpZJQ();
					String gfPsstate = gf.getPSState();
					
					if (!jddPSState.equals(gfPsstate)) {
						LogWrite.saveToFile("Psstate not match");
						LogWrite.saveToFile("jdd=[" +jddPSState);
						LogWrite.saveToFile("gf =[" +gfPsstate);
					}
					
					if (!jddSPF.equals(gfSPF)) {
						LogWrite.saveToFile("SPSPF not match");
						LogWrite.saveToFile("jdd=[" +jddSPF);
						LogWrite.saveToFile("gf =[" +gfSPF);
					}

					if (!jddRQSPF.equals(gfRQSPF)) {
						LogWrite.saveToFile("SPRQSPF not match");
						LogWrite.saveToFile("jdd=[" +jddRQSPF);
						LogWrite.saveToFile("gf =[" +gfRQSPF);
					}

					if (!jddBQC.equals(gfBQC)) {
						LogWrite.saveToFile("SPBQC not match");
						LogWrite.saveToFile("jdd=[" +jddBQC);
						LogWrite.saveToFile("gf =[" +gfBQC);
					}

					if (!jddZJQ.equals(gfZJQ)) {
						LogWrite.saveToFile("SPZJQ not match");
						LogWrite.saveToFile("jdd=[" +jddZJQ);
						LogWrite.saveToFile("gf =[" +gfZJQ);
					}

					if (!jddCBF.equals(gfCBF)) {
						LogWrite.saveToFile("SPCBF not match");
						LogWrite.saveToFile("jdd=[" +jddCBF);
						LogWrite.saveToFile("gf =[" +gfCBF);
					}
					break;
				}
			}
			LogWrite.saveToFile("\n");
		}
	}

	public static ArrayList<JzMatchSpInfo> getFinalSoccerFromData(String issue){
		LogWrite.saveToFile("获取jdd竞足比分: " + issue);
		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();
		
		String db = "jdd_basedata";
		String sql = "SELECT s_issue_match_name, s_host_team,s_visit_team,n_rq, s_half_score, s_full_score FROM `basedata_match_jczq` where s_issue_name = " + issue + " order by cast(s_match_no as signed) desc;";
		DBHelper db1 = new DBHelper(db, sql);
		ResultSet ret = null;
		LogWrite.saveToFile("IssueNo \t HTeam \t VTeam \t Rq \t half_soccer \t full_soccer");
		System.out.println("IssueNo \t HTeam \t VTeam \t Rq \t half_soccer \t full_soccer");
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				JzMatchSpInfo match = new JzMatchSpInfo();
				String IssueNo = ret.getString(1);
				String HTeam = ret.getString(2);
				String VTeam = ret.getString(3);
				String Rq = ret.getString(4);
				String half_soccer = ret.getString(5);
				String full_soccer = ret.getString(6);
				
				LogWrite.saveToFile(IssueNo + "\t" + HTeam + "\t" + VTeam + "\t" + Rq + "\t" + half_soccer + "\t" + full_soccer);
				System.out.println(IssueNo + "\t" + HTeam + "\t" + VTeam + "\t" + Rq + "\t" + half_soccer + "\t" + full_soccer);
				
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

	public static void main(String[] args) throws IOException, AesException {
		compareJddSpToGf_JZ();
//		getFinalSoccerFromData("20170918");
	}
}
