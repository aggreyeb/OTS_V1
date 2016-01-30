/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.Servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MEA
 */
public class SessionFilter implements Filter {

    private FilterConfig filterConfig;
    
        public FilterConfig getFilterConfig()
        {
             return this.filterConfig;
        }
        public void setFilterConfig (FilterConfig filterConfig)
        {
        this.filterConfig = filterConfig;
        }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       HttpSession session =null;
            HttpServletRequest req=(HttpServletRequest)request;
            HttpServletResponse res=(HttpServletResponse)response;
            try
            {
            session=req.getSession(false);
            if(session==null)
            {
            res.sendRedirect("/timeout.jsp"); //If the Active session is null ,we redirect to the timeout.jsp 
            }
            chain.doFilter(request, response); 
            }
            catch (IOException io) {
            System.out.println ("IOException raised in SimpleFilter");
            } catch (ServletException se) {
            System.out.println ("ServletException raised in SimpleFilter");
            }
    }

    @Override
    public void destroy() {
      
    }

   
    
}
