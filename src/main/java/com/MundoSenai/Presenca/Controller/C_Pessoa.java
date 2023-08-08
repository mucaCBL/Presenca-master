package com.MundoSenai.Presenca.Controller;

import com.MundoSenai.Presenca.Model.M_Pessoa;
import com.MundoSenai.Presenca.Service.S_Pessoa;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class C_Pessoa {
    @GetMapping("/")
    public String helloWorld(){
        return "Login/login";
    }

    @PostMapping("/")
    public String postLogin(@RequestParam("usuario") String usuario,
                            @RequestParam("senha") String senha, HttpSession session){
        session.setAttribute("usuario", S_Pessoa.getPessoaLogin(usuario,senha));
        if(session.getAttribute("usuario") == null){
            return "Login/login";
        }else{
            return "redirect:/Home";
        }
    }

    @ModelAttribute("usuario")
    public M_Pessoa getUsuario(HttpSession session){
        return (M_Pessoa) session.getAttribute("usuario");
    }

    @GetMapping ("/Home")
    public String getUsuario(@ModelAttribute("usuario") String usuario) {
        if (usuario != null) {
            return "Home/home";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/cadastro")
    public String getCadastro(){
        return "Pessoa/cadastro";
    }

    @PostMapping("/cadastro")
    public String postCadastro(@RequestParam("nome") String nome,
                               @RequestParam("email") String email,
                               @RequestParam("cpf") String cpf,
                               @RequestParam("telefone") String telefone,
                               @RequestParam("data_nasc") String data_nascimento,
                               @RequestParam("senha") String senha,
                               @RequestParam("confsenha") String conf_senha,
                               Model model){
        String mensagem = S_Pessoa.cadastrarPessoa(nome, email, cpf, telefone, data_nascimento, senha, conf_senha);
        model.addAttribute("mensagem",mensagem);
        model.addAttribute("nome",nome);
        model.addAttribute("email",email);
        model.addAttribute("cpf",cpf);
        model.addAttribute("telefone",telefone);
        model.addAttribute("data_nasc",data_nascimento);
        return "Pessoa/cadastro";
    }
}