<div class="modal fade" id="incluirPressao" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Cadastro</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <form action="Pressao" method="post" id="pressaoForm">
                    <div>
                        <input name="pressao" type="text" class="form-control" id="pressao-cadastro" placeholder="Pressão" />
                    </div>
                    <div>
                        <input name="dataInclusao" type="date" class="form-control" id="data-cadastro"
                            placeholder="Data" />
                    </div>
                    <div class="cadastro-pressao">
                        <button type="submit" class="btn btn-success" id="add-campo">
                            Cadastrar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>