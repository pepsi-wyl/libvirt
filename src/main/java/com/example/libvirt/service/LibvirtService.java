package com.example.libvirt.service;

import com.example.libvirt.Utils.*;
import com.example.libvirt.pojo.*;
import org.apache.commons.io.FileUtils;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.libvirt.*;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Log
@Service(value = "libvirtService")
public class LibvirtService {

    /**
     * getHostInformation
     */
    public Host getHostInfo() {
        return SigarUtils.getHostInfo();
    }

    /**
     * getLibvirtConnectInformation
     */
    public LibvirtConnect getLibvirtConnectInformation() {
        return LibvirtUtils.getConnectionIo();
    }

    /**
     * getDomainById
     */
    @SneakyThrows
    public Domain getDomainById(int id) {
        return LibvirtUtils.getConnection().domainLookupByID(id);
    }

    /**
     * getDomainByName
     */
    @SneakyThrows
    public Domain getDomainByName(String name) {
        return LibvirtUtils.getConnection().domainLookupByName(name);
    }

    /**
     * getVirtualById
     */
    @SneakyThrows
    public Virtual getVirtualById(int id) {
        Domain domain = getDomainById(id);
        return Virtual.builder()
                .id(domain.getID())
                .name(domain.getName())
                .state(domain.getInfo().state.toString())
                .build();
    }

    /**
     * getVirtualByName
     */
    @SneakyThrows
    public Virtual getVirtualByName(String name) {
        Domain domain = getDomainByName(name);
        return Virtual.builder()
                .id(domain.getID())
                .name(domain.getName())
                .state(domain.getInfo().state.toString())
                .build();
    }

    /**
     * 虚拟机列表
     */
    @SneakyThrows
    public List<Virtual> getVirtualList() {
        ArrayList<Virtual> virtualList = new ArrayList<>();
        // live
        int[] ids = LibvirtUtils.getConnection().listDomains();
        for (int id : ids) virtualList.add(getVirtualById(id));
        // down
        String[] names = LibvirtUtils.getConnection().listDefinedDomains();
        for (String name : names) virtualList.add(getVirtualByName(name));
        return virtualList;
    }

    /**
     * 暂停/挂起 虚拟机
     */
    @SneakyThrows
    private void suspendedDomain(Domain domain) {
        if (domain.isActive() == 1) {
            domain.suspend();
            log.info(domain.getName() + "虚拟机已挂起！");
        } else log.info("虚拟机未打开");
    }

    public void suspendedDomainById(int id) {
        suspendedDomain(getDomainById(id));
    }

    public void suspendedDomainName(String name) {
        suspendedDomain(getDomainByName(name));
    }


    /**
     * 还原 暂停/挂起 虚拟机
     */
    @SneakyThrows
    private void resumeDomain(Domain domain) {
        if (domain.isActive() == 1) {
            domain.resume();
            log.info(domain.getName() + "虚拟机已唤醒！");
        } else log.info("虚拟机未打开");
    }

    public void resumeDomainById(int id) {
        resumeDomain(getDomainById(id));
    }

    public void resumeDomainByName(String name) {
        resumeDomain(getDomainByName(name));
    }

