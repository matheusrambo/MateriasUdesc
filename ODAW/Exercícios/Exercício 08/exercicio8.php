<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Exercicio 08 - Formulário</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="main.js"></script>
  <style>
  form {
    text-align: center;
  }

  </style>
</head>
<body>

<?php
  $show = false;
  $VerifEmail = false;
  $VerifSenha = false;


  $arquivo;
  if(file_exists('./autenticacao.txt')) {
    $arquivo = './autenticacao.txt';
  } else {
    $open = fopen('./autenticacao.txt', 'w') or die('cannot open file');
    $arquivo = './autenticacao.txt';
  }
  $jsonLogin = json_decode(file_get_contents($arquivo), true);

  $nomeNaoEIgualAoGravado = '';
  $senhaoNaoEIgualGravada = '';
  $nomeEIgual = '';
  $senhaEIgual = '';

  if (!empty($_POST)) {

    if (($_POST['nome'] !== '') && ($_POST['sobrenome'] !== '') && ($_POST['senha'] !== '') &&
    ($_POST['descProb'] !== '') &&
    ($_POST['opt1'] !== '') &&
    ($_POST['radio1'] !== '') &&
    ($_POST['Categoria'] !== '')
    ) {
      $show = true;
    }

    if (isset($jsonLogin)) {
      if ($_POST['nome'] == $jsonLogin['login']) {
        $nomeEIgual = 'O nome '.$_POST['nome'].' é igual ao gravado: '.$jsonLogin['login'];
      } else {
        $nomeNaoEIgualAoGravado = 'O nome '.$_POST['nome'].' não é igual ao gravado: '.$jsonLogin['login'];
      }

      if (md5($_POST['senha']) == $jsonLogin['senha']) {
        $senhaEIgual =  'A senha '.$_POST['senha'].' é igual à gravada: '.$jsonLogin['senha'];
      } else {
        $senhaoNaoEIgualGravada = 'A senha '.$_POST['senha'].' não é igual à gravada: '.$jsonLogin['senha'];
      }
    }

    if (filter_var($_POST['sobrenome'], FILTER_VALIDATE_EMAIL)) {
      $VerifEmail = true;
    }
    if (strlen($_POST['senha']) >= 6) {
      $VerifSenha = true;

      $newLogin = array('login' => $_POST['nome'], 'senha' => md5($_POST['senha']));
      file_put_contents($arquivo, json_encode($newLogin));
    }
  }
  $location = "/exerc8.php?".http_build_query($_POST)."\n";
?>
<form action="<?php $location ?>" method="POST" id="form1" name="TCheck" onSubmit="verificaObrigatorios()">
      <h2>
        Login:
      </h2>

      <?php if ($nomeEIgual) { ?>
      <p class="obrigatorio" style="color: red"> <?php echo $nomeEIgual ?> </p> <?php }
        else {
      ?>
         <p class="obrigatorio" style="color: red"> <?php echo $nomeNaoEIgualAoGravado ?> </p>
        <?php } ?>

        <?php if ($senhaEIgual) { ?>
      <p class="obrigatorio" style="color: red"> <?php echo $senhaEIgual ?> </p> <?php }
        else {
      ?>
         <p class="obrigatorio" style="color: red"> <?php echo $senhaoNaoEIgualGravada ?> </p>
        <?php } ?>

      <?php if ($show) { ?>
      <p>Verifique os campos abaixo</p> <?php } ?>

      Nome: <input type="text" name="nome" value="<?php if($show) { echo $_POST['nome']; }?>"/> <br />

      <?php if (!empty($_POST) && $_POST['nome'] === '') { ?>
      <p class="obrigatorio" style="color: red">Campo NOME é obrigatorio</p> <?php } ?>


      Senha: <input type="password" name="senha"   value="<?php if($show) { echo $_POST['senha']; }?>"/> <br />
      <?php if (isset($_POST['senha']) && $_POST['senha'] === '') { ?>
      <p class="obrigatorio" style="color: red">Campo senha é obrigatorio</p> <?php } ?>
      <?php if (!$VerifSenha) { ?>
      <p>A senha precisa ter no minimo 6 caracteres</p> <?php } ?>

      Email: <input type="text" name="sobrenome" value="<?php if($show) { echo $_POST['sobrenome']; }?>"/> <br>
      <?php if (isset($_POST['sobrenome']) && $_POST['sobrenome'] === '') { ?>
      <p class="obrigatorio" style="color: red">Campo EMAIL é obrigatorio</p> <?php } ?>

      <hr>
      <p class="">Selecione o seu sexo:</p>
      <input checked type="radio" name="radio1" value="1"> Masculino <br>
      <input type="radio" name="radio1" value="2"> Feminino <br>
      <input type="radio" name="radio1" value="3"> Nenhum <br>
      <?php if (!isset($_POST['radio1']) && !empty($_POST)) { ?>
      <p class="obrigatorio" style="color: red">Campo Sexo é obrigatorio</p> <?php } ?>
      <hr/>

      <p>Cor Preferida:</p>
      <select name="Cor" class="grow">
        <option selected="selected" value="1">Azul</option>
        <option value="2">Vermelha</option>
        <option value="3">Verde</option>
        <option value="4">Branca</option>
        <option value="5">Preta</option>
      </select>
      <br/>
      <?php if (!isset($_POST['Cor']) && !empty($_POST)) { ?>
      <p class="obrigatorio" style="color: red">Campo COR é obrigatorio</p> <?php } ?>
      <br/>


      <p>Descrição</p>
      <textarea name="desc" id="textarea" class="grow" cols="60" rows="12"><?php if($show) { echo $_POST['descProb']; }?></textarea>
      <?php if (isset($_POST['desc']) && $_POST['descProb'] == '') { ?>
      <p class="obrigatorio" style="color: red">Campo de DESCRIÇÃO é obrigatorio</p> <?php } ?>
      <hr/>
      <div>
        <button class="button grow" type="submit" value="Submit" form="form1">
          Enviar
        </button>
        <input type="reset" name="resetar" value="Limpar"/>
      </div>
    </form>
</body>
</html>
