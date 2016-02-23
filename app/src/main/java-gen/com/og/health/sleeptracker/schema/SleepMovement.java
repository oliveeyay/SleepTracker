package com.og.health.sleeptracker.schema;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "SLEEP_MOVEMENT".
 */
public class SleepMovement {

    private Long id;
    private java.util.Date movementTime;
    private Float movementX;
    private Float movementY;
    private Float movementZ;

    public SleepMovement() {
    }

    public SleepMovement(Long id) {
        this.id = id;
    }

    public SleepMovement(Long id, java.util.Date movementTime, Float movementX, Float movementY, Float movementZ) {
        this.id = id;
        this.movementTime = movementTime;
        this.movementX = movementX;
        this.movementY = movementY;
        this.movementZ = movementZ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getMovementTime() {
        return movementTime;
    }

    public void setMovementTime(java.util.Date movementTime) {
        this.movementTime = movementTime;
    }

    public Float getMovementX() {
        return movementX;
    }

    public void setMovementX(Float movementX) {
        this.movementX = movementX;
    }

    public Float getMovementY() {
        return movementY;
    }

    public void setMovementY(Float movementY) {
        this.movementY = movementY;
    }

    public Float getMovementZ() {
        return movementZ;
    }

    public void setMovementZ(Float movementZ) {
        this.movementZ = movementZ;
    }

}
