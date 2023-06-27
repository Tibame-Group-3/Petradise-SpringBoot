package tw.idv.petradisespringboot.chat.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.chat.vo.ChatMessage;

@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage, String> {
	 List<ChatMessage> findAll();
}