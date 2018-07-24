package com.shop.model;
//計算在線人數
public class OnlineCounter {
	private static int online = 0;

	public static int getOnline() {
		return online;
	}

	public static void raise() {
		online++;
	}

	public static void reduce() {
		online--;
		if(online<0)
			online = 0;
	}

}
