package com.cursorclarity;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.input.MouseManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
	name = "Cursor Clarity",
	description = "Draws a highlighted ring around your mouse cursor so it's easier to track during fast-paced combat, similar to cursor-highlight addons in other games.",
	tags = {"cursor", "mouse", "highlight", "accessibility", "combat", "overlay"}
)
public class CursorClarityPlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private CursorClarityOverlay overlay;

	@Inject
	private MouseManager mouseManager;

	@Inject
	private CursorClarityMouseListener mouseListener;

	@Provides
	CursorClarityConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CursorClarityConfig.class);
	}

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
		mouseManager.registerMouseListener(mouseListener);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
		mouseManager.unregisterMouseListener(mouseListener);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (!event.getGroup().equals("cursorclarity"))
		{
			return;
		}
		// Overlay reads live from config each render, nothing to cache here.
	}
}