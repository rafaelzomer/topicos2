package io.github.rafaelzomer;

import io.github.rafaelzomer.request.AbstractServlet;
import io.github.rafaelzomer.template.TemplateRunner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class StartupServlet extends AbstractServlet {

  @Override
  public void get(HttpServletResponse response) throws IOException {
    TemplateRunner.create(response)
            .title("Bem vindo!")
            .menu()
            .component(TemplateRunner.container(response).file("welcome"))
            .render();
  }
}