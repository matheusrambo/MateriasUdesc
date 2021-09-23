<?php
// include("main.php");


function getVar($nome) {
  $aux = $_POST[$nome];
  if (!isset($aux)) {
    $aux = $_GET[$nome];
  }
  return $aux;
}

if (isset($_GET['funcao'])) {
  $mysqli = new mysqli('localhost', 'root', '', 'academia');
if ($mysqli->connect_errno) {
  printf("Falha na Conexão: %s\n", $mysqli->connect_error);
  exit();
} else {
  echo "Deu certo!!";
}
  switch ($_GET['funcao']) {
    case "formInserir": formInserir(true); break;
    case "formAlterar": formInserir(false); break;
    case "inserir":	inserir($mysqli); break;
    case "alterar":	alterar($mysqli); break;
    case "deletar": deletar($mysqli); break;
  }
}

function inserir($mysqli){
  $nome = $_POST['nome'];
  $email = $_POST['email'];
  $telefone = $_POST['telefone'];
  $cep = $_POST['cep'];
  $modalidade = $_POST['modalidade'];
  $inicio = $_POST['inicio'];

    $erro = validar();
  if (!empty($erro)) {
    header('Location: ./main.php?erro='.$erro);
    exit;
  }


  $query = "INSERT INTO registro (nome, email, telefone, cep, modalidade, inicio) VALUES ('$nome', '$email', '$telefone', '$cep', '$modalidade', '$inicio')";
  echo "INSERT: $query<br><hr>";
  if($result = $mysqli->query($query)) {
    printf("inseriu", $result);
  } else {
    printf("nao inseriu");
  }
  header('Location: ./main.php?inseriu=1');
	exit;
}

function alterar($mysqli){
  $nome = $_POST['nome'];
  $email = $_POST['email'];
  $telefone = $_POST['telefone'];
  $cep = $_POST['cep'];
  $modalidade = $_POST['modalidade'];
  $inicio = $_POST['inicio'];
  $cod = $_GET['cod'];

  $query = "UPDATE registro set nome='$nome', email='$email', telefone='$telefone', cep='$cep', modalidade='$modalidade', inicio='$inicio' WHERE cod = '$cod'";
  echo "UPDATE: $query<br><hr>";
  if($result = $mysqli->query($query)) {
    printf("atualizou", $result);
  header('Location: ./mostrar.php?atualizou='.$cod);
} else {
    printf("nao atualizou");
  }
}

function validar() {
  $error = '';
  $nome = $_POST['nome'];
  if ($nome == '') {
    $error = $error.'Nome precisa ser informado </br>';
  }
  $email = $_POST['email'];
  $telefone = $_POST['telefone'];
  $cep = $_POST['cep'];
  $modalidade = $_POST['modalidade'];
  $inicio = $_POST['inicio'];
  $timestamp = strtotime($inicio);
  if (!$timestamp) {
    $error = $error.'Data de inicio das atividades precisa ser informada </br>';
  }
}

  $cep = $_POST['cep'];

function deletar($mysqli){
  $cod = $_GET['cod'];
  echo $_GET;
  $query = "DELETE from registro WHERE cod = '$cod'";
  echo "DELETE: $query<br><hr>";
  if($result = $mysqli->query($query)) {
    printf("deletou", $result);
  } else {
    printf("nao deletou");
  }
  header('Location: ./mostrar.php?deletado='.$cod);
	exit;
}

?>