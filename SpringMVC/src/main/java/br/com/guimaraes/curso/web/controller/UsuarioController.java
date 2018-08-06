package br.com.guimaraes.curso.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.guimaraes.curso.dao.UsuarioDao;
import br.com.guimaraes.curso.domain.TipoSexo;
import br.com.guimaraes.curso.domain.Usuario;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDao dao;

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public ModelAndView listaTodos(ModelMap model) {
        model.addAttribute("usuarios", dao.getTodos());
        return new ModelAndView("/user/list", model);
    }

    @GetMapping("/cadastro")
    public String cadastro(@ModelAttribute("usuario") Usuario usuario, ModelMap model) {
        model.addAttribute("sexos", TipoSexo.values());
        return "/user/add";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/user/add";
        }
        dao.salvar(usuario);
        attr.addFlashAttribute("message", "Usuário salvo com sucesso.");
        return "redirect:/usuario/todos";
    }

    @GetMapping("/update/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {
        Usuario usuario = dao.getId(id);
        model.addAttribute("usuario", usuario);
        return new ModelAndView("/user/add", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/user/add");
        }
        dao.editar(usuario);
        attr.addFlashAttribute("message", "Usuário alterado com sucesso.");
        return new ModelAndView("redirect:/usuario/todos");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
        dao.excluir(id);
        attr.addFlashAttribute("message", "Usuário excluído com sucesso.");
        return "redirect:/usuario/todos";
    }


}
