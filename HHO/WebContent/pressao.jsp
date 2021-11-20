<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        
    <!DOCTYPE html>
    <html lang="pt-br">

    <head>
        <title>Pressão</title>
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
                        <h3 class="texto-pressao">Acompanhe seu progresso</h3>
                    </div>

                    <div class="subscribe">
                        <button type="button" data-bs-toggle="modal" data-bs-target="#newUser">
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

                            <tr id="linha1_tr">
                                <th scope="row">1</th>
                                <td>120/80mmHg</td>
                                <td>2021-11-13</td>
                                <td>
                                    <button type="button" data-bs-toggle="modal" data-bs-target="#newUser">
                                        <img id="editar" src="./_img/Icons/editar.png" />
                                    </button>
                                </td>
                                <td>
                                    <button type="button">
                                        <img id="editar" src="./_img/Icons/lixo.png" onclick="clicar('excluir1')" />
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

    <script>
        var contador = 3;
        $("#add-campo").click(
            function () {
                contador++;
                var sistolica = $('#sistólica-cadastro').val()
                var diastolica = $('#diastólica-cadastro').val()
                var data = $('#data-cadastro').val()
                $(".tabela-peso").append("<tr> <th scope='row'>" + contador + "</th>" + "<td>" + sistolica + "/" + diastolica + "mmHg</td>" + "<td>" + data + "</td>" + "<td type='button' data-bs-toggle='modal' data-bs-target='#newUser'>" + "<img id='editar'src='./_img/Icons/editar.png'  height='20px' />" + "</td>" + "<td>" + "<img id='editar'src='./_img/Icons/lixeira.png'  height='20px'/>" + "</td></tr>");
            }
        );
    </script>
    <script>
        function clicar(answer) {
            row_index = substring(answer, 1)
            row_to_delete = 'linha_' + row_index + '_tr'
            document.getElementById(row_to_delete).style.display = "none";
          
        }
    </script>

    <%@ include file="imports/menuModal.jsp" %>

    </html>