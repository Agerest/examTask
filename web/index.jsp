<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <p>${message}</p>
    <form action="${pageContext.request.contextPath}/" method="post">
      <p>дата</p><input type="date" name="date" value="2019-04-25">
      <p>income</p><input type="text" name="income">
      <p>consumption</p><input type="text" name="consumption">
      <p>description</p><input type="text" name="description">
      <input type="submit" value="Добавить">
    </form>
        <form action="${pageContext.request.contextPath}/" method="get">
            <p>Месяц</p><input type="month" name="month">
            <input type="submit" value="Отобразить">
        </form>

  </body>
</html>