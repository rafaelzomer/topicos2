package io.github.rafaelzomer.exerciciologin;

import io.github.rafaelzomer.request.AbstractServlet;
import io.github.rafaelzomer.template.TemplateRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends AbstractServlet {

  @Override
  public void post(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String login = request.getParameter("login");
    String password = request.getParameter("password");
    String pagina = "exerciciologin/login.jsp";
    if ("admin".equals(login) && "1".equals(password)) {
      response.addCookie(new Cookie("logado", "true"));
      pagina = "exerciciologin/sucesso_login.jsp";
    }
    TemplateRunner.create(response)
            .menu()
            .component(TemplateRunner.container(response).file(pagina))
            .render();
  }

  @Override
  public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String pagina = "exerciciologin/login.jsp";
    if ("true".equals(request.getCookies()[0].toString())) {
      pagina = "exerciciologin/sucesso_login.jsp";
    }
    TemplateRunner.create(response)
            .menu()
            .component(TemplateRunner.container(response).file(pagina))
            .render();
  }
}