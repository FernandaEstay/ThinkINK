package filter;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Filtro implements Filter {
	public void init(FilterConfig fConfig) throws ServletException { }

	public void destroy() {	}

	public void doFilter(
		ServletRequest request, ServletResponse response, 
		FilterChain chain) throws IOException, ServletException {

		((HttpServletResponse)response).addHeader(
				"Access-Control-Allow-Origin", "*"
			);
		((HttpServletResponse)response).addHeader(
				"Access-Control-Allow-Credentials", "true"
			);
		((HttpServletResponse)response).addHeader(
				"Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD"
			);
		((HttpServletResponse)response).addHeader(
				"Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With"
			);
		chain.doFilter(request, response);
	}
}

