package com.xhMall.common.util;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

@Component
public class VerifyCodeUtil {
    //产生验证码的随机因子
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKMNPQRSTUVWXYZ";
    //验证码的默认长度
    public static final int VERIFYSIZE = 4;



    //自动产生默认的随即验证码
    public static String generateVerifyCode() {
        return generateVerifyCode(VERIFYSIZE);
    }

    //自动生成验证码，并返回
    public static String generateVerifyCode(int verifySize) {
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    //自动生成验证码，并返回
    public static String generateVerifyCode(int verifySize,String sources) {
        if(null  == sources || 0 == sources.length()) {
            sources = VERIFY_CODES;
        }

        int codesLen = sources.length();
        Random random = new Random(System.currentTimeMillis());

        StringBuffer verifyCodes = new StringBuffer(verifySize);

        for(int i = 0; i<verifySize ; i++) {
            verifyCodes.append(sources.charAt(random.nextInt(codesLen-1)));
        }
        return verifyCodes.toString();
    }


    //获取随机的颜色
    private static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        Random random = new Random();
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }


    //输出指定验证码图片流

    public static byte[] outputImage(String code) throws ServletException, IOException {

        int verifySize = code.length();


        // 设置图形验证码的长和宽
        int w = 200, h = 30;

        //
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();
        //平面图形处理器
        Graphics2D g2 = image.createGraphics();
        //抗锯齿
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        Color[] colors = new Color[5];
        //颜色数组
        Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN,
                Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
                Color.PINK, Color.YELLOW };
        //渐变效果工具数组
        float[] fractions = new float[colors.length];
        //从颜色数组中随机取出5中颜色尽心组合
        for(int i = 0; i < colors.length; i++){
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
            fractions[i] = rand.nextFloat();
        }
        Arrays.sort(fractions);

        g2.setColor(Color.GRAY);// 设置边框色
        //填充指定的矩形
        g2.fillRect(0, 0, w, h);
        //获取随机的颜色
        Color c = getRandColor(200, 250);
        g2.setColor(c);// 设置背景色
        //******************
        g2.fillRect(0, 2, w, h-4);

        //绘制干扰线
        Random random = new Random();

        g2.setColor(getRandColor(160, 200));// 设置线条的颜色

        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(w - 1);
            int y = random.nextInt(h - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g2.drawLine(x, y, x + xl + 40, y + yl + 20);
        }

        // 添加噪点
        float yawpRate = 0.05f;// 噪声率

        int area = (int) (yawpRate * w * h);
        //setRGB()影片剪辑方法其后数据表示的是红、绿、蓝三色，即0xRRGGBB，举个例子如灰色，应写为setRGB(0x666666);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            //返回一个代表rgb颜色的int值
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }

        shear(g2, w, h, c);// 使图片扭曲

        g2.setColor(getRandColor(100, 160));
        int fontSize = h-4;
        Font font = new Font("宋体", Font.ITALIC, fontSize);
        g2.setFont(font);
        char[] chars = code.toCharArray();
        for(int i = 0; i < verifySize; i++){
            AffineTransform affine = new AffineTransform();
            //重置点的位置，达到扭曲的效果
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (w / verifySize) * i + fontSize/2, h/2);
            g2.setTransform(affine);
            g2.drawChars(chars, i, 1, ((w-10) / verifySize) * i + 5, h/2 + fontSize/2 - 10);
        }
        g2.dispose();
        //  ServletOutputStream sos=response.getOutputStream();
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", baos);
        byte[] buffer=baos.toByteArray();
        //response.setContentLength(buffer.length);
        // sos.write(buffer);
        baos.close();
        // sos.close();
        // session.setAttribute("checkcode", code);
        return buffer;
    }


    //返回一个代表rgb颜色的int值
    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    //返回一个rgb颜色数组
    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        Random random = new Random();
        for (int i = 0; i < rgb.length; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    //使图片扭曲
    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {
        Random random = new Random();
        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;//一帧
        int phase = random.nextInt(2);//相位

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {
        Random random = new Random();
        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }

    }

}
