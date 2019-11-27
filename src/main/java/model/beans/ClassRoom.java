package model.beans;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClassRoom {
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty capacity = new SimpleIntegerProperty();
    private BooleanProperty hdmi = new SimpleBooleanProperty();
    private BooleanProperty vga = new SimpleBooleanProperty();

    public ClassRoom() {
    }

    public ClassRoom(String name, int capacity, boolean hasHDMI, boolean hasVGA) {
        this.name.set(name);
        this.capacity.set(capacity);
        this.hdmi.set(hasHDMI);
        this.vga.set(hasVGA);
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


    public BooleanProperty hdmiProperty() {
        return hdmi;
    }

    public void setHdmi(boolean hdmi) {
        this.hdmi.set(hdmi);
    }

    public BooleanProperty vgaProperty() {
        return vga;
    }
    public void setVga(boolean vga) {
        this.vga.set(vga);
    }



    @Override
    public String toString() {
        return "ClassRoom{" +
                "name=" + name +
                ", capacity=" + capacity +
                ", hdmi=" + hdmi +
                ", vga=" + vga +
                '}';
    }
}
