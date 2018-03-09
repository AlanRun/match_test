package bdapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
			String name = firstTeam.getString("name");
			JSONObject sencondTeam = info.getJSONObject("sencondTeam");
			if (!sencondTeam.getString("name").equals("")) {
				name = name + "—" + sencondTeam.getString("name");
			}
			
			match.setTeam(name);
			
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
//			match.setChance(info.getString("odds"));
			list.add(match);
		}
		
		return list;
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
				
				if (!jdd.getTeam().equals(gf.getTeam()) && !jdd.getTeam().equals("")) {
					LogWrite.saveToFile("比赛球队名不符");
					LogWrite.saveToFile("jdd=" + jdd.getTeam());
					LogWrite.saveToFile("gf =" + gf.getTeam());
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
//				if (!jdd.getChance().equals(gf.getChance())) {
//					LogWrite.saveToFile("比赛概率不符");
//					LogWrite.saveToFile("jdd=[" + jdd.getChance());
//					LogWrite.saveToFile("gf =[" + gf.getChance());
//				}
				
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
			match.setTeam(info[1]);
			match.setStatue(info[2]);
			match.setSp(info[3]);
			match.setChance(info[5]);
//			System.out.println(match.toString());
			list.add(match);
		}
		
		return list;
	}
	

	public static ArrayList<CGJInfo> getGYJDataFromGF() throws IOException{
		Date d = new Date();
		ArrayList<CGJInfo> list = new ArrayList<CGJInfo>();

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("gbk");
		HttpRespons hr = request.sendGet(gf_gyj_url + d.getTime());
		String json =AppReq.decodeUnicode(hr.getContent());
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
			System.out.println(json.substring(8, json.trim().length()-2));
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
			match.setTeam(info[1]);
			match.setStatue(info[2]);
			match.setSp(info[3]);
			match.setChance(info[5]);
			System.out.println(match.toString());
			list.add(match);
		}
		
		return list;
	}
	
	public static void main(String[] args) throws Exception {
		compareData("cgj");
		compareData("gyj");
	}

}
