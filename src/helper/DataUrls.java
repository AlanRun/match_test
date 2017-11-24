package helper;

public class DataUrls {
	
	// 传统期次
	public static final String ct_issue = "https://dcds.jdd.com/Api.Soccer.V3/Ajax/Live.ashx?action=zqissue&lotteryid=10&playid=1001";
	// 传统对阵 issue = 17126
	public static final String ct_qlive =  "https://dcds.jdd.com/Api.Soccer.V3/Ajax/Live.ashx?action=qlive&lotteryid=10&playid=1001&pts=0&issue=";
	// 传统实况
	public static final String ct_live =  "https://dcds.jdd.com/Api.Soccer.V3/Ajax/Live.ashx?action=live&lotteryid=10&playid=1001&pts=0&issue=";
	
	// 竞足期次
	public static final String ft_issue = "https://dcds.jdd.com/Api.Soccer.V3/Ajax/Live.ashx?action=zqissue&lotteryid=90&playid=9001";
	// 竞足对阵  issue = 2017-09-04
	public static final String ft_qlive =  "https://dcds.jdd.com/Api.Soccer.V3/Ajax/Live.ashx?action=qlive&lotteryid=90&playid=9001&pts=0&issue=";
	// 竞足实况
	public static final String ft_live =  "https://dcds.jdd.com/Api.Soccer.V3/Ajax/Live.ashx?action=live&lotteryid=10&playid=1001&pts=0&issue=";
	// 我的关注
	public static final String ft_collect =  "https://dcds.jdd.com/Api.Soccer.V3/Ajax/Live.ashx?action=qlive&lotteryId=90&pts=0&matchids=";
	// 积分榜
	public static final String ft_teamrank = "http://smc.jdd.com/api/scoredetail/getssmatchrankinginfo?lotteryId=90&pcode=h5&pts=0&version=v2.1&mid=";
	// 基础数据URL
	public static final String bd_url = "http://bd-api.jdd.com/basedata/public/securityMobileHandler.do";
	public static final String act_url = "http://activity-api.jdd.com/activity/public/mobileHandler.do";
	public static final String market_url = "http://market-api.jdd.com/market/public/safeMobileHandler.do";
	
	//竞足
	public static final String url_jzissuelist = "https://smc.jdddata.com/api/matchlist/jzissuelist";
	public static final String url_jzmatchlist = "https://smc.jdddata.com/api/matchlist/jzmatchlist";
	public static final String url_jzmatchlivelist = "https://smc.jdddata.com/api/matchlist/jzmatchlivelist";
	
	//竞篮
	public static final String url_jlissuelist = "https://smc.jdddata.com/api/matchlist/jlissuelist";
	public static final String url_jlmatchlist = "https://smc.jdddata.com/api/matchlist/jlmatchlist";
	public static final String url_jlmatchlivelist = "https://smc.jdddata.com/api/matchlist/jlmatchlivelist";
	
	//足彩
	public static final String url_zcissuelist = "https://smc.jdddata.com/api/matchlist/zcissuelist";
	public static final String url_zcmatchlist = "https://smc.jdddata.com/api/matchlist/zcmatchlist";
	public static final String url_zcmatchlivelist = "https://smc.jdddata.com/api/matchlist/zcmatchlivelist";

	
	public static final String params_issuelist = "{\"header\":{\"appVersion\":\"3.9.6\",\"idfa\":\"\",\"cmdName\":\"app_zz\",\"userID\":\"\",\"uuid\":\"00000000-45fe-369b-0760-812f0033c587\",\"token\":\"\",\"cmdID\":\"0\",\"platformVersion\":\"7.0\",\"action\":\"\",\"imei\":\"356156074527318\",\"userType\":1,\"platformCode\":\"Android\",\"phoneName\":\"samsung\"},\"body\":\"\"}";
	public static final String params_matchlist = "{\"header\":{\"appVersion\":\"3.9.6\",\"idfa\":\"\",\"cmdName\":\"app_zz\",\"userID\":\"\",\"uuid\":\"00000000-45fe-369b-0760-812f0033c587\",\"token\":\"\",\"cmdID\":\"0\",\"platformVersion\":\"7.0\",\"action\":\"\",\"imei\":\"356156074527318\",\"userType\":1,\"platformCode\":\"Android\",\"phoneName\":\"samsung\"},\"body\":\"{'issue':'2017-11-21'}\"}";
	public static final String params_matchlivelist = "{\"header\":{\"appVersion\":\"3.9.6\",\"idfa\":\"\",\"cmdName\":\"app_zz\",\"userID\":\"\",\"uuid\":\"00000000-45fe-369b-0760-812f0033c587\",\"token\":\"\",\"cmdID\":\"0\",\"platformVersion\":\"7.0\",\"action\":\"\",\"imei\":\"356156074527318\",\"userType\":1,\"platformCode\":\"Android\",\"phoneName\":\"samsung\"},\"body\":\"{'issue':'2017-11-21'}\"}";
	
