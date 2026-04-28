package br.ifmg.produto1_2026.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailDTO {

    @Schema(description = "Endereço de e-mail do destinatário")
    @NotBlank
    @Email
    private String to;

    @Schema(description = "Assunto do e-mail")
    @NotBlank
    private String subject;

    @Schema(description = "Conteúdo do e-mail")
    @NotBlank
    private String body;

    public EmailDTO() {}

    public EmailDTO(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}