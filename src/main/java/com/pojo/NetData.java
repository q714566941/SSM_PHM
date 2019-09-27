package com.pojo;

import java.sql.Timestamp;

public class NetData {
    private Timestamp timeCreate;
    private String host;
    private long bytes_recv;
    private long bytes_sent;
    private long drop_in;
    private long drop_out;
    private long err_in;
    private long err_out;
    private long packets_recv;
    private long packets_sent;

    @Override
    public String toString() {
        return "NetData{" +
                "timeCreate=" + timeCreate +
                ", host='" + host + '\'' +
                ", bytes_recv=" + bytes_recv +
                ", bytes_sent=" + bytes_sent +
                ", drop_in=" + drop_in +
                ", drop_out=" + drop_out +
                ", err_in=" + err_in +
                ", err_out=" + err_out +
                ", packets_recv=" + packets_recv +
                ", packets_sent=" + packets_sent +
                '}';
    }

    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public long getBytes_recv() {
        return bytes_recv;
    }

    public void setBytes_recv(long bytes_recv) {
        this.bytes_recv = bytes_recv;
    }

    public long getBytes_sent() {
        return bytes_sent;
    }

    public void setBytes_sent(long bytes_sent) {
        this.bytes_sent = bytes_sent;
    }

    public long getDrop_in() {
        return drop_in;
    }

    public void setDrop_in(long drop_in) {
        this.drop_in = drop_in;
    }

    public long getDrop_out() {
        return drop_out;
    }

    public void setDrop_out(long drop_out) {
        this.drop_out = drop_out;
    }

    public long getErr_in() {
        return err_in;
    }

    public void setErr_in(long err_in) {
        this.err_in = err_in;
    }

    public long getErr_out() {
        return err_out;
    }

    public void setErr_out(long err_out) {
        this.err_out = err_out;
    }

    public long getPackets_recv() {
        return packets_recv;
    }

    public void setPackets_recv(long packets_recv) {
        this.packets_recv = packets_recv;
    }

    public long getPackets_sent() {
        return packets_sent;
    }

    public void setPackets_sent(long packets_sent) {
        this.packets_sent = packets_sent;
    }


}