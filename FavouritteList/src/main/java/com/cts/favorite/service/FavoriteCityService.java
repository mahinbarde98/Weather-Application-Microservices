package com.cts.favorite.service;
import com.cts.favorite.exception.DuplicateCityException;
import com.cts.favorite.model.FavoriteCity;
import com.cts.favorite.repository.FavoriteCityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteCityService {

    @Autowired
    private FavoriteCityRepository repository;

    @Transactional
    public List<FavoriteCity> getFavoriteCities(String userId) {
        return repository.findByUserId(userId);
    }

    @Transactional
    public void addFavoriteCity(String userId, String cityName) {
        if (isCityAlreadyInFavorites(userId, cityName)) {
            throw new DuplicateCityException(cityName);
        }

        FavoriteCity favoriteCity = new FavoriteCity();
        favoriteCity.setUserId(userId);
        favoriteCity.setCityName(cityName);
        repository.save(favoriteCity);
    }

    @Transactional
    private boolean isCityAlreadyInFavorites(String userId, String cityName) {
        List<FavoriteCity> existingFavorites = repository.findByUserId(userId);
        return existingFavorites.stream()
                .anyMatch(city -> city.getCityName().equalsIgnoreCase(cityName));
    }

    @Transactional
    public void removeFavoriteCity(String userId, String cityName) {
        repository.deleteByUserIdAndCityName(userId, cityName);
    }
}
