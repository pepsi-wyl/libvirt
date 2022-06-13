package com.example.libvirt.Utils;

import com.example.libvirt.pojo.Host;
import lombok.SneakyThrows;
import org.hyperic.sigar.*;

import java.util.HashMap;

public class SigarUtils {

    private static Sigar sigar;

    private static Sigar getSigarInstance() {
        if (null == sigar) sigar = new Sigar();
        return sigar;
    }

    // getHostInfo
    @SneakyThrows
    public static Host getHostInfo() {

        OperatingSystem OS = OperatingSystem.getInstance();
        Mem mem = getSigarInstance().getMem();
        Swap swap = sigar.getSwap();
        CpuInfo[] cpuInfoList = getSigarInstance().getCpuInfoList();

        HashMap<String, Object> fileInfoMap = new HashMap<>();
        for (FileSystem fs : getSigarInstance().getFileSystemList()) {
            //盘符类型:ext4   硬盘类型:本地硬盘 2
            if (fs.getType() == 2 && fs.getSysTypeName().equals("ext4")) {
                FileSystemUsage usage = getSigarInstance().getFileSystemUsage(fs.getDirName());
                fileInfoMap.put("devName", fs.getDevName());              // 盘符名称
                fileInfoMap.put("dirName", fs.getDirName());              // 盘符路径
                fileInfoMap.put("fileTotal", usage.getTotal());           // 总大小       KB
                fileInfoMap.put("fileUsed", usage.getUsed());             // 已经使用量   KB
                fileInfoMap.put("fileUsePercent", (usage.getUsePercent() * 100D)); // 资源的利用率 %
            }
        }

        HashMap<String, String> netInfoMap = new HashMap<>();
        for (String name : getSigarInstance().getNetInterfaceList()) {
            if (name.equals("ens33")) {
                NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(name);
                if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0 || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr()))
                    continue;
                netInfoMap.put("description", cfg.getDescription());    // 网卡描述
                netInfoMap.put("netType", cfg.getType());               // 网卡类型
                netInfoMap.put("IPAddress", cfg.getAddress());          // IP地址
                netInfoMap.put("MACAddress", cfg.getHwaddr());          // MAC地址
                netInfoMap.put("netmask", cfg.getNetmask());            // 子网掩码
            }
        }

        return Host.builder()

                // os
                .vendor(OS.getVendor())                 // Ubuntu
                .vendorVersion(OS.getVendorVersion())   // 20.04
                .vendorCodeName(OS.getVendorCodeName()) // focal
                .version(OS.getVersion())               // 5.13.0-44-generic

                // memory 单位:k
                .memory(mem.getTotal() / 1024L)
                .memoryUsed(mem.getUsed() / 1024L)
                .memoryFree(mem.getFree() / 1024L)
                .swap(swap.getTotal() / 1024L)
                .swapUsed(swap.getUsed() / 1024L)
                .swapFree(swap.getFree() / 1024L)

                // cpu
                .cpuNum(cpuInfoList.length)
                .cpuModel(cpuInfoList[1].getModel())
                .cpuMhz(cpuInfoList[1].getMhz())

                // file
                .devName((String) fileInfoMap.get("devName"))                    // 盘符名称
                .dirName((String) fileInfoMap.get("dirName"))                    // 盘符路径
                .fileTotal((Long) fileInfoMap.get("fileTotal"))                  // 总大小       KB
                .fileUsed((Long) fileInfoMap.get("fileUsed"))                    // 已经使用量   KB
                .fileUsePercent((Double) fileInfoMap.get("fileUsePercent"))      // 资源的利用率 %

                // net
                .netDescription(netInfoMap.get("description"))                   // 网卡描述
                .netType(netInfoMap.get("netType"))                              // 网卡类型
                .netIP(netInfoMap.get("IPAddress"))                              // IP地址
                .netMAC(netInfoMap.get("MACAddress"))                            // MAC地址
                .netMask(netInfoMap.get("netmask"))                              // 子网掩码

                .build();
    }

    public static void main(String[] args) {
        System.out.println(getHostInfo());
    }

}
