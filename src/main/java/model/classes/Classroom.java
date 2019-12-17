package model.classes;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Represents a classroom inside the university.
 * A classroom can be assigned to an exam.
 */
public class Classroom {
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty capacity = new SimpleIntegerProperty();
    private BooleanProperty hdmi = new SimpleBooleanProperty();
    private BooleanProperty vga = new SimpleBooleanProperty();

    public Classroom() {
    }

    /**
     * Creates a new classroom object in memory
     * @param name ID of the classroom (ex: E201.a)
     * @param capacity capacity of the classroom
     * @param hasHDMI true if the classroom has HDMI
     * @param hasVGA true if the classroom has VGA
     */
    public Classroom(String name, int capacity, boolean hasHDMI, boolean hasVGA) {
        this.name.set(name);
        this.capacity.set(capacity);
        this.hdmi.set(hasHDMI);
        this.vga.set(hasVGA);
    }

    /**
     * Returns the name/ID of the classroom.
     * @return name as StringProperty
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Sets the name of the classroom.
     * @param name new name for classroom
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Returns the capacity of the classroom.
     * @return capacity as IntegerProperty
     */
    public IntegerProperty capacityProperty() {
        return capacity;
    }

    /**
     * Sets the capacity of the classroom.
     * @param capacity new capacity for the classroom
     */
    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }

    /**
     * Returns true if the classroom has HDMI.
     * @return true if the classroom has HDMI as BooleanProperty
     */
    public BooleanProperty hdmiProperty() {
        return hdmi;
    }

    /**
     * Sets the HDMI of the classroom
     * @param hdmi new boolean value for HDMI
     */
    public void setHdmi(boolean hdmi) {
        this.hdmi.set(hdmi);
    }

    /**
     * Returns true if the classroom has VGA.
     * @return true if the classroom has VGA as BooleanProperty
     */
    public BooleanProperty vgaProperty() {
        return vga;
    }

    /**
     * Sets the VGA of the classroom
     * @param vga new boolean value fo VGA
     */
    public void setVga(boolean vga) {
        this.vga.set(vga);
    }

    /**
     * Returns a StringProperty containing all the information of the classroom in format Name, Capacity, HDMI, VGA
     * @return all the information of the classroom
     */
    public StringProperty classroomInfoProperty() {
        StringProperty info = new SimpleStringProperty();
        info.set(toString());
        return info;
    }

    @Override
    public String toString() {
        return name.get() + ", " + capacity.get() + " seats, " + ((hdmi.get() && vga.get())?(hdmi.get()?"HDMI, ":""):"HDMI") + (vga.get()?"VGA":"");
    }

    /**
     *
     * @param obj the object to be compared to
     * @return true if the objects are the same
     */
    public boolean equals(Object obj) {
        if(!(obj instanceof Classroom))
            return false;
        Classroom otherCl = (Classroom) obj;
        return otherCl.toString().equals(toString());
    }
}
