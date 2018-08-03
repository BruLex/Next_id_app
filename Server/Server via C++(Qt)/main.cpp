#include <QCoreApplication>
#include <QDebug>
#include <QObject>
#include <QTcpServer>
#include <QTcpSocket>
#include <QSqlDatabase>
#include <QSqlQuery>


class MyTcpServer : public QObject
{
private:
    QTcpServer * mTcpServer;
    QTcpSocket * mTcpSocket;
    QSqlDatabase database = QSqlDatabase::addDatabase("QSQLITE");

public:
    MyTcpServer() {

        this->database.setDatabaseName(":memory:");
        this->database.open();
        this->database.exec("CREATE TABLE if not exists nextid (id INTEGER PRIMARY KEY NOT NULL, number INTEGER );");
        this->database.exec("INSERT OR IGNORE INTO nextid VALUES(1,0);");

        mTcpServer = new QTcpServer(this);

        connect(mTcpServer, &QTcpServer::newConnection, this, &MyTcpServer::slotNewConnection);

        if(!mTcpServer->listen(QHostAddress::Any, 9999)){
            qDebug() << "server is not started";
        } else {
            qDebug() << "server is started";
        }
    }

public slots:

    void slotNewConnection() {
        mTcpSocket = mTcpServer->nextPendingConnection();
        qDebug() << "new connection";
        connect(mTcpSocket, &QTcpSocket::readyRead, this, &MyTcpServer::slotServerRead);
        connect(mTcpSocket, &QTcpSocket::disconnected, this, &MyTcpServer::slotClientDisconnected);
    }

    void slotServerRead() {

        while(mTcpSocket->bytesAvailable() > 0)
        {
            QByteArray array;
            array = mTcpSocket->readAll();
            QSqlQuery query;
            if(array.operator ==("next\n")){
                query.exec("UPDATE nextid SET number = number + 1 WHERE id = 1;");
                query.exec("SELECT * FROM nextid");
                query.next();
                array.operator = (QString::asprintf("Your id: %i \n",query.value(1).toInt()).toLatin1());
                mTcpSocket->write(array);

                qDebug() << (mTcpSocket->flush() ? "success transfer" : "transfer error");

            } else {

            }
        }
    }

    void slotClientDisconnected() {
        qDebug() << "close connection";
        mTcpSocket->close();
    }
};

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    MyTcpServer server;

    return a.exec();
}
