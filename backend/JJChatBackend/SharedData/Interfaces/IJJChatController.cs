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

        void SendMessage(JSONChatMessage message);
        IEnumerable<ChatMessage> GetMessages(JSONUser user);
    }
}