# por ahora son estos los valores que deberian estar incluidos en la base de datos, 
# sino los podeis ir añadiendo vosotros cpiando y pegando en el terminal dentro de mysql y la base de datos
# si vais añadiendo ponerlos aqui y actualizar el archivo.  	Merci entrenadores

# mysql -u root - p
# password: mysql

insert into etakemon (nombre, habilidad, tipo) values ("Alakasals","hipnosis",1);
insert into etakemon (nombre, habilidad, tipo) values ("Danisloth","rascada a una mano",2);
insert into etakemon (nombre, habilidad, tipo) values ("Davidos","mirada seductora",2);
insert into etakemon (nombre, habilidad, tipo) values ("Livanny", "DOStias",3);
insert into etakemon (nombre, habilidad, tipo) values ("Tonix","QueryParaTi",1);
insert into etakemon (nombre, habilidad, tipo) values ("Wooperal","merengada",3);

insert into localizacion (nombre, longitud, latitud) values ("EETAC",1.9872137904167175, 41.275591139229704);
insert into localizacion (nombre, longitud, latitud) values ("ESAB",1.986626386642456,41.275742324160106);
insert into localizacion (nombre, longitud, latitud) values ("Biblioteca",1.9853060692548752,41.27546313571486);
insert into localizacion (nombre, longitud, latitud) values ("ICFO",1.9895660877227783,41.27559718663362);
insert into localizacion (nombre, longitud, latitud) values ("ICFO lago",1.9898155331611633,41.27508920274953);
insert into localizacion (nombre, longitud, latitud) values ("UOC",1.98815256357193,41.27525449952666);
insert into localizacion (nombre, longitud, latitud) values ("UOC lago",1.9883671402931213,41.27477070290112);
insert into localizacion (nombre, longitud, latitud) values ("RDIT lago",1.9895526766777039,41.27593987194151);
insert into localizacion (nombre, longitud, latitud) values ("RDIT campo",1.9887346029281616,41.27611322970612);
insert into localizacion (nombre, longitud, latitud) values ("Profes secundario",1.987728774547577,41.275871335023865);
insert into localizacion (nombre, longitud, latitud) values ("Recepcion EETAC",1.987064927816391,41.27551252292746);
insert into localizacion (nombre, longitud, latitud) values ("Azul pasillo",1.986842304468155,41.27542785911152);
insert into localizacion (nombre, longitud, latitud) values ("Azul calle",1.986992508172989,41.2750388073074);
insert into localizacion (nombre, longitud, latitud) values ("Azul calle2",1.9867350161075592,41.27564959744429);
insert into localizacion (nombre, longitud, latitud) values ("Azul clase",1.9868671149015427,41.27526861020775);
insert into localizacion (nombre, longitud, latitud) values ("Amarilla pasillo",1.9865472614765167,41.27535932165626);
insert into localizacion (nombre, longitud, latitud) values ("Amarilla calle",1.9867269694805145,41.27498034854577);
insert into localizacion (nombre, longitud, latitud) values ("Amarilla calle2",1.98646679520607,41.275585091825185);
insert into localizacion (nombre, longitud, latitud) values ("Amarilla clase",1.9865848124027252,41.2751899935171);
insert into localizacion (nombre, longitud, latitud) values ("Roja pasillo",1.9862709939479828,41.27530086318167);
insert into localizacion (nombre, longitud, latitud) values ("Roja calle",1.9864372909069061,41.274909794798226);
insert into localizacion (nombre, longitud, latitud) values ("Roja calle2",1.986163705587387,41.275518570338704);
insert into localizacion (nombre, longitud, latitud) values ("Roja clase",1.9862870872020721,41.27513153489086);
insert into localizacion (nombre, longitud, latitud) values ("MalezaChunga",1.985454261302948,41.27514967722875);
insert into localizacion (nombre, longitud, latitud) values ("PingPong",1.9860939681529999,41.27513355070642);
insert into localizacion (nombre, longitud, latitud) values ("Camino salvaje resi",1.9862200319766998,41.27465983233608);
insert into localizacion (nombre, longitud, latitud) values ("Resi",1.985192745923996,41.274536866580064);
insert into localizacion (nombre, longitud, latitud) values ("Resi lago",1.985427439212799,41.27414982531144);
insert into localizacion (nombre, longitud, latitud) values ("Tierra Salvaje1",1.9846925139427185,41.27523030978055);
insert into localizacion (nombre, longitud, latitud) values ("Tierra Salvaje2",1.984831988811493,41.274948095413485);
insert into localizacion (nombre, longitud, latitud) values ("Huerto Chungo",1.9883403182029724,41.276359155047444);

