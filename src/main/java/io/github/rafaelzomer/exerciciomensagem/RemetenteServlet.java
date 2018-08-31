package io.github.rafaelzomer.exerciciomensagem;

import io.github.rafaelzomer.request.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RemetenteServlet")
public class RemetenteServlet extends AbstractServlet {
  @Override
  public void get(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String mensagem = "Você pode me ouvir?";
    request.setAttribute("chaveDaMensagem", mensagem);
    response.sendRedirect("ReceptorServlet");
//    request.getRequestDispatcher("ReceptorServlet").forward(request, response);
  }
}