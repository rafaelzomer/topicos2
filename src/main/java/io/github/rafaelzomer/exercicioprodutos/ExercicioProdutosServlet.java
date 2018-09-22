package io.github.rafaelzomer.exercicioprodutos;

import io.github.rafaelzomer.request.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {
        "/exercicio-produtos/salvar",
        "/exercicio-produtos/excluir",
        ExercicioProdutosServlet.URL_LISTAGEM,
        "/exercicio-produtos/editar",
        ExercicioProdutosServlet.URL_PRODUTO
})
public class ExercicioProdutosServlet extends AbstractServlet {
  static final String URL_PRODUTO = "/exercicio-produtos/produto";
  static final String URL_LISTAGEM = "/exercicio-produtos/listar";
  private static final String JSP_LISTAGEM = "../exercicioprodutos/listagem.jsp";
  private static final String JSP_PRODUTO = "../exercicioprodutos/cadastro.jsp";

  private void render(String view, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(view).forward(request, response);
  }

  private Produto getProduto(List<Produto> produtos, Integer codigo) {
    return produtos.stream().filter(t -> t.getCodigo().equals(codigo)).findFirst().orElse(null);
  }

  @Override
  public void post(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    if (constainsPath(request, URL_PRODUTO)) {
      Integer codigo = getParameter(request, "codigo", Integer.class);
      List<Produto> produtos = getSession(request, "produtos", List.class);
      Produto p = null;
      if(codigo == null) {
        codigo = produtos.size() + 1;
      } else {
        p = getProduto(produtos, codigo);
      }
      String descricao = getParameter(request, "descricao", String.class);
      if (descricao == null) {
        request.setAttribute("error", "Descrição obrigatória");
        render(JSP_PRODUTO, request, response);
        return;
      }
      if (p == null) {
        p = new Produto(codigo, descricao);
        produtos.add(p);
      } else {
        p.setDescricao(descricao);
      }
      request.getSession().setAttribute("produtos", produtos);
      response.sendRedirect(URL_LISTAGEM);
      return;
    }
    render(JSP_LISTAGEM, request, response);
  }

  @Override
  public void get(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    if (constainsPath(request, URL_PRODUTO)) {
      List<Produto> produtos = getSession(request, "produtos", List.class);
      Integer codigo = getParameter(request, "codigo", Integer.class);
      if (codigo != null) {
        Produto produto = produtos.stream().filter(t -> t.getCodigo().equals(codigo)).findFirst().orElse(null);
        if (produto == null) {
          response.sendRedirect(URL_LISTAGEM);
          return;
        }
        request.setAttribute("codigo", produto.getCodigo());
        request.setAttribute("descricao", produto.getDescricao());
      }
      render(JSP_PRODUTO, request, response);
      return;
    }
    render(JSP_LISTAGEM, request, response);
  }
}
