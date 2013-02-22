package ip.h2n0.main;

import ip.h2n0.main.GFX.SpriteManager;
import ip.h2n0.main.sound.Sound;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snake extends JPanel implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;

    private Graphics dbg;
    private Image dbImage;

    public JFrame frame;
    private final int WIDTH = 480;
    private final int HEIGHT = 480;
    public final String name = "Snake : h2n0 edition";
    public final Dimension dim = new Dimension(WIDTH, HEIGHT);
    private boolean gameOver;
    private int difficulty = 150;

    private Stack<Segment> snakeBody = new Stack<Segment>();
    boolean keyPressed = false;
    public static int dir = 1;
    public static int colour = 0;
    private final int tileSize = 20;
    private int headX = 5 * tileSize;
    private int headY = 5 * tileSize;

    private int foodX = 7 * tileSize;
    private int foodY = 10 * tileSize;

    public int[][] board;
    public int score;
    public int hiScore;
    public int highest;

    public boolean menu = true;

    public double mult = 1.0D;
    private int mu = 1;

    public Snake() {
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setMaximumSize(dim);

        frame = new JFrame(name);
        frame.add(this);
        frame.setSize(dim);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3); // short hand for
                                           // JFrame.EXIT_ON_CLOSE
        frame.setFocusable(true);

        frame.setLocationRelativeTo(null);
        frame.addKeyListener(this);

        board = new int[getWidth() / tileSize][getHeight() / tileSize];

    }

    public void start() {
        if (!SpriteManager.initalized) {
            SpriteManager.initialize();
        }
        if (!menu) {
            reset();

            newFood();
            snakeBody.clear();
            snakeBody.add(new Segment(headX, headY));
            snakeBody.add(1, new Segment(headX, headY));

            new Thread(this, "Snake_Main").start();
        }
    }

    public int getCenterX(String msg) {
        int x = (getWidth() / 2) - msg.length() * 5;
        return x;
    }

    public int getCenterY() {
        int y = getHeight() / 2;
        return y;
    }

    public void reset() {
        Random r = new Random();
        gameOver = false;
        score = 0;
        dir = 1;
        mult = 1D;
        difficulty = 150;
        headX = r.nextInt(20) * tileSize;
        headY = r.nextInt(5) * tileSize;
    }

    @Override
    public void paint(Graphics g) {

        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();

        render(dbg);
        dbg.drawString("Score : " + score, 0, 12);
        dbg.drawString("Hi-score : " + hiScore, 70, 12);
        if (menu) {
            String Snake = "Snake!";
            String how = "W-A-S-D to play";
            String con = "Press Enter / Space to play";
            String help = "Colour changing idea came from Owen L & Cameron M";
            dbg.setColor(Color.black);
            dbg.fillRect(0, 0, getWidth(), getHeight());
            dbg.setColor(Color.red);
            dbg.setFont(new Font("ArcadePix", Font.PLAIN, 50));
            dbg.drawString(Snake, getCenterX(Snake) - 60, 60);
            dbg.setColor(Color.white);
            dbg.setFont(new Font("ArcadePix", Font.PLAIN, 30));
            dbg.drawString(con, getCenterX(con) - 98, getCenterY());
            dbg.drawString(how, getCenterX(how) - 60, getCenterY() + 50);
            dbg.setFont(new Font("ArcadePix", Font.PLAIN, 12));
            dbg.drawString(help, getCenterX(help) + 45, getCenterY() + 120);
        }
        if (gameOver) {
            String made = "Made by : Elliot Lee-Cerrino";
            String over = "Game Over! Score : " + score;
            String re = "Press Space / Enter to restart";
            dbg.setFont(new Font("ArcadePix", Font.PLAIN, 16));
            dbg.fillRect(0, 0, getWidth(), getHeight());
            dbg.setColor(Color.RED);
            dbg.drawString(made, getCenterX(made) + 5, getCenterY() + 25);
            dbg.drawString(over, getCenterX(made), getCenterY());
            dbg.drawString(re, getCenterX(re), getCenterY() + 120);
        }
        g.drawImage(dbImage, 0, 0, null);
        repaint();
    }

    // for sprite redering
    public void render(Graphics g) {
        renderBG(g);
        if (snakeBody.isEmpty()) {
            return;
        }
        try {
            for (int i = 0; i < snakeBody.size(); i++) {
                snakeBody.get(i).render(g);
            }
        } catch (Exception e) {
            render(g);
        }

        g.drawImage(SpriteManager.Food.sprite, foodX, foodY, null);
        // g.setColor(Color.BLUE);
        // g.fillRect(foodX, foodY, tileSize, tileSize);
    }

    public void renderBG(Graphics g) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                int id = board[x][y];
                if (id == 0) {
                    g.drawImage(SpriteManager.Floor1.sprite, x * tileSize, y * tileSize, null);
                }
            }
        }
    }

    public void tick() {
        keyPressed = false;
        makeMove();
        checkCollision();
    }

    public void checkCollision() {
        for (int i = 1; i < snakeBody.size(); i++) {
            Segment s = snakeBody.get(i);
            if (s.xPos == headX && s.yPos == headY) {
                gameOver = true;
            }
        }
        if (headX < 0 || headY < 0 || headX > getWidth() - 20 || headY > getHeight() - 20) {
            gameOver = true;
        }
    }

    public int hiScore(int score) {
        if (score > hiScore) {
            hiScore = score;
        }
        return hiScore;
    }

    public void makeMove() {
        switch (dir) {
        case 0:
            headY -= tileSize;
            break;
        case 1:
            headY += tileSize;
            break;
        case 2:
            headX -= tileSize;
            break;
        case 3:
            headX += tileSize;
            break;
        }

        snakeBody.add(0, new Segment(headX, headY));

        if (headX == foodX & headY == foodY) {
            difficulty--;
            addScore(10);
            newFood();
            playSound(mu);
            // plusColour();
        } else {
            snakeBody.pop();
        }
    }
    
    public void playSound(int times){
        switch(times){
        case 1:
            Sound.eath.play();
            this.mu++;
            break;
        case 2:
            Sound.eatl.play();
            this.mu = 1;
            break;
        
        
        }
    }

    public void addScore(int score) {
        score = (int) (score * mult);
        mult += 0.01;
        this.score += score;
    }

    private void plusColour() {
        if (colour >= 3) {
            colour = 0;
        } else {
            colour++;
        }
    }

    public void newFood() {
        Random r = new Random();
        boolean broken = true;
        while (broken) {
            broken = false;
            int x = (1 + r.nextInt((getWidth() / tileSize) - 2)) * tileSize;
            int y = (1 + r.nextInt((getHeight() / tileSize) - 2)) * tileSize;

            for (int i = 0; i < snakeBody.size(); i++) {
                Segment s = snakeBody.get(i);

                if (s.xPos == x && s.yPos == y) {
                    broken = true;
                    break;
                }
            }
            if (!broken) {
                foodX = x;
                foodY = y;
            }
        }
    }

    public void run() {
        while (!gameOver) {
            tick();

            try {
                Thread.sleep(difficulty);
                plusColour();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (gameOver) {
            hiScore(score);
        }
        start();
    }

    public static void main(String[] args) {
        Snake s = new Snake();
        s.start();
    }

    public void keyPressed(KeyEvent e) {

        if (keyPressed) {
            return;
        }
        int code = e.getKeyCode();// up = 0 , down = 1 , left = 2 , right = 3
        if ((code == KeyEvent.VK_UP || code == KeyEvent.VK_W) && dir != 1) {
            keyPressed = true;
            dir = 0;
        }
        if ((code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) && dir != 0) {
            keyPressed = true;
            dir = 1;
        }
        if ((code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) && dir != 3) {
            keyPressed = true;
            dir = 2;
        }
        if ((code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) && dir != 2) {
            keyPressed = true;
            dir = 3;
        }
        if ((code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE)) {
            if (menu) {
                menu = false;
                Sound.eath.play();
                start();
            } else if(!menu && gameOver){
                reset();
                gameOver = false;
            }
        }
    }

    public void keyReleased(KeyEvent arg0) {

    }

    public void keyTyped(KeyEvent arg0) {

    }
}