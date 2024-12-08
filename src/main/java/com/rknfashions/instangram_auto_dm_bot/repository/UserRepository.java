package com.rknfashions.instangram_auto_dm_bot.repository;

import com.rknfashions.instangram_auto_dm_bot.model.User;
import com.rknfashions.instangram_auto_dm_bot.service.InstagramBotService;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
