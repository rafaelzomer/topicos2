package io.github.rafaelzomer.exerciciomensagem;

import io.github.rafaelzomer.request.AbstractServlet;
import io.github.rafaelzomer.template.TemplateRunner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ReceptorServlet")
public class ReceptorServlet extends AbstractServlet {

  @Override
  public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
    TemplateRunner.create(response)
            .menu()
            .variable("chaveDaMensagem", request.getParameter("chaveDaMensagem"))
            .component(TemplateRunner.container(response).file("exerciciomensagem"))
            .render();
  }
}