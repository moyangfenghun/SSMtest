package ssm.test.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * sessionCode是获取验证码保存的Session的key;
 * @author yt
 * @category d
 * @serial
 * @since jdk1.7
 * 
 */
public class VerificationCode {
	public static String sessionCode = "piccode";
	private static Random r = new Random();
	private static char[] chs = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
	private static final int NUMBER_OF_CHS = 4;
	private static final int IMG_WIDTH = 107;
	private static final int IMG_HEIGHT = 42;

	/**
	 * 生成验证码，并保存于session中
	 */
	public static void generateVerificationCode(HttpServletRequest request, HttpServletResponse response) {
		BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB); // 实例化BufferedImage
		Graphics g = image.getGraphics();
		Color c = Color.WHITE; // 验证码图片的背景颜色
		g.setColor(c);
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT); // 图片的边框
		g.setFont(new Font("宋体",Font.BOLD,pxtopt()));
		StringBuffer sb = new StringBuffer(); // 用于保存验证码字符串
		int index; // 数组的下标
		for (int i = 0; i < NUMBER_OF_CHS; i++) {
			index = r.nextInt(chs.length); // 随机一个下标
			g.setColor(new Color(r.nextInt(88), r.nextInt(210), r.nextInt(150))); // 随机一个颜色
			g.drawString(chs[index] + "", location(i)[0], location(i)[1]); // 画出字符
			sb.append(chs[index]); // 验证码字符串
		}
		request.getSession();
		request.getSession().setAttribute("piccode", sb.toString()); // 将验证码字符串保存到session中
		//禁止缓存
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.addHeader("Cache-Control", "no-cache");// 浏览器和缓存服务器都不应该缓存页面信息
		response.addHeader("Cache-Control", "no-store");// 请求和响应的信息都不应该被存储在对方的磁盘系统中；
		response.addHeader("Cache-Control", "must-revalidate");// 于客户机的每次请求，代理服务器必须想服务器验证缓存是否过时；
		try {
			ImageIO.write(image, "jpg", response.getOutputStream());//如果tomcat 目录下没有temp目录会无法显示图片
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
//			encoder.encode(image);在将来的更新中可能会被删除
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 计算文字大小
	 * 1 pt = px * 96/72 = px * 4/3
	 */
	private static int pxtopt(){
		int pt=0;
		pt = IMG_HEIGHT;
		return pt;
	}

	/**
	 * 计算文字位置
	 * @param i 字符序号
	 * @return
	 */
	private static int[] location(int i){
		int[] local=new int[2];
		local[0]=  i*(IMG_WIDTH/NUMBER_OF_CHS-2)+4;
		local[1]= IMG_HEIGHT-5;
		return local;
	}
}
