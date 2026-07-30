package com.cursorclarity;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CursorClarityTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CursorClarityPlugin.class);
		RuneLite.main(args);
	}
}
