package ejercicioeclipse;

import java.util.Scanner;

/**
 * @author AntonioManuelFrescoG
 * @version 1.0
 * 
 *          Este juego consiste en una version beta de un juego de peleas 1v1.
 *          Tiene un menu de 3 opciones, la primera es la pelea, donde los
 *          personajes tienen ataques y movimientos especiales, donde los
 *          movimientos especiales por el momento no hacen nada. La segunda
 *          consiste en la informacion del personaje, una descripcion del mismo
 *          con sus stats de combate, movimientos y habilidades. La ultima
 *          consiste en acabar el programa.
 */
public class JuegoEclipse {
	/**
	 * 
	 * @param args Estos son los parametros de ejecucion
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

// Principio de las variables/constantes

		int eleccion, usuario, enemy, acción, variable, opciones, opcionUsuario, choosing;
		String personaje, enemigo, opcion;
		String[][] informacion = new String[6][7];
		String[][] informacionDeItems = new String[6][1];
		String[][] nombreMovimiento = new String[6][5];

		int[][] statsTotales = new int[6][4];
		boolean certeza, verificador;
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

//Syso de los personajes

		mostrarMensaje();

		do {

			// Aqui comienza el menú, si el usuario escoge el numero 1, empezará el combate,
			// si escoge el 2, le aparecerá la información, si escoge el 3, el programa
			// terminará y si escoge otro numero distinto, le dara error.

			System.out.println(
					"\nAhora, dime qué es lo que quieres hacer,  ¿Preferirías establecer un modo de combate 1vs1 (pulsa '1')? preferirias ver la información de cada personaje? (pulsa '2') o preferirías acabar este juego?(pulsa '3')");
			eleccion = sc.nextInt();
			personaje = "";
			enemigo = "";
			informacion = informacion(informacion);
			switch (eleccion) {

			// Aqui empieza el combate.

			case 1:

				// Esto es la solicitud del personaje del usuario.

				System.out.println(
						"¡¡¡Perfecto!!! ¿Cual personaje quieres escoger? Escoge pulsando un numero del 1 al 6.");
				usuario = sc.nextInt();
				usuario = Reeleccion(usuario, -999);
				mensajePersonaje(usuario, personaje);

				// Aqui metemos un algoritmo que escoge cuál sera el personaje enemigo,
				// asegurándose de no ser nunca el mismo que el personaje del usuario.

				enemy = randomizer(usuario, 0, 0, 0);
				mensajeEnemigo(enemy, enemigo);
				personaje = nombrePj(usuario, personaje);
				enemigo = nombrePj(enemy, enemigo);
				System.out.println(personaje + " V/s " + enemigo + ". ");

				System.out.println("\n¡¡¡Que comience el combate!!!");

				// Estas 2 líneas de código importan los stats de los personajes y una variables
				// que iniciara un proceso de limpieza de scanner.

				statsTotales = statsPersonajes(statsTotales);
				variable = 0;

				// En esta parte del codigo se importan los movimientos del personaje del
				// usuario

				System.out.println("\nTus movimientos son:");
				muestraPersonaje(informacion, usuario);

				// Aqui se le pide al usuario que va a hacer, si atacar, usar un objeto, o tirar
				// una moneda.

				System.out.println("\n¿Qué harás, atacar, usar un objeto, o tirar una moneda?");
				sc.nextLine();
				opcion = sc.nextLine();
				opciones = eleccionIncorrecta(opcion, variable);

				// Aqui se importa una funcion dentro de una matriz que devuelve los items que
				// se pueden usar si el personaje escogido es "ChuhZmR".

				items = excepcionChuh(items, usuario);

				do {

					// Este es el proceso de limpiar el Scanner, ya que si no se limpia, el programa
					// va a dar errores.

					if (variable > 0) {
						System.out.println("\nTus movimientos son:");
						muestraPersonaje(informacion, usuario);
						System.out.println("\n¿Qué harás, atacar, usar un objeto, o tirar una moneda?");
						sc.nextLine();
						opcion = sc.nextLine();
						opciones = eleccionIncorrecta(opcion, variable);
					}

					// Este es el Switch que evalua si el usuario va a atacar, usar un objeto o
					// tirar una moneda.

					switch (opciones) {
					case 1: // Opcion atacar.

						System.out.println("Perfecto, ¿Qué movimiento vas a querer usar?");
						muestraPersonaje(informacion, usuario);
						acción = sc.nextInt();
						--acción;
						batalla(verificador, usuario, statsTotales, acción, enemy, movimientos, nombreMovimiento,
								personaje, enemigo, puntosMovimientos);
						break;
					case 2: // Opcion usar un objeto.

						totalDeItems = 6;

						// Estas 2 lineas de codigo invocan la descripcion de cada item y a posteriori
						// la cantidad de veces que se puede usar un item en especifico.

						informacionDeItems = devolverMatrizDeInformacion(items, informacionDeItems);
						totalDeItems = devuelveItemsUsables(totalDeItems, items);

						// Esta es la solicitud del item a usar.

						System.out.println("Perfecto, ¿Qué item vas a querer usar?");
						System.out.println("Antes de empezar, recuerda que tienes a tu disposición un total de "
								+ totalDeItems + " items, ahora, ¿Cuál quieres elegir?");
						opcionUsuario = sc.nextInt();
						--opcionUsuario;

						// Este es el bucle que da inicio cuando un item se acaba o selecciona un numero
						// que no tiene asignado ni un solo item.

						while (items[opcionUsuario][1] < 1 || opcionUsuario < 0 || opcionUsuario > 5) {
							if (items[opcionUsuario][1] < 1) {
								System.out.println("Item acabado, lo siento, vuelve a intentarlo.");
							} else if (opcionUsuario < 0 || opcionUsuario > 5) {
								System.out.println(opcion + " no es un item que puedas elegir, vuelve a intentarlo.");
							}
							opcionUsuario = sc.nextInt();
							--opcionUsuario;
						}

						// Choosing es una variable booleana que importa si el usuario va a querer usar
						// un item o si se arrepiente y no quiere usarlo.

						choosing = choosing(sc, choosing, informacionDeItems, opcionUsuario);

						// La variable statsTotales recoge la variacion de un stat especifico del
						// usuario en caso de escoger un objeto para curarse o subir su ataque, defensa
						// o velocidad.

						statsTotales = sistemaDeItems(sc, items, informacionDeItems, totalDeItems, statsTotales,
								opcionUsuario, choosing);

						// Y aqui se restan los puntos usables del usuario.

						items = restador(items, choosing, opcionUsuario);
						break;

					case 3: // Opcion tirar una moneda.

						System.out.println("Perfecto, vamos a tirar tu moneda.");
						System.out.println("Elige entre cara (1) o cruz (2).");
						opciones = sc.nextInt();
						// Este es el bucle que inicializa cuando el usuario no mete ni un numero que
						// sea 1 o 2.
						while (opciones < 1 || opciones > 2) {
							System.out.println("Error, vuelve a intentarlo.");
							opciones = sc.nextInt();
						}

						// Esta es una variable booleana que contiene un metodo, que realiza la función
						// de escoger si la moneda aleatoria es cara o cruz.

						certeza = tirarMoneda(opciones, certeza);

						// Y si la opcion escogida por el usuario coincide con la de la funcion, lleva a
						// cabo este proceso donde aumenta un stat aleatorio de su personaje.

						sumadorDeStats(certeza, statsTotales, usuario);

						break;

					}
					variable++;

				} while (statsTotales[usuario][0] > 0 && statsTotales[enemy][0] > 0);

				System.out.println("\nFelicidades, has acabado el juego.");

				break;

			// Aqui empieza la opcion de insertar la informacion del personaje que el
			// usuario escoge.

			case 2:

				statsTotales = statsPersonajes(statsTotales);
				System.out.println(
						"\nGenial, pulsa un numero del personaje del cual quieres buscar información, y a continuacion te mostrare su informacion.");
				usuario = sc.nextInt();
				usuario = Reeleccion(usuario, -999);
				personaje = nombrePj(usuario, personaje);

				// Aqui se pregunta si el usuario quiere preguntar por los stats de su personaje
				// o si quiere simplemente mirar su info. general.

				System.out.println("¿Quieres ver los movimientos y habilidades de " + personaje
						+ "? ¿O solo quieres ver sus stats?");
				sc.nextLine();
				opcion = sc.nextLine();

				// Esta es una funcion void que mira si el usuario quiere mirar sus stats o
				// info. general, o si simplemente ha escogido una opcion incorrecta.
				condicionInfo(sc, opcion, usuario, informacion, statsTotales);
				break;

			// Aqui da inicio la opcion de acabar el juego.

			case 3:
				System.out.println(
						"\nMuchas gracias por haber jugado a este juego de batallas, ¡¡¡Espero que te haya gustado!!! :D");
				break;

			// Aqui da inicio la opcion de pedirle al usuario otra opcion.

			default:
				System.out.println(eleccion + "\n no es un número de nuestro menú, vuelve a intentarlo.");
				break;
			}
		} while (eleccion != 3);

	}

	/**
	 * @param args recibe puntosMovs, usuario, eleccion, Scanner.
	 * @return nuevaEleccion
	 */
	public static int movsQueNoSePuedenUsar(int[][] puntosMovs, int usuario, int eleccion, Scanner sc) {

		int nuevaEleccion = eleccion;
		while (puntosMovs[usuario][nuevaEleccion] < 1) {
			System.out.println(
					"Error, no puedes escoger ese movimientos porque ya no tienes mas pp. Escoge otro movimiento.");
			nuevaEleccion = sc.nextInt();
			--nuevaEleccion;
			sc.nextLine();
		}
		return nuevaEleccion;
	}

