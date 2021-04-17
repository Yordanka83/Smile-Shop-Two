package softuni.smileShop.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class interceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String title = "Smile shop";

        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        } else {
            modelAndView.addObject("title", title + modelAndView.getViewName());
        }
    }

}


