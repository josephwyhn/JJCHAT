using DataAccess.DBContext;
using DataAccess.EF;
using SharedData.Interfaces;
using SharedData.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess
{
    public class JJChatContextController : IDisposable, IJJChatContextController
    {
        private DbContext _dbContext;

        private IRepository<User> _userRepo;
        private IRepository<ChatMessage> _chatMessageRepo;

        public JJChatContextController()
        {
            _dbContext = new JJChatContext();

            _userRepo = new EFRepository<User>(_dbContext);
            _chatMessageRepo = new EFRepository<ChatMessage>(_dbContext);
        }

        public User Login(string username, string password)
        {
            List<User> users = _userRepo.GetAll(x => x.Username == username && x.Password == password).ToList();
            if (users.Count > 1)
            {
                throw new Exception("Doppelter Benutzer!");
            }
            else if (!users.Any()) return null;

            return users.First();
        }

        public User Register(string username, string password)
        {
            
        }

        public void Send(ChatMessage message)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<ChatMessage> Get(User user)
        {
            throw new NotImplementedException();
        }


        #region Dispose

        private bool _isDisposed = false;
        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }

        protected virtual void Dispose(bool isCalledByDispose)
        {
            if (_isDisposed)
                return;

            if (isCalledByDispose)
            {

            }

            _isDisposed = true;
        }
        ~JJChatContextController()
        {
            Dispose(false);
        }
        #endregion
    }
}