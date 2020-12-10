using JJChatLogging;
using SharedData.Interfaces;

namespace JJChatAPI.InstanceManager
{
    public class JJChatLoggerInstanceManager
    {
        private static IJJChatLogger _loggerInstance;

        private static readonly object padlock = new object();

        public static IJJChatLogger GetInstance()
        {
            lock (padlock)
            {
                if (_loggerInstance == null)
                    _loggerInstance = NewInstance();

                return _loggerInstance;
            }
        }

        public static void RenewInstance()
        {
            lock (padlock)
            {
                _loggerInstance = NewInstance();
            }
        }

        private static IJJChatLogger NewInstance() => new JJChatFileLogger();
    }
}