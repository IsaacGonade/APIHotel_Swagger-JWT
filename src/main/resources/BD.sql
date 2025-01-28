DROP DATABASE IF EXISTS apiHoteles;
CREATE DATABASE apiHoteles;
USE apiHoteles;

CREATE TABLE hotel (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(255) NOT NULL,
                         descripcion VARCHAR(255) NOT NULL,
                         categoria VARCHAR(255) NOT NULL,
                         piscina BOOLEAN,
                         localidad VARCHAR(255) NOT NULL
);

CREATE TABLE habitacion (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              tamanio INT NOT NULL,
                              precio INT NOT NULL,
                              desayuno BOOLEAN default false,
                              ocupada BOOLEAN default false,
                              id_hotel INT NOT NULL,
                              FOREIGN KEY (id_hotel) REFERENCES hotel(id) ON DELETE CASCADE
);

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user VARCHAR(255) NOT NULL,
    pwd VARCHAR(255) NOT NULL
);


INSERT INTO hotel VALUES
                      (1, 'Savoy', 'Inaugurado en 1889, el Hotel Savoy fue el primer hotel de lujo en Londres que se construyó como tal.', '5 ESTRELLAS', TRUE, 'Londres'),
                      (2, 'Belmond Copacabana Palace', 'El Belmond Copacabana Palace fue construido inspirándose en los hoteles Negresco de Niza y Carlton de Cannes, ambos en la costa azul francesa.', '4 ESTRELLAS', FALSE, 'Rio de Janeiro'),
                      (3, 'Raffles', 'Es probablemente uno de los únicos hoteles del mundo que tiene una historiadora en plantilla: se trata de Leslie Danker.', '3 ESTRELLAS', TRUE, 'Singapur');


INSERT INTO habitacion VALUES
                           (1, 1, 450, TRUE, FALSE, 1),
                           (2, 2, 100, FALSE, TRUE, 3),
                           (3, 2, 500, TRUE, TRUE, 2),
                           (4, 1, 300, FALSE, FALSE, 1);

INSERT INTO user  VALUES (1, 'juan', 'juan'), (2, 'isaac', 'isaac')