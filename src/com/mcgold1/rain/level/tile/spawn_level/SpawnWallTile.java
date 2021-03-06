package com.mcgold1.rain.level.tile.spawn_level;

import com.mcgold1.rain.graphics.Screen;
import com.mcgold1.rain.graphics.Sprite;
import com.mcgold1.rain.level.tile.Tile;

public class SpawnWallTile extends Tile {

	public SpawnWallTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid(){
		return true;
	}

}
