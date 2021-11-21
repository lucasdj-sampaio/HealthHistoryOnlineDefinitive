<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="modal fade" id="alterUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5>Editar Perfil</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <form action="Profile" method="post">
                    <div class="modal-body">
                        <div class="col-12">
                            <div class="row">
                                <div class="col-6">
                                    <input name="nome" value="${user.getName()}" type="text" class="form-control"
                                        id="form-control" placeholder="Nome" />
                                </div>
                                <div class="col-6">
                                    <input name="sobrenome" value="${user.getLastName()}" type="text"
                                        class="form-control" id="form-control" placeholder="Sobrenome" />
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-6">
                                    <input name="aniversario" value="${user.getBirthDate()}" type="tel" maxlength="10"
                                        class="form-control" id="form-control" placeholder="Data de nascimento"
                                        oninput="this.value = DDMMYYYY(this.value, event)" />
                                </div>
                                <div class="col-6">
                                    <input name="email" value="${user.getCredential().getMailAddress()}" type="email"
                                        class="form-control" id="form-control" placeholder="E-mail" />
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-6">
                                    <c:choose>
                                        <c:when test="${measure.getWeight() != null}">
                                            <input name="peso" value="${measure.getWeight().getWeight()}" type="text"
                                                class="form-control" id="form-control" placeholder="Peso" />
                                        </c:when>
                                        <c:otherwise>
                                            <input name="peso" type="text" class="form-control" id="form-control"
                                                placeholder="Peso" />
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-6">
                                    <input name="senha" type="password" class="form-control" id="form-control"
                                        placeholder="Senha antiga" />
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-6">
                                    <c:choose>
                                        <c:when test="${measure.getHeight() != null}">
                                            <input name="altura" value="${measure.getHeight().getHeight()}" type="text"
                                                class="form-control" id="form-control" placeholder="Altura" />
                                        </c:when>
                                        <c:otherwise>
                                            <input name="altura" type="text" class="form-control" id="form-control"
                                                placeholder="Altura" />
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-6">
                                    <input name="novaSenha" type="password" class="form-control" id="form-control"
                                        placeholder="Nova Senha" />
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-6">
                                    <input name="cpf" value="${user.getCpf()}" type="text" class="form-control"
                                        id="form-control" placeholder="CPF" />
                                </div>
                                <div class="col-6">
                                    <input name="confirmaSenha" type="password" class="form-control" id="form-control"
                                        placeholder="Confirmar Nova Senha" />
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-3">
                                    <input value="${user.getPhone().getDdiNumber()}" name="ddi" type="text"
                                        class="form-control" id="form-control" placeholder="DDI" />
                                </div>
                                <div class="col-3">
                                    <input value="${user.getPhone().getDddNumber()}" name="ddd" type="text"
                                        class="form-control" id="form-control" placeholder="DDD" />
                                </div>
                                <div class="col-6">
                                    <input value="${user.getPhone().getNumber()}" name="telefone" type="text"
                                        class="form-control" id="form-control" placeholder="Telefone" />
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-6">
                                    <select name="sexo" class="form-control" id="format-input">
                                        <c:choose>
                                            <c:when test="${user.getGender() == Feminino}">
                                                <option value="F" selected>Feminino</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="F">Feminino</option>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${user.getGender() == Masculino}">
                                                <option value="M" selected>Masculino</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="M">Masculino</option>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${user.getGender() == Outros}">
                                                <option value="O" selected>Outro</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="O">Outro</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </div>
                                <div class="col-6">
                                    <input name="arquivo" type="file" id="avatar" name="avatar"
                                        accept="image/png, image/jpeg">
                                </div>
                            </div>

                            <div class="login">
                                <button type="submit" class="btn btn-success" id="btn-success">
                                    CONFIRMAR
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>