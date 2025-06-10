package com.edu.bcu.service;

import com.edu.bcu.entity.UserFavorite;
import com.edu.bcu.repository.jpa.UserFavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFavoriteService {
    private final UserFavoriteRepository favoriteRepository;

    public UserFavoriteService(UserFavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public UserFavorite addFavorite(Long userId, Long movieId) {
        if (exists(userId, movieId)) {
            throw new RuntimeException("已收藏该电影");
        }
        UserFavorite favorite = new UserFavorite();
        favorite.setUserId(userId);
        favorite.setMovieId(movieId);
        return favoriteRepository.save(favorite);
    }

    public void removeFavorite(Long userId, Long movieId) {
        favoriteRepository.deleteByUserIdAndMovieId(userId, movieId);
    }

    public boolean exists(Long userId, Long movieId) {
        return favoriteRepository.existsByUserIdAndMovieId(userId, movieId);
    }

    public List<UserFavorite> getFavoritesByUserId(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }
}