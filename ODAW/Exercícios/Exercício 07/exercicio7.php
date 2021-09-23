<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>Exercicios de PHP (07)</title>
</head>

	<body>
		<?php
			//Exercicio 01 ----------------------------------------------------------------------------------------------------
		       setlocale( LC_ALL, 'pt_BR', 'portuguese' );
		       date_default_timezone_set( 'America/Sao_Paulo' );
		       echo strftime ('Hoje é: %e/%m/%Y e agora são %H:%Mh', strtotime('now'));

		    //Exercicio 02 ----------------------------------------------------------------------------------------------------
			  $cores=array("Rosa","Azul","Preto","Branco");
				$arrTam=count($cores);

					echo "<br> Cores: ";
				for($x=0;$x<$arrTam;$x++){
					echo $cores[$x];
					if(!function_exists('mensagem')){
						function mensagem(){
							echo " é uma cor";
						}
					}
					mensagem();
					if($x!=$arrTam-1)
						echo ", ";
					else
						echo ".";
				 }

		    //Exercicio 03 e 04 ----------------------------------------------------------------------------------------------------
		 		$arquivo = "contador.txt";
				$contador = 0;

				$arquivo = fopen($arquivo,"wr+");
				if ($arquivo == false) die('Não foi possível criar o arquivo.');
				$contador++;
				fwrite($arquivo, $contador);
				fclose($arquivo);

				session_start();
				$_SESSION["cont"]=$contador;

				echo nl2br("\nEsta página foi visitada ");
				echo $_SESSION["cont"];
				if ($contador == 1)
					echo (" vez.");
				else
					echo (" vezes.");

				unset($_SESSION["cont"]);
		?>

	</body>
</html>
