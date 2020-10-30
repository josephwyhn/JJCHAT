using DataAccess;
using JJChatAPI.DataAccess;
using SharedData.Interfaces;
using SharedData.Models;
using SharedData.Models.JSON;
using System.Collections.Generic;
using System.Web.Http;

namespace JJChatAPI.Controllers
{
    public class ChatMessageController : ApiController
    {
        //ngrok http -host-header=localhost 8080
        private IJJChatController _controllerInstance;

        public ChatMessageController()
        {
            _controllerInstance = JJChatControllerInstanceManager.GetInstance();
        }

        // GET api/ChatMessage
        public JSONChatMessageList Get(JSONUser user) => new JSONChatMessageList(_controllerInstance.GetMessages(user));

        // POST api/ChatMessage
        public void Post(JSONChatMessage message) => _controllerInstance.SendMessage(message);
    }
}