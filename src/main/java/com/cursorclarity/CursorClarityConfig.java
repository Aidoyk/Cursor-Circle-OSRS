package com.cursorclarity;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("cursorclarity")
public interface CursorClarityConfig extends Config
{
	@ConfigItem(
		keyName = "ringColor",
		name = "Ring color",
		description = "Color of the circle drawn around your cursor",
		position = 1
	)
	default Color ringColor()
	{
		return Color.RED;
	}

	@ConfigItem(
		keyName = "radius",
		name = "Radius",
		description = "Radius of the cursor ring in pixels",
		position = 2
	)
	@Range(min = 4, max = 60)
	default int radius()
	{
		return 16;
	}

	@ConfigItem(
		keyName = "thickness",
		name = "Ring thickness",
		description = "Stroke width of the cursor ring",
		position = 3
	)
	@Range(min = 1, max = 12)
	default int thickness()
	{
		return 3;
	}

	@ConfigItem(
		keyName = "opacity",
		name = "Opacity (%)",
		description = "Transparency of the ring, from 1% (nearly invisible) to 100% (fully solid)",
		position = 4
	)
	@Range(min = 1, max = 100)
	default int opacity()
	{
		return 100;
	}

	@ConfigItem(
		keyName = "pulse",
		name = "Pulse animation",
		description = "Slowly grow/shrink the ring so it's easier to spot out of the corner of your eye",
		position = 5
	)
	default boolean pulse()
	{
		return true;
	}

	@ConfigItem(
		keyName = "onlyInCombat",
		name = "Only show in combat",
		description = "Only draw the ring while your character is in combat (has a combat interaction)",
		position = 6
	)
	default boolean onlyInCombat()
	{
		return false;
	}

	@ConfigItem(
		keyName = "hideWhenIdle",
		name = "Hide when mouse is idle",
		description = "Fade the ring out after the mouse stops moving for a bit",
		position = 7
	)
	default boolean hideWhenIdle()
	{
		return false;
	}

	@ConfigItem(
		keyName = "clickAnimation",
		name = "Click animation",
		description = "Ring briefly collapses toward the cursor then springs back out when you click",
		position = 8
	)
	default boolean clickAnimation()
	{
		return true;
	}

	@ConfigItem(
		keyName = "clickAnimationDuration",
		name = "Click animation duration (ms)",
		description = "How long the collapse-and-spring-back animation takes",
		position = 9
	)
	@Range(min = 100, max = 800)
	default int clickAnimationDuration()
	{
		return 250;
	}
}