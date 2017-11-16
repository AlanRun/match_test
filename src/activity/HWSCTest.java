package activity;

import java.io.IOException;

import com.jdd.fm.core.exception.AesException;

import helper.AppReq;
import helper.DataUrls;
import helper.SmsTest;

/**
 * 华为商城兑换码活动
 * @author jdd
 *
 */
public class HWSCTest {
	
	public static boolean registerUse(String type, String mobile, String redemptionCode, String actTypeId) throws AesException, IOException{
		String params = DataUrls.params_20018;
		String url = DataUrls.user_url;
		String suc = "发送验证码成功";
		
		String hParams = "";
		String bParams = "mobile," + mobile;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		boolean result = false;
		if (reString.contains(suc)) {
			String verifyCode = SmsTest.getSmsCode(type, mobile);
			System.out.println(verifyCode);
			result = verifyCode(actTypeId, redemptionCode, mobile, verifyCode);
		}
		
		System.out.println("register result =" + result);
		return result;
	}
	
	public static boolean verifyCode(String actTypeId, String redemptionCode, String mobile, String verifyCode) throws AesException, IOException{
		String params = DataUrls.params_7051;
		String url = DataUrls.act_url;
		String suc = "元彩金已到账";
		
		String hParams = "";
		String bParams = "actTypeId," + actTypeId + ";redemptionCode," + redemptionCode + ";mobile," + mobile + ";verifyCode," + verifyCode;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (reString.contains(suc)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean check7050Code(String actTypeId, String redemptionCode) throws AesException, IOException{
		String params = DataUrls.params_7050;
		String url = DataUrls.act_url;
		String suc = "此兑换码有效";
		String err = "此兑换码无效";
		
		String hParams = "";
		String bParams = "actTypeId," + actTypeId + ";redemptionCode," + redemptionCode;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (reString.contains(suc)) {
			return true;
		}else if (reString.contains(err)) {
			return false;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		String type = "zz";
		String actTypeId = "78674";
		String mobile = "13811110006";
		String redemptionCode = "b49c4570f1111565";
		verifyCode(actTypeId, redemptionCode, mobile, "543212");
		
		System.err.println("1.兑换码无效");
		mobile = "13811110005";
		redemptionCode = "ace53bd1b66d61d6";
		check7050Code(actTypeId, "ace53bd1b66d61d6");
		registerUse(type, "13811110004", "ace53bd1b66d61d6", actTypeId);
		
		System.err.println("2.用户已参加");
		mobile = "13811110004";
		redemptionCode = "b49c4570f1111565";
		check7050Code(actTypeId, "06aaccad55eb5fb1");
		check7050Code(actTypeId, "06AACCAD55EB5FB1");
		registerUse(type, "18811110099", "06aaccad55eb5fb1", actTypeId);
		
		check7050Code(actTypeId, "37f6f839047c9229");
		check7050Code(actTypeId, "37F6F839047C9229");
		registerUse(type, "13611110016", "37F6F839047C9229", actTypeId);
		check7050Code(actTypeId, "737ae0f09dfa6622");
		check7050Code(actTypeId, "737ae0f09dfa6622");
		registerUse(type, "13611110012", "737ae0f09dfa6622", actTypeId);
		System.err.println("3.用户未参加");
		check7050Code(actTypeId, "b49c4570f1111565");
		registerUse(type, "13811110006", "b49c4570f1111565", actTypeId);
	}

}
