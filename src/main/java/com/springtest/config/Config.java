package com.springtest.config;

import com.springtest.bean.MyImport;
import com.springtest.bean.MyImportSelector;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.springtest")
@Import({MyImport.class, MyImportSelector.class})
public class Config {
}
