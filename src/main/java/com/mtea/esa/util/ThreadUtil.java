/*
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package com.mtea.esa.util;


/**
 * 线程工具类
 * @author 	liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2013-1-2 下午3:52:50
 */
public class ThreadUtil {

	/**
	 * 睡眠
	 * @param i
	 * @author liangqiye / 2013-1-2 下午3:52:52
	 */
	public static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException ignore) {
			ignore.printStackTrace();
		}
	}

}
