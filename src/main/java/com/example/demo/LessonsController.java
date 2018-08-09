package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonsController {
    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return repository.save(lesson);
    }

    @GetMapping("/{id}")
    public Optional<Lesson> read(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PatchMapping("/{id}")
    public Lesson update(@PathVariable Long id, @RequestBody Lesson lessonUpdates) throws Exception {
        Lesson lesson = read(id).orElseThrow(() -> new Exception("ID not found: " + id));
        lesson.setTitle(lessonUpdates.getTitle());
        lesson.setDeliveredOn(lessonUpdates.getDeliveredOn());
        return create(lesson);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("")
    public Iterable<Lesson> list() {
        return repository.findAll();
    }

    @GetMapping("/find/{title}")
    public Lesson find(@PathVariable String title) {
        return repository.findByTitle(title);
    }

    @GetMapping("/between")
    public Iterable<Lesson> between(@RequestParam String date1, @RequestParam String date2) throws ParseException {
        return repository.betweenForDeliveredOn(toDate(date1), toDate(date2));
    }

    private Date toDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }
}