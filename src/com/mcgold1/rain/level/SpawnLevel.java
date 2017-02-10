package com.mcgold1.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mcgold1.rain.level.tile.Tile;

public class SpawnLevel extends Level{
	

	public SpawnLevel(String path) {
		super(path);
	}
	
	protected void loadLevel(String path){
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			
			tiles = new int[w*h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Grass = 0xFF00 0xFF00FF00
	// Flower = 0xFFFF00 0xFFFFFF00
	// Rock = 0x7F7F00 0xFF7F7F00
	protected void generateLevel(){
			System.out.println(tiles[0]);
		
	}
	
	// Grass = 0xFF00FF00
	// spawnpoint = 0xFFFFFF00
	// wall1 = 0xFF404040
	// wall2 = 0xFF000000
	// floor = 0x7C400000
}
