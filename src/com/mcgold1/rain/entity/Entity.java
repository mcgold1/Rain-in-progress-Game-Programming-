package com.mcgold1.rain.entity;

import java.util.Random;

import com.mcgold1.rain.graphics.Screen;
import com.mcgold1.rain.graphics.Sprite;
import com.mcgold1.rain.level.Level;

public abstract class Entity {

		public int x, y;
		private boolean removed = false;
		protected Level level;
		protected final Random random = new Random();
		protected Sprite sprite;
		
		public void update(){
		}
		
		public void render (Screen screen){
		}
		
		public void remove(){
			//Remove from level
			removed = true;
		}
		
		public boolean isRemoved(){
			return removed;
		}
		
		public void init(Level l){
			level = l;
		}
}
