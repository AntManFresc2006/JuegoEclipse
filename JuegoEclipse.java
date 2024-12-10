package juegoEclipse;
import java.util.Scanner;
public class JuegoEclipse {
	public static void main(String[] args) {

		// Principio de las variables/constantes

		Scanner sc = new Scanner(System.in);
		int eleccion, personajeUno, personajeDos, personajeTres, personajeCuatro, acción, variable, opciones,
				opcionUsuario, choosing;
		String personaje, personaje2, enemigo, enemigo2, opcion;
		String[][] informacion = new String[6][7];
		String[][] informacionDeItems = new String[6][1];
		String[][] nombreMovimiento = new String[6][5];
		
		int[][] datosPersonajes, statsTotales;
		statsTotales = new int[6][4];
		boolean certeza;
		boolean verificador;
		int[][] movimientos = new int[6][5];
		int[][] puntosMovimientos = new int[6][5];
		int items[][] = new int[6][2];
		int totalDeItems;

		// Asignacion de valores de variables/constantes
		choosing = 0;
		nombreMovimiento = importarNombreAtaques(nombreMovimiento);
		certeza = false;
		movimientos = potenciaMovs(movimientos);
		verificador = true;
		items = devolverMatrizDeItems(items);
		puntosMovimientos = usabilidadMovs(puntosMovimientos);

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
				items = excepcionChuh(items, personajeUno);

				do {
					if (variable > 0) {
						System.out.println("\nTus movimientos son:");
						muestraPersonaje(informacion, personajeUno);
						System.out.println("\n¿Qué harás, atacar, usar un objeto, o tirar una moneda?");
						sc.nextLine();
						opcion = sc.nextLine();
						opciones = eleccionIncorrecta(opcion, variable);
					}
					switch (opciones) {
					case 1:
						System.out.println("Perfecto, ¿Qué movimiento vas a querer usar?");
						muestraPersonaje(informacion, personajeUno);
						acción = sc.nextInt();
						--acción;
						batalla(verificador, personajeUno, statsTotales, acción, personajeDos, movimientos, nombreMovimiento, personaje, enemigo, puntosMovimientos);
						break;
					case 2:
						totalDeItems = 6;
						informacionDeItems = devolverMatrizDeInformacion(items, informacionDeItems);
						totalDeItems = devuelveItemsUsables(totalDeItems, items);
						System.out.println("Perfecto, ¿Qué item vas a querer usar?");
						System.out.println("Antes de empezar, recuerda que tienes a tu disposición un total de "
								+ totalDeItems + " items, ahora, ¿Cuál quieres elegir?");
						opcionUsuario = sc.nextInt();
						--opcionUsuario;
						while (items[opcionUsuario][1] < 1 || opcionUsuario < 0 || opcionUsuario > 5) {
							if (items[opcionUsuario][1] < 1) {
								System.out.println("Item acabado, lo siento, vuelve a intentarlo.");
							} else if (opcionUsuario < 0 || opcionUsuario > 5) {
								System.out.println(opcion + " no es un item que puedas elegir, vuelve a intentarlo.");
							}
							opcionUsuario = sc.nextInt();
							--opcionUsuario;
						}
						choosing = choosing(sc, choosing, informacionDeItems, opcionUsuario);
						statsTotales = sistemaDeItems(sc, items, informacionDeItems, totalDeItems, statsTotales,
								opcionUsuario, choosing);
						items = restador(items, choosing, opcionUsuario);
						break;
					case 3:
						System.out.println("Perfecto, vamos a tirar tu moneda.");
						System.out.println("Elige entre cara (1) o cruz (2).");
						opciones = sc.nextInt();
						while (opciones < 1 || opciones > 2) {
							System.out.println("Error, vuelve a intentarlo.");
							opciones = sc.nextInt();
						}
						certeza = tirarMoneda(opciones, certeza);
						sumadorDeStats(certeza, statsTotales, personajeUno);

						break;
					}
					variable++;

				} while (statsTotales[personajeUno][0] > 0 && statsTotales[personajeDos][0] > 0);
				System.out.println("\nFelicidades, has acabado el juego.");
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
	public static int movsQueNoSePuedenUsar (int [][]puntosMovs, int usuario, int eleccion, Scanner sc) {
		int nuevaEleccion = eleccion;
		while (puntosMovs[usuario][nuevaEleccion] < 1) {
			System.out.println("Error, no puedes escoger ese movimientos porque ya no tienes mas pp. Escoge otro movimiento.");
			nuevaEleccion = sc.nextInt();
			--nuevaEleccion;
			sc.nextLine();
		}
		return nuevaEleccion;
	}
	public static void batalla(boolean acierto, int usuario, int statsTotales[][], int eleccion, int enemigo, int[][] potenciaMovs, String [][]nombreMovimientos, String nombrePersonaje, String nombreEnemigo, int[][] puntosMovs) {
		// Esto es el combate
		Scanner sc = new Scanner(System.in);
		while (eleccion < 1 || eleccion > 5) {
			System.out.println("Error, no puedes escoger eso. Vuelve a intentarlo.");
			eleccion = sc.nextInt();
		}
		movsQueNoSePuedenUsar (puntosMovs, usuario, eleccion, sc);
		eleccion = movsQueNoSePuedenUsar (puntosMovs, usuario, eleccion, sc);
		--puntosMovs[usuario][eleccion];
		
		double random = dañoRandom();
		algoritmoDeVelocidad(acierto, statsTotales, eleccion, enemigo);
		if (acierto == true) {
			statsTotales[enemigo][0] -= ((statsTotales[usuario][1] * potenciaMovs[usuario][eleccion]) * random)
					/ statsTotales[enemigo][2];
			
			algoritmoDeEnemigo (potenciaMovs, enemigo, statsTotales, usuario);
			
			--puntosMovs[enemigo][eleccion];
			System.out.println(nombrePersonaje+" usó: "+nombreMovimientos[usuario][eleccion]+".");
			System.out.println(nombreEnemigo+" usó: "+nombreMovimientos[enemigo][eleccion]+".");
		}
		else if (acierto == false) {
			algoritmoDeEnemigo (potenciaMovs, enemigo, statsTotales, usuario);
			System.out.println(nombreEnemigo+" usó: "+nombreMovimientos[enemigo][eleccion]+".");
			statsTotales[enemigo][0] -= ((statsTotales[usuario][1] * potenciaMovs[usuario][eleccion]) * random)
					/ statsTotales[enemigo][2];
			System.out.println(nombrePersonaje+" usó: "+nombreMovimientos[usuario][eleccion]+".");
		}
		
		
		if (statsTotales[enemigo][0] > 1 && statsTotales[usuario][0] > 1) {
			System.out.println("A tu enemigo le quedan: " + statsTotales[enemigo][0] + " de HP, y a tu personaje "+ statsTotales[usuario][0] + ".");
		} else {
			System.out.println("A tu enemigo le quedan: " + 0 + " de HP.");
		}
	}

	public static boolean tirarMoneda(int opcion, boolean verificador) {
		String moneda;
		int max = 2;
		int min = 1;
		int range = (max - min) + min;
		int random = (int) ((range * Math.random()) + min);
		switch (random) {
		case 1:
			if (random == opcion) {
				System.out.println("Has acertado.");
				verificador = true;
			} else {
				System.out.println("Mala suerte, has fallado.");
				verificador = false;
			}
			break;
		case 2:
			if (random == opcion) {
				System.out.println("Has acertado.");
				verificador = true;
			} else {
				System.out.println("Mala suerte, has fallado.");
				verificador = false;
			}
			break;
		}
		return verificador;
	}

	public static void muestraPersonaje(String[][] matriz, int personaje) {
		for (int i = 1; i < 6; i++) {
			System.out.println(matriz[personaje][i]);
		}
	}

	public static boolean equalsParaQueGuillamonNoMeMateUsandoElIgnoreCase(String texto, String palabra) {
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
		// 0: Eclipse, 1: Evil, 2: Cosmic, 3: Elina, 4: Keravnos, 5: ChuhZmR.
		informacion[0][0] = 4;
		informacion[0][1] = 10;
		informacion[0][2] = 7;
		informacion[0][3] = 1;
		informacion[0][4] = 6;

		informacion[1][0] = 5;
		informacion[1][1] = 6;
		informacion[1][2] = 7;
		informacion[1][3] = 1; // Esta habilidad solo dura 3 turnos
		informacion[1][4] = 3; // Esta solo dura 4 turnos

		informacion[2][0] = 1; // Esta habilidad es infinita
		informacion[2][1] = 3;
		informacion[2][2] = 2; // esta dura 3 turnos y solo se puede usar despues de que el efecto de la
								// anterior haya acabado
		informacion[2][3] = 7;
		informacion[2][4] = 5;

		informacion[3][0] = 1; // Esta dura infinitamente
		informacion[3][1] = 7;
		informacion[3][2] = 5;
		informacion[3][3] = 5;
		informacion[3][4] = 6;

		informacion[4][0] = 1;
		informacion[4][1] = 3;
		informacion[4][2] = 3;
		informacion[4][3] = 4;
		informacion[4][4] = 3;

		informacion[5][0] = 4;
		informacion[5][1] = 4;
		informacion[5][2] = 4;
		informacion[5][3] = 4;
		informacion[5][4] = 4;

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

	public static int[][] potenciaMovs(int[][] informacion) {
		// Estos son los movimientos del personaje y el daño que hacen
		// 0: Eclipse, 1: Evil, 2: Cosmic, 3: Elina, 4: Keravnos, 5: ChuhZmR.
		informacion[0][0] = 0;
		informacion[0][1] = 0;
		informacion[0][2] = 250;
		informacion[0][3] = 0;
		informacion[0][4] = 200;

		informacion[1][0] = 0;
		informacion[1][1] = 300;
		informacion[1][2] = 250;
		informacion[1][3] = 0;
		informacion[1][4] = 0;

		informacion[2][0] = 0;
		informacion[2][1] = 0;
		informacion[2][2] = 0;
		informacion[2][3] = 300;
		informacion[2][4] = 250;

		informacion[3][0] = 0;
		informacion[3][1] = 0;
		informacion[3][2] = 150;
		informacion[3][3] = 150;
		informacion[3][4] = 250;

		informacion[4][0] = 0;
		informacion[4][1] = 450;
		informacion[4][2] = 250;
		informacion[4][3] = 0;
		informacion[4][4] = 0;

		informacion[5][0] = 0;
		informacion[5][1] = 0;
		informacion[5][2] = 0;
		informacion[5][3] = 200;
		informacion[5][4] = 450;

		return informacion;
	}

	public static double dañoRandom() {
		double max = 0.7;
		double min = 0.3;
		double range = (max - min) + min;
		double random = (double) ((range * Math.random()) + min);
		return random;
	}

	
	public static int[][] sumadorDeStats(int[][] matriz, int personaje) {
		int max = 3;
		int min = 1;
		int range = (max - min) + min;
		int random = (int) ((range * Math.random()) + min);
		switch (random) {
		case 1:
			matriz[personaje][1] += 50;
			break;
		case 2:
			matriz[personaje][2] += 50;
			break;
		case 3:
			matriz[personaje][3] += 50;
			break;
		}
		return matriz;
	}

	public static int[][] sumadorDeStats(boolean certeza, int[][] matriz, int personaje) {

		if (certeza == true) {
			int max = 3;
			int min = 1;
			int range = (max - min) + min;
			int random = (int) ((range * Math.random()) + min);
			switch (random) {
			case 1:
				matriz[personaje][1] += 50;
				System.out.println("¡¡¡Ha aumentado tu ataque!!!");
				break;
			case 2:
				matriz[personaje][2] += 50;
				System.out.println("¡¡¡Ha aumentado tu defensa!!!");
				break;
			case 3:
				matriz[personaje][3] += 50;
				System.out.println("¡¡¡Ha aumentado tu velocidad!!!");
				break;
			}
		} else {
			System.out.println("lo siento, has fallado y no obtienes ningun boost de stats.");
		}
		return matriz;
	}

	public static int[][] devolverMatrizDeItems(int matriz[][]) {

		// Estos items aumentan los puntos de salud del usuario.
		matriz[0][0] = 875;
		matriz[0][1] = 1;
		matriz[1][0] = 450;
		matriz[1][1] = 2;
		// Este aumenta los pps del personaje.
		matriz[2][0] = 3;
		matriz[2][1] = 1;
		// Estos aumentan los stats del personaje
		matriz[3][0] = 37; // Stats de ataque.
		matriz[3][1] = 2;
		matriz[4][0] = 37; // Stats de Defensa.
		matriz[4][1] = 2;
		matriz[5][0] = 37; // Stats de Velocidad.
		matriz[5][1] = 2;

		return matriz;
	}

	public static String[][] devolverMatrizDeInformacion(int matriz[][], String[][] matrix) {
		matrix[0][0] = "Elixir de Luminaria: Este item sube los puntos de salud del usuario a la mitad, tienes "
				+ matriz[0][1] + " puntos de pp.";
		matrix[1][0] = "Brebaje vital: Este item sube los puntos de salud del usuario a un cuarto, tienes "
				+ matriz[1][1] + " puntos de pp.";
		matrix[2][0] = "Tónico de Concentración: Este item sube los pps de un movimiento del usuario, tienes "
				+ matriz[2][1] + " puntos de pp.";
		matrix[3][0] = "Filofera: Este item aumenta el ataque del usuario, tienes " + matriz[3][1] + " puntos de pp.";
		matrix[4][0] = "Aegisflora: Este item aumenta la defensa del usuario, tienes " + matriz[4][1]
				+ " puntos de pp.";
		matrix[5][0] = "Velosprint: Este item aumenta la velocidad del usuario, tienes " + matriz[5][1]
				+ " puntos de pp.";
		return matrix;
	}

	public static int devuelveItemsUsables(int totalDeItems, int[][] matrizDePP) {
		if (matrizDePP[0][1] < 1 || matrizDePP[1][1] < 1 || matrizDePP[2][1] < 1 || matrizDePP[3][1] < 1
				|| matrizDePP[4][1] < 1 || matrizDePP[5][1] < 1) {
			totalDeItems--;
		}
		return totalDeItems;
	}

	public static int[][] restador(int[][] matrizDePP, int choosing, int opcionUsuario) {
		if (choosing == 1) {
			matrizDePP[opcionUsuario][1]--;
		}
		return matrizDePP;
	}

	public static int[][] excepcionChuh(int[][] matrizDePP, int personaje) {
		if (personaje == 6) {
			for (int i = 2; i < 6; i++) {
				matrizDePP[i][1] = 100;
			}
		}
		return matrizDePP;
	}

	public static int choosing(Scanner sc, int choosing, String[][] matrizDeInfo, int eleccion) {
		System.out.println(matrizDeInfo[eleccion][0]);
		System.out.println("¿Quieres usarlo? Pulsa(1) si quieres que asi sea, o pulsa (2) si no quieres el objeto.");
		choosing = sc.nextInt();
		while (choosing < 1 || choosing > 2) {
			System.out.println("Tienes que decir o si (1), o no (2), vuelve a intentarlo.");
			choosing = sc.nextInt();
		}

		return choosing;
	}

	public static int[][] sistemaDeItems(Scanner sc, int[][] matrizDePP, String[][] matrizDeInfo, int totalDeItems,
			int[][] statsUsuario, int opcion, int choosing) {

		if (choosing == 1) {
			int primerNumero = statsUsuario[opcion][0];
			statsUsuario[opcion][0] += matrizDePP[opcion][0];
			System.out.println(primerNumero+" ha pasado a "+statsUsuario[opcion][0]);
		} else if (choosing == 2) {
			System.out.println("Genial, vamos de vuelta al menú");
		}
		return statsUsuario;
	}

	public static int[][] personajeTipo(int[][] personajeTipo) {
		personajeTipo[0][0] = 0;
		personajeTipo[1][0] = 0;
		personajeTipo[2][0] = 0;
		personajeTipo[3][0] = 1;
		personajeTipo[4][0] = 1;
		personajeTipo[5][0] = 1;
		return personajeTipo;
	}

	
	  public static int[][] movimientosTipo(int[][] personajeTipo) {
	 
		//Esta es la matriz que contiene los tipos de movimientos de los personajes:
		/*
		 * 0: Normal
		 * 1: Inmaterial
		 * 2: Estado
		 * 3: Turnos
		 * 4: Especiales
		 * */
		personajeTipo[0][0] = 3;
		personajeTipo[0][1] = 2;
		personajeTipo[0][2] = 1;
		personajeTipo[0][3] = 4;
		personajeTipo[0][4] = 1;
		
		personajeTipo[1][0] = 4;
		personajeTipo[1][1] = 1;
		personajeTipo[1][2] = 1;
		personajeTipo[1][3] = 3;
		personajeTipo[1][4] = 3;
		
		personajeTipo[2][0] = 4;
		personajeTipo[2][1] = 3;
		personajeTipo[2][2] = 3;
		personajeTipo[2][3] = 0;
		personajeTipo[2][4] = 1;
		
		personajeTipo[3][0] = 3;
		personajeTipo[3][1] = 3;
		personajeTipo[3][2] = 4;
		personajeTipo[3][3] = 0;
		personajeTipo[3][4] = 0;
		
		personajeTipo[4][0] = 4;
		personajeTipo[4][1] = 1;
		personajeTipo[4][2] = 1;
		personajeTipo[4][3] = 3;
		personajeTipo[4][4] = 2;
		
		personajeTipo[5][0] = 3;
		personajeTipo[5][1] = 3;
		personajeTipo[5][2] = 4;
		personajeTipo[5][3] = 0;
		personajeTipo[5][4] = 0;
		return personajeTipo;
	}

	public static int[][] probabilidadMovs(int [][] personajeTipo) {
		personajeTipo[0][0] = 1;
		personajeTipo[0][1] = 1;
		personajeTipo[0][2] = 2;
		personajeTipo[0][3] = 1;
		personajeTipo[0][4] = 2;
		
		personajeTipo[1][0] = 2;
		personajeTipo[1][1] = 1;
		personajeTipo[1][2] = 2;
		personajeTipo[1][3] = 1;
		personajeTipo[1][4] = 3;
		
		personajeTipo[2][0] = 2;
		personajeTipo[2][1] = 2;
		personajeTipo[2][2] = 1;
		personajeTipo[2][3] = 1;
		personajeTipo[2][4] = 2;
		
		personajeTipo[3][0] = 1;
		personajeTipo[3][1] = 1;
		personajeTipo[3][2] = 1;
		personajeTipo[3][3] = 2;
		personajeTipo[3][4] = 2;
		
		personajeTipo[4][0] = 1;
		personajeTipo[4][1] = 1;
		personajeTipo[4][2] = 1;
		personajeTipo[4][3] = 2;
		personajeTipo[4][4] = 2;
		
		personajeTipo[5][0] = 1;
		personajeTipo[5][1] = 1;
		personajeTipo[5][2] = 1;
		personajeTipo[5][3] = 1;
		personajeTipo[5][4] = 1;
		return personajeTipo;
	}
	
	public static boolean posibilidadDeAcertar (boolean acierto, int [][]personajeTipo, int personaje, int ataque) {
		
		int max = personajeTipo[personaje][ataque];
		int min = 1;
		int range = (max - min) + min;
		int random = (int) ((range * Math.random()) + min);
		if(random == 1) {
			acierto = true;
		}
		else {
			acierto = false;
		}
		return acierto;
	}
public static boolean algoritmoDeVelocidad (boolean acierto, int[][]velocidadPersonajes, int personaje, int enemigo) {
		if (velocidadPersonajes[personaje][3] < velocidadPersonajes[enemigo][3]) {
			System.out.println("Tu enemigo es más rápido.");
			acierto = false;
		}
		else if (velocidadPersonajes[personaje][3] > velocidadPersonajes[enemigo][3]) {
			System.out.println("Eres más rápido que tu enemigo.");
			acierto = true;
		}
		if (acierto == true) {
			System.out.println("Eres más rápido que tu enemigo.");
		}
		else if (acierto == false) {
			System.out.println("Tu enemigo es más rápido.");
		}
		return acierto;
	}
public static int[][] algoritmoDeEnemigo (int[][]ataqueEnemigo, int enemigo, int [][] statsUsuario, int usuario) {
	double dañoRandom = dañoRandom();
	int max = 4;
	int min = 0;
	int range = (max - min) + min;
	int random = (int) ((range * Math.random()) + min);
	statsUsuario[usuario][0] -= ((statsUsuario[enemigo][1] * ataqueEnemigo[enemigo][random]) * dañoRandom)
			/ statsUsuario[usuario][2];
	
	return statsUsuario;
}

public static String[][] importarNombreAtaques (String[][]informacion) {
	informacion[0][0] = "Clonación";
	informacion[0][1] = "Stat-Boosts";
	informacion[0][2] = "Soulfuck";
	informacion[0][3] = "Adapting";
	informacion[0][4] = "Energy-Attacks";

	informacion[1][0] = "Mental Manipulation";
	informacion[1][1] = "Energy-Storm";
	informacion[1][2] = "Soulfuck";
	informacion[1][3] = "Phaisa-Shield";
	informacion[1][4] = "Parasit";

	informacion[2][0] = "Body-Change";
	informacion[2][1] = "Constraint";
	informacion[2][2] = "Cursed";
	informacion[2][3] = "Witness-Oblivion";
	informacion[2][4] = "Divine-Flash";

	informacion[3][0] = "Roots";
	informacion[3][1] = "Shell";
	informacion[3][2] = "Ray-Beam";
	informacion[3][3] = "Frost-Attack";
	informacion[3][4] = "Hurricane";

	informacion[4][0] = "Item-Warping";
	informacion[4][1] = "Cosmic Skill: Black Holes";
	informacion[4][2] = "Cosmic Skill: Mental Illness";
	informacion[4][3] = "Time-Stop";
	informacion[4][4] = "Fastering";

	informacion[5][0] = "BeforeDay";
	informacion[5][1] = "ClassRest";
	informacion[5][2] = "Gambling";
	informacion[5][3] = "Golpes normales";
	informacion[5][4] = "Super-Perfect-Cell-Kamehameha";

	return informacion;
}

public static int[][] algoritmoDeMovimientos (int [][] statsTotales, int personaje, int movimiento) {
	switch (personaje) {
	case 0:
		switch (movimiento) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
			}
		break;
	case 1:
		switch (movimiento) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
			}
		break;
	case 2:
		switch (movimiento) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
			}
		break;
	case 3:
		switch (movimiento) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
			}
		break;
	case 4:
		switch (movimiento) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
			}
		break;
	case 5:
		switch (movimiento) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
			}
		break;
	
	}
	return statsTotales;
}
	
}
