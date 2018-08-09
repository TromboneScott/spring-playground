package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
    Lesson findByTitle(String title);

    @Query("select lesson from Lesson lesson where lesson.deliveredOn BETWEEN ?1 AND ?2")
    Iterable<Lesson> betweenForDeliveredOn(Date from, Date to);
}