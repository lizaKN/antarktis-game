package pgdp.oop;

import java.awt.event.WindowEvent;
import java.util.Objects;

public class Antarktis extends Maze {
    private static int width, height;
    private static Penguin lostPenguin;
    private static Whale[] whales = new Whale[5];
    private static LeopardSeal[] leopardSeals = new LeopardSeal[20];
    private static Fish[] fishes = new Fish[500];
    private static PlayerPenguin playerPenguin;

    public static void main(String[] args) {
        width = 41;
        height = 41;
        antarktis = generateMaze(width, height);
        Animal.setAntarktis(antarktis);
        setupMaze();
        gameLoop();
        // Close the open frame
        closeFrame();
    }

    private static void gameLoop() {
        while (true) {
            if (!playerPenguin.alive) break;
            if (!lostPenguin.alive) break;
            draw();
            if(currentEvent == UP){
                if (playerPenguin.move(playerPenguin.x,playerPenguin.y - 1)) break;
            }
            else if(currentEvent == DOWN){
                if (playerPenguin.move(playerPenguin.x,playerPenguin.y + 1)) break;
            }
            else if(currentEvent == LEFT){
                if (playerPenguin.move(playerPenguin.x - 1, playerPenguin.y)) break;
            }
            else if(currentEvent == RIGHT){
                if (playerPenguin.move(playerPenguin.x + 1, playerPenguin.y)) break;
            }
            if (currentEvent != NOTHING) {
                moveAll();
            }
            currentEvent = NOTHING;

        }
    }

    private static void moveAll() {
        for (int j = 0; j < whales.length; j++) {
            whales[j].move();
        }
        for (int j = 0; j < leopardSeals.length; j++) {
            leopardSeals[j].move();
        }
        lostPenguin.move();
        for (int j = 0; j < fishes.length; j++) {
            fishes[j].move();
        }
    }

    /**
     * Example Setup for easier Testing during development
     */
    private static void setupMaze() {
        int[] pos;
        playerPenguin = new PlayerPenguin(3, 3);
        antarktis[3][3] = playerPenguin;

        for (int i = 0; i < whales.length; i++) {
            pos = getRandomEmptyField();
            whales[i] = new Whale(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = whales[i];
        }

        for (int i = 0; i < leopardSeals.length; i++) {
            pos = getRandomEmptyField();
            leopardSeals[i] = new LeopardSeal(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = leopardSeals[i];
        }

        for (int i = 0; i < fishes.length; i++) {
            pos = getRandomEmptyField();
            fishes[i] = new Fish(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = fishes[i];
        }

        antarktis[20][20] = new Penguin(20, 20);
        lostPenguin = (Penguin) antarktis[20][20];
    }
}
