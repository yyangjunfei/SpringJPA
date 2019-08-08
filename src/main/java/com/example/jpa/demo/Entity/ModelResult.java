package com.example.jpa.demo.Entity;

import lombok.Data;

@Data
public class ModelResult<String> {
    private  String Data;
    private  String Code;
    private  String Msg;
}
