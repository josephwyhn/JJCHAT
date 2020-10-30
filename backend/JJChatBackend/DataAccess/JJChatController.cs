using DataAccess.DBContext;
using DataAccess.EF;
using SharedData.Interfaces;
using SharedData.Models;
using SharedData.Models.JSON;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess
{
    public class JJChatController : IJJChatController
    {
        private DbContext _dbContext;

        private IRepository<User> _userRepo;
        private IRepository<ChatMessage> _chatMessageRepo;

        public JJChatController()
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

            return users.FirstOrDefault();
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

        public void SendMessage(JSONChatMessage jsonMessage)
        {
            var message = new ChatMessage
            {
                Id = jsonMessage.Id,
                Delivered = jsonMessage.Delivered,
                Sent = jsonMessage.Sent,
                Message = jsonMessage.Message,
                Sender = Login(jsonMessage.Sender.Username, jsonMessage.Sender.Password),
                Receiver = Login(jsonMessage.Receiver.Username, jsonMessage.Receiver.Password)
            };

            if (_chatMessageRepo.GetAll(x => x == message).Any())
                throw new Exception("Nachricht wurde bereits verschickt!");

            if (message.Receiver == null || message.Sender == null)
                throw new Exception("Ungültiger Empfänger oder Versender!");

            if (message.Sent == null) message.Sent = DateTime.Now;

            _chatMessageRepo.Insert(message);
            _chatMessageRepo.Save();
        }

        public IEnumerable<ChatMessage> GetMessages(JSONUser jsonUser)
        {
            if (jsonUser == null)
                throw new ArgumentNullException("jsonUser");

            var user = Login(jsonUser.Username, jsonUser.Password);

            if (user == null)
                throw new Exception("Ungültiger Benutzer! Nachrichten werden nicht abgerufen!");

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
        ~JJChatController()
        {
            Dispose(false);
        }
        #endregion
    }
}