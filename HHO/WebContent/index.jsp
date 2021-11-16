<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html lang="pt-br">

  <head>
    <title>Login</title>
    <link href="style/login.css" rel="stylesheet" />
    <%@ include file="head.jsp" %>
  </head>

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
              <hi>Login</hi>
            </div>
          </div>

          <div class="row justify-content-center">
            <div class="col sm-9 col-md-6 col-lg-6" id="acessBox">
              <form>
                <div>
                  <input type="email" class="form-control" id="email" placeholder="E-mail" required="true" />
                </div>

                <div>
                  <input type="password" class="form-control" id="pwd" placeholder="Senha" required="true" />
                </div>

                <div>
                  <label><input type="checkbox" /> Lembre-me</label>
                </div>

                <div class="acess">
                  <a href="./inicio.html">
                    <button type="reset" class="btn btn-primary" onclick="location.href='./inicio.html'"> <img
                        id="acessar-icon" src="./_img/Icons/acessar.png" alt="Acessar" />
                      Acessar
                    </button>
                  </a>
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

  <%@ include file="newUserModel.jsp" %>

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

  </html>