package com.rortegag.funwithflags.service.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "storage")
public class StorageProperties
{

    private String location = "images";

    public String getLocation() 
    {
        return location;
    }

    public void setLocation(String location) 
    {
        this.location = location;
    }

}
