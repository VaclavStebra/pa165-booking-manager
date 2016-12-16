<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
  <jsp:attribute name="body">
    <c:set var="endpoint" value="rooms" />

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

    <h1><fmt:message key="rooms" /></h1>

    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th><fmt:message key="room.roomIdentifier" /></th>
        <th><fmt:message key="room.hotel" /></th>
        <th><fmt:message key="room.capacity" /></th>
        <th><fmt:message key="room.price" /></th>

        <my:admin>
          <th><fmt:message key="edit" /></th>
          <th><fmt:message key="delete" /></th>
        </my:admin>
      </tr>
      </thead>
      <tbody>

      <c:forEach items="${rooms}" var="room">
        <tr>
          <td>
            <a href="${pageContext.request.contextPath}/${endpoint}/get/${room.id}">
              <c:out value="${room.roomIdentifier}" />
            </a>
          </td>
          <td>
            <c:out value="${room.hotel.name}" />
          </td>
          <td>
            <c:out value="${room.capacity}" />
          </td>
          <td>
            <c:out value="${room.pricePerNightPerPerson}" />
          </td>
          <my:admin>
          <td>
            <button class="btn btn-primary"
                onclick="location.href='${pageContext.request.contextPath}/${endpoint}/edit/${room.id}'">
              <fmt:message key="edit" />
            </button>
          </td>
          <td>
            <button class="glyphicon glyphicon-trash btn" onclick=" openModal(${room.id}) ">
            </button>

            <my:modal suffix="${room.id}" title="Delete Room">
              <jsp:attribute name="body">
                <strong><fmt:message key="room.delete.sure" /> <c:out value="${room.roomIdentifier}" /></strong>
              </jsp:attribute>
              <jsp:attribute name="footer">
                <form method="post"
                    action="${pageContext.request.contextPath}/${endpoint}/delete/${room.id}">
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
      <fmt:message key="rooms.add" />
    </button>
    </my:admin>

  </jsp:attribute>

</my:pagetemplate>