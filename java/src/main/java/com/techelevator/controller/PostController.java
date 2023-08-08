package com.techelevator.controller;

import com.techelevator.dao.DaoException;
import com.techelevator.dao.JdbcPostDao;
import com.techelevator.model.Playlist;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin

public class PostController {
    JdbcPostDao jdbcPostDao;
    public PostController(JdbcPostDao jdbcPostDao){
        this.jdbcPostDao = jdbcPostDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/playlist/add")
    public void addPlaylist(@Valid @RequestBody Playlist playlist){
        try {
            jdbcPostDao.addPlaylist(playlist);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
