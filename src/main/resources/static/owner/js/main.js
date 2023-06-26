'use strict';



$(document).ready(function() {
   
    loadMessages();
});

//消息拿出顯示到前端
function loadMessages() {
    $.ajax({
        url: '/api/messages', 
        method: 'GET',
        success: function(response) {
            console.log(response);
            var messages = response;
            for (var i = 0; i < messages.length; i++) {
                var message = messages[i];
                appendMessage(message);
            }
        },
        error: function(xhr, status, error) {
            
            console.error('Failed to load messages:', error);
        }
    });
}

function appendMessage(message) {
	
	if (message.content !== null && message.content !== "") { 
    var messageElement = $('<li></li>');
    
    var senderElement = $('<span></span>').text(message.sender + ": ");
    messageElement.append(senderElement);
    
    var contentElement = $('<span></span>');
    if (message.content !== null) {
        contentElement.text(message.content);
    }
    messageElement.append(contentElement);
    
    $('#messageArea').append(messageElement);
    }
}


var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
var nameInput = document.querySelector('#name');
var popup = document.querySelector('#hint');

var stompClient = null;
var username = null;

/**
 * 頭像的顏色
 */
var colors = [ '#2196F3', '#32c787', '#00bcd4','#4dbb00', '#ff5652', '#ffc107',
        '#ff85af', '#ff9800', '#39bbb0', '#b0c503' ];

/**
 * 連線
 * @param event
 * @returns
 */
function connect(event) {
    username = document.querySelector('#name').value.trim();

    if (username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/chatroom');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    } else {
        popHint();
    }
    event.preventDefault();
}

/**
 * 連線建立後要處理的邏輯
 * @returns
 */
function onConnected() {
    // 訂閱/topic/public
    stompClient.subscribe('/topic/public', onMessageReceived); // 當後端送訊息至/topic/public時，會執行onMessageReceived()。

    // 發送訊息至/app/join，也就是送到ChatController.addUser()
    stompClient.send("/app/join", {}, JSON.stringify({
        sender : username,
        type : 'JOIN'
    }))

    connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

/**
 * 彈出提示
 * @returns
 */
function popHint() {
    popup.classList.toggle("show");
}

/**
 * 發送訊息
 * @param event 發送訊息事件
 * @returns
 */
function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        var chatMessage = {
            sender : username,
            content : messageInput.value,
            type : 'CHAT'
        };
        // 發送訊息至/app/chat，也就是送到ChatController.sendMessage()
        stompClient.send("/app/chat", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

/**
 * 從後端接受訊息後要進行的處理
 * @param payload 後端送來的訊息
 * @returns
 */
function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if (message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' 加入聊天室';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' 離開聊天室';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = getAvatarElement(message.sender);
        messageElement.appendChild(avatarElement);

        var usernameElement = getUsernameElement(message.sender);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    
    if(message.content !== null){
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
}
    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

/**
 * 取得頭像元素
 * @param sender 訊息發送者名稱
 * @returns
 */
function getAvatarElement(sender) {
    var avatarElement = document.createElement('i');
    var avatarText = document.createTextNode(sender[0]);
    avatarElement.appendChild(avatarText);
    avatarElement.style['background-color'] = getAvatarColor(sender);
    return avatarElement;
}

/**
 * 取得頭像顏色
 * @param sender 訊息發送者名稱
 * @returns
 */
function getAvatarColor(sender) {
    var hash = 0;
    for (var i = 0; i < sender.length; i++) {
        hash = 31 * hash + sender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

/**
 * 取得使用者名稱的元素
 * @param sender 使用者名稱
 * @returns
 */
function getUsernameElement(sender) {
    var usernameElement = document.createElement('span');
    var usernameText = document.createTextNode(sender);
    usernameElement.appendChild(usernameText);
    return usernameElement;
}

/**
 * 移除彈出的提示
 * @param event
 * @returns
 */
function removePopup (event) {
    popup.classList.remove("show");
}

nameInput.addEventListener('focus', removePopup, true)
usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)