package com.assetbaitur.trello_app_react.rest;

import com.assetbaitur.trello_app_react.entities.CardTasks;
import com.assetbaitur.trello_app_react.entities.Cards;
import com.assetbaitur.trello_app_react.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api")
public class MainRestController {

    @Autowired
    private CardService cardService;

    @GetMapping("/cards")
    public ResponseEntity<?> getAllCards(){
        List<Cards> cards = cardService.getAllCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/cards/{id}")
    public ResponseEntity<Cards> getCard(@PathVariable Long id){
        Cards card = cardService.getCard(id);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @PostMapping("/cards")
    public ResponseEntity<?> addCard(@RequestBody Cards card){
        cardService.addCard(card);
        return ResponseEntity.ok(card);
    }

    @PutMapping("/cards/{id}")
    public ResponseEntity<Cards> updateCard(@PathVariable Long id, @RequestBody Cards cardDetails){
        Cards card = cardService.getCard(id);
        card.setName(cardDetails.getName());
        cardService.saveCard(card);
        return ResponseEntity.ok(card);
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id) {
        Cards card = cardService.getCard(id);
        cardService.deleteCard(card);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/cards/{id}/tasks")
    public ResponseEntity<?> getCardTasks(@PathVariable Long id){
        List<CardTasks> cardTasks = cardService.getTasksByCardId(id);
        Collections.reverse(cardTasks);
        
        return new ResponseEntity<>(cardTasks, HttpStatus.OK);
    }

    @PostMapping("/cards/{id}/tasks")
    public ResponseEntity<?> addCardTask(@RequestBody CardTasks task){
        cardService.addCardTask(task);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<CardTasks> getCardTaskById(@PathVariable Long id){
        CardTasks cardTask = cardService.getCardTask(id);

        return new ResponseEntity<>(cardTask, HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<CardTasks> updateCardTask(@PathVariable Long id, @RequestBody CardTasks cardTask){
        CardTasks task = cardService.getCardTask(id);
        task.setDone(cardTask.isDone());
        cardService.saveCardTask(task);
        return ResponseEntity.ok(task);
    }
}
