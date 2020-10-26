using JJChatAPI.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace JJChatAPI.DataAccess
{
    public class JJChatContext : DbContext
    {
        public DbSet<User> Users { get; set; }
        public DbSet<ChatMessage> Messages { get; set; }
    }
}