insert into logros (nombre, descripcion, experiencia) values ("etakeColector", "captura 10 etakemons", 100);
insert into logros (nombre, descripcion, experiencia) values ("etakeColector platino", "captura 20 etakemons", 200);
insert into logros (nombre, descripcion, experiencia) values ("etakeColector gold", "captura 50 etakemons", 300);
insert into logros (nombre, descripcion, experiencia) values ("etakeAtakker", "Libra una batalla etakemon", 50);
insert into logros (nombre, descripcion, experiencia) values ("etakeAtakker malote", "gana 5 batallas", 200);
insert into logros (nombre, descripcion, experiencia) values ("etakeAtakker destroyer", "gana 20 batallas", 500);
insert into logros (nombre, descripcion, experiencia) values ("etakeColector tipo1", "captura 5 etakemons tipo 1", 100);
insert into logros (nombre, descripcion, experiencia) values ("etakeColector tipo1 platino", "captura 10 etakemons tipo 1", 200);
insert into logros (nombre, descripcion, experiencia) values ("etakeColector tipo1 gold", "captura 20 etakemons tipo 1", 500);
insert into logros (nombre, descripcion, experiencia) values ("etakeColector tipo2", "captura 5 etakemons tipo 2", 100);
insert into logros (nombre, descripcion, experiencia) values ("etakeColector tipo2 platino", "captura 10 etakemons tipo 2", 200);
insert into logros (nombre, descripcion, experiencia) values ("etakeColector tipo2 gold", "captura 20 etakemons tipo 2", 500);
insert into logros (nombre, descripcion, experiencia) values ("etakeColector tipo3", "captura 5 etakemons tipo 3", 100);
insert into logros (nombre, descripcion, experiencia) values ("etakeColector tipo3 platino", "captura 10 etakemons tipo 3", 200);
insert into logros (nombre, descripcion, experiencia) values ("etakeColector tipo3 gold", "captura 20 etakemons tipo 3", 500);
insert into logros (nombre, descripcion, experiencia) values ("etakeKedex", "captura todos los etakemons", 1000);

insert into objetos (nombre, descripcion) values ("etakeball", "úsala para atrapar a los etakemons salvajes");
insert into objetos (nombre, descripcion) values ("revivir", "trae de vuelta a tu etakemon");
insert into objetos (nombre, descripcion) values ("pocion1", "cura a tu etakemon");
insert into objetos (nombre, descripcion) values ("pocion2", "cura media vida a tu etakemon");
insert into objetos (nombre, descripcion) values ("pocion3", "cura al maximo a tu etakemon");

insert into usuario (nombre, nick, email, contrasena, nivel, experiencia, modified, admin) values ("Ivan","sweex","munozloisivan@gmail.com","password",5,100,0,1);
insert into usuario (nombre, nick, email, contrasena, nivel, experiencia, modified, admin) values ("Roberto", "DomingueroRob", "roberto.arranz.93@gmail.com", "password", 5, 100, 0, 1);
insert into usuario (nombre, nick, email, contrasena, nivel, experiencia, modified, admin) values ("Daniel", "Sob", "danimb93@gmail.com", "password", 5, 100, 0, 1);
insert into usuario (nombre, nick, email, contrasena, nivel, experiencia, modified, admin) values ("Mariona", "marionn3", "rovira.caliz@gmail.com", "password", 3, 50, 0,0);

