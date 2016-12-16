<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="hotels.show.title"/>
<my:pagetemplate title="${title}">
<jsp:attribute name="body">
    <table class="table">
        <thead>
            <tr>
                <th><fmt:message key="hotel.name"/></th>
                <th><fmt:message key="hotel.address"/></th>
                <th><fmt:message key="hotel.phone"/></th>
                <th><fmt:message key="hotel.rooms.count"/></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${hotels}" var="hotel">
                <tr>
                    <td><c:out value="${hotel.name}"/></td>
                    <td><c:out value="${hotel.address}"/></td>
                    <td><c:out value="${hotel.phone}"/></td>
                    <td><c:out value="${roomsForHotel[hotel.id].size()}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/hotel/show/${hotel.id}">
                            <fmt:message key="hotel.list.rooms"/>
                       </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>