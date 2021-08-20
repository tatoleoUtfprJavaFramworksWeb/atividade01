package com.fomularios.application.views.crud;

import com.fomularios.application.entity.Disciplina;
import com.fomularios.application.entity.Nota;
import com.fomularios.application.forms.NotaForm;
import com.fomularios.application.repository.NotaRepository;
import com.fomularios.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@PageTitle("Atividade CRUD")
@Route(value = "crud", layout = MainLayout.class)
public class CrudView extends VerticalLayout {

    private final NotaRepository repository;

    Grid<Nota> gridNotas = new Grid<>(Nota.class);
    Button btnAdicionar = new Button("Adicionar");

    private NotaForm form;

    public CrudView(@Autowired NotaRepository repository) {
        this.repository = repository;
        form = new NotaForm(this, repository);
        form.setNota(null);

        gridNotas.setColumns("anoLetivo", "bimestre", "disciplina.nome", "professor", "atividade", "nota", "rascunho");
        gridNotas.setSizeFull();

        gridNotas.getColumnByKey("disciplina.nome")
                .setHeader("Disciplina")
                .setTextAlign(ColumnTextAlign.CENTER);
        gridNotas.asSingleSelect().addValueChangeListener(e -> selecionar());

        atualizarListaNotas();

        // grid Notas
        H2 H2Notas = new H2("Notas");

        btnAdicionar.addClickListener(e -> adicionarNovo());

        HorizontalLayout layoutNotas = new HorizontalLayout(H2Notas, btnAdicionar);
        add(form, layoutNotas, gridNotas);
        setSizeFull();

    }
    private void adicionarNovo() {
        gridNotas.asSingleSelect().clear();
        form.setNota(new Nota());
    }
    public void selecionar() {
        form.setNota(gridNotas.asSingleSelect().getValue());
    }
    public void atualizarListaNotas() {
        gridNotas.setItems(repository.findAll());
    }

}
