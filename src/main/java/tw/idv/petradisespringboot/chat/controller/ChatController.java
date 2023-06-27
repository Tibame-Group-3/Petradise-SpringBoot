package tw.idv.petradisespringboot.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.idv.petradisespringboot.chat.repo.ChatMessageRepository;
import tw.idv.petradisespringboot.chat.vo.ChatMessage;
import tw.idv.petradisespringboot.chat.vo.ChatMessage.ChatType;

@Controller
public class ChatController {

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	@GetMapping("/api/messages")
	@ResponseBody
	public List<ChatMessage> getRecentMessages() {
		return chatMessageRepository.findAll();
	}

	// 加入聊天室
	@MessageMapping("/join")
	@SendTo("/topic/public")
	public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {

		String content = chatMessage.getContent();

		String username = chatMessage.getSender();
		// 把username加入WebSocket的Session
		headerAccessor.getSessionAttributes().put("username", username);
		if (content != null) {

			headerAccessor.getSessionAttributes().put("content", content);
			chatMessage.setContent(content);
		}
		chatMessage.setSender(username);
		
		chatMessage.setChatType(ChatType.JOIN);

		chatMessageRepository.save(chatMessage);

//		return chatMessage; // 返回時會將訊息送至/topic/public

	}

	// 處理訊息
	@MessageMapping("/chat")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {

		String content = chatMessage.getContent();

		String username = chatMessage.getSender();

		chatMessage.setSender(username);
		if (content != null) {
			chatMessage.setContent(content);
		}
		chatMessage.setChatType(ChatType.CHAT);
		chatMessageRepository.save(chatMessage);
		return chatMessage; // 返回時會將訊息送至/topic/public
	}

}