    /**
     * 保存 虚拟机 --->img文件
     */
    @SneakyThrows
    private void saveDomain(Domain domain) {
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);
        jf.showDialog(null, null);
        String f = jf.getSelectedFile().getAbsolutePath() + "/save.img";
        if (domain.isActive() == 1) {
            domain.save(f);
            log.info(domain.getName() + "虚拟机状态已保存！" + "save: " + f);
        } else log.info("虚拟机未打开");
    }

    public void saveDomainById(int id) {
        saveDomain(getDomainById(id));
    }

    public void saveDomainByName(String name) {
        saveDomain(getDomainByName(name));
    }

    /**
     * 恢复 虚拟机 --->img文件
     */
    @SneakyThrows
    private void restoreDomain(Domain domain) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        if (chooser.getSelectedFile() != null) {
            String path = chooser.getSelectedFile().getPath();
            if (domain.isActive() == 0) {
                domain.getConnect().restore(path);
                log.info(domain.getName() + "虚拟机状态已恢复！！" + "path: " + path);
            } else log.info("虚拟机未关闭");
        }
    }

    public void restoreDomainById(int id) {
        restoreDomain(getDomainById(id));
    }

    public void restoreDomainByName(String name) {
        restoreDomain(getDomainByName(name));
    }

    /**
     * 启动 虚拟机
     */
    @SneakyThrows
    private void initiateDomain(Domain domain) {
        if (domain.isActive() == 0) {
            domain.create();
            log.info(domain.getName() + "虚拟机已启动！");
        } else log.info("虚拟机已经打开过！");
    }

    public void initiateDomainByName(String name) {
        initiateDomain(getDomainByName(name));
    }

    /**
     * 关闭 虚拟机
     */
    @SneakyThrows
    private void shutdownDomain(Domain domain) {
        if (domain.isActive() == 1) {
            domain.shutdown();
            log.info(domain.getName() + "虚拟机已正常关机！");
        } else log.info("虚拟机未打开");
    }

    public void shutdownDomainById(int id) {
        shutdownDomain(getDomainById(id));
    }

    public void shutdownDomainByName(String name) {
        shutdownDomain(getDomainByName(name));
    }

    /**
     * 强制关闭 虚拟机
     */
    @SneakyThrows
    private void shutdownMustDomain(Domain domain) {
        if (domain.isActive() == 1) {
            domain.destroy();
            log.info(domain.getName() + "虚拟机已强制关机！");
        } else log.info("虚拟机未打开");
    }

    public void shutdownMustDomainById(int id) {
        shutdownMustDomain(getDomainById(id));
    }

    public void shutdownMustDomainByName(String name) {
        shutdownMustDomain(getDomainByName(name));
    }

    /**
     * 重启 虚拟机
     */
    @SneakyThrows
    private void rebootDomain(Domain domain) {
        if (domain.isActive() == 1) {
            domain.reboot(0);
            log.info(domain.getName() + "虚拟机状态已重启！");
        } else log.info("虚拟机未打开");
    }

    public void rebootDomainById(int id) {
        rebootDomain(getDomainById(id));
    }

    public void rebootDomainByName(String name) {
        rebootDomain(getDomainByName(name));
    }

    /**
     * 添加 虚拟机 xml------>name   1024MB
     */
    @SneakyThrows
    public void addDomainByName(String name) {
        String xml = "<domain type='kvm'>\n" +
                "  <name>" + name + "</name>\n" +
                "  <uuid>" + UUID.randomUUID() + "</uuid>\n" +
                "  <metadata>\n" +
                "    <libosinfo:libosinfo xmlns:libosinfo=\"http://libosinfo.org/xmlns/libvirt/domain/1.0\">\n" +
                "      <libosinfo:os id=\"http://ubuntu.com/ubuntu/14.04\"/>\n" +
                "    </libosinfo:libosinfo>\n" +
                "  </metadata>\n" +
                "  <memory unit='KiB'>1048576</memory>\n" +                 // 1024 MB
                "  <currentMemory unit='KiB'>1048576</currentMemory>\n" +   // 1024 MB
                "  <vcpu placement='static'>2</vcpu>\n" +
                "  <os>\n" +
                "    <type arch='x86_64' machine='pc-i440fx-focal'>hvm</type>\n" +
                "    <boot dev='hd'/>\n" +
                "  </os>\n" +
                "  <features>\n" +
                "    <acpi/>\n" +
                "    <apic/>\n" +
                "    <vmport state='off'/>\n" +
                "  </features>\n" +
                "  <cpu mode='host-model' check='partial'/>\n" +
                "  <clock offset='utc'>\n" +
                "    <timer name='rtc' tickpolicy='catchup'/>\n" +
                "    <timer name='pit' tickpolicy='delay'/>\n" +
                "    <timer name='hpet' present='no'/>\n" +
                "  </clock>\n" +
                "  <on_poweroff>destroy</on_poweroff>\n" +
                "  <on_reboot>restart</on_reboot>\n" +
                "  <on_crash>destroy</on_crash>\n" +
                "  <pm>\n" +
                "    <suspend-to-mem enabled='no'/>\n" +
                "    <suspend-to-disk enabled='no'/>\n" +
                "  </pm>\n" +
                "  <devices>\n" +
                "    <emulator>" + "/usr/bin/qemu-system-x86_64" + "</emulator>\n" +
                "    <disk type='file' device='disk'>\n" +
                "      <driver name='qemu' type='qcow2'/>\n" +
                "      <source file='/home/wyl/KVMImg/" + name + ".img'/>\n" +   // FileSource
                "      <target dev='vda' bus='virtio'/>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x07' function='0x0'/>\n" +
                "    </disk>\n" +
                "    <controller type='usb' index='0' model='ich9-ehci1'>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x05' function='0x7'/>\n" +
                "    </controller>\n" +
                "    <controller type='usb' index='0' model='ich9-uhci1'>\n" +
                "      <master startport='0'/>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x05' function='0x0' multifunction='on'/>\n" +
                "    </controller>\n" +
                "    <controller type='usb' index='0' model='ich9-uhci2'>\n" +
                "      <master startport='2'/>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x05' function='0x1'/>\n" +
                "    </controller>\n" +
                "    <controller type='usb' index='0' model='ich9-uhci3'>\n" +
                "      <master startport='4'/>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x05' function='0x2'/>\n" +
                "    </controller>\n" +
                "    <controller type='pci' index='0' model='pci-root'/>\n" +
                "    <controller type='virtio-serial' index='0'>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x06' function='0x0'/>\n" +
                "    </controller>\n" +
                "    <interface type='network'>\n" +
                "      <mac address='52:54:00:27:6d:ef'/>\n" +
                "      <source network='default'/>\n" +
                "      <model type='virtio'/>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x03' function='0x0'/>\n" +
                "    </interface>\n" +
                "    <serial type='pty'>\n" +
                "      <target type='isa-serial' port='0'>\n" +
                "        <model name='isa-serial'/>\n" +
                "      </target>\n" +
                "    </serial>\n" +
                "    <console type='pty'>\n" +
                "      <target type='serial' port='0'/>\n" +
                "    </console>\n" +
                "    <channel type='spicevmc'>\n" +
                "      <target type='virtio' name='com.redhat.spice.0'/>\n" +
                "      <address type='virtio-serial' controller='0' bus='0' port='1'/>\n" +
                "    </channel>\n" +
                "    <input type='tablet' bus='usb'>\n" +
                "      <address type='usb' bus='0' port='1'/>\n" +
                "    </input>\n" +
                "    <input type='mouse' bus='ps2'/>\n" +
                "    <input type='keyboard' bus='ps2'/>\n" +
                "    <graphics type='vnc' port='-1' autoport='yes' listen='0.0.0.0' keymap='en-us'>\n" +
                "      <listen type='address' address='0.0.0.0'/>\n" +
                "    </graphics>\n" +
                "    <sound model='ich6'>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x04' function='0x0'/>\n" +
                "    </sound>\n" +
                "    <video>\n" +
                "      <model type='qxl' ram='65536' vram='65536' vgamem='16384' heads='1' primary='yes'/>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x02' function='0x0'/>\n" +
                "    </video>\n" +
                "    <redirdev bus='usb' type='spicevmc'>\n" +
                "      <address type='usb' bus='0' port='2'/>\n" +
                "    </redirdev>\n" +
                "    <redirdev bus='usb' type='spicevmc'>\n" +
                "      <address type='usb' bus='0' port='3'/>\n" +
                "    </redirdev>\n" +
                "    <memballoon model='virtio'>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x08' function='0x0'/>\n" +
                "    </memballoon>\n" +
                "  </devices>\n" +
                "</domain>";
        LibvirtUtils.getConnection().domainDefineXML(xml);    // define ------> creat
        log.info(name + "虚拟机已创建！");
    }

    /**
     * 删除 虚拟机 xml
     */
    @SneakyThrows
    private void deleteDomain(Domain domain) {
        if (domain.isActive() == 1) domain.destroy();  // 强制关机
        domain.undefine();
        log.info(domain.getName() + "虚拟机已删除！");
    }

    public void deleteDomainById(int id) {
        deleteDomain(getDomainById(id));
    }

    public void deleteDomainByName(String name) {
        deleteDomain(getDomainByName(name));
    }

    /**
     * get ImgList
     */
    public List<ImgFile> getImgList() {
        List<ImgFile> list = new ArrayList<>();
        File[] files = new File("/home/wyl/KVMImg/").listFiles();
        if (files != null) {
            for (File file : files) {
                list.add(ImgFile.builder()
                        .name(file.getName())
                        .size(FileUtils.byteCountToDisplaySize(FileUtils.sizeOf(file)))
                        .build());
            }
        }
        return list;
    }

    /**
     * 添加 img
     */
    @SneakyThrows
    public Boolean addImgFile(String name, MultipartFile file) {
        if (!file.isEmpty()) {
            file.transferTo(new File("/home/wyl/KVMImg/" + name + ".img"));
            log.info("文件" + name + ".img已经保存！");
            return true;
        }
        log.info("文件" + name + ".img保存失败！");
        return false;
    }

    /**
     * 下载 img
     */
    @SneakyThrows
    public String downImgFile(String name, HttpServletResponse response) {
        File file = new File("/home/wyl/KVMImg/" + name);
        if (!file.exists()) return "下载文件不存在";
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(), "UTF-8"));  // 设置编码格式
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        OutputStream os = response.getOutputStream();
        int i = 0;
        byte[] buff = new byte[1024];
        while ((i = bis.read(buff)) != -1) {
            os.write(buff, 0, i);
            os.flush();
        }
        bis.close();
        os.close();
        return "下载成功";
    }

    /**
     * 删除 img
     */
    public Boolean deleteImgFile(String name) {
        if (new File("/home/wyl/KVMImg/" + name).delete()) {
            log.info("文件" + name + "已经删除！");
            return true;
        }
        log.info("文件" + name + "文件不存在");
        return false;
    }

    /**
     * 关闭网络
     */
    @SneakyThrows
    public void closeNetWork() {
        Domain domain = getDomainByName(getVirtualList().get(0).getName());
        Network network = domain.getConnect().networkLookupByName("default");
        if (network.isActive() == 1) {
            network.destroy();
            log.info("网络" + network.getName() + "已经被关闭！");
        } else log.info("网络" + network.getName() + "已经处于关闭状态！");
    }

    /**
     * 启动网络
     */
    @SneakyThrows
    public void openNetWork() {
        Domain domain = getDomainByName(getVirtualList().get(0).getName());
        Network network = domain.getConnect().networkLookupByName("default");
        if (network.isActive() == 0) {
            network.create();
            log.info("网络" + network.getName() + "已经被打开！");
        } else log.info("网络" + network.getName() + "已经处于打开状态！");
    }

    /**
     * 网络 State
     */
    @SneakyThrows
    public String getNetState() {
        Domain domain = getDomainByName(getVirtualList().get(0).getName());
        if (domain.getConnect().networkLookupByName("default").isActive() == 1) return "on";
        else return "off";
    }

    /**
     * getSnapshotList
     */
    @SneakyThrows
    public List<Snapshot> getSnapshotListByName(String name) {
        // virsh snapshot-list      虚拟机名
        String cmd = "virsh snapshot" + "-list " + name;
        Process process = Runtime.getRuntime().exec(cmd);
        LineNumberReader line = new LineNumberReader(new InputStreamReader(process.getInputStream()));
        ArrayList<Snapshot> snapshots = new ArrayList<>();
        String str;
        int linCount = 0;
        int snapshotNum = getDomainByName(name).snapshotNum(); // 2
        while ((str = line.readLine()) != null && snapshotNum > 0) {
            linCount++;
            if (linCount <= 2) continue;  // -2 line
            else {
                snapshotNum--;
                String[] lineStr = str.split("   ");
                snapshots.add(Snapshot.builder().name(lineStr[0]).creationTime(lineStr[1]).state(lineStr[2]).build());
            }
        }
        return snapshots;
    }

    /**
     * snapshot管理
     */
    @SneakyThrows
    private void snapshotManger(String op, String name, String snapshotName) {
        // virsh snapshot-create-as 虚拟机名称 快照名称
        // virsh snapshot-delete    虚拟机名称 快照名称
        // virsh snapshot-revert    虚拟机名称 快照名称
        String cmd = "";
        switch (op) {
            case "creat":
                cmd = "virsh snapshot" + "-create-as " + name + " " + snapshotName;
                break;
            case "delete":
                cmd = "virsh snapshot" + "-delete " + name + " " + snapshotName;
                break;
            case "revert":
                cmd = "virsh snapshot" + "-revert " + name + " " + snapshotName;
                break;
        }
        Runtime.getRuntime().exec(cmd);
    }

    /**
     * 创建快照
     */
    public void createSnapshot(String name, String snapshotName) {
        snapshotManger("creat", name, snapshotName);
        log.info("虚拟机" + name + "成功创建快照" + snapshotName);
    }

    /**
     * 删除快照
     */
    public void deleteSnapshot(String name, String snapshotName) {
        snapshotManger("delete", name, snapshotName);
        log.info("虚拟机" + name + "成功删除快照" + snapshotName);
    }

    /**
     * 启动快照
     */
    public void revertSnapshot(String name, String snapshotName) {
        snapshotManger("revert", name, snapshotName);
        log.info("虚拟机" + name + "成功切换快照" + snapshotName);
    }

    /**
     * getStoragePoolList
     */
    @SneakyThrows
    public List<Storagepool> getStoragePoolList() {
        String[] pools = LibvirtUtils.getConnection().listStoragePools();
        String[] definedPools = LibvirtUtils.getConnection().listDefinedStoragePools();
        log.info("pools" + Arrays.toString(pools) + "definedPools" + Arrays.toString(definedPools));
        List<Storagepool> storagePoolList = new ArrayList<>();
        for (String pool : pools) storagePoolList.add(getStoragePool(pool));
        for (String pool : definedPools) storagePoolList.add(getStoragePool(pool));
        return storagePoolList;
    }

    /**
     * getStoragePool ByName
     */
    @SneakyThrows
    public Storagepool getStoragePool(String name) {
        StoragePool storagePool = LibvirtUtils.getConnection().storagePoolLookupByName(name);
        StoragePoolInfo info = storagePool.getInfo();
        return Storagepool.builder()
                .name(name)     // 名称
                .type("qcow2")  // 类型
                .capacity((int) (info.capacity / 1024.00 / 1024.00 / 1024.00))     // GB 容量
                .available((int) (info.available / 1024.00 / 1024.00 / 1024.00))   // GB 可用容量
                .allocation((int) (info.allocation / 1024.00 / 1024.00 / 1024.00)) // GB 已用容量
                .usage(((int) ((info.allocation / 1024.00 / 1024.00 / 1024.00) / (info.capacity / 1024.00 / 1024.00 / 1024.00) * 100)) + "%") // 使用率(%)
                .state(info.state.toString())                // 状态
                .xml(storagePool.getXMLDesc(0))        // 描述xml
                .build();
    }

    /**
     * 删除StoragePool ByName
     */
    @SneakyThrows
    public void deleteStoragePool(String name) {
        StoragePool storagePool = LibvirtUtils.getConnection().storagePoolLookupByName(name);
        for (String pool : LibvirtUtils.getConnection().listStoragePools())
            if (pool.equals(name)) storagePool.destroy();
        for (String pool : LibvirtUtils.getConnection().listDefinedStoragePools())
            if (pool.equals(name)) storagePool.undefine();
    }

    /**
     * 创建Storagepool  >>>>>> url必须存在
     */
    @SneakyThrows
    public boolean createStoragepool(String name, String url) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\n" +
                "<pool type=\"dir\">\n" +
                "    <name>" + name + "</name> \n" +       // 名称必须唯一
                "    <source>\n" +
                "    </source>\n" +
                "    <target>\n" +
                "        <path>" + url + "</path> \n" +                     // StoragePool 在宿主机的路径
                "        <permissions> \n" +                                // 权限
                "            <mode>0711</mode>\n" +
                "            <owner>0</owner>\n" +
                "            <group>0</group>\n" +
                "        </permissions>\n" +
                "    </target>\n" +
                "</pool>";
        return LibvirtUtils.getConnection().storagePoolCreateXML(xml, 0) == null ? false : true;
    }


    @SneakyThrows
    public static void main(String[] args) {
        LibvirtService libvirtService = new LibvirtService();


//        for (Virtual virtual : libvirtService.getVirtualList()) System.out.println(virtual);

//        libvirtService.createSnapshot("ubuntu14.04", "sna2");
//        libvirtService.deleteSnapshot("ubuntu14.04", "sna3");
//        libvirtService.revertSnapshot("ubuntu14.04", "sna2");
//        System.out.println(libvirtService.getSnapshotListByName("ubuntu14.04"));


//        System.out.println(libvirtService.createStoragepool("bsy", "/home/bsy"));
//        for (Storagepool storagePool : libvirtService.getStoragePoolList()) System.out.println(storagePool);
//        libvirtService.deleteStoragePool("bsy");
//        for (Storagepool storagePool : libvirtService.getStoragePoolList()) System.out.println(storagePool);

//        System.out.println(libvirtService.getNetState());

    }

}
