package com.example.libvirt.domain;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class Host {
    private String cpumodel;
    private int cpumhz;
    private int cpunum;
    private long memory;
    private long memoryused;
    private Sigar sigar;

    public Sigar getInstance() {
        if (null == sigar) {
            sigar = new Sigar();
        }
        return sigar;
    }
    public Host() throws SigarException {
        memory = getInstance().getMem().getTotal();
        memoryused = getInstance().getMem().getActualUsed();
        CpuInfo[] cpuInfos = getInstance().getCpuInfoList();
        cpunum = cpuInfos.length;
        for (CpuInfo cpuInfo : cpuInfos) {
            cpumodel = cpuInfo.getModel();
            cpumhz = cpuInfo.getMhz();
        }
    }

    public String getCpumodel() {
        return cpumodel;
    }

    public int getCpumhz() {
        return cpumhz;
    }

    public int getCpunum() {
        return cpunum;
    }

    public long getMemory() {
        return memory;
    }

    public long getMemoryused() {
        return memoryused;
    }
    /*
    @Override
    public String toString() {
        return "Host{" +
                "cpumodel='" + cpumodel + '\'' +
                ", cpumhz=" + cpumhz +
                '}';
    }

    public static void main(String[] args) throws SigarException {
        Host host = new Host();

        System.out.println(host);
    }

    */
}
