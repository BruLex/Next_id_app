#ifndef CLIENT_H
#define CLIENT_H
#include <QObject>
#include <QTcpSocket>
#include <QDebug>
class client : QTcpSocket
{
    Q_OBJECT
public:
    client();
    QTcpSocket*  socket;
    QByteArray Data;
public slots:
    void sockReady();
    void sockDisc();
    void con();

};

#endif // CLIENT_H
