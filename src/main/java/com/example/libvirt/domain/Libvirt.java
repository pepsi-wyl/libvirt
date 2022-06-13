package com.example.libvirt.domain;

import org.libvirt.Connect;
import org.libvirt.LibvirtException;

public class Libvirt {
    private Connect connect;

    public Connect getConnect() {
        return connect;
    }

    public Libvirt() throws LibvirtException {
        connect = new Connect("qemu:///system",false);
    }
}
