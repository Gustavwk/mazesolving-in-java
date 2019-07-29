package SnakeLogic;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.LinkedList;

public class DataDisplay {

    private LinkedList<String> dataList = new LinkedList<>();
    private LinkedList<Ghost> ghosts;
    private Ghost currentGhost;

    public DataDisplay (LinkedList ghost){
        this.ghosts = ghost;
    }

    public Boolean initialiseData(){
        for (Ghost ghost : ghosts) {
            currentGhost = ghost;
            getData(ghost);
        }
        return true;
    }

    public Boolean getData(Ghost ghost){
        String data;
        if (currentGhost.possible) {
            data = " \n " + currentGhost.toString() + " HAS TAKEN " + ghost.goPath.size() + " STEPS AND COMPLETED THE MAZE";

        } else {
            data = "\n " + currentGhost.toString() + " IS UNABLE TO COMPLETE THE CURRENT MAZE ";
        }
        dataList.add(data);

        return true;
    }

    public LinkedList<String> getDataList() {
        return dataList;
    }
}
