using SharedData.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SharedData.Interfaces
{
    public interface IJJChatContextController
    {
        User Login(string username, string password);
        User Register(string username, string password);

        void Send(ChatMessage message);
        IEnumerable<ChatMessage> Get(User user);
    }
}