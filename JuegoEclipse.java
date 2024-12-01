package Eclipse;

import java.util.Scanner;

public class JuegoEclipse {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int eleccion, personajeUno, personajeDos, personajeTres, personajeCuatro, acción, variable, opciones;
		String personaje, personaje2, enemigo, enemigo2, opcion;
		String[][] informacion = new String[6][7];
		int[][] datosPersonajes, statsTotales;
		boolean certeza = false;
		statsTotales = new int[6][4];
		int[][] movimientos = new int[6][4];
		int[][] puntosMovimientos = new int[6][5];

		System.out.println("\n Saludos, este es un juego de batalla de hasta 5 personajes, que son los siguientes.");
		System.out.println("\t1er personaje: Eclipse.");
		System.out.println("\t2ndo personaje: Evil.");
		System.out.println("\t3er personaje: Cosmic.");
		System.out.println("\t4to personaje: Elina.");
		System.out.println("\t5to personaje: Keravnos.");
		System.out.println("\t6to personaje: ChuhZmR.");
		do {
			System.out.println(
					"\nAhora, dime qué es lo que quieres hacer, ¿Preferirias hacer un tutorial (pulsa '1')? ¿Preferirías establecer un modo de combate 1vs1 (pulsa '2'), un modo de combate 2v2 (pulsa '3'), preferirias ver la información de cada personaje? (pulsa '4'), o preferirías acabar este juego? (pulsa '5')");
			eleccion = sc.nextInt();
			personaje = "";
			enemigo = "";
			personaje2 = "";
			enemigo2 = "";
			informacion = informacion(informacion);
			switch (eleccion) {

			case 1:
				informacion = informacion(informacion);
				System.out.println(
						"¡¡¡Perfecto!!! ¿Cuál personaje quieres escoger para el tutorial? Escoge pulsando un número del 1 al 6.");
				personajeUno = sc.nextInt();
				personajeUno = Reeleccion(personajeUno, -999);
				personaje = nombrePj(personajeUno, personaje);
				System.out.println(
						"Bien. Acabas de empezar el tutorial con " + personaje + ", elige el movimiento que quieras.");
				infoMovsUsuario(personajeUno, informacion);
				acción = sc.nextInt();

			case 2:
				System.out.println(
						"¡¡¡Perfecto!!! ¿Cual personaje quieres escoger? Escoge pulsando un numero del 1 al 6.");
				personajeUno = sc.nextInt();
				personajeUno = Reeleccion(personajeUno, -999);
				personaje = "";
				enemigo = "";
				mensajePersonaje(personajeUno, personaje);
				personajeDos = randomizer(personajeUno, 0, 0, 0);
				mensajeEnemigo(personajeDos, enemigo);
				personaje = nombrePj(personajeUno, personaje);
				enemigo = nombrePj(personajeDos, enemigo);
				System.out.println(personaje + " V/s " + enemigo + ". ");

				System.out.println("\n¡¡¡Que comience el combate!!!");
				statsTotales = statsPersonajes(statsTotales);
				variable = 0;

				System.out.println("\nTus movimientos son:");
				muestraPersonaje(informacion, personajeUno);
				System.out.println("\n¿Qué harás, atacar, usar un objeto, o tirar una moneda?");
				sc.nextLine();
				opcion = sc.nextLine();
				opciones = eleccionIncorrecta(opcion, variable);

				do {
					if (variable > 0) {
						System.out.println("\nTus movimientos son:");
						muestraPersonaje(informacion, personajeUno);
						System.out.println("\n¿Qué harás, atacar, usar un objeto, o tirar una moneda?");
						opcion = sc.nextLine();
						opciones = eleccionIncorrecta(opcion, variable);
					}
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
			case 3:
				System.out.println(
						"¡¡¡Perfecto!!! ¿Cuál personaje quieres escoger? Escoge pulsando un número del 1 al 6.");
				personajeUno = sc.nextInt();
				personajeUno = Reeleccion(personajeUno, -999);
				mensajePersonaje(personajeUno, personaje);
				personajeDos = sc.nextInt();
				personajeDos = Reeleccion(personajeDos, personajeUno);
				mensajePersonaje(personajeDos, personaje);
				personajeTres = randomizer(personajeUno, personajeDos, 0, 0);
				mensajeEnemigo(personajeTres, personaje);
				personajeCuatro = randomizer(personajeUno, personajeDos, personajeTres, 0);
				mensajeEnemigo(personajeCuatro, personaje);
				personaje = nombrePj(personajeUno, personaje);
				personaje2 = nombrePj(personajeDos, personaje2);
				enemigo = nombrePj(personajeTres, enemigo);
				enemigo2 = nombrePj(personajeCuatro, enemigo2);
				System.out.println(personaje + " y " + personaje2 + " V/s " + enemigo + " y " + enemigo2);
				break;
			case 4:

				System.out.println(
						"\nGenial, pulsa un numero del personaje del cual quieres buscar información, y a continuacion te mostrare su informacion.");
				personajeUno = sc.nextInt();
				personajeUno = Reeleccion(personajeUno, -999);
				infoPJ(informacion, personajeUno);
				break;
			case 5:
				System.out.println(
						"\nMuchas gracias por haber jugado a este juego de batallas, ¡¡¡Espero que te haya gustado!!! :D");
				break;
			default:
				System.out.println(eleccion + "\n no es un número de nuestro menú, vuelve a intentarlo.");
			}
		} while (eleccion != 5);
		sc.close();
	}

