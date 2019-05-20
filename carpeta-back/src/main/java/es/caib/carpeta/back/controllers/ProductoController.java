package es.caib.carpeta.back.controllers;

import es.caib.carpeta.core.service.ProductoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class ProductoController {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    private ProductoService productoService;

    @RequestMapping(value="/productos")
    public ModelAndView productos() {

        ModelAndView mav = new ModelAndView("productos");

        String now = (new Date()).toString();

        mav.addObject("now", now);
        mav.addObject("productos", productoService.getProductos());

        return mav;
    }

    @RequestMapping(value="/incrementar/{porcentaje}", method = RequestMethod.GET)
    public ModelAndView incrementarPrecio(@PathVariable("porcentaje") Integer porcentaje ) {

        productoService.incrementarPrecio(porcentaje);

        return productos();

    }
}
