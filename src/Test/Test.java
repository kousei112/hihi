package Test;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

import Sprite.Naruto;
import Sprite.Enemy;

public class Test extends Canvas implements Runnable {
    private Naruto naruto;
    private Enemy enemy;
    private Thread thread;
    private boolean running = false;

    public Test(Naruto naruto, Enemy enemy) {
        this.naruto = naruto;
        this.enemy = enemy;
        this.setPreferredSize(new Dimension(800, 600));
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            render();
        }
        stop();
    }

    private void tick() {
        naruto.move_towards(enemy.getX());
        enemy.updatePosition();
        if (Math.abs(naruto.getX() - enemy.getX()) <= 10) {
            naruto.attack();
        }
    }
    
    

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, 800, 600);

        // Render Naruto and Enemy
        naruto.defaultImage(g);
        enemy.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Naruto naruto = new Naruto(100, 400);
        Enemy enemy = new Enemy(700, 400, 0, 800, 100);
        Test test = new Test(naruto, enemy);

        JFrame frame = new JFrame("Naruto vs Enemy");
        frame.add(test);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        test.start();
    }
}
