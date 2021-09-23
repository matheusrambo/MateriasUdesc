<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>resultado</title>
</head>

  <style>
    table, th, td {
      border: 1px solid black;
    }
  </style>
<?php

$mysqli = new mysqli('localhost', 'root', '', 'academia');
if ($mysqli->connect_errno) {
  printf("Falha na Conexão: %s\n", $mysqli->connect_error);
  exit();
}
$select = "SELECT * FROM registro";

?>
<?php if (isset($_GET['atualizou'])) { ?>
   <p style="color: red">Atualizou registro <?php echo $_GET['atualizou'] ?> </p>  <?php } ?>
 <?php if (isset($_GET['deletado'])) { ?>
      <p style="color: red">Registro deletado <?php echo $_GET['deletado'] ?>  </p> <?php } ?>
<body>
  
  <table>
    <tr>
      <th>nome</th>
      <th>email</th>
      <th>telefone</th>
      <th>cep</th>
      <th>modalidade  </th>
      <th>inicio</th>
    </tr></table>
      <?php 
      if($result = $mysqli->query($select)) {
        while ($linha = $result->fetch_row()) {
          
          ?>
          <tr>
            <td>
              <?php echo $linha[1]; ?>
            </td>
            <td>
              <?php echo $linha[2]; ?>
            </td>
            <td>
              <?php echo $linha[3]; ?>
            </td>
            <td>
              <?php echo $linha[4]; ?>
            </td>
            <td>
              <?php echo $linha[5]; ?>
            </td>
            <td>
              <a href="./registro.php?funcao=deletar&cod=<?php echo $linha[0]; ?>">excluir</a>
            </td>
            <td>
              <a href="./main.php?<?php echo http_build_query($linha) ?>">alterar registro</a>
            </td>
          </tr>
          <br>
    <?php 
  }
  }
      ?>
  </table>
  <a href="./main.php">voltar</a> 
</body>

<?php
mysqli_close($mysqli);
?>
</html>