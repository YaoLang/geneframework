<%--
  Created by IntelliJ IDEA.
  User: kong
  Date: 15/8/27
  Time: 下午9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script type="text/javascript" src="/JqueryTest/jquery-2.1.1.js"></script>
<script type="text/javascript">
  $(function(){
    $(":input[name=uname]").blur(function(){
      var value = $(":input[name=uname]").val();
      $.ajax({
        url: "/JqueryTest/AjaxServlet",
        data: {val: value},
        async:true,
        cache:false,
        type:"POST",
        dataType:"json",
        success:function(){
          $("#l1").text("uname");
          alert("remove from uname");
        }
      });
    });

  });
</script>
    <title></title>
  <h1>hello</h1>
  username:<input type="text" name="uname"/><label id="l1"></label><br>
  password:<input type="password" name="password"/><label id="l2"></label><br>
  <input type="submit" value="submit"/>
</head>
<body>

</body>
</html>
