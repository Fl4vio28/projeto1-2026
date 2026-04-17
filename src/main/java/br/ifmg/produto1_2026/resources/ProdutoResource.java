package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.ProdutoDTO;
import br.ifmg.produto1_2026.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produtos", description = "Essa API é responsavel por gerenciar produtos na plataforma")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(produces = "application/json")
    @Operation(
            summary = "Endpoint para inserir um produto",
            description = "A plataforma precisa disponibilizar um cadastro e produtos ....",
            responses = {
                    @ApiResponse(description = "Lista retornada com sucesso", responseCode = "200"),
                    @ApiResponse(description = "Erro interno", responseCode = "500")
            }
    )
    public ResponseEntity<Page<ProdutoDTO>> produtos(Pageable pageable) {

        Page<ProdutoDTO> produtos = produtoService.findAll(pageable);
        return ResponseEntity.ok().body(produtos);
    };

    @GetMapping(value = "/{id}",produces = "application/json")
    @Operation(
            summary = "Endpoint para inserir um produto",
            description = "A plataforma precisa disponibilizar um cadastro e produtos ....",
            responses = {
                    @ApiResponse(description = "Retorna a informação pesquisada por 10", responseCode = "200"),
                    @ApiResponse(description = "Informação não encontrada", responseCode = "404")
            }
    )
    public ResponseEntity<ProdutoDTO> produto(@PathVariable Long id){


        ProdutoDTO dto = produtoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(produces = "application/json")
    @Operation(
            summary = "Endpoint para inserir um produto",
            description = "A plataforma precisa disponibilizar um cadastro e produtos ....",
            responses = {
                    @ApiResponse(description = "Registro criado", responseCode = "201",content = {}),
                    @ApiResponse(description = "Registro mal-feito", responseCode = "400"),
                    @ApiResponse(description = "Não autorizado", responseCode = "401"),
                    @ApiResponse(description = "Proibido no seu perfil", responseCode = "403"),
                    @ApiResponse(description = "Erro ao processar", responseCode = "422"),
                    @ApiResponse(description = "Erro interno no servidor", responseCode = "500")
            }
    )
    public ResponseEntity<ProdutoDTO> insert(@RequestBody ProdutoDTO dto){

        //inserindo mo BD e pegando o objeto inserido
        ProdutoDTO retorno = produtoService.insert(dto);

        //criando um link para acessar a produto criada
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(retorno.getId()).toUri();

        //enviando a produto criada
        return ResponseEntity.created(location).body(retorno);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Endpoint para apagar um produto",
            description = "A plataforma precisa disponibilizar um cadastro e produtos ....",
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "204", content = {}),
                    @ApiResponse(description = "Registro mal-feito", responseCode = "400"),
                    @ApiResponse(description = "Não autorizado", responseCode = "401"),
                    @ApiResponse(description = "Proibido no seu perfil", responseCode = "403"),
                    @ApiResponse(description = "Não encontrado", responseCode = "404"),
                    @ApiResponse(description = "Erro ao processar", responseCode = "422"),
                    @ApiResponse(description = "Erro interno no servidor", responseCode = "500")
            }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id){


        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping(value ="/{id}", produces = "application/json")
    @Operation(
            summary = "Endpoint para atualizar um produto",
            description = "A plataforma precisa disponibilizar um cadastro e produtos ....",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Registro mal-feito", responseCode = "400"),
                    @ApiResponse(description = "Não autorizado", responseCode = "401"),
                    @ApiResponse(description = "Proibido no seu perfil", responseCode = "403"),
                    @ApiResponse(description = "Não encontrado", responseCode = "404"),
                    @ApiResponse(description = "Erro ao processar", responseCode = "422"),
                    @ApiResponse(description = "Erro interno no servidor", responseCode = "500")
            }
    )
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO dto){

        ProdutoDTO retorno = produtoService.update(id,dto);
        return ResponseEntity.ok().body(retorno);
    }

}