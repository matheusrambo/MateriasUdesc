<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Formulário - Exercicio 10</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style>
    form {
    text-align: center;
    }
  
  </style>

</head>

<body>
<?php if (isset($_GET['inseriu'])) { ?>
   <p style="color: red">Inseriu <?php echo $_GET['inseriu'] ?> </p>  <?php } ?>
<?php if (isset($_GET['falha'])) { ?>
      <p style="color: red"> <?php echo $_GET['falha'] ?>   </p> <?php } ?>
<?php
  $show = false;
  $emailValid = false;
  $dataValid = false;
  if (isset($_GET[0])) {
    if (($_GET[1] !== '') && ($_GET[2] !== '') && ($_GET[3] !== '') &&
    ($_GET[4] !== '') &&
    ($_GET[5] !== '')) {
      $show = true;
    }
  }
  ?>
  <center>
    <hr>
    <a href="./mostrar.php">listar</a> 
    <hr>
    <?php if ($show) { ?>
      <p style="color: red">Formulario de edição</p> <?php } ?>
    <form action="<?php if (!$show) { echo "./registro.php?funcao=inserir"; } else { echo "./registro.php?funcao=alterar&cod=".$_GET[0];}?>" 
    method="POST" id="form1" name="form">
      <h3>Novo Registro</h3>
      Nome: <input type="text" name="nome" value="<?php if($show) { echo $_GET[1]; }?>">  <br>
      Email: <input type="text" name="email" value="<?php if($show) { echo $_GET[2]; }?>"> <br>
      CEP: <input type="text" name="cep" value="<?php if($show) { echo $_GET[2]; }?>"> <br>
      Telefone: <input type="tel" name="telefone" value="<?php if($show) { echo $_GET[3]; }?>"> <br>
      Modalidade: <select name="modalidade" id="">
        <option selected="<?php if( $_GET[5] == 2) echo 'selected'; else { echo 'erro';}?>" value="1">Funcional</option>
        <option selected="<?php if( $_GET[5] == 2) echo 'selected'; else { echo 'erro';}?>" value="2">HIIT</option>
        <option selected="<?php if( $_GET[5] == 2) echo 'selected'; else { echo 'erro';}?>" value="3">Musculacao</option>
        <option selected="<?php if( $_GET[5] == 2) echo 'selected'; else { echo 'erro';}?>" value="3">Zumba</option>
      </select> <br>
      Inicio de operações: <input type="date" name="inicio" value="<?php if($show) { ewho $_GET[4]; }?>"> <br>
      <button type="submit" value="Submit" form="form1">
        Enviar
      </button>
      <button type="reset" value="Reset">Cancelar</button>
      <br />
    </div>
    </form>
    </center>
</body>
</html>

<?php


?>