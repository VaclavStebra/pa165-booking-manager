<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
  <jsp:attribute name="body">
    <c:set var="endpoint" value="reservations" />

    <my:loggedUser>

      <h1>
        <fmt:message key="reservation.title.edit" />
      </h1>

    <form:form method="post" action="${pageContext.request.contextPath}/${endpoint}/${method}/${reservationId}" modelAttribute="reservationEdit" cssClass="form-horizontal">

      <div class="form-group ${from_error?'has-error':''}">
        <form:label path="from" cssClass="col-sm-2 control-label"><fmt:message key="reservation.from"/></form:label>
        <div class="col-sm-10">
          <form:input path="from" cssClass="form-control" />
          &nbsp;Use dd.MM.yyyy HH:mm format
          <form:errors path="from" cssClass="help-block" />
        </div>
      </div>

      <div class="form-group ${to_error?'has-error':''}">
        <form:label path="to" cssClass="col-sm-2 control-label"><fmt:message key="reservation.to"/></form:label>
        <div class="col-sm-10">
          <form:input path="to" cssClass="form-control" />
          &nbsp;Use dd.MM.yyyy HH:mm format
          <form:errors path="to" cssClass="help-block" />
        </div>
      </div>

      <button class="btn btn-primary" type="submit"><fmt:message key="edit"/></button>
    </form:form>

    </my:loggedUser>
  </jsp:attribute>

</my:pagetemplate>
