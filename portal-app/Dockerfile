FROM node:8.11.3

RUN mkdir /opt/portal-app
WORKDIR /opt/portal-app

ENV PATH /opt/portal-app/node_modules/.bin:$PATH

# install and cache app dependencies
COPY package.json /opt/portal-app/package.json
RUN npm install
RUN npm install -g @angular/cli@1.7.1

# add app
COPY . /opt/portal-app

# start app
CMD ng serve

