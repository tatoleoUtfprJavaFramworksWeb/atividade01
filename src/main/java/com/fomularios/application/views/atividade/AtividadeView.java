package com.fomularios.application.views.atividade;

import com.fomularios.application.entity.Cidade;
import com.fomularios.application.entity.Disciplina;
import com.fomularios.application.entity.Nota;
import com.fomularios.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@PageTitle("Atividade Formulários")
@Route(value = "atividade", layout = MainLayout.class)
public class AtividadeView extends FormLayout {

    private List<Nota> listaNotas = new ArrayList<>();

    public AtividadeView(){

        this.add(new H1("Atividade01 - Semana 02/08 - 08/08 - Formulários"), 12);

        Details detail = new Details("Veja Mais",
                new Text("Cadastro de Notas de um Aluno"));

        // Colunas do Formulário
        this.setResponsiveSteps(
                new ResponsiveStep("150px", 1),
                new ResponsiveStep("150px", 2),
                new ResponsiveStep("150px", 3),
                new ResponsiveStep("150px", 4),
                new ResponsiveStep("150px", 5),
                new ResponsiveStep("150px", 6),
                new ResponsiveStep("1500px", 7),
                new ResponsiveStep("150px", 8),
                new ResponsiveStep("150px", 9),
                new ResponsiveStep("150px", 10),
                new ResponsiveStep("150px", 11),
                new ResponsiveStep("150px", 12)
        );

        // Ano Letivo
        NumberField nfAnoLetivo = new NumberField();
        nfAnoLetivo.setLabel("Ano Letivo");
        nfAnoLetivo.setValue(2021d);
        nfAnoLetivo.setHasControls(true);
        nfAnoLetivo.setMin(2015);
        nfAnoLetivo.setMax(2025);

        // Bimestre
        RadioButtonGroup<String> rbBimestre = new RadioButtonGroup<>();
        rbBimestre.setLabel("Bimestre");
        rbBimestre.setItems("1º", "2º", "3º", "4º");

        // Disciplina
        Select<Disciplina> selDiciplina = new Select<>();
        selDiciplina.setLabel("Disciplina");
        selDiciplina.setItemLabelGenerator(Disciplina::getNome);
        selDiciplina.setItems(Disciplina.lista());
        selDiciplina.setRequiredIndicatorVisible(true);

        //Professor
        TextField tfProfessor = new TextField();
        tfProfessor.setLabel("Professor");
        tfProfessor.setPlaceholder("...");

        // Atividade
        TextField tfAtividade = new TextField();
        tfAtividade.setLabel("Atividade");
        tfAtividade.setPlaceholder("...");

        //Nota
        BigDecimalField bgNota = new BigDecimalField();
        bgNota.setLabel("Nota");
        bgNota.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        // Rascunho
        Checkbox cbRascunho = new Checkbox();
        cbRascunho.setLabel("Rascunho");
        cbRascunho.setValue(true);

        // grid Notas
        H2 H2Notas = new H2("Notas");

        Grid<Nota> gridNotas = new Grid<>(Nota.class);
        gridNotas.setItems(listaNotas);
        gridNotas.setColumns("anoLetivo", "bimestre", "disciplina.nome", "professor", "atividade", "nota", "rascunho");

        gridNotas.getColumnByKey("disciplina.nome")
                .setHeader("Disciplina")
                .setTextAlign(ColumnTextAlign.CENTER);

        Button btIncluir = new Button("Incluir", click -> {

            boolean valido = true;
            Nota nota = new Nota();
            nota.setRascunho(cbRascunho.getValue());
            nota.setAnoLetivo(nfAnoLetivo.getValue().intValue());

            String bimestre = rbBimestre.getValue();
            if (bimestre == null){
                Notification.show("Nenhum Bimestre selecionado!");
                valido = false;
            } else {
                nota.setBimestre(bimestre);
            }
            Disciplina disciplina = selDiciplina.getValue();
            if (disciplina == null){
                Notification.show("Nenhuma Disciplina selecionada!");
                valido = false;
            } else {
                nota.setDisciplina(disciplina);
            }
            String professor = tfProfessor.getValue();
            if (StringUtils.isBlank(professor)){
                Notification.show("Nenhum Professor informado!");
                valido = false;
            } else {
                nota.setProfessor(professor);
            }

            String atividade = tfAtividade.getValue();
            if (StringUtils.isBlank(atividade)){
                Notification.show("Nenhuma Atividade informada!");
                valido = false;
            } else {
                nota.setAtividade(atividade);
            }

            BigDecimal notaInformada = bgNota.getValue();
            if (notaInformada == null){
                Notification.show("Nenhuma Nota informada!");
                valido = false;
            } else {
                nota.setNota(notaInformada);
            }
            if (valido) {
                listaNotas.add(nota);
                gridNotas.getDataProvider().refreshAll();

                // limpar campos
                rbBimestre.setValue(null);
                selDiciplina.setValue(null);
                tfProfessor.setValue("");
                tfAtividade.setValue("");
                bgNota.setValue(null);
            }

        });



        this.add(detail, 12);
        this.add(nfAnoLetivo, 2);
        this.add(rbBimestre, 3);
        this.add(selDiciplina, 3);
        this.add(tfProfessor, 4);
        this.add(tfAtividade, 4);
        this.add(bgNota, 2);
        this.add(cbRascunho, 2);
        this.add(btIncluir, 2);
        this.add(H2Notas, 12);
        this.add(gridNotas, 12);



    }

    public List<Nota> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(List<Nota> listaNotas) {
        this.listaNotas = listaNotas;
    }
}
