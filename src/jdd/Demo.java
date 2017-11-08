package jdd;

import java.io.IOException;
import java.math.BigDecimal;

import com.jdd.fm.core.exception.AesException;

import redis.clients.jedis.Jedis;

public class Demo {

	public static void main(String[] args) throws IOException, AesException {
		// D = A*B*C / （A*B+B*C+A*C
		BigDecimal A = new BigDecimal("3.32");
		BigDecimal B = new BigDecimal("3.14");
		BigDecimal C = new BigDecimal("2.18");
		
		A.multiply(B).multiply(C);
		
//		float tmp = (float) ((3.32 * 3.14 * 2.18)/(3.32 * 3.14 + 3.14 * 2.18 +3.32 * 2.18));
		System.out.println(3.32 * 3.14 * 2.18);
		System.out.println(3.32 * 3.14 + 3.14 * 2.18 + 3.32 * 2.18);
//		System.out.println(3.14 * 2.18);
//		System.out.println(3.32 * 2.18);
		
//		System.out.println(tmp);
//		int count = 0;
//		for (int i = 0; i < 10; i++) {
//			for (int j = 0; j < 10; j++) {
//				for (int j2 = 0; j2 < 10; j2++) {
//					if ((i + j + j2) == 13 ) {
//						count ++;
//						System.out.println(i + "\t" + j + "\t" + j2);
//					}
//				}
//			}
//		}
//		System.out.println(count);
		
//		//连接redis服务器，192.168.0.100:6379
//        Jedis jedis = new Jedis("192.168.137.85", 6379);
//        //权限认证
//        jedis.auth("jdd.com");
//        
//        System.out.println(jedis.hget("user:info:userid", "10"));
	}

}
