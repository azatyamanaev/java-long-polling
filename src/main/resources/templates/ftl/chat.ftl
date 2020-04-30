<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script src="http://knockoutjs.com/downloads/knockout-3.4.0.js"></script>
</head>
<body onload="joinChat()">

<input id="user_login" type="hidden" value="${user.login}">

<p>
    <input id="message" name="message" type="text"/>
    <button id="post" type="submit" onclick="postMessage()">Post</button>
</p>

<ul id="messages"></ul>

<form id="joinChatForm" action="/messages" aria-hidden="true">
    <p>
        <input name="messageIndex" type="hidden" data-bind="value: messageIndex"/>
    </p>
</form>

<script type="text/javascript">

    let that = this;

    let viewModel = {
        messageIndex: ko.observable(0)
    }

    that.joinChat = function () {
        pollForMessages();
    }

    function pollForMessages() {
        let form = $("#joinChatForm");
        $.ajax({
            url: form.attr("action"), type: "GET", data: form.serialize(), cache: false,
            success: function (messages) {
                for (let i = 0; i < messages.length; i++) {
                    $('#messages').first().after('<li>' + "[" + messages[i]['login'] + "]" + messages[i]['text'] + '</li>');
                    viewModel.messageIndex(viewModel.messageIndex() + 1);
                }
            },
            complete: pollForMessages
        });
        $('#message').focus();
    }

    function postMessage() {
        $.ajax({
            url: "/messages",
            method: "POST",
            data: "message=" + $('#message').val() + "&login=" + $('#user_login').val(),
            error : function(xhr) {
                console.error("Error posting chat message: status=" + xhr.status + ", statusText=" + xhr.statusText);
            }
        });
    }

    ko.applyBindings(viewModel);
</script>
</body>
</html>