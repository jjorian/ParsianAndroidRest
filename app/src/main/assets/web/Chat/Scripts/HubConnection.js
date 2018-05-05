var SignalrConnection;
var ChatProxy;

function Connect() {
    ChatServerUrl = "http://192.168.1.146:8085/";
    var ChatUrl = ChatServerUrl + "signalr";
    //This will hold the connection to the signalr hub   
    SignalrConnection = $.hubConnection(ChatUrl, {
        useDefaultPath: false
    });
    ChatProxy = SignalrConnection.createHubProxy('MyHub');
    //This will be called by signalr   
    ChatProxy.on("broadcastMessage", function (code,username,message) {
        //chatlog.text(message);
		//var chatlog = document.getElementById("chatlog");
	    //chatlog.innerHTML+=(username+ "<br />" +message+ "<br />");	
				var chatbox = document.getElementById("chatbox");
		chatbox.innerHTML+=(username+ "<br />" +message+ "<br />");		
		chatbox.scrollTop = chatbox.scrollHeight;		
    });  
    //connecting the client to the signalr hub   
    SignalrConnection.start().done(function() {
            //alert("Connected to Signalr Server");
        })
        .fail(function() {
           // alert("failed in connecting to the signalr server");
        });
    document.getElementById("txtmessage")
    .addEventListener("keyup", function(event) {
    event.preventDefault();
    if (event.keyCode === 13) {
        document.getElementById("submitmsg").click();
    }
});
}
function SendToAll() {
    
	var txt_message=document.getElementById('txtmessage');  
    ChatProxy.invoke('SendToAll', '101','کاربر1',txt_message.value);
	txt_message.value='';
	txt_message.focus();
}  

