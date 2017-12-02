package bdapi;

import java.math.BigDecimal;
import helper.AppReq;
import helper.DataUrls;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import smc.BiFaData;

public class Test2142 {

	public static void check2142() throws Exception {

		String url = DataUrls.url_bd;
		String params = DataUrls.params_2142;

		String reString = AppReq.getResStr(url, params);
		JSONObject obj = JSONObject.fromObject(reString);
		JSONObject data = obj.getJSONObject("data");
		JSONArray analysisList = data.getJSONArray("analysisList");

		for (int i = 0; i < analysisList.size(); i++) {
			JSONObject analysis = (JSONObject) analysisList.get(i);

			String matchId = analysis.getString("matchId");
			String homeTeam = analysis.getString("homeTeam");
			String issueName = analysis.getString("issueName");
			String matchNo = analysis.getString("matchNo");
			System.out.println(issueName + "  " + matchNo + " homeTeam=" + homeTeam + " " + matchId);
			
			String avgEuroWin = analysis.getString("avgEuroWin");
			String avgEuroDraw = analysis.getString("avgEuroDraw");
			String avgEuroLoss = analysis.getString("avgEuroLoss");

			String aveEuro = "\t" + "百家欧赔：" + avgEuroWin + "\t" + avgEuroDraw + "\t" + avgEuroLoss;
			System.out.println(aveEuro);
			System.out.println(BiFaData.getBiFaPrice(matchId));

			String caloricWin = analysis.getString("caloricWin");
			String caloricDraw = analysis.getString("caloricDraw");
			String caloricLoss = analysis.getString("caloricLoss");

			String caloric = "\t" + "冷热指数：" + "主胜 " + caloricWin + "\t" + "平 " + caloricDraw + "\t" + "客胜 "
					+ caloricLoss;
			System.out.println(caloric);
			System.out.println(BiFaData.getHotCoolData(matchId));

			String spTradeWin = analysis.getString("spTradeWin");
			String spTradeDraw = analysis.getString("spTradeDraw");
			String spTradeLoss = analysis.getString("spTradeLoss");

			BigDecimal tw = new BigDecimal(spTradeWin);
			BigDecimal td = new BigDecimal(spTradeDraw);
			BigDecimal tl = new BigDecimal(spTradeLoss);

			String spTrade = "\t" + "必发交易：" + spTradeWin + "%\t" + spTradeDraw + "%\t" + spTradeLoss + "%";

			float tmp = tw.add(td).add(tl).floatValue();
			
			if (tmp != 100) {
				System.err.println(tmp + spTrade);
			} else {
				System.out.println(spTrade);
			}
			System.out.println(BiFaData.getBiFaData(matchId));
			
			String euroWin = analysis.getString("euroWin");
			String euroDraw = analysis.getString("euroDraw");
			String euroLoss = analysis.getString("euroLoss");
			
			String euroP = "\t" + "欧赔概率：" + euroWin + "\t" + euroDraw + "\t" + euroLoss;
			
			BigDecimal ew = new BigDecimal(euroWin);
			BigDecimal ed = new BigDecimal(euroDraw);
			BigDecimal el = new BigDecimal(euroLoss);

			float euro = ew.add(ed).add(el).floatValue();
			
			if (euro != 100) {
				System.err.println(euro + euroP);
			} else {
				System.out.println(euro + euroP);
			}
			
			Thread.sleep(500);
		}
	}

	public static void main(String[] args) throws Exception {
		check2142();
	}
}
