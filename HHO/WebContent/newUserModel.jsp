<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            <input type="tel" maxlength="10" class="form-control" id="format-input" placeholder="Data de nascimento"
              oninput="this.value = DDMMYYYY(this.value, event)" />
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