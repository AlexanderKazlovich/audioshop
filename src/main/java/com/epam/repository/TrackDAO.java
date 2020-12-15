package com.epam.repository;

import com.epam.domain.audio.Track;
import com.epam.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.util.List;

public class TrackDAO extends AbstractDao<Track> {
    public TrackDAO(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    @Override
    public List<Track> findAll() {
        return null;
    }

    @Override
    public void delete(String username) {

    }

    @Override
    public void update(Track track) {

    }
}
