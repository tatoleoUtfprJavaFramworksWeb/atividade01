package com.fomularios.application.views.testesForms;

import com.fomularios.application.entity.Cidade;
import com.fomularios.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

@PageTitle("Testes Formulários")
@Route(value = "testes", layout = MainLayout.class)
public class TestesFormsView extends VerticalLayout {
    public TestesFormsView(){
        VerticalLayout todosList = new VerticalLayout(); // (1)
        TextField taskField = new TextField(); // (2)
        Button addButton = new Button("Adicionar"); // (3)
        addButton.addClickShortcut(Key.ENTER);
        addButton.addClickListener(click -> {
            // (4)
            Checkbox checkbox = new Checkbox(taskField.getValue());
            todosList.add(checkbox);
            checkbox.addClickListener(check -> {
                // (5)
                todosList.remove(checkbox);
                taskField.focus();
            });
            taskField.setValue("");
            taskField.focus();
        });
        add( // (6)
                new H1("lista de Tarefas"),
                new HorizontalLayout(
                        taskField,
                        addButton
                ),
                todosList
        );

        NumberField numberField2 = new NumberField("Valor");
        numberField2.setPrefixComponent(VaadinIcon.DOLLAR.create());
        numberField2.setSuffixComponent(new Span(",00"));

        add(numberField2);

        Details detail = new Details("Veja em detalhes",
                new Text("Aqui vai todo o texto em detalhes... é possível adicionar qualquer componente aqui."));
        detail.addOpenedChangeListener(e -> Notification.show(e.isOpened() ? "Abriu" : "Fechou"));

        add(detail);


        Icon edita = new Icon(VaadinIcon.EDIT);
        Icon fechar = VaadinIcon.CLOSE.create();
        Icon customizar = new Icon(VaadinIcon.SMILEY_O);
        customizar.setColor("red");
        customizar.setSize("70px");

        add(edita);
        add(fechar);
        add(customizar);

        Tab tab1 = new Tab("Links");
        Tab tab2 = new Tab("Detalhes");
        Tab tab3 = new Tab("Icones");
        Tab tab4 = new Tab("Imagem");
        Tabs tabs = new Tabs(tab1, tab2, tab3, tab4);

        add(tabs);


        Checkbox checkbox1 = new Checkbox();
        checkbox1.setLabel("Opção 1");
        checkbox1.setValue(true);
        Checkbox checkbox2 = new Checkbox("Opção 2");
        checkbox1.addValueChangeListener(evt1 -> {
            if (evt1.getValue()) {
                Notification.show("Selecionado");
            } else {
                Notification.show("Desmarcado!");
            }
        });

        add(checkbox1);
        add(checkbox2);


        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        checkboxGroup.setLabel("Items");
        checkboxGroup.setItems("Item 1", "Item 2", "Item 3", "Item 4");
        checkboxGroup.setValue(new HashSet<>(Arrays.asList("Item 2", "Item 3")));
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        checkboxGroup.addValueChangeListener(evt2 -> {
            if (evt2.getValue().isEmpty()) {
                Notification.show("Nenhum item selecionado");
            } else {
                Notification.show("Selecionado: " + evt2.getValue());
            }
        });

        add(checkboxGroup);

        DatePicker labelDatePicker = new DatePicker();
        labelDatePicker.setLabel("Data");
        labelDatePicker.setAutoOpen(true);
        labelDatePicker.setMin(LocalDate.now());
        labelDatePicker.setMax(LocalDate.now().plusDays(30));

        add(labelDatePicker);


        TimePicker timePicker = new TimePicker("Hora");
        timePicker.setLocale(Locale.CANADA_FRENCH);
        timePicker.setMinTime(LocalTime.of(8, 0, 0));
        timePicker.setMaxTime(LocalTime.of(18, 0, 0));
        timePicker.setStep(Duration.ofMinutes(15));

        add(timePicker);


        Select<String> select = new Select<>();
        select.setItems("Item 1", "Item 2", "item N");
        select.setPlaceholder("Selecione um item");
        select.setLabel("Select");
        select.setValue("Item N");

        add(select);

        Select<Cidade> listaCidades = new Select<>();
        listaCidades.setLabel("Lista de cidades");
        listaCidades.setItemLabelGenerator(Cidade::getNome);
        listaCidades.setItems(Cidade.lista());

        listaCidades.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                Notification.show("Nenhuma opção selecionada");
            } else {
                Notification.show("Valor selecionado: " + event.getValue().getNome() + " / Valor anterior: " + event.getOldValue().getNome());
            }
        });

        add(listaCidades);


        ComboBox<Cidade> comboBox = new ComboBox();
        comboBox.setLabel("Combo de cidades");
        comboBox.setItemLabelGenerator(Cidade::getNome);
        comboBox.setItems(Cidade.lista());
        comboBox.setAllowCustomValue(false);     // 1
        comboBox.setClearButtonVisible(true);    // 2
        comboBox.setPreventInvalidInput(true);   // 3
        comboBox.setRequired(true);

        comboBox.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                Notification.show("Nenhum Combo selecionada");
            } else {
                Notification.show("Combo selecionado: " + event.getValue().getNome() + " / Valor anterior: " + event.getOldValue().getNome());
            }
        });

        add(comboBox);

        Grid<Cidade> grid = new Grid<>(Cidade.class);
        grid.setItems(Cidade.lista());
        grid.setColumns("id", "nome", "estado");

        grid.getColumnByKey("estado")
                .setHeader("UF")
                .setTextAlign(ColumnTextAlign.CENTER)
                .setWidth("50px");

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
//        grid.addSelectionListener(event -> {
//            Cidade cidade = event.getFirstSelectedItem().get();
//            if (cidade == null) {
//                selecionado.setVisible(false);
//            } else {
//                selecionado.setText(cidade.toString());
//                selecionado.setVisible(true);
//            }
//        });

        add(grid);
    }
}