	// 竞足202接口参数
	public static final String params_202 = "{\"header\":{\"appVersion\":\"3.8.9\",\"cmdID\":\"0\",\"platformVersion\":\"6.0\",\"action\":\"202\",\"cmdName\":\"app_zz\",\"userType\":1,\"userID\":\"\",\"uuid\":\"00000000-7fc6-bc83-0ab8-a6690033c587\",\"platformCode\":\"Android\",\"phoneName\":\"HUAWEI\",\"token\":\"\"},\"body\":\"{}\"}";
	// 竞篮2031接口参数
	public static final String params_2031 = "{\"header\":{\"appVersion\":\"3.8.9\",\"cmdID\":\"0\",\"platformVersion\":\"6.0\",\"action\":\"2031\",\"cmdName\":\"app_zz\",\"userType\":1,\"userID\":\"\",\"uuid\":\"00000000-7fc6-bc83-0ab8-a6690033c587\",\"platformCode\":\"Android\",\"phoneName\":\"HUAWEI\",\"token\":\"\"},\"body\":\"{}\"}";
	// 传统204接口参数
	public static final String params_204 = "{\"header\":{\"appVersion\":\"3.8.9\",\"cmdID\":\"0\",\"platformVersion\":\"6.0\",\"action\":\"204\",\"cmdName\":\"app_zz\",\"userType\":1,\"userID\":\"\",\"uuid\":\"00000000-7fc6-bc83-0ab8-a6690033c587\",\"platformCode\":\"Android\",\"phoneName\":\"HUAWEI\",\"token\":\"\"},\"body\":\"{\\\"LottID\\\":1}\"}";
	
