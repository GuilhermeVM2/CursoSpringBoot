package webapp.SA2_final.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
    //classe para criação das rotas de navegação

    @GetMapping("/home")
    public ModelAndView acessoHomePage() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    @GetMapping("")
    public ModelAndView acessoHomePage2() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    
    @GetMapping("/login-adm")
    public ModelAndView acessoLoginAdm() {
        ModelAndView mv = new ModelAndView("login/login-adm");
        return mv;
    }

    @GetMapping("/login-aluno")
    public ModelAndView acessoLoginAluno() {
        ModelAndView mv = new ModelAndView("login/login-aluno");
        return mv;
    }
    @GetMapping("/cadastro-adm")
    public String acessoCadastroAdm() {
        return "cadastro/cadastro-adm";
    }

    @GetMapping("/bloco_a2")
    public ModelAndView acessoBlocoA() {
        ModelAndView mv = new ModelAndView("login/bloco_a");
        return mv;
    }

    // @GetMapping("/login-b")
    // public String acessoLoginB() {
    //     return "login/bloco-b";
    // }

    // @GetMapping("/login-c")
    // public String acessoLoginC() {
    //     return "login/bloco-c";
    // }

    // @GetMapping("/login-d")
    // public String acessoLoginD() {
    //     return "login/bloco-d";
    // }
    
    // @GetMapping("/login/deposito")
    // public String acessoDeposito() {
    //     return "login/deposito";
    // }

    // @GetMapping("/login/descarte")
    // public String acessoDescarte() {
    //     return "login/descarte";
    // }
    
    
}
