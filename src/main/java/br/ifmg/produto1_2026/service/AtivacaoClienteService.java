package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.entities.Produto;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.util.Notificador;
import br.ifmg.produto1_2026.util.NotificaçãoEmail;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class AtivacaoClienteService {


    private Notificador notificador;
    public AtivacaoClienteService(Notificador notificador) {
        System.out.println("Iniciando AtivaçãoClienteService");
    }
    public void ativar(Usuario usuario, String mensagem) {
        // usuario ativo(); simular ativar o usuario.
        notificador.notificar(usuario,mensagem);
    }

    @PostConstruct
    public void init() {
        System.out.println("Metodo executado depois do construtor");
    }

    @PreDestroy
    public void  destroy() {
        System.out.println("Metodo executado ao destruir o objeto");
    }
}
