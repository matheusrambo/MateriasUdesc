<?php
// session_start inicia a sessão
session_start();
// as variáveis login e senha recebem os dados digitados na página anterior
$login = $_POST['login'];
$senha = $_POST['senha'];
$user = "root";
$password = "";
$database = "avaliacaoodaw";
$hostname = "localhost";

# Conecta com o servidor de banco de dados
$conn = new mysqli($hostname, $user, $password, $database);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
} else {
  echo "LOGADO";
}

$stmt = $conn->prepare("SELECT nome, senha from Usuarios where nome=? and senha=?");
$stmt->bind_param("ss", $nome, $senha);

$nome = $_POST['login'];
$senha = $_POST['senha'];

$stmt->execute();

$result = $stmt->get_result();
if ($result->fetch_array(MYSQLI_NUM) > 0) {
  $_SESSION['login'] = $login;
  $_SESSION['senha'] = $senha;
  header('location:site.php');
} else {
  unset($_SESSION['login']);
  unset($_SESSION['senha']);
  header('location:index.php');
}
