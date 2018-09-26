<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:base title="Listagem de atividades">
  <tags:menu />
  <tags:container>
    <c:if test="${message != null && !message.trim().isEmpty()}">
      <div class="row">
        <div class="col-md p-4">
          <div class="alert alert-info" role="alert">
            ${message}
          </div>
        </div>
      </div>
    </c:if>
    <c:if test="${error != null && !error.trim().isEmpty()}">
      <div class="row">
        <div class="col-md p-4">
          <div class="alert alert-danger" role="alert">
            ${error}
          </div>
        </div>
      </div>
    </c:if>
    <div class="row">
      <div class="col-md p-4">
        <a class="btn btn-primary float-right" href="/exercicio-atividades/atividade" role="button">Nova</a>
      </div>
    </div>
    <div class="row">
      <table class="table table-striped">
        <tr>
          <th>Código</th>
          <th>Descrição</th>
          <th>Data</th>
          <th>Estágio</th>
          <th>&nbsp;</th>
        </tr>
        <c:forEach var="p" items="${atividades}">
          <tr>
            <td>${p.codigo}</td>
            <td>${p.descricao}</td>
            <td>
              <fmt:formatDate value="${p.dataCadastro}" pattern="dd/MM/yyyy" />
            </td>
            <td>${p.estagio}%</td>
            <td>
              <a href="atividade?codigo=${p.codigo}">editar</a>
              &nbsp;
              <a href="atividade?excluir=${p.codigo}">excluir</a>
              &nbsp;
              <a href="atividade?concluir=${p.codigo}">concluir</a>
              &nbsp;
              <a href="atividade?notas=${p.codigo}">notas</a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </tags:container>
</tags:base>