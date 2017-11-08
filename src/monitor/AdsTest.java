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

/**
 * @category WiFi万能钥匙广告位信息抓取
 * @author Alan Huang
 *
 */
public class AdsTest {

	private static final String URL = "http://a.wkanx.com/adx";
	// http://a.wkanx.com/adx?di=7&n=1&dw=350&dh=244&pos=3&isos=0&sw=360&sh=484&sd=3&so=portrait&dk=1493778059179xm1e7f&os=Android&nt=0&dt=1&from=www.eastday.com&use_new_version=1&dat=AES%3A0%3A5EF805E267874D65FB387A658D455818DF59B76591023A0C57437C4EEDA130F873AEDC279C3D4B5AA6264EB679E7055AD0E0B733EDDCC80FC57A8A9C57F082A5747BBC6E1F4CD5724B182B3B3BFB4C714496761EC9532044C3FBF0841A89B5FFB531FEF6D8CB6C6E616EF08AC176C370D9F4EAC6E525BC2FE641E7DBDEAC71CC71DB2A26CA5C3419B9A3A1D82824E40D7726060D76E2EAF9937B7D41884B73378F420AFB8CFE7050352A97BFD1CB22F8E4C5138D22A8AB6F15DD4292BF3A841289C4FAF849814A70C546F398670E141D2339BD9BE1CAA6869C7F9FE81C60CEC1EE300BAE2C7D257F4E7280DEAA4B691948531A8F46B8D5E55D6D22C027798BBD4F7A06978FFC098FD1AD24D3AE2199A17B89847B9E4F148BF8FE797C07B61E337D9604607E14E18B6D4BF21FF6AF56AFB76549AF8B213885C085DE315057AA60597AE09E3375E6C4E37D66FC681D6F4E8DD3EFF6C7F8F2A81BDB55CA8ABDB6F8BA9EBC394C5E4C2BC9ACC1D2816FA119542C52EF4533B03E8564F98172636EB63E354E7CFCB5E2D56CC1C5F88AEA725558923E344666792AA62DBBB4DCA3CE421511007F1F7B01AAD2054329D22F9FC35C59D2A5FB7C5665D7F7926FBCE8435F544194C12A73950BE2FBF4E84A2281076055AB254967F8C91B75E7329246DBF9DD6B3E3E829B8131697B60C993FDDE068513E6464CD87E5C6C6A91C57CE96999A4C079D004BB37CDC3239C6E53F86BDE7F03CC9586F29B264C22D9DC675BEAF59320AE458098DDF91B76F9B9D518F6334A313D8B8B4AFA22AC753256CE8E6727CF0AF1BE008F7E86654BBF3196630F09783E1D99539E1596A980E1ED1F5E741FCCB071F147FBB01C6D9AD5D75B83837373E57BFBF9C38C750C3BB98DBAAA0A9149E137D2D2B6A1D30C9324B89B542DA57A9CA0D85ACBB91EDF41BFB5CCCB71A098A2AB94DAE792E73D97A574F05CD0286E33FE2FD30E0262543E82D47BBD4F830865C893BFEEDD259342F25F4E2ACDBA6BEF66C2B55DDE0F76E05B1D99DDBFCB27318527B128155CEEA8378475AF3800D6AAC8AFAF7FA986DDBAA162138F252C6977D5366EF700A20D0175AA97FB67AF83A2117571B48D82C641C4C207113F5D7DCFEB8E246FF2F0668D6FFB643E4E1AC8D6F3CB5C14321CA62951AAC645CD2F18C7B428C3EA0E9D3890E82F371C9AA67CC22C32B99381DCA667F14331BBAE876DFBAC8F6CCDDBACE8971537A52129D829CF6B666CF53FA339E0200C627013AF15F05543A301B3BECDF3155F98D0B6B2337744EEBFFD0E73D9DFD827D481B2FD338F68C86367FA8EA755FEFBF04A84513EF271BBE98113296B613E7561FC7DDD71F7539194ECB44DAAE74E89F42A781007BE4EDC0FD403EDA7E1B611883961175D413760BA9D3B8DCDE48DB15ECF55D20E6E2BF4B6AA2E96AB70F18AC4825749C161B7406D7E42140B5D4A018A3F62357387DA92035F9A2570C7BE18C16454F1FBC9D01B53891EED1A4E4C5FD071D209135A12C78BA6B7E2DD2970A357BC7699E8D9BD052AEFD504B61BF0A19B06521264C2619744E77BC22AE062F699B6EC69897E040A2831C2380A67818D8B6B6C42B8170977489E8406AC37EABE34000EB8AC4AB9B82DC291A092277BC49A755A683C72BEEDD5EB141AB0AC9F2FB02FFE694E3CEA47C6DD318BEDC15525F9C78A5CF37651FF4B93B87E5E94232651C3349BAA80300CEE69EDF236E3B437BD0E24BF652DD2766DFB480485ED2BBA93E5C1EAD5A697EEACDFF8520DBA6D5B4A38F1135B33F55E3DC93FD61130EA80A8DCC748836563585143A43311BA667806EC76B2B0F58EA8C861F7642863596209149A8473CC96567328270FA864FB930A7AA0CED2B73DAA4981E261EA2A6AC44B103DA93410B563FFFFE611584702F6854B9ADA336848B763C6D978A6FD9DBE441D1E5A7579C7EC0A47A58359CC72F4328C32CE15FED13AF98E5D56F4B6A0B4CA7C73A11F2B4B5F1CFD31F9FFF47F3B50ABF5591074B983F558F70A20F1281C476796F96357EDCBD9C9E16F2577FD275ABE1E55D81F97097161E49B8DC4BD277D0EC18DF7743A2FB7A5414EA4F727C682B23CF50DC378A00CA4054FB7946ADC2962C6435346626E46095AEC75248FFF745C482F82308F777F25C2D8C4B09554C60BC30CE5F6814CDA42D2B138CB4A71FAE479946F9104C07DE4FA1BA76EB8ACEBD867BB756AE64A1A8C8CEB1A116A2A5AD523D23F7E2E907808267CAF829EE61A4B64DB718933405599BCF83DB3FEBD94BC0E3890778E2FCB9CAB442A470FC26A1CC7186DE68923FDDEFA1AC70DAF183D6F86B62A2C0A7C423E6867780F5B7E189387B518B34504644A2BD478F4AE2831B05358B5E13B604533DFDE17A7E92640ECFC85941C9C67AAD3F5E4147E907A2ED3A959FE510B62A5AEBBD9221BF1D58966AB026DDAC4F992B6601467D36BF738C71B2761F8CF2B94484E96F5428D76BE2535C48575C46EC574D6F45B450AC42DAA4CF61868243472952BF3A9BA30BBFFB5F09EBA1F3FF9169467998109EAEA0173C23CBA63EF53B7F833BB73EE9B4E794DD73F4E21ACC04605CC6D56BDE18214473829BD89CE69524929E36990D57CF2890E84211C83F14442F874E2EB8ADE2EB00566523891399B4FC7EE109313E2961753AED878438BA225E9C46363A5997231A97E34EAB5011E95B58B17D07D1E4867BD79794CA0B7D06FDD9163C3B57AFB8BF1F621E6E5D218729078F482A7BA0E2ACAE5FB71BC8FC698C5590341ECE2BA971200EA006DAF9D7AB4F933F687F664317CB4A07FB5AB6E67CDF229A5531271545D67ECC3B14C94338C8E30F7D54BF88B2BCA15240E357F694D14B938326ADEF555561D2D7925C5E895393EFDC1C5946FEF3376191F97CF6A1839D8288E243E73DD9DBE04180398140650152019C62A6C4F792F3E67515E8F2129CECE00B815BB26D7251312A71254ACB2D1139E7BE1E2B7EBC0AEABDD4018CE4334B85C7361581800F80CCB383A48C446BF25C137BA7B9913DA96684A46C9CDEDEFFA881AB0011860825DF95216EC503EA66FE82A4CF16B89AC38FE3CD7BE78ED7575E90E543C9973EBB6F100A87214444CAE5FFE9756A735D25221693F790A4C80CA9A082158E05F0B0D07FBB5DF599FB4138707A378EBF97653E02EC958DD385BFDFB7F2D3A96B73A02E21D4833B6170DB4B077DC42D2294D3D8E400E6CBDF45CE01C1DC324EF6CF65B2EF40610B297C0346DEA018A4E07497E3FF3DE005553C872A0D79CFBFE02E775D3AB0B8D45C45B33749F52AE49DA35271DE902642DAC28DAB129364AE9C99AD1398944742CBBA7C22AF6A516A7CCBA4898D192DBB90B71DBE0FE36253C8FDE059A03ED54213D1A4A90E2FDC082EDB890E2878891E21074A6146799AD47B5BDD5143E7FB9CFAC6DE0BBF019760E8B3C26F275775557E55684F1ED511771413751267B3EE38CB73ABD3CB4BBF3084757949C13731F7F3B8B6869D57CB6D22B5FC8F3492B4C5B79D64F4279174504A2564144696C22697199644864570E9D563A5317BEDB1E48E1D54A0A1D9978530E76B0A5A6799439A479097A2A2DC083380F73278EA220F5787909644BDF96999177C34DD1D2DDA516593DDBA5C43D1A1C3A703CFAB146D3F396C2AE2366E24622772205A02DB74E41FE3578A52D2A375BCE5CAF2422912E63086057B47B3A02A2FB58979B0CAE389E55CC46EF4E1186EC20BD801E25863782547C8D85A1D2C3DCDA9BD3B35B425214965F86C7461CFD455A70269D0A168F4EC6E32549E09451930BDA4170F352C8C9485892501EE80F6948DCD51E42603983DAA39BA98DF904874932134FFDD1B34898E5F030DEEAE812C87B061EAC7211EC4125C6B6A040C4F8D8EF169A22120F1322126227D55F89D3644CF3ED9A800CEB2C8C0F1563A6ED2D03CA2E1CD8497566BC2031F37E68D8F569D726B9F70D0FD17924EC2C5AD21A57AFD4A9DE0FBE541EF32C8B96230BF6206E404C95B50D36C137B5B81D2B3A6DE3A4FE7F52A8DC0A08BA5D5468D21100161C1CDDE89B047D9804F2CCB218D7DD2A5C72E5D13709865BADA48E780B91537B91A3744EE4AF03FD871264A8D56C5CD401F3573DD70ADC6740096DAB466BF9CBE3A9AFB71B3825979156BE8421994E119F485D05977A83921A70B8848BB6FC97CD4D164642B38EB6B4345ADCE791D58C83128CBD818B8FF846E5D63CD2EBE83E7992BBC1682CF220D4C29B645488504AD56FF588C953F46CE4722543F263B21F14684B4D09DC81940A9C5AA8D8C8D959B479AE04F0A77A53C135B44183C6F04C84011323EEBDB816108B995C521FA0D317A639E7E41C878B216759D416DFCCCCB8052EEBD124EEAF1663DAC0AFBADF872C9DFC9041F864B1EB6BA572B2B7C88559837EA2E304CC7C536FE8F6791122FD1C96698D0E169D41205CF344D5103D490992F4FFD930F6CAE81953035E94F4E0E19196BA1B100D1A6ECFEC03D9A7E69417A3ABD6497B0BE2E75BCDC93A205180D3D42B1A0CC7022E507AFDB449E195A22D9F5C0BFB892F851F4C631A420ED8D3315882F53B4894F9457577775EDE1C8B5D799E41FF7075E0CD236A81D92775B21F42603598295BBAE6ED48EC247707FF0BD6F217C50B17D73ADC00F201A8F14CC95ECF4719D23C442912533A089D0B167491F6D512895B6DF1E42D200EFD6B2BADCA77F8D68E52C46FDFB782D38A575546D2B8721E685CEB80DC7BD8F1C7A9DE9F08924CBC713329F40B6E5A723DC50D77A07AF29832A06D0E64B83107BF5AF482BEAC798DE7188E216F940FEC49D27D2D59A15A8AB399095E668B2B6E8ADF66BA05AB6DBDC968303FA1858BDBCB94E57ED561B07464E43B68F917CC6E27DC68C7B48C999285017935B6EF2BCB850A408DD084718A4D226E0AFDA27025F16D84126F4414D4A6D94292CB8750B1BAC482F16D9D47F33713666A1CC90F4B53342F1232692F131C62E98F04697EE1325D93F143A830D9D63A6E0873BC3C7951C280BA7F8482363CD18D22144602D519AA1BD2575196352913F550D1E78FE110419DD37CBB1F79BBE7A76CF0F753AD26062E98376BB96F9C796D3B7DE1987AC3391FAD92F738D6451C25546A5247CADB9DBEB4B6398E3BEE0DC3570D16871A0A4987BC867F2FFD1BEC46299F48ED7C618E83A43E35D699925E0E208949EC6D98922A61A01BC37073502ABE75719843D3128E5888A2BC211B7CB699C0CD40FD2C816001E7290FADC805BEBDBB28DF269DD4BB3B6EDA767357FCBEAB102442207869EE4FC610DAF1FDC1056EF9E28A7E7AE724E792C2E1F0355EF361691A00B38723E24E39FE4912BFAD8B1C1DCFEB18B86DC355ECA0253BAC702B912AABD29890B6094D78345C2784983798F1FDC155E225CB8154387B6E50760A6C3FDCC9B3658B22C9ED3AF6190046EAC849F2C962E947BE18DD44F3A74E2F28729431B80852312C549ECB1E2FE1BEBF17F6D5F07352855F252C4D8BB46EFB70B58A56D8EFE1F40DB6335A3F21352C55DBFBBFE95E4BF4790CB9C3613BF3856D091DDE072A96B6E5124A2B2694B535EEB3C5676B0B9D0AF25C9731B3A3AAC1EA7114A8C9E8EE65458DD66AF1186AFFAEBCF199D70CE46E87BA529AA9AF029F96AD2F0C78359FC2260438CEAD843B0C099E94A48B9A03F2848772B4BB6A8BA7340478C85527C03230A9059E6B1AA4DFF72F038316862AF41A209E7C706C783151515837761C09E52734DD7DCA3DEBAA27C07F8D6157A4A5127CAB5BA720652241498B8D44C3E41E17D4C67745C1DCCC94C48E615E6972E8BDB9D2F5DBD765EB17385F1A4F9F0EF15305CB13DA65511FCEF7FE51FFABFC6C4A0A3A26E379E1E47B8A61E501B863E6C69EF49E01CFBF6E387D290613EF55323E3CDC3861F1EA653682261B8EF22933CDE832E906BE4C8A9D0E7EAAF0077802915ED658EEF5BFA03B0202C809D042188547E9107AA9A0DF55A98B636403BC2B2DC2B4DD45BAB349BCBB58C8744AB8C003FD0B225BE89ADC8D74A3A73F6A67FB946308A7F643C88CCBC257828CA274CA34A5D01B5FED0B400C5C0B1EC339EBAA1A263475982B21E10E07478E15DAAE87F9711E652DB259CC0BC508ED895C8206371A95393CCA5A8F4919ED69201E2DBD634B9534839358578719C95E67E63B3DCD06968DDD821D3BCB8C63AD228CE9C56F46A53A116B4B639B6940247B1DAA58318059F38323C9276DF4EC262D119A3B67EFB68F61A5F9CF3442BDFA1CED1324C3B30B56ABF1972E78810AE8283B2A6CBFF9C24D2B1341938AD7D5624621E2D8527CAC6843CE24257CE7BE6E14FF750C595CB22E56D0C0A5A6E0E1764901E45A725A6A4A3A18C0463B705ECC345E680C56F179FCB739BF0B725E1832E2BD446E7E157244B215464302007B9E5451AFCF4DB323CDC865634554EC4FF1DFE5305C816F01B718F198826A4062163F9F34785996B8D6F0AB4D1864423F0CCB4EF9E92F6FC03B07DF22DA65EA664A85D434D952E0851528F48BDD7987F3BB2D324FAEB17530195F4F573706D1A433B24563344FC03147D17DF74926F826BD14FA36A359648D266E8DC6AC99F58681B363F8722F724B8A1C40C8269C46B5800A657470D1B125159F9F5A62212C4E739BDFFE56711DEBA9682526DA0E8BA62F10CA0FD7EA9360393D7F311E255F454FE85D27DEAD092C7FF2A8B509F9D95A29230B7717FE361BCFFA6A0B59F5D7E2CA35428F8F4C4BC40CE19E2745D7FC209B738241FF5325E4C71C151738A1D1401E5AF3B95ACF490823E221D168C70FF4A695CBEF55ECFD1EB5AE14613ED64A0C3CD26EA5E911A4C0DC32E7067321A5F8DE0C0DFF93F8BB8D49DD30C35B270E404E815BA0FE534BF5460ACA56849BAAE374E8A5144CD429E449CAE612F088BD2910671BDF64369E6A4FBE4A82E35830B38D52F9C36FA000912FA5F8276AECDED7AFFA2E911E0C3CF8C58CB7553D6197F80DDA163E0665E19819AB6BB8662FA5CFB86FAEE02403B6A4967F7694D834F92830B199651DBD92867F918901BF1D3F3A0E6A4734B4586C3EF13B7FC6CA91E78CD4CF50A99F488F73CC2ACE000AD52F89E4A8E1E6439EA36F4463123E7425A82184F52D844F181063C1FFEE9CDF140CC378298EFB5F9EDF3EA9191BAC399F8D05F4A949B86BFB&mda=wifi&title=%E5%A6%B9%E5%AD%90%E8%87%AA%E6%8B%8D%E7%9C%9F%E6%98%AF%E5%8D%83%E5%A5%87%E7%99%BE%E6%80%AA%EF%BC%81