	public static String tirarMoneda() {
		String moneda;
		int max = 2;
		int min = 1;
		int range = (max - min) + min;
		int random = (int) ((range * Math.random()) + min);
		if (random == 1) {
			moneda = "\nHa salido cara.";
		} else {
			moneda = "\nHa salido cruz.";
		}
		return moneda;
	}

	public static void muestraPersonaje(String[][] matriz, int personaje) {
		for (int i = 1; i < 6; i++) {
			System.out.println(matriz[personaje][i]);
		}
	}

	public static boolean equalsParaQueGuillamonNoMeMateUsandoElIgnoreCase(String texto, String palabra) {
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

	public static int eleccionIncorrecta(String opcion, int variable) {

		int noSeComoLlamarEstaVariable = 0;
		int ola = 0;

		if (equalsParaQueGuillamonNoMeMateUsandoElIgnoreCase("atacar", opcion) == true) {

			noSeComoLlamarEstaVariable = 1;
			ola++;
		} else if (equalsParaQueGuillamonNoMeMateUsandoElIgnoreCase("usarunobjeto", opcion) == true) {

			noSeComoLlamarEstaVariable = 2;
			ola++;
		} else if (equalsParaQueGuillamonNoMeMateUsandoElIgnoreCase("tirarunamoneda", opcion) == true) {

			noSeComoLlamarEstaVariable = 3;
			ola++;
		}

		while (ola < 1) {
			System.out.println("\nError, tienes que escoger o 'Atacar', o 'Usar un objeto', o 'Tirar una moneda'.");
			Scanner sc = new Scanner(System.in);
			opcion = sc.nextLine();
			if (equalsParaQueGuillamonNoMeMateUsandoElIgnoreCase("atacar", opcion) == true) {
				ola++;
				noSeComoLlamarEstaVariable = 1;
			} else if (equalsParaQueGuillamonNoMeMateUsandoElIgnoreCase("usarunobjeto", opcion) == true) {
				ola++;
				noSeComoLlamarEstaVariable = 2;
			} else if (equalsParaQueGuillamonNoMeMateUsandoElIgnoreCase("tirarunamoneda", opcion) == true) {
				ola++;
				noSeComoLlamarEstaVariable = 3;
			}
		}
		return noSeComoLlamarEstaVariable;
	}

	public static void infoMovsUsuario(int eleccionPersonaje, String[][] cadena) {
		if (eleccionPersonaje == 0) {
			for (int i = 0; i < 5; i++) {
				System.out.println("\n" + cadena[eleccionPersonaje][i]);
			}
		} else if (eleccionPersonaje == 1) {
			for (int i = 0; i < 5; i++) {
				System.out.println("\n" + cadena[eleccionPersonaje][i]);
			}
		} else if (eleccionPersonaje == 2) {
			for (int i = 0; i < 5; i++) {
				System.out.println("\n" + cadena[eleccionPersonaje][i]);
			}
		} else if (eleccionPersonaje == 3) {
			for (int i = 0; i < 5; i++) {
				System.out.println("\n" + cadena[eleccionPersonaje][i]);
			}
		} else if (eleccionPersonaje == 4) {
			for (int i = 0; i < 5; i++) {
				System.out.println("\n" + cadena[eleccionPersonaje][i]);
			}
		} else if (eleccionPersonaje == 5) {
			for (int i = 0; i < 5; i++) {
				System.out.println("\n" + cadena[eleccionPersonaje][i]);
			}
		}

	}

	public static int[][] usabilidadMovs(int[][] informacion) {
		// 0: HP, 1: Ataque, 2: Defensa, 3: Velocidad.
		// 0: Eclipse, 1: Evil, 2: Cosmic, 3: Elina, 4: Keravnos, 5: ChuhZmR.
		informacion[0][0] = 4;
		informacion[0][1] = 10;
		informacion[0][2] = 7;
		informacion[0][3] = 1;
		informacion[0][4] = 6;

		informacion[1][0] = 5;
		informacion[1][1] = 6;
		informacion[1][2] = 7;
		informacion[1][3] = 1; //Esta habilidad solo dura 3 turnos
		informacion[1][4] = 3; // Esta solo dura 4 turnos

		informacion[2][0] = 1; //Esta habilidad es infinita
		informacion[2][1] = 3;
		informacion[2][2] = 2; // esta dura 3 turnos y solo se puede usar despues de que el efecto de la anterior haya acabado
		informacion[2][3] = 7;
		informacion[2][4] = 5;

		informacion[3][0] = 1; // Esta dura infinitamente
		informacion[3][1] = 7;
		informacion[3][2] = 5;
		informacion[3][3] = 5;
		informacion[3][4] = 6;

		informacion[4][0] = 1;
		informacion[4][1] = 400;
		informacion[4][2] = 300;
		informacion[4][3] = 700;
		informacion[4][4] = 1;

		informacion[5][0] = 1750;
		informacion[5][1] = 400;
		informacion[5][2] = 400;
		informacion[5][3] = 400;
		informacion[5][4] = 1;

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

		informacion[0][0] = "\nEclipse, este es el personaje principal y el héroe de este mundo, al igual que todos los personajes de este juego, sus habilidades son muy poderosas e interesantes. Procede de la raza 'Phaisa' y sus habilidades que lo caracterizan son la manipulación de su propia energia, la invulnerabilidad de ataques físicos y su Soulfuck.";
		informacion[0][1] = "Clonación: Eclipse usará esta habilidad para crear una cantidad indefinida de clones, pueden ser 1 clon como mínimo, y 4 como máximo, solo durara 1 turno.";
		informacion[0][2] = "Stat-Boosts: Eclipse potenciará un único apartado de sus stats, el stat en cuestión sera completamente aleatorio y no se puede elegir.";
		informacion[0][3] = "Soulfuck: este ataque consiste en una serie de movimientos que manipulan y atacan el alma del enemigo, y adquiere un bonus de poder de un x0.25 ante seres inmateriales como Cosmic o Evil.";
		informacion[0][4] = "Adapting: Eclipse aprende el último ataque o movimiento que haya usado su enemigo, sin incluir Divine-Attacks.";
		informacion[0][5] = "Energy-Attacks: Eclipse lanza un orbe de energia contra el enemigo, existe una posibilidad del 20% de que ese ataque absorba la salud de su enemigo en favor del propio Eclipse.";
		informacion[0][6] = "Intangibility: Los ataques físicos tienen un 20% de probabilidad de fallar y potenciar el ataque y la defensa de Eclipse.";

		informacion[1][0] = "\nEvil, este es el hermano gemelo de Eclipse, quién posee las mismas habilidades y hax que Eclipse, también es el rival malvado del mismo, asi que su fuerza de destrucción es mayor que la de Eclipse, pero Eclipse es mejor que Evil cuando se trata de inteligencia y durabilidad.";
		informacion[1][1] = "Mental Manipulation: Evil lanzara pequeños orbes de energia que llegaran a la mente del enemigo y hara que se ataque a si mismo, este movimiento solo funciona con ataques no-fisicos, y siempre se realizara despues de que el enemigo lance su ataque.";
		informacion[1][2] = "Energy-Storm: Evil invoca una cantidad muy grande de energía desde el cielo, esta energia cae al suelo con la intención de arrasar con el enemigo.";
		informacion[1][3] = "Soulfuck: este ataque consiste en una serie de movimientos que manipulan y atacan el alma del enemigo, y adquiere un bonus de poder de un x0.25 ante seres inmateriales como Cosmic o Eclipse.";
		informacion[1][4] = "Phaisa-Shield: Evil crea una armadura de energia que se impregna alrededor de su cuerpo, aumentando su defensa, y quitándole vida a cualquier enemigo que use ataques fisicos contra el.";
		informacion[1][5] = "Parasit: Evil lanzará una esfera de energia contra su enemigo, esta se impregnará dentro del cuerpo del enemigo y comenzará a chuparle la vida, y Evil se curará en base a la energia robada del usuario. La habilidad dura 4 turnos.";
		informacion[1][6] = "Intangibility: Los ataques fisicos tienen un 20% de probabilidad de fallar y potenciar el ataque y la defensa de Evil.";

		informacion[2][0] = "\nCosmic, el dios del universo de Eclipse, sus habilidades llegan hasta niveles divinos, como la habilidad 'Time-Warping' y demás, que se explicarán en el panel de personajes.";
		informacion[2][1] = "Body-Change: Cosmic cambiará de tipo, puede cambiar de tipo inmaterial a tipo fisico, y viceversa.";
		informacion[2][2] = "Constraint: Cosmic usará sus poderes para inhabilitar uno de los movimientos de su enemigo por un período de 3 turnos, esta habilidad solo se podrá usar nuevamente cuando acabe el efecto de la anterior.";
		informacion[2][3] = "Cursed: Cosmic maldecirá al enemigo, bajando sus probabilidades de acertar sus ataques al 40%, esta habilidad dura 3 turnos y sólo se puede usar 2 veces.";
		informacion[2][4] = "Witness-Oblivion: Cosmic ataca al enemigo con su espada divina.";
		informacion[2][5] = "Divine-Flash: Cosmic invoca 2 orbes de energia que se fusionan entre sí y atacan al enemigo.";
		informacion[2][6] = "Inmutable Body: Los ataques que intenten alterar los stats de Cosmic van a ser inútiles.";

		informacion[3][0] = "\nElina, ella es otra de las heroínas de este mundo, es capaz de usar el medio ambiente a su favor, sus ataques constan del uso de elementos de la naturaleza, como los rayos, el hielo e incluso la energia vital de otros seres vivos.";
		informacion[3][1] = "Roots: Elina usará los recursos del medio ambiente y los transforma en energia vital de la cuál ella se va a nutrir, curándole la vida por cada turno.";
		informacion[3][2] = "Shell: Elina se protegera este turno, haciendo que cualquier ataque fisico o inmaterial del enemigo no surta efecto.";
		informacion[3][3] = "Ray-Beam: Elina invocara un rayo que le hara daño, pero subira un 50% tanto su defensa como ataque.";
		informacion[3][4] = "Frost-Attack: Elina endurece sus puños con guantes de temperaturas bajas y golpea con ellos al enemigo, las temperaturas mas altas del guante son de -20 grados, y las minimas de -100 grados, a menor temperatura, mayor sera el daño.";
		informacion[3][5] = "Hurricane: Elina invoca un huracan que ataca a su enemigo, existe un 15% de probabilidad de bajar la defensa del enemigo.";
		informacion[3][6] = "Armor: Elina posee una armadura que la cubre ante ataques fisicos y ataques mentales, su defensa sube un 10% ante ataques fisicos y un 25% ante ataques mentales.";

		informacion[4][0] = "\nKeravnos, este es el mejor amigo de Eclipse, su set de ataques consiste en la manipulación del tiempo y la capacidad de atrapar al enemigo en dimensiones especiales, haciendo uso de las 'Cosmic-Skills', que le permite atacar físicamente tanto a Eclipse como a Evil, atacar a dioses como Cosmic, y demás.";
		informacion[4][1] = "Item-Warping: Keravnos alterara la composición física de todos los ítems del enemigo, haciendo que los elementos curativos le resten vida al enemigo, y que los elementos que potencien los atributos fisicos de su enemigo, se los baje.";
		informacion[4][2] = "Cosmic Skill: Black Holes: Keravnos enviara al enemigo a una dimension donde este no puede moverse ni defenderse, Keravnos lanzara una orda de agujeros negros, el minimo de agujeros negros es de 2 agujeros, y el maximo de 6.";
		informacion[4][3] = "Cosmic Skill: Mental Illness: Keravnos enviara al enemigo a una dimension donde torturara mentalmente al enemigo mentalmente, quitándole vida y bajando ligeramente su defensa, este movimiento es mas destructivo contra seres como Cosmic, Eclipse o Evil, tambien existe una posbilidad del 15% de que el enemigo sufra trastornos cerebrales y pueda hacerse daño a si mismo.";
		informacion[4][4] = "Time-Stop: Keravnos usara sus poderes sobrenaturales para detener el tiempo por el siguiente turno, esta tecnica solo se puede usar una sola vez.";
		informacion[4][5] = "Fastering: Keravnos alterara el entorno de batalla, haciendo que los personajes mas rapidos sean los mas rapidos, esta habilidad dura 5 turnos en total.";
		informacion[4][6] = "Equitative: los ataques fisicos que se lance contra seres inmateriales como Cosmic, Eclipse o Evil les hara daño, ignorando la intangibilidad de estos gracias a su Reality-Warping.";

		informacion[5][0] = "\nChuhZmR, este es sin duda alguna el personaje mas desbalanceado del juego, tiende a ser perezoso y normalmente la suerte de este mismo es demasiado baja, por suerte, tiene habilidades como 'BeforeDay' o 'ClassRest' que lo convierten en alguien muy poderoso si lo sabes utilizar.";
		informacion[5][1] = "BeforeDay: El usuario permanecera en estado de reposo por unos 3 turnos, al finalizar ese estado de reposo, este se recuperara toda su vida, y todos sus stats aumentaran x3.";
		informacion[5][2] = "ClassRest: El usuario usará un turno para dormir, en este estado se recuperara el 50% de toda su vida.";
		informacion[5][3] = "Gambling: Este movimiento siempre se ejecutará antes que el movimiento del enemigo, aquí, el usuario deberá escoger cuál será el movimiento que hará su enemigo, en caso de no acertar, recibirá el daño, y si acierta, el enemigo recibirá el daño de su mismo ataque con un bonus de x0.25.";
		informacion[5][4] = "Golpes normales: El usuario lanzará un golpe al enemigo, si, solamente eso.";
		informacion[5][5] = "Super-Perfect-Cell-Kamehameha: El usuario lanzará un kamehameha a la perfección al enemigo, capaz de destruir facilmente el sistema solar";
		informacion[5][6] = "Infinite: El usuario tiene una cantidad infinita de ítems a su disposición.";

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
		System.out.println("Aquí te enseñaré todas las habilidades de tu personaje. :)");
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
			System.out.println((primerPersonaje + 1)
					+ " no es un numero asignado a los 6 personajes de nuestro roster, por favor, vuelve a intentarlo.");
			primerPersonaje = sc.nextInt();
			--primerPersonaje;
		}
		if (segundoPersonaje != -999) {
			while (segundoPersonaje == primerPersonaje || segundoPersonaje < 0 || segundoPersonaje > 5) {
				if (segundoPersonaje == primerPersonaje) {
					System.out.println("Error, no se puede escoger dos veces al mismo personaje.");
				} else if (segundoPersonaje < 0 || segundoPersonaje > 5) {
					System.out.println((segundoPersonaje + 1)
							+ " no es un numero asignado a los 6 personajes de nuestro roster, por favor, vuelve a intentarlo.");
				}
				--segundoPersonaje;
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

	public static void mensajePersonaje(int personaje, String cadena) {
		cadena = "Has escogido a... ";
		if (personaje == 0) {
			cadena += "Eclipse, ¿A quién más piensas elegir?";
		}
		if (personaje == 1) {
			cadena += "Evil, ¿A quién más piensas elegir?";
		}
		if (personaje == 2) {
			cadena += "Cosmic, ¿A quién más piensas elegir?";
		}
		if (personaje == 3) {
			cadena += "Elina, ¿A quién más piensas elegir?";
		}
		if (personaje == 4) {
			cadena += "Keravnos, ¿A quién más piensas elegir?";
		}
		if (personaje == 5) {
			cadena += "ChuhZmR, ¿A quién más piensas elegir?";
		}
		System.out.println(cadena);
	}

	public static void mensajeEnemigo(int personaje, String cadena) {
		cadena = "Tu enemigo es... ";
		if (personaje == 0) {
			cadena += "Eclipse.";
		}
		if (personaje == 1) {
			cadena += "Evil.";
		}
		if (personaje == 2) {
			cadena += "Cosmic.";
		}
		if (personaje == 3) {
			cadena += "Elina.";
		}
		if (personaje == 4) {
			cadena += "Keravnos.";
		}
		if (personaje == 5) {
			cadena += "ChuhZmR.";
		}
		System.out.println(cadena);
	}
}