	// 北单229接口参数
	public static final String params_229 = "{\"header\":{\"appVersion\":\"3.8.9\",\"cmdID\":\"0\",\"platformVersion\":\"6.0\",\"action\":\"229\",\"cmdName\":\"app_zz\",\"userType\":1,\"userID\":\"\",\"uuid\":\"00000000-7fc6-bc83-0ab8-a6690033c587\",\"platformCode\":\"Android\",\"phoneName\":\"HUAWEI\",\"token\":\"\"},\"body\":\"{}\"}";
	public static final String params_90332 = "{\"header\":{\"appVersion\":\"4.0.2\",\"cmdId\":0,\"platformVersion\":\"4.0.2\",\"action\":90332,\"cmdName\":\"h5_zz\",\"userType\":1,\"uuid\":\"3C075555N9M0\",\"userID\":\"\",\"platformCode\":\"h5mobile\",\"token\":\"\"},\"body\":\"{}\"}";
	public static final String params_200_5 = "{\"header\":{\"appVersion\":\"3.9.0\",\"cmdID\":\"0\",\"platformVersion\":\"6.0\",\"action\":\"200\",\"cmdName\":\"app_zz\",\"userType\":\"1\",\"userID\":\"\",\"uuid\":\"00000000-7fc6-bc83-0ab8-a6690033c587\",\"platformCode\":\"Android\",\"phoneName\":\"HUAWEI\",\"token\":\"\"},\"body\":\"{'LottID':5}\"}";
	public static final String params_200_39 = "{\"header\":{\"appVersion\":\"3.9.0\",\"cmdID\":\"0\",\"platformVersion\":\"6.0\",\"action\":\"200\",\"cmdName\":\"app_zz\",\"userType\":\"1\",\"userID\":\"\",\"uuid\":\"00000000-7fc6-bc83-0ab8-a6690033c587\",\"platformCode\":\"Android\",\"phoneName\":\"HUAWEI\",\"token\":\"\"},\"body\":\"{'LottID':39}\"}";
	public static final String params_9009 = "{\"header\":{\"traceID\":\"\",\"appVersion\":\"5.1.5\",\"idfa\":\"43DDFBD4-5AE0-42FE-BF06-E5F693ED93B5\",\"usertype\":1,\"cmdName\":\"app_ios_zz\",\"userGuid\":\"\",\"uuid\":\"4C98D64157704EE8AE9070D06499A258\",\"token\":\"\",\"cmdId\":\"1\",\"UserID\":\"\",\"platformVersion\":\"10.3.2\",\"action\":\"9009\",\"imei\":\"\",\"phoneName\":\"iPhone 7\",\"platformCode\":\"IPHONE\",\"ts\":\"\"},\"body\":\"{\\n  'appId' : '94558726'\\n}\"}";
	public static final String params_100 = "{\"header\":{\"traceID\":\"\",\"appVersion\":\"5.1.7\",\"idfa\":\"8E9B817E-2267-455A-86FD-3E347122B8C3\",\"usertype\":1,\"cmdName\":\"app_ios_zz\",\"userGuid\":\"\",\"uuid\":\"4E579AA2B12C4A57AD8DE42C3EB7C83B\",\"cmdId\":\"1\",\"UserID\":\"\",\"platformVersion\":\"10.3.2\",\"action\":\"100\",\"imei\":\"\",\"platformCode\":\"IPHONE\",\"phoneName\":\"iPhone5\",\"ts\":\"\"},\"body\":\"{'CmdID':0,'Pw':'aaaaaa','Registype':2,'Name':'18811110001'}\"}";
	public static final String params_1034 = "{\"header\":{\"appVersion\":\"3.9.3\",\"idfa\":\"\",\"cmdName\":\"app_zz\",\"userID\":\"\",\"uuid\":\"00000000-7fc6-bc83-0ab8-a6690033c587\",\"token\":\"\",\"cmdID\":\"0\",\"platformVersion\":\"6.0\",\"action\":\"1034\",\"imei\":\"861759032966847\",\"userType\":1,\"platformCode\":\"Android\",\"phoneName\":\"HUAWEI\"},\"body\":\"{'mobile':'13811110020','verifycode':'570426'}\"}";
	public static final String params_1011 = "{\"header\":{\"appVersion\":\"3.8.9\",\"cmdID\":\"0\",\"platformVersion\":\"6.0\",\"action\":\"1011\",\"cmdName\":\"app_zz\",\"userType\":1,\"userID\":\"\",\"uuid\":\"00000000-7fc6-bc83-0ab8-a6690033c587\",\"platformCode\":\"Android\",\"phoneName\":\"HUAWEI\",\"token\":\"\"},\"body\":\"{'name':'1','pw':'a','usertype':'1'}\"}";
	public static final String params_7050 = "{\"header\":{\"appVersion\":\"4.0.2\",\"cmdId\":0,\"platformVersion\":\"4.0.2\",\"action\":7050,\"cmdName\":\"h5_zz\",\"userType\":1,\"uuid\":\"3C075555N9M0\",\"userID\":\"\",\"platformCode\":\"h5mobile\",\"token\":\"\"},\"body\":\"{'actTypeId':'78674','redemptionCode':'2630fbde6bf977e0'}\"}";
	public static final String params_20018 = "{\"header\":{\"appVersion\":\"4.0.2\",\"cmdId\":0,\"platformVersion\":\"4.0.2\",\"action\":20018,\"cmdName\":\"h5_zz\",\"userType\":1,\"uuid\":\"3C075555N9M0\",\"userID\":\"\",\"platformCode\":\"h5mobile\",\"token\":\"\"},\"body\":\"{'mobile':'13811110001'}\"}";
	public static final String params_7059 = "{\"header\":{\"appVersion\":\"4.0.2\",\"cmdId\":0,\"platformVersion\":\"4.0.2\",\"action\":7059,\"cmdName\":\"h5_zz\",\"userType\":1,\"uuid\":\"3C075555N9M0\",\"userID\":\"\",\"platformCode\":\"h5mobile\"},\"body\":\"{'actTypeId':'100004','mobile':'15034163010','verifyCode':'959077','device':'5F4166B33AE032C0BCE299A7D8D7C7666CBBF91C07E021C6DD4D25EA68FB7AF8327E5DF102CB30A0309B229EF4C6EB0FE8A5C6A9870FAC28BB8985B9608AF07D'}\"}";
	public static final String params_7058 = "{\"header\":{\"appVersion\":\"4.0.2\",\"cmdId\":0,\"platformVersion\":\"4.0.2\",\"action\":7058,\"cmdName\":\"h5_zz\",\"userType\":1,\"uuid\":\"3C075555N9M0\",\"userID\":\"\",\"platformCode\":\"h5mobile\"},\"body\":\"{'actTypeId':'100004','mobile':'15034163010','device':'5F4166B33AE032C0BCE299A7D8D7C7666CBBF91C07E021C6DD4D25EA68FB7AF8327E5DF102CB30A0309B229EF4C6EB0FE8A5C6A9870FAC28BB8985B9608AF07D'}\"}";
	public static final String params_7060 = "{\"header\":{\"appVersion\":\"5.1.6\",\"cmdId\":\"1\",\"platformVersion\":\"10.2.1\",\"action\":\"7060\",\"cmdName\":\"app_ios_zz\",\"userType\":1,\"uuid\":\"04FCEE6BDE0F461FACD8594E994A6467\",\"userID\":\"\",\"platformCode\":\"IPHONE\",\"islogin\":true,\"token\":\"\",\"username\":\"\"},\"body\":\"{'actTypeId':'100004'}\"}";
	public static final String params_2142 = "{\"header\":{\"appVersion\":\"4.0.2\",\"cmdId\":0,\"platformVersion\":\"4.0.2\",\"action\":2142,\"cmdName\":\"h5_zz\",\"userType\":1,\"uuid\":\"3C075555N9M0\",\"userID\":\"\",\"platformCode\":\"h5mobile\",\"token\":\"\"},\"body\":\"{}\"}";
	public static final String params_102 = "{\"header\":{\"appVersion\":\"3.8.6\",\"cmdID\":\"1173636\",\"platformVersion\":\"7.0\",\"action\":\"102\",\"cmdName\":\"app_wdj\",\"userType\":\"1\",\"userID\":\"MTAwMDAwNDc5Ng==\",\"uuid\":\"00000000-45fe-369b-0760-812f0033c587\",\"platformCode\":\"Android\",\"phoneName\":\"samsung\",\"token\":\"eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJqc2NwIiwiaXNzIjoiamRkLmNvbSJ9.eyJ1c2VyVHlwZSI6MSwidXNlcmlkIjoxMDAwMDA0Nzk2LCJ1dWlkIjoiMDAwMDAwMDAtNDVmZS0zNjliLTA3NjAtODEyZjAwMzNjNTg3In0.7a37c4913e1940192f3249cf3b5f10f5.YjE0ZjFkNzEtZDI3My00ZmQwLWFjMTMtMDU3ZTEzYjhiNDgy\"},\"body\":\"{'RName':'\u54C8\u54C8','IDCard':'202112198911031104','NickName':''}\"}";
	public static final String params_107 = "{\"header\":{\"appVersion\":\"3.8.6\",\"cmdID\":\"1173636\",\"platformVersion\":\"7.0\",\"action\":\"107\",\"cmdName\":\"app_wdj\",\"userType\":\"1\",\"userID\":\"MTAwMDAwNDk0MQ==\",\"uuid\":\"00000000-45fe-369b-0760-812f0033c587\",\"platformCode\":\"Android\",\"phoneName\":\"samsung\",\"token\":\"eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJqc2NwIiwiaXNzIjoiamRkLmNvbSJ9.eyJ1c2VyVHlwZSI6MSwidXNlcmlkIjoxMDAwMDA0OTQxLCJ1dWlkIjoiMDAwMDAwMDAtNDVmZS0zNjliLTA3NjAtODEyZjAwMzNjNTg3In0.7a37c4913e1940192f3249cf3b5f10f5.MWYzMmQ0MTQtNWVlYy00OWVhLWEyMjAtOTJiZGU1OWI1MDBl\"},\"body\":\"{}\"}";
	public static final String params_6000 = "{\"header\":{\"platformVersion\":\"10.3.2\",\"platformCode\":\"IPHONE\",\"cmdID\": 3,\"cmdName\": \"app_ios_zz\",\"token\": \"eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJqc2NwIiwiaXNzIjoiamRkLmNvbSJ9.eyJ1c2VyVHlwZSI6MSwidXNlcmlkIjoxMDAwMDA0Nzk2LCJ1dWlkIjoiMDAwMDAwMDAtNDVmZS0zNjliLTA3NjAtODEyZjAwMzNjNTg3In0.7a37c4913e1940192f3249cf3b5f10f5.YjE0ZjFkNzEtZDI3My00ZmQwLWFjMTMtMDU3ZTEzYjhiNDgy\",\"appVersion\": \"5.1.6\",\"userID\": \"MTA4MjEwMzc=\",\"userGuid\": \"\",\"userType\": 1,\"uuid\": \"4C98D64157704EE8AE9070D06499A258\",\"ts\": \"\",\"traceID\": \"\",\"action\": 6000,\"phoneName\": \"iPhone 7\"},\"body\": \"{'wapName':'4g','wapPower':'12','ipAddress':'10.22.23.65','serverIp':'10.22.23.65','pingDelay':'4','dns':'3dns','route':'4rr'}\"}";
	public static final String params_7054 = "{\"header\":{\"appVersion\":\"4.0.2\",\"cmdId\":0,\"platformVersion\":\"4.0.2\",\"action\":7054,\"cmdName\":\"h5_zz\",\"userType\":1,\"uuid\":\"3C075555N9M0\",\"userID\":\"\",\"platformCode\":\"h5mobile\",\"token\":\"\"},\"body\":\"{'actTypeId':'48484','verifyCode':'438156','userId':'226AEBFA7681B41012C28C822F5456B1','mobile':'13011110028'}\"}";
	public static final String params_1030 = "{\"header\":{\"traceID\":\"\",\"appVersion\":\"5.1.6\",\"idfa\":\"D8060B8D-581D-4CF8-901F-92E720459B5F\",\"usertype\":1,\"cmdName\":\"app_ios_zz\",\"userGuid\":\"\",\"uuid\":\"04FCEE6BDE0F461FACD8594E994A6467\",\"cmdId\":\"1\",\"UserID\":\"\",\"platformVersion\":\"10.2.1\",\"action\":\"1030\",\"imei\":\"\",\"phoneName\":\"iPhone 5s\",\"platformCode\":\"IPHONE\",\"ts\":\"\"},\"body\":\"{'mobile':'13011110030'}\"}";
	public static final String params_1032 = "{\"header\":{\"traceID\":\"\",\"appVersion\":\"5.1.6\",\"idfa\":\"D8060B8D-581D-4CF8-901F-92E720459B5F\",\"usertype\":1,\"cmdName\":\"app_ios_zz\",\"userGuid\":\"\",\"uuid\":\"04FCEE6BDE0F461FACD8594E994A6467\",\"cmdId\":\"1\",\"UserID\":\"\",\"platformVersion\":\"10.2.1\",\"action\":\"1032\",\"imei\":\"\",\"phoneName\":\"iPhone 5s\",\"platformCode\":\"IPHONE\",\"ts\":\"\"},\"body\":\"{'id':'1000005034','verifycode':'014429','username':'13011110030'}\"}";
	public static final String params_1061 = "{\"header\":{\"traceID\":\"\",\"appVersion\":\"5.1.6\",\"idfa\":\"D8060B8D-581D-4CF8-901F-92E720459B5F\",\"usertype\":1,\"cmdName\":\"app_ios_zz\",\"userGuid\":\"\",\"uuid\":\"04FCEE6BDE0F461FACD8594E994A6467\",\"cmdId\":\"1\",\"UserID\":\"\",\"platformVersion\":\"10.2.1\",\"action\":\"1061\",\"imei\":\"\",\"phoneName\":\"iPhone 5s\",\"platformCode\":\"IPHONE\",\"ts\":\"\"},\"body\":\"{'username':'13011110030','id':'1000005034','mobile':'13011110030','newpwd':'aaaaaa'}\"}";
	public static final String params_207   = "{\"header\":{\"appVersion\":\"3.9.3\",\"idfa\":\"\",\"cmdName\":\"app_xx\",\"userID\":\"\",\"uuid\":\"00000000-45fe-369b-0760-812f0033c587\",\"token\":\"\",\"cmdID\":\"5000081\",\"platformVersion\":\"7.0\",\"action\":\"207\",\"imei\":\"356156074527318\",\"userType\":\"1\",\"platformCode\":\"Android\",\"phoneName\":\"samsung\"},\"body\":\"{'SchemeType':1,'BetType':1,'BonusScale':0,'AssureShare':0,'Description':'','OpenUserList':'','SecrecyLevel':4,'BuyShare':2,'Multiple':1,'LotteryID':5,'IssueName':'2017135','Number':[{'number':'02 06 08 13 19 31 + 15 ','playid':501}],'Money':2,'choosetype':2,'HongBaoSelectID':'259584'}\"}";
	public static final String params_207Jz = "{\"header\":{\"traceID\":\"\",\"appVersion\":\"5.1.6\",\"idfa\":\"D8060B8D-581D-4CF8-901F-92E720459B5F\",\"usertype\":1,\"cmdName\":\"app_ios_zz\",\"userGuid\":\"\",\"uuid\":\"04FCEE6BDE0F461FACD8594E994A6467\",\"token\":\"\",\"cmdId\":\"1\",\"UserID\":\"\",\"platformVersion\":\"10.2.1\",\"action\":\"207\",\"imei\":\"\",\"phoneName\":\"iPhone 5s\",\"platformCode\":\"IPHONE\",\"ts\":\"\"},\"body\":\"{'BonusScale':0,'Description':'','LotteryID':90,'appname':'金山奖多多','SchemeType':1,'clientinfo':'com.jiangduoduo.jsjdd','IssueID':2012,'Multiple':5,'Number':[{'playid':9005,'number':'171126011=3_2:0|1*1|0|0'}],'Money':10,'OpenUserList':'','BetType':1,'recomtype':'0','BuyShare':10,'SecrecyLevel':4,'AssureShare':0,'HongBaoSelectID':'258992'}\"}";
	public static final String params_402 = "{\"header\":{\"appVersion\":\"3.9.3\",\"idfa\":\"\",\"cmdName\":\"app_xx\",\"userID\":\"\",\"uuid\":\"00000000-45fe-369b-0760-812f0033c587\",\"token\":\"\",\"cmdID\":\"5000081\",\"platformVersion\":\"7.0\",\"action\":\"402\",\"imei\":\"356156074527318\",\"userType\":\"1\",\"platformCode\":\"Android\",\"phoneName\":\"samsung\"},\"body\":\"{'hongbaoTypes':4,'PageNo':1,'PageSize':10,'t':1}\"}";
	public static final String params_1000 = "{\"header\":{\"traceID\":\"\",\"appVersion\":\"5.1.6\",\"idfa\":\"D8060B8D-581D-4CF8-901F-92E720459B5F\",\"usertype\":1,\"cmdName\":\"app_ios_zz\",\"userGuid\":\"\",\"uuid\":\"04FCEE6BDE0F461FACD8594E994A6467\",\"token\":\"\",\"cmdId\":\"1\",\"UserID\":\"\",\"platformVersion\":\"10.2.1\",\"action\":\"1000\",\"imei\":\"\",\"phoneName\":\"iPhone 5s\",\"platformCode\":\"IPHONE\",\"ts\":\"\"},\"body\":\"{'deviceToken':'ef5fc4b83969a4f3d4e948eea2eb09c5d9ed9787a9a1e77ffd8845e335e42385','IMEI' :''}\"}";
	public static final String params_7055 = "{\"header\":{\"appVersion\":\"5.1.6\",\"cmdId\":\"1\",\"platformVersion\":\"10.2.1\",\"action\":7055,\"cmdName\":\"app_ios_zz\",\"userType\":1,\"uuid\":\"04FCEE6BDE0F461FACD8594E994A6467\",\"userID\":\"\",\"platformCode\":\"IPHONE\",\"islogin\":true,\"token\":\"\"},\"body\":\"{'actTypeId':'100001'}\"}";
	public static final String params_7056 = "{\"header\":{\"appVersion\":\"5.1.6\",\"cmdId\":\"1\",\"platformVersion\":\"10.2.1\",\"action\":7056,\"cmdName\":\"app_ios_zz\",\"userType\":1,\"uuid\":\"04FCEE6BDE0F461FACD8594E994A6467\",\"userID\":\"\",\"platformCode\":\"IPHONE\",\"islogin\":true,\"token\":\"\",\"username\":\"\"},\"body\":\"{'actTypeId':'100001'}\"}";
	
