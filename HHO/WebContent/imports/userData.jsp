<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="row">
      <div class="col-12">
        <div class="card">
          <div class="card-body">
            <div class="row">
              <div class="col-sm-3 col-md-3 col-lg-3">
                <div class="conteinerPerfil">
                  <img src="${user.getUserPhoto().getFileName()}" alt="Perfil" />

                  <label>${user.getName()} ${user.getLastName()}</label>
                </div>
              </div>

              <div class="col-sm-9 col-md-9 col-lg-9">
                <div class="conteinerDados">
                  <label>
                    <fmt:formatNumber type="number" pattern="### anos" value="${user.getAge()}" />
                  </label>

                  <c:choose>
                    <c:when test="${measure.getWeight() != null}">
                      <label>
                        <fmt:formatNumber type="number" pattern="##.## Kg" value="${measure.getWeight().getWeight()}" />
                      </label>
                    </c:when>
                    <c:otherwise>
                      <label>-- kg</label>
                    </c:otherwise>
                  </c:choose>

                  <c:choose>
                    <c:when test="${measure.getHeight() != null}">
                      <label>
                        <fmt:formatNumber type="number" pattern="##.## m" value="${measure.getHeight().getHeight()}" />
                      </label>
                    </c:when>
                    <c:otherwise>
                      <label>-- m</label>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>