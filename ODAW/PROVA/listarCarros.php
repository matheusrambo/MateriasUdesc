<html>

<head>
    <meta charset="utf-8">
    <title>Lista de Carros</title>
    <p><A HREF = "site.php"> Página Inicial </A></p>
    <h1>Lista dos Veículos Cadastrados</h1>
    <link rel="stylesheet" text="text/css" href="estilo.css">
</head>

<body>
    <?php
    session_start();
    if ((!isset($_SESSION['login']) == true) and (!isset($_SESSION['senha']) == true)) {
        unset($_SESSION['login']);
        unset($_SESSION['senha']);
        header('location:index.php');
    }

    $logado = $_SESSION['login'];
    ?>

    <?php

    $user = "root";
    $password = "";
    $database = "avaliacaoodaw";
    $hostname = "localhost";


    # Conecta com o servidor de banco de dados
    $conn = new mysqli($hostname, $user, $password, $database);
    $sql = "select * from locacaoCarro";

    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        // output data of each row
        while ($row = $result->fetch_assoc()) {
            echo "ID: " . $row["id"] ." |      MODELO: " . $row["modelo"] .  " |      MARCA: ". $row["marca"]. " |      PLACA: ". $row["placa"]. " |      ANO: ".$row["ano"]. " |      PREÇO DIÁRIA: ".$row["diaria"]."<br>";
        }
    } else {
        echo "0 results";
    }
    $conn->close();


    ?>
</body>

</html>