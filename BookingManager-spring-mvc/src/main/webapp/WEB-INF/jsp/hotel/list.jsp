<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="hotels.show.title"/>
<my:pagetemplate title="${title}">
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

    <table class="table">
        <thead>
            <tr>
                <th><fmt:message key="hotel.name"/></th>
                <th><fmt:message key="hotel.address"/></th>
                <th><fmt:message key="hotel.phone"/></th>
                <th><fmt:message key="hotel.rooms.count"/></th>
                <th></th>
                <my:admin>
                  <th><fmt:message key="edit" /></th>
                  <th><fmt:message key="delete" /></th>
                </my:admin>
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
                    <my:admin>
                      <td>
                        <button class="btn btn-primary"
                            onclick="location.href='${pageContext.request.contextPath}/hotels/edit/${hotel.id}'">
                            <fmt:message key="edit" />
                        </button>
                      </td>
                      <td>
                        <button class="glyphicon glyphicon-trash btn" onclick=" openModal(${hotel.id}) ">
                        </button>

                        <my:modal suffix="${hotel.id}" title="Delete Hotel">
                          <jsp:attribute name="body">
                            <strong><fmt:message key="hotel.delete.sure" /><c:out value="${hotel.name}" /></strong>
                          </jsp:attribute>
                          <jsp:attribute name="footer">
                            <form method="post"
                                action="${pageContext.request.contextPath}/hotels/delete/${hotel.id}">
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
        onclick="location.href='${pageContext.request.contextPath}/hotels/create'">
      <fmt:message key="hotel.add" />
    </button>
    </my:admin>

</jsp:attribute>
</my:pagetemplate>