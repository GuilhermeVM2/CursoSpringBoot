package webapp.SA2_final.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webapp.SA2_final.Model.Patrimonio;
import webapp.SA2_final.Repository.PatrimonioRepository;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PatrimonioController {
    // atributos
    boolean acessoInternoPatrimonio = false;

        @Autowired
    private PatrimonioRepository patrimonioRepository;

    // Método para exibir página de movimentação de patrimônio
    @GetMapping("/movimentar-patrimonio")
    public ModelAndView mostrarFormularioMovimentacao() {
        ModelAndView mv = new ModelAndView("movimentar-patrimonio");
        mv.addObject("patrimonio", new Patrimonio());
        return mv;
    }

    // Método para processar a movimentação de patrimônio
    @PostMapping("/movimentar_patrimonio")
    public ModelAndView moverPatrimonio(@ModelAttribute("patrimonio") Patrimonio patrimonio) {
        ModelAndView mv = new ModelAndView("movimentar-patrimonio");

        // Busca o patrimônio pelo código
        Patrimonio patrimonioEncontrado = patrimonioRepository.findByCodigo(patrimonio.getCodigo());

        if (patrimonioEncontrado != null) {
            // Atualiza o bloco do patrimônio
            patrimonioEncontrado.setBloco(patrimonio.getBloco());
            patrimonioRepository.save(patrimonioEncontrado);
            mv.addObject("mensagem", "Patrimônio movido com sucesso para o bloco " + patrimonio.getBloco());
        } else {
            mv.addObject("mensagem", "Patrimônio não encontrado");
        }

        return mv;
    }

    // métodos
    @PostMapping("cadastrar-patrimonio")
    public ModelAndView cadastroPatrimonioBD(Patrimonio patrimonio) {
        ModelAndView mv = new ModelAndView("redirect:/interna-adm");

        if (patrimonio.getDataCriacao() == null) {
            patrimonio.setDataCriacao(LocalDateTime.now());
        }

        patrimonioRepository.save(patrimonio);
        String mensagem = "Cadastro de Patrimônio Realizado com sucesso";
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.addObject("classe", "verde");

        return mv;
    }

    @PostMapping("acesso-patrimonio")
    public ModelAndView acessoPatrimonioLogin(@RequestParam String codigo,
                                              @RequestParam String tipo,
                                              @RequestParam String bloco,
                                              @RequestParam String sala,
                                              RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/interna-patrimonio");// página interna de acesso

        Patrimonio patrimonio = patrimonioRepository.findByCodigo(codigo);

        if (patrimonio != null && patrimonio.getCodigo().equals(codigo) && patrimonio.getTipo().equals(tipo)
            && patrimonio.getBloco().equals(bloco) && patrimonio.getSala().equals(sala)) {
            String mensagem = "Acesso Realizado com sucesso";
            System.out.println(mensagem);
            acessoInternoPatrimonio = true;
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "verde");
        } else {
            String mensagem = "Acesso Não Efetuado";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
            mv.setViewName("redirect:/login-patrimonio");
        }
        return mv;
    }

    @GetMapping("/interna_adm")
    public String acessoPageInternaPatrimonio() {
        String acesso;
        if (acessoInternoPatrimonio) {
            System.out.println("Acesso Permitido");
            acesso = "interna/interna-adm";
        } else {
            String mensagem = "Acesso não Permitido - faça Login";
            System.out.println(mensagem);
            acesso = "login/login-adm";
        }
        return acesso;
    }

    @GetMapping("/bloco_a")
    public ModelAndView listarPatrimoniosBlocoA() {
        ModelAndView mv = new ModelAndView("bloco_a");
        List<Patrimonio> patrimoniosBlocoA = patrimonioRepository.findByBloco("BLOCO A");
        mv.addObject("patrimonios", patrimoniosBlocoA);
        return mv;
    }

    public List<Patrimonio> listarPatrimonios() {
        return (List<Patrimonio>) patrimonioRepository.findAll();
    }

    // public List<Patrimonio> listarPatrimonios() {
    //     return (List<Patrimonio>) patrimonioRepository.findAll();
    // }
}
