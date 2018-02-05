package interfaceTest;

import java.io.IOException;

import com.jdd.fm.core.exception.AesException;

import helper.AppReq;
import helper.DataUrls;

public class Market6000 {
	
	public static void getMarket6000Info() throws Exception{
		String url = DataUrls.url_market;
		String params = DataUrls.params_6000;
		
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
	}
	
	public static void main(String[] args) throws Exception {
		getMarket6000Info();
	}

}
