package com.fomularios.application.views.helloworld;

import com.fomularios.application.views.testesForms.TestesFormsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.fomularios.application.views.MainLayout;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public HelloWorldView() {
        addClassName("hello-world-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });

//        Button btnFazer = new Button("Fazer algo", click -> {
//            // Executa alguma rotina
//            // ...
//
//            // Redireciona para alguma visão
//            getUI().ifPresent(ui -> ui.navigate(TestesFormsView.class));
//        });
//
//        add(btnFazer);
    }

}
