<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <?php
  session_start();
  if ((!isset($_SESSION['login']) == true) and (!isset($_SESSION['senha']) == true)) {
    unset($_SESSION['login']);
    unset($_SESSION['senha']);
    header('location:index.php');
  }

  $logado = $_SESSION['login'];
  ?>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>Locação de Veículo </title>
</head>

<body>
  <div id="local" align=center>
    <h1>Formulário cadastro de Veículos</h1>
    <form name="cadastrar" method="post" action="cadastrarCarro.php">
      <table id="tab_cadastro">
        <tr>
          <td>Modelo:</td>
          <td><input type="text" name="modelo" required placeholder="MODELO do carro" id="modelo" class="txt" /></td>
        </tr>
        <tr>
          <td>Marca:</td>
          <td><input type="text" name="marca" required placeholder="MARCA do carro" id="marca" class="txt" /></td>
        </tr>
        <tr>
          <td>Placa:</td>
          <td><input type="text" name="placa" required placeholder="PLACA do carro" id="placa" class="txt" /></td>
        </tr>
        <tr>
          <td>Ano:</td>
          <td><input type="text" name="ano" required placeholder="ANO do carro" id="ano" class="txt" /></td>
        </tr>
        <tr>
          <td>Preço da Diária:</td>
          <td><input type="text" name="diaria" required placeholder="DIÁRIA do carro" id="diaria" class="txt" /></td>
        </tr>
        <tr>
          <td colspan="2" align=right><input type="submit" value="Cadastrar" name="go" id="botao_cad"></td>
        </tr>
      </table>
    </form>

    <p><A HREF = "listarCarros.php"> Listar Carros Cadastrados </A></p>
    <p><A HREF = "listarUsuarios.php"> Listar Usuários Cadastrados </A></p>
    <br>
  </div>

  </div>
</body>

</html>
