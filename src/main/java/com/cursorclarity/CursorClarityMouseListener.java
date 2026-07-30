package com.cursorclarity;

import java.awt.event.MouseEvent;
import javax.inject.Inject;
import net.runelite.client.input.MouseAdapter;

class CursorClarityMouseListener extends MouseAdapter
{
	private final CursorClickState clickState;

	@Inject
	private CursorClarityMouseListener(CursorClickState clickState)
	{
		this.clickState = clickState;
	}

	@Override
	public MouseEvent mousePressed(MouseEvent event)
	{
		clickState.recordClick();
		return event;
	}
}