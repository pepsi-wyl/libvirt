package com.example.libvirt.domain;

import org.libvirt.LibvirtException;
import org.libvirt.StoragePool;
import org.libvirt.StoragePoolInfo;

public class Storagepool {
    private Libvirt libvirt = new Libvirt();
    private String name;
    private String type;
    private String usage;
    private int capacity;

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    private String state;

    public Storagepool(String name) throws LibvirtException {
        this.name = name;
        StoragePool storagePool = libvirt.getConnect().storagePoolLookupByName(name);
        StoragePoolInfo storagePoolInfo = storagePool.getInfo();
        type = "qcow2";
        capacity = (int) (storagePoolInfo.capacity / 1024.00 / 1024.00 / 1024.00);
        usage = String.valueOf(storagePoolInfo.capacity/storagePoolInfo.allocation + "%");
        state = storagePoolInfo.state.toString();
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Storagepool{" +
                "libvirt=" + libvirt +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", usage='" + usage + '\'' +
                ", capacity=" + capacity +
                ", state='" + state + '\'' +
                '}';
    }
}
