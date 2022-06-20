package com.fanpay.rabbitmq;

/**
 * @author Leeko
 * @date 2022/3/3
 **/
public interface BindingConfigConstant {

    /************** remit out *****************/
    String REMIT_OUT_EXCHANGE = "remit-out-exchange";
    String REMIT_OUT_QUEUE = "remit-out-queue";
    String REMIT_OUT_ROUTING_KEY = "remit-out-routing-key";
    String REMIT_OUT_DEATH_QUEUE = "remit-out-death_queue";
    String REMIT_OUT_DEATH_ROUTING_KEY = "remit-out-death_routing-key";
    String DEATH_OUT_EXCHANGE = "death-out-exchange";
    /*************** remit out ****************/

    /************** remit in *****************/
    String REMIT_IN_EXCHANGE = "remit-in-exchange";
    String REMIT_IN_QUEUE = "remit-in-queue";
    String REMIT_IN_ROUTING_KEY = "remit-in-routing-key";
    String REMIT_IN_DEATH_QUEUE = "remit-in-death-queue";
    String REMIT_IN_DEATH_ROUTING_KEY = "remit-in-death-routing-key";
    String DEATH_IN_EXCHANGE = "death-in-exchange";
    /*************** remit in ****************/


}
