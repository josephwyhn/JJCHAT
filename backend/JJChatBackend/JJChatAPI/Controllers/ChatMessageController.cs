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
        //ngrok http -host-header=localhost 51857
        private IJJChatController _controllerInstance;

        public ChatMessageController()
        {
            _controllerInstance = JJChatControllerInstanceManager.GetInstance();
        }

        // GET api/ChatMessage
        public JSONChatMessageList Get(string username, string password) => new JSONChatMessageList(_controllerInstance.GetMessages(new JSONUser { username = username, password = password }));

        // POST api/ChatMessage
        public void Post([FromBody]JSONChatMessage message) => _controllerInstance.SendMessage(message);
    }
}