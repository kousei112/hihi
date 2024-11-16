package Sprite;

import Utils.GameConstants;
import Utils.BossConstants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Sprite implements GameConstants, BossConstants {
    public int x;
    public int y;
    protected int w;
    protected int h;
    protected int speed;
    protected BufferedImage[] animationFrames; // Các khung hình động
    protected int imageIndex;
    public int currentMove;
    protected int force;
    protected boolean isJump;
    protected boolean isCollide;
    protected int health;
    protected boolean isAttacking;
    protected int damageDealth;
    protected int damageTaken;

    public Sprite() {
        this(0, 0, 200, 200, 5, 100);
    }

    public Sprite(int x, int y, int w, int h, int speed, int health) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        this.health = health;
        this.isJump = false;
        this.isCollide = false;
        this.isAttacking = false;
    }

    // Getter và setter cho các thuộc tính
    // ...

    public void setSize(int width, int height) {
        this.w = width;
        this.h = height;
    }

    public void startAttack() {
        isAttacking = true;
    }

    public void endAttack() {
        isAttacking = false;
    }

    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y =y;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isAttacking() {
		return isAttacking;
	}

	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}

	public boolean isCollide() {
		return isCollide;
	}

	public void setCollide(boolean isCollide) {
		this.isCollide = isCollide;
	}   

    public int getSpeed() {
		return speed;
	}
	

	public int getCurrentMove() {
		return currentMove;
	}


	public void setCurrentMove(int currentMove) {
		this.currentMove = currentMove;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void move() {
		x = x + speed;
	}

    public void jump() {
        if (!isJump) {
            isJump = true;
            // Logic để xử lý nhảy
        }
    }

    public void land() {
        isJump = false;
        // Logic để xử lý việc hạ cánh
    }

    public boolean isColliding(Sprite other) {
        return this.x < other.x + other.w &&
                this.x + this.w > other.x &&
                this.y < other.y + other.h &&
                this.y + this.h > other.y;
    }

    public void checkAttackCollision(Sprite target) {
        if (isAttacking && isColliding(target)) {
            target.takeDamage(damageDealth);
        }
    }
    
    // Phương thức trừu tượng để trả về hình ảnh dựa trên `frameIndex`
    public abstract BufferedImage defaultImage(int frameIndex);
    public abstract BufferedImage defaultImage(Graphics g); 
    // Phương thức di chuyển cơ bản

    public void move(int dx, int dy) {
        x += dx * speed;
        y += dy * speed;
    }

    // Phương thức vẽ nhân vật, sử dụng `frameIndex` để kiểm soát khung hình
    public void print(Graphics pen, int frameIndex) {
        pen.drawImage(defaultImage(frameIndex), x, y, w, h, null);
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health <= 0) {
            die();
        }
    }

    protected void die() {
        // Logic xử lý khi nhân vật chết
    }
}
