package io.github.rafaelzomer;

import io.github.rafaelzomer.request.AbstractServlet;
import io.github.rafaelzomer.template.TemplateRunner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exe01")
public class Exe01Servlet extends AbstractServlet {

  @Override
  public void get(HttpServletResponse response) throws IOException {
    TemplateRunner.create(response)
            .menu()
            .component(TemplateRunner.container(response).file("exe001"))
            .render();
  }
}