<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
  <jsp:attribute name="body">
    <c:set var="endpoint" value="reservations" />

    <c:if test="${!sessionScope.user.admin}">
      <h1><fmt:message key="access.cmon" /></h1>
    </c:if>

    <my:admin>

      <h1>
        <fmt:message key="reservation.title.create" />
      </h1>

    <form:form method="post" action="${pageContext.request.contextPath}/${endpoint}/create" modelAttribute="reservationNew" cssClass="form-horizontal">

      <div class="form-group ${reservedFrom_error?'has-error':''}">
        <form:label path="reservedFrom" cssClass="col-sm-2 control-label"><fmt:message key="reservation.from"/></form:label>
        <div class="col-sm-10">
          <form:input path="reservedFrom" cssClass="form-control" />
          &nbsp;Use dd.MM.yyyy HH:mm format
          <form:errors path="reservedFrom" cssClass="help-block" />
        </div>
      </div>

      <div class="form-group ${reservedTo_error?'has-error':''}">
        <form:label path="reservedTo" cssClass="col-sm-2 control-label"><fmt:message key="reservation.to"/></form:label>
        <div class="col-sm-10">
          <form:input path="reservedTo" cssClass="form-control" />
          &nbsp;Use dd.MM.yyyy HH:mm format
          <form:errors path="reservedTo" cssClass="help-block" />
        </div>
      </div>

      <div class="form-group ${user_error?'has-error':''}">
        <form:label path="user" cssClass="col-sm-2 control-label"><fmt:message key="reservation.user"/></form:label>
        <div class="col-sm-10">
      <form:select path="user" name="user">
        <c:forEach items="${users}" var="user">
          <option value="${user.id}"><c:out value="${user.name}" />&nbsp;<c:out value="${user.surname}" /> &lt;<c:out value="${user.email}" />&gt;</option>
        </c:forEach>
      </form:select>
        </div>
      </div>

      <div class="form-group ${room_error?'has-error':''}">
        <form:label path="room" cssClass="col-sm-2 control-label"><fmt:message key="reservation.room"/></form:label>
        <div class="col-sm-10">
      <form:select path="room" name="room">
        <c:forEach items="${rooms}" var="room">
          <option value="${room.id}"><c:out value="${room.roomIdentifier}" /></option>
        </c:forEach>
      </form:select>
        </div>
      </div>

      <button class="btn btn-primary" type="submit"><fmt:message key="create"/></button>
    </form:form>

    </my:admin>
  </jsp:attribute>

</my:pagetemplate>
