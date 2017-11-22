package smc;

import java.io.IOException;
import java.util.ArrayList;

import com.jdd.fm.core.exception.AesException;

import helper.AppReq;
import helper.DataUrls;
import helper.FootballMatch;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.LogWrite;

public class NewListTest {
	
	/**
	 * 获取指定期次smc比分列表接口对阵
	 * @param issue 期次接口
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static ArrayList<FootballMatch> getNewJZMatchLists(String issue) throws Exception{
		ArrayList<FootballMatch> list = new ArrayList<FootballMatch>();
		
		String url = DataUrls.url_jzmatchlist;
		String params = DataUrls.params_matchlist;
		String suc = "数据正常";
		String printLog = "";
		
		String hParams = "";
		String bParams = "issue," + issue;
		params = AppReq.setParmas(params, hParams, bParams);
		
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		LogWrite.saveToFile(reString);
		
		if (reString.contains(suc)) {
			
			JSONObject obj = JSONObject.fromObject(reString);
			
			JSONArray data = obj.getJSONArray("data");
			for (int i = 0; i < data.size(); i++) {
				FootballMatch m = new FootballMatch();
				
				JSONObject match = (JSONObject) data.get(i);
				
				JSONObject matchInfo = match.getJSONObject("matchInfo");
				JSONObject issueInfo = match.getJSONObject("issueInfo");
				
				m.setI(issueInfo.getString("issue"));
				m.setLId(90);
				m.setNO(Integer.valueOf(issueInfo.getString("num")));
				m.setNUM(issueInfo.getString("num"));
				m.setMId(matchInfo.getString("matchId"));
				m.setTABS(matchInfo.getString("tournamentName"));
				m.setSMD(matchInfo.getString("matchTime"));
				m.setSC(matchInfo.getInt("statusCode"));
				m.setATD(matchInfo.getInt("awayTeamId"));
				m.setHTD(matchInfo.getInt("homeTeamId"));
				
				// 0:未开始;1:进行中;2:完场
				m.setIC(matchInfo.getInt("matchInProgress"));
				
				// ['未开始', '进行中', '完场']
				m.setSS(matchInfo.getString("statusNameLotteryDesc") + matchInfo.getString("minutes"));
				m.setHT(matchInfo.getString("homeTeamName"));
				m.setAT(matchInfo.getString("awayTeamName"));
				m.setScoreHalf(matchInfo.getString("scoreHalf"));
				m.setScoreNormal(matchInfo.getString("scoreNormal"));
				
//				System.out.println(m.toString());
				list.add(m);
			}
			
		} else {
			printLog = "Get new JZ list failed.";
			System.out.println(printLog);
			LogWrite.saveToFile(printLog);
		}
		
		return list;
	}
	
	public static void checkJzOldAndNewList(String issue) throws Exception{
		ArrayList<FootballMatch> oldList = JzMatchs.getMatchList(issue);
		ArrayList<FootballMatch> newList = getNewJZMatchLists(issue);
		String printLog = "";
		
		if (oldList.size() != newList.size()) {
			printLog = "期次对阵个数不一致";
			
			System.out.println(printLog);
			LogWrite.saveToFile(printLog);
		}
		
		for (int i = 0; i < newList.size(); i++) {
			FootballMatch nm = newList.get(i);
			String mid = nm.getMId();
			System.out.println("new match=" + nm.toString());
			LogWrite.saveToFile("new match=" + nm.toString());
			for (int j = 0; j < oldList.size(); j++) {
				FootballMatch om = oldList.get(j);
				if (mid.equals(om.getMId())) {
					System.out.println("old match=" + om.toString());
					LogWrite.saveToFile("old match=" + om.toString());
					break;
				}
			}
			System.out.println();
			Thread.sleep(1000);
		}
		
	}
	
	

	public static void main(String[] args) throws Exception{
		String issue = "2017-11-22";
//		JzMatchs.getMatchList(issue);
//		Thread.sleep(3000);
//		getNewMatchLists(issue);
		checkJzOldAndNewList(issue);
	}

}
