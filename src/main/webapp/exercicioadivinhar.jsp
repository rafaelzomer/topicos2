<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:base title="Adivinhar" >
    <tags:menu />
    <tags:container>
        <div class="row mt-5 justify-content-md-center">
            <div class="col col-lg-4 mt-5">
                <form action = "/exercicio-adivinhar" method = "POST">

                    <c:if test="${mensagem != null && !mensagem.trim().isEmpty()}">
                        <div class="alert alert-success" role="alert">
                          ${mensagem}
                        </div>
                    </c:if>
                    <c:if test="${dica != null && !dica.trim().isEmpty()}">
                        <div class="alert alert-info" role="alert">
                          ${dica}
                        </div>
                    </c:if>
                    <h4>
                        Adivinhação
                        <small>(Spoiler n.: ${numeroCookie})</small>
                    </h4>
                    <hr/>
                    <div class="form-group">
                        <input name="numero" type="number" class="form-control" placeholder="Número" required="required">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">Chutar</button>
                    </div>
                    <div class="text-muted"> Tentativas restantes: ${tentativaCookie} </div>
                </form>
            </div>
        </div>
    </tags:container>
</tags:base>