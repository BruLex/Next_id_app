#install java
yum -y install java-devel

#install nc
yum -y install nc

#create and init alias for app
echo 'alias nextid="nc localhost 9999 <<< \"next\""' >> ./.bashrc
source ./.bashrc


