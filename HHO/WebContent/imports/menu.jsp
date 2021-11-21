<div class="row">
  <div class="col-12">
    <nav class="navbar navbar-light bg-light shadow">
      <div id="menuBar" class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="modal" data-bs-target="#menuModelBar">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div id="userPN">
          <label>${user.getCredential().getUserName()}</label>
          <img id="profileEdit" src="${user.getUserPhoto().getFileName()}" alt="Editar perfil" height="80px" />
        </div>
      </div>
    </nav>
  </div>
</div>