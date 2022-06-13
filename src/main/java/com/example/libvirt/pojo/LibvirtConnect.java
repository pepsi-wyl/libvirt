package com.example.libvirt.pojo;

import lombok.*;

@Builder
@Getter
@ToString
public class LibvirtConnect {
    private String url;
    private String hostName;
    private long libVirVersion;
    private String hypervisorVersion;
}