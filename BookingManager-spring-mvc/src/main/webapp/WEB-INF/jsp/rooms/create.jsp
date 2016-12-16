<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
  <jsp:attribute name="body">
    <c:set var="endpoint" value="rooms" />


    <c:if test="${!sessionScope.user.admin}">
      <h1><fmt:message key="access.cmon" /></h1>
    </c:if>

    <my:admin>

      <h1>
        <fmt:message key="room.title.create" />
      </h1>

    <form:form method="post" action="${pageContext.request.contextPath}/${endpoint}/create" modelAttribute="roomNew" cssClass="form-horizontal">

      <div class="form-group ${roomIdentifier_error?'has-error':''}">
        <form:label path="roomIdentifier" cssClass="col-sm-2 control-label"><fmt:message key="room.roomIdentifier"/></form:label>
        <div class="col-sm-10">
          <form:input path="roomIdentifier" cssClass="form-control" />
          <form:errors path="roomIdentifier" cssClass="help-block" />
        </div>
      </div>

      <div class="form-group ${hotel_error?'has-error':''}">
        <form:label path="hotel" cssClass="col-sm-2 control-label"><fmt:message key="room.hotel"/></form:label>
        <div class="col-sm-10">
      <form:select path="hotel" name="hotel">
        <c:forEach items="${hotels}" var="hotel">
          <option value="${hotel.id}"><c:out value="${hotel.name}" /></option>
        </c:forEach>
      </form:select>
        </div>
      </div>

      <div class="form-group ${pricePerNightPerPerson_error?'has-error':''}">
        <form:label path="pricePerNightPerPerson" cssClass="col-sm-2 control-label"><fmt:message key="room.price"/></form:label>
        <div class="col-sm-10">
          <form:input path="pricePerNightPerPerson" cssClass="form-control" type="number" step="any"/>
          <form:errors path="pricePerNightPerPerson" cssClass="help-block" />
        </div>
      </div>

      <div class="form-group ${capacity_error?'has-error':''}">
        <form:label path="capacity" cssClass="col-sm-2 control-label"><fmt:message key="room.capacity"/></form:label>
        <div class="col-sm-10">
          <form:input path="capacity" cssClass="form-control" type="number" step="any" />
          <form:errors path="capacity" cssClass="help-block" />
        </div>
      </div>


      <button class="btn btn-primary" type="submit"><fmt:message key="create"/></button>
    </form:form>

    </my:admin>
  </jsp:attribute>

</my:pagetemplate>
