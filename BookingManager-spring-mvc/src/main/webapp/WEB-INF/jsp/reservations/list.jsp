<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
  <jsp:attribute name="body">
    <c:set var="endpoint" value="reservations" />

    <script>
      function openModal(suffix) {
        var modal = $("#modal_" + suffix);
        if (modal)
          modal.modal('show');
      }
      function closeModal(suffix) {
        var modal = $("#modal_" + suffix);
        if (modal)
          modal.modal('hide');
      }
    </script>

    <h1><fmt:message key="reservations" /></h1>

    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th></th>
        <th><fmt:message key="reservation.from" /></th>
        <th><fmt:message key="reservation.to" /></th>
        <th><fmt:message key="reservation.user" /></th>
        <th><fmt:message key="reservation.room" /></th>
        <th><fmt:message key="reservation.hotel" /></th>

        <my:admin>
          <th><fmt:message key="edit" /></th>
          <th><fmt:message key="delete" /></th>
        </my:admin>
      </tr>
      </thead>
      <tbody>

      <c:forEach items="${reservations}" var="r" varStatus="loop">
        <tr>
          <td>
            <a href="${pageContext.request.contextPath}/${endpoint}/get/${r.id}">
              <c:out value="${loop.index+1}" />
            </a>
          </td>
          <td>
            <fmt:formatDate value="${r.reservedFrom}" pattern="dd.MM.yyyy HH:mm" />
          </td>
          <td>
            <fmt:formatDate value="${r.reservedTo}" pattern="dd.MM.yyyy HH:mm" />
          </td>
          <td>
            <c:out value="${r.user.email}" />
          </td>
          <td>
            <c:out value="${r.room.roomIdentifier}" />
          </td>
          <td>
            <c:out value="${r.room.hotel.name}" />
          </td>
          <my:admin>
          <td>
            <button class="btn btn-primary"
                onclick="location.href='${pageContext.request.contextPath}/${endpoint}/edit/${r.id}'">
              <fmt:message key="edit" />
            </button>
          </td>
          <td>
            <button class="glyphicon glyphicon-trash btn" onclick=" openModal(${r.id}) ">
            </button>

            <my:modal suffix="${r.id}" title="Delete Reservation">
              <jsp:attribute name="body">
                <strong><fmt:message key="reservation.delete.sure" /></strong>
              </jsp:attribute>
              <jsp:attribute name="footer">
                <form method="post"
                    action="${pageContext.request.contextPath}/${endpoint}/delete/${r.id}">
                  <input type="submit" class="btn btn-primary" value="Delete" />
                </form>
              </jsp:attribute>
            </my:modal>

          </td>
          </my:admin>
        </tr>
        </c:forEach>

      </tbody>
    </table>

    <my:admin>
    <button class="btn btn-primary"
        onclick="location.href='${pageContext.request.contextPath}/${endpoint}/create'">
      <fmt:message key="reservations.add" />
    </button>
    </my:admin>

  </jsp:attribute>

</my:pagetemplate>