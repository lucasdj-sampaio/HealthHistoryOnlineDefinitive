<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="pt-br">

    <head>
        <title>Métricas</title>
        <link href="style/metricas.css" rel="stylesheet" />
        <link href="style/userData.css" rel="stylesheet" />
        <%@ include file="head.jsp" %>
    </head>

    <body>
        <header>
            <%@ include file="menu.jsp" %>
        </header>

        <main>
            <div class="container-fluid">
                <form>
                    <div id="hoLabel" class="col sm-10 col-md-10 col-lg-10">
                        <h3 class="texto-peso">Métricas</h3>
                    </div>
                </form>

                <div class="row" style="margin-top: 10px;">
                    <div class="col-sm-12 col-md-12 col-lg-6">
                        <%@ include file="userData.jsp" %>

                            <div class="row">
                                <div class="col-12 shadow">
                                    <div class="card">
                                        <div class="card-header">
                                            <div class="headerPressao">
                                                <img src="_img/Icons/pressao.png">

                                                <h1 class="subtitulo">Pressão arterial</h1>
                                            </div>
                                        </div>

                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-sm-12 col-md-12 col-lg-12">
                                                    <div class="container-data-weight">

                                                        <div class="container-weight">
                                                            <h2>Março</h2>
                                                            <div>
                                                                <p>--</p>
                                                            </div>
                                                        </div>

                                                        <div class="container-weight">
                                                            <h2>Abril</h2>
                                                            <div>
                                                                <p>--</p>
                                                            </div>
                                                        </div>

                                                        <div class="container-weight">
                                                            <h2>Maio</h2>
                                                            <div>
                                                                <p>--</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <img id="tabela-metrica" src="./_img/metricas/tabela.png"
                                                    alt="Editar perfil" />

                                                <div class="row" id="containerImc">
                                                    <label class="input-pressao">Situação atual:
                                                        <input id="username" disabled>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </div>

                    <div class="col-sm-12 col-md-12 col-lg-6">

                        <div class="row">
                            <div class="col-12 shadow">
                                <div class="card">
                                    <div class="card-header">
                                        <div class="headerPressao">
                                            <img src="_img/Icons/peso.png">

                                            <h1 class="subtitulo">Último peso cadastrado</h1>
                                        </div>
                                    </div>

                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-sm-12 col-md-12 col-lg-12">

                                                <div class="container-data-weight">
                                                    <div class="container-weight">
                                                        <h2>Janeiro</h2>
                                                        <div>
                                                            <p>--</p>
                                                        </div>
                                                    </div>

                                                    <div class="container-weight">
                                                        <h2>Fevereiro</h2>
                                                        <div>
                                                            <p>--</p>
                                                        </div>
                                                    </div>

                                                    <div class="container-weight">
                                                        <h2>Março</h2>
                                                        <div>
                                                            <p>--</p>
                                                        </div>
                                                    </div>

                                                    <div class="container-weight">
                                                        <h2>Abril</h2>
                                                        <div>
                                                            <p>--</p>
                                                        </div>
                                                    </div>

                                                    <div class="container-weight">
                                                        <h2>Maio</h2>
                                                        <div>
                                                            <p>--</p>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row" id="containerImc">
                                                    <label class="imcLabel">IMC:</label>

                                                    <div class="tabela-imc">
                                                        <img id="tabela-metrica" height="200px"
                                                            src="./_img/metricas/tabela-imc.png" />
                                                        <label class="input-pressao">Situação atual:
                                                            <input id="username" disabled>
                                                        </label>
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

    <%@ include file="menuModal.jsp" %>

    </html>