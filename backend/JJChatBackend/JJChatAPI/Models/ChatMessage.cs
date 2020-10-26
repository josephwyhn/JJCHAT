using System;
using System.ComponentModel.DataAnnotations;

namespace JJChatAPI.Models
{
    public class ChatMessage
    {
        [Key]
        public long Id { get; set; }

        public DateTime Sent { get; set; }
        public string Content { get; set; }

        public bool Delivered { get; set; }
        
        public virtual User Sender { get; set; }
        public virtual User Receiver { get; set; }
    }
}