package Sprite;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Naruto extends Sprite {
    private BufferedImage spriteSheet;
    private BufferedImage[] stanceImages = new BufferedImage[4];
    private BufferedImage[] guardImages = new BufferedImage[2];
    private BufferedImage[] jumpImages = new BufferedImage[3];
    private BufferedImage[] dashImages = new BufferedImage[3];
    private BufferedImage[] runImages = new BufferedImage[6];
    private BufferedImage[] defaultAttackingImages = new BufferedImage[3];
    private BufferedImage[] strongAttackingImages = new BufferedImage[6];
    private BufferedImage[] dotonNoJutsuImages = new BufferedImage[4];
    private BufferedImage[] stoneImages = new BufferedImage[6];

    private int jumpHeight = 50;
    private int initialY;
    private int moveForward = 10;
    private boolean holdFrame = false;
    // private int holdX, holdY;
    private boolean inPhase2 = false;
    private long phase2StartTime;
    private int dotonIndex = 0;
    private int stoneIndex = 0;

    private int frameDelay = 4;
    private int frameCounter = 0;

    // private int defaultAttackingDamage;
    // private int strongAttackingDamage;
    // private int dotonNoJutsuDamage;

    private boolean isFaceingRight = true;

    public Naruto(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.initialY = y;
        this.health = MAX_HEALTH;
        this.speed = 5;
        // this.defaultAttackingDamage = 10;
        // this.strongAttackingDamage = 20;
        // this.dotonNoJutsuDamage = 30;
        loadSpriteSheet();
        loadStanceImages();
        loadGuardImages();
        loadJumpImages();
        loadDashImages();
        loadRunImages();
        loadDefaultAttacking();
        loadStrongAttackingImages();
        loadDotonNoJutsuImages();
        loadStoneImages();
    }


    public void startAttack() {
        isAttacking = true;
    }

    public void endAttack() {
        isAttacking = false;
        currentMove = STANCING;
    }

    public void performAction(int action) {
        this.currentMove = action;
        this.imageIndex = 0;
        this.frameCounter = 0;
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(new File("F:/tyrt/Documents/LapTrinhGame/src/Sprite/Naruto_Java.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStanceImages() {
        stanceImages[0] = spriteSheet.getSubimage(3, 920, 98, 142);
        stanceImages[1] = spriteSheet.getSubimage(102, 917, 84, 145);
        stanceImages[2] = spriteSheet.getSubimage(182, 915, 81, 150);
        stanceImages[3] = spriteSheet.getSubimage(263, 911, 85, 151);
    }

    private void loadGuardImages() {
        guardImages[0] = spriteSheet.getSubimage(419, 1319, 93, 141);
        guardImages[1] = spriteSheet.getSubimage(509, 1316, 85, 144);
    }

    private void loadJumpImages() {
        jumpImages[0] = spriteSheet.getSubimage(2, 1323, 105, 131);
        jumpImages[1] = spriteSheet.getSubimage(123, 1313, 95, 147);
        jumpImages[2] = spriteSheet.getSubimage(216, 1359, 111, 95);
    }

    private void loadDashImages() {
        dashImages[0] = spriteSheet.getSubimage(485, 912, 136, 128);
        dashImages[1] = spriteSheet.getSubimage(626, 918, 121, 107);
        dashImages[2] = spriteSheet.getSubimage(761, 924, 130, 95);
    }

    private void loadRunImages() {
        runImages[0] = spriteSheet.getSubimage(30, 1115, 135, 132);
        runImages[1] = spriteSheet.getSubimage(191, 1116, 139, 120);
        runImages[2] = spriteSheet.getSubimage(344, 1110, 117, 128);
        runImages[3] = spriteSheet.getSubimage(470, 1116, 135, 128);
        runImages[4] = spriteSheet.getSubimage(636, 1113, 141, 125);
        runImages[5] = spriteSheet.getSubimage(830, 1119, 121, 119);
    }

    private void loadDefaultAttacking() {
        defaultAttackingImages[0] = spriteSheet.getSubimage(20, 1913, 100, 138);
        defaultAttackingImages[1] = spriteSheet.getSubimage(123, 1913, 149, 126);
        defaultAttackingImages[2] = spriteSheet.getSubimage(278, 1929, 141, 120);
    }

    private void loadStrongAttackingImages() {
        strongAttackingImages[0] = spriteSheet.getSubimage(6, 4121, 150, 150);
        strongAttackingImages[1] = spriteSheet.getSubimage(173, 4104, 142, 161);
        strongAttackingImages[2] = spriteSheet.getSubimage(312, 4140, 120, 129);
        strongAttackingImages[3] = spriteSheet.getSubimage(443, 4091, 157, 177);
        strongAttackingImages[4] = spriteSheet.getSubimage(611, 4089, 153, 177);
        strongAttackingImages[5] = spriteSheet.getSubimage(759, 4128, 128, 141);
    }

    private void loadDotonNoJutsuImages() {
        dotonNoJutsuImages[0] = spriteSheet.getSubimage(20, 4646, 91, 130);
        dotonNoJutsuImages[1] = spriteSheet.getSubimage(113, 4643, 99, 132);
        dotonNoJutsuImages[2] = spriteSheet.getSubimage(219, 4671, 119, 104);
        dotonNoJutsuImages[3] = spriteSheet.getSubimage(336, 4677, 114, 99);
    }

    private void loadStoneImages() {
        stoneImages[0] = spriteSheet.getSubimage(500, 4748, 84, 42);
        stoneImages[1] = spriteSheet.getSubimage(587, 4709, 81, 58);
        stoneImages[2] = spriteSheet.getSubimage(684, 4682, 84, 88);
        stoneImages[3] = spriteSheet.getSubimage(782, 4649, 88, 117);
        stoneImages[4] = spriteSheet.getSubimage(890, 4598, 96, 166);
        stoneImages[5] = spriteSheet.getSubimage(1008, 4550, 89, 217);
    }

    public void move_towards(int targetX) {
        if (this.x < targetX) {
            this.x += this.speed; // Di chuyển về phía bên phải
            if (Math.random() < 0.5) {
                currentMove = DASHING; 
            } else {
                currentMove = RUNNING;
            }
        } else if (this.x > targetX) {
            this.x -= this.speed; // Di chuyển về phía bên trái
            if (Math.random() < 0.5) {
                currentMove = DASHING; 
            } else {
                currentMove = RUNNING;
            }
        } else {
            currentMove = STANCING; // Khi không di chuyển, giữ nguyên trạng thái STANCING
        }
    }
    
    

    public void attack() {
        int randomAttack = (int) (Math.random() * 3);
        switch (randomAttack) {
            case 0:
                performAction(ATTACKING1);
                break;
        
            case 1:
                performAction(ATTACKING2);
                break;
            case 2:
                performAction(ATTACKING3);
                break;
        }
    }

    public void resetAttack() {
        this.isAttacking = false;
        this.currentMove = STANCING;
        this.imageIndex = 0;
        this.frameCounter = 0;
    }
    

    @Override
    public BufferedImage defaultImage(int frameIndex) {
        // Chọn hình ảnh dựa trên trạng thái
        BufferedImage img;

        switch (currentMove) {
            case RUNNING:
                img = runningImage();
                break;
            case JUMPING:
                img = jumpingImage();
                break;
            case GUARDING:
                img = guardingImage();
                break;
            case DASHING:
                img = dashingImage();
                break;
            case ATTACKING1:
                img = defaultAttackingImage();
                break;
            case ATTACKING2:
                img = strongAttackingImage();
                break;
            case ATTACKING3:
                img = dotonNoJutsuImage();
                break;
            default:
                img = stancingImage();
                break;
        }

        if (!isFaceingRight) {
            img = flipImage(img);
        }

        return img;
    }

    private boolean shouldAdvanceFrame() {
        frameCounter++;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            return true;
        }
        return false;
    }

    @Override
    public BufferedImage defaultImage(Graphics g) {
        BufferedImage img = defaultImage(0); // Gọi phương thức defaultImage(int frameIndex)
        g.drawImage(img, x, y, null); // Vẽ hình ảnh lên Graphics
        return img;
    }

    public BufferedImage runningImage() {
        if (runImages != null && runImages.length > 0) { // Kiểm tra mảng khác null và không rỗng
            if (shouldAdvanceFrame()) {
                imageIndex = (imageIndex + 1) % runImages.length; // Giới hạn chỉ số trong phạm vi mảng
                if (imageIndex == 0) { // Nếu đã hoàn thành một chu kỳ ảnh chạy
                    currentMove = STANCING;
                }
            }
            return runImages[imageIndex];
        }
        return null; // Trả về null nếu runImages chưa được tải hoặc rỗng
    }
    
    public BufferedImage jumpingImage() {
        // Kiểm tra nếu mảng jumpImages hợp lệ và imageIndex nằm trong phạm vi của mảng
        if (jumpImages == null || jumpImages.length == 0 || imageIndex >= jumpImages.length) {
            return null;
        }
    
        BufferedImage img = jumpImages[imageIndex];
        
        // Thiết lập vị trí y ban đầu khi bắt đầu nhảy
        if (imageIndex == 0) {
            initialY = y;
        } else if (imageIndex == 1) {
            y -= jumpHeight;
            x += moveForward;
        } else if (imageIndex == 2) {
            y = initialY;
            x += moveForward;
        }
    
        // Tiến đến khung hình tiếp theo nếu cần
        if (shouldAdvanceFrame()) {
            imageIndex++;
            if (imageIndex >= jumpImages.length) { // Khi đã hoàn thành toàn bộ hình nhảy
                imageIndex = 0;
                currentMove = STANCING;
            }
        }
        return img;
    }
    

    public BufferedImage guardingImage() {
        // Kiểm tra tính hợp lệ của mảng guardImages và chỉ số imageIndex
        if (guardImages == null || guardImages.length == 0 || imageIndex >= guardImages.length) {
            return null;
        }
    
        BufferedImage img = guardImages[imageIndex];
    
        // Tiến đến khung hình tiếp theo nếu cần
        if (shouldAdvanceFrame()) {
            imageIndex++;
            if (imageIndex >= guardImages.length) { // Khi đã hoàn thành toàn bộ hình bảo vệ
                imageIndex = 0;
                currentMove = STANCING;
            }
        }
        return img;
    }
    
    
    public BufferedImage dashingImage() {
        if (shouldAdvanceFrame()) {
            imageIndex++;
            if (imageIndex >= dashImages.length) {
                imageIndex = 0;
                currentMove = STANCING;
            }
        }
        x += moveForward;
        return dashImages[imageIndex];
    }
    
    
    public BufferedImage stancingImage() {
        if (shouldAdvanceFrame()) {
            imageIndex = (imageIndex + 1) % stanceImages.length;
        }
        return stanceImages[imageIndex];
    }

    public BufferedImage defaultAttackingImage() {
        if (shouldAdvanceFrame()) {
            imageIndex++;
            if (imageIndex >= defaultAttackingImages.length) {
                imageIndex = 0;
                currentMove = STANCING;
            }
        }
        return defaultAttackingImages[imageIndex];
    }
    
    

    public BufferedImage strongAttackingImage() {
        if (shouldAdvanceFrame()) {
            imageIndex++;
            if (imageIndex >= strongAttackingImages.length) {
                imageIndex = 0;
                currentMove = STANCING;
            }
        }
        // x += moveForward;
        return strongAttackingImages[imageIndex];
    }
    
    

    public boolean isDotonFinished() {
        return stoneIndex >= stoneImages.length;
    }

    public BufferedImage dotonNoJutsuImage() {
        BufferedImage img;
    
        // Giai đoạn 1: Hiển thị hình ảnh nhân vật từ mảng dotonNoJutsuImages
        if (!inPhase2) {
            img = dotonNoJutsuImages[dotonIndex];
    

            if (dotonIndex == dotonNoJutsuImages.length - 1) {
                if (!holdFrame) {
                    holdFrame = true;
                    phase2StartTime = System.currentTimeMillis(); 
                }
                if (System.currentTimeMillis() - phase2StartTime >= 1000) {
                    holdFrame = false;
                    inPhase2 = true; 
                    stoneIndex = 0; 
                }
            } else {
                if (shouldAdvanceFrame()) {
                    dotonIndex++;
                }
            }
        } else {
            // Giai đoạn 2: Hiển thị hình ảnh đá từ mảng stoneImages
    
            img = new BufferedImage(dotonNoJutsuImages[dotonNoJutsuImages.length - 1].getWidth() + 50 + stoneImages[0].getWidth(),
                                    Math.max(dotonNoJutsuImages[dotonNoJutsuImages.length - 1].getHeight(), stoneImages[0].getHeight()),
                                    BufferedImage.TYPE_INT_ARGB);
            
            Graphics g = img.getGraphics();
    
            g.drawImage(dotonNoJutsuImages[dotonNoJutsuImages.length - 1], 0, 0, null);
    
            g.drawImage(stoneImages[stoneIndex], dotonNoJutsuImages[dotonNoJutsuImages.length - 1].getWidth() + 50, 0, null);
            g.dispose();
    
            if (shouldAdvanceFrame()) {
                stoneIndex++;
                if (stoneIndex >= stoneImages.length) {
                    stoneIndex = 0;
                    inPhase2 = false;
                    currentMove = STANCING; 
                    dotonIndex = 0; 
                }
            }
        }
        return img;
    }
    
    private BufferedImage flipImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage flippedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = flippedImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, width, 0, 0, height, null);
        g2d.dispose();
        return flippedImage;
    }

    public void setFacingRight(boolean faceRight) {
        if (this.isFaceingRight != faceRight) {
            isFaceingRight = faceRight;
        }
    }
}