package bdapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helper.AppReq;
import helper.CGJInfo;
import helper.DataUrls;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;
import utils.LogWrite;

public class WcpTest {
	
	static String gf_cgj_url = "http://i.sporttery.cn/rank_calculator/get_list?tid[]=104895&pcode[]=chp&i_callback=cphData&_=";
	static String gf_gyj_url = "http://i.sporttery.cn/rank_calculator/get_list?tid[]=104895&pcode[]=fnl&i_callback=getList&_=";
	static String jdd_jz_url = DataUrls.url_bd;
	
	/**
	 * 获取jdd猜冠军对阵
	 * @return 
	 * @throws Exception 
	 */
	public static ArrayList<CGJInfo> getDataFromJdd(String type) throws Exception{
		String jz_params = "";
		ArrayList<CGJInfo> list = new ArrayList<CGJInfo>();
		if (type.equals("cgj")) {
			jz_params = DataUrls.params_205;
		} else {
			jz_params = DataUrls.params_206 ;
		}
		
		String json = AppReq.getResStr(jdd_jz_url, jz_params);
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
			System.out.println("empty content!!!");
		} else {
			 LogWrite.saveToFile(json);
			 System.out.println(json);
		}
		
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
		if (data.toString().equals("{}")) {
			return list;
		}
		JSONArray matches = data.getJSONArray("matches");
		for (int i = 0; i < matches.size(); i++) {
			JSONObject info = (JSONObject) matches.get(i);
			CGJInfo match = new CGJInfo();
			
			match.setNum(info.getString("matchNo"));
			match.setSp(info.getString("odds"));
			
			JSONObject firstTeam = info.getJSONObject("firstTeam");
			String Hname = firstTeam.getString("name");
			String HteamId = firstTeam.getString("teamId");
			String HimgUrl = firstTeam.getString("imgUrl");
			if (!HimgUrl.equals("") && !HimgUrl.contains(HteamId)) {
				System.err.println("球队ID和图片URL不符");
				System.err.println(HimgUrl);
				System.err.println(HteamId);
				LogWrite.saveToFile("球队ID和图片URL不符");
				LogWrite.saveToFile(HimgUrl);
				LogWrite.saveToFile(HteamId);
			}
			
			match.setHteam(Hname);
			match.setHteamId(HteamId);
			match.setHimgUrl(HimgUrl);
			
			int defa = 2;
			if (!Hname.equals("")) {
				
				defa = getTime(type, Hname);
				
				int countF = getCount(json,HteamId);
				if (countF != defa) {
					System.err.println(Hname + HteamId + " TeamID重复");
				}
			}
			
			JSONObject sencondTeam = info.getJSONObject("sencondTeam");
			String Aname = sencondTeam.getString("name");
			String AteamId = sencondTeam.getString("teamId");
			String AimgUrl = sencondTeam.getString("imgUrl");
			if (!AimgUrl.equals("") && !AimgUrl.contains(AteamId)) {
				System.err.println("球队ID和图片URL不符");
				System.err.println(AimgUrl);
				System.err.println(AteamId);
				LogWrite.saveToFile("球队ID和图片URL不符");
				LogWrite.saveToFile(AimgUrl);
				LogWrite.saveToFile(AteamId);
			}
			
			match.setAteam(Aname);
			match.setAteamId(AteamId);
			match.setAimgUrl(AimgUrl);
			if (!Aname.equals("")) {
				
				defa = getTime(type, Aname);
				
				int countF = getCount(json,AteamId);
				if (countF != defa) {
					System.err.println(Aname + AteamId + " TeamID重复");
				}
			}
			
			if (info.getString("status").equals("0")) {
				match.setStatue("停售");
			} else if (info.getString("status").equals("1")) {
				match.setStatue("开售");
			} else if (info.getString("status").equals("2")) {
				match.setStatue("未开售");
			} else if (info.getString("status").equals("")) {
				match.setStatue("已开奖");
			}
			match.setMatchResult(info.getString("matchResult"));
			match.setTournamentName(data.getString("tournamentName"));
			list.add(match);
		}
		
