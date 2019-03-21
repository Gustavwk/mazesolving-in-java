package SnakeGUI;



public class Position implements Comparable<Position> {
    private class Adjacents{
        private Position pos;
    }

    private int x;
    private int y;
    /*
    Position north;
    Position south;
    Position west;
    Position east;
    */
    private boolean occupied = false;
    String OccupiedString [] = {"Occopied", "Not Occupied"};


    public Position(int x, int y) {
        this.setX(x);
        this.setY(y);
/*
        this.north.setX(x);
        this.north.setX(y-1);

        this.south.setX(x);
        this.south.setY(y+1);

        this.west.setX(x-1);
        this.west.setY(y);

        this.east.setX(x+1);
        this.east.setY(y);
        */
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }


    public void setY(int y) {
        this.y = y;
    }


    @Override
    public int compareTo(Position otherPos) {
        int rtn = 0;
        if (getX() == otherPos.getX() && getY() == otherPos.getY()) {
            return 0;
        }
        if (getX() < otherPos.getX()) rtn = +1;
        if (getX() > otherPos.getX()) rtn = -1;

        if (rtn == 0) {
            if (getY() < otherPos.getY()) rtn = +1;
            if (getY() > otherPos.getY()) rtn = -1;
        }

        return rtn;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {

        this.occupied = occupied;
    }

    @Override
    public String toString() {
        int s;
        if (this.occupied){
            s = 0;
        }else{
            s = 1;
        }
        return "X Coordinat: (" + this.x + ") Y Coordinat (" + this.y + ") and it is " + OccupiedString[s];
    }
    @Override
    public boolean equals(Object otherObject) {

        try{
            //cast into position to get x and y values.
            Position otherObject1 = (Position)otherObject;

            //return whether x == x and y == y.
            return (otherObject1.x == this.x && otherObject1.y == this.y);

        }catch (ClassCastException e){

            System.out.println("ClassException!");
            return false;
        }


    }
}