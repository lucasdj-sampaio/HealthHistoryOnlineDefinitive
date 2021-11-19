<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <div class="modal fade" id="notificacaoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="headerModal">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="retornoModal">
            <div class="row">
              <c:if test="${message.getFirst().toString() == 'E'}">
                <img src="./_img/Icons/erro.png" />
              </c:if>
              <c:if test="${message.getFirst().toString() == 'W'}">
                <img src="./_img/Icons/aviso.png" />
              </c:if>
              <c:if test="${message.getFirst().toString() == 'S'}">
                <img src="./_img/Icons/sucesso.png" />
              </c:if>
            </div>
            <div class="row">
              <label>
                ${message.getSecond().toString()}
              </label>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>