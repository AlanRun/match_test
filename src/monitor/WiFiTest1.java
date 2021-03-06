package monitor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.ConfigProperty;
import utils.HttpRequester;
import utils.HttpRespons;

public class WiFiTest1 {

	private static final String URL = "http://cds.51y5.net/feeds.sec";
	// http://cds.51y5.net/feeds.sec?st=m&appId=A0008&sign=A51EBF1EE22D8002B9C18E6543C07BE4&et=a&pid=cds001001%2Ccds001002%2Ccds003001%2Ccds004001%2Ccds004002&ed=EFADF2B7610115D649BF09107F9544CEC1232FCEB5D6077D588836EC532EFC6BE7E876921F753D1CD3765496137F34EB9EC4572903FD1DA04CA9B8F2B8BB2AAD9BD36EA4069B8D327115CE4F0354F65C36256257DB1CBF6CA180C8058FDFF0A6960E640EC5E70B9057F0F24AB9DEB05DAEA366242BDEF466824878FA56905F07F66A78B5430FD4D9FCCA1B1C6CBF97C238877F55183C933459FCE5149567192A554913627B43D924EDD33D93FDFAB92695A557DB22DE892CFA4CB4D6374D871D7CDEDF3760886FE2B488AE9D58CB946159677862D7DC125C88502E248651E858C1935821DD571D78A9737CF8286F75C0A180DE20223C81B763CCA4BDA23C638476A6C1C8C583386CAA48CB22617C41571D7004347A17A825D936B1F983CC4007798F2225BCF433AA3930BB34089B36D9D0D2104315EF484F05B1A12BFE740652F511E64D278BF681E0A8052D2FF823B4A506B4F70AA3CE422403F5D8581E3E8CD8CBBE999DB204033A41D684A5DCA8C798233B7063689101C98E78777CEB7C39A2AF2544149A6DD1C9B06E433C8E51A82FC1786B34418C0CC3230F26D397F7D5BE50D3007A2312079D90F6AF1C145618B2841D55B906422E513BC06B6BC73C56608CD3BBB1A29A12694DF43C88289C8A8D8507CA5782BC98A21090F6F21662E2D1CE44BD46CAB09FE1193C2DB65DCA5CD2A56FC176E623BDC11111F1A6B111FABF0B75ADFDE6426F142BCB6052BFCD798DCA17BB78CAD1E54A411A619D63555A75C018E97C1A78526FE858755110060ECA5BB05B966E8A1773BBB166FBE79D3C71FE4A7CAEF3C90CEF9DA340514071F31A1FB55409031A17A63CEF4F8788752F595EA5E1F9919EA723B7FC4DC9A73A777B0F0DA3FF5BBF70FC7A9FB9EFBEB693848A9BE0A16343C20AC50D6740BF6EE3A672786B7657DE3280493A32985553B44BEAA9DDD87A17B7017D17148CA10234DE0919DFFE17485FE5E3590CE1C1DE3A5A02B621C52C6FBF42A3E17434A4FC24DB6668DF554B718A9F5C7C9BFC121AD345EA75289210DD6B42E0543A85529D20E1CFC1C82B9E2DC8E156C87E13920394B52C5EC46352B277050D116E0CAEE4BFC6464EFE3E5638F9311A459A136EC68E4A7EE034C954544D887BDCE0CFB0E01817A63E4F322601E3F853610275E4144053BBAEF8D5FD94F60274DB4D91CE9C762DC6EBCFD5A89BDF951AFF04D47E115609101BACB19E9A524AB992A382B09209BFAD47CC32616B2457001B6DA6E40A813F71C387E72867F22D40005B71B4A9DFF6DCACFAFAC4F36FB65E2361EE0114521D532DDFA618796F85149F67B5D3A8C3EFA6D19AEBC16199847115BD11AE3CC35642420B3473417131EDE7CB8D0CA2E26D31057F8261D3107F47B840E3829F20304E9E09D76A4A515A0C03EC0903A95B8244221AA89C60B3814196FA4B74307EBAF2FF60B9506A606C1A257D72DC2BA15EF26F84B4D835D4DB95F40F7FB7C1A153F7BEFADDE5B04BB2D1EF98A74AB9A05500E1A637F7DAFCFFF7AAA25E59129430FAAF2BAB3BFEF46764C9597E8C9A0C166CB1BEDDD54A2C71799E0A544E55FEA008E2A9D850FE874E1C36BA87D6740ECF03406EA1B0B693A06A9DE9837803B06C7D2C92F16875FBF77E9195F9D71427C5AD9FED957581467BD660F41F9480ED5087DA51FE20D404FC9CB47A508CA5D50D8EDACF11D3A72DDC6E516FC63103C8D30691D7F627F0F2C162B352F6AB3D7A495C7672D5488F3ACE73A298874B8B322CF3614515FADA701265161B2DB9432D2EB2F5CB16923D95D1E6673B5457C306

