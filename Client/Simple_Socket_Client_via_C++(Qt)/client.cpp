#include "client.h"
#include <QTcpSocket>
client::client() {
    socket = new QTcpSocket(this);
    connect(socket,SIGNAL(readyRead()),this,SLOT(sockReady()));
    connect(socket,SIGNAL(disconnected()),this,SLOT(sockDisc()));
}

void client::con() {
    socket->connectToHost("127.0.0.1" ,9999);
}
void client::sockDisc() {
    socket->disconnect();
}
void client::sockReady() {
    if(socket->waitForConnected(500)) {
        socket->waitForConnected(500);
        Data = socket->readAll();
        qDebug()<<Data;
    }
}
