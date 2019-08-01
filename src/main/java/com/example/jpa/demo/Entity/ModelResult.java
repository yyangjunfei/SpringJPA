package com.example.jpa.demo.Entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModelResult<String> {
    private  String Data;
    private  String Code;
    private  String Msg;
}
