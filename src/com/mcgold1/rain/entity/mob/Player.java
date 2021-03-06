package com.mcgold1.rain.entity.mob;

import com.mcgold1.rain.Game;
import com.mcgold1.rain.entity.projectile.Projectile;
import com.mcgold1.rain.entity.projectile.WizardProjectile;
import com.mcgold1.rain.graphics.AnimatedSprite;
import com.mcgold1.rain.graphics.Screen;
import com.mcgold1.rain.graphics.Sprite;
import com.mcgold1.rain.graphics.SpriteSheet;
import com.mcgold1.rain.input.Keyboard;
import com.mcgold1.rain.input.Mouse;

public class Player extends Mob {
	
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking;
	private int fireRate = 0;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;
	
	public Player(Keyboard input){
		this.input = input;

	}
	
	public Player(int x, int y, Keyboard input){	
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_forward;
		fireRate = WizardProjectile.RATE;
		
	}
	
	public void update(){
		if(walking)animSprite.update();
		else animSprite.setFrame(0);
		if (fireRate > 0) fireRate --;
		int xa = 0, ya = 0;
		if (anim < 7500) anim++; 
		else anim = 0;
		if(input.up){ 
			ya --;
			animSprite = up;
		}
		if(input.down) {
			ya ++;
			animSprite = down;
		}
		if(input.left) {
			xa --;
			animSprite = left;
		}
		if(input.right) {
			xa ++;
			animSprite = right;
		}
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else walking = false;
		
		clear();
		updateShooting();
	}
	
	private void clear(){
		for (int i =0; i< level.getProjectiles().size(); i++){
			Projectile p = level.getProjectiles().get(i);
			if(p.isRemoved()) level.getProjectiles().remove(i);
		}
	}
	
	private void updateShooting() {
		
		if (Mouse.getB() == 1 && fireRate <= 0){
			double dx = Mouse.getX() - (Game.width*Game.scale)/2;
			double dy = Mouse.getY() - (Game.height*Game.scale)/2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = WizardProjectile.RATE;
		}
		
	}

	public void render(Screen screen){
		int filp = 0;
		/*if(dir == 0){
			sprite = Sprite.player_forward;
			if(walking){
				if (anim % 40 > 10 && anim % 40 <=20){
					sprite = Sprite.player_forward_1;
				}
				if (anim % 40 > 30 && anim % 40 <=40){
					sprite = Sprite.player_forward_2;
				}
					
			}
		}
		if(dir == 1 || dir == 3){
			sprite = Sprite.player_side;
			if(walking){
				if (anim % 40 > 10 && anim % 40 <=20){
					sprite = Sprite.player_side_1;
				}
				if (anim % 40 > 30 && anim % 40 <=40){
					sprite = Sprite.player_side_2;
				}
					
			}
		}
		if(dir == 2){
			sprite = Sprite.player_back;
			 if(walking){
				if (anim % 40 > 10 && anim % 40 <=20){
					sprite = Sprite.player_back_1;
				}
				if (anim % 40 > 30 && anim % 40 <=40){
					sprite = Sprite.player_back_2;
				}
			 }
					
		}*/
		sprite = animSprite.getSprite();
		screen.renderPlayer(x-16, y-16, sprite, filp);

	}

}

