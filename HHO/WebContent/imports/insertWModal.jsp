<div class="modal fade" id="incluirPeso" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Cadastro</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <form action="Measures" method="post" id="medidaForm">
                    <div>
                        <input name="peso" type="number" class="form-control" id="peso-cadastro" placeholder="Peso" />
                    </div>
                    <div>
                        <input name="dataInclusao" type="date" class="form-control" id="data-cadastro"
                            placeholder="Data" />
                    </div>
                    <div class="cadastro-peso">
                        <button type="submit" class="btn btn-success" id="add-campo">
                            Cadastrar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>