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
            if (string.IsNullOrWhiteSpace(username) || string.IsNullOrWhiteSpace(password))
                throw new Exception("Benutzername oder Passwort waren leer!");

            if (_userRepo.GetAll(x => x.Username == username).Any())
                throw new Exception("Benutzername wird bereits verwendet");

            var user = new User
            {
                Username = username,
                Password = password
            };

            user = _userRepo.Insert(user);
            _userRepo.Save();

            return user;
        }

        public void Send(ChatMessage message)
        {
            if (_chatMessageRepo.GetAll(x => x == message).Any())
                throw new Exception("Nachricht wurde bereits verschickt!");

            if (message.Receiver == null || message.Sender == null)
                throw new Exception("Ungültiger Empfänger oder Versender!");

            if (message.Sent == null) message.Sent = DateTime.Now;

            _chatMessageRepo.Insert(message);
            _chatMessageRepo.Save();
        }

        public IEnumerable<ChatMessage> Get(User user)
        {
            if (user == null)
                throw new ArgumentNullException("user");

            return _chatMessageRepo.GetAll(x => x.Receiver == user || x.Sender == user);
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
                _dbContext.Dispose();
                _dbContext = null;
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