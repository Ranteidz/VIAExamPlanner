package model.classes;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Classroom {
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty capacity = new SimpleIntegerProperty();
    private BooleanProperty hdmi = new SimpleBooleanProperty();
    private BooleanProperty vga = new SimpleBooleanProperty();

    public Classroom() {
    }

    public Classroom(String name, int capacity, boolean hasHDMI, boolean hasVGA) {
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

    public StringProperty classroomInfoProperty() {
        StringProperty info = new SimpleStringProperty();
        info.set(toString());
        return info;
    }

    @Override
    public String toString() {
        return name.get() + ", " + capacity.get() + " seats, " + ((hdmi.get() && vga.get())?(hdmi.get()?"HDMI, ":""):"HDMI") + (vga.get()?"VGA":"");
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Classroom))
            return false;
        Classroom otherCl = (Classroom) obj;
        return otherCl.toString().equals(toString());
    }
}
