package com.cvbuilder.user;

import com.cvbuilder.user.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

//acesso aos métodos para transações com o bd. Esse cara é tipo minha dao, só conversa com banco
@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

}