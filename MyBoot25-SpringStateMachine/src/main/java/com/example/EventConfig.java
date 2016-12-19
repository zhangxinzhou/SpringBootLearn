package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.annotation.*;


@WithStateMachine
public class EventConfig {

	private Logger log = LoggerFactory.getLogger(getClass());

    @OnTransition(target = "UNPAID")
    public void create() {
    	log.info("订单创建，待支付");
    }

    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay() {
    	log.info("用户完成支付，待收货");
    }

    @OnTransitionStart(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void payStart() {
    	log.info("用户完成支付，待收货: start");
    }

    @OnTransitionEnd(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void payEnd() {
    	log.info("用户完成支付，待收货: end");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive() {
    	log.info("用户已收货，订单完成");
    }
    
    
	
}
