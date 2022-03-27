package org.lucius.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author: Lucius
 * @Date: 2022-03-27 15:01
 */
@Service
public class AsyncService {

    @Async
    public void doAsync(){
        System.out.println("开始调用");
        try {
            Thread.sleep(5000);
        }catch (Exception e){

        }
        System.out.println("完成调用");
    }
}
