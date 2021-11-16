<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="pt-br">

    <head>
        <title>Peso</title>
        <link rel="stylesheet" href="style/historico.css">
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
                            <tr>
                                <th scope="row">1</th>
                                <td>55,70kG</td>
                                <td>2021-11-13</td>
                                <td>
                                    <button type="button" data-bs-toggle="modal" data-bs-target="#newUser">
                                        <img id="editar" src="./_img/Icons/editar.png" />
                                    </button>
                                </td>
                                <td>
                                    <button type="button">
                                        <img id="editar" src="./_img/Icons/lixeira.png" onclick="clicar('excluir3')" />
                                    </button>
                                </td>
                            </tr>

                            <tr>
                                <th scope="row">2</th>
                                <td>56,00KG</td>
                                <td>2021-11-14</td>
                                <td>
                                    <button type="button" data-bs-toggle="modal" data-bs-target="#newUser">
                                        <img id="editar" src="./_img/Icons/editar.png" />
                                    </button>
                                </td>
                                <td>
                                    <button type="button">
                                        <img id="editar" src="./_img/Icons/lixeira.png" onclick="clicar('excluir3')" />
                                    </button>
                                </td>
                            </tr>

                            <tr>
                                <th scope="row">3</th>
                                <td>55,95KG</td>
                                <td>2021-11-15</td>
                                <td>
                                    <button type="button" data-bs-toggle="modal" data-bs-target="#newUser">
                                        <img id="editar" src="./_img/Icons/editar.png" />
                                    </button>
                                </td>
                                <td>
                                    <button type="button">
                                        <img id="editar" src="./_img/Icons/lixeira.png" onclick="clicar('excluir3')" />
                                    </button>
                                </td>
                            </tr>
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
                        <input type="text" class="form-control" id="peso-cadastro" placeholder="Peso" />
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
    <!-- #endregion -->

    <script>
        var contador = 3;
        $("#add-campo").click(
            function () {
                contador++;
                var peso = $('#peso-cadastro').val()
                var data = $('#data-cadastro').val()
                $(".tabela-peso").append("<tr> <th scope='row'>" + contador + "</th>" + "<td>" + peso + "KG</td>" + "<td>" + data + "</td></tr>");
            }
        );
    </script>

    <%@ include file="menuModal.jsp" %>

    </html>