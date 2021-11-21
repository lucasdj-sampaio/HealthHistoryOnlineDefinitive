<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <!DOCTYPE html>
        <html lang="pt-br">

        <head>
            <title>Perfil</title>
            <link href="style/perfil.css" rel="stylesheet" />
            <link href="style/userData.css" rel="stylesheet" />
            <%@ include file="imports/head.jsp" %>
        </head>

        <body>
            <header>
                <%@ include file="imports/menu.jsp" %>
            </header>

            <main>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12 d-grid gap-2">
                            <button class="perfilButton" data-bs-toggle="modal" data-bs-target="#alterUser">
                                Perfil
                                <img src="./_img/Icons/editar.png" id="editar-perfil" />
                            </button>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <div class="container col-9" style="margin-top: 30px;">
                                <%@ include file="imports/userData.jsp" %>

                                    <div class="row" style="margin-top: 30px;">
                                        <div class="col-12">
                                            <div class="card">
                                                <div class="card-body">
                                                    <div class="row">
                                                        <div class="col-12">
                                                            <div class="containerIconInput">
                                                                <img class="imageSize" src="./_img/Icons/email.png" />

                                                                <div class="containerInput">
                                                                    <label>E-mail:</label>

                                                                    ${user.getCredential().getMailAddress()}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-12">
                                                            <div class="containerIconInput">
                                                                <img class="imageSize" src="./_img/Icons/cpf.png" />

                                                                <div class="containerInput">
                                                                    <label>CPF:</label>

                                                                    ${user.getCpf()}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-12">
                                                            <div class="containerIconInput">
                                                                <img class="imageSize"
                                                                    src="./_img/Icons/nascimento.png" />

                                                                <div class="containerInput">
                                                                    <label>Data de Nascimento:</label>

                                                                    <fmt:formatDate pattern="dd/MM/yyyy"
                                                                        value="${user.getBirthDate()}" />
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-12">
                                                            <div class="containerIconInput">
                                                                <img class="imageSize"
                                                                    src="./_img/Icons/telefone.png" />

                                                                <div class="containerInput">
                                                                    <label>Telefone:</label>

                                                                    <fmt:formatNumber type="number" pattern="(##) "
                                                                        value="${user.getPhone().getDddNumber()}" />

                                                                    ${user.getPhone().getNumber()}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </body>
        <script>
            function DDMMYYYY(value, event) {
                let newValue = value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');

                const dayOrMonth = (index) => index % 2 === 1 && index < 4;

                // on delete key.  
                if (!event.data) {
                    return value;
                }

                return newValue.split('').map((v, i) => dayOrMonth(i) ? v + '/' : v).join('');;
            }
        </script>

        <%@ include file="imports/alterPModal.jsp" %>
            <%@ include file="imports/menuModal.jsp" %>

        </html>