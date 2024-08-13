package com.b1aboa.wedug.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.b1aboa.wedug.entity.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

	@Query("SELECT f FROM Favorite f WHERE f.userId.userId =:userId")
	List<Favorite> showAllFavorites(@Param("userId") String userId);
}
