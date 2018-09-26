<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:base title="Nova atividade">
  <tags:menu />
  <tags:container>
    <div class="row mt-5 justify-content-md-center">
      <div class="col col-lg-4 mt-5">
        <a href="/exercicio-atividades/listar" role="button">Voltar para listagem</a>
        <form action="/exercicio-atividades/atividade" method="POST">
          <h4>
            Cadastro
            <b>de atividade</b>
          </h4>
          <hr />
          <div class="form-group">
            <label for="codigo">Código</label>
            <input name="codigo" ${ codigo != null ? 'disabled="disabled"' : ''} value="${codigo}" type="number" class="form-control" placeholder="Código da atividade">
          </div>
          <div class="form-group">
            <label for="descricao">Descrição *</label>
            <input name="descricao" value="${descricao}" type="text" class="form-control" placeholder="Descrição da atividade" required="required">
          </div>

          <div class="form-group">
            <label for="estagio">Estágio</label>
            <input min="1" max="100" name="estagio" value="${estagio}" type="number" class="form-control" placeholder="Estágio da atividade">
          </div>
          <c:if test="${error != null && !error.trim().isEmpty()}">
            <div class="alert alert-danger" role="alert">
              ${error}
            </div>
          </c:if>
          <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Salvar</button>
          </div>
        </form>
      </div>
    </div>
  </tags:container>
</tags:base>