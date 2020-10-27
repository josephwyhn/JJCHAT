using SharedData.Models;
using System.Data.Entity;

namespace DataAccess.DBContext
{
    public class JJChatContext : DbContext
    {
        public DbSet<User> Users { get; set; }
        public DbSet<ChatMessage> Messages { get; set; }
    }
}