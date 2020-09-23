INSERT INTO jugador (id, nombre, puntos)
VALUES (1, 'RAFAEL NADAL', 1000);
INSERT INTO jugador (id, nombre, puntos)
VALUES (2, 'ROGER FEDERER', 1500);

INSERT INTO partido (id, fecha_comienzo, estado, id_local, id_visitante, score_local, puntos_game_actual_local, cantidad_games_local, score_visitante, puntos_game_actual_visitante, cantidad_games_visitante)
VALUES (1, CURRENT_DATE, 1, 1, 2, 0, '0', 0, 0, '0', 0);
INSERT INTO partido (id, fecha_comienzo, estado, id_local, id_visitante, score_local, puntos_game_actual_local, cantidad_games_local, score_visitante, puntos_game_actual_visitante, cantidad_games_visitante)
VALUES (2, CURRENT_DATE, 0, 2, 1, 0,'0', 0, 0, '0', 0);