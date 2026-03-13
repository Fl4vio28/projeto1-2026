package br.ifmg.produto1_2026.service.exepition;

public class ErroNoBancoDeDados extends RuntimeException {
    public  ErroNoBancoDeDados(){}

    public ErroNoBancoDeDados(String messagem){
        super(messagem);
    }
}
