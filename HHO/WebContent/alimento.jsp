<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="pt-br">

    <head>
        <title>Alimento</title>
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
                        <h3 class="texto-peso">Acompanhe sua alimentação</h3>
                    </div>

                    <div class="subscribe">
                        <button type="button" data-bs-toggle="modal" data-bs-target="#newUser">
                            Cadastre uma nova refeição
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
                                  <td data-bs-toggle="modal" data-bs-target="#newUser"><img id="editar"src="./_img/Icons/editar.png"  height="20px" /></td>
                                  <td ><img id="editar"src="./_img/Icons/lixeira.png"  onclick="clicar('excluir1')" height="20px" /></td> 
                            </tr>          
                   
                            <tr id="linha2_tr">
                                  <th scope="row">2</th>
                                  <td>2021-11-14</td>
                                  <td data-bs-toggle="modal" data-bs-target="#newfood"><img id="editar"src="./_img/Icons/visualizar.png"/></td> 
                                  <td data-bs-toggle="modal" data-bs-target="#newUser"><img id="editar"src="./_img/Icons/editar.png"  height="20px" /></td>
                                  <td ><img id="editar"src="./_img/Icons/lixeira.png"  onclick="clicar('excluir2')" height="20px" /></td>                 
                            </tr>

                            <tr  id="linha3_tr">
                              <th scope="row">3</th>
                              <td>2021-11-15</td>
                              <td data-bs-toggle="modal" data-bs-target="#newfood"><img id="editar"src="./_img/Icons/visualizar.png"/></td> 
                              <td data-bs-toggle="modal" data-bs-target="#newUser"><img id="editar"src="./_img/Icons/editar.png"  height="20px" /></td>
                              <td ><img id="editar"src="./_img/Icons/lixeira.png"  onclick="clicar('excluir3')" height="20px" /></td>            
                            </tr>             
                        </tbody>
                    </table>
                </form>
            </div>
        </main>
    </body>

    <!-- #region Modal food register--->
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
                        <select class="form-control" id="format-input" placeholder="Selecione a refeição" >
                            <option value="0">Café da manhã</option>
                            <option value="1">Almoço</option>
                            <option value="3">Café da tarde</option>
                            <option value="4">Jantar</option>
                            <option value="5">Ceia</option>     
                        </select>
                    </div>             
                    <div class="fake-input" id="formulario-alimento"> 
                        <input type="text" class="form-control" placeholder="Alimento">              
                        <img type="button" src="./_img/Icons/mais.png" alt="Editar perfil" id="add-alimento" width="20px" height="20px" />                   
                    </div>
                    <div>
                        <input type="text" class="form-control" id="format-input" placeholder="Kcal total da refeição"/>
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

    <!-- #region Modal return registration-->
    <div class="modal fade" id="newfood" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5>Cadastro</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div> 
                <div class="modal-body">        
                    <div class="data-cadastro">
                        <img src="./_img/Icons/calendario.png"/> 
                        <label>14.11.2021</label>
                    </div>
                    <div>
                        <img src="./_img/Icons/refeição.png"/> 
                        <label>Refeições:</label>
                    </div>
                    <div class="subtitulo-centro">
                        <label>Café da manhã</label>
                    </div>
                    <div class="subtitulo-centro">
                        <div class="alinhamento">             
                            <input class="input-cadastro" type="text" placeholder="pão" disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento">    
                            <input class="input-cadastro" type="text" placeholder="suco de laranja"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento">                   
                            <input class="input-cadastro" type="text" placeholder="torrada"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento"> 
                            <input class="input-cadastro"type="text" placeholder="manteiga"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento"> 
                            <input class="input-cadastro" type="text" placeholder="geléia"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento"> 
                            <input class="input-cadastro" type="text" placeholder="bolo de cenoura"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                    </div>

                    <div class="subtitulo-centro">
                        <label>Almoço</label>
                    </div>
                    <div class="subtitulo-centro">
                        <div class="alinhamento">             
                            <input class="input-cadastro" type="text" placeholder="Suco de abacaxi" disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento"> 
                            <input class="input-cadastro" type="text" placeholder="Arroz"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento"> 
                            <input class="input-cadastro" type="text" placeholder="Feijoada"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento"> 
                            <input class="input-cadastro" type="text" placeholder="Farofa"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento"> 
                            <input class="input-cadastro" type="text" placeholder="Couve"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento"> 
                            <input class="input-cadastro" type="text" placeholder="Mousse de chocolate"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                    </div>

                    <div class="subtitulo-centro">
                        <label>Café da tarde</label>
                    </div>
                    <div class="subtitulo-centro">
                        <div class="alinhamento">              
                            <input class="input-cadastro" type="text" placeholder="Café" disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento"> 
                            <input class="input-cadastro" type="text" placeholder="Torrada"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento"> 
                            <input class="input-cadastro" type="text" placeholder="geléia"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento"> 
                            <input class="input-cadastro" type="text" placeholder="pão"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento"> 
                            <input class="input-cadastro" type="text" placeholder="Requeijão"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>                   
                    </div>


                    <div class="subtitulo-centro">
                        <label>Jantar</label>
                    </div>
                    <div class="subtitulo-centro">
                        <div class="alinhamento">              
                            <input class="input-cadastro" type="text" placeholder="Sopa de legumes" disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>
                        <div class="alinhamento"> 
                            <input class="input-cadastro" type="text" placeholder="Suco de laranja"disabled>
                            <input class="input-cadastro" type="text" placeholder="52 Kcal" disabled>
                        </div>                              
                    </div>
                </div>                           
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
                $( "#formulario-alimento" ).append( "<input type='text' class='form-control'>" );          
        });
    </script>

    <%@ include file="menuModal.jsp" %>

    </html>