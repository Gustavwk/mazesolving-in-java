package SnakeGUI;



public class Position implements Comparable<Position> {


    private int x;
    private int y;


    private boolean occupied = false;
    private String[] OccupiedString  = {"Occopied", "Not Occupied"};


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
        if (this.isOccupied()){
            s = 0;
        }else{
            s = 1;
        }
        return "X Coordinat: (" + this.getX() + ") Y Coordinat (" + this.getY() + ") and it is " + getOccupiedString()[s];
    }
    @Override
    public boolean equals(Object otherObject) {

        try{
            //cast into position to get x and y values.
            Position otherObject1 = (Position)otherObject;

            //return whether x == x and y == y.
            return (otherObject1.getX() == this.getX() && otherObject1.getY() == this.getY());

        }catch (ClassCastException e){

            System.out.println("ClassException!");
            return false;
        }


    }


    public String[] getOccupiedString() {
        return OccupiedString;
    }

    public void setOccupiedString(String[] occupiedString) {
        OccupiedString = occupiedString;
    }
}