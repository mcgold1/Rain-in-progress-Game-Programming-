package com.mcgold1.rain.entity.projectile;

import java.util.Random;

import com.mcgold1.rain.entity.Entity;
import com.mcgold1.rain.graphics.Sprite;
import com.mcgold1.rain.level.Level;

public abstract class Projectile extends Entity{
	
	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny;
	protected double distance;
	protected double speed, rateOfFire, range, damage;
	
	protected final Random random = new Random();
	
	public Projectile(int x, int y, double dir){
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	protected void move(){
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public int getSpriteSize(){
		return sprite.SIZE;
	}
	
	

}
