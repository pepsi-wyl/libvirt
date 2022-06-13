package com.example.libvirt.Utils;

import com.example.libvirt.pojo.LibvirtConnect;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.libvirt.Connect;

@Log
public class LibvirtUtils {

    private static Connect connect;

    // Connection
    @SneakyThrows
    public static Connect getConnection() {
        if (null == connect) {
            connect = new Connect("qemu:///system", false);
            log.info("Libvirt local connection successful" + "\n"
                    + "     连接URI: " + connect.getURI() + "\n"
                    + "     宿主机主机名: " + connect.getHostName() + "\n"
                    + "     宿主机剩余内存: " + connect.getFreeMemory() + "\n"
                    + "     宿主机最大cpu数量: " + connect.getMaxVcpus(null) + "\n"
                    + "     libvirt库版本号: " + connect.getLibVirVersion() + "\n"
                    + "     hypervisor名称: " + connect.getType()
            );
        }
        return connect;
    }

    // ConnectionInfo
    @SneakyThrows
    public static LibvirtConnect getConnectionIo() {
        Connect connect = getConnection();
        return LibvirtConnect.builder()
                .url(connect.getURI())
                .hostName(connect.getHostName())
                .libVirVersion(connect.getLibVirVersion())
                .hypervisorVersion(connect.getType())
                .build();
    }

    public static void main(String[] args) {
        LibvirtUtils.getConnection();
        System.out.println(LibvirtUtils.getConnectionIo());
    }

}
