package monitor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.ConfigProperty;
import utils.HttpRequester;
import utils.HttpRespons;

/**
 * @category 直播吧广告位信息抓取
 * @author Alan Huang
 *
 */

public class ZBBTest {
	static Date d = new Date();
	private static String ios_splash = "http://118.178.168.156:8091/allOne.php?ad_name=main_splash_ios";
	private static String ios_important = "http://118.178.168.156:8091/allOne.php?ad_name=main_important_ios";
	private static String ios_news = "http://118.178.168.156:8091/allOne.php?ad_name=main_news_ios";
	private static String ios_news_list = "http://118.178.168.156:8091/allOne.php?ad_name=news_list_ios";
	private static String ios_neiye_f = "http://118.178.168.156:8091/allOne.php?ad_name=neiye_news_zuqiu_ios";
	private static String ios_neiye_b = "http://118.178.168.156:8091/allOne.php?ad_name=neiye_news_nba_ios";
	private static String ios_common = "&ip=10.33.98.235&device=iPhone%205c&adh=188&openudid=e2036a4e9322da35056cdcf485e7d0d8fc2d0433&osv=10.3.2&dvh=1136&tag=iOS4.5.8%2CNBA%2Cself%2C%E7%AF%AE%E7%90%83%2C%E8%B6%B3%E7%90%83&os=iOS&operator=%28null%29%28null%29&lan=zh-Hans-CN&adw=608&geo=&net=2&model=iPhone5%2C4&_only_care=3&devicetype=0&dvw=640&appname=zhibo8&isboot=1&idfa=834D4F30-3325-40F4-996C-3D1B1908D706&version_code=4.5.8&density=326&orientation=0&vendor=Apple&platform=ios";

	private static String android_splash = "http://118.178.168.156:8091/allOne.php?ad_name=main_splash&_only_care=3";
	private static String android_important = "http://118.178.168.156:8091/allOne.php?ad_name=main_important&_only_care=3";
	private static String android_news = "http://118.178.168.156:8091/allOne.php?ad_name=main_news&_only_care=3";
	private static String android_news_list = "http://118.178.168.156:8091/allOne.php?ad_name=news_list&_only_care=3";
	private static String android_neiye_f = "http://118.178.168.156:8091/allOne.php?ad_name=neiye_news_zuqiu&_only_care=3";
	private static String android_neiye_b = "http://118.178.168.156:8091/allOne.php?ad_name=neiye_news_nba&_only_care=3";
	private static String android_common = "&osv=6.0&ssid=%22jdd-office%22&isboot=0&geo=120.733405%2C31.259382&devicetype=0&iem=861759032966847&pkgname=android.zhibo8&version_name=4.8.2&imei=861759032966847&adid=8f14432e574bc6fc&orientation=0&android_id=e77ec65ac2cf7bbe26e48bc299623b8d4a1ab4ce&lan=zh-CN&tag=version4_8_2%2Cnotification_only%2CNBA%2C%E7%AF%AE%E7%90%83%2C%E8%B6%B3%E7%90%83&appname=%E7%9B%B4%E6%92%AD%E5%90%A7&csinfo=%7B%22bid%22%3A60530%2C%22nid%22%3A11%2C%22ptype%22%3A2%2C%22sid%22%3A14175%7D&mac=fc%3A3f%3A7c%3A12%3Abb%3Adc&version_code=107&dvw=1080&dvh=1812&net=2&density=480&os=Android&platform=android&operator=&ip=10.33.98.245&vendor=HUAWEI&model=EVA-AL00";

	static Map<String, String> params = new HashMap<String, String>();

	public static void saveToFile(String text, String path, boolean isClose) {
		File file = new File(path);
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

	public static void getIOSCommonData(String URL) throws Exception {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = s.format(d);
		URL = URL + ios_common + "&ts=" + d.getTime();

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL, params);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			return;
		} else {
		}

