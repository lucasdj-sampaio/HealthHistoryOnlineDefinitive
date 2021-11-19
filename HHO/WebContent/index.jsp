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
            openModal()
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
                    <label><input type="checkbox" /> Lembre-me</label>
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
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5>Cadastro</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>

          <form action="SignUp" method="post">
            <div class="modal-body">
              <div>
                <input name="nome" type="text" class="form-control" id="nome_user" placeholder="Nome" />
              </div>

              <div>
                <input name="sobrenome" type="text" class="form-control" id="sobrenome_user" placeholder="Sobrenome" />
              </div>

              <div>
                <input name="numero" type="tel" maxlength="10" class="form-control" id="format-input"
                  placeholder="Data de nascimento" oninput="this.value = DDMMYYYY(this.value, event)" />
              </div>

              <div>
                <input name="cpf" type="text" class="form-control" id="cpf_user" placeholder="CPF"
                  onkeypress="$(this).mask('000.000.000-00');" />
              </div>

              <div>
                <select name="sexo" class="form-control" id="format-input">
                  <option value="F" selected>Feminino</option>
                  <option value="M">Masculino</option>
                  <option value="O">Outro</option>
                </select>
              </div>

              <div>
                <input name="sobrenome" type="text" class="form-control" id="sobrenome_user" placeholder="Telefone" />
              </div>

              <div>
                <input name="email" type="email" class="form-control" id="email" placeholder="E-mail" />
              </div>

              <div>
                <input name="confirmarEmail" type="email" class="form-control" id="email"
                  placeholder="Confirme seu E-mail" />
              </div>

              <div>
                <input name="senha" type="password" class="form-control" id="senha" placeholder="Senha" />
              </div>

              <div>
                <input name="confirmarSenha" type="password" class="form-control" id="senha"
                  placeholder="Confirme sua senha" />
              </div>

              <div class="login">
                <a href="./login.html">
                  <button type="submit" class="btn btn-success" onclick="location.href='./login.html'">
                    CONFIRMAR
                  </button>
                </a>
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

    <%@ include file="imports/notifyModal.jsp" %>

    </html>