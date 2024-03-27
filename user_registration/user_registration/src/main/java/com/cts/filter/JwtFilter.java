package com.cts.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String header = httpServletRequest.getHeader("authorization");

        if(header == null || !header.startsWith("Bearer ")){
            throw new ServletException("Missing or invalid authentication header");
        }
        else {

            String token = header.substring(7);
            Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
            httpServletRequest.setAttribute("username",claims);
chain.doFilter(request,response);
        }
    }
}
