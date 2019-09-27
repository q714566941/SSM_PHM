package com.pojo;

import java.sql.Timestamp;

public class DiskData {
    private Timestamp timeCreate;
    private String device;
    private String fstype;
    private String host;
    private String mode;
    private String path;
    private long free;
    private int inodes_free;
    private int inodes_total;
    private int inodes_used;
    private long total;
    private long used;
    private float used_precent;

    public DiskData(Timestamp timeCreate, String device, String fstype, String host, String mode, String path, long free, int inodes_free, int inodes_total, int inodes_used, long total, long used, float used_precent) {
        this.timeCreate = timeCreate;
        this.device = device;
        this.fstype = fstype;
        this.host = host;
        this.mode = mode;
        this.path = path;
        this.free = free;
        this.inodes_free = inodes_free;
        this.inodes_total = inodes_total;
        this.inodes_used = inodes_used;
        this.total = total;
        this.used = used;
        this.used_precent = used_precent;
    }

    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getFstype() {
        return fstype;
    }

    public void setFstype(String fstype) {
        this.fstype = fstype;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getFree() {
        return free;
    }

    public void setFree(long free) {
        this.free = free;
    }

    public int getInodes_free() {
        return inodes_free;
    }

    public void setInodes_free(int inodes_free) {
        this.inodes_free = inodes_free;
    }

    public int getInodes_total() {
        return inodes_total;
    }

    public void setInodes_total(int inodes_total) {
        this.inodes_total = inodes_total;
    }

    public int getInodes_used() {
        return inodes_used;
    }

    public void setInodes_used(int inodes_used) {
        this.inodes_used = inodes_used;
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

    public float getUsed_precent() {
        return used_precent;
    }

    public void setUsed_precent(float used_precent) {
        this.used_precent = used_precent;
    }

    @Override
    public String toString() {
        return "DiskData{" +
                "timeCreate=" + timeCreate +
                ", device='" + device + '\'' +
                ", fstype='" + fstype + '\'' +
                ", host='" + host + '\'' +
                ", mode='" + mode + '\'' +
                ", path='" + path + '\'' +
                ", free=" + free +
                ", inodes_free=" + inodes_free +
                ", inodes_total=" + inodes_total +
                ", inodes_used=" + inodes_used +
                ", total=" + total +
                ", used=" + used +
                ", used_precent=" + used_precent +
                '}';
    }
}
