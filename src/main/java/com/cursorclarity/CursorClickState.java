package com.cursorclarity;

import javax.inject.Singleton;

@Singleton
class CursorClickState
{
	private volatile long lastClickTime = -1;

	void recordClick()
	{
		lastClickTime = System.currentTimeMillis();
	}

	long getLastClickTime()
	{
		return lastClickTime;
	}
}