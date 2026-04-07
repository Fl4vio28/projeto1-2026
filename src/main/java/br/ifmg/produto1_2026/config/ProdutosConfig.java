package br.ifmg.produto1_2026.config;

import br.ifmg.produto1_2026.service.AtivacaoClienteService;
import br.ifmg.produto1_2026.util.NotificacaoEmail;
import br.ifmg.produto1_2026.util.NotificacaoSMS;
import br.ifmg.produto1_2026.util.Notificador;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
class ProdutosConfig {

    @Bean
    public Notificador notificacaoEmail(){
        NotificacaoEmail notificacaoEmail = new NotificacaoEmail("smptp.googlemail.com");
        notificacaoEmail.setCaixaAlta(true);
        return notificacaoEmail;
    }
    //@Primary - desanbigua o beans, indicando qual objeto o spring deve usar
    @Bean
    public Notificador notificacaoSMS(){
        NotificacaoSMS notificacaoSMS = new NotificacaoSMS();
        notificacaoSMS.setCaixaAlta(true);
        return notificacaoSMS;
    }
    /*
    @Bean
    public AtivacaoClienteService atividacaoClienteService(Notificador notificador){
            AtivacaoClienteService ativacaoClienteService = new AtivacaoClienteService(notificador);
            return ativacaoClienteService;
    }
    */
}