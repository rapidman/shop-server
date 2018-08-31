package com.ajax.shop.web.filter;

import com.ajax.shop.data.BasketData;
import com.ajax.shop.web.controller.BasketController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 13.07.18
 */
public class SessionManagementFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if(httpServletRequest.getRequestURI().indexOf("/api/") != -1){
            HttpSession session = httpServletRequest.getSession(true);
            if(session.getAttribute(BasketController.BASKET_ATTR) == null){
                session.setAttribute(BasketController.BASKET_ATTR, new BasketData());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
