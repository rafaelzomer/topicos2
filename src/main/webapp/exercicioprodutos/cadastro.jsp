<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:base title="Novo produto">
  <tags:menu />
  <tags:container>
    <div class="row mt-5 justify-content-md-center">
      <div class="col col-lg-4 mt-5">
        <a href="/exercicio-produtos/listar" role="button">Voltar para listagem</a>
        <form action="/exercicio-produtos/produto" method="POST">
          <h4>
            Cadastro
            <b>de produto</b>
          </h4>
          <hr />
          <div class="form-group">
            <label for="codigo">Código</label>
            <input name="codigo" value="${codigo}" type="number" class="form-control" placeholder="Código do produto">
          </div>
          <div class="form-group">
            <label for="descricao">Descrição *</label>
            <input name="descricao" value="${descricao}" type="text" class="form-control" placeholder="Descrição do produto" required="required">
          </div>
          <c:if test="${error != null && !error.trim().isEmpty()}">
            <div class="alert alert-danger" role="alert">
              ${error}
            </div>
          </c:if>
          <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Cadastrar</button>
          </div>
        </form>
      </div>
    </div>
  </tags:container>
</tags:base>