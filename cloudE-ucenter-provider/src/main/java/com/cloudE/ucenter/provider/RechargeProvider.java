package com.cloudE.ucenter.provider;

import com.alibaba.fastjson.JSON;
import com.cloudE.dto.BaseResult;
import com.cloudE.entity.User;
import com.cloudE.pay.client.ApplePayClient;
import com.cloudE.ucenter.manager.UserManager;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author vangao1989
 * @date 2017年7月26日
 */
@RestController
public class RechargeProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(RechargeProvider.class);

    @Resource
    private UserManager userManager;
    @Resource
    private ApplePayClient applePayClient;


    @HystrixCommand(fallbackMethod = "rechargeFallback")
    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    public BaseResult<Boolean> recharge(@RequestParam @ApiParam(name = "userId",value = "用户名") Long userId,
                                        @RequestParam @ApiParam(name = "amount",value = "金额") Double amount,
                                        @RequestParam @ApiParam(name = "type",value = "充值方式：1.支付宝|2.微信支付") String type) {
        User user = userManager.getUserByUserId(userId);
        LOGGER.info("user {} recharge {},type:{}", user.getUsername(), amount, type);
        BaseResult<Boolean> baseResult = applePayClient.recharge(userId, amount);
        LOGGER.info("user {} recharge  res:{}", user.getUsername(), JSON.toJSONString(baseResult));
        return baseResult;

    }

    private BaseResult<Boolean> rechargeFallback(Long useId, Double amount, String type, Throwable throwable) {
        LOGGER.error("user:{} recharge,amount:{},type:{}, fail:{}", useId, amount, type, throwable.getMessage(), throwable);
        return new BaseResult<>(false, throwable.getMessage());
    }


}
