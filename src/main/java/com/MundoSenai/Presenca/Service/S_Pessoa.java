package com.MundoSenai.Presenca.Service;

import com.MundoSenai.Presenca.Model.M_Pessoa;
import com.MundoSenai.Presenca.Repository.R_Pessoa;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.sql.SQLException;
import java.time.LocalDate;

@Service
public class S_Pessoa {
    private static R_Pessoa r_pessoa;

    public S_Pessoa(R_Pessoa r_pessoa) {
        this.r_pessoa = r_pessoa;
    }

    public static M_Pessoa getPessoaLogin(String usuario, String senha) {
        usuario = NumberCleaner.cleanNumber(usuario);
        M_Pessoa retorno;
        if (usuario.equals("")) {
            return r_pessoa.findByUsuarioESenha(null, senha);
        } else {
            return r_pessoa.findByUsuarioESenha(Long.valueOf(usuario), senha);
        }
    }

    public static String cadastrarPessoa(String nome, String email, String cpf,
                                         String telefone, String data_nascimento,
                                         String senha, String conf_senha) {
        boolean cadastroValido = true;
        String mensagemRetorno = "";
        telefone = NumberCleaner.cleanNumber((telefone));

        if (!senha.equals(conf_senha)) {
            mensagemRetorno += "A Senha e a Confirmação de Senha devem ser iguais<br/>";
            cadastroValido = false;
        }
        if (!CpfValidator.validateCPF(cpf)) {
            mensagemRetorno += "CPF Inválido<br/>";
            cadastroValido = false;
        }
        if (nome == null || nome.trim() == "") {
            mensagemRetorno += "Deve ser informado o Nome<br/>";
            cadastroValido = false;
        }
        if ((email == null || email.trim() == "")
                && (NumberCleaner.cleanNumber(telefone) == null
                || NumberCleaner.cleanNumber(telefone).trim() == "")) {
            mensagemRetorno += "e-Mail ou Telefone precisa ser informado<br/>";
            cadastroValido = false;
        }
        if (cadastroValido) {
            M_Pessoa m_pessoa = new M_Pessoa();
            m_pessoa.setNome(nome);
            m_pessoa.setCpf(Long.valueOf(NumberCleaner.cleanNumber(cpf)));
            m_pessoa.setTelefone(Long.valueOf(NumberCleaner.cleanNumber(telefone)));
            if (telefone != null) {
                m_pessoa.setTelefone(Long.valueOf(telefone));
            } else {
                m_pessoa.setTelefone(null);
            }
            m_pessoa.setEmail(email);
            m_pessoa.setData_nasc(LocalDate.parse(data_nascimento));
            m_pessoa.setSenha(senha);
            try {
                r_pessoa.save(m_pessoa);
                mensagemRetorno += "Cadastro efetuado com sucesso";
            } catch (DataIntegrityViolationException e) {
                if (e.getMessage().contains(("u_cpf"))) {
                    mensagemRetorno += "O CPF informado ja foi cadastrado !";
                } else {
                    mensagemRetorno += "Erro ao cadastrar";
                }
            }



        }
        return mensagemRetorno;
}
}