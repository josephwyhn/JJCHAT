using JJChatAPI.InstanceManager;
using SharedData.Exceptions;
using SharedData.Interfaces;
using SharedData.Models.JSON;
using System;
using System.Web.Http;

namespace JJChatAPI.Controllers
{
    public class ChatController : ApiController
    {
        //ngrok http -host-header=localhost 51857
        private IJJChatController _controllerInstance;

        public ChatController()
        {
            _controllerInstance = JJChatControllerInstanceManager.GetInstance();
        }

        // GET api/Chat
        public JSONResponse Get(string username, string password)
        {
            var response = new JSONResponse(false, null);

            try
            {
                response.responseObject = _controllerInstance.GetMessages(new JSONUser { username = username, password = password });
            }
            catch (JJLowPrioException jjexc)
            {
                response.isException = true;
                response.responseObject = new JSONJJLowPrioException(jjexc);
            }
            catch (Exception exc)
            {
                JJChatLoggerInstanceManager.GetInstance().Error("Fatal error occured in 'GET api/Chat'!", exc);
                throw exc;
            }

            return response;
        }

        // POST api/Chat
        public JSONResponse Post([FromBody] JSONChatMessage message)
        {
            var response = new JSONResponse(false, null);

            try
            {
                _controllerInstance.SendMessage(message);
                response.responseObject = null;
            }
            catch (JJLowPrioException jjexc)
            {
                response.isException = true;
                response.responseObject = new JSONJJLowPrioException(jjexc);
            }
            catch (Exception exc)
            {
                JJChatLoggerInstanceManager.GetInstance().Error("Fatal error occured in 'POST api/Chat'!", exc);
                throw exc;
            }

            return response;
        }
    }
}