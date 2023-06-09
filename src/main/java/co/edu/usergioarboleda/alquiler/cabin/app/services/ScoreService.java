package co.edu.usergioarboleda.alquiler.cabin.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usergioarboleda.alquiler.cabin.app.models.Score;
import co.edu.usergioarboleda.alquiler.cabin.app.repository.ScoreRepository;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository repository;

    public List<Score> getAll() {
        return repository.findAll();
    }

    public Optional<Score> getById(Integer id) {
        return repository.findById(id);
    }

    public Score save(Score score) {
        if (score.getId() == null) {
            return repository.save(score);
        } else {
            if (repository.findById(score.getId()) == null) {
                return repository.save(score);
            } else {
                // throw new RuntimeException("Score not found");
                return score;
            }
        }
    }

    public Score update(Score score) {
        if (score.getId() != null) {
            Optional<Score> optionalScore = repository.findById(score.getId());
            if (optionalScore.isPresent()) {
                if (score.getScore() != null) {
                    optionalScore.get().setScore(score.getScore());
                }
                if (score.getMessage() != null) {
                    optionalScore.get().setMessage(score.getMessage());
                }
                return repository.save(optionalScore.get());
            } else {
                // throw new RuntimeException("Score not found");
                return score;
            }
        } else {
            // throw new RuntimeException("Score not found");
            return score;
        }
    }

    public void deleteAll(Score score) {
        if (score.getId() != null) {
            Optional<Score> optionalScore = repository.findById(score.getId());
            if (optionalScore.isPresent()) {
                repository.delete(score);
            } else {
                // throw new RuntimeException("Score not found");
            }
        } else {
            // throw new RuntimeException("Score not found");
        }

    }

    public void deleteById(Integer id) {
        Optional<Score> optionalScore = repository.findById(id);
        if (optionalScore.isPresent()) {
            repository.delete(optionalScore.get());
        } else {
            // throw new RuntimeException("Score not found");
        }
    }
}
