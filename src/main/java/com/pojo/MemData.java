package com.pojo;

import java.sql.Timestamp;

public class MemData {
    private Timestamp timeCreate;
    private long active;
    private long available;
    private long buffered;
    private long cached;
    private long dirty;
    private long free;
    private long inactive;
    private long shared;
    private long total;
    private long used;
    private float available_percent;
    private float used_percent;
    private String host;

    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }

    public long getAvailable() {
        return available;
    }

    public void setAvailable(long available) {
        this.available = available;
    }

    public long getBuffered() {
        return buffered;
    }

    public void setBuffered(long buffered) {
        this.buffered = buffered;
    }

    public long getCached() {
        return cached;
    }

    public void setCached(long cached) {
        this.cached = cached;
    }

    public long getDirty() {
        return dirty;
    }

    public void setDirty(long dirty) {
        this.dirty = dirty;
    }

    public long getFree() {
        return free;
    }

    public void setFree(long free) {
        this.free = free;
    }

    public long getInactive() {
        return inactive;
    }

    public void setInactive(long inactive) {
        this.inactive = inactive;
    }

    public long getShared() {
        return shared;
    }

    public void setShared(long shared) {
        this.shared = shared;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public float getAvailable_percent() {
        return available_percent;
    }

    public void setAvailable_percent(float available_percent) {
        this.available_percent = available_percent;
    }

    public float getUsed_percent() {
        return used_percent;
    }

    public void setUsed_percent(float used_percent) {
        this.used_percent = used_percent;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "MemData{" +
                "timeCreate=" + timeCreate +
                ", active=" + active +
                ", available=" + available +
                ", buffered=" + buffered +
                ", cached=" + cached +
                ", dirty=" + dirty +
                ", free=" + free +
                ", inactive=" + inactive +
                ", shared=" + shared +
                ", total=" + total +
                ", used=" + used +
                ", available_percent=" + available_percent +
                ", used_percent=" + used_percent +
                ", host='" + host + '\'' +
                '}';
    }
}

    