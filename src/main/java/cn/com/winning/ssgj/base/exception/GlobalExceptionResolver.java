package cn.com.winning.ssgj.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.base.exception
 * @date 2018-02-23 15:27
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object o, Exception ex) {
        boolean isAsync = req.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(req.getHeader("X-Requested-With").toString());

        if(isAsync) {
            //WebTransException是自定义异常
            if (ex instanceof SSGJException) {
                SSGJException ssgjException = (SSGJException) ex;
                printWrite(ssgjException.getResponseMsg(), resp);
                logger.warn("ajax warn - [{}]", ssgjException.getResponseCode()+":"+ssgjException.getResponseMsg());
            }else {
                ex.printStackTrace();
                printWrite("系统异常！", resp);
            }

            return new ModelAndView();
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/500");

        if(ex instanceof SSGJException)
            modelAndView.addObject("WebTransException", ex);
        else
            ex.printStackTrace();

        return modelAndView;
    }

    public void printWrite(String msg, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter pw = response.getWriter();
            pw.write(msg);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