	/**
	 * @param args recibe acierto, usuario, statsTotales[][], eleccion, enemigo,
	 *             potenciaMovs, nombreMovimientos, nombrePersonaje, nombreEnemigo,
	 *             puntosMovs
	 * @return no retorna nada. Esto es el combate
	 */
	public static void batalla(boolean acierto, int usuario, int statsTotales[][], int eleccion, int enemigo,
			int[][] potenciaMovs, String[][] nombreMovimientos, String nombrePersonaje, String nombreEnemigo,
			int[][] puntosMovs) {
		Scanner sc = new Scanner(System.in);
		while (eleccion < 0 || eleccion > 4) {
			System.out.println("Error, no puedes escoger eso. Vuelve a intentarlo.");
			eleccion = sc.nextInt();
		}
		movsQueNoSePuedenUsar(puntosMovs, usuario, eleccion, sc);
		eleccion = movsQueNoSePuedenUsar(puntosMovs, usuario, eleccion, sc);
		--puntosMovs[usuario][eleccion];

		double random = dañoRandom();
		algoritmoDeVelocidad(acierto, statsTotales, eleccion, enemigo);
		if (acierto == true) {
			statsTotales[enemigo][0] -= ((statsTotales[usuario][1] * potenciaMovs[usuario][eleccion]) * random)
					/ statsTotales[enemigo][2];

			algoritmoDeEnemigo(potenciaMovs, enemigo, statsTotales, usuario);

			--puntosMovs[enemigo][eleccion];
			System.out.println(nombrePersonaje + " usó: " + nombreMovimientos[usuario][eleccion] + ".");
			System.out.println(nombreEnemigo + " usó: " + nombreMovimientos[enemigo][eleccion] + ".");
		} else if (acierto == false) {
			algoritmoDeEnemigo(potenciaMovs, enemigo, statsTotales, usuario);
			System.out.println(nombreEnemigo + " usó: " + nombreMovimientos[enemigo][eleccion] + ".");
			statsTotales[enemigo][0] -= ((statsTotales[usuario][1] * potenciaMovs[usuario][eleccion]) * random)
					/ statsTotales[enemigo][2];
			System.out.println(nombrePersonaje + " usó: " + nombreMovimientos[usuario][eleccion] + ".");
		}

		if (statsTotales[enemigo][0] > 1 && statsTotales[usuario][0] > 1) {
			System.out.println("A tu enemigo le quedan: " + statsTotales[enemigo][0] + " de HP, y a tu personaje "
					+ statsTotales[usuario][0] + ".");
		} else {
			System.out.println("A tu enemigo le quedan: " + 0 + " de HP.");
		}
	}

