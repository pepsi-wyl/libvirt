package com.example.libvirt.pojo;

import lombok.*;

@Builder
@Getter
@ToString
public class Storagepool {
    private String name;    // 名称
    private String type;    // 类型

    private int capacity;   // GB 容量
    private int available;  // GB 可用容量
    private int allocation; // GB 已用容量
    private String usage;   // 使用率(%)

    private String state;   // 状态
    private String xml;     // 描述xml
 }
