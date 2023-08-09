package com.techelevator.dao;

import com.techelevator.model.Playlist;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGetDao {

    JdbcTemplate jdbcTemplate;

    public JdbcGetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Playlist> getUserPlaylist(int userId) throws DaoException {
        List<Playlist> playlists = new ArrayList<Playlist>();
        String sql = "SELECT * FROM playlists WHERE playlist_id IN (SELECT playlist_id FROM playlist_users WHERE user_id = ?)";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
            while (rowSet.next()) {
                playlists.add(mapRowToPlaylist(rowSet));

            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return playlists;
    }


    public Playlist mapRowToPlaylist(SqlRowSet rowSet) {
        Playlist playlist = new Playlist();
        playlist.setDj_id(rowSet.getInt("dj_id"));
        playlist.setPlaylistId(rowSet.getString("playlist_id"));
        playlist.setPlaylistName(rowSet.getString("playlist_name"));
        return playlist;
    }

}
