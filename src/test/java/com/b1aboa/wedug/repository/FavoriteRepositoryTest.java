package com.b1aboa.wedug.repository;

import com.b1aboa.wedug.dto.FavoriteDTO;
import com.b1aboa.wedug.entity.Favorite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class FavoriteRepositoryTest {
    @Autowired
    FavoriteRepository favoriteRepository;

    @Test
    public void aa() {
        List<FavoriteDTO> favoriteList =  favoriteRepository.showAllFavorites("joowon");
        System.out.println("================");
        System.out.println("favoriteList:" + favoriteList);
    }
}