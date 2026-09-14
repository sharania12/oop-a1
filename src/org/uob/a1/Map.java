package org.uob.a1;

public class Map {
    final private char EMPTY = '.';
    private char [][] map;
    private int width,height;
    private Position pos;
    private char symbol;

    public Map(int width, int height){
        this.width = width;
        this.height = height;
        this.map = new char[height][width];
        for (int y=0;y<height;y++){
            for (int x=0;x<width;x++){
                map[y][x] = EMPTY;
            }//inner for
        }//outer for
    }//method
    
    public void placeRoom(Position pos, char symbol){
        map[pos.y][pos.x]=symbol;
    }
    
    public String display(){
        String mapString ="";
        for (int y=0;y<height;y++){
            for (int x=0;x<width;x++){
                mapString+=map[y][x];
            }
            mapString+="\n";
        }
        return mapString;
    }//display
}//class