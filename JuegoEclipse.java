package Eclipse;

import java.util.Scanner;

public class JuegoEclipse {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int eleccion, personajeUno, personajeDos, personajeTres, personajeCuatro, opciones, variable;
		String personaje, enemigo;
		String[][] informacion = new String[6][7];
		boolean certeza = false;
		int[][] statsTotales = new int[6][4], movimientos = new int[6][4];
		String opcion;
		System.out.println("\n Saludos, este es un juego de batalla de hasta 5 personajes, que son los siguientes.");
		System.out.println("\t1er personaje: Eclipse.");
		System.out.println("\t2ndo personaje: Evil.");
		System.out.println("\t3er personaje: Cosmic.");
		System.out.println("\t4to personaje: Elina.");
		System.out.println("\t5to personaje: Keravnos.");
		System.out.println("\t6to personaje: ChuhZmR.");
		do {
			System.out.println(
					"\nAhora, dime que es lo que quieres hacer, ¿Preferirias establecer un modo de combate 1vs1 (pulsa '1'), un modo de combate 2v2 (pulsa '2'), preferirias ver la informacion de cada personaje? (pulsa '3'), o preferirias acabar este juego? (pulsa '4')");
			eleccion = sc.nextInt();
			informacion = informacion(informacion);
			switch (eleccion) {
			case 1:
				System.out.println(
						"¡¡¡Perfecto!!! ¿Cual personaje quieres escoger? Escoge pulsando un numero del 1 al 6.");
				personajeUno = sc.nextInt();
				personajeUno = Reeleccion(personajeUno, -999);
				personaje = "";
				enemigo = "";
				sysoPersonaje(personajeUno, personaje);
				personajeDos = randomizer(personajeUno, 0, 0, 0);
				sysoEnemigo(personajeDos, personaje);
				personaje = nombrePj(personajeUno, personaje);
				enemigo = nombrePj(personajeDos, enemigo);

				System.out.println(personaje + " V/s " + enemigo + ". ");

				System.out.println("\n¡¡¡Que comience el combate!!!");
				statsTotales = statsPersonajes(statsTotales);
				variable = 0;

				do {
					System.out.println("\nTus movimientos son:");
					muestraPersonaje(informacion, personajeUno);
					System.out.println("\n¿Qué harás, atacar, usar un objeto, o tirar una moneda?");
					sc.nextLine();
					opcion = sc.nextLine();
					opciones = eleccionIncorrecta(opcion, variable);
					switch (opciones) {
					case 1:
						System.out.println("Perfecto, ¿Qué movimiento vas a querer usar?");
						break;
					case 2:
						System.out.println("Perfecto, ¿Qué item vas a querer usar?");
						break;
					case 3:
						System.out.println("Perfecto, vamos a tirar tu moneda.");
						System.out.println(tirarMoneda());
						break;
					}
					variable++;
					System.out.println("\nFelicidades, has acabado el juego.");

				} while (statsTotales[personajeUno][0] > 0 || statsTotales[personajeDos][0] > 0);
				break;
			case 2:
				System.out.println(
						"¡¡¡Perfecto!!! ¿Cuál personaje quieres escoger? Escoge pulsando un número del 1 al 6.");
				personajeUno = sc.nextInt();
				personajeUno = Reeleccion(personajeUno, -999);
				personaje = "";
				sysoPersonaje(personajeUno, personaje);
				personajeDos = sc.nextInt();
				personajeDos = Reeleccion(personajeDos, personajeUno);
				sysoPersonaje(personajeDos, personaje);
				personajeTres = randomizer(personajeUno, personajeDos, 0, 0);
				sysoEnemigo(personajeTres, personaje);
				personajeCuatro = randomizer(personajeUno, personajeDos, personajeTres, 0);
				sysoEnemigo(personajeCuatro, personaje);
				break;
			case 3:

				System.out.println(
						"\nGenial, pulsa un numero del personaje del cual quieres buscar información, y a continuacion te mostrare su informacion.");
				personajeUno = sc.nextInt();
				personajeUno = Reeleccion(personajeUno, -999);
				personajeUno = personajeUno - 1;
				infoPJ(informacion, personajeUno);

				break;
			case 4:
				System.out.println(
						"\nMuchas gracias por haber jugado a este juego de batallas, ¡¡¡Espero que te haya gustado!!! :D");
				break;
			default:
				System.out.println(eleccion + "\n no es un numero de nuestro menu, vuelve a intentarlo.");
			}
		} while (eleccion != 4);
		sc.close();
	}
	
	public static String tirarMoneda () {
		String moneda;
		int max = 2;
		int min = 1;
		int range = (max-min) + min;
		int random = (int) ((range * Math.random()) + min);
		if (random == 1) {
			moneda = "Cara.";
		}
		else {
			moneda = "Cruz.";
		}
		return moneda;
	}

	public static int eleccionIncorrecta(String opcion, int variable) {
		
		int noSeComoLlamarEstaVariable = 0;
		int ola = 0;

		if (equalsParaGuillamon("atacar", opcion) == true) {

			noSeComoLlamarEstaVariable = 1;
			ola++;
		} else if (equalsParaGuillamon("usarunobjeto", opcion) == true) {

			noSeComoLlamarEstaVariable = 2;
			ola++;
		} else if (equalsParaGuillamon("tirarunamoneda", opcion) == true) {

			noSeComoLlamarEstaVariable = 3;
			ola++;
		}

		while (ola < 1) {
			System.out.println("\nError, tienes que escoger o 'Atacar', o 'Usar un objeto', o 'Tirar una moneda'.");
			Scanner sc = new Scanner(System.in);
			if (variable == 0) {
				opcion = sc.nextLine();
			}
			else {
				sc.nextLine();
				opcion = sc.nextLine();
			}
			if (equalsParaGuillamon("atacar", opcion) == true) {
				ola++;
				noSeComoLlamarEstaVariable = 1;
			} else if (equalsParaGuillamon("usarunobjeto", opcion) == true) {
				ola++;
				noSeComoLlamarEstaVariable = 2;
			} else if (equalsParaGuillamon("tirarunamoneda", opcion) == true) {
				ola++;
				noSeComoLlamarEstaVariable = 3;
			}
		}
		return noSeComoLlamarEstaVariable;
	}

	public static void muestraPersonaje(String[][] matriz, int personaje) {
		for (int i = 1; i < 6; i++) {
			System.out.println(matriz[personaje][i]);
		}
	}

	public static boolean equalsParaGuillamon(String texto, String palabra) {
		Scanner sc = new Scanner(System.in);
		boolean certeza;
		String palabraTransformada = "";
		int numero = 0;
		char letra;
		for (int i = 0; i < palabra.length(); i++) {
			letra = palabra.charAt(i);
			if (letra >= 'A' & letra <= 'Z') {
				numero = letra + 32;
				letra = (char) numero;
				palabraTransformada += letra;
			} else if (letra >= 'a' & letra <= 'z') {
				palabraTransformada += letra;
			}
		}
		certeza = (palabraTransformada.equals(texto));
		return certeza;
	}

	public static int[][] usabilidadMovs(int[][] informacion) {
		// 0: HP, 1: Ataque, 2: Defensa, 3: Velocidad.
		// 0: Eclipse, 1: Evil, 2: Cosmic, 3: Elina, 4: Keravnos, 5: ChuhZmR.
		informacion[0][0] = 1750;
		informacion[0][1] = 450;
		informacion[0][2] = 400;
		informacion[0][3] = 500;
		informacion[1][0] = 1750;
		informacion[1][1] = 500;
		informacion[1][2] = 300;
		informacion[1][3] = 450;
		informacion[2][0] = 1750;
		informacion[2][1] = 600;
		informacion[2][2] = 250;
		informacion[2][3] = 350;
		informacion[3][0] = 1750;
		informacion[3][1] = 400;
		informacion[3][2] = 500;
		informacion[3][3] = 550;
		informacion[4][0] = 1750;
		informacion[4][1] = 400;
		informacion[4][2] = 300;
		informacion[4][3] = 700;
		informacion[5][0] = 1750;
		informacion[5][1] = 400;
		informacion[5][2] = 400;
		informacion[5][3] = 400;

		return informacion;
	}

	public static int[][] statsPersonajes(int[][] informacion) {
		// 0: HP, 1: Ataque, 2: Defensa, 3: Velocidad.
		// 0: Eclipse, 1: Evil, 2: Cosmic, 3: Elina, 4: Keravnos, 5: ChuhZmR.
		informacion[0][0] = 1750;
		informacion[0][1] = 450;
		informacion[0][2] = 400;
		informacion[0][3] = 500;
		informacion[1][0] = 1750;
		informacion[1][1] = 500;
		informacion[1][2] = 300;
		informacion[1][3] = 450;
		informacion[2][0] = 1750;
		informacion[2][1] = 600;
		informacion[2][2] = 250;
		informacion[2][3] = 350;
		informacion[3][0] = 1750;
		informacion[3][1] = 400;
		informacion[3][2] = 500;
		informacion[3][3] = 550;
		informacion[4][0] = 1750;
		informacion[4][1] = 400;
		informacion[4][2] = 300;
		informacion[4][3] = 700;
		informacion[5][0] = 1750;
		informacion[5][1] = 400;
		informacion[5][2] = 400;
		informacion[5][3] = 400;

		return informacion;
	}

	public static String[][] informacion(String[][] informacion) {
		informacion[0][0] = "\nEclipse, este es el personaje principal y el héroe de este mundo, al igual que todos los personajes de este juego, sus habilidades son muy poderosas e interesantes. Procede de la raza 'Phaisa' y sus habilidades que lo caracterizan son la manipulacion de su propia energia, la invulnerabilidad de ataques fisicos y su Soulfuck.";
		informacion[0][1] = "Clonación: Eclipse usara esta habilidad para crear una cantidad indefinida de clones, pueden ser 1 clon como minimo, y 4 como maximo, solo durara 1 turno.";
		informacion[0][2] = "Stat-Boosts: Eclipse potenciara un unico apartado de sus stats, el stat en cuestion sera completamente aleatorio y no se puede elegir.";
		informacion[0][3] = "Soulfuck: este ataque consiste en una serie de movimientos que manipulan y atacan el alma del enemigo, y adquiere un bonus de poder de un x0.25 ante seres inmateriales como Cosmic o Evil.";
		informacion[0][4] = "Adapting: Eclipse aprende el ultimo ataque o movimiento que haya usado su enemigo, sin incluir Divine-Attacks.";
		informacion[0][5] = "Energy-Attacks: Eclipse lanza un orbe de energia contra el enemigo, existe una posibilidad del 20% de que ese ataque absorba la salud de su enemigo en favor del propio Eclipse.";
		informacion[0][6] = "Intangibility: Los ataques fisicos tienen un 20% de probabilidad de fallar y potenciar el ataque y la defensa de Eclipse.";
		informacion[1][0] = "\nEvil, este es el hermano gemelo de Eclipse, quien posee las mismas habilidades y hax que Eclipse, tambien es el rival malvado del mismo, asi que su fuerza de destruccion es mayor que la de Eclipse, pero Eclipse es mejor que Evil cuando se trata de inteligencia y durabilidad.";
		informacion[1][1] = "Mental Manipulation: Evil lanzara pequeños orbes de energia que llegaran a la mente del enemigo y hara que se ataque a si mismo, este movimiento solo funciona con ataques no-fisicos, y siempre se realizara despues de que el enemigo lance su ataque.";
		informacion[1][2] = "";
		informacion[1][3] = "Soulfuck: este ataque consiste en una serie de movimientos que manipulan y atacan el alma del enemigo, y adquiere un bonus de poder de un x0.25 ante seres inmateriales como Cosmic o Eclipse.";
		informacion[1][4] = "";
		informacion[1][5] = "";
		informacion[1][6] = "Intangibility: Los ataques fisicos tienen un 20% de probabilidad de fallar y potenciar el ataque y la defensa de Evil.";
		informacion[2][0] = "\nCosmic, el dios del universo de Eclipse, sus habilidades llegan hasta niveles divinos, como la habilidad 'Time-Warping' y demas, que se explicaran en el panel de personajes.";
		informacion[2][1] = "Body-Change: Cosmic cambiara de tipo, puede cambiar de tipo inmaterial a tipo fisico, y viceversa.";
		informacion[2][2] = "";
		informacion[2][3] = "";
		informacion[2][4] = "";
		informacion[2][5] = "";
		informacion[2][6] = "";
		informacion[3][0] = "\nElina, ella es otra de las heroinas de este mundo, su set de ataques consiste en la manipulacion del tiempo y la capacidad de atrapar al enemigo en dimensiones especiales, haciendo uso de las 'Cosmic-Skills', que le permite atacar fisicamente tanto a Eclipse como a Evil, atacar a dioses como Cosmic, y demas.";
		informacion[3][1] = "";
		informacion[3][2] = "";
		informacion[3][3] = "";
		informacion[3][4] = "";
		informacion[3][5] = "";
		informacion[3][6] = "Armor: Elina posee una armadura que la cubre ante ataques fisicos y ataques mentales, su defensa sube un 10% ante ataques fisicos y un 25% ante ataques mentales.";
		informacion[4][0] = "\nKeravnos, este es el mejor amigo de Eclipse, tiene habilidades de manipulacion del espacio-tiempo, Time-Stopping y demas.";
		informacion[4][1] = "Item-Warping: Keravnos alterara la composicion fisica de todos los items del enemigo, haciendo que los elementos curativos le resten vida al enemigo, y que los elementos que potencien los atributos fisicos de su enemigo, se los baje.";
		informacion[4][2] = "Cosmic Skill: Black Holes: Keravnos enviara al enemigo a una dimension donde este no puede moverse ni defenderse, Keravnos lanzara una orda de agujeros negros, el minimo de agujeros negros es de 2 agujeros, y el maximo de 6.";
		informacion[4][3] = "Cosmic Skill: Mental Illness: Keravnos enviara al enemigo a una dimension donde torturara mentalmente al enemigo mentalmente, quitándole vida y bajando ligeramente su defensa, este movimiento es mas destructivo contra seres como Cosmic, Eclipse o Evil, tambien existe una posbilidad del 15% de que el enemigo sufra trastornos cerebrales y pueda hacerse daño a si mismo.";
		informacion[4][4] = "Time-Stop: Keravnos usara sus poderes sobrenaturales para detener el tiempo por el siguiente turno, esta tecnica solo se puede usar una sola vez.";
		informacion[4][5] = "Fastering: Keravnos alterara el entorno de batalla, haciendo que los personajes mas rapidos sean los mas rapidos, esta habilidad dura 5 turnos en total.";
		informacion[4][6] = "Equitative: los ataques fisicos que se lance contra seres inmateriales como Cosmic, Eclipse o Evil les hara daño, ignorando la intangibilidad de estos gracias a su Reality-Warping.";
		informacion[5][0] = "\nChuhZmR, este es sin duda alguna el personaje mas desbalanceado del juego, tiende a ser perezoso y normalmente la suerte de este mismo es demasiado baja, por suerte, tiene habilidades como 'BeforeDay' o 'ClassRest' que lo convierten en alguien muy poderoso si lo sabes utilizar.";
		informacion[5][1] = "BeforeDay: El usuario permanecera en estado de reposo por unos 3 turnos, al finalizar ese estado de reposo, este se recuperara toda su vida, y todos sus stats aumentaran x3.";
		informacion[5][2] = "ClassRest: El usuario usará un turno para dormir, en este estado se recuperara el 50% de toda su vida.";
		informacion[5][3] = "Gambling: Este movimiento siempre se ejecutara antes que el movimiento del enemigo, aqui, el usuario debera escoger cual sera el movimiento que hara su enemigo, en caso de no acertar, recibira el daño, y si acierta, el enemigo recibira el daño de su mismo ataque con un bonus de x0.25.";
		informacion[5][4] = "Golpes normales: El usuario lanzara un golpe al enemigo, si, solamente eso.";
		informacion[5][5] = "Super-Perfect-Cell-Kamehameha: El usuario lanzara un kamehameha a la perfeccion al enemigo, capaz de destruir facilmente el sistema solar";
		informacion[5][6] = "Infinite: El usuario tiene una cantidad infinita de items a su disposicion.";

		return informacion;
	}

	public static String nombrePj(int personaje, String cadena) {
		if (personaje == 0) {
			cadena += "Eclipse";
		}
		if (personaje == 1) {
			cadena += "Evil";
		}
		if (personaje == 2) {
			cadena += "Cosmic";
		}
		if (personaje == 3) {
			cadena += "Elina";
		}
		if (personaje == 4) {
			cadena += "Keravnos";
		}
		if (personaje == 5) {
			cadena += "ChuhZmR";
		}
		return cadena;
	}

	public static void infoPJ(String[][] informacion, int numero) {
		System.out.println(informacion[numero][0]);
		System.out.println("Aqui te enseñare todas las habilidades de tu personaje. :)");
		for (int i = 1; i < 7; i++) {
			if (i == 6) {
				System.out.println("\nLa habilidad es ----> " + informacion[numero][i]);
			} else {
				System.out.println("\n" + i + ": " + informacion[numero][i]);
			}
		}

	}

	public static int Reeleccion(int primerPersonaje, int segundoPersonaje) {
		Scanner sc = new Scanner(System.in);
		--primerPersonaje;
		while (primerPersonaje < 0 || primerPersonaje > 5) {
			System.out.println(primerPersonaje
					+ " no es un numero asignado a los 6 personajes de nuestro roster, por favor, vuelve a intentarlo.");
			primerPersonaje = sc.nextInt();
		}
		if (segundoPersonaje != -999) {
			while (segundoPersonaje == primerPersonaje || segundoPersonaje < 0 || segundoPersonaje > 5) {
				if (segundoPersonaje == primerPersonaje) {
					System.out.println("Error, no se puede escoger dos veces al mismo personaje.");
				} else if (segundoPersonaje < 0 || segundoPersonaje > 5) {
					System.out.println(segundoPersonaje
							+ " no es un numero asignado a los 6 personajes de nuestro roster, por favor, vuelve a intentarlo.");
				}

				segundoPersonaje = sc.nextInt();
			}
		}

		return primerPersonaje;
	}

	public static int randomizer(int personaje1, int personaje2, int personaje3, int personaje4) {
		int max = 6;
		int min = 1;
		int range = (max - min) + min;
		int random = (int) ((range * Math.random()) + min);
		--random;
		while (random == personaje1 || random == personaje2 || random == personaje3 || random == personaje4) {
			random = (int) ((range * Math.random()) + min);
			--random;
		}
		return random;
	}

	public static void sysoPersonaje(int personaje, String cadena) {
		cadena = "Has escogido a... ";
		if (personaje == 0) {
			cadena += "Eclipse";
		}
		if (personaje == 1) {
			cadena += "Evil";
		}
		if (personaje == 2) {
			cadena += "Cosmic";
		}
		if (personaje == 3) {
			cadena += "Elina";
		}
		if (personaje == 4) {
			cadena += "Keravnos";
		}
		if (personaje == 5) {
			cadena += "ChuhZmR";
		}
		System.out.println(cadena);
	}

	public static void sysoEnemigo(int personaje, String cadena) {
		cadena = "Tu enemigo es... ";
		if (personaje == 0) {
			cadena += "Eclipse";
		}
		if (personaje == 1) {
			cadena += "Evil";
		}
		if (personaje == 2) {
			cadena += "Cosmic";
		}
		if (personaje == 3) {
			cadena += "Elina";
		}
		if (personaje == 4) {
			cadena += "Keravnos";
		}
		if (personaje == 5) {
			cadena += "ChuhZmR";
		}
		System.out.println(cadena);
	}

}
