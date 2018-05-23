package utils;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
    public AESUtils() {
    }

    public static String encryptAES(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(1, key);
            return parseByte2HexStr(cipher.doFinal(byteContent));
        } catch (Exception var9) {
            var9.printStackTrace();
            System.err.println("加密出错,内容={" + content + "}，密码={ " +password + "},异常={" + var9 +"}");
            return "-1";
        }
    }

    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, key);
            return cipher.doFinal(content);
        } catch (Exception var8) {
            var8.printStackTrace();
            System.err.println("加密出错,内容={" + content + "}，密码={ " +password + "},异常={" + var8 +"}");
            return "-1".getBytes();
        }
    }

    public static String decryptAES(String content, String password) {
        byte[] decrypt = decrypt(parseHexStr2Byte(content), password);
        return decrypt == null?"-1":new String(decrypt);
    }

    public static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        byte[] var2 = buf;
        int var3 = buf.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte aBuf = var2[var4];
            String hex = Integer.toHexString(aBuf & 255);
            if(hex.length() == 1) {
                hex = '0' + hex;
            }

            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if(hexStr.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[hexStr.length() / 2];

            for(int i = 0; i < hexStr.length() / 2; ++i) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte)(high * 16 + low);
            }

            return result;
        }
    }
    
    public static void main(String[] args){
    	
    	String password = "uo0mc8+w/iSYAUvaG7yVOQ==";
		String content = "{'uuid':'104','params':{}}";
		String s = encryptAES(content, password);
		
		System.err.println(s);
    }
}
