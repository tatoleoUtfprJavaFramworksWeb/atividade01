package com.fomularios.application.forms;

import com.fomularios.application.components.ConfirmationDialog;
import com.fomularios.application.entity.Disciplina;
import com.fomularios.application.entity.Nota;
import com.fomularios.application.repository.NotaRepository;
import com.fomularios.application.views.crud.CrudView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.data.binder.Binder;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class NotaForm extends FormLayout {

    // Ano Letivo
    private IntegerField anoLetivo = new IntegerField("Ano Letivo");
    private RadioButtonGroup<String> bimestre = new RadioButtonGroup<String>();
    private Select<Disciplina> disciplina = new Select<Disciplina>();
    private TextField professor = new TextField("Professor");
    private TextField atividade = new TextField("Atividade");
    private BigDecimalField nota = new BigDecimalField("Nota");
    private Checkbox rascunho = new Checkbox("Rascunho");

    private Button salvarButton = new Button("Salvar");
    private Button excluirButton = new Button("Excluir");

    private CrudView crudView;
    private ConfirmationDialog confirmationDialog = new ConfirmationDialog();

    private Binder<Nota> binder = new Binder<>(Nota.class);
    private NotaRepository repository;

    public NotaForm(CrudView crudView, NotaRepository repository) {
        this.crudView = crudView;
        this.repository = repository;

        binder.bindInstanceFields(this);

        anoLetivo.setValue(2021);
        anoLetivo.setHasControls(true);
        anoLetivo.setMin(2015);
        anoLetivo.setMax(2025);

        bimestre.setItems("1º", "2º", "3º", "4º");

        disciplina.setItemLabelGenerator(Disciplina::getNome);
        disciplina.setItems(Disciplina.lista());

        nota.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);


        HorizontalLayout btns = new HorizontalLayout(salvarButton, excluirButton);
        salvarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        salvarButton.addClickListener(event -> salvar());
        excluirButton.addClickListener(event -> excluir());

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

        this.add(new H1("Atividade01 - Semana 02/08 - 08/08 - Formulários"), 12);

        Details detail = new Details("Veja Mais",
                new Text("Cadastro de Notas de um Aluno"));

        this.add(detail, 12);
        this.add(anoLetivo, 2);
        this.add(bimestre, 3);
        this.add(disciplina, 3);
        this.add(professor, 4);
        this.add(atividade, 4);
        this.add(nota, 2);
        this.add(rascunho, 2);
        this.add(btns, 2);

    }

    private void salvar() {
        Nota nota = binder.getBean();
        if (validarForm(nota)){
            repository.save(nota);
            crudView.atualizarListaNotas();
            setNota(null);
            limparCampos();
            Notification.show("Nota incluída com sucesso!");
        }
    }

    private void excluir() {
        Nota nota = binder.getBean();
        confirmationDialog.setQuestion("Deseja realmente excluir a nota '"+ nota.toString() + "'?");
        confirmationDialog.open();
        confirmationDialog.addConfirmationListener(evt -> {
            repository.delete(nota);
            crudView.atualizarListaNotas();
            setNota(null);
            Notification.show("Nota excluída com sucesso!");
        });
    }

    public void setNota(Nota nota) {
        binder.setBean(nota);
        if (nota == null) {
            setVisible(false);
        } else {
            setVisible(true);
            if (binder.getBean().isPersisted()) {
                excluirButton.setVisible(true);
            } else {
                excluirButton.setVisible(false);
            }
            anoLetivo.focus();
        }
    }

    private boolean validarForm(Nota nota){
        boolean valido = true;

        if (nota.getAnoLetivo() == null){
            Notification.show("Nenhum Ano Letivo selecionado!");
            valido = false;
        }

        if (StringUtils.isBlank(nota.getBimestre())){
            Notification.show("Nenhum Bimestre selecionado!");
            valido = false;
        }

        if (nota.getDisciplina() == null){
            Notification.show("Nenhuma Disciplina selecionada!");
            valido = false;
        }
        if (StringUtils.isBlank(nota.getProfessor())){
            Notification.show("Nenhum Professor informado!");
            valido = false;
        }

        if (StringUtils.isBlank(nota.getAtividade())){
            Notification.show("Nenhuma Atividade informada!");
            valido = false;
        }

        if (nota.getNota() == null){
            Notification.show("Nenhuma Nota informada!");
            valido = false;
        }

        return valido;

    }

    private void limparCampos(){
        // limpar campos
        bimestre.setValue(null);
        disciplina.setValue(null);
        professor.setValue("");
        atividade.setValue("");
        nota.setValue(null);
    }
}
