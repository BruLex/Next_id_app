Vagrant.configure(2) do |config|
  config.vm.box = "centos/7"

  config.vm.network :forwarded_port, guest: 9999, host: 9999

  config.vm.synced_folder "./data", "/tmp/data"

  config.vm.provision "shell", path: "bootstrap.sh", preserve_order: true

  config.vm.provision "shell", path: "compile.sh", preserve_order: true, run: "always"

  config.vm.provision "shell", inline: " sudo /vagrant/build/install/nextid/bin/nextid &", preserve_order: true, run: "always"

end
