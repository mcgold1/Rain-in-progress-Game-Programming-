package com.mcgold1.rain.entity.particle;

import com.mcgold1.rain.entity.Entity;
import com.mcgold1.rain.graphics.Screen;
import com.mcgold1.rain.graphics.Sprite;

public class Particle extends Entity{
	
	private int life;
	private int time = 0;
	
	protected double xa, ya, za;
	protected double xx, yy, zz;
	
	public Particle(int x, int y, int life){
		sprite = Sprite.particle_normal;
		this.x = x;
		this.y = y;
		xx =  x;
		yy = y;
		zz = random.nextFloat() + 2;
		this.life = life+random.nextInt(20) - 10;
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
	}
	
	
	public void update(){
		time++;
		if (time >= 7400) time =0;
		if (time > life) remove();
		za -= 0.1;
		
		if (zz < 0){
			zz=0;
			za *= -0.7;
			xa *= 0.7;
			ya *= 0.7;
		}
		move((xx + xa), (yy + ya) + (zz + za));

		
	}
	
	private void move(double x, double y) {
		if (collision(x, y)){
			this.xa *= -0.7;
			this.ya *= -0.7;
			this.za *= -0.7;
		}
		xx += xa;
		yy += ya;
		zz += za;
	}
	
	public boolean collision(double x, double y){
		boolean solid = false;
		for (int c = 0; c < 4; c++){
			double xt = (x - c % 2 * 16 ) / 16;
			double yt = (y - c / 2 * 16) / 16;
			int ix = (int)Math.ceil(xt);
			int iy = (int)Math.ceil(yt);
			if (c%2 == 0) ix = (int)Math.floor(xt);
			if (c/2 == 0) iy = (int)Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}


	public void render (Screen screen){
		screen.renderSprite((int)xx - 1, (int)yy - (int) zz -1, sprite, true);
	}

}
