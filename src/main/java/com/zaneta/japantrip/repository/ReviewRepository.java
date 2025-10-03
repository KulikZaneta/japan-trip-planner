package com.zaneta.japantrip.repository;

import com.zaneta.japantrip.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByUser_Nickname(String nickName);

}
