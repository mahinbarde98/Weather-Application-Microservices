package com.cts.favorite.repository;


import com.cts.favorite.model.FavoriteCity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteCityRepository extends JpaRepository<FavoriteCity, Long> {
    List<FavoriteCity> findByUserId(String userId);
    void deleteByUserIdAndCityName(String userId, String cityName);
}
