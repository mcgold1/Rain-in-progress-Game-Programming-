package com.mcgold1.rain.entity.spawner;

import java.util.ArrayList;
import java.util.List;

import com.mcgold1.rain.entity.Entity;
import com.mcgold1.rain.entity.particle.Particle;
import com.mcgold1.rain.level.Level;

public class Spawner extends Entity{
	
	public enum Type {
		MOB, PARTICLE;
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level){
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}

}
