package com.example.demo;

import org.springframework.web.bind.annotation.*;

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
}