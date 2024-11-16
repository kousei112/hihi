package Test;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Time;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import Sprite.Naruto;
import Sprite.Enemy;
import Sprite.Power;

import Utils.BossConstants;
import Utils.GameConstants;



public class Board extends JPanel implements GameConstants, BossConstants{
    BufferedImage imageBg;
    private Naruto naruto;
    private Enemy enemy;
    private Timer timer;
    private Power narutoPower;
    private Power enemyPower;
    public boolean isGameOver;

    public Board() throws IOException {
        loadPower();
    }

    private void loadPower() {
        narutoPower = new Power(50, "Naruto".toUpperCase());
        enemyPower = new Power(GWIDTH/2 + 150, "Enemy".toUpperCase());
    }

    private void paintPower(Graphics pen) {
        narutoPower.printBox(pen);
        enemyPower.printBox(pen);
    }

    public void collision() {
        if (isCollide()) {
            if (naruto.isAttacking()) {
                
            }
        }
    }

    private boolean isCollide() {
        int xDistance = Math.abs(naruto.getX() - enemy.getX());
        int yDistance = Math.abs(naruto.getY() - enemy.getY());
        int maxH = Math.abs(naruto.getH() - enemy.getHeight());
        int maxW = Math.abs(naruto.getW() - enemy.getWidth());
        return xDistance <= maxW - 10 && yDistance <= maxH;
    }
}
