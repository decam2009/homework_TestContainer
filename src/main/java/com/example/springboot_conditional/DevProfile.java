package com.example.springboot_conditional;

public class DevProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "This is development profile";
    }
}
