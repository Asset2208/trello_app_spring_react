package com.assetbaitur.trello_app_react.service;

import com.assetbaitur.trello_app_react.entities.CardTasks;
import com.assetbaitur.trello_app_react.entities.Cards;

import java.util.List;

public interface CardService {

    List<Cards> getAllCards();
    Cards addCard(Cards card);
    Cards getCard(Long id);
    void deleteCard(Cards card);
    Cards saveCard(Cards card);

    List<CardTasks> getAllTasks();
    CardTasks addCardTask (CardTasks cardTask);
    CardTasks getCardTask (Long id);
    void deleteCardTask (CardTasks cardTask);
    CardTasks saveCardTask (CardTasks cardTask);
    List<CardTasks> getTasksByCardId(Long id);
}
