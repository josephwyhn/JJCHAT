using System;

namespace SharedData.Interfaces
{
    public interface IJJChatLogger
    {
        void Error(string message, Exception e);

        void Verbose(string message);

        void Info(string message);
    }
}