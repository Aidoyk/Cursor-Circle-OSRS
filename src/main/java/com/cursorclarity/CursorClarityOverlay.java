package com.cursorclarity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

class CursorClarityOverlay extends Overlay
{
	// How long the ring stays fully visible after the mouse stops moving, before it starts fading (ms)
	private static final long IDLE_GRACE_MS = 400;
	// How long the fade-out takes once it starts (ms)
	private static final long IDLE_FADE_MS = 600;

	private final Client client;
	private final CursorClarityConfig config;

	private Point lastMousePos = null;
	private long lastMoveTime = System.currentTimeMillis();

	@Inject
	private CursorClarityOverlay(Client client, CursorClarityConfig config)
	{
		this.client = client;
		this.config = config;
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ALWAYS_ON_TOP);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (config.onlyInCombat())
		{
			if (client.getLocalPlayer() == null || client.getLocalPlayer().getInteracting() == null)
			{
				return null;
			}
		}

		net.runelite.api.Point mouse = client.getMouseCanvasPosition();
		if (mouse == null || mouse.getX() < 0 || mouse.getY() < 0)
		{
			return null;
		}

		Point currentPos = new Point(mouse.getX(), mouse.getY());
		long now = System.currentTimeMillis();

		if (lastMousePos == null || !currentPos.equals(lastMousePos))
		{
			lastMousePos = currentPos;
			lastMoveTime = now;
		}

		float alpha = 1f;
		if (config.hideWhenIdle())
		{
			long idleFor = now - lastMoveTime;
			if (idleFor > IDLE_GRACE_MS)
			{
				long fadeProgress = idleFor - IDLE_GRACE_MS;
				alpha = 1f - Math.min(1f, fadeProgress / (float) IDLE_FADE_MS);
				if (alpha <= 0f)
				{
					return null;
				}
			}
		}

		int baseRadius = config.radius();
		int radius = baseRadius;
		if (config.pulse())
		{
			// Smooth sine pulse, +/- 15% of base radius, ~1.5s period
			double phase = (now % 1500) / 1500.0 * 2 * Math.PI;
			radius = (int) (baseRadius + Math.sin(phase) * baseRadius * 0.15);
		}

		Color base = config.ringColor();
		Color drawColor = new Color(
			base.getRed(),
			base.getGreen(),
			base.getBlue(),
			Math.round(base.getAlpha() * alpha)
		);

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setColor(drawColor);
		graphics.setStroke(new java.awt.BasicStroke(config.thickness()));

		Ellipse2D.Double ring = new Ellipse2D.Double(
			currentPos.x - radius,
			currentPos.y - radius,
			radius * 2.0,
			radius * 2.0
		);
		graphics.draw(ring);

		return null;
	}
}
