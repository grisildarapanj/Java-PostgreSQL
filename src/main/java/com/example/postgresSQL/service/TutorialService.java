package com.example.postgresSQL.service;
import com.example.postgresSQL.repository.TutorialRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.postgresSQL.model.Tutorial;
import org.springframework.stereotype.Service;

@Service
public class TutorialService {
    TutorialRepository tutorialRepository;

    public TutorialService(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    public List<Tutorial> findAll(String title) {
        List<Tutorial> tutorials = new ArrayList<Tutorial>();
        if (title == null)
            tutorialRepository.findAll().forEach(tutorials::add);
        else
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

        return tutorials;
    }

    public Tutorial create(Tutorial tutorial) {
        Tutorial copy = new Tutorial(
            tutorial.getTitle(),
            tutorial.getDescription(),
            false
        );
        return tutorialRepository.save(copy);
    }

}
