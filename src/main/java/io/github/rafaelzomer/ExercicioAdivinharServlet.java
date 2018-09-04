package io.github.rafaelzomer;

import com.sun.javafx.binding.StringFormatter;
import io.github.rafaelzomer.request.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

@WebServlet("/exercicio-adivinhar")
public class ExercicioAdivinharServlet extends AbstractServlet {
  private static final String NUMERO_COOKIE = "numeroCookie";
  private static final String TENTATIVA_COOKIE = "tentativaCookie";
  private static final String JSP = "exercicioadivinhar.jsp";
  private static final String MENSAGEM = "mensagem";
  private static final String DICA = "dica";
  private static final int MAX_TENTATIVA = 10;

  private Integer getTentativa(HttpServletRequest request, HttpServletResponse response, boolean nova, boolean decrementar) {
    Integer tentativa = getCookie(request, TENTATIVA_COOKIE, Integer.class);
    if (Objects.isNull(tentativa) || tentativa < 0 || nova) {
      tentativa = MAX_TENTATIVA;
    }
    if (decrementar) {
      tentativa -= 1;
    }
    response.addCookie(new Cookie(TENTATIVA_COOKIE, tentativa.toString()));
    return tentativa;
  }

  private Integer getNumeroAleatorio(HttpServletRequest request, HttpServletResponse response, boolean gerar) {
    Integer numeroAleatorio = getCookie(request, NUMERO_COOKIE, Integer.class);
    if (Objects.isNull(numeroAleatorio) || gerar) {
      numeroAleatorio = new Random().nextInt(100);
      response.addCookie(new Cookie(NUMERO_COOKIE, numeroAleatorio.toString()));
    }
    return numeroAleatorio;
  }

  private void render(HttpServletRequest request, HttpServletResponse response, boolean gerar, Integer tentativa) throws ServletException, IOException {
    Integer numeroAleatorio = getNumeroAleatorio(request, response, gerar);
    if (Objects.isNull(tentativa) || tentativa == 0) {
      tentativa = getTentativa(request, response, gerar, false);
    }
    request.setAttribute(NUMERO_COOKIE, numeroAleatorio.toString());
    request.setAttribute(TENTATIVA_COOKIE, tentativa.toString());
    request.getRequestDispatcher(JSP).forward(request, response);
  }

  @Override
  public void post(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    Integer numero = getParameter(request, "numero", Integer.class);
    Integer numeroAleatorio = getNumeroAleatorio(request, response, false);
    if (numeroAleatorio.equals(numero)) {
      request.setAttribute(MENSAGEM, "Você acertou!");
      render(request, response,true, null);
      return;
    } else {
      String frase = numero > numeroAleatorio ? "menor" : "maior";
      request.setAttribute(DICA, String.format("O número secreto é %s que %d", frase, numero));
    }
    Integer tentativa = getTentativa(request, response, false, true);
    if (tentativa == 0) {
      request.setAttribute(MENSAGEM, "Você perdeu!");
      request.setAttribute(DICA, String.format("O número era: %d", numeroAleatorio));
      render(request, response,true, tentativa);
      return;
    }
    render(request, response, false, tentativa);
  }

  @Override
  public void get(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    render(request, response, false, null);
  }
}
