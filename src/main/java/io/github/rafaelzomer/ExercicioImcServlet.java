package io.github.rafaelzomer;

import io.github.rafaelzomer.request.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exercicio-imc")
public class ExercicioImcServlet extends AbstractServlet {
  private static final String ERROR_ATTR = "error";
  private static final String MENSAGEM_ATTR = "mensagem";
  private static final String IMC_ATTR = "imc";
  private static final String COR_ATTR = "cor";
  private static final String JSP = "exercicioimc.jsp";

  private void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(JSP).forward(request, response);
  }

  @Override
  public void post(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    Double peso = getParameter(request, "peso", Double.class);
    Double altura = getParameter(request, "altura", Double.class);
    if (peso == null || peso <= 0) {
      request.setAttribute(ERROR_ATTR, "O peso é obrigatório");
      return;
    }
    if (altura == null || altura <= 0) {
      request.setAttribute(ERROR_ATTR, "A altura é obrigatória");
      return;
    }
    Double imc = peso / (altura * 2);
    request.setAttribute(IMC_ATTR, String.format("%.2f", imc));
    if (imc < 20) {
      request.setAttribute(COR_ATTR, "#333300");
      request.setAttribute(MENSAGEM_ATTR, "abaixo do peso");
    } else if (imc <= 25) {
      request.setAttribute(COR_ATTR, "#3366FF");
      request.setAttribute(MENSAGEM_ATTR, "peso normal");
    } else if (imc < 30) {
      request.setAttribute(COR_ATTR, "blue");
      request.setAttribute(MENSAGEM_ATTR, "sobrepeso");
    } else if (imc < 40) {
      request.setAttribute(COR_ATTR, "#FF9900");
      request.setAttribute(MENSAGEM_ATTR, "obeso");
    } else {
      request.setAttribute(COR_ATTR, "red");
      request.setAttribute(MENSAGEM_ATTR, "obsesidade mórbida");
    }
    render(request, response);
  }

  @Override
  public void get(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    render(request, response);
  }
}
