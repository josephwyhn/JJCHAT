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
            if (string.IsNullOrWhiteSpace(username))
                throw new ArgumentNullException("username");

            if (string.IsNullOrWhiteSpace(password))
                throw new ArgumentNullException("password");

            List<User> users = _userRepo.GetAll(x => x.Username == username && x.Password == password).ToList();
            if (users.Count > 1)
                throw new Exception("Doppelter Benutzer!");

            var user = users.FirstOrDefault();

            if (user == null)
                throw new Exception("Benutzername oder Passwort ist falsch!");

            return user;
        }

        public User Register(string username, string password)
        {
            if (string.IsNullOrWhiteSpace(username) || string.IsNullOrWhiteSpace(password))
                throw new Exception("Benutzername oder Passwort dürfen nicht leer sein!");

            if (_userRepo.GetAll(x => x.Username == username).Any())
                throw new Exception("Benutzername wird bereits verwendet!");

            var user = new User
            {
                Username = username,
                Password = password
            };

            user = _userRepo.Insert(user);
            _userRepo.Save();

            return user;
        }

        public JSONUser GetFriend(string username)
        {
            if (string.IsNullOrWhiteSpace(username))
                throw new Exception("Benutzername des Freundes darf nicht leer sein!");

            var friend = _userRepo.GetAll(x => x.Username == username).FirstOrDefault();

            if (friend == null)
                throw new Exception($"Freund mit dem Benutzernamen '{username}' wurde nicht gefunden (Benutzername ist Case-Sensitive)!");

            return new JSONUser
            {
                id = friend.Id,
                username = friend.Username,
                password = null
            };
        }
        
        public void SendMessage(JSONChatMessage jsonMessage)
        {
            var msgSender = _userRepo.GetAll(x => x.Id == jsonMessage.sender).FirstOrDefault();
            var msgReceiver = _userRepo.GetAll(x => x.Id == jsonMessage.receiver).FirstOrDefault();

            if (msgSender == null)
                throw new Exception($"ungültiger Versender: {jsonMessage.sender}!");

            if (msgReceiver == null)
                throw new Exception($"Ungültiger Empfänger: {jsonMessage.receiver}!");

            if (msgSender == msgReceiver)
                throw new Exception("Versender kann nicht Empfänger sein!");

            var message = new ChatMessage
            {
                Id = jsonMessage.id,
                Delivered = jsonMessage.delivered,
                Sent = jsonMessage.sent,
                Message = jsonMessage.message,
                Sender = msgSender,
                Receiver = msgReceiver
            };

            if (message.Sent == null) message.Sent = DateTime.Now;

            _chatMessageRepo.Insert(message);
            _chatMessageRepo.Save();
        }

        public JSONChatMessageList GetMessages(JSONUser jsonUser)
        {
            if (jsonUser == null)
                throw new ArgumentNullException("jsonUser");

            var user = Login(jsonUser.username, jsonUser.password);

            if (user == null)
                throw new Exception("Ungültiger Benutzer! Nachrichten werden nicht abgerufen!");

            return new JSONChatMessageList(user.SentMessages, user.ReceivedMessages);
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