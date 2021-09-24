<html>

<head>
    <meta charset="utf-8">
    <title>Cadastro de Carros</title>
    <p><A HREF = "site.php"> Página Inicial </A></p>
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

    if (strlen($_POST['placa']) != 7) {
        echo "PLACA deve ter 7 caracteres";
    }
    else {
        $user = "root";
        $password = "";
        $database = "avaliacaoodaw";
        $hostname = "localhost";


        # Conecta com o servidor de banco de dados
        $conn = new mysqli($hostname, $user, $password, $database);
        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }
        // prepare and bind
        $stmt = $conn->prepare("INSERT INTO locacaoCarro (modelo, marca, placa, diaria, ano) VALUES (?, ?, ?, ?, ?)");
        $stmt->bind_param("sssss", $modelo, $marca, $placa, $diaria, $ano);

        $modelo = $_POST['modelo'];
        $marca = $_POST['modelo'];
        $placa = $_POST['placa'];
        $diaria = $_POST['diaria'];
        $ano = $_POST['ano'];

        $stmt->execute();

        echo "Novo carro Inserido";

        $stmt->close();
        $conn->close();
    }


    ?>
</body>

</html>
