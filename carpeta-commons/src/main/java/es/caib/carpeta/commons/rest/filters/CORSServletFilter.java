package es.caib.carpeta.commons.rest.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filtre per aplicar als recursos com openapi.json que es puguin obtenir via peticions CORS.
 *
 * @author areus
 */
@WebFilter(urlPatterns = "/openapi.json")
public class CORSServletFilter extends HttpFilter {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // si és una cridada CORS
        if (request.getHeader("Origin") != null) {
            response.addHeader("Access-Control-Allow-Origin", "*");
        }

        chain.doFilter(request, response);
    }
}
