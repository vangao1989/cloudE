package cloudE.zuul.server.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author vangao1989
 * @Description: filter 前置过滤器
 * @date 2017年7月26日
 */
public class AccessFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();
            String requestURL = request.getRequestURL().toString();
            logger.info("{} AccessFilter request to {}", request.getMethod(), requestURL);
            if (true) {
                ctx.setRequest(request);
                ctx.setSendZuulResponse(true);// 对该请求进行路由
                ctx.setResponseStatusCode(200);
                ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
            } else {
                ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
                ctx.setResponseStatusCode(401);// 返回错误码
                ctx.setResponseBody("{\"result\":\"request fail!\"}");// 返回错误内容
                ctx.set("isSuccess", false);
            }

        } catch (Throwable throwable) {
            logger.error("AccessFilter fail", throwable);
        }
        return null;

    }

    @Override
    public boolean shouldFilter() {
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public int filterOrder() {
        return 0;// 优先级为0，数字越大，优先级越低
    }

    @Override
    public String filterType() {
        return "pre";// 前置过滤器
    }
}
