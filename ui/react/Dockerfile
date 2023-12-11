# Image to build application
FROM node:20.9.0 AS build-image

WORKDIR /app

COPY . .

RUN npm install

CMD npm run build

# Final image
FROM nginx:alpine3.18-slim

WORKDIR /app

COPY --from=build-image /app/dist /app
COPY ./nginx.conf /etc/nginx/nginx.conf