using Newtonsoft.Json;
using System;
using System.ComponentModel;

namespace SharedData.Models.JSON
{
    public class JSONChatMessage
    {
        public JSONChatMessage() { }

        public JSONChatMessage(ChatMessage msg)
        {
            id = msg.Id;
            sent = msg.Sent;
            message = msg.Message;
            delivered = msg.Delivered;
            sender = msg.SenderId;
            receiver = msg.ReceiverId;
        }

        [DefaultValue(-1)]
        [JsonProperty(DefaultValueHandling = DefaultValueHandling.Populate)]
        public long id { get; set; }
        
        public DateTime sent { get; set; }

        public string message { get; set; }

        public bool delivered { get; set; }

        [DefaultValue(-1)]
        [JsonProperty(DefaultValueHandling = DefaultValueHandling.Populate)]
        public long sender { get; set; }

        [DefaultValue(-1)]
        [JsonProperty(DefaultValueHandling = DefaultValueHandling.Populate)]
        public long receiver { get; set; }
    }
}