insert into captura (idusuariosss, idetakemon, idlocalizacion, nivel, experiencia, vida, ataque, defensa, estado, fecha) values (7,4,5,5,100,90,90,90,1,'2017-05-05 17:45:32');
insert into captura (idusuariosss, idetakemon, idlocalizacion, nivel, experiencia, vida, ataque, defensa, estado, fecha) values (8,1,19,5,100,90,90,90,1,'2017-05-05 17:46:34');
insert into captura (idusuariosss, idetakemon, idlocalizacion, nivel, experiencia, vida, ataque, defensa, estado, fecha) values (9,2,24,5,100,90,90,90,1,'2017-05-05 17:46:53');

insert into batalla (idcaptura, resultado, experiencia, fecha) values (5,1,100,'2017-05-05 17:45:32');
insert into batalla (idcaptura, resultado, experiencia, fecha) values (6,1,100,'2017-05-05 17:45:32');
insert into batalla (idcaptura, resultado, experiencia, fecha) values (7,1,100,'2017-05-05 17:45:32');

insert into logrosusuario (idlogros,idusuarios) values (22,7);
insert into logrosusuario (idlogros,idusuarios) values (22,8);
insert into logrosusuario (idlogros,idusuarios) values (22,9);

insert into objetosusuario (idobjeto,idusuario) values (3,7);
insert into objetosusuario (idobjeto,idusuario) values (3,7);
insert into objetosusuario (idobjeto,idusuario) values (3,7);
insert into objetosusuario (idobjeto,idusuario) values (3,7);
insert into objetosusuario (idobjeto,idusuario) values (3,7);
insert into objetosusuario (idobjeto,idusuario) values (3,7);
insert into objetosusuario (idobjeto,idusuario) values (4,7);
insert into objetosusuario (idobjeto,idusuario) values (4,7);
insert into objetosusuario (idobjeto,idusuario) values (4,7);
insert into objetosusuario (idobjeto,idusuario) values (4,7);
insert into objetosusuario (idobjeto,idusuario) values (4,7);
insert into objetosusuario (idobjeto,idusuario) values (7,7);
insert into objetosusuario (idobjeto,idusuario) values (7,7);
insert into objetosusuario (idobjeto,idusuario) values (7,7);
insert into objetosusuario (idobjeto,idusuario) values (3,8);
insert into objetosusuario (idobjeto,idusuario) values (3,8);
insert into objetosusuario (idobjeto,idusuario) values (3,8);
insert into objetosusuario (idobjeto,idusuario) values (3,8);
insert into objetosusuario (idobjeto,idusuario) values (3,8);
insert into objetosusuario (idobjeto,idusuario) values (3,8);
insert into objetosusuario (idobjeto,idusuario) values (4,8);
insert into objetosusuario (idobjeto,idusuario) values (4,8);
insert into objetosusuario (idobjeto,idusuario) values (4,8);
insert into objetosusuario (idobjeto,idusuario) values (4,8);
insert into objetosusuario (idobjeto,idusuario) values (4,8);
insert into objetosusuario (idobjeto,idusuario) values (7,8);
insert into objetosusuario (idobjeto,idusuario) values (7,8);
insert into objetosusuario (idobjeto,idusuario) values (7,8);
insert into objetosusuario (idobjeto,idusuario) values (3,9);
insert into objetosusuario (idobjeto,idusuario) values (3,9);
insert into objetosusuario (idobjeto,idusuario) values (3,9);
insert into objetosusuario (idobjeto,idusuario) values (3,9);
insert into objetosusuario (idobjeto,idusuario) values (3,9);
insert into objetosusuario (idobjeto,idusuario) values (3,9);
insert into objetosusuario (idobjeto,idusuario) values (4,9);
insert into objetosusuario (idobjeto,idusuario) values (4,9);
insert into objetosusuario (idobjeto,idusuario) values (4,9);
insert into objetosusuario (idobjeto,idusuario) values (4,9);
insert into objetosusuario (idobjeto,idusuario) values (4,9);
insert into objetosusuario (idobjeto,idusuario) values (7,9);
insert into objetosusuario (idobjeto,idusuario) values (7,9);
insert into objetosusuario (idobjeto,idusuario) values (7,9);