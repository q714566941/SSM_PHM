package com.pojo;

import java.sql.Timestamp;

public class CpuData {
    private Timestamp timeCreate;
    private String cpu;
    private String host;
    private double usage_guest;
    private double usage_guest_nice;
    private double usage_idle;
    private double usage_iowait;
    private double usage_irq;
    private double usage_nice;
    private double usage_softirq;
    private double usage_steal;
    private double usage_system;
    private double usage_user;

    public CpuData(Timestamp timeCreate, String cpu, String host, double usage_guest, double usage_guest_nice, double usage_idle, double usage_iowait, double usage_irq, double usage_nice, double usage_softirq, double usage_steal, double usage_system, double usage_user) {
        this.timeCreate = timeCreate;
        this.cpu = cpu;
        this.host = host;
        this.usage_guest = usage_guest;
        this.usage_guest_nice = usage_guest_nice;
        this.usage_idle = usage_idle;
        this.usage_iowait = usage_iowait;
        this.usage_irq = usage_irq;
        this.usage_nice = usage_nice;
        this.usage_softirq = usage_softirq;
        this.usage_steal = usage_steal;
        this.usage_system = usage_system;
        this.usage_user = usage_user;
    }

    public Timestamp getTime() {
        return timeCreate;
    }

    public void setTime(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public double getUsage_guest() {
        return usage_guest;
    }

    public void setUsage_guest(double usage_guest) {
        this.usage_guest = usage_guest;
    }

    public double getUsage_guest_nice() {
        return usage_guest_nice;
    }

    public void setUsage_guest_nice(double usage_guest_nice) {
        this.usage_guest_nice = usage_guest_nice;
    }

    public double getUsage_idle() {
        return usage_idle;
    }

    public void setUsage_idle(double usage_idle) {
        this.usage_idle = usage_idle;
    }

    public double getUsage_iowait() {
        return usage_iowait;
    }

    public void setUsage_iowait(double usage_iowait) {
        this.usage_iowait = usage_iowait;
    }

    public double getUsage_irq() {
        return usage_irq;
    }

    public void setUsage_irq(double usage_irq) {
        this.usage_irq = usage_irq;
    }

    public double getUsage_nice() {
        return usage_nice;
    }

    public void setUsage_nice(double usage_nice) {
        this.usage_nice = usage_nice;
    }

    public double getUsage_softirq() {
        return usage_softirq;
    }

    public void setUsage_softirq(double usage_softirq) {
        this.usage_softirq = usage_softirq;
    }

    public double getUsage_steal() {
        return usage_steal;
    }

    public void setUsage_steal(double usage_steal) {
        this.usage_steal = usage_steal;
    }

    public double getUsage_system() {
        return usage_system;
    }

    public void setUsage_system(double usage_system) {
        this.usage_system = usage_system;
    }

    public double getUsage_user() {
        return usage_user;
    }

    public void setUsage_user(double usage_user) {
        this.usage_user = usage_user;
    }

    @Override
    public String toString() {
        return "CpuData{" +
                "timeCreate=" + timeCreate +
                ", cpu='" + cpu + '\'' +
                ", host='" + host + '\'' +
                ", usage_guest=" + usage_guest +
                ", usage_guest_nice=" + usage_guest_nice +
                ", usage_idle=" + usage_idle +
                ", usage_iowait=" + usage_iowait +
                ", usage_irq=" + usage_irq +
                ", usage_nice=" + usage_nice +
                ", usage_softirq=" + usage_softirq +
                ", usage_steal=" + usage_steal +
                ", usage_system=" + usage_system +
                ", usage_user=" + usage_user +
                '}';
    }
}
