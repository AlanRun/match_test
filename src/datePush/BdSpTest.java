package datePush;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.jdd.fm.core.exception.AesException;

import helper.AppReq;
import helper.DataUrls;
import helper.JzMatchSpInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.DBHelper;
import utils.DateAndTime;
import utils.HttpRequester;
import utils.HttpRespons;
import utils.LogWrite;

public class BdSpTest {

	public static String jdd_jz_url = DataUrls.bd_url;
	public static String bd_params = DataUrls.params_229;
	public static String gf_bd_rqspf = "http://www.bjlot.com/data/200ParlayGetGame.xml"; // 胜平负
	public static String gf_bd_sxds = "http://www.bjlot.com/data/210ParlayGetGame.xml"; // 上下单双
	public static String gf_bd_url = "http://www.bjlot.com/data/220ParlayGetGame.xml"; //
	public static String gf_bd_zjq = "http://www.bjlot.com/data/230ParlayGetGame.xml"; // 总进球
	public static String gf_bd_bqc = "http://www.bjlot.com/data/240ParlayGetGame.xml"; // 半全场
	public static String gf_bd_bf = "http://www.bjlot.com/data/250ParlayGetGame.xml"; // 单场比分
	public static String gf_bd_bc = "http://www.bjlot.com/data/260ParlayGetGame.xml"; // 半场比分
	public static String gf_bd_sf = "http://www.bjlot.com/data/270ParlayGetGame.xml"; // 胜负过关

	public static String bd_final_rqspf = "http://www.bjlot.com/data/200ParlayGetGame_"; // 胜平负
	public static String bd_final_sxds = "http://www.bjlot.com/data/210ParlayGetGame_"; // 上下单双
	public static String bd_final_url = "http://www.bjlot.com/data/220ParlayGetGame_"; //
	public static String bd_final_zjq = "http://www.bjlot.com/data/230ParlayGetGame_"; // 总进球
	public static String bd_final_bqc = "http://www.bjlot.com/data/240ParlayGetGame_"; // 半全场
	public static String bd_final_bf = "http://www.bjlot.com/data/250ParlayGetGame_"; // 单场比分
	public static String bd_final_bc = "http://www.bjlot.com/data/260ParlayGetGame_"; // 半场比分
	public static String bd_final_sf = "http://www.bjlot.com/data/270ParlayGetGame_"; // 胜负过关

	public static String issue;

	public static String getIssue() {
		return issue;
	}

	public static void setIssue(String issue) {
		BdSpTest.issue = issue;
	}

