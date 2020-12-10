using SharedData.Interfaces;
using System;
using System.IO;
using System.Text;
using System.Threading;

namespace JJChatLogging
{
    public class JJChatFileLogger : IJJChatLogger
    {
        private string _loggingFilePath;

        public string TimeStamp => DateTime.Now.ToString("[yyyy-MM-dd hh:mm:ss]");

        public JJChatFileLogger()
        {
            try
            {
                DefineLoggingFilePath();
            }
            catch (Exception) { }
        }

        private readonly string logFolder = @"D:\JJLogs";
        private void DefineLoggingFilePath()
        {
            _loggingFilePath = Path.Combine(logFolder, "log-" + DateTime.Now.ToString("yyyy-MM-dd") + ".log");
        }

        public void Error(string message, Exception e)
        {
            StringBuilder sb = new StringBuilder();

            var ts = TimeStamp; //safe timestamp so message and exception gets the same
            sb.AppendLine($"{ts} [Exception] --> Message: {message}");
            sb.AppendLine($"{ts} [Exception] --> Exception: {e.ToString()} <EOM>");
            sb.AppendLine();

            AppendText(sb.ToString());
        }

        public void Info(string message)
        {
            StringBuilder sb = new StringBuilder();

            sb.AppendLine($"{TimeStamp} [Info] --> Message: {message} <EOM>");
            sb.AppendLine();

            AppendText(sb.ToString());
        }

        public void Verbose(string message)
        {
            StringBuilder sb = new StringBuilder();

            sb.AppendLine($"{TimeStamp} [Verbose] --> Message: {message} <EOM>");
            sb.AppendLine();

            AppendText(sb.ToString());
        }

        private void AppendText(string text)
        {
            File.AppendAllText(_loggingFilePath, text);
            Thread.Sleep(50); //wait so stream can be closed properly
        }
    }
}