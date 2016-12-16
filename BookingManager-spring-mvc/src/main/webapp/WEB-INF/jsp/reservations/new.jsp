<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
  <jsp:attribute name="body">

      <h1>
          <fmt:message key="reservation.title.create" />
      </h1>

    <form:form method="post" action="${pageContext.request.contextPath}/reservations/new/${roomId}" modelAttribute="reservation" cssClass="form-horizontal">

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

      <button class="btn btn-primary" type="submit"><fmt:message key="create"/></button>
    </form:form>

  </jsp:attribute>

</my:pagetemplate>
