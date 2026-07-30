# Cursor Clarity

A RuneLite plugin that draws a highlighted, animated ring around your mouse cursor —
similar to cursor-highlight addons in other games — so it's easier to track during
fast-paced combat or busy screens.

## Features

- **Configurable ring** — color, radius, and stroke thickness
- **Opacity control** — 1–100% transparency
- **Pulse animation** — the ring gently grows/shrinks so it catches your eye peripherally
- **Click animation** — the ring collapses toward the cursor and springs back out on click
- **Combat-only mode** — optionally only show the ring while your character is in combat
- **Idle fade** — optionally fade the ring out when the mouse hasn't moved recently

## Why

Busy fights, cluttered UI, and fast camera movement can make it easy to lose track of
where your cursor actually is. This plugin adds a simple, customizable visual anchor
so your cursor never gets lost in the noise.

## Configuration

All options are available in the plugin's settings panel in RuneLite:

| Option | Description |
|---|---|
| Ring color | Color of the ring |
| Radius | Size of the ring in pixels |
| Ring thickness | Stroke width |
| Opacity (%) | Transparency, 1–100% |
| Pulse animation | Toggle the idle "breathing" animation |
| Only show in combat | Only draw the ring while in combat |
| Hide when mouse is idle | Fade out after the mouse stops moving |
| Click animation | Toggle the collapse-and-spring effect on click |
| Click animation duration (ms) | How long the click animation takes |

## Notes

This plugin is purely visual — it only reads the cursor position and click timing to
draw an overlay. It does not move the mouse, simulate input, or interact with the game
<<<<<<< HEAD
in any way beyond rendering.
=======
in any way beyond rendering.
>>>>>>> 4ec0d6d08496c444d626cd48e16e89445da09f17
