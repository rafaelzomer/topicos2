package io.github.rafaelzomer.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    get(resp);
    get(req, resp);
  }

  public void get(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

  }

  public void get(HttpServletResponse response) throws IOException, ServletException {

  }

  ;
}
