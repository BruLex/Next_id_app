mkdir ./data -m 777
touch ./data/database.db
chmod 777 ./data/database.db
sudo apt-get update
sudo apt-get install -y virtualbox
cd /tmp/ && wget https://releases.hashicorp.com/vagrant/2.1.2/vagrant_2.1.2_x86_64.deb
sudo dpkg -i /tmp/vagrant_2.1.2_x86_64.deb
sudo rm -f /tmp/vagrant_2.1.2_x86_64.deb
vagrant plugin install vagrant-vbguest

