package com.b1aboa.wedug.repository;

import java.util.List;

import com.b1aboa.wedug.dto.FavoriteDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.b1aboa.wedug.entity.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query("SELECT f FROM Favorite f WHERE f.userId.userId =:userId")
    List<Favorite> showAllFavorites(@Param("userId") String userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Favorite f WHERE f.userId.userId =:userId AND f.filmId.filmId =:placeId")
    int deleteUserFavorite(@Param("userId") String userId, @Param("placeId") long placeId);

    @Query("SELECT f FROM Favorite f WHERE f.userId.userId =:userId AND f.filmId.filmId =:placeId")
    List<Favorite> isUserFavorite(@Param("userId") String userId, @Param("placeId") long placeId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Favorite(favorite_id, user_id, film_id) VALUES (favorite_id_seq.NEXTVAL, :userId, :placeId)", nativeQuery = true)
    int addUserFavorite(@Param("userId") String userId, @Param("placeId") long placeId);
}