	// 竞篮期次
	public static final String jl_issue = "https://dcds.jdd.com/Api.Basket/Ajax/NBALive.ashx?action=lqissue&lotteryId=91&playId=9101&pts=0&pcode=android&cmsm=dz&version=v3.0";
	// 竞篮对阵  issue = 2017-09-04
	public static final String jl_qlive =  "https://dcds.jdd.com/Api.Basket/Ajax/NBALive.ashx?action=match&lotteryId=91&playId=9101&pts=0&pcode=android&cmsm=dz&version=v3.0&issue=";
	// 竞篮实况
	public static final String jl_live =   "https://dcds.jdd.com/Api.Basket/Ajax/NBALive.ashx?action=match&lotteryId=91&playId=9101&pts=0&pcode=android&cmsm=dz&version=v3.0&issue=";

	public static final String user_url = "http://user-api.jdd.com/user/public/securityMobileHandler.do";
	public static final String order_url = "https://order-api.jdd.com/order/public/securityMobileHandler.do";
	public static final String rp_url = "https://rp-api.jdd.com/redpacket/public/handselMobileHandler.do";
	public static final String push_url = "https://push-server.jdd.com/jdd/public/safe/pushSet.do";
	
	public static final String info_url = "https://info-api.jdd.com/info/public/safeMobileHandler.do";
	public static final String appadmin_url = "https://appadmin-api.jdd.com/appadmin/public/safeMobileHandler.do";
	
