using System;
using System.Collections.Generic;
using System.Linq;

namespace SharedData.Models.JSON
{
    public class JSONChatMessageList
    {
        public List<JSONChatMessage> messages { get; set; }

        public JSONChatMessageList()
        {
            messages = new List<JSONChatMessage>();
        }

        public JSONChatMessageList(IEnumerable<ChatMessage> messages)
        {
            if (messages == null)
                throw new ArgumentNullException("messages");

            this.messages = messages.Select(x => new JSONChatMessage
            {
                id = x.Id,
                delivered = x.Delivered,
                sent = x.Sent,
                message = x.Message,
                sender = new JSONUser(x.Sender),
                receiver = new JSONUser(x.Receiver)
            }).ToList();
        }
    }
}