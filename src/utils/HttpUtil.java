//package utils;
//
//import com.jdd.fm.core.log.LogExceptionStackTrace;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Map;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class HttpUtil {
//	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
//
//	public HttpUtil() {
//	}
//
//	public static boolean httpClientPost(String url, String paramsValues) {
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		HttpPost httpPost = new HttpPost(url);
//		ArrayList nvps = new ArrayList();
//		nvps.add(new BasicNameValuePair("params", paramsValues));
//		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName("utf-8")));
//		CloseableHttpResponse response2 = null;
//
//		try {
//			response2 = httpclient.execute(httpPost);
//			if (response2.getStatusLine().getStatusCode() != 200) {
//				boolean e = true;
//				return e;
//			}
//		} catch (Exception var23) {
//			logger.error("请求URL= {} Post请求异常; StackTrace = {} ", url, LogExceptionStackTrace.erroStackTrace(var23));
//			boolean e1 = false;
//			return e1;
//		} finally {
//			if (response2 != null) {
//				try {
//					response2.close();
//				} catch (IOException var22) {
//					var22.printStackTrace();
//					logger.error("关闭CloseableHttpResponse失败", var22);
//				}
//			}
//
//			if (httpclient != null) {
//				try {
//					httpclient.close();
//				} catch (IOException var21) {
//					var21.printStackTrace();
//					logger.error("关闭CloseableHttpClient失败", var21);
//				}
//			}
//
//		}
//
//		return true;
//	}
//
//	public static String sendGet(String url, String param) {
//		String result = "";
//		BufferedReader in = null;
//
//		try {
//			String e2 = url + "?" + param;
//			URL realUrl = new URL(e2);
//			URLConnection connection = realUrl.openConnection();
//			connection.setRequestProperty("accept", "*/*");
//			connection.setRequestProperty("connection", "Keep-Alive");
//			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//			connection.setConnectTimeout(30000);
//			connection.connect();
//			Map map = connection.getHeaderFields();
//
//			String line;
//			for (in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
//					"UTF-8")); (line = in.readLine()) != null; result = result + line) {
//				;
//			}
//		} catch (Exception var17) {
//			var17.printStackTrace();
//		} finally {
//			try {
//				if (in != null) {
//					in.close();
//				}
//			} catch (Exception var16) {
//				var16.printStackTrace();
//			}
//
//		}
//
//		return result;
//	}
//
//	public static String sendPost(String url, String param) {
//		PrintWriter out = null;
//		BufferedReader in = null;
//		String result = "";
//
//		try {
//			URL e = new URL(url);
//			URLConnection conn = e.openConnection();
//			conn.setRequestProperty("accept", "*/*");
//			conn.setRequestProperty("connection", "Keep-Alive");
//			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//			conn.setDoOutput(true);
//			conn.setDoInput(true);
//			conn.setConnectTimeout(30000);
//			out = new PrintWriter(conn.getOutputStream());
//			out.print(param);
//			out.flush();
//
//			String line;
//			for (in = new BufferedReader(new InputStreamReader(conn.getInputStream(),
//					"UTF-8")); (line = in.readLine()) != null; result = result + line) {
//				;
//			}
//		} catch (Exception var15) {
//			var15.printStackTrace();
//			throw new RuntimeException("创建Post请求失败！", var15);
//		} finally {
//			try {
//				if (out != null) {
//					out.close();
//				}
//
//				if (in != null) {
//					in.close();
//				}
//			} catch (IOException var14) {
//				var14.printStackTrace();
//				throw new RuntimeException("创建Post请求失败！", var14);
//			}
//
//		}
//
//		return result;
//	}
//
//	public static String sendHttpPost(String httpUrl, String params) {
//		HttpPost httpPost = new HttpPost(httpUrl);
//
//		try {
//			StringEntity e = new StringEntity(params, "UTF-8");
//			e.setContentType("application/json");
//			httpPost.setEntity(e);
//		} catch (Exception var4) {
//			throw new RuntimeException("创建Post请求失败！", var4);
//		}
//
//		return sendHttpPost(httpPost);
//	}
//
//	public static String sendHttpPost(String httpUrl, String params, int timeOut) {
//		HttpPost httpPost = new HttpPost(httpUrl);
//
//		try {
//			StringEntity e = new StringEntity(params, "UTF-8");
//			e.setContentType("application/json");
//			httpPost.setEntity(e);
//		} catch (Exception var5) {
//			throw new RuntimeException("创建Post请求失败！", var5);
//		}
//
//		return sendHttpPost(httpPost, timeOut);
//	}
//
//	public static String sendHttpPost(String httpUrl, Map<String, String> params, int timeOut) {
//		HttpPost httpPost = new HttpPost(httpUrl);
//
//		try {
//			ArrayList e = new ArrayList();
//			Iterator iterator = params.keySet().iterator();
//
//			while (iterator.hasNext()) {
//				String key = (String) iterator.next();
//				e.add(new BasicNameValuePair(key, (String) params.get(key)));
//			}
//
//			httpPost.setEntity(new UrlEncodedFormEntity(e, Charset.forName("utf-8")));
//			return sendHttpPost(httpPost, timeOut);
//		} catch (Exception var7) {
//			throw new RuntimeException("创建Post请求失败！", var7);
//		}
//	}
//
//	public static String sendHttpPost(String httpUrl, Map<String, String> params) {
//		HttpPost httpPost = new HttpPost(httpUrl);
//
//		try {
//			ArrayList e = new ArrayList();
//			Iterator iterator = params.keySet().iterator();
//
//			while (iterator.hasNext()) {
//				String key = (String) iterator.next();
//				e.add(new BasicNameValuePair(key, (String) params.get(key)));
//			}
//
//			httpPost.setEntity(new UrlEncodedFormEntity(e, Charset.forName("utf-8")));
//			return sendHttpPost(httpPost);
//		} catch (Exception var6) {
//			throw new RuntimeException("创建Post请求失败！", var6);
//		}
//	}
//
//	private static String sendHttpPost(HttpPost httpPost) {
//		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000)
//				.setConnectionRequestTimeout(5000).build();
//		CloseableHttpClient httpClient = null;
//		CloseableHttpResponse response = null;
//		String responseContent = null;
//
//		try {
//			httpClient = HttpClients.createDefault();
//			httpPost.setConfig(requestConfig);
//			response = httpClient.execute(httpPost);
//			HttpEntity e = response.getEntity();
//			responseContent = EntityUtils.toString(e, "UTF-8");
//		} catch (Exception var13) {
//			throw new RuntimeException(String.format("请求%s失败", new Object[] { httpPost.getURI() }), var13);
//		} finally {
//			try {
//				if (response != null) {
//					response.close();
//				}
//
//				if (httpClient != null) {
//					httpClient.close();
//				}
//			} catch (IOException var12) {
//				var12.printStackTrace();
//			}
//
//		}
//
//		return responseContent;
//	}
//
//	private static String sendHttpPost(HttpPost httpPost, int timeOut) {
//		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut)
//				.setConnectionRequestTimeout(timeOut).build();
//		CloseableHttpClient httpClient = null;
//		CloseableHttpResponse response = null;
//		String responseContent = null;
//
//		try {
//			httpClient = HttpClients.createDefault();
//			httpPost.setConfig(requestConfig);
//			response = httpClient.execute(httpPost);
//			HttpEntity e = response.getEntity();
//			responseContent = EntityUtils.toString(e, "UTF-8");
//		} catch (Exception var14) {
//			throw new RuntimeException(String.format("请求%s失败", new Object[] { httpPost.getURI() }), var14);
//		} finally {
//			try {
//				if (response != null) {
//					response.close();
//				}
//
//				if (httpClient != null) {
//					httpClient.close();
//				}
//			} catch (IOException var13) {
//				var13.printStackTrace();
//			}
//
//		}
//
//		return responseContent;
//	}
//
//	@Test
//	public void dd() {
//		System.out.println(
//				sendAuthorizationPost("http://10.10.11.188/jddvts/format/json", "vtsadmin", "iOBpp1eepslwwBeeUnFK"));
//	}
//
//	private static String getHeader(String userName, String passWord) {
//		String auth = userName + ":" + passWord;
//		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
//		String authHeader = "Basic " + new String(encodedAuth);
//		return authHeader;
//	}
//
//	public static String sendAuthorizationPost(String url, String userName, String passWord) {
//		CloseableHttpClient client = HttpClients.createDefault();
//		HttpGet get = new HttpGet(url);
//		get.addHeader("Authorization", getHeader(userName, passWord));
//		String responseContent = null;
//		CloseableHttpResponse response = null;
//
//		try {
//			response = client.execute(get);
//			if (response.getStatusLine().getStatusCode() == 200) {
//				HttpEntity e = response.getEntity();
//				responseContent = EntityUtils.toString(e, "UTF-8");
//			}
//		} catch (ClientProtocolException var116) {
//			var116.printStackTrace();
//		} catch (IOException var117) {
//			var117.printStackTrace();
//		} finally {
//			try {
//				if (response != null) {
//					response.close();
//				}
//			} catch (IOException var114) {
//				var114.printStackTrace();
//			} finally {
//				try {
//					if (client != null) {
//						client.close();
//					}
//				} catch (IOException var113) {
//					var113.printStackTrace();
//				}
//
//			}
//
//		}
//
//		return responseContent;
//	}
//}
