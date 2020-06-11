Proceso Buscar
	Definir numeroBuscado Como Entero;
	Definir i Como Entero;
	Definir encontro Como Logico;
	Definir array Como Entero;
	Definir pos Como Entero;
	
	numeroBuscado <- 51;
	i <- 0;
	encontro <- Falso;
	Dimension array[14];
	
	array[0] <- 1;
	array[1] <- 3;
	array[2] <- 13;
	array[3] <- 15;
	array[4] <- 17;
	array[5] <- 19;
	array[6] <- 21;
	array[7] <- 23;
	array[8] <- 31;
	array[9] <- 34;
	array[10] <- 40;
	array[11] <- 51;
	array[12] <- 54;
	array[13] <- 68;
	
	Mientras  NO encontro Y i<14 Hacer
		Si numeroBuscado=array[i] Entonces
			encontro <- Verdadero;
			pos <- i+1;
		FinSi
		i <- i+1;
	FinMientras
	
	Si encontro Entonces
		Escribir 'El numero ',numeroBuscado,' se encuentra en la posicion ',pos;
	FinSi
FinProceso
