package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.service.AtivacaoClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/venda")
public class VendaResourse {
    private AtivacaoClienteService ativivacaoClienteService;

    public VendaResourse(AtivacaoClienteService atividacaoCliente) {
        this.ativivacaoClienteService= atividacaoCliente;
        System.out.println("Camada de resource executada.");


    }

    @PostMapping
            public ResponseEntity <String> insert() {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano");
        usuario.setTelefone("+5555555555");
        usuario.setEmail("fulano@gmail.com");
        ativivacaoClienteService.ativar(usuario, "ativado ...");

        return ResponseEntity.ok().body("Venda realizada.");
    }

}
