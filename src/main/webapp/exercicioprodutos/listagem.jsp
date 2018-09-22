<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:base title="Listagem de produtos">
  <tags:menu />
  <tags:container>
    <div class="row">
      <div class="col-md p-4">
        <a class="btn btn-primary float-right" href="/exercicio-produtos/produto" role="button">Novo</a>
      </div>
    </div>
    <div class="row">
      <table class="table">
        <tr>
          <th>Código</th>
          <th>Descrição</th>
          <th>&nbsp;</th>
        </tr>
        <c:forEach var="p" items="${produtos}">
          <tr>
            <td>${p.codigo}</td>
            <td>${p.descricao}</td>
            <td><a href="produto?codigo=${p.codigo}">editar</a></td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </tags:container>
</tags:base>