var stompClient = null;

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived); // publicチャットルームに購読
}

function onError(error) {
    // エラー処理
    console.log("エラーが発生しました: " + error);
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    var messageContainer = document.getElementById('message-container');
    messageContainer.innerHTML += "<b>" + message.sender + ":</b> " + message.content + "<br/>";
}

document.addEventListener('DOMContentLoaded', function() {
    var form = document.querySelector('form');
    form.addEventListener('submit', function(e) {
        e.preventDefault();
    });

    var sendButton = document.getElementById('send');
    sendButton.addEventListener('click', sendMessage);
});

function sendMessage() {
    var messageInput = document.getElementById('message');
    var messageContent = messageInput.value.trim();
    var username = document.getElementById('username').value;
    var fileInput = document.getElementById('image');
    var file = fileInput.files[0];
    var reader = new FileReader();

    reader.onloadend = function() {
        var base64data = reader.result; // 画像ファイルをBase64形式に変換

        if (messageContent && stompClient) {
            var chatMessage = {
                sender: username, // ユーザー名を設定
                content: messageContent,
                image: base64data // 画像データを追加
            };
            stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
            messageInput.value = '';

            var messageElement = document.createElement('li');
            messageElement.innerHTML = "<b>" + chatMessage.sender + ":</b> " + chatMessage.content;
            if (chatMessage.image) {
                var breakElement = document.createElement('br'); // 改行要素を作成
                messageElement.appendChild(breakElement); // 改行要素を追加

                var imageElement = document.createElement('img');
                imageElement.src = chatMessage.image;
                messageElement.appendChild(imageElement);
            }
            document.getElementById('message-container').appendChild(messageElement);
        }
    }
    
    if (file) {
        reader.readAsDataURL(file);
    } else {
        reader.onloadend();
    }
}