	/**
	 * @param args recibe opcion, verificador
	 * @return verificador Esta es la funcion que lanza una moneda aleatoria y la
	 *         compara con la opcion que haya elegido el usuario. Si el usuario
	 *         acierta, uno de los stats de su personaje subirá. Si no acepta,
	 *         pierde el turno.
	 */
	public static boolean tirarMoneda(int opcion, boolean verificador) {
		// Esta es la funcion que lanza una moneda aleatoria y la compara con la opcion
		// que haya elegido el usuario.
		// Si el usuario acierta, uno de los stats de su personaje subirá.
		// Si no acepta, pierde el turno.
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

	/**
	 * @param args recibe matriz, personaje
	 * @return no retorna nada.
	 */
	public static void muestraPersonaje(String[][] matriz, int personaje) {
		for (int i = 1; i < 6; i++) {
			System.out.println(matriz[personaje][i]);
		}
	}

	/**
	 * @param args recibe texto, palabra
	 * @return certeza Esta funcion realiza lo mismo que un ignore case
	 */
	public static boolean equalsParaQueGuillamonNoMeMateUsandoElIgnoreCase(String texto, String palabra) {
		// Esta funcion realiza lo mismo que un ignore case.
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

	/**
	 * @param args recibe opcion, variable
	 * @return noSeComoLlamarEstaVariable Esta funcion realiza lo mismo que un
	 *         ignore case Esta es la funcion que le vuelve a pedir al usuario una
	 *         opcion escrita hasta que la introduzca.
	 */
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

	/**
	 * @param args recibe informacion
	 * @return informacion Esta es la funcion que almacena los informacion general
	 *         del personaje en un array
	 */

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

	/**
	 * @param args recibe eleccionPersonaje, cadena.
	 * @return no retorna nada. Esta es la funcion que le devuelve al usuario los
	 *         movimientos y las habilidades de su personaje
	 */
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

	/**
	 * @param args recibe informacion
	 * @return informacion. Esta es la funcion que contiene un array de enteros,
	 *         estos son la cantidad de veces que un personaje puede usar su
	 *         movimiento. 0: Eclipse, 1: Evil, 2: Cosmic, 3: Elina, 4: Keravnos, 5:
	 *         ChuhZmR.
	 */
	public static int[][] usabilidadMovs(int[][] informacion) {

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

	/**
	 * @param args recibe informacion
	 * @return informacion. Esta es la funcion que almacena los stats del personaje
	 *         en un array 0: HP, 1: Ataque, 2: Defensa, 3: Velocidad. 0: Eclipse,
	 *         1: Evil, 2: Cosmic, 3: Elina, 4: Keravnos, 5: ChuhZmR.
	 */
	public static int[][] statsPersonajes(int[][] informacion) {

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

	/**
	 * @param args recibe personaje, cadena
	 * @return cadena. Esta es la funcion que almacena los nombres del personaje en
	 *         un array para su posterior uso.
	 */
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

	/**
	 * @param args recibe informacion, numero
	 * @return no retorna nada. Esta es la funcion que muestra la info general del
	 *         personaje.
	 */
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

	/**
	 * @param args recibe primerPersonaje, segundoPersonaje
	 * @return primerPersonaje. Esta es la funcion que le vuelve a pedir un numero
	 *         al usuario para elegir un personaje en caso de que haya introducido
	 *         un numero que no coincida con el personaje.
	 */
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

	/**
	 * @param args recibe personaje1, personaje2, personaje3, personaje4
	 * @return random. 
	 * Esta es una funcion que asigna un personaje enemigo, asegurandose de que no devuelva el mismo personaje
	 */

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
	
	/**
	 * @param args recibe personaje, cadena
	 * @return no retorna nada. 
	 * Esta es una funcion que devuelve el personaje del usuario y a cual mas va a querer elegir.
	 */
	
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
	/**
	 * @param args recibe personaje, cadena
	 * @return no retorna nada. 
	 * Esta es una funcion que devuelve el enemigo del usuario.
	 */
	
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

	/**
	 * @param args recibe informacion
	 * @return informacion. 
	 * Esta es una funcion que devuelve el enemigo del usuario.
	 * Estos son los movimientos del personaje y el daño que hacen
	 * 0: Eclipse, 1: Evil, 2: Cosmic, 3: Elina, 4: Keravnos, 5: ChuhZmR.
	 */
	public static int[][] potenciaMovs(int[][] informacion) {
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
	/**
	 * @param recibe args null
	 * @return random. 
	 * Esta funcion es la que devuelve el daño que hace un personaje, para darle mas dinamismo al combate, el daño del ataque no será el mismo.
	 */
	public static double dañoRandom() {
	
		double max = 0.7;
		double min = 0.3;
		double range = (max - min) + min;
		double random = (double) ((range * Math.random()) + min);
		return random;
	}
	/**
	 * @param args recibe  matriz, personaje
	 * @return matriz. 
	 * Esta funcion es la que devuelve el stat que va a subir del usuario has haber aceptado el juego de la moneda.
	 */
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
	/**
	 * @param args recibe certeza, matriz, personaje
	 * @return matriz. 
	 * Esta es la funcion que devuelve el stat que va a aumentar del personaje una vez acertado el juego de la moneda.
	 */
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
	/**
	 * @param args recibe matriz
	 * @return matriz. 
	 * Esta es la funcion que devuelve los stats del usuario que aumentaran una vez usado un item.
	 * Estos items aumentan los puntos de salud del usuario.
	 */
	public static int[][] devolverMatrizDeItems(int matriz[][]) {
		// 
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
	/**
	 * @param args recibe matriz, matrix
	 * @return matrix. 
	 * Esta es la funcion que devuelve la informacion de los items y cuantas veces se podran usar.
	 */
	public static String[][] devolverMatrizDeInformacion(int matriz[][], String[][] matrix) {
		// 
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
	/**
	 * @param args recibe totalDeItems, matrizDePP
	 * @return totalDeItems. 
	 * Esta es la funcion que devuelve cuantos items pueden usarse.
	 */
	public static int devuelveItemsUsables(int totalDeItems, int[][] matrizDePP) {
		if (matrizDePP[0][1] < 1 || matrizDePP[1][1] < 1 || matrizDePP[2][1] < 1 || matrizDePP[3][1] < 1
				|| matrizDePP[4][1] < 1 || matrizDePP[5][1] < 1) {
			totalDeItems--;
		}
		return totalDeItems;
	}
	/**
	 * @param args recibe matrizDePP, choosing, opcionUsuario
	 * @return matrizDePP.
	 * Esta es la funcion que resta los puntos de usaje de un item.
	 */
	public static int[][] restador(int[][] matrizDePP, int choosing, int opcionUsuario) {
		if (choosing == 1) {
			matrizDePP[opcionUsuario][1]--;
		}
		return matrizDePP;
	}

	/**
	 * @param args recibe sc, choosing, matrizDeInfo, eleccion
	 * @return choosings.
	 * Esta es la matriz que le pide al usuario si quiere usar un item o no.
	 */
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
	/**
	 * @param args recibe sc, matrizDePP, matrizDeInfo, totalDeItems,
			statsUsuario, opcion, choosing
	 * @return statsUsuario.
	 * Esta es la matriz que dice cuantas veces van a subir los stats del personaje.
	 */
	public static int[][] sistemaDeItems(Scanner sc, int[][] matrizDePP, String[][] matrizDeInfo, int totalDeItems,
			int[][] statsUsuario, int opcion, int choosing) { 
		matrizDePP = excepcionChuh(matrizDePP, opcion);
		int primerNumero;
		if (choosing == 1) {

			if (opcion == 0 || opcion == 1) {
				primerNumero = statsUsuario[opcion][0];
				statsUsuario[opcion][0] += matrizDePP[opcion][0];
				System.out.println("Tu personaje ha pasado de " + primerNumero + " puntos de vida a "
						+ statsUsuario[opcion][0] + " puntos.");
			} else if (opcion == 2) {
				System.out.println("El movimiento de tu personaje ha pasado de " + 0 + " puntos de PP a "
						+ statsUsuario[opcion][0] + " puntos.");
			} else if (opcion == 3) {
				primerNumero = statsUsuario[opcion][1];
				statsUsuario[opcion][1] += matrizDePP[opcion][0];
				System.out.println("Tu personaje ha pasado de " + primerNumero + " puntos de ataque a "
						+ statsUsuario[opcion][1] + " puntos.");
			} else if (opcion == 4) {
				primerNumero = statsUsuario[opcion][2];
				statsUsuario[opcion][2] += matrizDePP[opcion][0];
				System.out.println("Tu personaje ha pasado de " + primerNumero + " puntos de defensa a "
						+ statsUsuario[opcion][2] + " puntos.");
			} else if (opcion == 5) {
				primerNumero = statsUsuario[opcion][3];
				statsUsuario[opcion][3] += matrizDePP[opcion][0];
				System.out.println("Tu personaje ha pasado de " + primerNumero + " puntos de velocidad a "
						+ statsUsuario[opcion][3] + " puntos.");
			}

		} else if (choosing == 2) {
			System.out.println("Genial, vamos de vuelta al menú");
		}
		return statsUsuario;
	}
	/**
	 * @param args recibe personajeTipo
	 * @return personajeTipo.
	 * Esta es la matriz que dice el tipo de personaje que va a ser.
	 */
	public static int[][] personajeTipo(int[][] personajeTipo) {
		personajeTipo[0][0] = 0;
		personajeTipo[1][0] = 0;
		personajeTipo[2][0] = 0;
		personajeTipo[3][0] = 1;
		personajeTipo[4][0] = 1;
		personajeTipo[5][0] = 1;
		return personajeTipo;
	}

	/**
	 * @param args recibe personajeTipo
	 * @return personajeTipo.
	 * Esta es la matriz que contiene los tipos de movimientos de los personajes:
	 * 0: Normal 1: Inmaterial 2: Estado 3: Turnos 4: Especiales
	 */
	public static int[][] movimientosTipo(int[][] personajeTipo) {
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
	/**
	 * @param args recibe personajeTipo
	 * @return personajeTipo.
	 * Esta es la matriz que contiene la probabilidad de que un ataque acierte o no, mientras mas pequeño sea el numero, mas posibilidades hay de fallar el ataque.
	 */
	public static int[][] probabilidadMovs(int[][] personajeTipo) {
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
	/**
	 * @param args recibe acierto, personajeTipo, personaje, ataque
	 * @return acierto.
	 * Esta funcion es una continuacion de la anterior
	 */
	public static boolean posibilidadDeAcertar(boolean acierto, int[][] personajeTipo, int personaje, int ataque) {
		// Esta funcion es una continuacion de la anterior
		int max = personajeTipo[personaje][ataque];
		int min = 1;
		int range = (max - min) + min;
		int random = (int) ((range * Math.random()) + min);
		if (random == 1) {
			acierto = true;
		} else {
			acierto = false;
		}
		return acierto;
	}
	/**
	 * @param args recibe acierto, velocidadPersonajes, personaje,
			enemigo
	 * @return acierto.
	 * Esta es la matriz que determina que personaje se movera antes que el otro
	 */
	public static boolean algoritmoDeVelocidad(boolean acierto, int[][] velocidadPersonajes, int personaje,
			int enemigo) {
		if (velocidadPersonajes[personaje][3] < velocidadPersonajes[enemigo][3]) {
			System.out.println("Tu enemigo es más rápido.");
			acierto = false;
		} else if (velocidadPersonajes[personaje][3] > velocidadPersonajes[enemigo][3]) {
			System.out.println("Eres más rápido que tu enemigo.");
			acierto = true;
		}
		if (acierto == true) {
			System.out.println("Eres más rápido que tu enemigo.");
		} else if (acierto == false) {
			System.out.println("Tu enemigo es más rápido.");
		}
		return acierto;
	}
	/**
	 * @param args recibe ataqueEnemigo, enemigo, statsUsuario, usuario
	 * @return statsUsuario.
	 * Esta es la matriz que realiza los movimientos que hara el enemigo
	 */
	public static int[][] algoritmoDeEnemigo(int[][] ataqueEnemigo, int enemigo, int[][] statsUsuario, int usuario) {
		double dañoRandom = dañoRandom();
		int max = 4;
		int min = 0;
		int range = (max - min) + min;
		int random = (int) ((range * Math.random()) + min);
		statsUsuario[usuario][0] -= ((statsUsuario[enemigo][1] * ataqueEnemigo[enemigo][random]) * dañoRandom)
				/ statsUsuario[usuario][2];

		return statsUsuario;
	}

	/**
	 * @param args recibe informacion
	 * @return informacion.
	 * Esta es la matriz que contiene los nombres de los ataques.
	 */
	public static String[][] importarNombreAtaques(String[][] informacion) {
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
	/**
	 * @param args recibe matrizDePP, personaje
	 * @return matrizDePP.
	 * Esta es la funcion que devuelve los puntos de usaje de chuh, puesto que este personaje posee items ilimitados.
	 */
	public static int[][] excepcionChuh(int[][] matrizDePP, int personaje) {
		if (personaje == 6) {
			for (int i = 2; i < 6; i++) {
				matrizDePP[i][1] = 100;
			}
		}
		return matrizDePP;
	}
	/**
	 * @param args recibe statsTotales, opcion
	 * @return nada.
	 * Esta es la funcion que devuelve los puntos de stats
	 */
	public static void mostrarStatsDelPj(int[][] statsTotales, int opcion) {
		for (int i = 0; i < statsTotales[opcion].length; i++) {
			if (i == 0) {
				System.out.println(statsTotales[opcion][i] + " puntos de HP.");
			}
			if (i == 1) {
				System.out.println(statsTotales[opcion][i] + " puntos de Ataque.");
			}
			if (i == 2) {
				System.out.println(statsTotales[opcion][i] + " puntos de Defensa.");
			}
			if (i == 3) {
				System.out.println(statsTotales[opcion][i] + " puntos de Velocidad.");
			}
		}
	}
	/**
	 * @param args recibe sc, opcion, personaje, info, statsTotales
	 * @return nada.
	 * Esta es la funcion que comprueba si el usuario puso stats, movimientos o si introdujo un error.
	 */
	public static void condicionInfo(Scanner sc, String opcion, int personaje, String[][] info, int[][] statsTotales) {
		while (equalsParaQueGuillamonNoMeMateUsandoElIgnoreCase("stats", opcion) == false
				&& equalsParaQueGuillamonNoMeMateUsandoElIgnoreCase("movimientos", opcion) == false) {
			System.out.println("Error, tienes que escribir o 'stats' o 'movimientos'.");
			opcion = sc.nextLine();
		}
		if (equalsParaQueGuillamonNoMeMateUsandoElIgnoreCase("stats", opcion) == true) {
			mostrarStatsDelPj(statsTotales, personaje);
		} else if (equalsParaQueGuillamonNoMeMateUsandoElIgnoreCase("movimientos", opcion) == true) {
			infoPJ(info, personaje);
		}

	}
	/**
	 * @param args recibe null
	 * @return nada.
	 * Esta es la funcion que devuelve los mensajes de los personajes.
	 */
	public static void mostrarMensaje() {
		System.out.println("\n Saludos, este es un juego de batalla de hasta 5 personajes, que son los siguientes.");
		System.out.println("\t1er personaje: Eclipse.");
		System.out.println("\t2ndo personaje: Evil.");
		System.out.println("\t3er personaje: Cosmic.");
		System.out.println("\t4to personaje: Elina.");
		System.out.println("\t5to personaje: Keravnos.");
		System.out.println("\t6to personaje: ChuhZmR.");

	}
}
