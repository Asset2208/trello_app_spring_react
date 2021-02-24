package com.assetbaitur.trello_app_react.service.impl;

import com.assetbaitur.trello_app_react.entities.CardTasks;
import com.assetbaitur.trello_app_react.entities.Cards;
import com.assetbaitur.trello_app_react.repo.CardRepository;
import com.assetbaitur.trello_app_react.repo.CardTasksRepository;
import com.assetbaitur.trello_app_react.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardTasksRepository cardTasksRepository;

    @Override
    public List<Cards> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Cards addCard(Cards card) {
        return cardRepository.save(card);
    }

    @Override
    public Cards getCard(Long id) {
        return cardRepository.findById(id).get();
    }

    @Override
    public void deleteCard(Cards card) {
        cardRepository.delete(card);
    }

    @Override
    public Cards saveCard(Cards card) {
        return cardRepository.save(card);
    }

    @Override
    public List<CardTasks> getAllTasks() {
        return cardTasksRepository.findAll();
    }

    @Override
    public CardTasks addCardTask(CardTasks cardTask) {
        return cardTasksRepository.save(cardTask);
    }

    @Override
    public CardTasks getCardTask(Long id) {
        return cardTasksRepository.findById(id).get();
    }

    @Override
    public void deleteCardTask(CardTasks cardTask) {
        cardTasksRepository.delete(cardTask);
    }

    @Override
    public CardTasks saveCardTask(CardTasks cardTask) {
        return cardTasksRepository.save(cardTask);
    }

    @Override
    public List<CardTasks> getTasksByCardId(Long id) {
        return cardTasksRepository.findAllByCardId(id);
    }
}
