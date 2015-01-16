package com.nd.rock.server.controller.manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manager")
public class AppController {

	
    public AppController(){
        System.out.println("init app controller");
    }

    /**
     * Ä£ºý²éÑ¯APP
     */
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String listAppsLike(HttpServletRequest request, ModelMap modelMap) {

        return "index";
    }
}
