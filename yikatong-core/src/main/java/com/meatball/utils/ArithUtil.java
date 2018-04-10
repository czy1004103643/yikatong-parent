/**
 * Project Name:meatball-core
 * File Name:ArithUtil.java
 * Package Name:com.meatball.utils
 * Date:2018年3月20日上午10:15:18
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**   
 * @Title: ArithUtil.java 
 * @Package com.meatball.utils 
 * @Description: TODO(算法工具类) 
 * @author jw 
 * @date 2018年3月20日 上午10:15:18 
 * @version V1.0   
 */
public class ArithUtil {
	// 默认除法运算精度
		@SuppressWarnings("unused")
		private static final int DEF_DIV_SCALE = 10;

		private ArithUtil() {
			
		}

		/**
		 * 提供精确的加法运算。
		 * 
		 * @param v1
		 *            被加数
		 * @param v2
		 *            加数
		 * @return 两个参数的和
		 */
		public static double add(double v1, double v2) {
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			return b1.add(b2).doubleValue();
		}

		/**
		* @Title: addByFloat 
		* @Description: TODO(提供精确的加法运算,默认四舍五入保留2位小数) 
		* @param @param v1
		* @param @param v2
		* @param @return    设定文件 
		* @return float    返回类型 
		* @throws
		 */
		public static float addByFloat(float v1, float v2) {
			BigDecimal b1 = new BigDecimal(Float.toString(v1));
			BigDecimal b2 = new BigDecimal(Float.toString(v2));
			return roundFloat(b1.add(b2).floatValue(), 2);
		}
		
		
		
		/**
		 * 提供精确的减法运算。
		 * 
		 * @param v1
		 *            被减数
		 * @param v2
		 *            减数
		 * @return 两个参数的差
		 */
		public static double sub(double v1, double v2) {
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			return b1.subtract(b2).doubleValue();
		}

		/**
		* @Title: subByFloat 
		* @Description: TODO(精确的减法运算,默认四舍五入保留2位小数) 
		* @param @param v1
		* @param @param v2
		* @param @return    设定文件 
		* @return float    返回类型 
		* @throws
		 */
		public static float subByFloat(float v1, float v2) {
			BigDecimal b1 = new BigDecimal(Float.toString(v1));
			BigDecimal b2 = new BigDecimal(Float.toString(v2));
			return roundFloat(b1.subtract(b2).floatValue(),2);
		}
		
		
		
		/**
		 * 提供精确的乘法运算。
		 * 
		 * @param v1
		 *            被乘数
		 * @param v2
		 *            乘数
		 * @return 两个参数的积
		 */
		public static double mul(double v1, double v2) {
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			return b1.multiply(b2).doubleValue();
		}

		/**
		* @Title: mulByFloat 
		* @Description: TODO(精确的乘法运算,默认四舍五入保留2位小数) 
		* @param @param v1
		* @param @param v2
		* @param @return    设定文件 
		* @return float    返回类型 
		* @throws
		 */
		public static float mulByFloat(float v1, float v2) {
			BigDecimal b1 = new BigDecimal(Float.toString(v1));
			BigDecimal b2 = new BigDecimal(Float.toString(v2));
			return roundFloat(b1.multiply(b2).floatValue(), 2);
		}
		
		
		/**
		 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后2位，以后的数字四舍五入。
		 * 
		 * @param v1
		 *            被除数
		 * @param v2
		 *            除数
		 * @return 两个参数的商
		 */
		public static double div(double v1, double v2) {
			return div(v1, v2, 2);
		}

		/**
		* @Title: divByFloat 
		* @Description: TODO(精确的除法运算,默认四舍五入保留2位小数) 
		* @param @param v1
		* @param @param v2
		* @param @return    设定文件 
		* @return float    返回类型 
		* @throws
		 */
		public static float divByFloat(float v1, float v2) {
			return divByFloat(v1, v2, 2);
		}
		
