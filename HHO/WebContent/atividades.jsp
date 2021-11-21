<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="pt-br">

    <head>
        <title>Atividades</title>
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
                                <th scope="col">Data de modificação</th>
                                <th scope="col">Visualização</th>
                                <th scope="col">Edição</th>
                                <th scope="col">Excluir</th>
                            </tr>
                        </thead>
                        
                        <tbody class="tabela-peso">                    
                            <tr id="linha1_tr">
                                <th scope="row">1</th>
                                <td>2021-11-13</td>
                                <td data-bs-toggle="modal" data-bs-target="#newfood"><img id="editar"src="./_img/Icons/visualizar.png"/></td> 
                                <td data-bs-toggle="modal" data-bs-target="#newUser"><img id="editar"src="./_img/Icons/editar.png" /></td>
                                <td ><img id="editar"src="./_img/Icons/lixo.png"  onclick="clicar('excluir2')" /></td>  
                            </tr>          
                   
                            <tr id="linha2_tr">
                                <th scope="row">2</th>
                                <td>2021-11-14</td>
                                <td data-bs-toggle="modal" data-bs-target="#newfood"><img id="editar"src="./_img/Icons/visualizar.png"/></td> 
                                <td data-bs-toggle="modal" data-bs-target="#newUser"><img id="editar"src="./_img/Icons/editar.png" /></td>
                                <td ><img id="editar"src="./_img/Icons/lixo.png"  onclick="clicar('excluir2')" /></td>                  
                            </tr>

                            <tr id="linha3_tr">
                                <th scope="row">3</th>
                                <td>2021-11-15</td>
                                <td data-bs-toggle="modal" data-bs-target="#newfood"><img id="editar"src="./_img/Icons/visualizar.png"/></td> 
                                <td data-bs-toggle="modal" data-bs-target="#newUser"><img id="editar"src="./_img/Icons/editar.png"/></td>
                                <td ><img id="editar"src="./_img/Icons/lixo.png"  onclick="clicar('excluir3')" /></td>             
                            </tr>             
                        </tbody>
                    </table>
                </form>
            </div>
        </main>
    </body>

    <!-- #region Modal new register-->
    <div class="modal fade" id="newUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5>Cadastro</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body" id="formulario">   
                    <div>
                        <input type="date" class="form-control" id="format-input" placeholder="Data"/>
                    </div>
                    <div>
                        <select class="form-control" onchange="HideTextBox();" id="format-input" placeholder="Selecione tipo de exercício" >
                            <option value="0">Selecione tipo de exercício</option>
                            <option value="1">Corrida</option>
                            <option value="2">Natação</option>
                            <option value="3">Yoga</option>
                            <option value="4">Alongamento</option>
                            <option value="5">Caminhada</option>
                            <option value="6">Corda</option>
                            <option value="7">Crossfit</option>
                            <option value="8">Bicicleta</option>
                            <option value="9">Patins</option>
                            <option value="10">Escada</option> 
                            <option value="11">Musculação</option>
                            <option value="12">Funcional</option>
                            <option value="13">Abdominal</option>
                            <option value="14">Luta</option>
                            <option value="15">Dança</option>
                            <option value="16">Pilates</option>
                            <option value="17">Outros</option>                             
                        </select>
                    </div>
                    <div>
                      <input type="text" class="form-control" id="format-input" placeholder="BPM"/>
                    </div>                      
                    <div>
                      <input type="text" class="form-control" id="format-input" placeholder="Kcal gasta"/>
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

        <!-- #region Modal new user-->
    <div class="modal fade" id="newfood" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <img src="./_img/Icons/icone-exercicio.png"/> 
                    <h5 class="titulo-modal">Exercícios praticados</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div> 
                <form action="SignUp" method="post">
                    <div class="modal-body">
                        <div class="col-12">
                            <div class="row">
                                <div class="col-12">
                                    <div id="centro-modal">
                                        <img src="./_img/Icons/calendario.png"/> 
                                        <label>14.11.2021</label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-5">
                                    <input class="input-cadastro" type="text" placeholder="Musculação" disabled>
                                </div>
                                <div class="col-3">
                                    <input class="input-cadastro" type="text" placeholder="250Kcal" disabled>
                                </div>
                                <div class="col-3">
                                    <input class="input-cadastro" type="text" placeholder="100.2bpm" disabled>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-5">
                                    <input class="input-cadastro" type="text" placeholder="Musculação" disabled>
                                </div>
                                <div class="col-3">
                                    <input class="input-cadastro" type="text" placeholder="250Kcal" disabled>
                                </div>
                                <div class="col-3">
                                    <input class="input-cadastro" type="text" placeholder="100.2bpm" disabled>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-5">
                                    <input class="input-cadastro" type="text" placeholder="Musculação" disabled>
                                </div>
                                <div class="col-3">
                                    <input class="input-cadastro" type="text" placeholder="250Kcal" disabled>
                                </div>
                                <div class="col-3">
                                    <input class="input-cadastro" type="text" placeholder="100.2bpm" disabled>
                                </div>
                            </div>                     
                        </div>
                    </div>
                </form>                 
            </div>                           
        </div>
    </div>
    <!-- #endregion -->

    <script>
    var contador = 3;
    $("#add-campo").click(
        function() {
            contador++;
            var sistolica = $('#siatólica-cadastro').val()
            var diastolica = $('#diastólica-cadastro').val()
            var data = $('#data-cadastro').val()
            $(".tabela-peso").append(`
                <tr id='linha` + contador + `_tr'>
                    <th scope='row'>` + contador + `</th>                   
                    <td>` + data + `</td>
                    <td  data-bs-toggle='modal' data-bs-target='#newfood'> <img id='editar'src='./_img/Icons/visualizar.png'/> </td>
                    <td data-bs-toggle='modal' data-bs-target='#newUser'><img id='editar'src='./_img/Icons/editar.png'  height='20px' /> </td>
                    <td> <img id='editar'src='./_img/Icons/lixeira.png' onclick="clicar('excluir` + contador + `')" height='20px'/> </td>
                </tr>`
            );
        }
    );
    </script>
    <script>
        function clicar(answer) {
            row_index = answer.substring(7, 8)
            row_to_delete = 'linha' + row_index + '_tr'
            document.getElementById(row_to_delete).style.display = "none";
        }
    </script> 

    <script>
            $( "#add-alimento" ).click(function() {
                $( "#formulario-alimento" ).append( "<input type='text'>" );          
        });
    </script>

    <%@ include file="imports/menuModal.jsp" %>

    </html>