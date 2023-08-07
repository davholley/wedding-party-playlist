BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS playlist_song;
DROP TABLE IF EXISTS songs;
DROP TABLE IF EXISTS playlists;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE playlists(
	playlist_id Serial PRIMARY KEY,
	playlist_name VARCHAR(255),
	dj_id int,
	special_event VARCHAR(255),
	host_id int,
	CONSTRAINT FK_dj_id FOREIGN KEY(dj_id) REFERENCES users(user_id),
	CONSTRAINT FK_host_id FOREIGN KEY(host_id) REFERENCES users(user_id)
	
);
CREATE TABLE songs(
	song_id SERIAL PRIMARY KEY,
	title VARCHAR(255),
	artist VARCHAR(255),
	album VARCHAR(255)
	
);
CREATE TABLE playlist_song(
	playlist_id int ,
	song_id int,
	CONSTRAINT FK_playlist_id FOREIGN KEY(playlist_id) REFERENCES playlists(playlist_id),
	CONSTRAINT FK_song_id FOREIGN KEY(song_id) REFERENCES songs(song_id),
	CONSTRAINT PK_playlist_song PRIMARY KEY(playlist_id, song_id)
);

COMMIT TRANSACTION;
