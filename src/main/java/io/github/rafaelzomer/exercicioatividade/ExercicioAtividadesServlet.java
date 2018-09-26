package io.github.rafaelzomer.exercicioatividade;

import io.github.rafaelzomer.request.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {
        "/exercicio-atividades/salvar",
        "/exercicio-atividades/excluir",
        ExercicioAtividadesServlet.URL_LISTAGEM,
        "/exercicio-atividades/editar",
        ExercicioAtividadesServlet.URL_ATIVIDADE
})
public class ExercicioAtividadesServlet extends AbstractServlet {
  static final String URL_ATIVIDADE = "/exercicio-atividades/atividade";
  static final String URL_LISTAGEM = "/exercicio-atividades/listar";
  private static final String JSP_LISTAGEM = "../exercicioatividades/listagem.jsp";
  private static final String JSP_ATIVIDADE = "../exercicioatividades/cadastro.jsp";

  private void render(String view, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(view).forward(request, response);
  }

  private void saveAtividades(HttpServletRequest request, List<Atividade> atividades) {
    request.getSession().setAttribute("atividades", atividades);
  }

  private Atividade getAtividade(List<Atividade> atividades, Integer codigo) {
    return atividades.stream().filter(t -> t.getCodigo().equals(codigo)).findFirst().orElse(null);
  }

  @Override
  public void post(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    if (constainsPath(request, URL_ATIVIDADE)) {
      Integer codigo = getParameter(request, "codigo", Integer.class);
      List<Atividade> atividades = getSessionList(request, "atividades", Atividade.class);
      Atividade p = null;
      if (codigo == null) {
        codigo = atividades.size() + 1;
      } else {
        p = getAtividade(atividades, codigo);
      }
      String descricao = getParameter(request, "descricao", String.class);
      if (descricao == null) {
        request.setAttribute("error", "Descrição obrigatória");
        render(JSP_ATIVIDADE, request, response);
        return;
      }
      Integer estagio = getParameter(request, "estagio", Integer.class);
      if (estagio == null) {
        request.setAttribute("error", "Estágio obrigatório");
        render(JSP_ATIVIDADE, request, response);
        return;
      }
      if (p == null) {
        p = Atividade.of(codigo, descricao, new Date(), null, estagio, null);
        atividades.add(p);
      } else {
        p.setDescricao(descricao);
        p.setEstagio(estagio);
      }
      saveAtividades(request, atividades);
      response.sendRedirect(URL_LISTAGEM);
      return;
    }
    render(JSP_LISTAGEM, request, response);
  }

  @Override
  public void get(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.getSession().removeAttribute("message");
    if (constainsPath(request, URL_ATIVIDADE)) {
      List<Atividade> atividades = getSessionList(request, "atividades", Atividade.class);
      Integer codigo = getParameter(request, "codigo", Integer.class);
      if (codigo != null) {
        Atividade atividade = getAtividade(atividades, codigo);
        if (atividade == null) {
          response.sendRedirect(URL_LISTAGEM);
          return;
        }
        request.setAttribute("codigo", atividade.getCodigo());
        request.setAttribute("descricao", atividade.getDescricao());
      }
      Integer concluir = getParameter(request, "concluir", Integer.class);
      if (concluir != null) {
        Atividade atividade = getAtividade(atividades, concluir);
        if (atividade != null && atividade.getEstagio() < 100) {
          atividade.setDataConclusao(new Date());
          atividade.setEstagio(100);
          saveAtividades(request, atividades);
          request.getSession().setAttribute("message", MessageFormat.format("Atividade {0} concluída com sucesso!", concluir));
        }
        render(JSP_LISTAGEM, request, response);
        return;
      }
      Integer excluir = getParameter(request, "excluir", Integer.class);
      if (excluir != null) {
        Atividade atividade = getAtividade(atividades, excluir);
        if (atividade != null) {
          atividades = atividades.stream().filter(e -> !e.getCodigo().equals(atividade.getCodigo())).collect(Collectors.toList());
          saveAtividades(request, atividades);
          request.setAttribute("message", "Atividade excluída com sucesso!");
        }
        render(JSP_LISTAGEM, request, response);
        return;
      }
      render(JSP_ATIVIDADE, request, response);
      return;
    }
    render(JSP_LISTAGEM, request, response);
  }
}