	/**
	 * 获取jdd北单投注对阵 action=229
	 * 
	 * @return
	 * @throws IOException
	 * @throws AesException
	 */
	public static ArrayList<JzMatchSpInfo> getBdSpFromJdd() throws Exception {
		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();
		
		String json = AppReq.getResStr(jdd_jz_url, bd_params);
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

			setIssue(issue);

			JSONArray matchList = wk.getJSONArray("matchList");
			for (int j = 0; j < matchList.size(); j++) {
				JSONObject matchs = (JSONObject) matchList.get(j);
				JSONArray matchlist = matchs.getJSONArray("matchlist");
				for (int k = 0; k < matchlist.size(); k++) {
					JzMatchSpInfo jzMatch = new JzMatchSpInfo();
					JSONObject match = (JSONObject) matchlist.get(k);
					String IssueNo = match.getString("IssueNo");
					String NMm = match.getString("NMm");
					String Rq = match.getString("Rq");
					String matchStartTime = match.getString("ETime");
					String HTeam = match.getString("HTeam");
					String VTeam = match.getString("VTeam");
					String HomeRank = match.getString("HomeRank");
					String AwayRank = match.getString("AwayRank");
					String SpBF = match.getString("SpBF");
					String SpBQC = match.getString("SpBQC");
					String SpRQSPF = match.getString("SpRQSPF");
					String SpSXDS = match.getString("SpSXDS");
					String SpZJQ = match.getString("SpZJQ");

//					String AwayFieldRank = match.getString("AwayFieldRank");
//					String HomeFieldRank = match.getString("HomeFieldRank");
					String MID = match.getString("MID");

					jzMatch.setIssueNo(IssueNo);
					jzMatch.setMID(MID);
					jzMatch.setNMm(NMm);
					jzMatch.setMatchStartTime(matchStartTime);
					jzMatch.setRq(Rq);
					jzMatch.setHTeam(HTeam);
					jzMatch.setVTeam(VTeam);
					jzMatch.setHomeRank(HomeRank);
					jzMatch.setAwayRank(AwayRank);
//					jzMatch.setHomeFieldRank(HomeFieldRank);
//					jzMatch.setAwayFieldRank(AwayFieldRank);
					jzMatch.setIssue(issue);
					jzMatch.setSpRQSPF(SpRQSPF);
					jzMatch.setSpBF(SpBF);
					jzMatch.setSpZJQ(SpZJQ);
					jzMatch.setSpBQC(SpBQC);
					jzMatch.setSpSXDS(SpSXDS);
					// LogWrite.saveToFile(IssueNo + "\t" + matchStartTime +
					// "\t" + Rq + "\t" + NMm + "\t" + HTeam + " VS " + VTeam);
					// LogWrite.saveToFile(jzMatch.getIssueNo() + "\t" +
					// jzMatch.getMatchStartTime() + "\t" + jzMatch.getRq() +
					// "\t" + jzMatch.getNMm() + "\t" + jzMatch.getHTeam() + "
					// VS " + jzMatch.getVTeam());
					list.add(jzMatch);
				}
			}
		}
		return list;
	}

	public static ArrayList<JzMatchSpInfo> getRealSpList(String type) throws Exception {
		LogWrite.saveToFile("获取gf即时赔率: " + type);
		String url = "";
		if (type.equals("rqspf")) {
			url = gf_bd_rqspf;
		} else if (type.equals("sxds")) {
			url = gf_bd_sxds;
		} else if (type.equals("bf")) {
			url = gf_bd_bf;
		} else if (type.equals("zjq")) {
			url = gf_bd_zjq;
		} else if (type.equals("bqc")) {
			url = gf_bd_bqc;
		}

		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr = request.sendGet(url);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
			// LogWrite.saveToFile(json);
		}

		json = json.substring(39, json.length());

		Document validateNode = DocumentHelper.parseText(json);
		List<Node> itemList = validateNode.selectNodes("info/matches/matchInfo/matchelem/item");
		if (itemList.size() != 0) {
			for (int i = 0; i < itemList.size(); i++) {
				Node item = itemList.get(i);
				String IssueNo = item.valueOf("no");
				String HTeam = item.valueOf("host");
				String VTeam = item.valueOf("guest");
				String endTime = item.valueOf("endTime");
				String Rq = item.valueOf("handicap");
				String NMm = item.valueOf("leagueName");
				String matchandstate = item.valueOf("matchandstate");

				Element spitem = (Element) item.selectSingleNode("spitem");
				List<Element> spList = spitem.elements();
				String Sp = "";
				for (int j = 0; j < spList.size(); j++) {
					Element sp = spList.get(j);
					if (sp.getName().contains("_v")) {
						Sp = Sp + checkSp(sp.getText()) + "|";
					}
				}
				Sp = Sp.substring(0, Sp.length() - 1);

				JzMatchSpInfo jzMatch = new JzMatchSpInfo();
				jzMatch.setIssueNo(IssueNo);
				jzMatch.setNMm(NMm);
				jzMatch.setMatchStartTime(endTime);
				jzMatch.setRq(Rq);
				jzMatch.setHTeam(HTeam);
				jzMatch.setVTeam(VTeam);
				jzMatch.setMatchandstate(matchandstate);

				if (type.equals("rqspf")) {
					jzMatch.setSpRQSPF(Sp);
				} else if (type.equals("sxds")) {
					jzMatch.setSpSXDS(Sp);
				} else if (type.equals("bf")) {
					jzMatch.setSpBF(Sp);
				} else if (type.equals("zjq")) {
					jzMatch.setSpZJQ(Sp);
				} else if (type.equals("bqc")) {
					jzMatch.setSpBQC(Sp);
				}

				LogWrite.saveToFile(
						IssueNo + "\t" + endTime + "\t" + Rq + "\t" + NMm + "\t" + HTeam + " VS " + VTeam + "\t" + Sp);
				list.add(jzMatch);
			}
		}
		return list;
	}

	public static ArrayList<JzMatchSpInfo> getFinalSpList(String type) throws Exception {
		LogWrite.saveToFile(type);
		String url = "";
		if (type.equals("rqspf")) {
			url = gf_bd_rqspf;
		} else if (type.equals("sxds")) {
			url = gf_bd_sxds;
		} else if (type.equals("bf")) {
			url = gf_bd_bf;
		} else if (type.equals("zjq")) {
			url = gf_bd_zjq;
		} else if (type.equals("bqc")) {
			url = gf_bd_bqc;
		}

		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr = request.sendGet(url);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
			// LogWrite.saveToFile(json);
		}

		json = json.substring(39, json.length());

		Document validateNode = DocumentHelper.parseText(json);
		List<Node> itemListSp = validateNode.selectNodes("info/matchesp/matchInfo/matchelem/item");
		if (itemListSp.size() != 0) {
			for (int i = 0; i < itemListSp.size(); i++) {
				Node item = itemListSp.get(i);
				String IssueNo = item.valueOf("no");
				String HTeam = item.valueOf("host");
				String VTeam = item.valueOf("guest");
				String endTime = item.valueOf("endTime");
				String Rq = item.valueOf("handicap");
				String NMm = item.valueOf("leagueName");
				String soccer = item.valueOf("soccer");
				String matchandstate = item.valueOf("matchandstate");

				Element spitem = (Element) item.selectSingleNode("spitem");
				List<Element> spList = spitem.elements();
				String Sp = "";
				for (int j = 0; j < spList.size(); j++) {
					Element sp = spList.get(j);
					if (sp.getName().contains("_v")) {
						if (!sp.getText().equals("0")) {
							Sp = checkSp(sp.getText());
						}
					}
				}
				Sp = Sp.substring(0, Sp.length());
				JzMatchSpInfo jzMatch = new JzMatchSpInfo();
				jzMatch.setIssueNo(IssueNo);
				jzMatch.setNMm(NMm);
				jzMatch.setMatchStartTime(endTime);
				jzMatch.setRq(Rq);
				jzMatch.setHTeam(HTeam);
				jzMatch.setVTeam(VTeam);
				jzMatch.setMatchandstate(matchandstate);
				jzMatch.setSoccer(soccer);

				if (type.equals("rqspf")) {
					jzMatch.setSpRQSPF(Sp);
				} else if (type.equals("sxds")) {
					jzMatch.setSpSXDS(Sp);
				} else if (type.equals("bf")) {
					jzMatch.setSpBF(Sp);
				} else if (type.equals("zjq")) {
					jzMatch.setSpZJQ(Sp);
				} else if (type.equals("bqc")) {
					jzMatch.setSpBQC(Sp);
				}

				LogWrite.saveToFile(
						IssueNo + "\t" + endTime + "\t" + Rq + "\t" + NMm + "\t" + HTeam + " VS " + VTeam + "\t" + Sp);
				list.add(jzMatch);
			}
		}
		return list;
	}

	/**
	 * 比对北单投注对阵 jdd vs 官方
	 * 
	 * @throws IOException
	 * @throws AesException
	 * @throws DocumentException
	 * @throws ParseException
	 */
	public static void compareJddSpToGf_Bd() throws Exception {
		LogWrite.saveToFile("北单对阵、及时sp验证");
		ArrayList<JzMatchSpInfo> jddList = getBdSpFromJdd();

		if (jddList.size() == 0) {
			LogWrite.saveToFile("jdd无北单对阵数据");
			setIssue("null");
			return;
		}

		ArrayList<JzMatchSpInfo> bfList = getRealSpList("bf");
		LogWrite.saveToFile("jddlist=" + jddList.size() + " gflist=" + bfList.size());

		ArrayList<JzMatchSpInfo> bqcList = getRealSpList("bqc");
		ArrayList<JzMatchSpInfo> rqspfList = getRealSpList("rqspf");
		ArrayList<JzMatchSpInfo> sxdsList = getRealSpList("sxds");
		ArrayList<JzMatchSpInfo> zjqList = getRealSpList("zjq");

		for (int i = 0; i < jddList.size(); i++) {
			JzMatchSpInfo jdd = jddList.get(i);
			LogWrite.saveToFile(
					jdd.getIssueNo() + "\t" + jdd.getMatchStartTime() + "\t" + jdd.getHTeam() + "\t" + jdd.getVTeam());

			for (int j = 0; j < bfList.size(); j++) {
				if (jdd.getIssueNo().equals(bfList.get(j).getIssueNo())) {
					String bf = bfList.get(j).getSpBF();
					String bqc = bqcList.get(j).getSpBQC();
					String rqspf = rqspfList.get(j).getSpRQSPF();
					String sxds = sxdsList.get(j).getSpSXDS();
					String zjq = zjqList.get(j).getSpZJQ();

					if (!jdd.getIssueNo().equals(bfList.get(j).getIssueNo())) {
						LogWrite.saveToFile("Issue not match");
						LogWrite.saveToFile("jdd=" + jdd.getIssueNo());
						LogWrite.saveToFile("gf =" + bfList.get(j).getIssueNo());
					}

					String time = DateAndTime.getEndTime(bfList.get(i).getMatchStartTime(), 45);
					if (!jdd.getMatchStartTime().equals(time)) {
						LogWrite.saveToFile("matchtime not match");
						LogWrite.saveToFile("jdd=" + jdd.getMatchStartTime());
						LogWrite.saveToFile("gf =" + time);
					}

					if (!bf.equals(jdd.getSpBF())) {
						LogWrite.saveToFile("bf sp not match");
						LogWrite.saveToFile("jdd=" + jdd.getSpBF());
						LogWrite.saveToFile("gf =" + bf);
					}

					if (!bqc.equals(jdd.getSpBQC())) {
						LogWrite.saveToFile("bqc sp not match");
						LogWrite.saveToFile("jdd=" + jdd.getSpBQC());
						LogWrite.saveToFile("gf =" + bqc);
					}

					if (!rqspf.equals(jdd.getSpRQSPF())) {
						LogWrite.saveToFile("rqspf sp not match");
						LogWrite.saveToFile("jdd=" + jdd.getSpRQSPF());
						LogWrite.saveToFile("gf =" + rqspf);
					}

					if (!sxds.equals(jdd.getSpSXDS())) {
						LogWrite.saveToFile("sxds sp not match");
						LogWrite.saveToFile("jdd=" + jdd.getSpSXDS());
						LogWrite.saveToFile("gf =" + sxds);
					}

					if (!zjq.equals(jdd.getSpZJQ())) {
						LogWrite.saveToFile("zjq sp not match");
						LogWrite.saveToFile("jdd=" + jdd.getSpZJQ());
						LogWrite.saveToFile("gf =" + zjq);
					}
					LogWrite.saveToFile("\n");
					break;
				}
			}
		}
	}

	/**
	 * 获取最终赔率以及比分
	 * 
	 * @param issue
	 * @throws IOException
	 * @throws AesException
	 * @throws DocumentException
	 */

	public static void getFinalSpAndSoccor(String issue) throws Exception {
		LogWrite.saveToFile("北单比分、最终sp验证");

		if (issue.equals("null")) {
			LogWrite.saveToFile("jdd无北单对阵数据");
			return;
		}

		ArrayList<JzMatchSpInfo> jddList = getFinalSpFromData(issue);
		if (jddList.size() == 0) {
			LogWrite.saveToFile("数据库无北单最终sp");
			return;
		}
		ArrayList<JzMatchSpInfo> bfList = getFinalSpList("bf");
		ArrayList<JzMatchSpInfo> bqcList = getFinalSpList("bqc");
		ArrayList<JzMatchSpInfo> rqspfList = getFinalSpList("rqspf");
		ArrayList<JzMatchSpInfo> sxdsList = getFinalSpList("sxds");
		ArrayList<JzMatchSpInfo> zjqList = getFinalSpList("zjq");

		for (int i = 0; i < jddList.size(); i++) {
			JzMatchSpInfo jdd = jddList.get(i);
			String jddSoccer = jdd.getSoccer();
			String jddFinalSp = jdd.getFinalSp();

			LogWrite.saveToFile(jdd.getIssueNo() + "\t" + jdd.getHTeam() + " VS " + jdd.getVTeam());
			
			System.out.println(jdd.getIssueNo());
			for (int j = 0; j < bfList.size(); j++) {
				JzMatchSpInfo gf = bfList.get(j);

				if (jdd.getIssueNo().equals(gf.getIssueNo())) {
					String bf = bfList.get(j).getSpBF();
					String bqc = bqcList.get(j).getSpBQC();
					String rqspf = rqspfList.get(j).getSpRQSPF();
					String sxds = sxdsList.get(j).getSpSXDS();
					String zjq = zjqList.get(j).getSpZJQ();

					String gfRq = gf.getRq();
					String jddRq = gf.getRq();
					String gfSoccer = gf.getSoccer();
					String gfFinalSp = rqspf + "," + zjq + "," + sxds + "," + bf + "," + bqc;

					if (!gfSoccer.equals(jddSoccer)) {
						LogWrite.saveToFile("soccer not match");
						LogWrite.saveToFile("jdd=[" + jddSoccer);
						LogWrite.saveToFile("gf =[" + gfSoccer);
					}

					if (!gfFinalSp.equals(jddFinalSp)) {
						LogWrite.saveToFile("Final sp not match");
						LogWrite.saveToFile("jdd=[ " + jddRq + "\t" + jddSoccer + "\t" + jddFinalSp);
						LogWrite.saveToFile("gf =[ " + gfRq + "\t" + gfSoccer + "\t" + gfFinalSp);
					}
					LogWrite.saveToFile("\n");

					break;
				}

			}
		}
	}

	/**
	 * get bd final sp from data
	 * 
	 * @param issue
	 * @return
	 */
	public static ArrayList<JzMatchSpInfo> getFinalSpFromData(String issue) {
		LogWrite.saveToFile("获取jdd北单比分、终赔: " + issue);
		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();

		String db = "jdd_basedata";
		String sql = "SELECT s_match_no, s_host_team, s_visit_team, s_full_score, s_final_sp FROM `basedata_match_bjdc` where s_issue_name = "
				+ issue + " order by  cast(s_match_no as signed);";
		DBHelper db1 = new DBHelper(db, sql);
		ResultSet ret = null;
		LogWrite.saveToFile("IssueNo \t HTeam \t VTeam \t Rq \t soccer \t FinalSp");
		System.out.println("IssueNo \t HTeam \t VTeam \t Rq \t soccer \t FinalSp");
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				JzMatchSpInfo match = new JzMatchSpInfo();
				String IssueNo = ret.getString(1);
				String HTeam = ret.getString(2);
				String VTeam = ret.getString(3);
				String soccer = ret.getString(4);
				String FinalSp = ret.getString(5);

				System.out.println(IssueNo + "\t" + HTeam + "\t" + VTeam + "\t" + soccer + "\t" + FinalSp);
				LogWrite.saveToFile(IssueNo + "\t" + HTeam + "\t" + VTeam + "\t" + soccer + "\t" + FinalSp);

				match.setIssueNo(IssueNo);
				match.setHTeam(HTeam);
				match.setVTeam(VTeam);
				match.setSoccer(soccer);
				match.setFinalSp(FinalSp);
				list.add(match);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static String checkSp(String value) {
		// System.err.println(value);
		if (value.startsWith("-")) {
			value = value.substring(1, value.length());
		}

		if (!value.contains(".")) {
			value = value + ".00";
		}

		if (value.indexOf(".") > 0) {
			int l = value.length() - value.indexOf(".") - 1;
			if (l == 1) {
				value = value + "0";
			}
			if (l == 3) {
				value = value + "000";
			} else if (l == 4) {
				value = value + "00";
			} else if (l == 5) {
				value = value + "0";
			}
		}
		// System.err.println(value);
		return value;
	}

	public static void main(String[] args) throws Exception {
		// getFinalSpFromData("170903");
//		 getBdSpFromJdd();
//		getFinalSpAndSoccor("170904");
		// getFinalSpList("rqspf");
		compareJddSpToGf_Bd();
//		getFinalSpAndSoccor("171005");
//		getBDSpFromJdd();
	}
}
