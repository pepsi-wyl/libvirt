package com.example.libvirt.pojo;

import lombok.*;

@Builder
@Getter
@ToString
public class Virtual {
    private int id;
    private String name;
    private String state;
}
