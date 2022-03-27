package org.lucius.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @NacosInjected
    private NamingService namingService;

    @RequestMapping("/getList")
    public List<Instance>  getList() throws NacosException {
        List<Instance> allInstances = namingService.getAllInstances("nacos-provider");
        return allInstances;

    }
}
