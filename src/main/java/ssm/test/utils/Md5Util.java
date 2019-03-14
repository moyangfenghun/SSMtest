package ssm.test.utils;
import java.security.MessageDigest;

public class Md5Util {
	/***
	 * MD5加码 生成32位md5�?
	 */
	public final static String MD5(String s) {  
        //16进制下数字到字符的映射数�?    
       char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
               'a', 'b', 'c', 'd', 'e', 'f' };  
       try {  
           byte[] strTemp = s.getBytes();  
           MessageDigest mdTemp = MessageDigest.getInstance("MD5");  
           mdTemp.update(strTemp);  
           byte[] md = mdTemp.digest();  
           int j = md.length;  
           char str[] = new char[j * 2];  
           int k = 0;  
           for (int i = 0; i < j; i++) {  
               byte byte0 = md[i];  
               str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
               str[k++] = hexDigits[byte0 & 0xf];  
           }
           return new String(str);  
       } catch (Exception e) {  
           // TODO: handle exception  
           e.printStackTrace();  
           return null;  
       }  
   }  
	// 可�?�的加密算法   
	 public static String KL(String inStr) {   
	  // String s = new String(inStr);   
	  char[] a = inStr.toCharArray();   
	  for (int i = 0; i < a.length; i++) {   
	   a[i] = (char) (a[i] ^ 't');   
	  }   
	  String s = new String(a);   
	  return s;   
	 }   
	 // 加密后解�?   
	 public static String JM(String inStr) {   
	  char[] a = inStr.toCharArray();   
	  for (int i = 0; i < a.length; i++) {   
	   a[i] = (char) (a[i] ^ 't');   
	  }   
	  String k = new String(a);   
	  return k;   
	 }   
}
