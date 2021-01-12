package Controller;

import View.*;

import java.awt.*;
import java.util.stream.IntStream;

public class GameInit {

    public static final int ROWS = 15;
    public static final int COLUMNS = 26;
    private static int[][] route;

    private static GameLoop gameLoop;
    private static Display myDisplay;

    public static void main(String[] args) {

        route = new int[COLUMNS + 2][ROWS + 2];
        for (int i = 1; i < COLUMNS + 1; i++)
            for (int j = 1; j < ROWS + 1; j++)
                route[i][j] = 888;
        for (int i = 0; i < COLUMNS + 1; i++) {
            route[i][0] = 999;
            route[i][ROWS + 1] = 999;
        }
        for (int j = 0; j < ROWS + 1; j++) {
            route[0][j] = 999;
            route[COLUMNS + 1][j] = 999;
        }

        gameLoop = new GameLoop();
        myDisplay = new Display();

        gameLoop.run(myDisplay);
    }

    public static void recalculateRoute(Point toTile) {
        for (int i = 1; i < COLUMNS + 1; i++)
            for (int j = 1; j < ROWS + 1; j++)
                route[i][j] = 888;
        for (int i = 0; i < COLUMNS + 1; i++) {
            route[i][0] = 999;
            route[i][ROWS + 1] = 999;
        }
        for (int j = 0; j < ROWS + 1; j++) {
            route[0][j] = 999;
            route[COLUMNS + 1][j] = 999;
        }

        IntStream.range(0, gameLoop.getTowers().size()).forEach(i -> route[gameLoop.getTowers().get(i).getPositionTile().x][gameLoop.getTowers().get(i).getPositionTile().y] = 999);

        route[toTile.x][toTile.y] = 0;
        boolean change;
        int loopCounter = COLUMNS * ROWS;
        do {
            change = false;
            for (int i = 1; i < COLUMNS + 1; i++)
                for (int j = 1; j < ROWS + 1; j++)
                    if (route[i][j] == 888) {
                        route[i][j] = Math.min(Math.min(route[i][j], Math.min(route[i + 1][j], Math.min(route[i][j + 1], Math.min(route[i - 1][j], route[i][j - 1])))) + 1, 888);
                        change = true;
                    }
            loopCounter--;
            if (loopCounter == 0)
                throw new IllegalStateException("can't find route");
        } while (change);
    }

    public static GameLoop getGameLoop() {
        return gameLoop;
    }
    public static int[][] getRoute() {
        return route;
    }
}