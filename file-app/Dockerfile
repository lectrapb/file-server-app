#########################
### STAGE 1: Build app
#########################
# FROM node:12.22.4-alpine as build-step
FROM node:12 as build-step

RUN mkdir -p /app

WORKDIR /app

RUN npm install -g @angular/cli@12.2.17

COPY ./package.json .
# Install all the dependencies
RUN npm install
COPY . .
#Environment
ARG PROFILE
ENV PROFILE $PROFILE

#Definig profile to build
RUN echo "Environment: ${PROFILE}"
#Generate the build of the application
RUN ng build --${PROFILE}
###############
### STAGE 2: Serve app with nginx ###
###############
FROM nginx as runtime
RUN apt-get update && apt-get install -y curl && apt-get clean
COPY --from=build-step /app/dist/file-app /usr/share/nginx/html
COPY /nginx.conf  /etc/nginx/conf.d/default.conf

# When the container starts, replace the env.js with values from environment variables
CMD ["/bin/sh",  "-c",  "envsubst < /usr/share/nginx/html/assets/env/env.template.js > /usr/share/nginx/html/assets/env/env.js && exec nginx -g 'daemon off;'"]