	public static final String ft_head = "https://smc.jdddata.com/api/scoredetail/head?lotteryId=90&pts=0&pcode=h5&version=v2.1&matchid=";
	public static final String ft_matchhis = "https://smc.jdddata.com/api/scoredetail/ssmatchhis?version=v2.1&pts=0&pcode=h5&lotteryId=90&mid=";
	public static final String ft_sslive = "https://smc.jdddata.com/api/scoredetail/sslive?pcode=h5&version=v2.1&pts=1506665233025&lotteryId=90&isNotLoading=true&mid=";
	public static final String ft_batteArray = "https://saishi.jdd.com/Ajax/Soccer.ashx?cmsm=teamCallback&action=getBattleArray&pcode=h5&lotteryId=90&callback=teamCallback&mid=11914100";
	public static final String ft_teamRank = "https://smc.jdddata.com/api/scoredetail/getssmatchrankinginfo?version=v2.1&pts=0&pcode=h5&lotteryId=90&mid=";
	public static final String ft_oddsPlateinfo_euro = "https://smc.jdddata.com/api/odds/getoddsplateinfo?pcode=h5&version=v2.1&pts=0&type=69&lotteryId=90&matchid=";
	public static final String ft_oddsPlateinfo_asia = "https://smc.jdddata.com/api/odds/getoddsplateinfo?pcode=h5&version=v2.1&pts=0&type=48&lotteryId=90&matchid=";
	public static final String ft_oddsPlateinfo_handicap = "https://smc.jdddata.com/api/odds/getoddsplateinfo?pcode=h5&version=v2.1&pts=0&type=8&lotteryId=90&matchid=";
	public static final String ft_oddsPlateinfo_under = "https://smc.jdddata.com/api/odds/getoddsplateinfo?pcode=h5&version=v2.1&pts=0&type=47&lotteryId=90&matchid=";
	public static final String ft_saishiSslive = "https://saishi.jdd.com/Ajax/Soccer.ashx?cmsm=sk_jsonpCallback&action=sslive&pcode=h5&lotteryId=90&isNotLoading=false&callback=sk_jsonpCallback&mid=";
	public static final String ft_matchAnalze = "https://forecast.databiger.com/odds/forecast/getSsMatchAnalyzeInfo?version=v2.1&pts=0&pcode=h5&lotteryId=90&platform=trade&matchId=";
	public static final String ft_matchAnalze_B  = "https://smc.jdddata.com/api/scoredetail/getSsMatchAnalyzeInfoVerB?version=v2.1&pts=0&pcode=h5&lotteryId=90&mid=";
	public static final String ft_matchInfo = "https://smc.jdddata.com/api/scoredetail/getMatchFullViewInfo?version=v2.1&pts=0&pcode=h5&lotteryId=90&matchId=";
	
