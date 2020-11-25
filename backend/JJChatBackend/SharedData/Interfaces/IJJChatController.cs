using SharedData.Models;
using SharedData.Models.JSON;
using System;
using System.Collections.Generic;

namespace SharedData.Interfaces
{
    public interface IJJChatController : IDisposable
    {
        User Login(string username, string password);
        User Register(string username, string password);

        JSONUser GetFriend(string username);

        void SendMessage(JSONChatMessage message);
        JSONChatMessageList GetMessages(JSONUser user);
    }
}