<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                        <button class="perfilButton" data-bs-toggle="modal" data-bs-target="#newUser">
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
                                                            <img class="imageSize" src="./_img/Icons/nascimento.png" />

                                                            <div class="containerInput">
                                                                <label>Data de Nascimento:</label>

                                                                ${user.getBirthDate()}
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

                                                                ${usuario.getPhone().getNumber()}
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

        <div class="modal fade" id="newUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5>Cadastro</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <form action="SignUp" method="post">
                        <div class="modal-body">
                            <div class="col-12">
                                <div class="row">
                                    <div class="col-6">
                                        <input type="text" class="form-control" id="nome_user"
                                            placeholder="Nome Completo" />
                                    </div>
                                    <div class="col-6">
                                        <input type="text" class="form-control" id="cpf_user"
                                            placeholder="Nome do Usuário" />
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-6">
                                        <input type="tel" maxlength="10" class="form-control" id="format-input"
                                            placeholder="Data de nascimento"
                                            oninput="this.value = DDMMYYYY(this.value, event)" />
                                    </div>
                                    <div class="col-6">
                                        <input type="email" class="form-control" id="email_user" placeholder="E-mail" />
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-6">
                                        <input type="text" class="form-control" id="peso" placeholder="Peso" />
                                    </div>
                                    <div class="col-6">
                                        <input type="password" class="form-control" id="senha-antiga"
                                            placeholder="Senha antiga" />
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-6">
                                        <input type="text" class="form-control" id="altura" placeholder="Altura" />
                                    </div>
                                    <div class="col-6">
                                        <input type="password" class="form-control" id="nova-senha"
                                            placeholder="Nova Senha" />
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-6">
                                        <input type="text" class="form-control" id="cpf" placeholder="CPF" />
                                    </div>
                                    <div class="col-6">
                                        <input type="password" class="form-control" id="confirmar-senha"
                                            placeholder="Confirmar Nova Senha" />
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-6">
                                        <input type="text" class="form-control" id="cpf" placeholder="CPF" />
                                    </div>
                                    <div class="col-6">
                                        <input type="password" class="form-control" id="confirmar-senha"
                                            placeholder="Confirmar Nova Senha" />
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-3">
                                        <input name="sobrenome" type="text" class="form-control" id="sobrenome_user"
                                            placeholder="DDI" />
                                    </div>
                                    <div class="col-3">
                                        <input name="sobrenome" type="text" class="form-control" id="sobrenome_user"
                                            placeholder="DDD" />
                                    </div>
                                    <div class="col-6">
                                        <input name="sobrenome" type="text" class="form-control" id="sobrenome_user"
                                            placeholder="Telefone" />
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-6">
                                        <select name="sexo" class="form-control" id="format-input">
                                            <option value="F" selected>Feminino</option>
                                            <option value="M">Masculino</option>
                                            <option value="O">Outro</option>
                                        </select>
                                    </div>
                                    <div class="col-6">
                                        <input type="file" id="avatar" name="avatar" accept="image/png, image/jpeg">
                                    </div>
                                </div>

                                <div class="login">
                                    <button type="reset" class="btn btn-success"
                                        onclick="location.href='./perfil.html'">
                                        CONFIRMAR
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
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


    <div class="modal fade" id="newUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5>Cadastro</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <div>
                        <input type="text" class="form-control" id="nome_user" placeholder="Nome" />
                    </div>

                    <div>
                        <input type="text" class="form-control" id="sobrenome_user" placeholder="Sobrenome" />
                    </div>

                    <div>
                        <input type="tel" maxlength="10" class="form-control" id="format-input"
                            placeholder="Data de nascimento" oninput="this.value = DDMMYYYY(this.value, event)" />
                    </div>

                    <div>
                        <input type="text" class="form-control" id="cpf_user" placeholder="CPF"
                            onkeypress="$(this).mask('000.000.000-00');" />
                    </div>

                    <div>
                        <select class="form-control" id="format-input">
                            <option value="F">Feminino selected</option>
                            <option value="M">Masculino</option>
                            <option value="O">Outro</option>
                        </select>
                    </div>

                    <div>
                        <input type="text" class="form-control" id="peso" placeholder="Peso" />
                    </div>

                    <div>
                        <input type="text" class="form-control" id="altura" placeholder="Altura" />
                    </div>

                    <div>
                        <input type="text" class="form-control" id="sobrenome_user" placeholder="Telefone" />
                    </div>

                    <div>
                        <input type="email" class="form-control" id="email" placeholder="E-mail" />
                    </div>

                    <div>
                        <input type="email" class="form-control" id="email" placeholder="Confirme seu E-mail" />
                    </div>

                    <div>
                        <input type="password" class="form-control" id="senha" placeholder="Senha" />
                    </div>

                    <div>
                        <input type="password" class="form-control" id="senha" placeholder="Confirme sua senha" />
                    </div>


                    <div class="login">
                        <a href="./login.html">
                            <button type="reset" class="btn btn-success" onclick="location.href='./login.html'">
                                CONFIRMAR
                            </button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="imports/menuModal.jsp" %>

    </html>