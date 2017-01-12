<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
  <jsp:attribute name="body">
    <c:set var="endpoint" value="hotels" />


    <c:if test="${!sessionScope.user.admin}">
      <h1><fmt:message key="access.cmon" /></h1>
    </c:if>

    <my:admin>

      <h1>
        <fmt:message key="hotel.title.create" />
      </h1>

    <form:form method="post" action="${pageContext.request.contextPath}/${endpoint}/" modelAttribute="hotel" cssClass="form-horizontal">

      <div class="form-group ${name_error?'has-error':''}">
        <form:label path="name" cssClass="col-sm-2 control-label"><fmt:message key="hotel.name"/></form:label>
        <div class="col-sm-10">
          <form:input path="name" cssClass="form-control" />
          <form:errors path="name" cssClass="help-block" />
        </div>
      </div>

      <div class="form-group ${address_error?'has-error':''}">
        <form:label path="address" cssClass="col-sm-2 control-label"><fmt:message key="hotel.address"/></form:label>
        <div class="col-sm-10">
          <form:input path="address" cssClass="form-control" />
          <form:errors path="address" cssClass="help-block" />
        </div>
      </div>

      <div class="form-group ${phone_error?'has-error':''}">
        <form:label path="phone" cssClass="col-sm-2 control-label"><fmt:message key="hotel.phone"/></form:label>
        <div class="col-sm-10">
          <form:input path="phone" cssClass="form-control" />
          <form:errors path="phone" cssClass="help-block" />
        </div>
      </div>


      <button class="btn btn-primary" type="submit"><fmt:message key="create"/></button>
    </form:form>

    </my:admin>
  </jsp:attribute>

</my:pagetemplate>