		return list;
	}
	
	private static int getTime(String type, String name) {
		if (type.equals("cgj")) {
			return 2;
		} else {
			if (name.equals("巴西")) {
				return 26;
			} else if (name.equals("德国")) {
				return 22;
			} else if (name.equals("西班牙")) {
				return 20;
			} else if (name.equals("阿根廷")) {
				return 20;
			} else if (name.equals("法国")) {
				return 20;
			} else if (name.equals("比利时")) {
				return 14;
			} else if (name.equals("葡萄牙")) {
				return 16;
			} else if (name.equals("英格兰")) {
				return 16;
			} else if (name.equals("乌拉圭")) {
				return 14;
			} else if (name.equals("哥伦比亚")) {
				return 10;
			} else if (name.equals("俄罗斯")) {
				return 4;
			} else if (name.equals("克罗地亚")) {
				return 10;
			} else {
				return 2;
			}
		}
	}

	public static int getCount(String source, String sub) {
        int count = 0;
        int length = source.length() - sub.length();
        for (int i = 0; i < length; i++) {
            String sourceBak = source.substring(i, i + sub.length());
            int index = sourceBak.indexOf(sub);
            if (index != -1) {
                count++;
            }
        }
        return count;
    }

    public static int finder(String source, String regexStr) {
        String regex = "[a-zA-Z]+";
        if (regexStr != null && !regexStr.equals("")) {
            regex = regexStr;
        }
        Pattern expression = Pattern.compile(regex);
        Matcher matcher = expression.matcher(source);

        int n = 0;
        while (matcher.find()) {
            n++;
        }
        return n;
    }
	
	public static void compareData(String type) throws Exception {
		LogWrite.saveToFile(type + "对阵、及时sp验证");
		ArrayList<CGJInfo> gfList = getDataFromGF(type);
		ArrayList<CGJInfo> jddList = getDataFromJdd(type);
		if (jddList.size() == 0) {
			LogWrite.saveToFile("jdd无对阵数据");
			return;
		}
		LogWrite.saveToFile("jddCount=" + jddList.size() + " gfCount=" + gfList.size());
		
		for (int i = 0; i < jddList.size(); i++) {
			CGJInfo jdd = jddList.get(i);
			LogWrite.saveToFile(jdd.getNum());
			
			for (int j = 0; j < gfList.size(); j++) {
				CGJInfo gf = gfList.get(j);
				
				if (jdd.getNum().equals(gf.getNum())) {
					LogWrite.saveToFile("jdd=" + jdd.toString());
					LogWrite.saveToFile("gf =" + gf.toString());
				}else {
					continue;
				}
				
				if (!jdd.getHteam().equals(gf.getHteam()) && !jdd.getHteam().equals("")) {
					LogWrite.saveToFile("比赛H球队名不符");
					LogWrite.saveToFile("jdd=" + jdd.getHteam());
					LogWrite.saveToFile("gf =" + gf.getHteam());
				}
				
				if (!jdd.getAteam().equals("")){
					if (!jdd.getAteam().equals(gf.getAteam())) {
						LogWrite.saveToFile("比赛A球队名不符");
						LogWrite.saveToFile("jdd=" + jdd.getAteam());
						LogWrite.saveToFile("gf =" + gf.getAteam());
					}
				}
				
				if (!jdd.getStatue().equals(gf.getStatue())) {
					LogWrite.saveToFile("比赛开停售状态不符");
					LogWrite.saveToFile("jdd=" + jdd.getStatue());
					LogWrite.saveToFile("gf =" + gf.getStatue());
				}
				if (!jdd.getSp().equals(gf.getSp())) {
					LogWrite.saveToFile("比赛赔率不符");
					LogWrite.saveToFile("jdd=" + jdd.getSp());
					LogWrite.saveToFile("gf =" + gf.getSp());
				}
			}
		}
		
	}
	
	/**
	 * 获取官方猜冠军对阵
	 * @return 
	 * @throws IOException
	 */
	public static ArrayList<CGJInfo> getDataFromGF(String type) throws Exception{
		Date d = new Date();
		String url = "";
		if (type.equals("cgj")) {
			url = gf_cgj_url + d.getTime();
		} else {
			url = gf_gyj_url + d.getTime();
		}
		
		ArrayList<CGJInfo> list = new ArrayList<CGJInfo>();

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("gbk");
		HttpRespons hr = request.sendGet(url);
		String json =AppReq.decodeUnicode(hr.getContent());
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
			System.out.println(json.substring(8, json.trim().length()-2));
			LogWrite.saveToFile(json.substring(8, json.trim().length()-2));
		}
		json = json.substring(8, json.trim().length()-2);
		
		JSONObject obj = JSONObject.fromObject(json);
		JSONArray dataArr = obj.getJSONArray("data");
		JSONObject data = (JSONObject) dataArr.get(0);
		String matchStr = data.getString("data");
		System.out.println(matchStr);
		
		String[] matchList = matchStr.split("\\|");
		for (int i = 0; i < matchList.length; i++) {
			String[] info = matchList[i].split("-");
			
			CGJInfo match = new CGJInfo();
			match.setNum(info[0]);
			
			String team = info[1];
			if (team.contains("—")) {
				match.setHteam(team.split("—")[0]);
				match.setAteam(team.split("—")[1]);
			} else {
				match.setHteam(team);
				match.setAteam("");
			}
			match.setStatue(info[2]);
			match.setSp(info[3]);
			match.setChance(info[5]);
			list.add(match);
		}
		
		return list;
	}
	
	public static void main(String[] args) throws Exception {
		compareData("cgj");
		compareData("gyj");
	}

}
