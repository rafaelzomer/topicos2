<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:base title="IMC - Boa Forma" >
    <tags:menu />
    <tags:container>
        <div class="row mt-5 justify-content-md-center">
            <div class="col col-lg-4 mt-5">
                <form action = "/exercicio-imc" method = "POST">

                    <c:if test="${mensagem != null && !mensagem.trim().isEmpty()}">
                        <div class="alert alert-light" role="alert">
                            <b> Seu IMC é ${imc} </b>
                            Segundo a classificação da organização mundial da saúda você está
                            <p style="color: ${cor}">${mensagem}</p>
                        </div>
                    </c:if>
                    <h4>
                        Academia
                        <b>Boa forma</b>
                    </h4>
                    <hr/>
                    <div class="form-group">
                        <input name="peso" type="number" step="0.01" class="form-control" placeholder="Seu peso" required="required">
                    </div>
                    <div class="form-group">
                        <input name="altura" type="number" step="0.01" class="form-control" placeholder="Sua altura" required="required">
                    </div>
                    <c:if test="${error != null && !error.trim().isEmpty()}">
                        <div class="alert alert-error" role="alert">
                            ${error}
                        </div>
                    </c:if>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">Calcular IMC</button>
                    </div>
                </form>
            </div>
        </div>
    </tags:container>
</tags:base>