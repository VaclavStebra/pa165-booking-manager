<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:pagetemplate>
    <jsp:attribute name="body">
       <form:form method="post" action="${pageContext.request.contextPath}/auth/signin/"
           modelAttribute="userLogin" cssClass="form-horizontal">

       <div class="form-group ${email_error?'has-error':''}">
         <form:label path="email" cssClass="col-sm-2 control-label"><fmt:message key="email" /></form:label>
         <div class="col-sm-10">
           <form:input path="email" cssClass="form-control"/>
           <form:errors path="email" cssClass="help-block"/>
         </div>
       </div>
        <div class="form-group ${password_error?'has-error':''}">
          <form:label path="password" cssClass="col-sm-2 control-label"><fmt:message key="password" /></form:label>
          <div class="col-sm-10">
            <form:password path="password" cssClass="form-control"/>
            <form:errors path="password" cssClass="help-block"/>
          </div>
        </div>
      <button class="btn btn-primary" type="submit"><fmt:message key="login" /></button>
    </form:form>
        <h3><fmt:message key="home.availableusers"/></h3>
        <table class="table">
            <thead>
                <tr>
                    <th><fmt:message key="email"/></th>
                    <th><fmt:message key="password"/></th>
                    <th><fmt:message key="admin"/></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>paolo@jennings.com</td>
                    <td>111</td>
                    <td><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
                </tr>
                <tr>
                    <td>dan@carter.com</td>
                    <td>111</td>
                    <td><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
                </tr>
                <tr>
                    <td>maa@nonu</td>
                    <td>111</td>
                    <td><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
                </tr>
                <tr>
                    <td>a@a.a</td>
                    <td>111</td>
                    <td><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></td>
                </tr>
            </tbody>
        </table>

    </jsp:attribute>
</my:pagetemplate>