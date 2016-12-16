<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
  <jsp:attribute name="body">

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

    <h1><fmt:message key="hotel" />&nbsp;${hotel.name}</h1>

    <table class="table table-striped table-hover">
        <tr>
            <th><fmt:message key="hotel.name" /></th>
            <td><c:out value="${hotel.name}" /></td>
        </tr>
        <tr>
            <th><fmt:message key="hotel.address" /></th>
            <td><c:out value="${hotel.address}" /></td>
        </tr>
        <tr>
            <th><fmt:message key="hotel.phone" /></th>
            <td><c:out value="${hotel.phone}" /></td>
        </tr>
    </table>

    <my:admin>

        <button class="btn btn-primary"
                onclick="location.href='${pageContext.request.contextPath}/hotels/edit/${hotel.id}'">
            <fmt:message key="edit" />
        </button>

        <button class="glyphicon glyphicon-trash btn" onclick=" openModal(${hotel.id}) ">
        </button>

        <my:modal suffix="${hotel.id}" title="Delete Hotel">
          <jsp:attribute name="body">
            <strong><fmt:message key="hotel.delete.sure" /> <c:out value="${hotel.name}" /></strong>
          </jsp:attribute>
          <jsp:attribute name="footer">
            <form method="post"
                  action="${pageContext.request.contextPath}/hotels/delete/${hotel.id}">
                <input type="submit" class="btn btn-primary" value="Delete" />
            </form>
          </jsp:attribute>
        </my:modal>

    </my:admin>

      <table class="table table-striped table-hover">
          <thead>
          <tr>
                <th><fmt:message key="room.roomIdentifier" /></th>
                <th><fmt:message key="room.hotel" /></th>
                <th><fmt:message key="room.capacity" /></th>
                <th><fmt:message key="room.price" /></th>
          </tr>
          </thead>

          <tbody>
          <c:forEach items="${rooms}" var="room">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/rooms/get/${room.id}">
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
              <c:if test="${!sessionScope.user.admin}">
                  <td>
                      <button class="btn btn-primary"
                              onclick="location.href='${pageContext.request.contextPath}/reservations/new/${room.id}'">
                          <fmt:message key="reserve" />
                      </button>
                  </td>
              </c:if>
            </tr>
        </c:forEach>

          </tbody>
      </table>

  </jsp:attribute>
</my:pagetemplate>
