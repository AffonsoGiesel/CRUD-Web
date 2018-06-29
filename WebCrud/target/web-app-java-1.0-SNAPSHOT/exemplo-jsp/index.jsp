<!DOCTYPE html>
<html>
  <head>
    <title>É maior ou menor?</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="bootstrap.min.css">
  </head>
<body style="margin-top: 15px">
  <div class="container">
    <div class="row">
      <div class="col-md-offset-4 col-md-4">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title"><b>Maior ou Menor</b></h3>
          </div>
          <div class="panel-body">
            <form>
                <div class="form-group">
                <input
                    name="Age"
                    type="number"
                    class="form-control"
                    placeholder="Age">
                </div>
                <button class="btn btn-default">Calculate</button>
            </form>
<%
//InÃ­cio do scriptlet.

//Pegar o parÃ¢metro "idade".
String idadeStr = request.getParameter("age");

String mensagem = "Informe a idade.";
if (idadeStr != null && !idadeStr.isEmpty()) {
    int idade = Integer.parseInt(idadeStr);
    if (idade >= 18) {
        mensagem = "18+";
    } else {
        mensagem = "Classificação Disney";
    }
}
%>
          </div>
          <div class="panel-footer">
            <%
            out.print(mensagem);
            %>
          </div>          
        </div>
      </div>
    </div>
  </div>
</body>
</html>