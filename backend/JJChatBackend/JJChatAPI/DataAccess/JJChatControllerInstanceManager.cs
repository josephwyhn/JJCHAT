using DataAccess;
using SharedData.Interfaces;

namespace JJChatAPI.DataAccess
{
    public class JJChatControllerInstanceManager
    {
        private static IJJChatController _controllerInstance;

        public static IJJChatController GetInstance()
        {
            if (_controllerInstance == null)
                _controllerInstance = NewInstance();

            return _controllerInstance;
        }

        public static void RenewInstance()
        {
            if (_controllerInstance != null)
            {
                _controllerInstance.Dispose();
                _controllerInstance = NewInstance();
            }
        }

        private static IJJChatController NewInstance() => new JJChatController();
    }
}