package webapp.SA2_final.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webapp.SA2_final.Model.Administrador;
import webapp.SA2_final.Repository.AdministradorRepository;
import webapp.SA2_final.Repository.VerificaCadastroAdmRepository;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministradorController {
//atributos
boolean acessoInternoAdm = false;

    @Autowired
    private AdministradorRepository ar;

    @Autowired
    private VerificaCadastroAdmRepository vcar;
    
//métodos  
    @PostMapping("cadastrar-adm")
    public ModelAndView cadastroAdmBD(Administrador adm) {

        boolean verificaCpf = vcar.existsById(adm.getCpf()) ;

        ModelAndView mv = new ModelAndView("login/login-adm");

        if(verificaCpf){
            ar.save(adm);
            String mensagem = "Cadastro Realizado com sucesso";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "verde");
        }else{
            String mensagem = "Cadastro Não Realizado";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }
         
        return mv;
    }

    @PostMapping("acesso-adm")
    public ModelAndView acessoAdmLogin(@RequestParam String cpf,
                                       @RequestParam String senha,
                                       RedirectAttributes attributes) {
        ModelAndView mv =  new ModelAndView("redirect:/interna-adm");//página interna de acesso
        
        boolean acessoCPF = cpf.equals(ar.findByCpf(cpf).getCpf());
        boolean acessoSenha = senha.equals(ar.findByCpf(cpf).getSenha());
        
        if(acessoCPF && acessoSenha){
            String mensagem = "Login Realizado com sucesso";
            System.out.println(mensagem);
            acessoInternoAdm = true;
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "verde");
        }else{
            String mensagem = "Login Não Efetuado";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
            mv.setViewName("redirect:/login-adm");
        }
        return mv;
    }

    @GetMapping("/interna-adm")
    public String acessoPageInternaAdm() {
        String acesso = "";
        ModelAndView mv = new ModelAndView();
        if (acessoInternoAdm) {
            System.out.println("Acesso Permitido");
            acesso = "interna/interna-adm";

        } else{
            String mensagem = "Acesso não Permitido - faça Login";
            System.out.println(mensagem);
            acesso = "login/login-adm";
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho"); 
        }

        return acesso;
    }

    @GetMapping("/cadastro-patrimonio")
    public String acessoPageCadastropatrimonio() {
        String acesso = "";
        ModelAndView mv = new ModelAndView();
        if (acessoInternoAdm) {
            System.out.println("Acesso Permitido");
            acesso = "cadastro/cadastro-patrimonio";

        } else{
            String mensagem = "Acesso não Permitido - faça Login";
            System.out.println(mensagem);
            acesso = "cadastro/cadastro-patrimonio";
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho"); 
        }

        return acesso;
    }
        @GetMapping("/movimentar-patrimonio2")
    public String acessoPageCadastroProfessor() {
        String acesso = "";
        ModelAndView mv = new ModelAndView();
        if (acessoInternoAdm) {
            System.out.println("Acesso Permitido");
            acesso = "cadastro/movimentar-patrimonio";

        } else{
            String mensagem = "Acesso não Permitido - faça Login";
            System.out.println(mensagem);
            acesso = "cadastro/movimentar-patrimonio";
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho"); 
        }

        return acesso;
    }

    @GetMapping("/index")
    public String acessoPageIndex() {
        String acesso = "";
        ModelAndView mv = new ModelAndView();
        if (acessoInternoAdm) {
            System.out.println("Acesso Permitido");
            acesso = "/index";
            acessoInternoAdm = false;
        } else{
            String mensagem = "Acesso não Permitido - faça Login";
            System.out.println(mensagem);
            acesso = "/index";
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho"); 
        }

        return acesso;
    }
    
    
    
}
