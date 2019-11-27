package model.beans;

import javafx.beans.property.*;

public class ClassRoom {

    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty capacity = new SimpleIntegerProperty();
    private BooleanProperty hasHDMI = new SimpleBooleanProperty();
    private BooleanProperty hasVGA = new SimpleBooleanProperty();
    private boolean isAvailable;



    public ClassRoom(String name, int capacity, boolean hasHDMI, boolean hasVGA) {
        this.name.set(name);
        this.capacity.set(capacity);
        this.hasHDMI.set(hasHDMI);
        this.hasVGA.set(hasVGA);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public IntegerProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }

    public BooleanProperty hasHDMIProperty() {
        return hasHDMI;
    }

    public void setHasHDMI(boolean hasHDMI) {
        this.hasHDMI.set(hasHDMI);
    }

    public BooleanProperty hasVGAProperty() {
        return hasVGA;
    }

    public void setHasVGA(boolean hasVGA) {
        this.hasVGA.set(hasVGA);
    }

    /* public ClassRoom()
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
    }*/
}
