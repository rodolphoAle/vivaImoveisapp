package com.vivaImoveis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.models.Imoveis;
import com.repository.ImoveisRepository;

@Controller
public class ImoveisController {

    @Autowired
    private ImoveisRepository ir;

    @RequestMapping(value="/cadastrarimoveis",method =RequestMethod.GET)
    public String form(){

        return "imoveis/formImoveis";
    }
    @RequestMapping(value="/cadastrarimoveis",method =RequestMethod.POST)
    public String form(Imoveis imoveis){

        ir.save(imoveis);

        return "redirect:imoveis/formImoveis";
    }

}