	static Map<String, String> params = new HashMap<String, String>();
	
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

			if (isClose)
				bf.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getWiFiData() throws Exception {
		Date d = new Date();
		params.put("st", "m");
		params.put("appId", "A0008");
		params.put("sign", "A51EBF1EE22D8002B9C18E6543C07BE4");
		params.put("et", "a");
		params.put("pid", "cds001001%2Ccds001002%2Ccds003001%2Ccds004001%2Ccds004002");
		params.put("ed",
				"EFADF2B7610115D649BF09107F9544CEC1232FCEB5D6077D588836EC532EFC6BE7E876921F753D1CD3765496137F34EB9EC4572903FD1DA04CA9B8F2B8BB2AAD9BD36EA4069B8D327115CE4F0354F65C36256257DB1CBF6CA180C8058FDFF0A6960E640EC5E70B9057F0F24AB9DEB05DAEA366242BDEF466824878FA56905F07F66A78B5430FD4D9FCCA1B1C6CBF97C238877F55183C933459FCE5149567192A554913627B43D924EDD33D93FDFAB92695A557DB22DE892CFA4CB4D6374D871D7CDEDF3760886FE2B488AE9D58CB946159677862D7DC125C88502E248651E858C1935821DD571D78A9737CF8286F75C0A180DE20223C81B763CCA4BDA23C638476A6C1C8C583386CAA48CB22617C41571D7004347A17A825D936B1F983CC4007798F2225BCF433AA3930BB34089B36D9D0D2104315EF484F05B1A12BFE740652F511E64D278BF681E0A8052D2FF823B4A506B4F70AA3CE422403F5D8581E3E8CD8CBBE999DB204033A41D684A5DCA8C798233B7063689101C98E78777CEB7C39A2AF2544149A6DD1C9B06E433C8E51A82FC1786B34418C0CC3230F26D397F7D5BE50D3007A2312079D90F6AF1C145618B2841D55B906422E513BC06B6BC73C56608CD3BBB1A29A12694DF43C88289C8A8D8507CA5782BC98A21090F6F21662E2D1CE44BD46CAB09FE1193C2DB65DCA5CD2A56FC176E623BDC11111F1A6B111FABF0B75ADFDE6426F142BCB6052BFCD798DCA17BB78CAD1E54A411A619D63555A75C018E97C1A78526FE858755110060ECA5BB05B966E8A1773BBB166FBE79D3C71FE4A7CAEF3C90CEF9DA340514071F31A1FB55409031A17A63CEF4F8788752F595EA5E1F9919EA723B7FC4DC9A73A777B0F0DA3FF5BBF70FC7A9FB9EFBEB693848A9BE0A16343C20AC50D6740BF6EE3A672786B7657DE3280493A32985553B44BEAA9DDD87A17B7017D17148CA10234DE0919DFFE17485FE5E3590CE1C1DE3A5A02B621C52C6FBF42A3E17434A4FC24DB6668DF554B718A9F5C7C9BFC121AD345EA75289210DD6B42E0543A85529D20E1CFC1C82B9E2DC8E156C87E13920394B52C5EC46352B277050D116E0CAEE4BFC6464EFE3E5638F9311A459A136EC68E4A7EE034C954544D887BDCE0CFB0E01817A63E4F322601E3F853610275E4144053BBAEF8D5FD94F60274DB4D91CE9C762DC6EBCFD5A89BDF951AFF04D47E115609101BACB19E9A524AB992A382B09209BFAD47CC32616B2457001B6DA6E40A813F71C387E72867F22D40005B71B4A9DFF6DCACFAFAC4F36FB65E2361EE0114521D532DDFA618796F85149F67B5D3A8C3EFA6D19AEBC16199847115BD11AE3CC35642420B3473417131EDE7CB8D0CA2E26D31057F8261D3107F47B840E3829F20304E9E09D76A4A515A0C03EC0903A95B8244221AA89C60B3814196FA4B74307EBAF2FF60B9506A606C1A257D72DC2BA15EF26F84B4D835D4DB95F40F7FB7C1A153F7BEFADDE5B04BB2D1EF98A74AB9A05500E1A637F7DAFCFFF7AAA25E59129430FAAF2BAB3BFEF46764C9597E8C9A0C166CB1BEDDD54A2C71799E0A544E55FEA008E2A9D850FE874E1C36BA87D6740ECF03406EA1B0B693A06A9DE9837803B06C7D2C92F16875FBF77E9195F9D71427C5AD9FED957581467BD660F41F9480ED5087DA51FE20D404FC9CB47A508CA5D50D8EDACF11D3A72DDC6E516FC63103C8D30691D7F627F0F2C162B352F6AB3D7A495C7672D5488F3ACE73A298874B8B322CF3614515FADA701265161B2DB9432D2EB2F5CB16923D95D1E6673B5457C306");

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL, params);

