<%@ tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Кнопка удаления показывается только если:
1) пользователь аутентифицирован (authUser!=null);
2) передано текущее объявление (ad!=null);
3) id автора объявленния и id аутентифицированного пользователя
совпадают --%>
<c:if
        test="${sessionScope.authUser!=null}">
    <div
            style="background-color: #a0c8ff; border: 1px solid lavender; border-radius: 30px; float: right; margin: 10px; margin-top: 20px; padding: 5px 0px; text-align: center; width: 150px;">
        <a href="<c:url value="/doDeleteAll.jsp" />">Удалить все</a>
    </div>
</c:if>