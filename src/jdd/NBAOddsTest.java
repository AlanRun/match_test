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

import helper.NBAOdds;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class NBAOddsTest {

	private static String URL = "http://client.jdd.com/action/MobileHandler.ashx";

	static Map<String, String> params = new HashMap<String, String>();

	public static String stringToAscii(String value) {
		StringBuffer sbu = new StringBuffer();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if ((int) chars[i] == 10) {
			} else if ((int) chars[i] == 13) {
			} else {
				sbu.append("" + chars[i]);
			}
		}
		sbu.delete(17, 18);
		sbu.delete(sbu.length() - 11, sbu.length() - 10);
		return sbu.toString();
	}

	public static void getMatchSP() throws IOException {
		ArrayList<NBAOdds> list = new ArrayList<NBAOdds>();
		params.put("action", "203");
		params.put("appVersion", "3.7.1");
		params.put("cmdId", "3718969");
		params.put("cmdName", "app_zz");
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
			saveToFile("empty content!!!", "", false);
		} else {
		}
		String str = json.replaceAll("\\\\", "").replaceAll(" ", "").trim();
		JSONObject obj = JSONObject.fromObject(stringToAscii(str));
		JSONArray DataArray = obj.getJSONArray("Data");

		for (int i = 0; i < DataArray.size(); i++) {
			JSONObject Issue = DataArray.getJSONObject(i);
			JSONArray MatchsArray = Issue.getJSONArray("Matches");

			for (int j = 0; j < MatchsArray.size(); j++) {
				JSONObject M = MatchsArray.getJSONObject(j);
				System.out.println(M);

				NBAOdds mo = new NBAOdds();
				long MID = M.getLong("MID");
				String NMm = M.getString("NMm");
				String ETime = M.getString("ETime");
				String HTeam = M.getString("HTeam");
				String VTeam = M.getString("VTeam");
				String SpRFSF = M.getString("SpRFSF");
				String SpSF = M.getString("SpSF");
				String SpSFC = M.getString("SpSFC");
				String SpDXF = M.getString("SpDXF");
				String PSState = M.getString("PSState");
				int Hd = M.getInt("Hd");

				mo.setMID(MID);
				mo.setNMm(NMm);
				mo.setETime(ETime);
				mo.setHTeam(HTeam);
				mo.setVTeam(VTeam);
				mo.setHd(Hd);
				mo.setSpRFSF(SpRFSF);
				mo.setSpSF(SpSF);
				mo.setSpSFC(SpSFC);
				mo.setSpDXF(SpDXF);
				mo.setPSState(PSState);

				saveToFile("" + MID, "", false);
				System.out.println(MID);

				if (Hd != 1) { // "Hd"://是否停售 ，1、在售，0、停售
					saveToFile("match not sell!", "", false);
					return;
				} else {
					String[] state = PSState.split(",");
					// PSState: 胜负、让分、大小分、胜分差 的停售判断，格式：1,1,1,1  其中：1、在售，0、停售
					if (state.length != 4) {
						saveToFile("Odds state length not 4", "", false);
					} else {
						if (state[0].equals("1")) { // 让分胜负
							if (SpRFSF.equals("")) {
								saveToFile("让分胜负未开售", "", false);
								System.out.println("让分胜负未开售");
							} else {
								String[] odds = SpRFSF.split("\\|");
								for (int k = 0; k < odds.length; k++) {
									Double odd = Double.valueOf(odds[k]);
									if (odd == 0) {
										saveToFile(SpRFSF, "", false);
										System.out.println(SpRFSF);
										break;
									}
								}
							}
							
						} else {
							saveToFile("让分胜负停售", "", false);
							System.out.println("让分胜负停售");
						}
						if (state[1].equals("1")) { // 胜负
							if (SpSF.equals("")) {
								saveToFile("胜负未开售", "", false);
								System.out.println("胜负未开售");
							} else {
								String[] odds = SpSF.split("\\|");
								for (int k = 0; k < odds.length; k++) {
									Double odd = Double.valueOf(odds[k]);
									if (odd == 0) {
										saveToFile(SpSF, "", false);
										System.out.println(SpSF);
										break;
									}
								}
							}
							
						} else {
							saveToFile("胜负停售", "", false);
							System.out.println("胜负停售");
						}
						if (state[2].equals("1")) { // 胜分差
							if (SpSFC.equals("")) {
								saveToFile("胜分差未开售", "", false);
								System.out.println("胜分差未开售");
							} else {
								String[] odds = SpSFC.split("\\|");
								for (int k = 0; k < odds.length; k++) {
									Double odd = Double.valueOf(odds[k]);
									if (odd == 0) {
										saveToFile(SpSFC, "", false);
										System.out.println(SpSFC);
										break;
									}
								}
							}
							
						} else {
							saveToFile("胜分差停售", "", false);
							System.out.println("胜分差停售");
						}
						if (state[3].equals("1")) { // 大小球
							if (SpDXF.equals("")) {
								saveToFile("大小分未开售", "", false);
								System.out.println("大小分未开售");
							} else {
								String[] odds = SpDXF.split("\\|");
								for (int k = 0; k < odds.length; k++) {
									Double odd = Double.valueOf(odds[k]);
									if (odd == 0) {
										saveToFile(SpDXF, "", false);
										System.out.println(SpDXF);
										break;
									}
								}
							}
							
						} else {
							saveToFile("大小分停售", "", false);
							System.out.println("大小分停售");
						}
						saveToFile("match sp all right!", "", false);
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
