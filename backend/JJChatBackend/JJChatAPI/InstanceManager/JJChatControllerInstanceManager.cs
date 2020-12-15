using DataAccess;
using SharedData.Interfaces;

namespace JJChatAPI.InstanceManager
{
    public class JJChatControllerInstanceManager
    {
        private static IJJChatController _controllerInstance;

        private static object padlock = new object();

        public static IJJChatController GetInstance()
        {
            lock (padlock)
            {
                if (_controllerInstance == null)
                    _controllerInstance = NewInstance();

                return _controllerInstance;
            }
        }

        public static void RenewInstance()
        {
            lock (padlock)
            {
                if (_controllerInstance != null)
                    _controllerInstance.Dispose();

                _controllerInstance = NewInstance();
            }
        }

        private static IJJChatController NewInstance() => new JJChatController();
    }
}