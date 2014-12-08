package de.leniki.snowflakes.obj;

import de.leniki.snowflakes.Game;
import de.leniki.snowflakes.entity.Player;
import de.leniki.snowflakes.graphics.Art;
import de.leniki.snowflakes.graphics.Screen;

public class EnergyBar {

	private Player owner;

	public EnergyBar(Player owner) {
		this.owner = owner;
	}

	public void update() {
		if (owner.isCatching()) {
			owner.energy--;
		} else {
			if (owner.energy < owner.MAX_ENERGY) owner.energy++;
		}
	}

	public void render(Screen screen) {
		screen.drawQuad(0xff0000, Game.WIDTH - 41, (int) (114 + owner.getMaxEnergy() - owner.getEnergy() / 2), 19, (int) owner.getEnergy() + 1);

		screen.renderSprite(Art.energyBar, Game.WIDTH - 64, 100, 0);
		screen.renderSprite(Art.energyBar, Game.WIDTH - 64, 100 + Art.energyBar.getSize(), 2);
	}
}