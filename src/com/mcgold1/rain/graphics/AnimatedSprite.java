package com.mcgold1.rain.graphics;

public class AnimatedSprite extends Sprite {

	private int frame = 0;
	private Sprite sprite;
	private int rate = 7;
	private int length = -1;
	private int time = 0;
	
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length){
		super(sheet, width, height);
		this.length = length;
		sprite = sheet.getSprites()[0];
		if (length > sheet.getSprites().length) System.err.println("Error! Inputed length of animation is too long!");
	}
	
	public void update(){
		time++;
		if (time % rate == 0){
			frame ++;
			if (frame >= length) frame = 0;
			sprite = sheet.getSprites()[frame];
		}
		System.out.println(sprite + ", Frame: " + frame);
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public void setFrameRate(int frames){
		rate = frames;
	}
	
	public void setFrame(int index){
		if (index > sheet.getSprites().length -1){
			System.err.println("Index out of bounds in " + this);
			return;
		}
		sprite = sheet.getSprites()[index];
	}

}
