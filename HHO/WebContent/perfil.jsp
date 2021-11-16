<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="pt-br">

    <head>
        <title>Perfil</title>
        <link href="style/perfil.css" rel="stylesheet" />
        <%@ include file="head.jsp" %>
    </head>

    <body>
        <header>
            <%@ include file="menu.jsp" %>
        </header>

        <main>
            <div class="row">
                <div class="col-12 d-grid gap-2">
                    <button class="perfilButton">
                        Perfil
                        <img src="./_img/Icons/editar.png" data-bs-toggle="modal" data-bs-target="#newUser"
                            id="editar-perfil" />
                    </button>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="container col-9" style="margin-top: 30px;">
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-sm-3 col-md-3 col-lg-3">
                                                <div class="conteinerPerfil">
                                                    <img src="./_img/Index/profile.jpg" alt="Perfil" />

                                                    <label>Nome do Usuário</label>
                                                </div>
                                            </div>

                                            <div class="col-sm-9 col-md-9 col-lg-9">
                                                <div class="conteinerDados">
                                                    <label>25 anos</label>

                                                    <label>67 kg</label>

                                                    <label>1,80m</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

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

                                                        email@email.com.br
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

                                                        000.000.000-00
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-12">
                                                <div class="containerIconInput">
                                                    <img class="imageSize" src="./_img/Icons/nascimento.png" />

                                                    <div class="containerInput">
                                                        <label>Data de Nascimento:</label>

                                                        00/00/0000
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-12">
                                                <div class="containerIconInput">
                                                    <img class="imageSize" src="./_img/Icons/telefone.png" />

                                                    <div class="containerInput">
                                                        <label>Telefone:</label>

                                                        (00) 00000-0000
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

        <div class="modal fade" id="newUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5>Cadastro</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body">
                        <div>
                            <input type="text" class="form-control" id="nome_user" placeholder="Nome Completo" />
                        </div>

                        <div>
                            <input type="text" class="form-control" id="cpf_user" placeholder="Nome do Usuário" />
                        </div>

                        <div>
                            <input type="email" class="form-control" id="email_user" placeholder="E-mail" />
                        </div>

                        <div>
                            <input type="password" class="form-control" id="senha-antiga" placeholder="Senha antiga" />
                        </div>

                        <div>
                            <input type="password" class="form-control" id="nova-senha" placeholder="Nova Senha" />
                        </div>

                        <div>
                            <input type="password" class="form-control" id="confirmar-senha"
                                placeholder="Confirmar Nova Senha" />
                        </div>

                        <div>
                            <input type="tel" maxlength="10" class="form-control" id="format-input"
                                placeholder="Data de nascimento" oninput="this.value = DDMMYYYY(this.value, event)" />
                        </div>

                        <div>
                            <input type="text" class="form-control" id="peso" placeholder="Peso" />
                        </div>

                        <div>
                            <input type="text" class="form-control" id="altura" placeholder="Altura" />
                        </div>

                        <div>
                            <input type="text" class="form-control" id="cpf" placeholder="CPF" />
                        </div>

                        <div>
                            <input type="text" class="form-control" id="telefone" placeholder="Telefone" />
                        </div>
                        <div>
                            <input type="file" id="avatar" name="avatar" accept="image/png, image/jpeg">
                        </div>

                        <div class="login">
                            <button type="reset" class="btn btn-success" onclick="location.href='./perfil.html'">
                                CONFIRMAR
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

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

        <%@ include file="menuModal.jsp" %>

    </html>