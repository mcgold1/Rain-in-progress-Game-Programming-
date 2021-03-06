package com.mcgold1.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import com.mcgold1.rain.entity.mob.Player;
import com.mcgold1.rain.graphics.Screen;
import com.mcgold1.rain.graphics.Sprite;
import com.mcgold1.rain.graphics.SpriteSheet;
import com.mcgold1.rain.input.Keyboard;
import com.mcgold1.rain.input.Mouse;
import com.mcgold1.rain.level.Level;
import com.mcgold1.rain.level.RandomLevel;
import com.mcgold1.rain.level.SpawnLevel;
import com.mcgold1.rain.level.TileCoordinate;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	public static String title = "Rain";
	
	private Thread gameThread;
	private JFrame frame;
	private boolean running = false;
	private Keyboard key;
	private Level level;
	private Player player;
	
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game(){
		Dimension size = new Dimension(width*scale, height*scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate(20, 64);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		player.init(level);
		
		addKeyListener(key);
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	public synchronized void start() {
		running = true;
		gameThread = new Thread(this, "Display");
		gameThread.start();
	}
	
	public synchronized void stop(){
		running = false;
		try{
			gameThread.join();
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running){
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while (delta >= 1){
				update();
				updates ++;
				delta --;
			}
			render();
			frames ++;
			
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + "  |   " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	
	public void update(){
		key.update();
		player.update();
		level.update();

	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if (bs == null){
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		int xScroll = player.x - screen.width /2;
		int yScroll = player.y - screen.height /2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		//screen.renderSheet(40, 40, SpriteSheet.player_left, false);
	
			
		for (int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Rain");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}

}