		try {
			JSONArray arr = JSONArray.fromObject(json);
			JSONObject obj = (JSONObject) arr.get(0);
			String content = obj.getString("content");
			if (content.contains(",")) {
				content = content.replaceAll(",", "，");
			}
			String name = obj.getString("name");
			String showTimes = obj.getString("showTimes");
			String duration = obj.getString("duration");
			String img = obj.getString("img");
			String url = obj.getString("url");
			String position = obj.getString("position");
			String act = obj.getString("act");
			if (!url.contains("http") && img.equals("")) {
				JSONObject spare = obj.getJSONObject("spare");
				content = spare.getString("content");
				name = spare.getString("name");
				showTimes = spare.getString("showTimes");
				duration = spare.getString("duration");
				img = spare.getString("img");
				url = spare.getString("url");
				position = spare.getString("position");
				act = spare.getString("act");
			}
			System.out.println("Date,content,name,showTimes,duration,position,act,img,url");
			String feedback = time + "," + content + "," + name + "," + showTimes + "," + duration + "," + position
					+ "," + act + "," + img + "," + url;
			System.out.println(feedback);
			
			s = new SimpleDateFormat("yyyy-MM-dd");
			String c = s.format(d);
			
			saveToFile(feedback, c + "_ios.txt", false);
		} catch (Exception e) {
			return;
		}
	}

	public static void getIOSSplashData(String URL) throws Exception {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = s.format(d);
		URL = URL + ios_common + "&ts=" + d.getTime();

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL, params);
		String json = hr.getContent();

		if (json == null || json.equals("")) {
			return;
		} else {
		}

		try {
			JSONArray arr = JSONArray.fromObject(json);
			JSONObject obj = (JSONObject) arr.get(0);
			String placement = obj.getString("placement");
			String name = obj.getString("name");
			String showTimes = obj.getString("showTimes");
			String duration = obj.getString("duration");
			String img = obj.getString("img");
			String url = obj.getString("url");
			String position = obj.getString("position");
			String act = obj.getString("act");
			System.out.println("Date,placement,name,showTimes,duration,position,act,img,url");
			String feedback = time + "," + placement + "," + name + "," + showTimes + "," + duration + "," + position
					+ "," + act + "," + img + "," + url;
			System.out.println(feedback);
			
			s = new SimpleDateFormat("yyyy-MM-dd");
			String c = s.format(d);
			
			saveToFile(feedback, c + "_ios.txt", false);
		} catch (Exception e) {
			return;
		}
	}

	public static void getAndroidCommonData(String URL) throws Exception {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = s.format(d);
		URL = URL + android_common + "&ts=" + d.getTime();

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL, params);
		String json = hr.getContent();

		if (json == null || json.equals("")) {
			return;
		} else {
		}

		try {
			JSONArray arr = JSONArray.fromObject(json);
			JSONObject obj = (JSONObject) arr.get(0);
			String content = obj.getString("content");
			if (content.contains(",")) {
				content = content.replaceAll(",", "，");
			}
			String name = obj.getString("name");
			String showTimes = obj.getString("showTimes");
			String duration = obj.getString("duration");
			String img = obj.getString("img");
			String url = obj.getString("url");
			String position = obj.getString("position");
			String act = obj.getString("act");
			System.out.println("Date,content,name,showTimes,duration,position,act,img,url");
			String feedback = time + "," + content + "," + name + "," + showTimes + "," + duration + "," + position
					+ "," + act + "," + img + "," + url;
			System.out.println(feedback);
			
			s = new SimpleDateFormat("yyyy-MM-dd");
			String c = s.format(d);
			
			saveToFile(feedback, c + "_android.txt", false);
		} catch (Exception e) {
			return;
		}
	}

	public static void getAndroidSplashData(String URL) throws Exception {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = s.format(d);
		URL = URL + android_common + "&ts=" + d.getTime();

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL, params);
		String json = hr.getContent();

		if (json == null || json.equals("")) {
			return;
		} else {
		}

		try {
			JSONArray arr = JSONArray.fromObject(json);
			JSONObject obj = (JSONObject) arr.get(0);
			String placement = obj.getString("placement");
			String name = obj.getString("name");
			String showTimes = obj.getString("showTimes");
			String duration = obj.getString("duration");
			String img = obj.getString("img");
			String url = obj.getString("url");
			String position = obj.getString("position");
			String act = obj.getString("act");
			System.out.println("Date,placement,name,showTimes,duration,position,act,img,url");
			String feedback = time + "," + placement + "," + name + "," + showTimes + "," + duration + "," + position
					+ "," + act + "," + img + "," + url;
			System.out.println(feedback);
			
			s = new SimpleDateFormat("yyyy-MM-dd");
			String c = s.format(d);
			
			saveToFile(feedback, c + "_android.txt", false);
		} catch (Exception e) {
			return;
		}
	}

	public static void main(String[] args) throws Exception {
		
		getIOSSplashData(ios_splash);
		getIOSCommonData(ios_important);
		getIOSCommonData(ios_news);
		getIOSCommonData(ios_news_list);
		getIOSCommonData(ios_neiye_f);
		getIOSCommonData(ios_neiye_b);

		getAndroidSplashData(android_splash);
		getAndroidCommonData(android_important);
		getAndroidCommonData(android_news);
		getAndroidCommonData(android_news_list);
		getAndroidCommonData(android_neiye_f);
		getAndroidCommonData(android_neiye_b);
	}

	public static String getConfigProperty(String key) {
		ConfigProperty cp = new ConfigProperty("config.properties");
		String value = cp.getProperty(key);

		return value;
	}
}
