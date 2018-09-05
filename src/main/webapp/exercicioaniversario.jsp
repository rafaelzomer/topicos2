<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:base title="IMC - Boa Forma" >
    <tags:menu />
    <tags:container>
        <div class="row mt-5 justify-content-md-center">
            <div class="col col-lg-4 mt-5">
                <form action = "/exercicio-aniversario" method = "POST">

                    <c:if test="${mensagem != null && !mensagem.trim().isEmpty()}">
                        <div class="alert alert-info" role="alert">
                            Você nasceu em
                            <b>${mensagem}</b>
                        </div>
                    </c:if>
                    <h4>
                        Seu
                        <b>Aniversário</b>
                    </h4>
                    <hr/>
                    <div class="form-group">
                        <select name="mes" type="number" step="0.01" class="form-control" placeholder="Mês do seu aniversário" required="required">
                            <c:forEach var="i" begin="1" end="12">
                                <option value="${i}">${i}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">Calcular IMC</button>
                    </div>
                </form>
            </div>
        </div>
    </tags:container>
</tags:base>