	public static final String ft_asiaList = "https://smc.jdddata.com/api/odds/getasiaoddslist?pcode=h5&version=v2.1&pts=0&lotteryId=90&matchid=";
	public static final String ft_asiaDetail = "https://smc.jdddata.com/api/odds/getasiadetail?pcode=h5&version=v2.1&pts=0&lotteryId=90&matchid="; //&matchid=11914100&bookid=3000048
	public static final String ft_euroChart = "https://smc.jdddata.com/api/odds/geteurochartdatalist?pcode=h5&version=v2.1&pts=0&lotteryId=90&isNotLoading=false&matchid=";	//&bookid=1000&matchid=11914100
	public static final String ft_euroList = "https://smc.jdddata.com/api/odds/geteurooddslist?pcode=h5&version=v2.1&pts=0&lotteryId=90&matchid=";
	public static final String ft_euroDetail = "https://smc.jdddata.com/api/odds/geteurodetail?pcode=h5&version=v2.1&pts=0&lotteryId=90&matchid=";	//&bookid=1000
	public static final String ft_handicapList = "https://smc.jdddata.com/api/odds/gethandicapoddslist?pcode=h5&version=v2.1&pts=0&lotteryId=90&matchid=";
	public static final String ft_handicapDetail = "https://smc.jdddata.com/api/odds/geteurodetail?pcode=h5&version=v2.1&pts=0&lotteryId=90&matchid=";	//&bookid=
	public static final String ft_overunderList = "https://smc.jdddata.com/api/odds/getoverunderoddslist?pcode=h5&version=v2.1&pts=0&lotteryId=90&matchid=";
	public static final String ft_overunderDetail = "https://smc.jdddata.com/api/odds/getoverunderdetail?pcode=h5&version=v2.1&pts=0&lotteryId=90&matchid=";	//&bookid=undefined
	public static final String ft_bifa = "https://smc.jdddata.com/api/betfair/getbftradeinfo?type=1&version=v2.1&pts=0&pcode=h5&lotteryId=90&matchid=";
	public static final String ft_bifaBig = "https://smc.jdddata.com/api/betfair/getbfblocktradeinfo?version=v2.1&pts=0&pcode=h5&lotteryId=90&matchid=";
	
