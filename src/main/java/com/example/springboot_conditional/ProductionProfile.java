package com.example.springboot_conditional;

public class ProductionProfile implements SystemProfile{
    @Override
    public String getProfile() {
        return  "This is production profile";
    }
}
