package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 用aop来管理事务,发生任何一次就回滚
 * @author user
 *
 */
@Configuration
@ImportResource(locations={"classpath:application-txaop.xml"})
public class TxAopConfig {

}
