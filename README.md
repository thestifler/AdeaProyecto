# AdeaProyecto



Para el siguiente proyecto me base lo mas posible en el documento proporcionado para la prueba.

 puntos que me faltaria mejorar en el proyecto.

 1.-Me faltan mas contexto de la tabla , hay campos que deje sin usar por no saber que hacer o como deben de funcionar dentro de aplicacion web.
    por ejemplo el tema de que el usuario no tiene un ROL , se debe de tomar en cuenta para configurar correctamente la seguridad.
    NOACCESO tambien fue un campo que me faltaria agregarle funcionalidad , supongo que se refiere a numero de acceso o login que ha hecho el usuario.MAS CONTEXTO
    NOINTENTOS igual que el punto anterior , y creo que se refiere a intentos fallidos de hacer login.
    FECHAREVOCADO solo utilice la fecha de vigencia para validar el login.
    EMAIL Y CLIENTE , faltaria saber si son unicos , ya que no se indico y tambien saber si se puede tener mas de un EMAIL , ya que tambien cambiaria la estructura de la tabla.
    STATUS , se especifico que solo se validara por fecha de vigencia para hacer login , asi que no valide por STATUS , ya que no se especifico.

 2.-Deje el cifrado que esta viene con spring secutiry.
 3.-Cambie lo nombres de los campos de la base de datos , ya que en el documento estan en mayusculas(dejarel el DDL abajo), si es necesario dejarlos tal cual esta en el documento lo puedo modificar en la CLASE Entity y mappearlo.
 4.-Deje el formulario de agregar Usuario fuera de la seguridad para que se pueda dar de alta y se pueda hacer prueba del login


 5.- en el Caso de que la aplicacion web que estoy enviando no cumple con los requisitos que estan buscando , si son tan amable podrian darme un feedback por correo de que cosas puedo mejorar , me tomare el tiempo de mejorarlo a un nivel el cual se mas que aceptable para sus estandares , se los agradeceria.


AGREGO DDL DEL PROYECTO (MYSQL)

CREATE TABLE `usuario` (
  `login` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(80) NOT NULL,
  `nombre` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `cliente` float NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_baja` date DEFAULT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  `intentos` float NOT NULL,
  `fecha_revocado` date DEFAULT NULL,
  `fecha_vigencia` date DEFAULT NULL,
  `no_acceso` int DEFAULT NULL,
  `apellido_paterno` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `apellido_materno` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `area` int DEFAULT NULL,
  `fecha_modificacion` date NOT NULL,
  `idusuariareao` bigint DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci