using DataAccess;
using JJChatAPI.DataAccess;
using SharedData.Interfaces;
using SharedData.Models;
using SharedData.Models.JSON;
using System.Web.Http;

namespace JJChatAPI.Controllers
{
    public class FriendController : ApiController
    {
        private IJJChatController _controllerInstance;

        public FriendController()
        {
            _controllerInstance = JJChatControllerInstanceManager.GetInstance();
        }

        // GET api/Friend
        public JSONUser Get(string username) => _controllerInstance.GetFriend(username);
    }
}