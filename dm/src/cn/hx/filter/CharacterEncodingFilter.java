package cn.hx.filter;

import java.io.IOException;
import javax.servlet.*;

public class CharacterEncodingFilter implements Filter {
    String encodingString = "UTF-8";

    @Override
    public void destroy() {
        encodingString = "";
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encodingString = filterConfig.getInitParameter("encoding").trim();
        if (encodingString == null) {
            encodingString = "UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        if (encodingString != null) {
            arg0.setCharacterEncoding(encodingString);
        }
        arg2.doFilter(arg0, arg1);
    }
}