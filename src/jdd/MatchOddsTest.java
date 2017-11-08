package jdd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helper.MatchOdds;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class MatchOddsTest {
	
	private static String URL = "https://client.jdd.com/action/MobileHandler.ashx";

	static Map<String, String> params = new HashMap<String, String>();
	
	public static String stringToAscii(String value) {
		StringBuffer sbu = new StringBuffer();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if ((int) chars[i]==10) {
			} else if ((int) chars[i]==13) {
			} else {
				sbu.append(""+chars[i]);
			}
		}
		sbu.delete(17, 18);
		sbu.delete(sbu.length()-11, sbu.length()-10);
		return sbu.toString();
	}
	
	public static void getMatchSP() throws IOException {
		ArrayList<MatchOdds> list = new ArrayList<MatchOdds>();
		params.put("action", "202");
		params.put("appVersion", "3.7.1");
		params.put("cmdId", "3718969");
		params.put("cmdName", "app_yyb");
		params.put("params", "{}");
		params.put("platformCode", "Android");
		params.put("platformVersion", "4.4.4");
		params.put("sign", "127B84B57BC26AC9EA23DAC8A7C16055");
		params.put("UserID", "MTA4MDM5NTY=");
		params.put("uuid", "ffffffff-87b3-1fd3-be31-2c220033c587");

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr = request.sendGet(URL, params);

		String json = hr.getContent().replaceAll("\\\\r\\\\n", "");

		if (json == null || json.equals("")) {
			saveToFile("empty content!!!", "",false);
		} else {
		}
		String str = json.replaceAll("\\\\","").replaceAll(" ", "").trim();
		JSONObject obj = JSONObject.fromObject(stringToAscii(str));
		JSONArray DataArray = obj.getJSONArray("Data");

		for (int i = 0; i < DataArray.size(); i++) {
			JSONObject Issue = DataArray.getJSONObject(i);
			JSONArray MatchsArray = Issue.getJSONArray("Matches");

			for (int j = 0; j < MatchsArray.size(); j++) {
				JSONObject M = MatchsArray.getJSONObject(j);

				MatchOdds mo = new MatchOdds();
				long MatchID = M.getLong("MatchID");
				long MID = M.getLong("MID");
				String NMm = M.getString("NMm");
				String ETime = M.getString("ETime");
				String HTeam = M.getString("HTeam");
				String VTeam = M.getString("VTeam");
				String Rq = M.getString("Rq");
				String SpSPF = M.getString("SpSPF");
				String SpRQSPF = M.getString("SpRQSPF");
				String SpZJQ = M.getString("SpZJQ");
				String SpCBF = M.getString("SpCBF");
				String SpBQC = M.getString("SpBQC");
				String PSState = M.getString("PSState");
				int Hd = M.getInt("Hd");
				
				mo.setMatchID(MatchID);
				mo.setMID(MID);
				mo.setNMm(NMm);
				mo.setETime(ETime);
				mo.setHTeam(HTeam);
				mo.setVTeam(VTeam);
				mo.setRq(Rq);
				mo.setSpSPF(SpSPF);
				mo.setSpRQSPF(SpRQSPF);
				mo.setSpZJQ(SpZJQ);
				mo.setSpCBF(SpCBF);
				mo.setSpBQC(SpBQC);
				mo.setPSState(PSState);
				
				saveToFile("" + MatchID,"",false);
				System.out.println(MID);
				
				if (Hd !=1) { // "Hd"://是否停售 ，1、在售，0、停售
					saveToFile("match not sell!", "", false);
					return;
				} else {
					String [] state = PSState.split(",");
					if (state.length != 5) {
						saveToFile("Odds state length not 5","",false);
					} else {
						if (state[0].equals("1")) { //让球胜平负
							if (SpRQSPF.equals("")) {
								saveToFile("让球胜平负未开售","",false);
								System.out.println("让球胜平负未开售");
							} else {
								String [] odds = SpRQSPF.split("\\|");
								for (int k = 0; k < odds.length; k++) {
									Double odd = Double.valueOf(odds[k]);
									if (odd ==0) {
										saveToFile(SpRQSPF,"",false);
										break;
									}
								}
							}
						} else {
							saveToFile("让球胜平负停售","",false);
							System.out.println("让球胜平负停售");
						}
						if (state[1].equals("1")) { //总进球
							System.out.println(SpZJQ);
							if (SpZJQ.equals("")) {
								saveToFile("总进球未开售","",false);
								System.out.println("总进球未开售");
							} else {
								String [] odds = SpZJQ.split("\\|");
								for (int k = 0; k < odds.length; k++) {
									Double odd = Double.valueOf(odds[k]);
									if (odd ==0) {
										saveToFile(SpZJQ,"",false);
										break;
									}
								}
							}
							
						} else {
							saveToFile("总进球停售","",false);
							System.out.println("总进球停售");
						}
						if (state[2].equals("1")) { //猜比分
							if (SpCBF.equals("")) {
								saveToFile("猜比分未开售","",false);
								System.out.println("猜比分未开售");
							} else {
								String [] odds = SpCBF.split("\\|");
								for (int k = 0; k < odds.length; k++) {
									Double odd = Double.valueOf(odds[k]);
									if (odd ==0) {
										saveToFile(SpCBF,"",false);
										break;
									}
								}
							}
						} else {
							saveToFile("猜比分停售售","",false);
							System.out.println("猜比分停售售");
						}
						if (state[3].equals("1")) { //半全场
							if (SpBQC.equals("")) {
								saveToFile("半全场未开售","",false);
								System.out.println("半全场未开售");
							} else {
								String [] odds = SpBQC.split("\\|");
								for (int k = 0; k < odds.length; k++) {
									Double odd = Double.valueOf(odds[k]);
									if (odd ==0) {
										saveToFile(SpBQC,"",false);
										break;
									}
								}
							}
						} else {
							saveToFile("半全场停售","",false);
							System.out.println("半全场停售");
						}
						if (state[4].equals("1")) { //胜平负
							if (SpSPF.equals("")) {
								saveToFile("胜平负未开售","",false);
								System.out.println("胜平负未开售");
							} else {
								String [] odds = SpSPF.split("\\|");
								for (int k = 0; k < odds.length; k++) {
									Double odd = Double.valueOf(odds[k]);
									if (odd ==0) {
										saveToFile(SpSPF,"",false);
										break;
									}
								}
							}
							
						} else {
							saveToFile("胜平负停售", "",false);
							System.out.println("胜平负停售");
						}
						saveToFile("match sp all right!","",false);
						System.out.println("match sp all right!");
					}
				}
				list.add(mo);
			}
		}
	}
	
	

	public static void main(String[] args) throws IOException {
		getMatchSP();
	}
	
	public static void saveToFile(String text, String path, boolean isClose) {
		File file = new File("runlog.txt");
		BufferedWriter bf = null;
		try {
			FileOutputStream outputStream = new FileOutputStream(file, true);
			OutputStreamWriter outWriter = new OutputStreamWriter(outputStream);
			bf = new BufferedWriter(outWriter);
			bf.append(text);
			bf.newLine();
			bf.flush();

			if (isClose) {
				bf.close();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
