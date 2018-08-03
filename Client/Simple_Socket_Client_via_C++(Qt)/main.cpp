#include <QCoreApplication>
#include <QTcpSocket>
#include "client.h"
int main(int argc, char *argv[])
{

    QCoreApplication a(argc, argv);
    client client;

    QTextStream in(stdin);
    QTextStream out(stdout);

       QString line;
       QString ss = "next";
       do
       {
           line = in.readLine();
           if(line.operator==("next"))
               client.con();
       }
       while (!line.isNull());

       out<<line<<endl;
    return a.exec();
}
