CREATE DATABASE IF NOT EXISTS ad;
USE ad;

CREATE TABLE IF NOT EXISTS Equipo (
    id          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(255),
    categoria   VARCHAR(255),
    disponible  BIT
);

INSERT INTO Equipo (nombre, categoria, disponible) VALUES
 ('Portátil Dell XPS 13', 'Informática', 1),
 ('Proyector Epson EB-X06', 'Audiovisual', 1),
 ('Cámara Canon EOS 250D', 'Audiovisual', 0),
 ('Router Cisco 2901', 'Redes', 1),
 ('Switch HP 24 puertos', 'Redes', 1),
 ('Tablet Samsung Galaxy Tab', 'Informática', 0),
 ('Micrófono Shure SM58', 'Audiovisual', 1),
 ('Raspberry Pi 4', 'Electrónica', 1),
 ('Kit Arduino Elegoo', 'Electrónica', 0),
 ('Impresora 3D Ender 3', 'Fabricación', 1);

GRANT ALL PRIVILEGES ON ad.* TO 'user'@'%';
FLUSH PRIVILEGES;
