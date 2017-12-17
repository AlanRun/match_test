package bdapi;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import com.jdd.fm.core.exception.AesException;

import helper.AppReq;
import helper.DataUrls;
import helper.JzMatchSpInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.LogWrite;

public class Jz202Test {
	static String gf_jz_url = "http://info.sporttery.cn/interface/interface_mixed.php?action=fb_list&_=";
	static String jdd_jz_url = DataUrls.url_bd;
	static String jz_params = DataUrls.params_202;;
	
	/**
	 * 获取jdd竞足投注对阵赔率 action=202
	 * @return
	 * @throws IOException
	 * @throws AesException
	 */
	public static ArrayList<JzMatchSpInfo> getTeadRankAndHis() throws IOException, AesException {
		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();
		
		String json = AppReq.getResStr(jdd_jz_url, jz_params);
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
			// LogWrite.saveToFile(json);
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");
		for (int i = 0; i < data.size(); i++) {
			JSONObject wk = (JSONObject) data.get(i);
			JSONArray Matches = wk.getJSONArray("Matches");
			for (int j = 0; j < Matches.size(); j++) {
				JzMatchSpInfo jzMatch = new JzMatchSpInfo();
				JSONObject match = (JSONObject) Matches.get(j);

				String MID = match.getString("MID");
				String NMm = match.getString("NMm");
				String HTeam = match.getString("HTeam");
				String VTeam = match.getString("VTeam");
//				String HomeFieldRank = match.getString("HomeFieldRank");
//				String AwayFieldRank = match.getString("AwayFieldRank");
				String HomeRank = match.getString("HomeRank");
				String AwayRank = match.getString("AwayRank");
				String HAHistory = match.getString("HAHistory");
				String HomeHistory = match.getString("HomeHistory");
				String AwayHistory = match.getString("AwayHistory");
				String AvgSP = match.getString("AvgSP");

				jzMatch.setMID(MID);
				jzMatch.setNMm(NMm);
				jzMatch.setHTeam(HTeam);
				jzMatch.setVTeam(VTeam);
//				jzMatch.setHomeFieldRank(HomeFieldRank);
//				jzMatch.setAwayFieldRank(AwayFieldRank);
				jzMatch.setHomeRank(HomeRank);
				jzMatch.setAwayRank(AwayRank);
				jzMatch.setHAHistory(HAHistory);
				jzMatch.setHomeHistory(HomeHistory);
				jzMatch.setAwayHistory(AwayHistory);
				jzMatch.setAvgSP(AvgSP);
				
				System.out.println(MID + "  " + HTeam + "\t" + VTeam);
//				System.out.println("球队实力\t" + HomeFieldRank + "\t" + AwayFieldRank);
				System.out.println("球队排名\t" + HomeRank + "\t" + AwayRank);
				if (HAHistory.equals("") || HAHistory.equals("null")) {
					System.err.println("两队交锋\t 无数据");
				} else {
					System.out.println("两队交锋\t" + HAHistory);
				}
				if (HomeHistory.equals("") || HomeHistory.equals("null")) {
					System.err.println("近期状态\t 无数据");
				} else {
					System.out.println("近期状态\t" + HomeHistory + "\t" + AwayHistory);
				}
//				System.out.println("平均欧赔\t" + AvgSP);
				
				LogWrite.saveToFile(MID + "  " + HTeam + "\t" + VTeam);
//				LogWrite.saveToFile("球队实力\t" + HomeFieldRank + "\t" + AwayFieldRank);
				LogWrite.saveToFile("球队排名\t" + HomeRank + "\t" + AwayRank);
				LogWrite.saveToFile("两队交锋\t" + HAHistory);
				LogWrite.saveToFile("近期状态\t" + HomeHistory + "\t" + AwayHistory);
//				LogWrite.saveToFile("平均欧赔\t" + AvgSP);
				System.out.println();
				
				list.add(jzMatch);
			}
		}
		return list;
	}
	
	/**
	 * 获取jdd竞足投注对阵赔率 action=202
	 * @return
	 * @throws IOException
	 * @throws AesException
	 */
	public static ArrayList<JzMatchSpInfo> getMatchsSp() throws IOException, AesException {
		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();
		
		String json = AppReq.getResStr(jdd_jz_url, jz_params);
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
			System.out.println(json);
			// LogWrite.saveToFile(json);
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");
		for (int i = 0; i < data.size(); i++) {
			JSONObject wk = (JSONObject) data.get(i);
			JSONArray Matches = wk.getJSONArray("Matches");
			for (int j = 0; j < Matches.size(); j++) {
				JzMatchSpInfo jzMatch = new JzMatchSpInfo();
				JSONObject match = (JSONObject) Matches.get(j);

				String MID = match.getString("MID");
				String NMm = match.getString("NMm");
				String HTeam = match.getString("HTeam");
				String VTeam = match.getString("VTeam");
				String SpSPF = match.getString("SpSPF");

				System.out.println(MID + " " + NMm);
				
				countSP(SpSPF);
				System.out.println();
			}
		}
		return list;
	}
	
	public static void countSP(String sp){
		
		if (sp.contains("0.00")) {
			return;
		}
		
		String[] spList = sp.split("\\|");
		System.out.println(sp);
		
		BigDecimal win = new BigDecimal(spList[0].toString());
		BigDecimal draw = new BigDecimal(spList[1].toString());
		BigDecimal loss = new BigDecimal(spList[2].toString());
		
		BigDecimal ji_san = win.multiply(draw).multiply(loss);
		BigDecimal he_two = ((win.multiply(draw)).add(win.multiply(loss)).add(draw.multiply(loss)));
		
		
//		System.out.println(ji_san);
//		System.out.println(he_two);
		BigDecimal reString = (ji_san.divide(he_two, 4,BigDecimal.ROUND_DOWN)).multiply(new BigDecimal("100"));
//		System.out.println(reString);
		
		int d = reString.divide(draw, 0, BigDecimal.ROUND_DOWN).intValue();
		int l = reString.divide(loss, 0, BigDecimal.ROUND_DOWN).intValue();
		int w = 100 - d - l;
		
		System.out.println(win.toString() + "\t" + w + "%");
		System.out.println(draw.toString() + "\t" + d + "%");
		System.out.println(loss.toString() + "\t" + l + "%");
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
	
	public static void main(String[] args) throws IOException, AesException {
		getMatchsSp();
	}
}
