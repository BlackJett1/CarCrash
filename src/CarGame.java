import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CarGame extends JFrame implements KeyListener {
    private Car playerCar;
    private ArrayList<Obstacle> obstacles;
    private Tree[] trees;
    private OpponentCar[] opponentCars;
    private int score = 0;
    private int speed = 90;
    private boolean gameOver = false;
    private Timer gameTimer;
    private int[][] roadLines = new int[8][2]; // 2D array for road lines

    public CarGame(String title) {
        super(title);
        setBounds(300, 10, 700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setFocusable(true);
        setResizable(false);

        initializeGame();
        initializeRoadLines();
    }

    private void initializeGame() {
        playerCar = new Car(300, 600, 2);
        obstacles = new ArrayList<>();
        trees = new Tree[6];
        opponentCars = new OpponentCar[3];

        // Initialize trees
        for (int i = 0; i < trees.length; i++) {
            trees[i] = new Tree((i % 2 == 0) ? 0 : 600, -i * 200);
            obstacles.add(trees[i]);
        }

        // Initialize opponent cars
        for (int i = 0; i < opponentCars.length; i++) {
            opponentCars[i] = new OpponentCar();
            obstacles.add(opponentCars[i]);
        }
    }

    private void initializeRoadLines() {
        for (int i = 0; i < roadLines.length; i++) {
            roadLines[i][0] = 350; // x position
            roadLines[i][1] = i * 100 - 50; // y position
        }
    }

    public void start() {
        gameTimer = new Timer(100, e -> {
            if (!gameOver) {
                updateGame();
                repaint();
            }
        });
        setVisible(true);
        gameTimer.start();
    }

    private void updateGame() {
        // Move obstacles
        for (Obstacle obstacle : obstacles) {
            obstacle.move();

            // Check collision with player
            if (obstacle.collidesWith(playerCar)) {
                gameOver = true;
                break;
            }
        }

        // Update road lines
        for (int[] line : roadLines) {
            line[1] += 50;
            if (line[1] > 700) {
                line[1] = -50;
            }
        }

        score++;
        if (score % 50 == 0 && speed < 100) {
            speed++;
            gameTimer.setDelay(100 - (speed - 80));
        }
    }

    @Override
    public void paint(Graphics g) {
        // Draw background
        g.setColor(new Color(0x82CD47));
        g.fillRect(0, 0, 700, 700);

        // Draw road
        g.setColor(new Color(0x9F8772));
        g.fillRect(90, 0, 10, 700);
        g.fillRect(600, 0, 10, 700);
        g.fillRect(100, 0, 500, 700);

        // Draw road lines
        g.setColor(Color.WHITE);
        for (int[] line : roadLines) {
            g.fillRect(line[0], line[1], 10, 70);
        }

        // Draw obstacles
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }

        // Draw player car
        playerCar.draw(g);

        // Draw UI
        drawUI(g);

        // Game over screen
        if (gameOver) {
            drawGameOver(g);
        }
    }

    private void drawUI(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(120, 35, 220, 50);
        g.setColor(Color.BLACK);
        g.fillRect(125, 40, 210, 40);
        g.setColor(Color.RED);
        g.fillRect(385, 35, 180, 50);
        g.setColor(Color.BLACK);
        g.fillRect(390, 40, 170, 40);
        g.setColor(Color.WHITE);
        g.setFont(new Font("MV Boli", Font.BOLD, 30));
        g.drawString("Score: " + score, 130, 67);
        g.drawString(speed + " m/h", 400, 67);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(120, 210, 460, 200);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(130, 220, 440, 180);
        g.setFont(new Font("MV Boli", Font.BOLD, 50));
        g.setColor(Color.RED);
        g.drawString("Game Over!", 210, 270);
        g.setColor(Color.WHITE);
        g.setFont(new Font("MV Boli", Font.BOLD, 30));
        g.drawString("Press Enter to Restart", 190, 340);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver && e.getKeyCode() == KeyEvent.VK_ENTER) {
            restartGame();
        } else if (!gameOver) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    playerCar.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    playerCar.moveRight();
                    break;
            }
        }
        repaint();
    }

    private void restartGame() {
        gameOver = false;
        score = 0;
        speed = 90;
        initializeGame();
        gameTimer.setDelay(100);
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}