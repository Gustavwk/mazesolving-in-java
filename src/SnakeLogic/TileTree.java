package SnakeLogic;


import SnakeGUI.Position;

public class TileTree {
    private class Tile {



            Position data;               //This variable is the Type
            Tile left;              //This is gonna be an item similar to itself
            Tile right;
            Tile up;
            Tile down;


        public Tile (Position position) {
            this.data = position;
            this.left = null;
            this.right = null;
            this.up = null;
            this.down = null;

        }
        @Override
        public String toString() {
            return "Data: " + this.data + "\n" + "Left: " + this.left + "\n" + "Right:" + this.right;
        }
    }

    Tile root;

    public void add(Tile position) {
        if (root == null){
            root = position;
        }
    }




}





