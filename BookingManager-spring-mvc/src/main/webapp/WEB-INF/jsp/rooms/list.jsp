<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Rooms">
  <jsp:attribute name="body">
    <c:set var="endpoint" value="rooms" />

    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th>Room identifier</th>
        <th>Hotel</th>
        <th>Capacity</th>
        <th>Price</th>

        <my:admin>
          <th>Edit</th>
          <th>Delete</th>
        </my:admin>
      </tr>
      </thead>
      <tbody>

      <c:forEach items="${rooms}" var="room">
        <tr>
          <td>
            <a href="/${endpoint}/get/${room.id}">
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
              Edit
            </button>
          </td>
          <td>
            <form method="post"
                action="${pageContext.request.contextPath}/${endpoint}/delete/${room.id}">
              <input type="submit" class="btn btn-primary" value="Delete" />
            </form>
          </td>
          </my:admin>
        </tr>
        </c:forEach>

      </tbody>
    </table>

    <my:admin>
    <button class="btn btn-primary"
        onclick="location.href='${pageContext.request.contextPath}/${endpoint}/create'">
      Add Room
    </button>
    </my:admin>

  </jsp:attribute>

</my:pagetemplate>