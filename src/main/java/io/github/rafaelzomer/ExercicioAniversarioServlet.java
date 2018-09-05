package io.github.rafaelzomer;

import io.github.rafaelzomer.request.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@WebServlet("/exercicio-aniversario")
public class ExercicioAniversarioServlet extends AbstractServlet {
  private static final String MENSAGEM_ATTR = "mensagem";
  private static final String JSP = "exercicioaniversario.jsp";
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("LLLL", new Locale("pt", "BR"));

  private void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(JSP).forward(request, response);
  }

  @Override
  public void post(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    Integer mes = getParameter(request, "mes", Integer.class);
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, mes - 1);
    request.setAttribute(MENSAGEM_ATTR, DATE_FORMAT.format(calendar.getTime()));
    render(request, response);
  }

  @Override
  public void get(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    render(request, response);
  }
}
