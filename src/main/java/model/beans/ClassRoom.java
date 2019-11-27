package model.beans;

public class ClassRoom {
    private String name;
    private int capacity;
    private boolean hasHDMI;
    private boolean hasVGA;
    private boolean isAvailable;

    public ClassRoom()
    {
        this.name = "";
        this.capacity = 0;
        hasHDMI = false;
        hasVGA = false;
    }

    public ClassRoom(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        hasHDMI = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isHasHDMI() {
        return hasHDMI;
    }


    public void setHasHDMI(boolean hasHDMI) {
        this.hasHDMI = hasHDMI;
    }

    public void setHasVGA(boolean hasVGA) {
        this.hasVGA = hasVGA;
    }

    public String toString() {
        return this.name + " " + this.capacity + " " + this.hasHDMI;
    }
}
