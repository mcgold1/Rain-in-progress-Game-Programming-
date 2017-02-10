package com.mcgold1.rain.level.tile;

import com.mcgold1.rain.graphics.Screen;
import com.mcgold1.rain.graphics.Sprite;

public class VoidTile extends Tile{
	
	public VoidTile(Sprite sprite){
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}

}
