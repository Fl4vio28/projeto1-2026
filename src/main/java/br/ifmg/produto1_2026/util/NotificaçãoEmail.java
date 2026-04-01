package br.ifmg.produto1_2026.util;

import br.ifmg.produto1_2026.entities.Usuario;
import org.springframework.stereotype.Component;

@Component
public class NotificaçãoEmail implements Notificador {

    private boolean caixaAlta;
    private String servidorSmpt;

    public NotificaçãoEmail(String servidorSmpt) {
        System.out.println("NotificacaoEmail criado com sucesso!");
        this.servidorSmpt = servidorSmpt;
    }

    public void notificar(Usuario usuario, String mensagem){
        // usuario ativo(); simulando ativar o usuario
        if (caixaAlta) {
            mensagem = mensagem.toUpperCase();
        }

        System.out.printf("Notificando %s através do e-mail %s no servidor %s : %s\n", usuario.getNome(),usuario.getEmail(),servidorSmpt,mensagem);
    }
}