		String json = hr.getContent();

		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
		} else {
			// System.out.println(json);
		}

		JSONObject obj = JSONObject.fromObject(json);

		JSONObject cds001001 = obj.getJSONObject("cds001001");
		JSONArray results = cds001001.getJSONArray("result");

		for (int i = 0; i < results.size(); i++) {
			String feedback = "" + d + ",";
			JSONObject result = results.getJSONObject(i);
			JSONArray items = result.getJSONArray("item");
			JSONObject item = items.getJSONObject(0);
			String title = item.getString("title");
//			System.out.print(title);
//			System.out.print(",");
			feedback = feedback + title + ",";
			JSONArray imgs = item.getJSONArray("imgs");
			for (int j = 0; j < 3; j++) {
				if (j < imgs.size()) {
					JSONObject img = imgs.getJSONObject(j);
					String url = img.getString("url");
//					System.out.print(url);
//					System.out.print(",");
					feedback = feedback + url + ",";
				} else {
//					System.out.print(",");
					feedback = feedback + ",";
				}
			}
			JSONArray tags = item.getJSONArray("tags");
			for (int j = 0; j < 3; j++) {
				if (j < tags.size()) {
					JSONObject tag = tags.getJSONObject(j);
					String text = tag.getString("text");
//					System.out.print(text);
//					System.out.print(",");
					feedback = feedback + text + ",";
				} else {
//					System.out.print(",");
					feedback = feedback + ",";
				}
			}
//			System.out.println(feedback);
			saveToFile(feedback, "runlog.txt", false);
		}
	}
	
	public static String getConfigProperty(String key) {
		ConfigProperty cp = new ConfigProperty("config.properties");
		String value = cp.getProperty(key);

		return value;
	}

	public static void main(String[] args) throws Exception {
//		while (true) {
			getWiFiData();
//			Thread.sleep(30 * 60* 1000);
//		}
	}
}