	public static final String ft_teamInfo = "https://saishi.jdd.com/Ajax/Soccer.ashx?cmsm=teamDetailCallback&action=ssteam&pcode=h5&version=v1.0.0&lotteryId=90&callback=teamDetailCallback";	//&teamId=2530
	
	public static final String bs_issue = "https://dcds.jdd.com/Api.Basket/Ajax/NBALive.ashx?action=lqissue&lotteryid=91&playid=9101";	// 竞篮期次
	public static final String bs_match = "https://dcds.jdd.com/Api.Basket/Ajax/NBALive.ashx?action=match&lotteryid=91&playid=9101&pts=0&issue=";	// 竞篮期次对阵
	public static final String bs_qlive = "https://dcds.jdd.com/Api.Basket/Ajax/NBALive.ashx?action=match&lotteryid=91&playid=9101&pts=0&issue=";	// 竞篮期次实况
	public static final String bs_matchs = "https://dcds.jdd.com/Api.Basket/Ajax/NBALive.ashx?action=match&matchids=12460620&lotteryid=91&pts=0&r=1507691882278";	// 竞篮关注
	public static final String bs_bsdetail = "https://smc.jdd.com/api/scoredetail/bsdetail?lotteryid=91&matchid=11483949&pcode=h5&pts=0&version=v2.1";	// NBA比赛详情
	public static final String bs_bsmatchhis = "https://smc.jdd.com/api/scoredetail/bsmatchhis?lotteryid=91&matchid=11483949&pcode=h5&pts=0&version=v2.1&isNBA=1";	// NBA历史战绩
	public static final String bs_bsteamrank = "https://saishi.jdd.com/ajax/soccer.ashx?action=bsteamrank&pcode=h5&version=v1.0.0&cmsm=rankingListCallback&IsNBA=1&lotteryId=91&seasonId=10001&tournamentId=10000";	// NBA球队排名
	public static final String bs_bsstats = "https://saishi.jdd.com/ajax/soccer.ashx?action=bsstats&pcode=h5&version=v1.0.0&cmsm=pkCallback&IsNBA=1&lotteryId=91&matchId=11483949&pts=0";	// NBA实况
	public static final String bs_bsbattlearray = "https://saishi.jdd.com/ajax/soccer.ashx?action=bsbattlearray&pcode=h5&version=v1.0.0&cmsm=zrCallback&IsNBA=1&lotteryId=91&matchId=11483949";	// NBA球队阵容
	public static final String bs_bsteam = "https://saishi.jdd.com/ajax/soccer.ashx?action=bsteam&pcode=h5&version=v1.0.0&cmsm=dzCallback&IsNBA=1&lotteryId=91&teamId=1024&year=2016";	// NBA球队详情
	public static final String bs_overunder = "https://saishi.jdd.com/ajax/soccer.ashx?action=bsteam&pcode=h5&version=v1.0.0&cmsm=dzCallback&IsNBA=1&lotteryId=91&teamId=1024&year=2016";	// 胜负赔率
	public static final String bs_overunderdetail = "https://saishi.jdd.com/ajax/soccer.ashx?action=bsteam&pcode=h5&version=v1.0.0&cmsm=dzCallback&IsNBA=1&lotteryId=91&teamId=1024&year=2016";	// 胜负赔率详情
	public static final String bs_asianodds = "https://saishi.jdd.com/ajax/soccer.ashx?action=asianodds&pcode=h5&version=v1.0.0&cmsm=asianOddsCallback&IsNBA=1&lotteryId=91&matchid=11483949";	// 让分赔率
	public static final String bs_asianoddsdetail= "https://saishi.jdd.com/ajax/soccer.ashx?action=asianoddsdetail&pcode=h5&version=v1.0.0&cmsm=dzCallback&isnba=1&lotteryId=91&matchid=11483949&bookid=-99999";	// 让分赔率详情
	public static final String bs_handicap = "https://saishi.jdd.com/ajax/soccer.ashx?action=handicap&pcode=h5&version=v1.0.0&cmsm=handicapCallback&IsNBA=1&lotteryId=91&matchid=11483949";	// 大小分赔率
	public static final String bs_handicapdetail = "https://saishi.jdd.com/ajax/soccer.ashx?action=handicapdetail&pcode=h5&version=v1.0.0&cmsm=dzCallback&isnba=1&lotteryId=91&matchid=11483949&bookid=-99999&handicap=20";	// 大小分赔率
	public static final String params_7051 = "{\"header\":{\"appVersion\":\"4.0.2\",\"cmdId\":0,\"platformVersion\":\"4.0.2\",\"action\":7051,\"cmdName\":\"h5_zz\",\"userType\":1,\"uuid\":\"3C075555N9M0\",\"userID\":\"\",\"platformCode\":\"h5mobile\",\"token\":\"\"},\"body\":\"{'actTypeId':'78674','redemptionCode':'2c3c67cb3ca8114f','mobile':'13811110001','verifyCode':'660727'}\"}";
	
	
	public static void main(String[] args) {
		
	}

}
