<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@page import="java.util.List" %>
            <%@page import="br.com.healthhistoryonline.sysmodel.Weight" %>
                <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

                    <!DOCTYPE html>
                    <html lang="pt-br">

                    <head>
                        <title>Peso</title>
                        <link rel="stylesheet" href="style/historico.css">
                        <script src="js/peso.js"></script>
                        <%@ include file="imports/head.jsp" %>
                    </head>

                    <body>
                        <header>
                            <c:if test="${message.getSecond() != null}">
                                <script>
                                    window.onload = function () {
                                        openModal('#notificacaoModal')
                                    }
                                </script>
                            </c:if>
                            <%@ include file="imports/menu.jsp" %>
                        </header>

                        <main>
                            <div class="container-fluid">
                                <form>
                                    <div id="hoLabel" class="col sm-10 col-md-10 col-lg-10">
                                        <h3 class="texto-peso">Acompanhe seu progresso</h3>
                                    </div>

                                    <div class="subscribe">
                                        <button type="button" data-bs-toggle="modal" data-bs-target="#incluirPeso">
                                            Cadastre um novo peso
                                        </button>
                                    </div>

                                    <table class="table bg-light shadow">
                                        <thead>
                                            <tr>
                                                <th scope="col">°</th>
                                                <th scope="col">Peso</th>
                                                <th scope="col">Data de modificação</th>
                                                <th scope="col">Edição</th>
                                                <th scope="col">Excluir</th>
                                            </tr>
                                        </thead>

                                        <tbody class="tabela-peso">
                                            <c:set var="contador" value="0" />
                                            <c:forEach var="currentW" items="${pesos}">
                                                <tr class="line-w">
                                                    <th scope="row">
                                                        <fmt:parseNumber type="number" value="${contador+1}" />
                                                    </th>

                                                    <td>
                                                        <fmt:formatNumber type="number" pattern="##.##Kg"
                                                            value="${currentW.getWeight()}" />
                                                    </td>
                                                    <td>
                                                        <fmt:formatDate pattern="dd/MM/yyyy"
                                                            value="${currentW.getInclusionDate()}" />
                                                    </td>
                                                    <td>
                                                        <button type="button"
                                                            onclick="loadModalData('${contador}',  '${currentW.getWeightCode()}')">
                                                            <img id="editar" src="./_img/Icons/editar.png" />
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <a href="AlterMeasures?id=${currentW.getWeightCode()}">
                                                            <img id="editar" src="./_img/Icons/lixo.png" />
                                                        </a>
                                                    </td>
                                                </tr>

                                                <c:set var="contador" value="${contador+1}" />
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </main>
                    </body>

                    <%@ include file="imports/insertWModal.jsp" %>
                        <%@ include file="imports/alterWModal.jsp" %>
                            <%@ include file="imports/menuModal.jsp" %>
                                <%@ include file="imports/notifyModal.jsp" %>

                    </html>