using SharedData.Models;
using SharedData.Models.JSON;
using System;

namespace SharedData.Interfaces
{
    public interface IJJChatController : IDisposable
    {
        User Login(string username, string password);
        User Register(string username, string password);

        JSONUser GetFriend(string username);

        JSONChatMessage SendMessage(JSONChatMessage message);
        JSONChatMessageList GetMessages(JSONUser user);
    }
}