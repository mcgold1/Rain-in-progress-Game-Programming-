package com.mcgold1.rain.entity.projectile;

import com.mcgold1.rain.entity.spawner.ParticleSpawner;
import com.mcgold1.rain.entity.spawner.Spawner;
import com.mcgold1.rain.graphics.Screen;
import com.mcgold1.rain.graphics.Sprite;

public class WizardProjectile extends Projectile{
	
	public static final int RATE = 5; //Higher is slower

	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 20;
		sprite = Sprite.projectile_wizard;
		
		nx = Math.cos(angle)*speed;
		ny = Math.sin(angle)*speed;
	}
	
	public void update(){
		if (level.tilecollision((int)(x+nx),(int)(y+ ny), 7, 5, 4)){
			level.add(new ParticleSpawner((int)x, (int)y, 77, 50, level));
			remove();
		}
		else move();
		
	}
	
	protected void move()
	{
		x += nx;
		y += ny;
		if(distance() > range) remove();
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt((xOrigin -x)*(xOrigin - x) + (yOrigin -y)*(yOrigin - y));
		return dist;
		
	}

	public void render(Screen screen){
		
		screen.renderProjectile((int)x - 12, (int)y - 2, this);
		
	}

		
}
