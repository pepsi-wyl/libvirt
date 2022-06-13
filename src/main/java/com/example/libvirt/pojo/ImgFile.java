package com.example.libvirt.pojo;

import lombok.*;

@Builder
@Getter
@ToString
public class ImgFile {
    private String name;
    private String size;
}
