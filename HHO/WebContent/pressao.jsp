<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        	<%@page import="java.util.List" %>
        
    <!DOCTYPE html>
    <html lang="pt-br">

    <head>
        <title>Pressão</title>
        <link rel="stylesheet" href="style/historico.css">
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
                        <h3 class="texto-pressao">Acompanhe seu progresso</h3>
                    </div>

                    <div class="subscribe">
                        <button type="button" data-bs-toggle="modal" data-bs-target="#incluirPressao">
                            Cadastre uma nova pressão
                        </button>
                    </div>

                    <table class="table bg-light shadow">
                        <thead>
                            <tr>
                                <th scope="col">°</th>
                                <th scope="col">Pressão</th>
                                <th scope="col">Data de modificação</th>
                                <th scope="col">Edição</th>
                                <th scope="col">Excluir</th>
                            </tr>
                        </thead>

                        <tbody class="tabela-pressao">
							<c:set var="contador" value="0" />
                            <c:forEach var="currentP" items="${pressoes}">
                            	<tr class="line-w">
	                                <th scope="row">
	                                    <fmt:parseNumber type="number" value="${contador+1}" />
	                                </th>
	                                
	                                <td>
                                    	<fmt:formatNumber type="number" value="${currentP.getDiastolic()}" />
                                    </td>
                                    
                                    <td>
                                    	<fmt:formatNumber type="number" value="${currentP.getSystolic()}" />
                                    </td>
                                    
                                    <td>
                                   		<fmt:formatDate pattern="dd/MM/yyyy"
                                        	value="${currentP.getInclusionDate()}" />
                                    </td>
                                    
                                    <td>
                                        <button type="button" 
                                        	onclick="loadModalData('${contador}', '${currentP.getPressureCode()}')">
                                            <img id="editar" src="./_img/Icons/editar.png" />
                                    	</button>
                                    </td>
                                    
                                    <td>
                                    	<a href="AlterPressure?id=${currentP.getPressureCode()}">
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

    <!-- #region Modal new user-->
    <div class="modal fade" id="newUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5>Cadastro</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <div>
                        <input type="text" class="form-control" id="siatólica-cadastro"
                            placeholder="Pressão Sistólica" />
                    </div>
                    <div>
                        <input type="text" class="form-control" id="diastólica-cadastro"
                            placeholder="Pressão Diastólica" />
                    </div>
                    <div>
                        <input type="date" class="form-control" id="data-cadastro" placeholder="Data" />
                    </div>

                    <div class="cadastro-peso">
                        <button type="reset" class="btn btn-success" id="add-campo">
                            Cadastrar
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="imports/menuModal.jsp" %>
    	<%@ include file="imports/insertPModal.jsp" %>
    		 <%@ include file="imports/alterPModal.jsp" %>
    		 	<%@ include file="imports/notifyModal.jsp" %>

    </html>