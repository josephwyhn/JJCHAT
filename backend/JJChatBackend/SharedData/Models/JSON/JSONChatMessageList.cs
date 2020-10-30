using System;
using System.Collections.Generic;
using System.Linq;

namespace SharedData.Models.JSON
{
    public class JSONChatMessageList
    {
        public List<JSONChatMessage> Messages { get; set; }

        public JSONChatMessageList()
        {
            Messages = new List<JSONChatMessage>();
        }

        public JSONChatMessageList(IEnumerable<ChatMessage> messages)
        {
            if (messages == null)
                throw new ArgumentNullException("messages");

            Messages = messages.Select(x => new JSONChatMessage
            {
                Id = x.Id,
                Delivered = x.Delivered,
                Sent = x.Sent,
                Message = x.Message,
                Sender = new JSONUser(x.Sender),
                Receiver = new JSONUser(x.Receiver)
            }).ToList();
        }
    }
}