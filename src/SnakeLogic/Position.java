package SnakeLogic;


import java.text.DecimalFormat;

/**
 * This is the primary node of the maze. The directions north,south,west,east is the
 * children og the node and parent is the parent of the node.
 */
public class Position implements Comparable<Position> {


    private int x;
    private int y;
    private double cost;
    private boolean occupied = false;
    private int edge;

    private Position west;
    private Position south;
    private Position east;
    private Position north;
    private Position parent;



    private String[] occupiedString = {"Occopied", "Not Occupied"};

    /**
     * This is a tool gathered from the internet, that help me cut down the digits the to string displays!
     */

    private  DecimalFormat df2 = new DecimalFormat("#.##");

    public Position(int x, int y) {
        this.setX(x);
        this.setY(y);



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

        if (this.getCost() < otherPos.getCost()){ rtn = -1;}
        if (this.getCost() > otherPos.getCost()){ rtn = +1;}
        return rtn;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {

        this.occupied = occupied;
    }

    /**
     * @return This method return a very descriptive string telling almost all information about a giving note..
     */
    @Override
    public String toString() {
        int s;
        if (this.isOccupied()) {
            s = 0;
        } else {
            s = 1;
        }
        return "X Coordinat: (" + this.getX() + ") Y Coordinat (" + this.getY() + ") and it is " + getOccupiedString()[s] + " COST: " + ( df2.format(this.cost));
    }

    @Override
    public boolean equals(Object object2) {

        return object2 instanceof Position && this.x == (((Position)object2).x) && this.y ==(((Position)object2).y);


    }





    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String[] getOccupiedString() {
        return occupiedString;
    }

    public void setOccupiedString(String[] occupiedString) {
        this.occupiedString = occupiedString;
    }


    public Position getWest() {
        return west;
    }

    public void setWest(Position west) {
        this.west = west;
    }

    public Position getSouth() {
        return south;
    }

    public void setSouth(Position south) {
        this.south = south;
    }

    public Position getEast() {
        return east;
    }

    public void setEast(Position east) {
        this.east = east;
    }

    public Position getNorth() {
        return north;
    }

    public void setNorth(Position north) {
        this.north = north;
    }

    public Position getParent() {
        return parent;
    }

    public void setParent(Position parent) {
        this.parent = parent;
    }
}

