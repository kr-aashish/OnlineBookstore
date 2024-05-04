#! /bin/sh
sudo yum update -y

sudo yum install docker -y
sudo systemctl start docker
sudo usermod -a -G docker ec2-user
sudo systemctl enable docker

sudo curl -L https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

sudo yum install git -y
sudo yum install maven -y

git clone https://github.com/kr-aashish/OnlineBookstore

cd OnlineBookstore
chmod +x initialiseApp.sh && chmod +x client.sh 
sudo ./initialiseApp.sh && ./client.sh