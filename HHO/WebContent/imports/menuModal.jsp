<div class="modal fade" id="menuModelBar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-fullscreen">
    <div id="modelContent" class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">MENU</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="icones-menu">
        <a href="Profile?user=${user.getCredential().getUserName()}">
          <img src="./_img/Icons/perfil.png" alt="Perfil" id="menu-icone" />
        </a>
        <a href="./metricas.jsp">
          <img src="./_img/Icons/metricas.png" alt="Métricas" id="menu-icone" />
        </a>
      </div>
      <div class="icones-menu">
        <a href="Measures?user=${user.getCredential().getUserName()}">
          <img src="./_img/Icons/btnPeso.png" alt="Peso" id="menu-icone" />
        </a>
        <a href="./pressao.jsp">
          <img src="./_img/Icons/btnPressao.png" alt="Perfil" id="menu-icone" />
        </a>
      </div>
      <div class="icones-menu">
        <a href="./atividades.jsp">
          <img src="./_img/Icons/exercicios.png" alt="Exercicios" id="menu-icone" />
        </a>
        <a href="./alimento.jsp">
          <img src="./_img/Icons/alimentacao.png" alt="Alimentação" id="menu-icone" />
        </a>
      </div>
      <div class="icones-menu">
        <a href="">
          <img src="./_img/Icons/suporte.png" alt="Perfil" id="menu-icone" />
        </a>
        <a href="SignOut">
          <img src="./_img/Icons/sair.png" alt="Perfil" id="menu-icone" />
        </a>
      </div>
    </div>
  </div>
</div>