		public static float divByFloat(float v1, float v2, int scale) {
			if (scale < 0) {
				throw new IllegalArgumentException("参数scale必须为整数为零!");
			}
			BigDecimal b1 = new BigDecimal(Float.toString(v1));
			BigDecimal b2 = new BigDecimal(Float.toString(v2));
			return roundFloat(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).floatValue(),2);
		}
		
		
		
		/**
		 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
		 * 
		 * @param v1
		 *            被除数
		 * @param v2
		 *            除数
		 * @param scale
		 *            表示表示需要精确到小数点以后几位。
		 * @return 两个参数的商
		 */
		public static double div(double v1, double v2, int scale) {
			if (scale < 0) {
				throw new IllegalArgumentException("参数scale必须为整数为零!");
			}
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		}

		/**
		 * 提供精确的小数位四舍五入处理。
		 * 
		 * @param v
		 *            需要四舍五入的数字
		 * @param scale
		 *            小数点后保留几位
		 * @return 四舍五入后的结果
		 */
		public static double round(double v, int scale) {
			if (scale < 0) {
				throw new IllegalArgumentException("参数scale必须为整数为零!");
			}
			BigDecimal b = new BigDecimal(Double.toString(v));
			BigDecimal one = new BigDecimal("1");
			return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		}

		public static float roundFloat(float v, int scale) {
			if (scale < 0) {
				throw new IllegalArgumentException("参数scale必须为整数为零!");
			}
			BigDecimal b = new BigDecimal(Float.toString(v));
			BigDecimal one = new BigDecimal("1");
			return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).floatValue();
		}
		
		
		
		
		/**
		 * 提供精确的类型转换(Float)
		 * 
		 * @param v
		 *            需要被转换的数字
		 * @return 返回转换结果
		 */
		public static float convertsToFloat(double v) {
			BigDecimal b = new BigDecimal(v);
			return b.floatValue();
		}

		/**
		 * 提供精确的类型转换(Int)不进行四舍五入
		 * 
		 * @param v
		 *            需要被转换的数字
		 * @return 返回转换结果
		 */
		public static int convertsToInt(double v) {
			BigDecimal b = new BigDecimal(v);
			return b.intValue();
		}

		/**
		 * 提供精确的类型转换(Long)
		 * 
		 * @param v
		 *            需要被转换的数字
		 * @return 返回转换结果
		 */
		public static long convertsToLong(double v) {
			BigDecimal b = new BigDecimal(v);
			return b.longValue();
		}

		/**
		 * 返回两个数中大的一个值
		 * 
		 * @param v1
		 *            需要被对比的第一个数
		 * @param v2
		 *            需要被对比的第二个数
		 * @return 返回两个数中大的一个值
		 */
		public static double returnMax(double v1, double v2) {
			BigDecimal b1 = new BigDecimal(v1);
			BigDecimal b2 = new BigDecimal(v2);
			return b1.max(b2).doubleValue();
		}

		/**
		 * 返回两个数中小的一个值
		 * 
		 * @param v1
		 *            需要被对比的第一个数
		 * @param v2
		 *            需要被对比的第二个数
		 * @return 返回两个数中小的一个值
		 */
		public static double returnMin(double v1, double v2) {
			BigDecimal b1 = new BigDecimal(v1);
			BigDecimal b2 = new BigDecimal(v2);
			return b1.min(b2).doubleValue();
		}

		/**
		 * 精确比较两个数字
		 * 
		 * @param v1
		 *            需要被对比的第一个数
		 * @param v2
		 *            需要被对比的第二个数
		 * @return 如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1
		 */
		public static int compareTo(double v1, double v2) {
			BigDecimal b1 = new BigDecimal(v1);
			BigDecimal b2 = new BigDecimal(v2);
			return b1.compareTo(b2);
		}
		
		/**
		 * 获取数字小数位数
		 * 
		 * @param number
		 *            数字.
		 * 
		 * @return 小数位数
		 */
		public static int getDecimals(double number) {
			DecimalFormat decimalFormat = new DecimalFormat("#.####");
			String numberString = decimalFormat.format(number);
			if (numberString.indexOf(".") > 0) {
				return numberString.length() - String.valueOf(number).indexOf(".") - 1;
			} else {
				return 0;
			}
		}
		
		/**
		 * 获取数字小数位数
		 * 
		 * @param number
		 *            数字.
		 * 
		 * @return 小数位数
		 */
		public static int getDecimals(float number) {
			DecimalFormat decimalFormat = new DecimalFormat("#.####");
			String numberString = decimalFormat.format(number);
			if (numberString.indexOf(".") > 0) {
				return numberString.length() - String.valueOf(number).indexOf(".") - 1;
			} else {
				return 0;
			}
		}
}