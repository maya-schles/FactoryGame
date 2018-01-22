package com.r3dtech.factory.tile_map.implementation;


import android.graphics.Point;

import com.r3dtech.factory.Machines.Machine;
import com.r3dtech.factory.framework.FileIO;
import com.r3dtech.factory.tile_map.MapTile;
import com.r3dtech.factory.tile_map.TileType;
import com.r3dtech.factory.tile_map.TileMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * This class represents a tiled map of the game.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 12/01/2018.
 */

public class GameMap implements TileMap {
    private static final String MAP_FILE = "/res/raw/world.txt";
    private static final String MACHINES_FILE = "machines.txt";

    private GameTile[][] map = new GameTile[Constants.MAP_HEIGHT][Constants.MAP_WIDTH];
    private static final int VISIBLE_WIDTH_START = 16;
    private static final int VISIBLE_HEIGHT_START = 16;

    private boolean[][] isDiscovered = new boolean[Constants.MAP_HEIGHT][Constants.MAP_WIDTH];

    public GameMap() throws IOException {
        int height = Constants.MAP_HEIGHT;
        int width = Constants.MAP_WIDTH;
        int[][] resArr = new int[height][width];
        int[][] vers = new int[height][width];

        InputStream in = this.getClass().getResourceAsStream(MAP_FILE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        for (int i = 0; i < height; i++) {
            Scanner lineScanner = new Scanner(reader.readLine());
            lineScanner.useDelimiter("[ :]");
            for (int j = 0; j < width; j++) {
                resArr[i][j] =  lineScanner.nextInt();
                vers[i][j] = lineScanner.nextInt();
            }
            lineScanner.close();
        }
        reader.close();
        this.map = GameTile.from2dArr(TileType.fromInt(resArr), vers);

        for (int i = tiledHeight()/2 - VISIBLE_HEIGHT_START/2;
             i < tiledHeight()/2 + VISIBLE_HEIGHT_START/2; i++) {
            for (int j = tiledWidth()/2 - VISIBLE_WIDTH_START/2;
                    j < tiledWidth()/2 + VISIBLE_WIDTH_START/2; j++) {
                isDiscovered[i][j] = true;
            }
        }
    }

    @Override
    public MapTile getTile(int x, int y) {
        if ( 0<=x  && x < tiledWidth() && 0<=y && y < tiledHeight()) {
            return map[y][x];
        }
        else {
            return null;
        }
    }

    @Override
    public int tiledHeight() {
        return map.length;
    }

    @Override
    public int tiledWidth() {
        return map[0].length;
    }

    public String toString() {
        StringBuilder res = new StringBuilder("Game Map: \n");
        for (int i = 0; i < tiledHeight(); i++) {
            for (int j = 0; j < tiledWidth(); j++) {
                res.append(map[i][j]);
                res.append("|");
            }
            res.append("\n");
        }
        return res.toString();
    }

    @Override
    public int height() {
        return tiledHeight()*Constants.TILE_SIZE;
    }

    @Override
    public int width() {
        return tiledWidth()*Constants.TILE_SIZE;
    }

    @Override
    public int simulatedHeight() {
        return tiledHeight()*Constants.TILE_SIZE;
    }

    @Override
    public int simulatedWidth() {
        return tiledWidth()*Constants.TILE_SIZE;
    }

    @Override
    public Point getTileFromLoc(int x, int y) {
        return new Point( x/Constants.TILE_SIZE, y/Constants.TILE_SIZE);
    }

    @Override
    public boolean isDiscovered(int x, int y) {
        if ( 0<=x  && x < tiledWidth() && 0<=y && y < tiledHeight()) {
            return isDiscovered[y][x];
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isLocDiscovered(int x, int y) {
        return isDiscovered(x/Constants.TILE_SIZE, y/Constants.TILE_SIZE);
    }

    @Override
    public int getSmallDistFromDiscovered(int x, int y) {
        for (int i = 0; i < 5; i++) {
            for (int x0 = x - i; x0 <= x+i; x0++) {
                for (int y0 = y - i; y0 <= y+i; y0++) {
                    if (isDiscovered(x0, y0)) {
                        return i;
                    }
                }
            }
        }
        return 5;
    }


    public void loadMachines(FileIO fileIO) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(fileIO.readFile(MACHINES_FILE));
        while(scanner.hasNextLine()) {
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            Machine machine = Machine.loadFromString(scanner.nextLine());
            map[i][j].setMachine(machine);
        }
        scanner.close();
    }

    public void saveMachines(FileIO fileIO) throws IOException {
        PrintWriter writer = new PrintWriter(fileIO.writeFile(MACHINES_FILE));
        for (int i = 0; i < tiledHeight(); i++) {
            for (int j = 0; j < tiledWidth(); j++) {
                if (map[i][j].getMachine() != null) {
                    writer.write(Integer.toString(i)+" "+j+" ");
                    writer.write(map[i][j].getMachine().saveToString());
                }
                writer.write("\n");
            }
        }
        writer.close();
    }

    @Override
    public void addMachine(int x, int y, Machine machine) {
        map[y][x].setMachine(machine);
    }
}
