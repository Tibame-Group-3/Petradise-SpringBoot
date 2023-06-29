package tw.idv.petradisespringboot.chat.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
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
		 List<ChatMessage> messages = new ArrayList<>(chatMessageRepository.findAll());
		 messages.sort(Comparator.comparing(message -> Optional.ofNullable(message.getTimestamp()).orElse(Instant.EPOCH)));
		return messages; // 返回排序后的messages，而不是findAll的结果
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
		
		chatMessage.setTimestamp(Instant.now());
		chatMessage.setChatType(ChatType.CHAT);
		chatMessageRepository.save(chatMessage);
		return chatMessage; // 返回時會將訊息送至/topic/public
	}

}

//// 加入聊天室
//@MessageMapping("/join")
//@SendTo("/topic/public")
//public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//
//	String content = chatMessage.getContent();
//
//	String username = chatMessage.getSender();
//	// 把username加入WebSocket的Session
//	headerAccessor.getSessionAttributes().put("username", username);
//	if (content != null) {
//
//		headerAccessor.getSessionAttributes().put("content", content);
//		chatMessage.setContent(content);
//	}
//	chatMessage.setSender(username);
//	
//
//
//	chatMessageRepository.save(chatMessage);
//
////	return chatMessage; // 返回時會將訊息送至/topic/public
//
//}