	static Map<String, String> params = new HashMap<String, String>();

	public static void saveToFile(String text, String path, boolean isClose) {
		File file = new File("runlog.txt");
		BufferedWriter bf = null;
		try {
			FileOutputStream outputStream = new FileOutputStream(file, true);
			OutputStreamWriter outWriter = new OutputStreamWriter(outputStream, "utf-8");
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

	public static void getAdsData() throws Exception {
		Date d = new Date();
		params.put("di", "7");
		params.put("n", "1");
		params.put("dw", "350");
		params.put("dh", "244");
		params.put("pos", "3");
		params.put("isos", "0");
		params.put("sw", "360");
		params.put("sh", "484");
		params.put("sd", "3");
		params.put("so", "portrait");
		params.put("dk", "1493778059179xm1e7f");
		params.put("os", "Android");
		params.put("nt", "0");
		params.put("dt", "1");
		params.put("from", "www.eastday.com");
		params.put("use_new_version", "1");
		params.put("dat",
				"AES%3A0%3A5EF805E267874D65FB387A658D455818DF59B76591023A0C57437C4EEDA130F873AEDC279C3D4B5AA6264EB679E7055AD0E0B733EDDCC80FC57A8A9C57F082A5747BBC6E1F4CD5724B182B3B3BFB4C714496761EC9532044C3FBF0841A89B5FFB531FEF6D8CB6C6E616EF08AC176C370D9F4EAC6E525BC2FE641E7DBDEAC71CC71DB2A26CA5C3419B9A3A1D82824E40D7726060D76E2EAF9937B7D41884B73378F420AFB8CFE7050352A97BFD1CB22F8E4C5138D22A8AB6F15DD4292BF3A841289C4FAF849814A70C546F398670E141D2339BD9BE1CAA6869C7F9FE81C60CEC1EE300BAE2C7D257F4E7280DEAA4B691948531A8F46B8D5E55D6D22C027798BBD4F7A06978FFC098FD1AD24D3AE2199A17B89847B9E4F148BF8FE797C07B61E337D9604607E14E18B6D4BF21FF6AF56AFB76549AF8B213885C085DE315057AA60597AE09E3375E6C4E37D66FC681D6F4E8DD3EFF6C7F8F2A81BDB55CA8ABDB6F8BA9EBC394C5E4C2BC9ACC1D2816FA119542C52EF4533B03E8564F98172636EB63E354E7CFCB5E2D56CC1C5F88AEA725558923E344666792AA62DBBB4DCA3CE421511007F1F7B01AAD2054329D22F9FC35C59D2A5FB7C5665D7F7926FBCE8435F544194C12A73950BE2FBF4E84A2281076055AB254967F8C91B75E7329246DBF9DD6B3E3E829B8131697B60C993FDDE068513E6464CD87E5C6C6A91C57CE96999A4C079D004BB37CDC3239C6E53F86BDE7F03CC9586F29B264C22D9DC675BEAF59320AE458098DDF91B76F9B9D518F6334A313D8B8B4AFA22AC753256CE8E6727CF0AF1BE008F7E86654BBF3196630F09783E1D99539E1596A980E1ED1F5E741FCCB071F147FBB01C6D9AD5D75B83837373E57BFBF9C38C750C3BB98DBAAA0A9149E137D2D2B6A1D30C9324B89B542DA57A9CA0D85ACBB91EDF41BFB5CCCB71A098A2AB94DAE792E73D97A574F05CD0286E33FE2FD30E0262543E82D47BBD4F830865C893BFEEDD259342F25F4E2ACDBA6BEF66C2B55DDE0F76E05B1D99DDBFCB27318527B128155CEEA8378475AF3800D6AAC8AFAF7FA986DDBAA162138F252C6977D5366EF700A20D0175AA97FB67AF83A2117571B48D82C641C4C207113F5D7DCFEB8E246FF2F0668D6FFB643E4E1AC8D6F3CB5C14321CA62951AAC645CD2F18C7B428C3EA0E9D3890E82F371C9AA67CC22C32B99381DCA667F14331BBAE876DFBAC8F6CCDDBACE8971537A52129D829CF6B666CF53FA339E0200C627013AF15F05543A301B3BECDF3155F98D0B6B2337744EEBFFD0E73D9DFD827D481B2FD338F68C86367FA8EA755FEFBF04A84513EF271BBE98113296B613E7561FC7DDD71F7539194ECB44DAAE74E89F42A781007BE4EDC0FD403EDA7E1B611883961175D413760BA9D3B8DCDE48DB15ECF55D20E6E2BF4B6AA2E96AB70F18AC4825749C161B7406D7E42140B5D4A018A3F62357387DA92035F9A2570C7BE18C16454F1FBC9D01B53891EED1A4E4C5FD071D209135A12C78BA6B7E2DD2970A357BC7699E8D9BD052AEFD504B61BF0A19B06521264C2619744E77BC22AE062F699B6EC69897E040A2831C2380A67818D8B6B6C42B8170977489E8406AC37EABE34000EB8AC4AB9B82DC291A092277BC49A755A683C72BEEDD5EB141AB0AC9F2FB02FFE694E3CEA47C6DD318BEDC15525F9C78A5CF37651FF4B93B87E5E94232651C3349BAA80300CEE69EDF236E3B437BD0E24BF652DD2766DFB480485ED2BBA93E5C1EAD5A697EEACDFF8520DBA6D5B4A38F1135B33F55E3DC93FD61130EA80A8DCC748836563585143A43311BA667806EC76B2B0F58EA8C861F7642863596209149A8473CC96567328270FA864FB930A7AA0CED2B73DAA4981E261EA2A6AC44B103DA93410B563FFFFE611584702F6854B9ADA336848B763C6D978A6FD9DBE441D1E5A7579C7EC0A47A58359CC72F4328C32CE15FED13AF98E5D56F4B6A0B4CA7C73A11F2B4B5F1CFD31F9FFF47F3B50ABF5591074B983F558F70A20F1281C476796F96357EDCBD9C9E16F2577FD275ABE1E55D81F97097161E49B8DC4BD277D0EC18DF7743A2FB7A5414EA4F727C682B23CF50DC378A00CA4054FB7946ADC2962C6435346626E46095AEC75248FFF745C482F82308F777F25C2D8C4B09554C60BC30CE5F6814CDA42D2B138CB4A71FAE479946F9104C07DE4FA1BA76EB8ACEBD867BB756AE64A1A8C8CEB1A116A2A5AD523D23F7E2E907808267CAF829EE61A4B64DB718933405599BCF83DB3FEBD94BC0E3890778E2FCB9CAB442A470FC26A1CC7186DE68923FDDEFA1AC70DAF183D6F86B62A2C0A7C423E6867780F5B7E189387B518B34504644A2BD478F4AE2831B05358B5E13B604533DFDE17A7E92640ECFC85941C9C67AAD3F5E4147E907A2ED3A959FE510B62A5AEBBD9221BF1D58966AB026DDAC4F992B6601467D36BF738C71B2761F8CF2B94484E96F5428D76BE2535C48575C46EC574D6F45B450AC42DAA4CF61868243472952BF3A9BA30BBFFB5F09EBA1F3FF9169467998109EAEA0173C23CBA63EF53B7F833BB73EE9B4E794DD73F4E21ACC04605CC6D56BDE18214473829BD89CE69524929E36990D57CF2890E84211C83F14442F874E2EB8ADE2EB00566523891399B4FC7EE109313E2961753AED878438BA225E9C46363A5997231A97E34EAB5011E95B58B17D07D1E4867BD79794CA0B7D06FDD9163C3B57AFB8BF1F621E6E5D218729078F482A7BA0E2ACAE5FB71BC8FC698C5590341ECE2BA971200EA006DAF9D7AB4F933F687F664317CB4A07FB5AB6E67CDF229A5531271545D67ECC3B14C94338C8E30F7D54BF88B2BCA15240E357F694D14B938326ADEF555561D2D7925C5E895393EFDC1C5946FEF3376191F97CF6A1839D8288E243E73DD9DBE04180398140650152019C62A6C4F792F3E67515E8F2129CECE00B815BB26D7251312A71254ACB2D1139E7BE1E2B7EBC0AEABDD4018CE4334B85C7361581800F80CCB383A48C446BF25C137BA7B9913DA96684A46C9CDEDEFFA881AB0011860825DF95216EC503EA66FE82A4CF16B89AC38FE3CD7BE78ED7575E90E543C9973EBB6F100A87214444CAE5FFE9756A735D25221693F790A4C80CA9A082158E05F0B0D07FBB5DF599FB4138707A378EBF97653E02EC958DD385BFDFB7F2D3A96B73A02E21D4833B6170DB4B077DC42D2294D3D8E400E6CBDF45CE01C1DC324EF6CF65B2EF40610B297C0346DEA018A4E07497E3FF3DE005553C872A0D79CFBFE02E775D3AB0B8D45C45B33749F52AE49DA35271DE902642DAC28DAB129364AE9C99AD1398944742CBBA7C22AF6A516A7CCBA4898D192DBB90B71DBE0FE36253C8FDE059A03ED54213D1A4A90E2FDC082EDB890E2878891E21074A6146799AD47B5BDD5143E7FB9CFAC6DE0BBF019760E8B3C26F275775557E55684F1ED511771413751267B3EE38CB73ABD3CB4BBF3084757949C13731F7F3B8B6869D57CB6D22B5FC8F3492B4C5B79D64F4279174504A2564144696C22697199644864570E9D563A5317BEDB1E48E1D54A0A1D9978530E76B0A5A6799439A479097A2A2DC083380F73278EA220F5787909644BDF96999177C34DD1D2DDA516593DDBA5C43D1A1C3A703CFAB146D3F396C2AE2366E24622772205A02DB74E41FE3578A52D2A375BCE5CAF2422912E63086057B47B3A02A2FB58979B0CAE389E55CC46EF4E1186EC20BD801E25863782547C8D85A1D2C3DCDA9BD3B35B425214965F86C7461CFD455A70269D0A168F4EC6E32549E09451930BDA4170F352C8C9485892501EE80F6948DCD51E42603983DAA39BA98DF904874932134FFDD1B34898E5F030DEEAE812C87B061EAC7211EC4125C6B6A040C4F8D8EF169A22120F1322126227D55F89D3644CF3ED9A800CEB2C8C0F1563A6ED2D03CA2E1CD8497566BC2031F37E68D8F569D726B9F70D0FD17924EC2C5AD21A57AFD4A9DE0FBE541EF32C8B96230BF6206E404C95B50D36C137B5B81D2B3A6DE3A4FE7F52A8DC0A08BA5D5468D21100161C1CDDE89B047D9804F2CCB218D7DD2A5C72E5D13709865BADA48E780B91537B91A3744EE4AF03FD871264A8D56C5CD401F3573DD70ADC6740096DAB466BF9CBE3A9AFB71B3825979156BE8421994E119F485D05977A83921A70B8848BB6FC97CD4D164642B38EB6B4345ADCE791D58C83128CBD818B8FF846E5D63CD2EBE83E7992BBC1682CF220D4C29B645488504AD56FF588C953F46CE4722543F263B21F14684B4D09DC81940A9C5AA8D8C8D959B479AE04F0A77A53C135B44183C6F04C84011323EEBDB816108B995C521FA0D317A639E7E41C878B216759D416DFCCCCB8052EEBD124EEAF1663DAC0AFBADF872C9DFC9041F864B1EB6BA572B2B7C88559837EA2E304CC7C536FE8F6791122FD1C96698D0E169D41205CF344D5103D490992F4FFD930F6CAE81953035E94F4E0E19196BA1B100D1A6ECFEC03D9A7E69417A3ABD6497B0BE2E75BCDC93A205180D3D42B1A0CC7022E507AFDB449E195A22D9F5C0BFB892F851F4C631A420ED8D3315882F53B4894F9457577775EDE1C8B5D799E41FF7075E0CD236A81D92775B21F42603598295BBAE6ED48EC247707FF0BD6F217C50B17D73ADC00F201A8F14CC95ECF4719D23C442912533A089D0B167491F6D512895B6DF1E42D200EFD6B2BADCA77F8D68E52C46FDFB782D38A575546D2B8721E685CEB80DC7BD8F1C7A9DE9F08924CBC713329F40B6E5A723DC50D77A07AF29832A06D0E64B83107BF5AF482BEAC798DE7188E216F940FEC49D27D2D59A15A8AB399095E668B2B6E8ADF66BA05AB6DBDC968303FA1858BDBCB94E57ED561B07464E43B68F917CC6E27DC68C7B48C999285017935B6EF2BCB850A408DD084718A4D226E0AFDA27025F16D84126F4414D4A6D94292CB8750B1BAC482F16D9D47F33713666A1CC90F4B53342F1232692F131C62E98F04697EE1325D93F143A830D9D63A6E0873BC3C7951C280BA7F8482363CD18D22144602D519AA1BD2575196352913F550D1E78FE110419DD37CBB1F79BBE7A76CF0F753AD26062E98376BB96F9C796D3B7DE1987AC3391FAD92F738D6451C25546A5247CADB9DBEB4B6398E3BEE0DC3570D16871A0A4987BC867F2FFD1BEC46299F48ED7C618E83A43E35D699925E0E208949EC6D98922A61A01BC37073502ABE75719843D3128E5888A2BC211B7CB699C0CD40FD2C816001E7290FADC805BEBDBB28DF269DD4BB3B6EDA767357FCBEAB102442207869EE4FC610DAF1FDC1056EF9E28A7E7AE724E792C2E1F0355EF361691A00B38723E24E39FE4912BFAD8B1C1DCFEB18B86DC355ECA0253BAC702B912AABD29890B6094D78345C2784983798F1FDC155E225CB8154387B6E50760A6C3FDCC9B3658B22C9ED3AF6190046EAC849F2C962E947BE18DD44F3A74E2F28729431B80852312C549ECB1E2FE1BEBF17F6D5F07352855F252C4D8BB46EFB70B58A56D8EFE1F40DB6335A3F21352C55DBFBBFE95E4BF4790CB9C3613BF3856D091DDE072A96B6E5124A2B2694B535EEB3C5676B0B9D0AF25C9731B3A3AAC1EA7114A8C9E8EE65458DD66AF1186AFFAEBCF199D70CE46E87BA529AA9AF029F96AD2F0C78359FC2260438CEAD843B0C099E94A48B9A03F2848772B4BB6A8BA7340478C85527C03230A9059E6B1AA4DFF72F038316862AF41A209E7C706C783151515837761C09E52734DD7DCA3DEBAA27C07F8D6157A4A5127CAB5BA720652241498B8D44C3E41E17D4C67745C1DCCC94C48E615E6972E8BDB9D2F5DBD765EB17385F1A4F9F0EF15305CB13DA65511FCEF7FE51FFABFC6C4A0A3A26E379E1E47B8A61E501B863E6C69EF49E01CFBF6E387D290613EF55323E3CDC3861F1EA653682261B8EF22933CDE832E906BE4C8A9D0E7EAAF0077802915ED658EEF5BFA03B0202C809D042188547E9107AA9A0DF55A98B636403BC2B2DC2B4DD45BAB349BCBB58C8744AB8C003FD0B225BE89ADC8D74A3A73F6A67FB946308A7F643C88CCBC257828CA274CA34A5D01B5FED0B400C5C0B1EC339EBAA1A263475982B21E10E07478E15DAAE87F9711E652DB259CC0BC508ED895C8206371A95393CCA5A8F4919ED69201E2DBD634B9534839358578719C95E67E63B3DCD06968DDD821D3BCB8C63AD228CE9C56F46A53A116B4B639B6940247B1DAA58318059F38323C9276DF4EC262D119A3B67EFB68F61A5F9CF3442BDFA1CED1324C3B30B56ABF1972E78810AE8283B2A6CBFF9C24D2B1341938AD7D5624621E2D8527CAC6843CE24257CE7BE6E14FF750C595CB22E56D0C0A5A6E0E1764901E45A725A6A4A3A18C0463B705ECC345E680C56F179FCB739BF0B725E1832E2BD446E7E157244B215464302007B9E5451AFCF4DB323CDC865634554EC4FF1DFE5305C816F01B718F198826A4062163F9F34785996B8D6F0AB4D1864423F0CCB4EF9E92F6FC03B07DF22DA65EA664A85D434D952E0851528F48BDD7987F3BB2D324FAEB17530195F4F573706D1A433B24563344FC03147D17DF74926F826BD14FA36A359648D266E8DC6AC99F58681B363F8722F724B8A1C40C8269C46B5800A657470D1B125159F9F5A62212C4E739BDFFE56711DEBA9682526DA0E8BA62F10CA0FD7EA9360393D7F311E255F454FE85D27DEAD092C7FF2A8B509F9D95A29230B7717FE361BCFFA6A0B59F5D7E2CA35428F8F4C4BC40CE19E2745D7FC209B738241FF5325E4C71C151738A1D1401E5AF3B95ACF490823E221D168C70FF4A695CBEF55ECFD1EB5AE14613ED64A0C3CD26EA5E911A4C0DC32E7067321A5F8DE0C0DFF93F8BB8D49DD30C35B270E404E815BA0FE534BF5460ACA56849BAAE374E8A5144CD429E449CAE612F088BD2910671BDF64369E6A4FBE4A82E35830B38D52F9C36FA000912FA5F8276AECDED7AFFA2E911E0C3CF8C58CB7553D6197F80DDA163E0665E19819AB6BB8662FA5CFB86FAEE02403B6A4967F7694D834F92830B199651DBD92867F918901BF1D3F3A0E6A4734B4586C3EF13B7FC6CA91E78CD4CF50A99F488F73CC2ACE000AD52F89E4A8E1E6439EA36F4463123E7425A82184F52D844F181063C1FFEE9CDF140CC378298EFB5F9EDF3EA9191BAC399F8D05F4A949B86BFB");
		params.put("mda", "wifi");
		params.put("title",
				"%E5%A6%B9%E5%AD%90%E8%87%AA%E6%8B%8D%E7%9C%9F%E6%98%AF%E5%8D%83%E5%A5%87%E7%99%BE%E6%80%AA%EF%BC%81");

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL, params);

		String json = hr.getContent();

		if (json == null || json.equals("")) {
			return;
		} else {

		}
		String feedback = "" + d + ",";

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray adss = obj.getJSONArray("ads");
		JSONObject ads = adss.getJSONObject(0);

		String dsp_name = ads.getString("dsp_name");

		JSONArray sub_adss = ads.getJSONArray("sub_ads");
		JSONObject sub_ads = sub_adss.getJSONObject(0);


		String title = sub_ads.getString("title").trim();
		JSONArray image_urls;
		feedback = feedback + title + ",";
		try {
			image_urls = sub_ads.getJSONArray("image_urls");
		} catch (Exception e) {
			return;
		}

		for (int j = 0; j < 3; j++) {
			if (j < image_urls.size()) {

				String url = (String) image_urls.get(j);
				if (url.contains(",")) {
					url = url.replaceAll(",", "，");
				}
				feedback = feedback + url + ",";
			} else {
				feedback = feedback + ",";
			}
		}

		feedback = feedback + dsp_name;

		System.out.println(feedback);
		saveToFile(feedback, "runlog.txt", false);
	}

	public static String getConfigProperty(String key) {
		ConfigProperty cp = new ConfigProperty("config.properties");
		String value = cp.getProperty(key);

		return value;
	}

	public static void main(String[] args) throws Exception {
		getAdsData();
	}
}
