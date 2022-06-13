package com.example.libvirt.domain;

public class Net {

    private String name;
    private String type;
    private String drivingtype;
    private String state;
    private String ip;

    public Net(String name,String ip, String type, String drivingtype, String state) {
        this.name = name;
        this.type = type;
        this.drivingtype = drivingtype;
        this.state = state;
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDrivingtype() {
        return drivingtype;
    }

    public void setDrivingtype(String drivingtype) {
        this.drivingtype = drivingtype;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Net{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", drivingtype='" + drivingtype + '\'' +
                ", state='" + state + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
