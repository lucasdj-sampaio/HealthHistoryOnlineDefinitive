<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="pt-br">

    <head>
      <title>Login</title>
      <link href="style/login.css" rel="stylesheet" />
      <%@ include file="imports/head.jsp" %>
    </head>

    <header>
      <c:if test="${message.getSecond() != null}">
        <script>
          window.onload = function () {
            openModal('#notificacaoModal')
          }
        </script>
      </c:if>
    </header>

    <body>

      <div class="container-fluid">

        <div class="row">
          <div class="col-12">
            <nav class="navbar navbar-light bg-light shadow">
              <div id="menuBar" class="container-fluid">
                <h3 class="bold">HEALTH TRACK</h3>
                <h3>HOME</h3>
                <h3>PARCEIROS</h3>
                <h3>SOBRE</h3>
                <h3 class="bold">LOGIN</h3>
              </div>
            </nav>
          </div>
        </div>

        <div class="row col-xs-12 col-sm-12 col-md-12 col-lg-12">
          <div class="row">
            <div class="row justify-content-center">
              <div id="titleDiv" class="col sm-9 col-md-6 col-lg-6">
                <h1>Login</h1>
              </div>
            </div>

            <div class="row justify-content-center">
              <div class="col sm-9 col-md-6 col-lg-6" id="acessBox">
                <form action="SignIn" method="post">
                  <div>
                    <input name="usuario" type="text" class="form-control" id="email" placeholder="E-mail" required />
                  </div>

                  <div>
                    <input name="senha" type="password" class="form-control" id="pwd" placeholder="Senha" required />
                  </div>

                  <div>
                    <label><input type="checkbox" id="checkbox" /> Lembre-me</label>
                  </div>

                  <div class="acess">
                    <button type="submit" class="btn btn-primary">
                      <img id="acessar-icon" src="./_img/Icons/acessar.png" alt="Acessar" />
                      Acessar
                    </button>
                  </div>

                  <div class="subscribe">
                    <button type="button" data-bs-toggle="modal" data-bs-target="#newUser"><img id="cadastro-icon"
                        src="./_img/Icons/cadastro.png" alt="Cadastrar" />
                      Cadastrar
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </body>

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
                  <div class="col-4">
                    <input name="nome" type="text" class="form-control" id="format-input" placeholder="Nome" />
                  </div>
                  <div class="col-4">
                    <input name="sobrenome" type="text" class="form-control" id="format-input"
                      placeholder="Sobrenome" />
                  </div>
                  <div class="col-4">
                    <input name="username" type="text" class="form-control" id="format-input"
                      placeholder="NOME DE USU??RIO" />
                  </div>
                </div>


                <div class="col-12">
                  <div class="row">
                    <div class="col-6">
                      <input name="cpf" type="text" class="form-control" id="format-input" placeholder="CPF"
                        onkeypress="$(this).mask('000.000.000-00');" />
                    </div>
                    <div class="col-6">
                      <input name="datanasc" type="tel" maxlength="10" class="form-control" id="format-input"
                        placeholder="Data de nascimento" oninput="this.value = DDMMYYYY(this.value, event)" />
                      <div>
                      </div>
                    </div>

                    <div class="col-12">
                      <div class="row">
                        <div class="col-3">
                          <input name="ddi" type="text" class="form-control" id="format-input" placeholder="DDI" />
                        </div>
                        <div class="col-3">
                          <input name="ddd" type="text" class="form-control" id="format-input" placeholder="DDD" />
                        </div>
                        <div class="col-6">
                          <input name="telefone" type="text" class="form-control" id="format-input"
                            placeholder="Telefone" />
                        </div>
                      </div>
                    </div>

                    <div class="col-12">
                      <div class="row">
                        <div class="col-6">
                          <select name="sexo" class="form-control" id="format-input">
                            <option value="F" selected>Feminino</option>
                            <option value="M">Masculino</option>
                            <option value="O">Outro</option>
                          </select>
                        </div>
                        <div class="col-6">
                          <input name="email" type="email" class="form-control" id="format-input"
                            placeholder="E-mail" />
                        </div>
                      </div>
                    </div>


                    <div class="col-12">
                      <div class="row">
                        <div class="col-6">
                          <input name="senha" type="password" class="form-control" id="format-input"
                            placeholder="Senha" />
                        </div>
                        <div class="col-6">
                          <input name="confirmarSenha" type="password" class="form-control" id="format-input"
                            placeholder="Confirme sua senha" />
                        </div>
                      </div>
                    </div>

                    <div class="login">
                      <a href="./login.html">
                        <button type="submit" class="btn btn-success" id="btn-success"
                          onclick="location.href='./login.html'">
                          CONFIRMAR
                        </button>
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </form>
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

    <%@ include file="imports/menuModal.jsp" %>
      <%@ include file="imports/notifyModal.jsp" %>

    </html>