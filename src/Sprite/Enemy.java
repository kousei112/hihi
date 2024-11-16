package Sprite;

import java.awt.Color;
import java.awt.Graphics;
public class Enemy {
    private int x, y;
    private int width = 50;
    private int height = 150;
    private int speed = 1;
    private int moveDirection = 1;
    private boolean movingLeft = true;
    private int health;

    private int leftBound;
    private int rightBound;

    protected boolean isAttacking;
    protected boolean isCollide;

    public Enemy(int startX, int startY, int leftBound, int rightBound, int health) {
        this.x = startX;
        this.y= startY;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.health = health;
    }

    public void updatePosition() {
        x += speed * moveDirection;

        if (x <= leftBound || x + width >= rightBound) {
            moveDirection *= -1;
        }
    }

    public void move() {
        if (movingLeft) {
            x -= speed; // Move left
            if (x <= 0) { // Check left boundary
                movingLeft = false;
            }
        } else {
            x += speed; // Move right
            if (x >= 400 - width) { // Check right boundary, assuming window width is 400
                movingLeft = true;
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth() {
        health = health - (int) (health * 0.03);
    }
}

