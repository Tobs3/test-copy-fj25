package de.hhbk.immoweg24.web.filter;

import java.io.IOException;
import java.io.Serializable;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebFilter(filterName = "BackendAreaFilter", urlPatterns ={"/backend/*"})
public class BackendAreaFilter implements Filter, Serializable
{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException
    { 
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession websession = req.getSession(true); 
        
        if (websession.getAttribute("MyLoginObject") != null && (boolean) websession.getAttribute("MyLoginObject") == true)
        { 
            chain.doFilter(request, response);         
        }
        else
        { 
            HttpServletResponse resp = (HttpServletResponse) response; 
            resp.sendRedirect("../login.xhtml");             
        }        
    } 

}