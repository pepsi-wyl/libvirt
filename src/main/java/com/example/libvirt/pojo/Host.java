package com.example.libvirt.pojo;

import lombok.*;

@Builder
@Getter
@ToString
public class Host {
    // os
    private String vendor;           // Ubuntu
    private String vendorVersion;    // 20.04
    private String vendorCodeName;   // focal
    private String version;          // 5.13.0-44-generic

    // memory 单位:k
    private long memory;
    private long memoryUsed;
    private long memoryFree;
    private long swap;
    private long swapUsed;
    private long swapFree;

    // cpu
    private int cpuNum;
    private String cpuModel;
    private int cpuMhz;

    // file
    private String devName;
    private String dirName;
    private long fileTotal;
    private long fileUsed;
    private double fileUsePercent;

    // net
    private String netDescription;
    private String netType;
    private String netIP;
    private String netMAC;
    private String netMask;
}