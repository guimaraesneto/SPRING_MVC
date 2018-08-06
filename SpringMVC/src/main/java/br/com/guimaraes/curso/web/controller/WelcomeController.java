package br.com.guimaraes.curso.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        //return "welcome";
        return "redirect:/usuario/todos";
    }

    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    public ModelAndView teste() {
        ModelAndView view = new ModelAndView("welcome");
        view.addObject("teste", "Olá, eu sou o spring MVC.");
        return view;
    }
}
