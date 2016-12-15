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

    <h1><fmt:message key="reservation" /></h1>

    <table class="table table-striped table-hover">
      <tr>
        <th><fmt:message key="reservation.from" /></th>
        <td>
          <c:out value="${reservation.reservedFrom}" />
        </td>
      </tr>
      <tr>
        <th><fmt:message key="reservation.to" /></th>
        <td>
          <c:out value="${reservation.reservedTo}" />
        </td>
      </tr>
      <tr>
        <th><fmt:message key="reservation.user" /></th>
        <td>
          <c:out value="${reservation.user.name}" />&nbsp;<c:out value="${reservation.user.surname}" />
        </td>
      </tr>
      <tr>
        <th><fmt:message key="reservation.room" /></th>
        <td>
          <c:out value="${reservation.room.roomIdentifier}" />
        </td>
      </tr>

    </table>

    <my:admin>

            <button class="btn btn-primary"
                onclick="location.href='${pageContext.request.contextPath}/${endpoint}/edit/${reservation.id}'">
              <fmt:message key="edit" />
            </button>


            <button class="glyphicon glyphicon-trash btn" onclick=" openModal(${reservation.id}) ">
            </button>

            <my:modal suffix="${reservation.id}" title="Delete Reservation">
              <jsp:attribute name="body">
                <strong><fmt:message key="reservation.delete.sure" /></strong>
              </jsp:attribute>
              <jsp:attribute name="footer">
                <form method="post"
                    action="${pageContext.request.contextPath}/${endpoint}/delete/${reservation.id}">
                  <input type="submit" class="btn btn-primary" value="Delete" />
                </form>
              </jsp:attribute>
            </my:modal>

          </my:admin>

  </jsp:attribute>

</my:pagetemplate>