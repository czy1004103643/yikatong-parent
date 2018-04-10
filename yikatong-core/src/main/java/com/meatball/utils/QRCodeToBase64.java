/**
 * Project Name:meatball-core
 * File Name:QRCodeToBase64.java
 * Package Name:com.meatball.utils
 * Date:2018年3月17日下午1:39:24
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * @Title: QRCodeToBase64.java
 * @Package com.meatball.utils
 * @Description: TODO(二维图片转base64)
 * @author 張翔宇
 * @date 2018年3月17日 下午1:39:24
 * @version V1.0
 */
public class QRCodeToBase64 {
	private static final int black = 0xFF000000;

	private static final int white  = 0xFFFFFFFF;
 
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? black : white);
            }
        }
        return image;
    }

	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		ImageIO.write(image, format, file);
	}

	public static void createQRImage(String content, int width, int height, String path, String fileName)
			throws Exception {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		if (StringUtils.isNotBlank(path)) {
			if (!path.endsWith("/")) {
				path = path + "/";
			}
		} else {
			path = "";
		}
		String suffix = "jpg";
		if (fileName.indexOf(".") <= -1) {
			fileName = fileName + "." + suffix;
		} else {
			suffix = fileName.split("[.]")[1];
		}
		fileName = path + fileName;
		File file = new File(fileName);
		writeToFile(bitMatrix, suffix, file);
	}

	public static BufferedImage createQRImageBuffer(String content, int width, int height) {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		BufferedImage image = toBufferedImage(bitMatrix);
		return image;
	}
	
	public static String qrCodeToBase64(String qr) {
		BufferedImage qrImageBuffer = QRCodeToBase64.createQRImageBuffer(qr, 300, 300);
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		try {
			ImageIO.write(qrImageBuffer, "png", os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Base64 base64 = new Base64();
		String base64Img = new String(base64.encode(os.toByteArray()));
		return base64Img;
	}
}
