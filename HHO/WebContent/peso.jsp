<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html lang="pt-br">

            <head>
                <title>Peso</title>
                <link rel="stylesheet" href="style/historico.css">
                <%@ include file="imports/head.jsp" %>
            </head>

            <body>
                <header>
                    <%@ include file="imports/menu.jsp" %>
                </header>

                <main>
                    <div class="container-fluid">
                        <form>
                            <div id="hoLabel" class="col sm-10 col-md-10 col-lg-10">
                                <h3 class="texto-peso">Acompanhe seu progresso</h3>
                            </div>

                            <div class="subscribe">
                                <button type="button" data-bs-toggle="modal" data-bs-target="#newUser">
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
                                    <c:set var="contador" value="1" />

                                    <c:forEach var="peso" items="${pesos}">
                                        <tr>
                                            <th scope="row">
                                                <fmt:parseNumber type="number" value="${contador}"/>
                                            </th>
                                            <td>
                                                <fmt:formatNumber type="number" pattern="##.##Kg"
                                                    value="${peso.getWeight()}" />
                                            </td>
                                            <td>
                                                <fmt:formatDate pattern="dd/MM/yyyy"
                                                    value="${peso.getInclusionDate()}" />
                                            </td>
                                            <td>
                                                <button type="button" data-bs-toggle="modal" data-bs-target="#newUser">
                                                    <img id="editar" src="./_img/Icons/editar.png"
                                                        onclick="setModalValue('${contador}-1')" />
                                                </button>
                                            </td>
                                            <td>
                                                <a href="AlterMeasures?id=${peso.getWeightCode()}">
                                                    <img id="editar" src="./_img/Icons/lixo.png" />
                                                </a>
                                            </td>
                                        </tr>

                                        <c:set var="contador" value="${contador}+1" />
                                    </c:forEach>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </main>
            </body>

            <script>
                setModalValue = function (indice) {
                    document.querySelector('iptId').Value = '${pesos[' + indice + '].getWeightCode()}';
                    document.querySelector('iptPeso').Value = '${pesos[' + indice + '].getWeight()}';
                    document.querySelector('iptData').Value = '${pesos[' + indice + '].getInclusionDate()}';

                    openModal('#atualizarPeso');
                }
            </script>

            <%@ include file="imports/alterWModal.jsp" %>

            </html>