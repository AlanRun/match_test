package interfaceTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.jdd.fm.core.exception.AesException;

import helper.AppReq;
import helper.JlMatchSpInfo;
import helper.JzMatchSpInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class SpCollectTest {
	static String gf_jz_url = "http://info.sporttery.cn/interface/interface_mixed.php?action=fb_list&_=";
	static String jdd_jz_url = "https://bd-api.jdd.com/basedata/public/securityMobileHandler.do";
	static String jz_params = "{\"header\":{\"appVersion\":\"3.8.9\",\"cmdID\":\"0\",\"platformVersion\":\"6.0\",\"action\":\"202\",\"cmdName\":\"app_zz\",\"userType\":1,\"userID\":\"\",\"uuid\":\"00000000-7fc6-bc83-0ab8-a6690033c587\",\"platformCode\":\"Android\",\"phoneName\":\"HUAWEI\",\"token\":\"\"},\"body\":\"{}\"}";
	
	static String gf_jl_url = "http://info.sporttery.cn/interface/interface_mixed.php?action=bk_list&_=";
	static String jdd_jl_url = "https://bd-api.jdd.com/basedata/public/securityMobileHandler.do";
	static String jl_params = "{\"header\":{\"appVersion\":\"3.8.9\",\"cmdID\":\"0\",\"platformVersion\":\"6.0\",\"action\":\"2031\",\"cmdName\":\"app_zz\",\"userType\":1,\"userID\":\"\",\"uuid\":\"00000000-7fc6-bc83-0ab8-a6690033c587\",\"platformCode\":\"Android\",\"phoneName\":\"HUAWEI\",\"token\":\"\"},\"body\":\"{}\"}";
	
	static String gf_ct_url = "http://i.sporttery.cn/wap/fb_lottery/fb_lottery_match?key=wilo&num=&f_callback=getDataBack&_=";
	static String jdd_ct_url = "";
	static String ct_params = "";
	
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
			System.out.println("empty content!!!");
		} else {
			// System.out.println(json);
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
				String HomeRank = match.getString("HomeFieldRank");
				String AwayRank = match.getString("AwayFieldRank");
				String SpBQC = match.getString("SpBQC");
				String SpCBF = match.getString("SpCBF");
				String SpRQSPF = match.getString("SpRQSPF");
				String SpSPF = match.getString("SpSPF");
				String SpZJQ = match.getString("SpZJQ");

				jzMatch.setMID(MID);
				jzMatch.setNMm(NMm);
				jzMatch.setRq(Rq);
				jzMatch.setMatchStartTime(matchStartTime);
				jzMatch.setHTeam(HTeam);
				jzMatch.setVTeam(VTeam);
				jzMatch.setHomeRank(HomeRank);
				jzMatch.setAwayRank(AwayRank);
				jzMatch.setIssue(issue);
				
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
			System.out.println("empty content!!!");
		} else {

		}
		String[] s1 = json.split(";");
		String[] s2 = s1[2].split("=");
		String data = s2[1];

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
//				System.out.println(value);
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
	
	/**
	 * 获取jdd竞篮投注对阵赔率 action=2031
	 * @return
	 * @throws IOException
	 * @throws AesException
	 */
	public static ArrayList<JlMatchSpInfo> getJLSpFromJdd() throws IOException, AesException {
		ArrayList<JlMatchSpInfo> list = new ArrayList<JlMatchSpInfo>();
		
		String json = AppReq.getResStr(jdd_jl_url, jl_params);
		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
		} else {
			// System.out.println(json);
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
			System.out.println("empty content!!!");
		} else {

		}
		String[] s1 = json.split(";");
		String[] s2 = s1[2].split("=");
		String data = s2[1];
		
		JSONArray matchArr = JSONArray.fromObject(data);
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
//				System.out.println(value);
				switch (j) {
				case 0:
					String[] info = value.split(",");
					String MID = info[0];
					String NMm = info[1];
					String matchStartTime = "20" + info[4] + ":00";
					String HTeam = info[8];
					String VTeam = info[9];
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
	 * 比对竞足投注赔率jdd vs 官方
	 * @throws IOException
	 * @throws AesException
	 */
	public static void compareJddSpToGf_JZ() throws IOException, AesException{
		ArrayList<JzMatchSpInfo> gfList = getJZSpFromGF();
		ArrayList<JzMatchSpInfo> jddList = getJZSpFromJdd();
		for (int i = 0; i < jddList.size(); i++) {
			JzMatchSpInfo jdd = jddList.get(i);
			JzMatchSpInfo gf = gfList.get(i);
			if (jdd.getMID().equals(gf.getMID())) {
				System.out.println(jdd.getMID());
				if (!jdd.getMatchStartTime().equals(gf.getMatchStartTime())) {
					System.out.println("start time not match");
					System.out.println(jdd.toString());
					System.out.println(gf.toString());
				}

				if (!jdd.getRq().equals(gf.getRq())) {
					System.out.println("RQ not match");
					System.out.println(jdd.toString());
					System.out.println(gf.toString());
				}

				if (!jdd.getIssue().equals(gf.getIssue())) {
					System.out.println("Issue not match");
					System.out.println(jdd.toString());
					System.out.println(gf.toString());
				}

				String jddBQC = jdd.getSpBQC();
				String jddCBF = jdd.getSpCBF();
				String jddRQSPF = jdd.getSpRQSPF();
				String jddSPF = jdd.getSpSPF();
				String jddZJQ = jdd.getSpZJQ();

				String gfBQC = gf.getSpBQC();
				String gfCBF = gf.getSpCBF();
				String gfRQSPF = gf.getSpRQSPF();
				String gfSPF = gf.getSpSPF();
				String gfZJQ = gf.getSpZJQ();

				if (!jddBQC.equals(gfBQC)) {
					System.out.println("SPBQC not match");
					System.out.println(jddBQC);
					System.out.println(gfBQC);
				}

				if (!jddZJQ.equals(gfZJQ)) {
					System.out.println("SPZJQ not match");
					System.out.println(jddZJQ);
					System.out.println(gfZJQ);
				}

				if (!jddRQSPF.equals(gfRQSPF)) {
					System.out.println("SPRQSPF not match");
					System.out.println(jddRQSPF);
					System.out.println(gfRQSPF);
				}

				if (!jddSPF.equals(gfSPF)) {
					System.out.println("SPSPF not match");
					System.out.println(jddSPF);
					System.out.println(gfSPF);
				}

				if (!jddCBF.equals(gfCBF)) {
					System.out.println("SPCBF not match");
					System.out.println(jddCBF);
					System.out.println(gfCBF);
				}

			} else {
				System.out.println("match not right");
			}
			System.out.println();
		}
	}
	
	/**
	 * 比对竞篮投注赔率jdd vs 官方
	 * @throws IOException
	 * @throws AesException
	 */
	public static void compareJddSpToGf_JL() throws IOException, AesException{
		ArrayList<JlMatchSpInfo> gfList = getJLSpFromGF();
		ArrayList<JlMatchSpInfo> jddList = getJLSpFromJdd();
		for (int i = 0; i < jddList.size(); i++) {
			JlMatchSpInfo jdd = jddList.get(i);
			JlMatchSpInfo gf = gfList.get(i);
			if (jdd.getMID().equals(gf.getMID())) {
				System.out.println(jdd.getMID());

				if (!jdd.getIssue().equals(gf.getIssue())) {
					System.out.println("Issue not match");
					System.out.println(jdd.toString());
					System.out.println(gf.toString());
				}

				String jddSF = jdd.getSpSF();
				String jddRFSF = jdd.getSpRFSF();
				String jddDXF = jdd.getSpDXF();
				String jddSFC = jdd.getSpSFC();

				String gfSF = gf.getSpSF();
				String gfRFSF = gf.getSpRFSF();
				String gfDXF = gf.getSpDXF();
				String gfSFC = gf.getSpSFC();

				if (!jddSFC.equals(gfSFC)) {
					System.out.println("SPSFC not match");
					System.out.println(jddSFC);
					System.out.println(gfSFC);
				}

				if (!jddDXF.equals(gfDXF)) {
					System.out.println("SPDXF not match");
					System.out.println(jddDXF);
					System.out.println(gfDXF);
				}

				if (!jddRFSF.equals(gfRFSF)) {
					System.out.println("SPRFSF not match");
					System.out.println(jddRFSF);
					System.out.println(gfRFSF);
				}

				if (!jddSF.equals(gfSF)) {
					System.out.println("SPSF not match");
					System.out.println("jdd="+jddSF);
					System.out.println("gf="+gfSF);
				}


			} else {
				System.out.println("match not right");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException, AesException {
//		compareJddSpToGf_JL();
		compareJddSpToGf_JZ();
	}
}
