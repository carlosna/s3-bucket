FROM node:8.11.3

ENV APP_ROOT /opt/portal-app
ENV PATH /opt/portal-app/node_modules/.bin:$PATH

RUN mkdir $APP_ROOT
WORKDIR $APP_ROOT

RUN npm i -g @angular/cli \
    && npm i -g typescript

# add app
COPY . /opt/portal-app
	
EXPOSE 4200

CMD ng serve --host 0.0.0.0
