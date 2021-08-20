package com.fomularios.application;

import com.fomularios.application.entity.Disciplina;
import com.fomularios.application.entity.Nota;
import com.fomularios.application.repository.NotaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.helpers.LaunchUtil;
import com.vaadin.flow.component.dependency.NpmPackage;

import java.math.BigDecimal;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }

    @Bean
    public CommandLineRunner carregarDisciplina(NotaRepository repository) {
//            public Nota(Integer anoLetivo, String bimestre, Disciplina disciplina, String professor, String atividade, BigDecimal nota, Boolean rascunho) {

        return (args) -> {
            repository.save(new Nota(2020, "1º", new Disciplina("Biologia"), "AA", "ASD", new BigDecimal("10"), false));
            repository.save(new Nota(2021, "2º", new Disciplina("Matemática"), "BB", "trabalho", new BigDecimal("9"), true));
        };
    }
}
