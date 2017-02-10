package com.mcgold1.rain.entity.mob;


import java.util.ArrayList;
import java.util.List;

import com.mcgold1.rain.entity.Entity;
import com.mcgold1.rain.entity.particle.Particle;
import com.mcgold1.rain.entity.projectile.Projectile;
import com.mcgold1.rain.entity.projectile.WizardProjectile;
import com.mcgold1.rain.graphics.Sprite;
import com.mcgold1.rain.level.Level;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
		
	public void move(int xa, int ya){
		if(xa != 0 && ya != 0){
			move(xa, 0);
			move(0, ya);
			return;
		}
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		if(!collision(xa, ya)){
			x += xa;
			y += ya;
		} else {
			//Particle p = new Particle(x, y, 500, 50);
			//level.add(p);
		}
	}
	
	public void update(){
	}
	
	protected void shoot (int x, int y, double dir){
		Projectile p = new WizardProjectile(x, y, dir);
		level.add(p);
	}
	
	public boolean collision(int xa, int ya){
		boolean solid = false;
		for (int c = 0; c < 4; c++){
			int xt = ((x+xa)+c % 2 * 12 -7)/16;
			int yt = ((y+ya)+c / 2 * 12 + 3)/16;		
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		int xt = (x+xa)/16;
		int yt = (y+ya)/16;
		if (level.getTile(xt, yt).solid()) solid = true;
		return solid;
	}
	
	public void init(Level level){
		this.level = level;
	}
	public void render(){
	}

}
