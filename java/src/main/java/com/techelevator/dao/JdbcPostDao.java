package com.techelevator.dao;

import com.techelevator.model.Playlist;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcPostDao {
    JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addPlaylist(Playlist playlist) throws DaoException {
        String sql = "Insert into playlists (playlist_id, playlist_name, dj_id) values (?,?,?);";
        try {
            jdbcTemplate.update(sql, playlist.getPlaylistId(), playlist.getPlaylistName(), playlist.getDj_id());

        }  catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }


}
