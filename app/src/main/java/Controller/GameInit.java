package Controller;

import View.*;

import java.awt.*;

public class GameInit {

    public static int Rows = 15;
    public static final int Columns = 26;
    private static int[][] route;

    private static GameLoop gameLoop;
    private static Display myDisplay;

    public static GameLoop getGameLoop() {
        return gameLoop;
    }

    public static void main(String[] args) {

        route = new int[Columns + 2][Rows + 2];
        for (int i = 1; i < Columns + 1; i++)
            for (int j = 1; j < Rows + 1; j++)
                route[i][j] = 888;
        for (int i = 0; i < Columns + 1; i++) {
            route[i][0] = 999;
            route[i][Rows + 1] = 999;
        }
        for (int j = 0; j < Rows + 1; j++) {
            route[0][j] = 999;
            route[Columns + 1][j] = 999;
        }

        gameLoop = new GameLoop();
        myDisplay = new Display();

        gameLoop.run(myDisplay);

    }

    public static void recalculateRoute(Point toTile) throws Exception {
        //Tile[][] tiles = gameLoop.getTiles();

        for (int i = 1; i < Columns + 1; i++)
            for (int j = 1; j < Rows + 1; j++)
                route[i][j] = 888;
        for (int i = 0; i < Columns + 1; i++) {
            route[i][0] = 999;
            route[i][Rows + 1] = 999;
        }
        for (int j = 0; j < Rows + 1; j++) {
            route[0][j] = 999;
            route[Columns + 1][j] = 999;
        }


        for (int i = 0; i < gameLoop.getTowers().size(); i++)
            route[gameLoop.getTowers().get(i).getPositionTile().x][gameLoop.getTowers().get(i).getPositionTile().y] = 999;

        route[toTile.x][toTile.y] = 0;
        boolean change = false;
        int loopCounter = Columns * Rows;
        do {
            change = false;
            for (int i = 1; i < Columns + 1; i++)
                for (int j = 1; j < Rows + 1; j++)
                    if (route[i][j] == 888) {
                        route[i][j] = Math.min(Math.min(route[i][j], Math.min(route[i + 1][j], Math.min(route[i][j + 1], Math.min(route[i - 1][j], route[i][j - 1])))) + 1, 888);
                        change = true;
                    }
            loopCounter--;
            if (loopCounter == 0)
                throw new Exception("can't find route");
        } while (change);
    }

    public static int[][] getRoute() {
        return route;
    }


}