package com.rortegag.funwithflags.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.rortegag.funwithflags.service.storage.StorageProperties;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageConfiguration {}