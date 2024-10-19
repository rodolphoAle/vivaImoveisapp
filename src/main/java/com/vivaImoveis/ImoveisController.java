package com.vivaImoveis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImoveisController {
    @RequestMapping("/cadastrarimoveis")
    public String form(){

        return "imoveis/formImoveis";
    }

}
