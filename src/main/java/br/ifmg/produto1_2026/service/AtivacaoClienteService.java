package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.entities.Produto;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.util.Notificador;
import br.ifmg.produto1_2026.util.NotificacaoEmail;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtivacaoClienteService {


//@Autowired <-- Forma 01 de injetar o bean
    //private Notificador notificador;

    private List<Notificador> notificadores;
    // Forma 02 de injetar o bean, agora no construtor
    @Autowired //<-- Forma 03, quando overload de construtores
    public AtivacaoClienteService(List<Notificador> notificadores) {
        System.out.println("Iniciando AtivaçãoClienteService");
        this.notificadores = notificadores;
    }
    public void ativar(Usuario usuario, String mensagem) {
        // usuario ativo(); simular ativar o usuario.
        //if (noficador != null);
        //notificador.notificar(usuario,mensagem);
        for (Notificador notificador : notificadores){
            notificador.notificar(usuario, mensagem);
        }
    }

    @PostConstruct
    public void init() {
        System.out.println("Metodo executado depois do construtor");
    }

    @PreDestroy
    public void  destroy() {
        System.out.println("Metodo executado ao destruir o objeto");
    }
/*
    public Notificador getNotificador() {
        return notificador;
    }
//@Autowired <-- Forma 04, no set
    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
    }

 */
}


