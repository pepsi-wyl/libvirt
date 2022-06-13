package com.example.libvirt.domain;

import org.libvirt.Domain;
import org.libvirt.LibvirtException;

public class Virtual {
    private Libvirt libvirt = new Libvirt();
    private String name;
    private int id;
    private String type;
    private int memory;
    private String ip;
    private String state;
    private String port;

    public Virtual(int id) throws LibvirtException {
        this.id = id;
        Domain domain = libvirt.getConnect().domainLookupByID(id);
        name = domain.getName();
        type = domain.getOSType();
        memory = (int)domain.getMaxMemory();
        int begain = domain.getXMLDesc(0).indexOf("<mac address=");
        ip = domain.getXMLDesc(0).substring(begain+14,begain+31);
        state = domain.getInfo().state.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Virtual{" +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", memory=" + memory +
                ", ip='" + ip + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
