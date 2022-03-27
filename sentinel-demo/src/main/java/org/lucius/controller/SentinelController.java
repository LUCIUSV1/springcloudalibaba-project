package org.lucius.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lucius
 * @Date: 2022-03-27 12:54
 */
@RestController
public class SentinelController {


    /***
     * 抛出异常的方式定义资源
     * @return
     */
    @RequestMapping("index")
    public String sentinelDemo(){
        try(Entry entry = SphU.entry("index")){

            return "sentinel 怎么说!"+System.currentTimeMillis();
        }catch (Exception e){
            e.printStackTrace();
            return "系统繁忙！";
        }

    }

    /***
     * 布尔值方式定义资源
     * @return
     */
    @RequestMapping("index2")
    public String sentinelDemo2(){

        if(SphO.entry("index2")){
            try{
                return "sentinel子 怎么说!"+System.currentTimeMillis();
            }finally {
                SphO.exit();
            }
        }else{
            return "系统繁忙！";
        }


    }

    /***
     * 注解方式定义资源
     * 定义资源名称，   限流后的操作
     * @return
     */
    @SentinelResource(value = "index3",blockHandler = "bh1")
    @RequestMapping("index3")
    public String sentinelDemo3(){


        if(SphO.entry("index3")){
            try{
                return "sentinel子 怎么说!"+System.currentTimeMillis();
            }finally {
                SphO.exit();
            }
        }else{
            return "系统繁忙！";
        }
    }


    /***
     * 方法被限流后调用
     * @return
     */
    public String bh1(BlockException e){
        e.printStackTrace();
        return "限流了 兄弟";
    }

    /**
     *
     * 定义限流规则
     * @PostConstruct 该注解表明方法是执行完构造方法所执行的方法，用来定义和加载限流规则
     */
//    @PostConstruct
//    public void initFlowRules(){
//        List<FlowRule> rules = new ArrayList<>(); //定义限流规则集合
//        FlowRule flowRule = new FlowRule();// 定义限流规则资源
//        flowRule.setResource("index");/// 设置资源名称
//        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);// 设置限流类型
//        flowRule.setCount(2);//设置QPS阈值，即最多可通过的请求数量
//        rules.add(flowRule);//添加规则到集合
//        FlowRuleManager.loadRules(rules);//加载规则集合
//
//    }
}
