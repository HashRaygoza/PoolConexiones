CREATE TABLE `prueba` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `puerto_serial` int(11) DEFAULT 0,
  `puerto_impresora` int(11) DEFAULT 0,
  `puerto_red` int(11) DEFAULT 0,
  `puerto_escaner` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
)