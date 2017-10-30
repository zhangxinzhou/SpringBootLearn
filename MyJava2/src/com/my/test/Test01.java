package com.my.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

//保留小数
public class Test01 {
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#.00");
		BigDecimal bd = new BigDecimal("1234564654.5555");
		String result = df.format(bd);
		BigDecimal result1 = bd.setScale(3,BigDecimal.ROUND_HALF_UP);
		System.out.println(result);
		System.out.println(result1);
	}
}
